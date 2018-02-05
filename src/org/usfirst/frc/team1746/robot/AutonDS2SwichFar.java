  package org.usfirst.frc.team1746.robot;

/*<<<<<<< HEAD
import org.usfirst.frc.team1746.robot.AutonCrossTheLine.States;
=======
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class AutonDS2SwichFar {
	private DriveTrain m_driveTrain;
	private States currentState;
	private int m_direction;
<<<<<<< HEAD
	private int m_startPosition;
=======
	private double m_startPosition;
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
	private double m_driveSpeed;
	private double m_turnSpeed;
	
	public enum States{
		INIT,
		GRAB_CUBE,
		DECIDE_POSITION,
		
		
<<<<<<< HEAD
		POS1_3_DRIVEFORWARD,
		POS1_3_TURN,
		POS1_3_CROSS_FIELD,
		POS1_3_TURN_TO_SWICH,
		POS1_3_LIFT,
		POS1_3_FORWARD_TO_SWICH,
		POS1_3_SCORE,
		POS1_3_DRIVE_STOP,
=======
		POS1_4_DRIVEFORWARD,
		POS1_4_TURN,
		POS1_4_CROSS_FIELD,
		POS1_4_TURN_TO_SWICH,
		POS1_4_LIFT,
		POS1_4_FORWARD_TO_SWICH,
		POS1_4_SCORE,
		POS1_4_DRIVE_STOP,
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
		
		POS2_3_DRIVEFORWARD,
		POS2_3_TURN,
		POS2_3_CROSS_TO_SWICH,
		POS2_3_TURN_FOR_SWICH,
		POS2_3_DRIVE_FOR_SWICH,
		POS2_3_TURN_TO_SWICH,
		POS2_3_DRIVE_TO_SWICH,
		POS2_3_SCORE,
		POS2_3_DRIVE_STOP, //temporary
		
		END,
		
	}
	
<<<<<<< HEAD
	public AutonDS2SwichFar(DriveTrain driveTrain, int startPosition) {
=======
	public AutonDS2SwichFar(DriveTrain driveTrain, double startPosition) {
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(.5);
		m_startPosition = startPosition;
		m_driveSpeed = .5;
		m_turnSpeed = .3;
	
		if (startPosition <= 2) {
			m_direction = 1;
		}
		else if (startPosition <= 4){
			m_direction = -1;
		}
		
	
	}
	
	public void auton() {
		switch(currentState){
		
		
		case INIT: 
			m_driveTrain.resetEncoders();
			m_driveTrain.setBrakeMode(true);
			currentState = States.GRAB_CUBE;			
		break;
		
		case GRAB_CUBE:
			//Stuff
			currentState = States.DECIDE_POSITION;
		break;
			
		
		case DECIDE_POSITION:
<<<<<<< HEAD
			if (m_startPosition == 1 || m_startPosition == 3) {
				currentState = States.POS1_3_DRIVEFORWARD;
			}
			else if (m_startPosition == 2){
=======
			if (m_startPosition == 1 || m_startPosition == 4) {
				currentState = States.POS1_4_DRIVEFORWARD;
			}
			else if (m_startPosition == 2 || m_startPosition == 3){
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
				currentState = States.POS2_3_DRIVEFORWARD;
			}
		break;		
		
<<<<<<< HEAD
///////////   Position 1 and 3   //////////
		
		case POS1_3_DRIVEFORWARD:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 242.73) {
				currentState = States.POS1_3_TURN;
			}
		break;
		
		case POS1_3_TURN:
			m_driveTrain.autonDriveTurn(m_turnSpeed * m_direction);
			if (Math.abs(m_driveTrain.getHeading()) > 88) {  // 5 for test purpouse (make 90 normally)
				currentState = States.POS1_3_CROSS_FIELD;
=======
///////////   Position 1 and 4   //////////
		
		case POS1_4_DRIVEFORWARD:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 242.73) {
				currentState = States.POS1_4_TURN;
			}
		break;
		
		case POS1_4_TURN:
			m_driveTrain.autonDriveTurn(m_turnSpeed * m_direction);
			if (Math.abs(m_driveTrain.getHeading()) > 88) {  // 5 for test purpouse (make 90 normally)
				currentState = States.POS1_4_CROSS_FIELD;
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
				m_driveTrain.resetEncoders();
			}		
		break;
		
<<<<<<< HEAD
		case POS1_3_CROSS_FIELD:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 161.56) {
				currentState = States.POS1_3_TURN_TO_SWICH;
			}
		break;
		
		case POS1_3_TURN_TO_SWICH:
=======
		case POS1_4_CROSS_FIELD:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 161.56) {
				currentState = States.POS1_4_TURN_TO_SWICH;
			}
		break;
		
		case POS1_4_TURN_TO_SWICH:
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
			m_driveTrain.autonDriveTurn(m_turnSpeed * m_direction);
			if (Math.abs(m_driveTrain.getHeading()) > 175) { 
			//	currentState = States.LIFT;	
				m_driveTrain.resetEncoders();
<<<<<<< HEAD
				currentState = States.POS1_3_FORWARD_TO_SWICH;
=======
				currentState = States.POS1_4_FORWARD_TO_SWICH;
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
				m_driveTrain.resetEncoders();
			}
		break;
		
<<<<<<< HEAD
//		case POS1_4_LIFT:
			//Stuff
		
//			currentState = States.SCORE;
		
		case POS1_3_FORWARD_TO_SWICH:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeft() > 68) {
				currentState = States.POS1_3_DRIVE_STOP;
			}	
		break;
		
		case POS1_3_DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);
			currentState = States.POS1_3_SCORE;
			break;
		
		case POS1_3_SCORE:
			// Stuff
			currentState = States.END; // END at bottom of Position 2
=======
//		case Lift:
//		
//			currentState = States.SCORE;
		
		case POS1_4_FORWARD_TO_SWICH:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeft() > 68) {
				currentState = States.POS1_4_DRIVE_STOP;
			}	
		break;
		
		case POS1_4_DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);
			currentState = States.POS1_4_SCORE;
			break;
		
		case POS1_4_SCORE:
			// Stuff
			currentState = States.END;
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
		
			
		
						
///////////    Position 2     /////////////			
			
			
			
			
		case POS2_3_DRIVEFORWARD:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 70) {
				currentState = States.POS2_3_TURN;	
			}
		break;
		
		case POS2_3_TURN:
<<<<<<< HEAD
			m_driveTrain.autonDriveTurn(-m_turnSpeed);
			if (Math.abs(m_driveTrain.getHeading()) > 88) {  //will change degree
				currentState = States.POS2_3_CROSS_TO_SWICH;
				m_driveTrain.resetEncoders();
			}
		break;
		
		case POS2_3_CROSS_TO_SWICH:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 120) {
=======
			m_driveTrain.autonDriveTurn(m_turnSpeed * m_direction);
			if (Math.abs(m_driveTrain.getHeading()) > 5) {  //will change degree
				currentState = States.POS2_3_CROSS_TO_SWICH;
				m_driveTrain.resetEncoders();
			}
		break;	
		
		case POS2_3_CROSS_TO_SWICH:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 119) {
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
				currentState = States.POS2_3_TURN_FOR_SWICH;
			}
		break;
		
		case POS2_3_TURN_FOR_SWICH:
<<<<<<< HEAD
			m_driveTrain.autonDriveTurn(m_turnSpeed);
			if (Math.abs(m_driveTrain.getHeading()) < 2) {
=======
			m_driveTrain.autonDriveTurn(-m_turnSpeed * m_direction);
			if (Math.abs(m_driveTrain.getHeading()) < 0.2) {
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
				currentState = States.POS2_3_DRIVE_FOR_SWICH;
				m_driveTrain.resetEncoders();
			}
		break;
		
		case POS2_3_DRIVE_FOR_SWICH:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 98) {
				currentState = States.POS2_3_TURN_TO_SWICH;
			}
		break;
		
		case POS2_3_TURN_TO_SWICH:
<<<<<<< HEAD
			m_driveTrain.autonDriveTurn(m_turnSpeed);
			if (Math.abs(m_driveTrain.getHeading()) > 88) {
=======
			m_driveTrain.autonDriveTurn(-m_turnSpeed * m_direction);
			if (Math.abs(m_driveTrain.getHeading()) > 5) {
>>>>>>> 684607d107011949f92c254ffd5afdd910119e71
				currentState = States.POS2_3_DRIVE_TO_SWICH;
				m_driveTrain.resetEncoders();
			}
		break;
		
		case POS2_3_DRIVE_TO_SWICH:
			m_driveTrain.autonDriveStraight(m_driveSpeed);
			if (m_driveTrain.getEncoderLeftInches() > 10) {
				currentState = States.POS2_3_SCORE;
			}
		break;
		
		case POS2_3_SCORE:
			//stuff
			currentState = States.POS2_3_DRIVE_STOP;
		break;
		
		case POS2_3_DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);
			currentState = States.END;
		break;
			
	/// END ///		
		
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
		SmartDashboard.putNumber("Gyro", m_driveTrain.getHeading());
	}

}
*/