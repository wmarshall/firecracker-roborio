package com.elevendustries.firecracker;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Firecracker extends Subsystem implements Runnable {

	public static final int FIRECRACKER_I2C_ADDR = 0x11;
	public static final int FIRECRACKER_CHANNEL_COUNT = 12;

	private final I2C firecracker;
	private final SPI firecrackerSPI = null; // TODO: SPI Support
	private final BlockingQueue<FVMCommand> data;
	private byte errorCode = 0;
	private ArrayList<FVMChannel> channels = new ArrayList<FVMChannel>(
			FIRECRACKER_CHANNEL_COUNT);

	private boolean connected = false;

	public Firecracker(int i2cAddress, Port port) {
		firecracker = new I2C(port, i2cAddress);
		data = new LinkedBlockingQueue<FVMCommand>();
	}

	public Firecracker() {
		this(FIRECRACKER_I2C_ADDR, Port.kOnboard);
	}

	public void sendCommand(FVMCommand command) throws InterruptedException {
		data.put(command);
	}

	private byte[] normalizeCommand(FVMCommand command) {
		byte[] commandData = command.getBytes();
		PushCommand push = new PushCommand(commandData);
		byte[] pushData = push.getBytes();
		byte[] retData = new byte[pushData.length];
		System.arraycopy(pushData, 0, retData, 0, pushData.length);
		retData[retData.length - 1] = command.getBytes()[0];
		return retData;
	}

	@Override
	public void run() {
		try {
			FVMCommand command = data.take();
			if (!firecracker.writeBulk(normalizeCommand(command))) {
				connected = false;
			}
			byte[] buf = new byte[1];
			if (!firecracker.readOnly(buf, 1)) {
				connected = false;
			}
			errorCode = buf[0];
			connected = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isError() {
		return errorCode != 0;
	}

	public boolean isConnected() {
		return connected;
	}

	public byte getError() {
		return errorCode;
	}

	/**
	 * @return the channels
	 */
	public ArrayList<FVMChannel> getChannels() {
		return channels;
	}

	public void register(FVMChannel fvmChannel) {
		if (!channels.contains(fvmChannel)) {
			channels.add(fvmChannel);
		}
	}

	public void unregister(FVMChannel fvmChannel) {
		channels.remove(fvmChannel);
	}

	public void updateAllChannels() {
		for (FVMChannel fvmChannel : channels) {
			try {
				sendCommand(fvmChannel.update());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void initDefaultCommand() {
		new UpdateChannels(this).start();
	}
}
