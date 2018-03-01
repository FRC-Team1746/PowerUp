package org.usfirst.frc.team1746.robot;


import java.lang.Math;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonSwitch2ScaleNear {
	private TeleopDriveTrain m_driveTrain;
	private Direction instructions;
	private String Positions;
	
	
	
	private enum Direction {
		INIT,
		L_TURN,
		FORWARD,
		R_TURN,
		FORWARD1,
		R_TURN1,
		FORWARD2,
		R_TURN2,
		FORWARD3,
		FORWARD4,
		BACK,
		BACK1,
		DRIVE_STOP,
		END,
		L_TURN1,
		L_TURN2,
		L_TURN3,
	}
	

	
	public  AutonSwitch2ScaleNear(TeleopDriveTrain driveTrain){
		m_driveTrain = driveTrain;
		instructions = Direction.INIT;
		m_driveTrain.setRampRate(.5);
	}

	public void nearSwitch2Scale(){
		if (Positions == "leftFirst") {
		switch(instructions){
		case INIT: 
			m_driveTrain.resetEncoders();
			//m_driveTrain.resetGyro();
			m_driveTrain.setBrakeMode(true);
			instructions = Direction.BACK;
		break;
		case BACK:
			m_driveTrain.autonDriveStraight(-0.5);
			if(m_driveTrain.bothEncoderInchValues()> 24) {
				instructions=Direction.L_TURN;
			}
			break;
		case L_TURN:
			m_driveTrain.autonDriveTurn(-0.4);
//			if (Math.abs(m_driveTrain.getHeading())> 85) {
//				instructions = Direction.FORWARD;
//				m_driveTrain.resetEncoders();
//			}
		break;
		case FORWARD:
			m_driveTrain.autonDriveStraight(0.5);
			if(m_driveTrain.bothEncoderInchValues()> 60) {
				instructions=Direction.R_TURN;
			}
			break;
		case R_TURN:
			m_driveTrain.autonDriveTurn(0.5);
//			if(Math.abs(m_driveTrain.getHeading())< 5) {
//				instructions = Direction.FORWARD1;	
//				m_driveTrain.resetEncoders();
//			}
			break;
		case FORWARD1:
			m_driveTrain.autonDriveStraight(0.5);
			if(m_driveTrain.bothEncoderInchValues()>24) {
				instructions=Direction.BACK1;
				m_driveTrain.resetEncoders();
			}
			break;
		case BACK1:
			m_driveTrain.autonDriveStraight(-0.5);
			if(m_driveTrain.bothEncoderInchValues() >36 ) {
				instructions=Direction.L_TURN1;
			}
			break;
		case L_TURN1:
			m_driveTrain.autonDriveTurn(-0.5);
//			if(m_driveTrain.getHeading()>90) {
//				instructions = Direction.FORWARD3;	
//				m_driveTrain.resetEncoders();
//			}
			break;
		case FORWARD3:
			m_driveTrain.autonDriveStraight(0.5);
			if(m_driveTrain.bothEncoderInchValues()> 180 ) {
				instructions=Direction.R_TURN2;
			}
			break;
		case R_TURN2:
			m_driveTrain.autonDriveTurn(0.5);
//			if(m_driveTrain.getHeading()<1) {
//				instructions = Direction.DRIVE_STOP;
//				m_driveTrain.resetEncoders();
//			}
			break;
		case DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);
			instructions = Direction.END;
			break;
		case END:
			break;
		}
		
		}
		if (Positions == "rightFirst") {
			switch(instructions){
			case INIT: 
				m_driveTrain.resetEncoders();
				//m_driveTrain.resetGyro();
				m_driveTrain.setBrakeMode(true);
				instructions = Direction.BACK;
			break;
			case BACK:
				m_driveTrain.autonDriveStraight(-0.5);
				if(m_driveTrain.bothEncoderInchValues()> 24) {
					instructions=Direction.R_TURN;
				}
				break;
			case R_TURN:
				m_driveTrain.autonDriveTurn(-0.4);
//				if (Math.abs(m_driveTrain.getHeading())> 85) {
//					instructions = Direction.FORWARD;
//					m_driveTrain.resetEncoders();
//				}
			break;
			case FORWARD:
				m_driveTrain.autonDriveStraight(0.5);
				if(m_driveTrain.bothEncoderInchValues()> 60) {
					instructions=Direction.L_TURN;
				}
				break;
			case L_TURN:
				m_driveTrain.autonDriveTurn(0.4);
//				if(Math.abs(m_driveTrain.getHeading())< 1) {
//					instructions = Direction.FORWARD1;	
//					m_driveTrain.resetEncoders();
//				}
				break;
			case FORWARD1:
				m_driveTrain.autonDriveStraight(0.5);
				if(m_driveTrain.bothEncoderInchValues()>24) {
					instructions=Direction.BACK1;
					m_driveTrain.resetEncoders();
				}
				break;
			case BACK1:
				m_driveTrain.autonDriveStraight(-0.5);
				if(m_driveTrain.bothEncoderInchValues() > 36) {
					instructions=Direction.R_TURN1;
				}
				break;
			case R_TURN1:
				m_driveTrain.autonDriveTurn(-0.4);
//				if(m_driveTrain.getHeading()>85) {
//					instructions = Direction.FORWARD3;	
//					m_driveTrain.resetEncoders();
//				}
				break;
			case FORWARD3:
				m_driveTrain.autonDriveStraight(0.5);
				if(m_driveTrain.bothEncoderInchValues()> 180) {
					instructions=Direction.L_TURN2;
				}
				break;
			case L_TURN2:
				m_driveTrain.autonDriveTurn(0.4);
//				if(m_driveTrain.getHeading()<5) {
//					instructions = Direction.DRIVE_STOP;
//					m_driveTrain.resetEncoders();
//				}
				break;
			case DRIVE_STOP:
				m_driveTrain.autonDriveStraight(0);
				instructions = Direction.END;
				break;
			case END:
				break;
			
			}
		}
	}
	public String getState(){
		return instructions.name();
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
//		SmartDashboard.putNumber("1Heading",m_driveTrain.getHeading());
		SmartDashboard.putNumber("Both Encoders",m_driveTrain.bothEncoderInchValues());
		SmartDashboard.putString("AutonState", getState());
	}
	
	  public void changePosition(String newLocation) {
	         Positions = newLocation;
	    }
}

