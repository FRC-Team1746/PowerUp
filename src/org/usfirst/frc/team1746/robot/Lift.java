package org.usfirst.frc.team1746.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Lift {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;

	private VictorSP m_liftLeft;
	private WPI_TalonSRX m_liftRight;
	private double m_liftPosition;
	
//	private Encoder m_liftEncoder;
	
	public Lift(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		m_liftLeft = new VictorSP(m_eConstants.ELEVATOR_LEFT);
		m_liftRight = new WPI_TalonSRX(m_eConstants.ELEVATOR_RIGHT);
//		m_liftEncoder = new Encoder(m_eConstants.ENCODER_LIFT_A, m_eConstants.ENCODER_LIFT_B, false, Encoder.EncodingType.k1X);	
		m_liftRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		resetEncoder();
		m_liftPosition = 0;
		
		/* set acceleration and vcruise velocity - see documentation */
		m_liftRight.configMotionCruiseVelocity(800, 0);
		m_liftRight.configMotionAcceleration(3000, 0);

	}
	
	
	
	public void resetEncoder(){
//		m_liftEncoder.reset();
		m_liftRight.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public void setRampRate(double rate){
		m_liftRight.configOpenloopRamp(rate, 5);
//		m_liftLeft.configOpenloopRamp(rate, 5);
		
	}
	
	
	public void setBrakeMode(boolean brake) {
		if (brake){
			m_liftRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
//			m_liftLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}
	}
	public void setCoast(boolean coast){
		if (coast) {
			m_liftRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
//			m_liftLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
	}
	
	public void updateLift() {
		if (m_controls.driver_Y_Button()) {
			m_liftPosition = -2000;
		}
		if (m_controls.driver_X_Button()) {
			m_liftPosition = 2000;
		}
		if (m_controls.driver_A_Button()) {
			m_liftPosition = 0;
		}
		//m_liftRight.set(ControlMode.Position, m_liftPosition);
		System.out.println(m_liftRight.getSelectedSensorVelocity(0));
		m_liftRight.set(ControlMode.MotionMagic, m_liftPosition);
		
		
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Lift position", m_liftRight.getSensorCollection().getQuadraturePosition());
	}
	
}