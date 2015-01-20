package com.elevendustries.firecracker;

public class RGBChannel {

	public int[] RED = new int[] { 255, 0, 0 };
	public int[] GREEN = new int[] {0, 255, 0};
	public int[] BLUE = new int[] {0, 0, 255};
	public int[] ORANGE = new int[] {255, 32, 0};
	public int[] YELLOW = new int[] {255, 64, 0};
	public int[] PINK = new int[] {255, 0, 128};
	public int[] WHITE = new int[] {255, 255, 255};
	public int[] OFF = new int[] {0, 0, 0};


	private final LEDChannel rChannel;
	private final LEDChannel gChannel;
	private final LEDChannel bChannel;

	public RGBChannel(int rChannel, int gChannel, int bChannel,
			Firecracker owner) {
		this.rChannel = new LEDChannel(rChannel, owner);
		this.gChannel = new LEDChannel(gChannel, owner);
		this.bChannel = new LEDChannel(bChannel, owner);
	}

	public void setR(int R) {
		rChannel.setVal(R);
	}

	public void setG(int G) {
		gChannel.setVal(G);
	}

	public void setB(int B) {
		bChannel.setVal(B);
	}

	public void setRGB(int R, int G, int B) {
		setR(R);
		setG(G);
		setB(B);
	}

}
