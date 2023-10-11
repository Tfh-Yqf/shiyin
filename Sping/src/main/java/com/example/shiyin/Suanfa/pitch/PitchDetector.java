

package com.example.shiyin.Suanfa.pitch;


public interface PitchDetector {

	PitchDetectionResult getPitch(final float[] audioBuffer);
}
