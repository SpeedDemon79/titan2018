package org.usfirst.frc.team2789.robot.subsystems;

import java.util.Set;

import org.usfirst.frc.team2789.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	// Storage variables for piston commands
	private DoubleSolenoid.Value m_mainCylCmd;
	private boolean m_extCylCmd;
	private Talon m_climbMtr;
	private boolean m_climbOn;
	private Solenoid m_extCylIn;
	private Solenoid m_extCylOut;
	// DoubleSolenoids to control cylinders
	private DoubleSolenoid m_mainCyl;
//	private DoubleSolenoid m_extCyl;
	
	public Climber() {
		// Construct DoubleSolenoids with corresponding channels
		this.m_mainCyl = new DoubleSolenoid(
				RobotMap.DS_MAIN_CYL_FWD,
				RobotMap.DS_MAIN_CYL_REV);
//		this.m_extCyl = new DoubleSolenoid(
//				RobotMap.DS_EXT_CYL_FWD,
//				RobotMap.DS_EXT_CYL_REV);
		
		this.m_extCylOut = new Solenoid(RobotMap.DS_EXT_CYL_FWD);
		this.m_extCylIn = new Solenoid(RobotMap.DS_EXT_CYL_REV);
		
		this.m_climbMtr = new Talon(RobotMap.MTR_CLIMB);
		
		// Reset all values
		this.reset();
	}
	
	public void reset() {
		// Commands to retract
		this.m_mainCylCmd = DoubleSolenoid.Value.kReverse;
		this.m_extCylCmd = false;
		this.m_climbOn = false;
	}
	
	//update cylinders
	public void update() {
		this.m_mainCyl.set(this.m_mainCylCmd);
		
		if (this.m_extCylCmd){
			this.m_extCylOut.set(true);
			this.m_extCylIn.set(false);
		}
		else {
			this.m_extCylOut.set(false);
			this.m_extCylIn.set(true);
		}
		
		// command Talons.
		if(this.m_climbOn) {
			this.m_climbMtr.set(RobotMap.CLIMB_VAL);
		}
		else {
			this.m_climbMtr.set(0.0);
		}
	}
		
	// setter method
	//TODO
	public void setClimbOn(boolean climbOn) {
		this.m_climbOn = climbOn;
	}
	
	//Main
	public void fireMainCyl(boolean fire) {
		if(fire) {
			this.m_mainCylCmd = DoubleSolenoid.Value.kForward;
		}
		else {
			this.m_mainCylCmd = DoubleSolenoid.Value.kReverse;
		
		}
	}
	//Ext
	public void fireExtCyl(boolean fire) {
			this.m_extCylCmd = fire;
		
	}

	@Override
	protected void initDefaultCommand() {} // not used
}
