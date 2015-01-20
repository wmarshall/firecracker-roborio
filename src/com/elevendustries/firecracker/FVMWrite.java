package com.elevendustries.firecracker;

public class FVMWrite extends FVMCommand {
	private byte channel;
	private byte val;

	public FVMWrite(int channel, int val) {
		//TODO: Clamping/Bounds
		this.channel = (byte) channel;
		this.val = (byte) val;
	}

	@Override
	public byte[] getBytes() {
		return new byte[] { (byte) FVMOp.FVM_WRITE_OPCODE.getVal(),
				this.channel, this.val };
	}

}
