package org.usfirst.frc.team1746.robot;


//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.Victor;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class TeleopDriveTrain {
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Class setup
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	ElectricalConstants eConstants;
	
	private Controls m_controls;
	private WPI_TalonSRX m_LeftMaster;
	private WPI_VictorSPX m_LeftFollowerA;
	private WPI_VictorSPX m_LeftFollowerB;
	private WPI_TalonSRX m_RightMaster;
	private WPI_VictorSPX m_RightFollowerA;
	private WPI_VictorSPX m_RightFollowerB;
	
	
	private DifferentialDrive myRobot;
	 
	public TeleopDriveTrain(Controls controls) {
		m_controls = controls;
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

		myRobot = new DifferentialDrive(m_LeftMaster, m_RightMaster);
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Drivetrain init
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
	public void init(){
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Drivetrain Functions
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void teleopArcadeDrive(){
		myRobot.arcadeDrive(-m_controls.driver_Y_Axis(), m_controls.driver_X_Axis());
//		setRampRate(0.25);
//		setCoast(true);		
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
	}

}
