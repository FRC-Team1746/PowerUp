package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class PandaAutonCenterSwitchLeft {
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
	
	public PandaAutonCenterSwitchLeft(DriveTrain drivetrain, Lift lift, Intake intake, Retractor retractor) {
		m_autonDriveTrain = drivetrain;
		m_autonLift = lift;
		m_constants = new Constants();
		m_intake = intake;
		m_retractor = retractor;
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
			m_speed = .5;
			m_turnRadius = 25;
			m_rightTurn = false;
			m_targetDegrees = -90;
			m_autonDriveTrain.setRightPID(0.75, 0.003, 75, 0.209);
			m_autonDriveTrain.setLeftPID(0.75, 0.003, 75, 0.209);
			m_autonDriveTrain.setRampRate(0);
//			m_autonDriveTrain.setRightPID(1.2, 0.01, 24, 0);
//			m_autonDriveTrain.setLeftPID(0, 0, 0, 0);
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			currentState = States.FIRSTLEG;
			break;
		case FIRSTLEG:
			m_autonDriveTrain.radialDriveAtSpeed(m_speed, m_turnRadius, m_rightTurn);
			System.out.println("Gyro Value: " + m_autonDriveTrain.getAdjustedHeading());
			if (m_autonDriveTrain.getAdjustedHeading() <= -25) {
				currentState = States.SECONDLEGINIT;
				m_delayCounter = 0;
				m_autonDriveTrain.resetEncoders();
			}
			break;
		case SECONDLEGINIT:
			if(m_delayCounter ++ >= 2){
			m_speed = .5;
			m_turnRadius = 30;
			m_rightTurn = true;
			m_targetDegrees = -1;
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
			currentState = States.SECONDLEG;
			}
			break;
		case SECONDLEG:
			m_autonDriveTrain.radialDriveToStop(m_speed, m_turnRadius, m_rightTurn, m_targetDegrees);
			System.out.println("Adjusted Heading" + m_autonDriveTrain.getAdjustedHeading());
			if (m_autonDriveTrain.getAdjustedHeading() >= m_targetDegrees - 3) {
				System.out.println("finished second leg, heading = " + m_autonDriveTrain.getAdjustedHeading());
				m_delayCounter = 0;
				currentState = States.SHOOT;
			}
			break;
		case SHOOT:
			m_speed = 0;
			m_autonDriveTrain.autonDriveStraight(m_speed);
			m_retractor.retractorDownDumb();
			if (m_delayCounter ++ >= 50) {
				m_intake.intakeOut();
				if (m_delayCounter ++ >= 100) {
				currentState = States.STOP;
				m_delayCounter = 0;
				}
			}
			break;
		case DELAY:
			m_speed = 0;
			m_autonDriveTrain.autonDriveStraight(m_speed);
			if(m_delayCounter ++ >= 100){
				currentState = States.STOP;
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
	}
	public void init() {
		currentState = States.INIT;
	}

	
}
