package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabber {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	Lift m_lift;
	Constants constants;
	PowerDistributionPanel m_pdp;
	
	private WPI_TalonSRX m_grabberRight;
	private WPI_TalonSRX m_grabberLeft;
	private double grabberOut;
	private double grabberInHard;
	private double grabberInSoft;

	private double m_grabberPosition;
	
	public Grabber(Controls controls, Lift lift) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		m_lift = lift;
		m_grabberLeft = new WPI_TalonSRX(m_eConstants.GRABBER_LEFT);
		m_grabberRight = new WPI_TalonSRX(m_eConstants.GRABBER_RIGHT);
		m_pdp = new PowerDistributionPanel(0);
		
		m_grabberLeft.configContinuousCurrentLimit(2, 0);
		m_grabberRight.configContinuousCurrentLimit(2, 0);
		
		grabberOut = .4;
		grabberInHard = -.4;
		grabberInSoft = -.15;
		
		m_grabberPosition = 13;
		
		/* first choose the sensor */
		m_grabberRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,constants.kPIDLoopIdx, constants.kTimeoutMs);
		m_grabberRight.setSensorPhase(true);
		m_grabberRight.setInverted(true);
		/* Set relevant frame periods to be at least as fast as periodic rate*/
		m_grabberRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,
		constants.kTimeoutMs);
		m_grabberRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,
		constants.kTimeoutMs);
		/* set the peak and nominal outputs */
		m_grabberRight.configNominalOutputForward(0, constants.kTimeoutMs);
		m_grabberRight.configNominalOutputReverse(0, constants.kTimeoutMs);
		m_grabberRight.configPeakOutputForward(1, constants.kTimeoutMs);
		m_grabberRight.configPeakOutputReverse(-1, constants.kTimeoutMs);
		/* set closed loop gains in slot0 - see documentation */
		m_grabberRight.selectProfileSlot(constants.kSlotIdx, constants.kPIDLoopIdx);
		m_grabberRight.config_kF(0, .146, Constants.kTimeoutMs);
		m_grabberRight.config_kP(0, 2.5, Constants.kTimeoutMs);
		m_grabberRight.config_kI(0, 0, Constants.kTimeoutMs);
		m_grabberRight.config_kD(0, 25, Constants.kTimeoutMs);
		m_grabberLeft.config_kF(0, .146, Constants.kTimeoutMs);
		m_grabberLeft.config_kP(0, 2.5, Constants.kTimeoutMs);
		m_grabberLeft.config_kI(0, 0, Constants.kTimeoutMs);
		m_grabberLeft.config_kD(0, 25, Constants.kTimeoutMs);
		/* set acceleration and cruise velocity - see documentation */
		m_grabberRight.configMotionCruiseVelocity(500, constants.kTimeoutMs);
		m_grabberRight.configMotionAcceleration(5000, constants.kTimeoutMs);
		/* zero the sensor */
		m_grabberRight.setSelectedSensorPosition(0, constants.kPIDLoopIdx, constants.kTimeoutMs);
		m_grabberRight.configOpenloopRamp(0, 0);
		m_grabberRight.configClosedloopRamp(0, 0);
		
	}
	
	public void setRampRate(double rate){
		m_grabberRight.configOpenloopRamp(rate, 5);
		m_grabberLeft.configOpenloopRamp(rate, 5);
		
	}
	
	
	public void setBrakeMode(boolean brake) {
		if (brake){
			m_grabberRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
			m_grabberLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}
	}
	public void setCoast(boolean coast){
		if (coast) {
			m_grabberRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
			m_grabberLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
	}
	
	public void update(){
//		System.out.println("Grabber");
//		System.out.println(m_grabberRight.getSelectedSensorPosition(0));
//		if (m_controls.oper_UP_DPAD()) {
//			m_grabberRight.configMotionCruiseVelocity(1000, constants.kTimeoutMs);
////			m_grabberLeft.configMotionCruiseVelocity(1000, constants.kTimeoutMs);
//			m_grabberPosition = 3000;
//			System.out.println("DPAD UP Pressed");
//		}else if (m_controls.oper_DOWN_DPAD()) {
//			m_grabberRight.configMotionCruiseVelocity(1000, constants.kTimeoutMs);
////			m_grabberLeft.configMotionCruiseVelocity(1000, constants.kTimeoutMs);
//			m_grabberPosition = 0;
//			System.out.println("DPAD DOWN Pressed");
//		}
//		m_grabberRight.set(ControlMode.MotionMagic, m_grabberPosition);
		
		
//		if (m_lift.getLiftPosition() >= 15 || m_lift.getLiftPosition() <= 300){
//			if (m_controls.oper_LB_Button()){
//				m_grabberLeft.set(grabberOut);
//			} else if(m_pdp.getCurrent(7) < 1){
//				m_grabberLeft.set(grabberInHard);
//			} else if(m_pdp.getCurrent(7) >= 1){
//				m_grabberLeft.set(grabberInSoft);
//			}
//		
//			if (m_controls.oper_RB_Button()){
//				m_grabberRight.set(-grabberOut);
//			} else if(m_pdp.getCurrent(4) < 1){
//				m_grabberRight.set(-grabberInHard);
//			} else if(m_pdp.getCurrent(4) >= 1){
//				m_grabberRight.set(-grabberInSoft);
//			}
//		} else {
//			m_grabberLeft.set(1);
//			m_grabberRight.set(1);
//		}
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("PDP 7", m_pdp.getCurrent(7));
		SmartDashboard.putNumber("PDP 4", m_pdp.getCurrent(4));
	}
}