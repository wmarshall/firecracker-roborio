package com.elevendustries.firecracker;

public class LEDChannel extends FVMChannel {
	private byte channel = 0;
	private byte val = 0;
	


	public LEDChannel(int channel, Firecracker firecracker){
		super(firecracker);
		this.channel = (byte) channel;
	}
	/**
	 * @return the val
	 */
	public byte getVal() {
		return val;
	}

	/**
	 * @param r the val to set
	 */
	public void setVal(int r) {
		this.val = (byte) r;
	}
	@Override
	public FVMCommand update() {
		return new FVMWrite(this.channel, this.getVal());
	}
	
}
