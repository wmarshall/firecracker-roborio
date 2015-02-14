package com.elevendustries.bottlerocket;

public class TricolorLED extends AddressableLED {

	private final ColorType type;
	private int rVal = 0;
	private int gVal = 0;
	private int bVal = 0;
	private boolean dirty;

	public TricolorLED(ColorType type) {
		this.type = type;
	}

	@Override
	public byte[] getData() {
		if (dirty) {
			switch (type) {
			case RGB:
				return new byte[] { getR(), getG(), getB() };
			case RBG:
				return new byte[] { getR(), getB(), getG() };
			case BGR:
				return new byte[] { getB(), getG(), getR() };
			case BRG:
				return new byte[] { getB(), getR(), getG() };
			case GRB:
				return new byte[] { getG(), getR(), getB() };
			case GBR:
				return new byte[] { getG(), getB(), getR() };
			}
		}
		return null;
	}

	/**
	 * @return the rVal
	 */
	public byte getR() {
		return (byte) rVal;
	}

	/**
	 * @param rVal
	 *            the rVal to set
	 */
	public void setR(int rVal) {
		dirty = true;
		this.rVal = rVal;
	}

	/**
	 * @return the gVal
	 */
	public byte getG() {
		return (byte) gVal;
	}

	/**
	 * @param gVal
	 *            the gVal to set
	 */
	public void setG(int gVal) {
		dirty = true;
		this.gVal = gVal;
	}

	/**
	 * @return the bVal
	 */
	public byte getB() {
		return (byte) bVal;
	}

	/**
	 * @param bVal
	 *            the bVal to set
	 */
	public void setB(int bVal) {
		dirty = true;
		this.bVal = bVal;
	}

	/**
	 * @return the type
	 */
	public ColorType getType() {
		return type;
	}

}
