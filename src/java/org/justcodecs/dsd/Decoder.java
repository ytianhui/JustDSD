package org.justcodecs.dsd;

public class Decoder {
	public static class PCMFormat {
		public int bitsPerSample;
		public int sampleRate;
		public int channels;
		public boolean lsb;
	}
	
	public static class DecodeException extends Exception {
		public DecodeException(String msg, Throwable reason) {
			super(msg, reason);
		}
	}
	
	protected FMTChunk fmt;
	protected PCMFormat pcmf;

	public PCMFormat getPCMFormat() {
		if (fmt == null)
			throw new IllegalArgumentException("Not initialized yet or invalid");
		if (pcmf == null) {
			pcmf = new PCMFormat();
			switch(fmt.sampleFreq) {
			case 5644800:
				pcmf.bitsPerSample = 32;
				pcmf.sampleRate = 5644800 / 32;
				pcmf.channels = fmt.channelNum;
				pcmf.lsb = fmt.bitPerSample == 1;
			}
		}
		return pcmf;
	}
	
	public void init(DSDStream ds) throws DecodeException {
		DSDChunk.read(ds);
		fmt = FMTChunk.read(ds);
		System.out.printf("FMT:%s%n", fmt);
		DATAChunk dc = DATAChunk.read(ds);
		System.out.printf("DATA:%s%n", dc);
	}
	
	public int decodePCM(byte[]...channels) {
		return 0;
		
	}
}
