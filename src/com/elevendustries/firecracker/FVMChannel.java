package com.elevendustries.firecracker;

public abstract class FVMChannel {
	private Firecracker firecracker;
	
	public FVMChannel(Firecracker owner){
		firecracker = owner;
		owner.register(this);
	}
	public Firecracker getFirecracker(){
		return firecracker;
	}
    public abstract FVMCommand update();
}
