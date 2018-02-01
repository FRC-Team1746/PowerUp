package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class AutonDS2ScaleFar {
	private DriveTrain m_driveTrain;
	private States firstState;
	private States secondState;
	private States thirdState;
	private States fourthState;
	
	public enum States {
		INIT,
		GRABBER,
		DRIVE_PAST_SWITCH1,
		TURN_RIGHT1,
		DRIVE_FORWARD1,
		TURN_LEFT1,
		DRIVE_MID_SCALE1,
		TURN_TOWARD_SCALE1,
		DRIVE_TO_SCALE1,
		ELEVATOR1,
		DRIVE_STOP1,
		END,
		DRIVE_OUT2,
		TURN_AWAY_SWITCH2,
		DRIVE_AWAY_SWITCH2,
		TURN_RIGHT_FORWARD2,
		DRIVE_PAST_SWITCH2,
		TURN_RIGHT2,
		DRIVE_FORWARD2,
		TURN_LEFT2,
		DRIVE_MID_SCALE2,
		TURN_TOWARD_SCALE2,
		DRIVE_TO_SCALE2,
		ELEVATOR2,
		DRIVE_STOP2,
	}
	
	public AutonDS2ScaleFar(DriveTrain driveTrain, int position) {
		m_driveTrain = driveTrain;
		secondState = States.INIT;
		m_driveTrain.setRampRate(.5);
	}
	
	public void auton(){
		switch(firstState){
		case INIT: 
			m_driveTrain.resetEncoders();
			m_driveTrain.setBrakeMode(true);
			firstState = States.GRABBER;
		break;
		case GRABBER:
			firstState = States.DRIVE_PAST_SWITCH1;
		case DRIVE_PAST_SWITCH1:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 261.47) {
				m_driveTrain.resetEncoders();
				firstState = States.TURN_RIGHT1;
			}
		break;
		case TURN_RIGHT1:
			m_driveTrain.autonDriveStraight(0);
			m_driveTrain.autonDriveTurn(.5);
			if (m_driveTrain.getHeading() > Math.abs(3)) {
				firstState = States.DRIVE_FORWARD1;
			}
		break;
		case DRIVE_FORWARD1:
			m_driveTrain.autonDriveTurn(0);
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 220) {
				m_driveTrain.resetEncoders();
				firstState = States.TURN_LEFT1;
			}
		break;
		case TURN_LEFT1:
			m_driveTrain.autonDriveTurn(-.5);
			if (m_driveTrain.getHeading() > Math.abs(3)) {
				m_driveTrain.autonDriveTurn(0);
				firstState = States.DRIVE_MID_SCALE1;
			} 
		break;
		case DRIVE_MID_SCALE1:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 62.53) {
				m_driveTrain.resetEncoders();
				firstState = States.TURN_TOWARD_SCALE1;
			}
		break;
		case TURN_TOWARD_SCALE1:
			m_driveTrain.autonDriveStraight(0);
			m_driveTrain.autonDriveTurn(-.5);
			if (m_driveTrain.getHeading() > Math.abs(3)) {
				firstState = States.DRIVE_TO_SCALE1;
			}
		break;
		case DRIVE_TO_SCALE1:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 35.79) {
				m_driveTrain.resetEncoders();
				firstState = States.ELEVATOR1;
			}
		break;
		case ELEVATOR1:
			m_driveTrain.autonDriveStraight(0);
			firstState = States.DRIVE_STOP1;
		break;
		case DRIVE_STOP1:
			//m_driveTrain.autonDriveStraight(0);
			firstState = States.END;
		break; 
		case END:
			break;
		}
	}
			
		public String getState(){
			return secondState.name();
		}
			
		public void updateSmartDashboard() {
			SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
			SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
			SmartDashboard.putString("AutonState", getState());
		}

		public void auton2(){
			switch(secondState){
			case INIT: 
				m_driveTrain.resetEncoders();
				m_driveTrain.setBrakeMode(true);
				secondState = States.GRABBER;
			break;
			case GRABBER:
				secondState = States.DRIVE_OUT2;
			case DRIVE_OUT2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 40) {
					m_driveTrain.resetEncoders();
					secondState = States.TURN_AWAY_SWITCH2;
				}
			break;
			case TURN_AWAY_SWITCH2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(3)) {
					secondState = States.DRIVE_AWAY_SWITCH2;
				}
			break;
			case DRIVE_AWAY_SWITCH2:
				m_driveTrain.autonDriveTurn(0);
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 40) {
					m_driveTrain.resetEncoders();
					secondState = States.TURN_LEFT2;
				}
			break;
			case TURN_RIGHT_FORWARD2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(3)) {
					secondState = States.DRIVE_PAST_SWITCH2;
				}
			break;
			case DRIVE_PAST_SWITCH2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 221.47) {
					m_driveTrain.resetEncoders();
					secondState = States.TURN_RIGHT2;
				}
			break;
			case TURN_RIGHT2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(3)) {
					secondState = States.DRIVE_FORWARD2;
				}
			break;
			case DRIVE_FORWARD2:
				m_driveTrain.autonDriveTurn(0);
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 220) {
					m_driveTrain.resetEncoders();
					secondState = States.TURN_LEFT2;
				}
			break;
			case TURN_LEFT2:
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(3)) {
					m_driveTrain.autonDriveTurn(0);
					secondState = States.DRIVE_MID_SCALE2;
				} 
			break;
			case DRIVE_MID_SCALE2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 62.53) {
					m_driveTrain.resetEncoders();
					secondState = States.TURN_TOWARD_SCALE2;
				}
			break;
			case TURN_TOWARD_SCALE2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(3)) {
					secondState = States.DRIVE_TO_SCALE2;
				}
			break;
			case DRIVE_TO_SCALE2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 35.79) {
					m_driveTrain.resetEncoders();
					secondState = States.ELEVATOR2;
				}
			break;
			case ELEVATOR2:
				m_driveTrain.autonDriveStraight(0);
				secondState = States.DRIVE_STOP2;
			break;
			case DRIVE_STOP2:
				//m_driveTrain.autonDriveStraight(0);
				secondState = States.END;
			break; 
			case END:
			break;
		}
	}
		
	public String getState2(){
		return secondState.name();
	}
		
	public void updateSmartDashboard2() {
		SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
		SmartDashboard.putString("AutonState", getState2());
	}
}