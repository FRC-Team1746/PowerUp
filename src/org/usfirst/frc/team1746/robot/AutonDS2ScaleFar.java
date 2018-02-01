package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class AutonDS2ScaleFar {
	private DriveTrain m_driveTrain;
	private States currentState;
	
	public enum States {
		INIT,
		GRABBER,
		DRIVE_PAST_SWITCH,
		TURN_RIGHT,
		DRIVE_FORWARD,
		TURN_LEFT,
		DRIVE_MID_SCALE,
		TURN_TOWARD_SCALE,
		DRIVE_TO_SCALE,
		ELEVATOR,
		DRIVE_STOP,
		END,
	}
	
	public AutonDS2ScaleFar(DriveTrain driveTrain, int position) {
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(.5);
	}
	
	public void auton(){
		switch(currentState){
		case INIT: 
			m_driveTrain.resetEncoders();
			m_driveTrain.setBrakeMode(true);
			currentState = States.GRABBER;
		break;
		case GRABBER:
			currentState = States.DRIVE_PAST_SWITCH;
		case DRIVE_PAST_SWITCH:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 261.47) {
				m_driveTrain.resetEncoders();
				currentState = States.TURN_RIGHT;
			}
		break;
		case TURN_RIGHT:
			m_driveTrain.autonDriveStraight(0);
			m_driveTrain.autonDriveTurn(.5);
			if (m_driveTrain.getHeading() > Math.abs(3)) {
				currentState = States.DRIVE_FORWARD;
			}
		break;
		case DRIVE_FORWARD:
			m_driveTrain.autonDriveTurn(0);
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 220) {
				m_driveTrain.resetEncoders();
				currentState = States.TURN_LEFT;
			}
		break;
		case TURN_LEFT:
			m_driveTrain.autonDriveTurn(-.5);
			if (m_driveTrain.getHeading() > Math.abs(3)) {
				m_driveTrain.autonDriveTurn(0);
				currentState = States.DRIVE_MID_SCALE;
			} 
		break;
		case DRIVE_MID_SCALE:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 62.53) {
				m_driveTrain.resetEncoders();
				currentState = States.TURN_TOWARD_SCALE;
			}
		break;
		case TURN_TOWARD_SCALE:
			m_driveTrain.autonDriveStraight(0);
			m_driveTrain.autonDriveTurn(-.5);
			if (m_driveTrain.getHeading() > Math.abs(3)) {
				currentState = States.DRIVE_TO_SCALE;
			}
		break;
		case DRIVE_TO_SCALE:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 35.79) {
				m_driveTrain.resetEncoders();
				currentState = States.ELEVATOR;
			}
		break;
		case ELEVATOR:
			m_driveTrain.autonDriveStraight(0);
			currentState = States.DRIVE_STOP;
		break;
		case DRIVE_STOP:
			//m_driveTrain.autonDriveStraight(0);
			currentState = States.END;
		break; 
		case END:
			break;
		}
	}
			
		public String getState(){
			return currentState.name();
		}
			
		public void updateSmartDashboard() {
			SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
			SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
			SmartDashboard.putString("AutonState", getState());
		}
}