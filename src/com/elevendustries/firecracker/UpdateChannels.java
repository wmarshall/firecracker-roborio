package com.elevendustries.firecracker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpdateChannels extends Command {

	private final Firecracker firecracker ;
	private final Timer timer;
	
	private static final double UPDATE_HERTZ = 30;
	
    public UpdateChannels(Firecracker firecracker) {
        requires(firecracker);
        setInterruptible(false);
        this.firecracker = firecracker;
        this.timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 1/UPDATE_HERTZ){
    		firecracker.updateAllChannels();
    		timer.reset();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
