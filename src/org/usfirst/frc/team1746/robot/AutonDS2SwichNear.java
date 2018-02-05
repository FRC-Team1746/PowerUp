package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonDS2SwichNear {
	private DriveTrain m_DriveTrain;
	private States currentStates;
	
	public enum States {
		INIT,
		STRAIGHTSWITCH,
		TURN,
		SWERVE,
		DEPLOY,
		STOP
	}
	
	public AutonDS2SwichNear(DriveTrain drivetrain) {
		m_DriveTrain = drivetrain;
		currentStates = States.INIT;
		m_DriveTrain.setRampRate(20);
	}
	
	public void auton() {
		switch(currentStates){
		case INIT:
			m_DriveTrain.resetEncoders();
			m_DriveTrain.setBrakeMode(true);
			currentStates = States.STRAIGHTSWITCH;
		break;
		case STRAIGHTSWITCH:
			m_DriveTrain.autonDriveStraight(.99);
			if(m_DriveTrain.getEncoderRightInches() > 168){
				currentStates = States.TURN;
			}
		break;
		case TURN:
			m_DriveTrain.autonDriveTurn(.5);
			if(Math.abs(m_DriveTrain.getHeading()) > 5){
				currentStates = States.SWERVE;
			}
		break;
		case SWERVE:
			m_DriveTrain.autonDriveStraight(.5);
			if(m_DriveTrain.getEncoderRightInches() > 73.25){
				currentStates = States.DEPLOY;
			}
		break;
		case DEPLOY:
			currentStates = States.STOP;
		break;
		case STOP:
		break;
		
		}
	}
	public String getState(){
		return currentStates.name();
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", m_DriveTrain.getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", m_DriveTrain.getEncoderRightInches());
		SmartDashboard.putString("AutonState", getState());
	}


}
