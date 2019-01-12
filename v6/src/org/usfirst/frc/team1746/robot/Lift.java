package org.usfirst.frc.team1746.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import com.ctre.phoenix.motorcontrol.ControlMode;
import java.util.concurrent.TimeUnit;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Lift {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	PowerDistributionPanel m_pdp;
	Constants constants;

	private VictorSPX m_liftLeft;
	private WPI_TalonSRX m_liftRight;
	private double m_liftPosition;
	
	private DigitalInput m_liftBottom;
	private DigitalOutput m_liftTestLED;
	
	StringBuilder _sb = new StringBuilder();
	
//	private Encoder m_liftEncoder;
	
	public Lift(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		constants = new Constants();
		m_liftLeft = new VictorSPX(m_eConstants.ELEVATOR_LEFT);
		m_liftRight = new WPI_TalonSRX(m_eConstants.ELEVATOR_RIGHT);
		m_liftLeft.follow(m_liftRight);
		m_pdp = new PowerDistributionPanel(0);
//		m_liftEncoder = new Encoder(m_eConstants.ENCODER_LIFT_A, m_eConstants.ENCODER_LIFT_B, false, Encoder.EncodingType.k1X);	
		
		m_liftPosition = 13;
		
		/* first choose the sensor */
		m_liftRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,constants.kPIDLoopIdx, constants.kTimeoutMs);
		m_liftRight.setSensorPhase(true);
		m_liftRight.setInverted(false);
		/* Set relevant frame periods to be at least as fast as periodic rate*/
		m_liftRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,
		constants.kTimeoutMs);
		m_liftRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,
		constants.kTimeoutMs);
		/* set the peak and nominal outputs */
		m_liftRight.configNominalOutputForward(0, constants.kTimeoutMs);
		m_liftRight.configNominalOutputReverse(0, constants.kTimeoutMs);
		m_liftRight.configPeakOutputForward(1, constants.kTimeoutMs);
		m_liftRight.configPeakOutputReverse(-1, constants.kTimeoutMs);
		/* set closed loop gains in slot0 - see documentation */
		m_liftRight.selectProfileSlot(constants.kSlotIdx, constants.kPIDLoopIdx);
		m_liftRight.config_kF(0, .146, Constants.kTimeoutMs);
		m_liftRight.config_kP(0, 2.5, Constants.kTimeoutMs);
		m_liftRight.config_kI(0, 0, Constants.kTimeoutMs);
		m_liftRight.config_kD(0, 25, Constants.kTimeoutMs);
		m_liftLeft.config_kF(0, .146, Constants.kTimeoutMs);
		m_liftLeft.config_kP(0, 2.5, Constants.kTimeoutMs);
		m_liftLeft.config_kI(0, 0, Constants.kTimeoutMs);
		m_liftLeft.config_kD(0, 25, Constants.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		m_liftRight.configMotionCruiseVelocity(500, constants.kTimeoutMs);
		m_liftRight.configMotionAcceleration(5000, constants.kTimeoutMs);
		/* zero the sensor */
		m_liftRight.setSelectedSensorPosition(0, constants.kPIDLoopIdx, constants.kTimeoutMs);
		m_liftRight.configOpenloopRamp(0, 0);
		m_liftRight.configClosedloopRamp(0, 0);

		m_liftBottom = new DigitalInput(m_eConstants.LIFT_BOTTOM);
		m_liftTestLED = new DigitalOutput(m_eConstants.LIFT_LED);
		
		resetEncoder();
	}
	
	
	
	public void resetEncoder(){
//		m_liftEncoder.reset();
		m_liftRight.setSelectedSensorPosition(0, 0, 10);
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
		
//		m_liftRight.set(ControlMode.Position, m_liftPosition);
		
		if (m_controls.oper_YR_Axis() > .15 || m_controls.oper_YR_Axis() < -.15) {
			if (m_pdp.getCurrent(9) < 10){
				m_liftRight.set(ControlMode.PercentOutput, -m_controls.oper_YR_Axis()/2);
			}else{
				m_liftRight.set(ControlMode.PercentOutput, -m_controls.oper_YR_Axis()/5);
			}
			System.out.println("Stick");
		} else {
			if (m_controls.oper_Y_Button()) {
				m_liftRight.configMotionCruiseVelocity(1000, constants.kTimeoutMs);
				m_liftPosition = 38667;
				System.out.println("Y Pressed");
			}else if (m_controls.oper_X_Button()) {
				m_liftRight.configMotionCruiseVelocity(2500, constants.kTimeoutMs);
				m_liftPosition = 20000;
				System.out.println("X Pressed");
			}else if (m_controls.oper_A_Button()) {
				m_liftRight.configMotionCruiseVelocity(700, constants.kTimeoutMs);
				m_liftPosition = 13;
				System.out.println("A Pressed");
			}
			
			m_liftTestLED.set(m_liftBottom.get());
			
			if (!m_liftBottom.get() && !m_controls.oper_A_Button() && !m_controls.oper_X_Button() && !m_controls.oper_Y_Button()) {
				m_liftRight.set(0);
			}else {
			m_liftRight.set(ControlMode.MotionMagic, m_liftPosition);
			}
			//System.out.println("Buttons");
		}
		if (!m_liftBottom.get()) {
			resetEncoder();
		}
	
		System.out.println(m_liftRight.getSelectedSensorVelocity(0));
		
	}
	
	public double getLiftPosition(){
		return m_liftRight.getSelectedSensorPosition(0);
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Lift position", getLiftPosition());
		SmartDashboard.putNumber("Intended", m_liftPosition);
		SmartDashboard.putNumber("PDP 8", m_pdp.getCurrent(8));
		SmartDashboard.putNumber("PDP 9", m_pdp.getCurrent(9));
//		SmartDashboard.putNumber("Lift Encoder", m_liftEncoder);
	}
	
}