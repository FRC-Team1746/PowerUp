package org.usfirst.frc.team1746.robot;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

import javax.swing.tree.DefaultMutableTreeNode;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class DriveTrain {
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Class setup
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Controls m_controls;
	private ElectricalConstants eConstants;
	private AutonConstants aConstants;
	private VisionCube m_vision;
	
	
	public WPI_TalonSRX m_LeftMaster;
	private WPI_VictorSPX m_LeftFollowerA;
	private WPI_VictorSPX m_LeftFollowerB;
	public WPI_TalonSRX m_RightMaster;
	private WPI_VictorSPX m_RightFollowerA;
	private WPI_VictorSPX m_RightFollowerB;
	private double m_initialHeading;
	
	public DifferentialDrive myRobot;

//	private Encoder m_encoderLeft;
//	private Encoder m_encoderRight;
	private ADXRS450_Gyro m_Gyro;
	private double m_drivePosition;
	
	private int m_differentialInit;
	private int m_autoInit;
	private double leftMotorSpeed;
	private double MOTOR_INCREMENT_RATE;
	private boolean m_turned;
	private double WHEELBASE_IN = 26;
	
	
	private double vision_P = .004; //005
	private double vision_speedLeft;
	private double vision_speedRight;
	private double vision_error;
	
	double integral;
	double encoderDifference;
	double prevEncoderDiff;
	double derivative;
	
	public DriveTrain(Controls controls, VisionCube vision) {
		m_controls = controls;
		m_vision = vision;
		eConstants = new ElectricalConstants();
		m_LeftMaster = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_MASTER);
		m_LeftFollowerA = new WPI_VictorSPX(eConstants.MOTOR_DRIVE_LEFT_FOLLOWER_A);
		m_LeftFollowerB = new WPI_VictorSPX(eConstants.MOTOR_DRIVE_LEFT_FOLLOWER_B);
		m_RightMaster = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_MASTER);
		m_RightFollowerA = new WPI_VictorSPX(eConstants.MOTOR_DRIVE_RIGHT_FOLLOWER_A);
		m_RightFollowerB = new WPI_VictorSPX(eConstants.MOTOR_DRIVE_RIGHT_FOLLOWER_B);
		
		m_LeftFollowerA.follow(m_LeftMaster);
		m_LeftFollowerB.follow(m_LeftMaster);
		m_RightFollowerA.follow(m_RightMaster);
		m_RightFollowerB.follow(m_RightMaster);	
	
//		m_encoderLeft = new Encoder(eConstants.ENCODER_DRIVE_LEFT_A, eConstants.ENCODER_DRIVE_LEFT_B, false, Encoder.EncodingType.k1X);
//		m_encoderRight = new Encoder(eConstants.ENCODER_DRIVE_RIGHT_A, eConstants.ENCODER_DRIVE_RIGHT_B, false, Encoder.EncodingType.k1X);
		m_Gyro = new ADXRS450_Gyro();
		m_Gyro.reset();
		m_drivePosition = 0;
		m_autoInit = 0;
		m_differentialInit = 0;
		leftMotorSpeed = .25;
		MOTOR_INCREMENT_RATE = .05;
		m_turned = false;
		
		m_RightMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_RightMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		m_LeftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_LeftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		

		integral = 0;
		encoderDifference = 0;
		prevEncoderDiff = 0;
		derivative = 0;
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Drivetrain init
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
	public void initTele(){
		
		if(m_differentialInit == 0) {
			if(m_autoInit >= 1) {
				m_RightMaster.free();
				m_LeftMaster.free();
				m_autoInit = 0;
				

				m_RightMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
				m_RightMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);
				m_LeftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
				m_LeftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

			}
			myRobot = new DifferentialDrive(m_LeftMaster, m_RightMaster);
			myRobot.setSafetyEnabled(false);
			m_differentialInit ++;
		}
	}
	
public void initAuto(){
		
		if(m_autoInit == 0) {
			if(m_differentialInit >= 1) {
				myRobot.free();
				m_differentialInit = 0;
			}
			
			///////  Right Master ///////
		
			/* first choose the sensor */
			m_RightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx, Constants.kTimeoutMs);
			m_RightMaster.setSensorPhase(false);
			m_RightMaster.setInverted(false);
			/* Set relevant frame periods to be at least as fast as periodic rate*/
			m_RightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,
			Constants.kTimeoutMs);
//			m_RightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,
//			Constants.kTimeoutMs);
			/* set the peak and nominal outputs */
			m_RightMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
			m_RightMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
//			m_RightMaster.configPeakOutputForward(AutonConstants.maxVelocity, Constants.kTimeoutMs);
//			m_RightMaster.configPeakOutputReverse(-AutonConstants.maxVelocity, Constants.kTimeoutMs);
			m_RightMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
			m_RightMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);
			/* set closed loop gains in slot0 - see documentation */
			m_RightMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
			m_RightMaster.config_kF(Constants.kSlotIdx, 0.205, Constants.kTimeoutMs);
			m_RightMaster.config_kP(Constants.kSlotIdx, .25, Constants.kTimeoutMs);
			m_RightMaster.config_kI(Constants.kSlotIdx, .001, Constants.kTimeoutMs);
			m_RightMaster.config_kD(Constants.kSlotIdx, 75, Constants.kTimeoutMs);
			/* set acceleration and vcruise velocity - see documentation */
//			m_RightMaster.configMotionCruiseVelocity((int)(AutonConstants.maxVelocity), Constants.kTimeoutMs);
//			m_RightMaster.configMotionAcceleration((int)(AutonConstants.maxVelocity / AutonConstants.DefaultRampRate), Constants.kTimeoutMs);
			/* zero the sensor */
			m_RightMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
			m_RightMaster.configOpenloopRamp(0, 0);
			m_RightMaster.configClosedloopRamp(0, 0);
			
		
			///////  Left Master ///////

			/* first choose the sensor */
			m_LeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx, Constants.kTimeoutMs);
			m_LeftMaster.setSensorPhase(false);
			m_LeftMaster.setInverted(false);
			/* Set relevant frame periods to be at least as fast as periodic rate*/
			m_LeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,
			Constants.kTimeoutMs);
//			m_LeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,
//			Constants.kTimeoutMs);
			/* set the peak and nominal outputs */
			m_LeftMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
			m_LeftMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
			m_LeftMaster.configPeakOutputForward(AutonConstants.DefaultDrivingSpeed, Constants.kTimeoutMs);
			m_LeftMaster.configPeakOutputReverse(-AutonConstants.DefaultDrivingSpeed, Constants.kTimeoutMs);
			System.out.println("initAuto been hit<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			/* set closed loop gains in slot0 - see documentation */
			m_LeftMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
			m_LeftMaster.config_kF(Constants.kSlotIdx, 0.205, Constants.kTimeoutMs);
			m_LeftMaster.config_kP(Constants.kSlotIdx, 0.5, Constants.kTimeoutMs);
			m_LeftMaster.config_kI(Constants.kSlotIdx, 0, Constants.kTimeoutMs);
			m_LeftMaster.config_kD(Constants.kSlotIdx, 0, Constants.kTimeoutMs);
//			m_LeftMaster.config_kF(Constants.kSlotIdx, 0.205, Constants.kTimeoutMs);
//			m_LeftMaster.config_kP(Constants.kSlotIdx, 1, Constants.kTimeoutMs);
//			m_LeftMaster.config_kI(Constants.kSlotIdx, 0, Constants.kTimeoutMs);
//			m_LeftMaster.config_kD(Constants.kSlotIdx, 20, Constants.kTimeoutMs);
//			/* set acceleration and vcruise velocity - see documentation */
//			m_LeftMaster.configMotionCruiseVelocity(5250, Constants.kTimeoutMs);
//			m_LeftMaster.configMotionAcceleration(21000, Constants.kTimeoutMs);
//			m_RightMaster.configMotionCruiseVelocity(5250, Constants.kTimeoutMs);
//			m_RightMaster.configMotionAcceleration(21000, Constants.kTimeoutMs);
//			m_LeftMaster.configMotionCruiseVelocity(2000, Constants.kTimeoutMs);
//			m_LeftMaster.configMotionAcceleration((int)(AutonConstants.defaultAcceleration), Constants.kTimeoutMs);
//			m_RightMaster.configMotionCruiseVelocity(2000, Constants.kTimeoutMs);
//			m_RightMaster.configMotionAcceleration((int)(AutonConstants.defaultAcceleration), Constants.kTimeoutMs);
			/* zero the sensor */
			m_LeftMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
			m_LeftMaster.configOpenloopRamp(0, 0);
			m_LeftMaster.configClosedloopRamp(0, 0);
			m_autoInit ++;
		}
	}
	public void setRightPID(double P, double I, double D, double F) {
		m_RightMaster.config_kP(Constants.kSlotIdx, P, Constants.kTimeoutMs);
		m_RightMaster.config_kI(Constants.kSlotIdx, I, Constants.kTimeoutMs);
		m_RightMaster.config_kD(Constants.kSlotIdx, D, Constants.kTimeoutMs);
		m_RightMaster.config_kF(Constants.kSlotIdx, F, Constants.kTimeoutMs);
		m_RightMaster.configClosedloopRamp(0, 0);
	}
	public void setLeftPID(double P, double I, double D, double F) {
		m_LeftMaster.config_kP(Constants.kSlotIdx, P, Constants.kTimeoutMs);
		m_LeftMaster.config_kI(Constants.kSlotIdx, I, Constants.kTimeoutMs);
		m_LeftMaster.config_kD(Constants.kSlotIdx, D, Constants.kTimeoutMs);
		m_LeftMaster.config_kF(Constants.kSlotIdx, F, Constants.kTimeoutMs);
		m_LeftMaster.configClosedloopRamp(0, 0);
//		m_LeftMaster.configOpenloopRamp(0.25, 0);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Drivetrain Functions
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void teleopArcadeDrive(){
		myRobot.arcadeDrive(-m_controls.driver_Y_Axis(), m_controls.driver_X_Axis()/4*3);
//		setRampRate(0.0);
//		setCoast(true);		
	}
	public void autonDriveStraight(double distance, double speed){
		m_RightMaster.configMotionCruiseVelocity((int)(AutonConstants.maxVelocity * speed), Constants.kTimeoutMs);
//		m_RightMaster.configMotionAcceleration(21000, Constants.kTimeoutMs);
		m_LeftMaster.configMotionCruiseVelocity((int)(AutonConstants.maxVelocity * speed), Constants.kTimeoutMs);
//		m_LeftMaster.configMotionAcceleration(21000, Constants.kTimeoutMs);
		m_RightMaster.set(ControlMode.MotionMagic, (-distance * AutonConstants.ticksPerInch));
		m_LeftMaster.set(ControlMode.MotionMagic, (distance * AutonConstants.ticksPerInch));
//		try { 
//			TimeUnit.MILLISECONDS.sleep(10);
//		} catch (Exception e) {
//			System.out.println("Exception: " + e);
//		}
//		if(Math.abs(getEncoderLeft()) < Math.abs(distance*AutonConstants.ticksPerInch)-333) {
//			m_LeftMaster.set(ControlMode.Position, (distance * AutonConstants.ticksPerInch));
//			m_RightMaster.set(ControlMode.Position, (-distance * AutonConstants.ticksPerInch));
////			drivePID(AutonConstants.DefaultDrivingSpeed, 0);
//		}else {
//			m_RightMaster.set(ControlMode.PercentOutput, 0);
//			m_LeftMaster.set(ControlMode.PercentOutput, 0);
//		}
}

	public void autonDriveStraight(double speed){
//		myRobot.tankDrive(-speed, -speed);
		m_LeftMaster.set(speed);
		m_RightMaster.set(-speed);
		System.out.println("Speed: " + speed + " Talon Right Output: " + m_RightMaster.getMotorOutputPercent() + " Talon Left Output: " + m_LeftMaster.getMotorOutputPercent());
//		System.out.println("Running Tank Drive");
	}
	public void autonDriveProto(double speed, double speed1) {
		myRobot.tankDrive(speed, speed1);
	}
	public void radialDriveToStop(double speed, double radius, boolean rightTurn, double turn) {
		
		double P = 0.00002;
		System.out.println("Initial Heading" + m_initialHeading);
		System.out.println("Speed  = " + speed + " R OUt " + m_RightMaster.getMotorOutputPercent() + " L Out " + m_LeftMaster.getMotorOutputPercent());
		if(rightTurn) {
			double gyroDifference = turn - getAdjustedHeading();
			double angleRatio = gyroDifference/Math.abs(turn)*0.82;
			if (angleRatio >= 1) {
				angleRatio = 1;
			}
			double encoderDifference = (-getEncoderRight()*(((radius+WHEELBASE_IN) / radius))) - getEncoderLeft();
			m_RightMaster.set(ControlMode.PercentOutput, -((speed)-(P*(encoderDifference)))*(angleRatio));
			m_LeftMaster.set(ControlMode.PercentOutput, ((speed))*(angleRatio));
//			m_LeftMaster.set(ControlMode.Position, -getEncoderRight()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println(">RightENC: " + getEncoderRight());
//			System.out.println(">LeftENC: " + -getEncoderRight()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println("LeftActual:" + getEncoderLeft());
			System.out.println("Right Turn");
			System.out.println("P*Difference" + (P*(encoderDifference)));
		}else {
			double gyroDifference = getAdjustedHeading() - turn;
			double angleRatio = gyroDifference/Math.abs(turn)*0.82;
			if (angleRatio >= 1) {
				angleRatio = 1;
			}
			double encoderDifference = (getEncoderLeft()*(((radius+WHEELBASE_IN) / radius)) - (-getEncoderRight()));
			m_LeftMaster.set(ControlMode.PercentOutput, ((speed)-(P*(encoderDifference)))*(angleRatio));
//			m_LeftMaster.set(ControlMode.PercentOutput, ((speed)-(P*(encoderDifference))));
			m_RightMaster.set(ControlMode.PercentOutput, -(speed)*(angleRatio));
//			m_RightMaster.set(ControlMode.PercentOutput, -(speed));
//			m_RightMaster.set(ControlMode.Position, -getEncoderLeft()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println("<LeftENC: " + getEncoderLeft());
//			System.out.println("<RightENC: " + -getEncoderLeft()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println("RightActual:" + getEncoderRight());
			System.out.println("P*Difference: " + (P*(encoderDifference)));
			System.out.println("EncoderLeftCalc: " + (getEncoderLeft()*(((radius+WHEELBASE_IN) / radius))));
			System.out.println("Gyro Ratio " + gyroDifference/Math.abs(turn));
			System.out.println("Angle Ratio " + angleRatio);
			SmartDashboard.putNumber("Angle Ratio", angleRatio);
			SmartDashboard.putNumber("Gyro Ratio", gyroDifference/Math.abs(turn));
			System.out.println("Left Turn");
		}
		SmartDashboard.putNumber("GyroPID", P*encoderDifference);
	}

	public void radialDriveAtSpeed(double speed, double radius, boolean rightTurn) {
		
		double P = 0.00008;
		System.out.println("Initial Heading" + m_initialHeading);
		System.out.println("Speed  = " + speed + " R OUt " + m_RightMaster.getMotorOutputPercent() + " L Out " + m_LeftMaster.getMotorOutputPercent());
		if(rightTurn) {
			double encoderDifference = (-getEncoderRight()*(((radius+WHEELBASE_IN) / radius))) - getEncoderLeft();
			m_RightMaster.set(ControlMode.PercentOutput, -((speed)-(P*(encoderDifference))));
			m_LeftMaster.set(ControlMode.PercentOutput, ((speed)));
//			m_LeftMaster.set(ControlMode.Position, -getEncoderRight()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println(">RightENC: " + getEncoderRight());
//			System.out.println(">LeftENC: " + -getEncoderRight()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println("LeftActual:" + getEncoderLeft());
			System.out.println("Right Turn");
			System.out.println("P*Difference" + (P*(encoderDifference)));
		}else {
			double encoderDifference = (getEncoderLeft()*(((radius+WHEELBASE_IN) / radius)) - (-getEncoderRight()));
			m_LeftMaster.set(ControlMode.PercentOutput, ((speed)-(P*(encoderDifference))));
//			m_LeftMaster.set(ControlMode.PercentOutput, ((speed)-(P*(encoderDifference))));
			m_RightMaster.set(ControlMode.PercentOutput, -(speed));
//			m_RightMaster.set(ControlMode.PercentOutput, -(speed));
//			m_RightMaster.set(ControlMode.Position, -getEncoderLeft()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println("<LeftENC: " + getEncoderLeft());
//			System.out.println("<RightENC: " + -getEncoderLeft()*(((radius+WHEELBASE_IN) / radius)));
//			System.out.println("RightActual:" + getEncoderRight());
			System.out.println("P*Difference: " + (P*(encoderDifference)));
			System.out.println("EncoderLeftCalc: " + (getEncoderLeft()*(((radius+WHEELBASE_IN) / radius))));
			System.out.println("Left Turn");
		}
	}

	public void pandaDriveStraight(double speed) {
		double P = 0.0002;
		double I = 0.000000;
		double D = 0;
		
		encoderDifference = getEncoderLeft() + getEncoderRight();
		derivative = encoderDifference-prevEncoderDiff;
		prevEncoderDiff = encoderDifference;
		integral = integral + encoderDifference;
		if(encoderDifference == 0) {integral = 0;}
		if((Math.abs(integral)>4000)) {integral = 0;}
		
		m_LeftMaster.set(ControlMode.PercentOutput, speed-((P*encoderDifference+D*derivative+I*integral)/2));
		System.out.println("Speed: " + speed + " Talon Right Output: " + m_RightMaster.getMotorOutputPercent() + " Talon Left Output: " + m_LeftMaster.getMotorOutputPercent());
		m_RightMaster.set(ControlMode.PercentOutput, -(speed+((P*encoderDifference+D*derivative+I*integral)/2)));
		System.out.println("Difference: " + (encoderDifference) + "  Integral" + integral + "  I*Integral" + (I*integral));
		
		SmartDashboard.putNumber("STRAIGHTPID", P*encoderDifference+D*derivative+I*integral);
		System.out.println("STRAIGHTPID" + (P*encoderDifference+D*derivative+I*integral));
	}
	public void driveStraightGyro(double speed, double targetHeading) {
		double P = 0.0002;
		double I = 0.000000;
		double D = 0;
		
		encoderDifference = (getAdjustedHeading() - targetHeading) * 128.8;
		
		derivative = encoderDifference-prevEncoderDiff;
		prevEncoderDiff = encoderDifference;
		integral = integral + encoderDifference;
		if(encoderDifference == 0) {integral = 0;}
		if((Math.abs(integral)>4000)) {integral = 0;}
		
		m_LeftMaster.set(ControlMode.PercentOutput, speed-((P*encoderDifference+D*derivative+I*integral)/2));
		System.out.println("Speed: " + speed + " Talon Right Output: " + m_RightMaster.getMotorOutputPercent() + " Talon Left Output: " + m_LeftMaster.getMotorOutputPercent());
		m_RightMaster.set(ControlMode.PercentOutput, -(speed+((P*encoderDifference+D*derivative+I*integral)/2)));
		System.out.println("Difference: " + (encoderDifference) + "  Integral" + integral + "  I*Integral" + (I*integral));
		
		SmartDashboard.putNumber("STRAIGHTPID", P*encoderDifference+D*derivative+I*integral);
		System.out.println("STRAIGHTPID" + (P*encoderDifference+D*derivative+I*integral));
	}
	
	public void cubeDrive(double speed) {
		vision_error = m_vision.getError();
		
		vision_speedLeft = speed  - (vision_P*vision_error)/2;
		vision_speedRight = speed + (vision_P*vision_error)/2;
		
		
		m_LeftMaster.set(vision_speedLeft);
		m_RightMaster.set(vision_speedRight);
	}
	
	public double autonDriveTurn(double direction, double initialHeading){
//		myRobot.tankDrive(speed, -speed);
//		m_LeftMaster.set(ControlMode.MotionMagic, (direction * aConstants.encoderTicksPer90Degrees));
//		m_RightMaster.set(ControlMode.MotionMagic, (direction * aConstants.encoderTicksPer90Degrees));
//		System.out.println("Running Tank	 Turn");
		
//		m_LeftMaster.set(ControlMode.PercentOutput , direction * aConstants.DefaultTurningSpeed);
//		m_RightMaster.set(ControlMode.PercentOutput, direction * aConstants.DefaultTurningSpeed);
//		System.out.println(getHeading());	
//		if(Math.abs(getHeading()) > 78) {
//				m_turned = true;
//			}else {
//				m_turned = false;
//		}			
//		return m_turned;
		double turnError = (initialHeading + direction*90)-getHeading();
//		System.out.println("Initial Heading: " + initialHeading);
//		System.out.println("Direction: " + direction);
//		System.out.println("Get Heading: " + getHeading());
		System.out.println("Turn Error: " + turnError);
		double P = 1/90;
		if(Math.abs(turnError) > 40) {
			myRobot.tankDrive(direction*aConstants.DefaultTurningSpeed,-direction*aConstants.DefaultTurningSpeed);
		}else {
			myRobot.tankDrive(direction*aConstants.DefaultTurningSpeed*P*turnError,-direction*aConstants.DefaultTurningSpeed*P*turnError);
			System.out.println("Initial Heading: " + initialHeading);
			System.out.println("Direction: " + direction);
			System.out.println("Get Heading: " + getHeading());
		}
		return turnError;
	}


	//////////////////////////////////////////////////////////////
	//////////////		    	PID              ////////////////
	/////////////////////////////////////////////////////////////	
//	int sumEncoderErrors;
//
//	public void drivePID(double desiredLeftMotorSpeed, double turnRadius){
////		double P = SmartDashboard.getNumber("PID P");
//		//double I = SmartDashboard.getNumber("PID I");
//		double P = .05;
//		double encoderError;
//		if(turnRadius != 0){
//			encoderError = getEncoderLeft() - getEncoderRight() - (getEncoderLeft() * (1-(turnRadius-11)/(turnRadius+11)));
//		} else{
//			encoderError = -(getEncoderLeft() + getEncoderRight());
//		}
//		double rightMotorSpeed;
//		if(desiredLeftMotorSpeed >= 0){
//			if(leftMotorSpeed < desiredLeftMotorSpeed){
//				leftMotorSpeed = leftMotorSpeed+MOTOR_INCREMENT_RATE;
//			}else if(leftMotorSpeed > desiredLeftMotorSpeed){
//				leftMotorSpeed = leftMotorSpeed-MOTOR_INCREMENT_RATE;
//			}else{
//				leftMotorSpeed = desiredLeftMotorSpeed;
//			}
//		} else{
//			if(leftMotorSpeed > desiredLeftMotorSpeed){
//				leftMotorSpeed = leftMotorSpeed-MOTOR_INCREMENT_RATE;
//			}else if(leftMotorSpeed < desiredLeftMotorSpeed){
//				leftMotorSpeed = leftMotorSpeed+MOTOR_INCREMENT_RATE;
//			}else{
//				leftMotorSpeed = desiredLeftMotorSpeed;
//			}
//		}
//		
//		rightMotorSpeed = leftMotorSpeed + P*encoderError;
////		myRobot.tankDrive(-leftMotorSpeed, -rightMotorSpeed);
//		m_LeftMaster.set(ControlMode.PercentOutput, leftMotorSpeed);
//		m_RightMaster.set(ControlMode.PercentOutput, -rightMotorSpeed);
//	}
	
	public int getEncoderLeft(){
		return m_LeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIdx);
	}
	
	public int getEncoderRight(){
		return m_RightMaster.getSelectedSensorPosition(Constants.kPIDLoopIdx);
	}
	
	public double getEncoderLeftInches(){
		return m_LeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIdx) / AutonConstants.ticksPerInch;
	}
	
	public double getEncoderRightInches(){
		return m_RightMaster.getSelectedSensorPosition(Constants.kPIDLoopIdx) / AutonConstants.ticksPerInch;
	}
	
	public int getEncoderLeftVelocity(){
		return m_LeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIdx);
	}
	
	public int getEncoderRightVelocity(){
		return m_RightMaster.getSelectedSensorVelocity(Constants.kPIDLoopIdx);
	}
	
	public void resetEncoders(){
		m_RightMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		m_LeftMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}
	
	public double getHeading(){
		return m_Gyro.getAngle();
	}
	public void initHeading() {
		m_initialHeading = getHeading();
	}
	public double getAdjustedHeading() {
		return getHeading() - m_initialHeading;
	}	
	public void resetGyro(){
		m_Gyro.reset();
	}
	
	public void setRampRate(double rate){
		m_LeftMaster.configOpenloopRamp(rate, 10);
		m_RightMaster.configOpenloopRamp(rate, 10);

	}
	
	public void setBrakeMode(boolean brake) {
		if (brake){
			m_LeftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
			m_RightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}else{
			m_LeftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
			m_RightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
		
		
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", getEncoderRightInches());
		SmartDashboard.putNumber("heading",getHeading());
		SmartDashboard.putNumber("Encoder Diff", encoderDifference);
		SmartDashboard.putNumber("integral", integral);
		System.out.println("RightEncoder: " + getEncoderRight() + " LeftEncoder: " + getEncoderLeft());
		
//		/* smart dash plots */
//		SmartDashboard.putNumber("RSensorVel", m_RightMaster.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
//		SmartDashboard.putNumber("RSensorPos", m_RightMaster.getSelectedSensorPosition(Constants.kPIDLoopIdx));		
		SmartDashboard.putNumber("RMotorOutputPercent", m_RightMaster.getMotorOutputPercent());
//		SmartDashboard.putNumber("RClosedLoopError", m_RightMaster.getClosedLoopError(Constants.kPIDLoopIdx));
//		
//		/* print the Active Trajectory Point Motion Magic is servoing towards */
////		SmartDashboard.putNumber("RClosedLoopTarget", m_RightMaster.getClosedLoopTarget(Constants.kPIDLoopIdx));
//    	SmartDashboard.putNumber("RActTrajVelocity", m_RightMaster.getActiveTrajectoryVelocity());
//    	SmartDashboard.putNumber("RActTrajPosition", m_RightMaster.getActiveTrajectoryPosition());
//    	SmartDashboard.putNumber("RActTrajHeading", m_RightMaster.getActiveTrajectoryHeading());
//		
//		SmartDashboard.putNumber("LSensorVel", m_LeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
//		SmartDashboard.putNumber("LSensorPos", m_LeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIdx));		
		SmartDashboard.putNumber("LMotorOutputPercent", m_LeftMaster.getMotorOutputPercent());
//		SmartDashboard.putNumber("LClosedLoopError", m_LeftMaster.getClosedLoopError(Constants.kPIDLoopIdx));
//		
//		/* print the Active Trajectory Point Motion Magic is servoing towards */
////		SmartDashboard.putNumber("LClosedLoopTarget", m_LeftMaster.getClosedLoopTarget(Constants.kPIDLoopIdx));
//    	SmartDashboard.putNumber("LActTrajVelocity", m_LeftMaster.getActiveTrajectoryVelocity());
//    	SmartDashboard.putNumber("LActTrajPosition", m_LeftMaster.getActiveTrajectoryPosition());
//    	SmartDashboard.putNumber("LActTrajHeading", m_LeftMaster.getActiveTrajectoryHeading());
		
	}

}
