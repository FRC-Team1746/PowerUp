package org.usfirst.frc.team1746.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;
import edu.wpi.first.wpilibj.VictorSP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import java.util.concurrent.TimeUnit;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

public class Lift {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;

	private VictorSPX m_liftLeft;
	private WPI_TalonSRX m_liftRight;
	private double m_liftPosition;
	
	StringBuilder _sb = new StringBuilder();
	
//	private Encoder m_liftEncoder;
	
	public Lift(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		m_liftLeft = new VictorSPX(m_eConstants.ELEVATOR_LEFT);
		m_liftRight = new WPI_TalonSRX(m_eConstants.ELEVATOR_RIGHT);
		m_liftLeft.follow(m_liftRight);
//		m_liftEncoder = new Encoder(m_eConstants.ENCODER_LIFT_A, m_eConstants.ENCODER_LIFT_B, false, Encoder.EncodingType.k1X);	
		
		m_liftPosition = 0;
		
		/* first choose the sensor */
		m_liftRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		m_liftRight.setSensorPhase(true);
		m_liftRight.setInverted(false);
		/* Set relevant frame periods to be at least as fast as periodic rate*/
		m_liftRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,
		Constants.kTimeoutMs);
		m_liftRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,
		Constants.kTimeoutMs);
		/* set the peak and nominal outputs */
		m_liftRight.configNominalOutputForward(0, Constants.kTimeoutMs);
		m_liftRight.configNominalOutputReverse(0, Constants.kTimeoutMs);
		m_liftRight.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_liftRight.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/* set closed loop gains in slot0 - see documentation */
		m_liftRight.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
//		_talon.config_kF(0, 0.2, Constants.kTimeoutMs);
//		_talon.config_kP(0, 0.2, Constants.kTimeoutMs);
//		_talon.config_kI(0, 0, Constants.kTimeoutMs);
//		_talon.config_kD(0, 0, Constants.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		m_liftRight.configMotionCruiseVelocity(5250, Constants.kTimeoutMs);
		m_liftRight.configMotionAcceleration(21000, Constants.kTimeoutMs);
		/* zero the sensor */
		m_liftRight.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		m_liftRight.configOpenloopRamp(0, 0);
		m_liftRight.configClosedloopRamp(0, 0);

		resetEncoder();
	}
	
	
	
	public void resetEncoder(){
//		m_liftEncoder.reset();
		m_liftRight.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public void setRampRate(double rate){
		m_liftRight.configOpenloopRamp(rate, 5);
		m_liftLeft.configOpenloopRamp(rate, 5);
		
	}
	
	
	public void setBrakeMode(boolean brake) {
		if (brake){
			m_liftRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
			m_liftLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}
	}
	public void setCoast(boolean coast){
		if (coast) {
			m_liftRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
			m_liftLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
	}
	
	public void update() {
		if (m_controls.oper_Y_Button()) {
			m_liftPosition = 6*4000;
		}
		if (m_controls.oper_X_Button()) {
			m_liftPosition = 3*4000;
		}
		if (m_controls.oper_A_Button()) {
			m_liftPosition = 0;
		}
		//m_liftRight.set(ControlMode.Position, m_liftPosition);
		System.out.println(m_liftRight.getSelectedSensorVelocity(0));
		m_liftRight.set(ControlMode.MotionMagic, m_liftPosition);
	}
	public double getLiftPosition(){
		return m_liftRight.getSensorCollection().getQuadraturePosition();
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Lift position", getLiftPosition());
	}
	
}