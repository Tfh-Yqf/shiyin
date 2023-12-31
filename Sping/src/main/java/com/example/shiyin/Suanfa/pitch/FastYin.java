
package com.example.shiyin.Suanfa.pitch;

import com.example.shiyin.Suanfa.util.fft.FloatFFT;

public final class FastYin implements PitchDetector {

	private static final double DEFAULT_THRESHOLD = 0.20;


	public static final int DEFAULT_BUFFER_SIZE = 2048;


	public static final int DEFAULT_OVERLAP = 1536;


	private final double threshold;


	private final float sampleRate;


	private final float[] yinBuffer;	
	

	private final PitchDetectionResult result;
	

	private final float[] audioBufferFFT;
	

	private final  float[] kernel;
	

	private final float[] yinStyleACF;
	

	private final FloatFFT fft;


	public FastYin(final float audioSampleRate, final int bufferSize) {
		this(audioSampleRate, bufferSize, DEFAULT_THRESHOLD);
	}


	public FastYin(final float audioSampleRate, final int bufferSize, final double yinThreshold) {
		this.sampleRate = audioSampleRate;
		this.threshold = yinThreshold;
		yinBuffer = new float[bufferSize / 2];
		//Initializations for FFT difference step
		audioBufferFFT = new float[2*bufferSize];
		kernel = new float[2*bufferSize];
		yinStyleACF = new float[2*bufferSize];
		fft = new FloatFFT(bufferSize);
		result = new PitchDetectionResult();
	}


	public PitchDetectionResult getPitch(final float[] audioBuffer) {

		final int tauEstimate;
		final float pitchInHertz;

		// step 2
		difference(audioBuffer);

		// step 3
		cumulativeMeanNormalizedDifference();

		// step 4
		tauEstimate = absoluteThreshold();

		// step 5
		if (tauEstimate != -1) {
			final float betterTau = parabolicInterpolation(tauEstimate);

			// step 6
			// TODO Implement optimization for the AUBIO_YIN algorithm.
			// 0.77% => 0.5% error rate,
			// using the data of the YIN paper
			// bestLocalEstimate()

			// conversion to Hz
			pitchInHertz = sampleRate / betterTau;
		} else{
			// no pitch found
			pitchInHertz = -1;
		}
		
		result.setPitch(pitchInHertz);

		return result;
	}


	private void difference(final float[] audioBuffer) {
		// POWER TERM CALCULATION
		// ... for the power terms in equation (7) in the Yin paper
		float[] powerTerms = new float[yinBuffer.length];
		for (int j = 0; j < yinBuffer.length; ++j) {
			powerTerms[0] += audioBuffer[j] * audioBuffer[j];
		}
		// now iteratively calculate all others (saves a few multiplications)
		for (int tau = 1; tau < yinBuffer.length; ++tau) {
			powerTerms[tau] = powerTerms[tau-1] - audioBuffer[tau-1] * audioBuffer[tau-1] + audioBuffer[tau+yinBuffer.length] * audioBuffer[tau+yinBuffer.length];  
		}

		// YIN-STYLE AUTOCORRELATION via FFT
		// 1. data
		for (int j = 0; j < audioBuffer.length; ++j) {
			audioBufferFFT[2*j] = audioBuffer[j];
			audioBufferFFT[2*j+1] = 0;
		}
		fft.complexForward(audioBufferFFT);
		
		// 2. half of the data, disguised as a convolution kernel
		for (int j = 0; j < yinBuffer.length; ++j) {
			kernel[2*j] = audioBuffer[(yinBuffer.length-1)-j];
			kernel[2*j+1] = 0;
			kernel[2*j+audioBuffer.length] = 0;
			kernel[2*j+audioBuffer.length+1] = 0;
		}
		fft.complexForward(kernel);

		// 3. convolution via complex multiplication
		for (int j = 0; j < audioBuffer.length; ++j) {
			yinStyleACF[2*j]   = audioBufferFFT[2*j]*kernel[2*j] - audioBufferFFT[2*j+1]*kernel[2*j+1]; // real
			yinStyleACF[2*j+1] = audioBufferFFT[2*j+1]*kernel[2*j] + audioBufferFFT[2*j]*kernel[2*j+1]; // imaginary
		}
		fft.complexInverse(yinStyleACF, true);
		
		// CALCULATION OF difference function
		// ... according to (7) in the Yin paper.
		for (int j = 0; j < yinBuffer.length; ++j) {
			// taking only the real part
			yinBuffer[j] = powerTerms[0] + powerTerms[j] - 2 * yinStyleACF[2 * (yinBuffer.length - 1 + j)];
		}
	}


	private void cumulativeMeanNormalizedDifference() {
		int tau;
		yinBuffer[0] = 1;
		float runningSum = 0;
		for (tau = 1; tau < yinBuffer.length; tau++) {
			runningSum += yinBuffer[tau];
			yinBuffer[tau] *= tau / runningSum;
		}
	}


	private int absoluteThreshold() {
		// Uses another loop construct
		// than the AUBIO implementation
		int tau;
		// first two positions in yinBuffer are always 1
		// So start at the third (index 2)
		for (tau = 2; tau < yinBuffer.length; tau++) {
			if (yinBuffer[tau] < threshold) {
				while (tau + 1 < yinBuffer.length && yinBuffer[tau + 1] < yinBuffer[tau]) {
					tau++;
				}
				// found tau, exit loop and return
				// store the probability
				// From the YIN paper: The threshold determines the list of
				// candidates admitted to the set, and can be interpreted as the
				// proportion of aperiodic power tolerated
				// within a periodic signal.
				//
				// Since we want the periodicity and and not aperiodicity:
				// periodicity = 1 - aperiodicity
				result.setProbability(1 - yinBuffer[tau]);
				break;
			}
		}

		
		// if no pitch found, tau => -1
		if (tau == yinBuffer.length || yinBuffer[tau] >= threshold || result.getProbability() > 1.0) {
			tau = -1;
			result.setProbability(0);
			result.setPitched(false);	
		} else {
			result.setPitched(true);
		}

		return tau;
	}


	private float parabolicInterpolation(final int tauEstimate) {
		final float betterTau;
		final int x0;
		final int x2;

		if (tauEstimate < 1) {
			x0 = tauEstimate;
		} else {
			x0 = tauEstimate - 1;
		}
		if (tauEstimate + 1 < yinBuffer.length) {
			x2 = tauEstimate + 1;
		} else {
			x2 = tauEstimate;
		}
		if (x0 == tauEstimate) {
			if (yinBuffer[tauEstimate] <= yinBuffer[x2]) {
				betterTau = tauEstimate;
			} else {
				betterTau = x2;
			}
		} else if (x2 == tauEstimate) {
			if (yinBuffer[tauEstimate] <= yinBuffer[x0]) {
				betterTau = tauEstimate;
			} else {
				betterTau = x0;
			}
		} else {
			float s0, s1, s2;
			s0 = yinBuffer[x0];
			s1 = yinBuffer[tauEstimate];
			s2 = yinBuffer[x2];
			// fixed AUBIO implementation, thanks to Karl Helgason:
			// (2.0f * s1 - s2 - s0) was incorrectly multiplied with -1
			betterTau = tauEstimate + (s2 - s0) / (2 * (2 * s1 - s2 - s0));
		}
		return betterTau;
	}
}
