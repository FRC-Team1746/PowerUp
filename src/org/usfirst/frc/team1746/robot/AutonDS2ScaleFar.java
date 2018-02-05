package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class AutonDS2ScaleFar {
	private DriveTrain m_driveTrain;
	private int m_position;
	private States currentState;
	
	
	public enum States {
		INIT,
		GRABBER1,
		DRIVE_PAST_SWITCH1,
		TURN_RIGHT1,
		DRIVE_FORWARD1,
		TURN_LEFT1,
		DRIVE_MID_SCALE1,
		TURN_TOWARD_SCALE1,
		DRIVE_TO_SCALE1,
		ELEVATOR1,
		ELEVATOR_STOP1,
		GRABBER2,
		DRIVE_OUT2,
		TURN_AWAYL_SWITCH2,
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
		ELEVATOR_STOP2,
		GRABBER4,
		DRIVE_PAST_SWITCH4,
		TURN_LEFT4,
		DRIVE_FORWARD4,
		TURN_RIGHT4,
		DRIVE_MID_SCALE4,
		TURN_TOWARD_SCALE4,
		DRIVE_TO_SCALE4,
		ELEVATOR4,
		ELEVATOR_STOP4,
		END,
	}
	
	public AutonDS2ScaleFar(DriveTrain driveTrain, double d) {
		m_driveTrain = driveTrain;
		m_position = (int) d;
		currentState = States.INIT;
		m_driveTrain.setRampRate(.5);
	}
	
	public void auton(){
		if (m_position == 1) {
			switch(currentState){
			case INIT: 
				m_driveTrain.resetEncoders();
				m_driveTrain.setBrakeMode(true);
				currentState = States.GRABBER1;
			break;
			case GRABBER1:
				currentState = States.DRIVE_PAST_SWITCH1;	
			break;
			case DRIVE_PAST_SWITCH1:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 261.47) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_RIGHT1;
				}
			break;
			case TURN_RIGHT1:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_FORWARD1;
				}
			break;
			case DRIVE_FORWARD1:
				m_driveTrain.autonDriveTurn(0);
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 220) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_LEFT1;
				}
			break;
			case TURN_LEFT1:
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					m_driveTrain.autonDriveTurn(0);
					currentState = States.DRIVE_MID_SCALE1;
				} 
			break;
			case DRIVE_MID_SCALE1:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 62.53) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_TOWARD_SCALE1;
				}
			break;
			case TURN_TOWARD_SCALE1:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_TO_SCALE1;
				}
			break;
			case DRIVE_TO_SCALE1:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 35.79) {
					m_driveTrain.resetEncoders();
					currentState = States.ELEVATOR1;
				}
			break;
			case ELEVATOR1:
				m_driveTrain.autonDriveStraight(0);
				currentState = States.ELEVATOR_STOP1;
			break;
			case ELEVATOR_STOP1:
				//m_driveTrain.autonDriveStraight(0);
				currentState = States.END;
			break; 
			case END:
			break;
			}
		}else if (m_position == 2) {
			switch(currentState){
			case INIT: 
				m_driveTrain.resetEncoders();
				m_driveTrain.setBrakeMode(true);
				currentState = States.GRABBER2;
			break;
			case GRABBER2:
				currentState = States.DRIVE_OUT2;
			case DRIVE_OUT2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 40) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_AWAYL_SWITCH2;
				}
			break;
			case TURN_AWAYL_SWITCH2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_AWAY_SWITCH2;
				}
			break;
			case DRIVE_AWAY_SWITCH2:
				m_driveTrain.autonDriveTurn(0);
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 40) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_LEFT2;
				}
			break;
			case TURN_RIGHT_FORWARD2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_PAST_SWITCH2;
				}
			break;
			case DRIVE_PAST_SWITCH2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 221.47) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_RIGHT2;
				}
			break;
			case TURN_RIGHT2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_FORWARD2;
				}
			break;
			case DRIVE_FORWARD2:
				m_driveTrain.autonDriveTurn(0);
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 220) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_LEFT2;
				}
			break;
			case TURN_LEFT2:
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					m_driveTrain.autonDriveTurn(0);
					currentState = States.DRIVE_MID_SCALE2;
				} 
			break;
			case DRIVE_MID_SCALE2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 62.53) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_TOWARD_SCALE2;
				}
			break;
			case TURN_TOWARD_SCALE2:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_TO_SCALE2;
				}
			break;
			case DRIVE_TO_SCALE2:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 35.79) {
					m_driveTrain.resetEncoders();
					currentState = States.ELEVATOR2;
				}
			break;
			case ELEVATOR2:
				m_driveTrain.autonDriveStraight(0);
				currentState = States.ELEVATOR_STOP2;
			break;
			case ELEVATOR_STOP2:
				//m_driveTrain.autonDriveStraight(0);
				currentState = States.END;
			break; 
			case END:
			break;
			}
		}else if (m_position == 4) {
			switch(currentState){
			case INIT: 
				m_driveTrain.resetEncoders();
				m_driveTrain.setBrakeMode(true);
				currentState = States.GRABBER4;
			break;
			case GRABBER4:
				currentState = States.DRIVE_PAST_SWITCH4;	
			break;
			case DRIVE_PAST_SWITCH4:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 100) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_LEFT4;
				}
			break;
			case TURN_LEFT4:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(-.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_FORWARD4;
				}
			break;
			case DRIVE_FORWARD4:
				m_driveTrain.autonDriveTurn(0);
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 80) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_RIGHT4;
				}
			break;
			case TURN_RIGHT4:
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					m_driveTrain.autonDriveTurn(0);
					currentState = States.DRIVE_MID_SCALE4;
				} 
			break;
			case DRIVE_MID_SCALE4:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 20) {
					m_driveTrain.resetEncoders();
					currentState = States.TURN_TOWARD_SCALE4;
				}
			break;
			case TURN_TOWARD_SCALE4:
				m_driveTrain.autonDriveStraight(0);
				m_driveTrain.autonDriveTurn(.5);
				if (m_driveTrain.getHeading() > Math.abs(90)) {
					currentState = States.DRIVE_TO_SCALE4;
				}
			break;
			case DRIVE_TO_SCALE4:
				m_driveTrain.autonDriveStraight(.5);
				if (m_driveTrain.getEncoderLeftInches() > 10) {
					m_driveTrain.resetEncoders();
					currentState = States.ELEVATOR4;
				}
			break;
			case ELEVATOR4:
				m_driveTrain.autonDriveStraight(0);
				currentState = States.ELEVATOR_STOP4;
			break;
			case ELEVATOR_STOP4:
				//m_driveTrain.autonDriveStraight(0);
				currentState = States.END;
			break; 
			case END:
			break;
			}
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