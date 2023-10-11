
package com.example.shiyin.Suanfa.pitch;



public class FFTPitch implements PitchDetector {
	
	private final PitchDetectionResult result;
	public FFTPitch(int sampleRate,int bufferSize){
		result = new PitchDetectionResult();
	}

	@Override
	public PitchDetectionResult getPitch(float[] audioBuffer) {
		
		
		return result;
	}
	

}
