package org.usfirst.frc.team1746.robot;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class DriveTrain {
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Class setup
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	ElectricalConstants eConstants;
	
	private Controls m_controls;
	private WPI_TalonSRX m_LeftMaster;
	private WPI_TalonSRX m_LeftFollowerA;
	private WPI_TalonSRX m_LeftFollowerB;
	private WPI_TalonSRX m_RightMaster;
	private WPI_TalonSRX m_RightFollowerA;
	private WPI_TalonSRX m_RightFollowerB;
	
	
	private DifferentialDrive myRobot;
	
	private Encoder m_encoderLeft;
	private Encoder m_encoderRight;
	private ADXRS450_Gyro m_Gyro;
	
	public DriveTrain(Controls controls) {
		m_controls = controls;
		eConstants = new ElectricalConstants();
		m_LeftMaster = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_MASTER);
		m_LeftFollowerA = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_FOLLOWER_A);
		m_LeftFollowerB = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_FOLLOWER_B);
		m_RightMaster = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_MASTER);
		m_RightFollowerA = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_FOLLOWER_A);
		m_RightFollowerB = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_FOLLOWER_B);
		
		myRobot = new DifferentialDrive(m_LeftMaster, m_RightMaster);
		
		m_LeftFollowerA.follow(m_LeftMaster);
		m_LeftFollowerB.follow(m_LeftMaster);
		m_RightFollowerA.follow(m_RightMaster);
		m_RightFollowerB.follow(m_RightMaster);
		
		m_encoderLeft = new Encoder(eConstants.ENCODER_DRIVE_LEFT_A, eConstants.ENCODER_DRIVE_LEFT_B, false, Encoder.EncodingType.k1X);
		m_encoderRight = new Encoder(eConstants.ENCODER_DRIVE_RIGHT_A, eConstants.ENCODER_DRIVE_RIGHT_B, false, Encoder.EncodingType.k1X);
		m_Gyro = new ADXRS450_Gyro();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Drivetrain init
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	SendableChooser<String> driveSelector = new SendableChooser<>();
		
	public void init(){
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Drivetrain Functions
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void teleopArcadeDrive(){
		myRobot.arcadeDrive(-m_controls.driver_Y_Axis(), -m_controls.driver_X_Axis());
		setRampRate(0.5);
		setCoast(true);
		resetEncoders();
		
		
	}
	
	public void autonDriveStraight(double speed){
		myRobot.tankDrive(speed, speed);
	}
	
	public void autonDriveTurn(double speed){
		myRobot.tankDrive(speed, -speed);
	}
	
	public int getEncoderLeft(){
		return m_encoderLeft.get();
	}
	public int getEncoderRight(){
		return m_encoderRight.get();
	}
	
	public double getEncoderLeftInches(){
		return m_encoderLeft.get()*Math.PI*4/360;
	}
	public double getEncoderRightInches(){
		return m_encoderRight.get()*Math.PI*4/360;
	}
	
	public void resetEncoders(){
		m_encoderLeft.reset();
		m_encoderRight.reset();
	}
	
	public double getHeading(){
		return m_Gyro.getAngle();
	}
	
	public void resetGyro(){
		m_Gyro.reset();
	}
	
	public void setRampRate(double rate){
		m_LeftMaster.configOpenloopRamp(rate, 5);
		m_RightMaster.configOpenloopRamp(rate, 5);
	}
	
	
	public void setBrakeMode(boolean brake) {
		if (brake){
			m_LeftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
			m_RightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}
	}
	public void setCoast(boolean coast){
		if (coast) {
			m_LeftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
			m_RightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", getEncoderRightInches());
		SmartDashboard.putNumber("heading",getHeading());
		SmartDashboard.putNumber("Both Encoders",bothEncoderInchValues());
	}
	

	public double bothEncoderInchValues() {
		
		return Math.abs(m_encoderRight.get()*Math.PI*4/360 + m_encoderLeft.get()*Math.PI*4/360);
			
	}
	
}
