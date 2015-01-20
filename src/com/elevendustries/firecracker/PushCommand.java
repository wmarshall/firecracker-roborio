package com.elevendustries.firecracker;


public class PushCommand extends FVMCommand {
	
	private byte[] data = null; 
		
	public PushCommand(byte[] data) {
		this.data = data;
		for(int i =0; i < data.length/2; i++){
			byte temp = data[i];
			data[i] = data[data.length - 1 - i];
			data[data.length - 1 - i] = temp;
		}
	}

	@Override
	public byte[] getBytes() {
		byte[] retData  = new byte[data.length];
		System.arraycopy(data, 0, retData, 1, data.length-1);
		retData[0] = (byte) FVMOp.FVM_PUSH_OPCODE.getVal();
		return retData;
	}

}
