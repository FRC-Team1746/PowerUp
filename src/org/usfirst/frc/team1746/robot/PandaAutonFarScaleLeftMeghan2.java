package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonGo.States;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class PandaAutonFarScaleLeftMeghan2 {
	AutonConstants aConstants;
	private DriveTrain m_autonDriveTrain;
	private States currentState;
	private Lift m_lift;
	private Constants m_constants;
	private double m_speed;
	private double m_intialHeading;
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
		FIRSTLEGINIT,
		FIRSTLEG,
		SECONDDRIVESTRAIGHTINIT,
		SECONDDRIVESTRAIGHT,
		SECONDLEGINIT,
		SECONDLEG,
		THIRDDRIVESTRAIGHTINIT,
		THIRDDRIVESTRAIGHT,
		LIFT,
		SHOOT,
		BACK,
		DELAY,
		STOP,
		IDLE
	}
	
	public PandaAutonFarScaleLeftMeghan2(DriveTrain drivetrain, Lift lift, Intake intake, Retractor retractor) {
		m_autonDriveTrain = drivetrain;
		m_lift = lift;
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
			m_autonDriveTrain.initHeading();
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
			currentState = States.DRIVESTRAIGHTINIT;
//			currentState = States.SECONDLEGINIT;
			break;
		case DRIVESTRAIGHTINIT:
			if(m_delayCounter ++ >= 2){ //Waiting For Encoders To Reset
				m_speed = .65;
				m_rightTurn = true;
				m_autonDriveTrain.pandaDriveStraight(m_speed);
				currentState = States.DRIVESTRAIGHT;
			}
			break;
		case DRIVESTRAIGHT:
			m_autonDriveTrain.pandaDriveStraight(m_speed);
			System.out.println("Encoder" + m_autonDriveTrain.getAdjustedHeading());
//			if (!m_intake.intakeSensor()) { //Sensor Returns False When It Sees A Cube
			if (m_autonDriveTrain.getEncoderLeftInches() >= 15) {
				m_autonDriveTrain.setRampRate(0);
			}
			if (m_autonDriveTrain.getEncoderLeftInches() >= 140) {
				System.out.println("finished Drive 2 Cube");
				currentState = States.FIRSTLEGINIT;
			}
			break;
		case FIRSTLEGINIT:
			m_speed = .65;
			m_turnRadius = 50;
			m_rightTurn = false;
			m_targetDegrees = -90;
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			currentState = States.FIRSTLEG;
			break;
		case FIRSTLEG:
//			pidcontroller.setSetpoint(-45.0);
//			double value = pidcontroller.get(); 
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			System.out.println("Gyro Value: " + m_autonDriveTrain.getAdjustedHeading());
			if (Math.abs(m_autonDriveTrain.getAdjustedHeading()) >= -50) {
				currentState = States.SECONDDRIVESTRAIGHTINIT;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
			}
			break;
		case SECONDDRIVESTRAIGHTINIT:
			if(m_delayCounter ++ >= 2){ //Waiting For Encoders To Reset
				m_speed = .65;
				m_rightTurn = true;
				m_autonDriveTrain.driveStraightGyro(m_speed, -90);
				currentState = States.SECONDDRIVESTRAIGHT;
			}
			break;
		case SECONDDRIVESTRAIGHT:
			m_autonDriveTrain.driveStraightGyro(m_speed, -90);
			System.out.println("Encoder" + m_autonDriveTrain.getAdjustedHeading());
//			if (!m_intake.intakeSensor()) { //Sensor Returns False When It Sees A Cube
			if (m_autonDriveTrain.getEncoderLeftInches() >= 15) {
				m_autonDriveTrain.setRampRate(0);
			}
			if (m_autonDriveTrain.getEncoderLeftInches() >= 90) {
				System.out.println("finished Drive 2 Cube");
				currentState = States.SECONDLEGINIT;
//				currentState = States.STOP;
			}
			break;
		case SECONDLEGINIT:
			if(m_delayCounter ++ >= 2){
			m_speed = .65;
			m_turnRadius = 0;
			m_rightTurn = true;
			m_targetDegrees = 55;
			m_intialHeading = m_autonDriveTrain.getHeading();
			m_autonDriveTrain.autonDriveTurn(1, m_initialHeading, m_speed);
//			m_autonLift.initPandaLift(1);
			currentState = States.SECONDLEG;
			
			}
			break;
		case SECONDLEG:
			m_autonDriveTrain.autonDriveTurn(1, m_initialHeading, m_speed);
			System.out.println("Adjusted Heading" + m_autonDriveTrain.getAdjustedHeading());
			if (m_autonDriveTrain.getAdjustedHeading() >= m_targetDegrees) {
				System.out.println("finished second leg, heading = " + m_autonDriveTrain.getAdjustedHeading());
//				m_pandaIntake.initPandaIntake(1);
				m_delayCounter = 0;
				currentState = States.DELAY;
			}
			break;
		case THIRDDRIVESTRAIGHTINIT:
			if(m_delayCounter ++ >= 2){ 
				m_speed = .4;
				m_rightTurn = true;
				m_delayCounter = 0;
				m_autonDriveTrain.driveStraightGyro(m_speed,  0);
				currentState = States.THIRDDRIVESTRAIGHT;
			}
			break;
		case THIRDDRIVESTRAIGHT:
			m_autonDriveTrain.driveStraightGyro(m_speed, 0);
			System.out.println("Encoder" + m_autonDriveTrain.getAdjustedHeading());
//			if (!m_intake.intakeSensor()) { //Sensor Returns False When It Sees A Cube
			if (m_delayCounter >= 13) {
				m_autonDriveTrain.setRampRate(0);
			}
			if (m_delayCounter ++ >= 25) {
				System.out.println("finished Drive 2 Cube");
				m_delayCounter = 0;
				currentState = States.SHOOT;
			}
			break;
//		case LIFT:
//			m_speed = 0;
//			m_autonDriveTrain.autonDriveStraight(0);
//			m_lift.updatePosition(3);
//			m_lift.update();
//			if(m_lift.getLiftPosition() >= Constants.liftEncoderPosition3 - 1000) {
//				currentState = States.SHOOT;
//			}
//			break;
		case SHOOT:
			m_speed = 0;
			m_autonDriveTrain.autonDriveStraight(m_speed);
			m_lift.updatePosition(3);
			m_lift.update();
			if(m_lift.getLiftPosition() >= Constants.liftEncoderPosition3 - 1000) {
					m_retractor.retractorDownDumb();
					if (m_delayCounter ++ >= 50) {
						m_intake.intakeOut();
					if (m_delayCounter ++ >= 100) {
						currentState = States.BACK;
						m_delayCounter = 0;
					}
				}
			}
			break;
		case BACK:
			if(m_delayCounter >= 2){ //Waiting For Encoders To Reset
				m_speed = -.4;
				m_rightTurn = true;
				m_autonDriveTrain.pandaDriveStraight(m_speed);
			}
			if(m_delayCounter >= 20) {
				m_lift.updatePosition(0);
			}
			if(m_delayCounter ++ >= 50) {
				currentState = States.STOP;
			}
			break;
			//insert
		case DELAY:
			m_speed = 0;
			m_autonDriveTrain.autonDriveStraight(m_speed);
			if(m_delayCounter ++ >= 50){
				currentState = States.THIRDDRIVESTRAIGHTINIT;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
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