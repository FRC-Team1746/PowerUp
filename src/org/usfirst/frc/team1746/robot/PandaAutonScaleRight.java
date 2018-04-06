package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonGo.States;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class PandaAutonScaleRight {
	AutonConstants aConstants;
	private DriveTrain m_autonDriveTrain;
	private States currentState;
	private Lift m_autonLift;
	private Constants m_constants;
	private double m_speed;
	private double m_turnRadius;
	private boolean m_rightTurn;
	private Intake m_intake;
	private int m_delayCounter;
//	private double inf = Double.POSITIVE_INFINITY;
	private double m_initialHeading;
	private double m_targetDegrees;
	private Retractor m_retractor;
	
	public enum States {
		INIT,
		DRIVESTRAIGHTINIT,
		DRIVESTRAIGHT,
		LEFTLEGINIT,
		LEFTLEG,
		SHOOT,
		STOP,
		IDLE
	}
	
	public PandaAutonScaleRight(DriveTrain drivetrain, Lift lift, Intake intake, Retractor retractor) {
		m_autonDriveTrain = drivetrain;
		m_autonLift = lift;
		m_constants = new Constants();
		m_intake = intake;
		m_speed = 0;
		m_turnRadius = 150;
		m_rightTurn = false;
		currentState = States.IDLE;
		m_retractor = retractor;
		
	}
	
	public void auton() {
		System.out.println("currentState = " + currentState);
		switch(currentState) {
		case INIT:
			m_autonDriveTrain.resetEncoders();
			m_initialHeading = m_autonDriveTrain.getHeading();
//			pidcontroller = new PIDController(0, 0, 0, 0, m_Gyro, m_LeftMaster);
//			PIDController pidcontroller1 = new PIDController(0, 0, 0, 0, m_Gyro, m_RightMaster); //Enter Gyro value and Both Motor Sides? // Try Speed Controller Groups?
			m_speed = .25;
			m_turnRadius = 50;
			m_rightTurn = true;
			m_targetDegrees = 90;
			m_autonDriveTrain.setRightPID(0.75, 0.003, 75, 0.209);
			m_autonDriveTrain.setLeftPID(0.75, 0.003, 75, 0.209);
			m_autonDriveTrain.setRampRate(.2);
//			m_autonDriveTrain.setRightPID(1.2, 0.01, 24, 0);
//			m_autonDriveTrain.setLeftPID(0, 0, 0, 0);
			currentState = States.DRIVESTRAIGHTINIT;
			break;
		case DRIVESTRAIGHTINIT:
			if(m_delayCounter ++ >= 2){ //Waiting For Encoders To Reset
				m_speed = .7;
				m_rightTurn = true;
				m_autonDriveTrain.pandaDriveStraight(m_speed);
				currentState = States.DRIVESTRAIGHT;
			}
			break;
			
		case DRIVESTRAIGHT:
			m_autonDriveTrain.pandaDriveStraight(m_speed);
			System.out.println("Encoder" + m_autonDriveTrain.getAdjustedHeading());
//			if (!m_intake.intakeSensor()) { //Sensor Returns False When It Sees A Cube
			if (m_autonDriveTrain.getEncoderLeftInches() >= 30) {
				m_autonDriveTrain.setRampRate(0);
			}
			if (m_autonDriveTrain.getEncoderLeftInches() >= 116) {
				System.out.println("finished Drive 2 Cube");
				currentState = States.LEFTLEGINIT;
			}
			break;
		case LEFTLEGINIT:
			if(m_delayCounter ++ >= 2){
			m_speed = .7;
			m_turnRadius = 100;
			m_targetDegrees = -40;
			m_rightTurn = false;
//			m_autonLift.initPandaLift(1);
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
			currentState = States.LEFTLEG;
			}
			break;
		case LEFTLEG:
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
			System.out.println("Adjusted Heading" + m_autonDriveTrain.getAdjustedHeading());
			if (m_autonDriveTrain.getAdjustedHeading() <= m_targetDegrees) {
				System.out.println("finished left leg, heading = " + m_autonDriveTrain.getAdjustedHeading());
//				m_pandaIntake.initPandaIntake(1);
				m_delayCounter = 0;
				currentState = States.SHOOT;
			}
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) <=  m_targetDegrees + 5) {
				m_autonLift.updatePosition(3);
				//init Shooting
			}
			break;	
		case SHOOT:
			m_retractor.updateNew();
			m_retractor.retractorDown();
			if(m_retractor.getPot() >= Constants.retFourtyFiveDeg) {
				m_intake.intakeOut();
				if (m_delayCounter ++ >= 20) {
					currentState = States.STOP;
					m_delayCounter = 0;
				}
			}
			
			break;
		case STOP:
//			System.out.println("STOP has been reached");
			m_speed = 0;
			m_autonDriveTrain.autonDriveStraight(m_speed);
			m_intake.intakeStop();
			break;
		
		case IDLE:
			break;
		}
//		System.out.println("Speed of Robot: " + m_speed);
//		m_pandaIntake.updatePandaIntake();
//		m_autonLift.updatePandaLift();
//		m_autonDriveTrain.pandaDriveStraight(m_speed);
//		m_autonDriveTrain.radialDrive(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees); // boolean defines true/false for right
	}
	public void init() {
		currentState = States.INIT;
	}

	
}
