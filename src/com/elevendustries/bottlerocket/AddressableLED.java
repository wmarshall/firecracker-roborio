package com.elevendustries.bottlerocket;

public abstract class AddressableLED {

	/**
	 * @return null if clean, modified bytes if dirty
	 */
	public abstract byte[] getData();

}
