
package com.example.shiyin.Suanfa.io.jvm;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;

import com.example.shiyin.Suanfa.io.TarsosDSPAudioFormat;
import com.example.shiyin.Suanfa.io.TarsosDSPAudioInputStream;


public class JVMAudioInputStream implements TarsosDSPAudioInputStream {
	
	private final AudioInputStream underlyingStream;
	private final TarsosDSPAudioFormat tarsosDSPAudioFormat;
	public JVMAudioInputStream(AudioInputStream stream){
		this.underlyingStream = stream;
		this.tarsosDSPAudioFormat = JVMAudioInputStream.toTarsosDSPFormat(stream.getFormat());
	}

	@Override
	public long skip(long bytesToSkip) throws IOException {
		return underlyingStream.skip(bytesToSkip);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return underlyingStream.read(b, off, len);
	}

	@Override
	public void close() throws IOException {
		underlyingStream.close();
	}

	@Override
	public long getFrameLength() {

		return underlyingStream.getFrameLength();
	}

	@Override
	public TarsosDSPAudioFormat getFormat() {
		return tarsosDSPAudioFormat;
	}
	

	public static TarsosDSPAudioFormat toTarsosDSPFormat(AudioFormat format) {
		boolean isSigned = format.getEncoding() == Encoding.PCM_SIGNED;
		TarsosDSPAudioFormat tarsosDSPFormat = new TarsosDSPAudioFormat(format.getSampleRate(), format.getSampleSizeInBits(), format.getChannels(), isSigned, format.isBigEndian());
		return tarsosDSPFormat;
	}


	public static AudioFormat toAudioFormat(TarsosDSPAudioFormat format) {
		boolean isSigned = format.getEncoding() == TarsosDSPAudioFormat.Encoding.PCM_SIGNED;
		AudioFormat audioFormat = new AudioFormat(format.getSampleRate(), format.getSampleSizeInBits(), format.getChannels(), isSigned, format.isBigEndian());
		return audioFormat;
	}
	
}
