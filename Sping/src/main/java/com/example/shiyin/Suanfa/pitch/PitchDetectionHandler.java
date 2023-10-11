

package com.example.shiyin.Suanfa.pitch;

import com.example.shiyin.Suanfa.AudioEvent;
import com.example.shiyin.Suanfa.Note;


public interface PitchDetectionHandler {

	void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent);
}
