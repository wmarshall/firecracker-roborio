package com.elevendustries.bottlerocket;

public abstract class AddressableLED {

	private final AddressableChannel channel;

	public AddressableLED(AddressableChannel channel) {
		this.channel = channel;
	}

}
