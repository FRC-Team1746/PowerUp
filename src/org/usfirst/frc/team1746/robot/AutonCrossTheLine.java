package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonCrossTheLine {
	private TeleopDriveTrain m_driveTrain;
	private States currentState;
	
	public enum States {
		INIT,
		CROSSLINE,
		DRIVE_STOP,
		END,
	}

	public  AutonCrossTheLine(TeleopDriveTrain driveTrain){
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(.5);
	}
	
	public void auton(){
		switch(currentState){
		case INIT: 
			m_driveTrain.resetEncoders();
			m_driveTrain.setBrakeMode(true);
			currentState = States.CROSSLINE; // change to shoot init
		break;
		case CROSSLINE:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 140) {
				currentState = States.DRIVE_STOP;
			}
		break;
		case DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);
			currentState = States.END;
		break;
		case END:
			break;
		}
	}
	
	public String getState(){
		return currentState.name();
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
		SmartDashboard.putString("AutonState", getState());
	}
}
