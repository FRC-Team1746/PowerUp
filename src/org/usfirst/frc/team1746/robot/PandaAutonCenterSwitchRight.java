package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonGo.States;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class PandaAutonCenterSwitchRight {
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
		DRIVESTRAIGHT,
		FIRSTLEG,
		SECONDLEGINIT,
		SECONDLEG,
		SHOOT,
		DELAY,
		SECONDBACKINIT,
		SECONDBACK,
		FIRSTBACKINIT,
		FIRSTBACK,
		DRIVE2CUBEINIT,
		DRIVE2CUBE,
		STOP,
		IDLE
	}
	
	public PandaAutonCenterSwitchRight(DriveTrain drivetrain, Lift lift, Intake intake, Retractor retractor) {
		m_autonDriveTrain = drivetrain;
		m_autonLift = lift;
		m_constants = new Constants();
		m_retractor = retractor;
		m_intake = intake;
		m_speed = 0;
		m_turnRadius = 150;
		m_rightTurn = true;
		currentState = States.IDLE;
	}
	
	public void auton() {
		System.out.println("currentState = " + currentState);
		switch(currentState) {
		case INIT:
			m_autonDriveTrain.resetEncoders();
			m_initialHeading = m_autonDriveTrain.getHeading();
//			pidcontroller = new PIDController(0, 0, 0, 0, m_Gyro, m_LeftMaster);
//			PIDController pidcontroller1 = new PIDController(0, 0, 0, 0, m_Gyro, m_RightMaster); //Enter Gyro value and Both Motor Sides? // Try Speed Controller Groups?
			m_speed = .5;
			m_turnRadius = 25;
			m_rightTurn = true;
			m_targetDegrees = 90;
			m_autonDriveTrain.setRightPID(0.75, 0.003, 75, 0.209);
			m_autonDriveTrain.setLeftPID(0.75, 0.003, 75, 0.209);
			m_autonDriveTrain.setRampRate(0);
//			m_autonDriveTrain.setRightPID(1.2, 0.01, 24, 0);
//			m_autonDriveTrain.setLeftPID(0, 0, 0, 0);
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			currentState = States.FIRSTLEG;
			break;
		case FIRSTLEG:
//			pidcontroller.setSetpoint(-45.0);
//			double value = pidcontroller.get(); 
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			System.out.println("Gyro Value: " + m_autonDriveTrain.getAdjustedHeading());
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) >= 25) {
				currentState = States.SECONDLEGINIT;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
			}
			break;
		case SECONDLEGINIT:
			if(m_delayCounter ++ >= 2){
			m_speed = .5;
			m_turnRadius = 25;
			m_rightTurn = false;
			m_targetDegrees = 1;
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
//			m_autonLift.initPandaLift(1);
			currentState = States.SECONDLEG;
			
			}
			break;
		case SECONDLEG:
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
			System.out.println("Adjusted Heading" + m_autonDriveTrain.getAdjustedHeading());
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) <= m_targetDegrees) {
				System.out.println("finished second leg, heading = " + m_autonDriveTrain.getAdjustedHeading());
//				m_pandaIntake.initPandaIntake(1);
				m_delayCounter = 0;
				currentState = States.SHOOT;
			}
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) <= 15) {
				//init Shooting
			}
			break;
		case SHOOT:
			m_retractor.retractorDown();
			if(m_retractor.getPot() >= Constants.retFourtyFiveDeg) {
				m_intake.intakeOut();
				if (m_delayCounter ++ >= 20) {
					currentState = States.STOP;
					m_delayCounter = 0;
				}
			}
			break;
			//insert
		case DELAY:
			m_speed = 0;
			m_autonDriveTrain.autonDriveStraight(m_speed);
			if(m_delayCounter ++ >= 100){
				currentState = States.STOP;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
			}
			break;
		case SECONDBACKINIT:
			if(m_delayCounter ++ >= 2){
			m_speed = -.5;
			m_turnRadius = 70;
			m_rightTurn = false;
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			currentState = States.SECONDBACK;
			}
			break;
		case SECONDBACK:
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) >= 43) {
				System.out.println("finished second back, heading = " + m_autonDriveTrain.getAdjustedHeading());
				currentState = States.FIRSTBACKINIT;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
			}
			break;
		case FIRSTBACKINIT:
			if(m_delayCounter ++ >= 2){ //Waiting For Encoders To Reset
			m_speed = -.5;
			m_turnRadius = 40;
			m_rightTurn = true;
			m_targetDegrees = 10;
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
//			m_autonLift.initPandaLift(0);
			currentState = States.FIRSTBACK;
			}
			break;
		case FIRSTBACK:
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) <= m_targetDegrees) {
				System.out.println("finished first back, heading = " + m_autonDriveTrain.getAdjustedHeading());
//				m_pandaIntake.initPandaIntake(1);
				currentState = States.DRIVE2CUBEINIT;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
			}
			break;
		case DRIVE2CUBEINIT:
			if(m_delayCounter ++ >= 2){ //Waiting For Encoders To Reset
				m_speed = .7;
				m_turnRadius = 4294000000.0;
//				m_turnRadius = inf;
				m_rightTurn = true;
				m_autonDriveTrain.pandaDriveStraight(m_speed);
				currentState = States.DRIVE2CUBE;
			}
			break;
			
		case DRIVE2CUBE:
			m_autonDriveTrain.pandaDriveStraight(m_speed);
			System.out.println("Encoder" + m_autonDriveTrain.getAdjustedHeading());
			if (!m_intake.intakeSensor()) { //Sensor Returns False When It Sees A Cube
//			if (m_autonDriveTrain.getEncoderLeftInches() >= 50) {
				System.out.println("finished Drive 2 Cube");
				currentState = States.STOP;
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
		case DRIVESTRAIGHT:
			if (m_autonDriveTrain.getEncoderLeftInches() >= 200) {
				System.out.println("finished Drive Straight");
				currentState = States.STOP;
			}
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
