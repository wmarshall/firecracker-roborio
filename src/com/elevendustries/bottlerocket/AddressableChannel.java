package com.elevendustries.bottlerocket;

import com.elevendustries.firecracker.FVMChannel;
import com.elevendustries.firecracker.FVMCommand;
import com.elevendustries.firecracker.Firecracker;

public class AddressableChannel extends FVMChannel {
	
	private ColorType type;
	private int length;
	private TricolorLED[] leds; 

	public AddressableChannel(Firecracker owner, ColorType type, int length) {
		super(owner);
		this.type = type;
		this.length = length;
		this.leds = new TricolorLED[length];
		for (int i = 0; i < leds.length; i++) {
			leds[i] = new TricolorLED(this.type);
		}
	}

	
	public TricolorLED getLED(int index){
		return leds[index];
	}
	
	
	@Override
	public FVMCommand update() {
		for (int i = 0; i < leds.length; i++) {
			
		}
		// TODO Auto-generated method stub
		return null;
	}

}
