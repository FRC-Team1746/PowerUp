package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Retractor {	
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	Lift m_lift;
	Constants constants;
	AutonConstants m_aConstants;
	
	private WPI_TalonSRX m_retractor;

	int analogPos;
	int analogVel;
	double m_retPosition;
	
	public Retractor(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		constants = new Constants();
		m_aConstants = new AutonConstants();
		m_retractor = new WPI_TalonSRX(m_eConstants.RETRACTOR);
				
		analogPos = m_retractor.getSensorCollection().getAnalogIn();
		analogVel = m_retractor.getSensorCollection().getAnalogInVel();

		/* analog signal with no wrap-around (0-3.3V) */
		m_retractor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0); /* PIDLoop=0, timeoutMs=0 */
		/*eFeedbackNotContinuous = 1, subValue/ordinal/timeoutMs = 0*/
		m_retractor.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
		
		m_retractor.setSensorPhase(false);
		m_retractor.setInverted(false);
		
		m_retractor.setSelectedSensorPosition(0, constants.kPIDLoopIdx, constants.kTimeoutMs);
		m_retractor.configOpenloopRamp(0, 0);
		m_retractor.configClosedloopRamp(0, 0);
	}

	public void updateOld(){
		if (m_controls.oper_Y_Axis() > .15 || m_controls.oper_Y_Axis() < -.15) {
//			System.out.println("Moving Retractor in Teleop  -  Output Percentage: " + (-m_controls.oper_YR_Axis()/3*4));
			m_retractor.set(ControlMode.PercentOutput, -m_controls.oper_Y_Axis()/3*4);
			m_retractor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}else{
			m_retractor.set(0);
		}
	}
	
	public void update() {
		
			if (m_controls.oper_Y_Axis() > .15 || m_controls.oper_Y_Axis() < -.15) {
				m_retractor.configMotionCruiseVelocity(2, 10);
				m_retPosition = m_retPosition - m_controls.oper_Y_Axis()/2;
			}else{
				if(m_controls.oper_UP_DPAD()) {
					m_retractor.configMotionCruiseVelocity(constants.retSpeed, 10);
					m_retPosition = constants.retNinetyDeg;
				} else if(m_controls.oper_DOWN_DPAD()) {
					m_retractor.configMotionCruiseVelocity(constants.retSpeed, 10);
					m_retPosition = constants.retZeroDeg;
				} else if(m_controls.oper_RIGHT_DPAD()) {
					m_retractor.configMotionCruiseVelocity(constants.retSpeed, 10);
					m_retPosition = constants.retFourtyFiveDeg;
				} else if(m_controls.oper_LEFT_DPAD()) {
					m_retractor.configMotionCruiseVelocity(constants.retSpeed, 10);
					m_retPosition = constants.retZeroDeg;
				}
			}
		m_retractor.set(ControlMode.MotionMagic, m_retPosition);	
	}
	
	public boolean checkPosition() {
		boolean pastElevator;
		if (analogPos >= constants.retCrashDeg) {
			pastElevator = true;
		}else {
			pastElevator = false;
		}
		return pastElevator;
	}
	
	public double getPot() {
		return m_retractor.getSelectedSensorPosition(constants.kPIDLoopIdx);
	}
	
	public void retractorDown() { //Retractor at 90 degrees
		m_retractor.configMotionCruiseVelocity(constants.retSpeed, 10);
		m_retPosition = constants.retZeroDeg;
//		m_retractor.set(ControlMode.PercentOutput, -m_aConstants.DefaultRetractorDown);
	}
	public void retractorUp() { //Retractor at 45 degrees
		m_retractor.configMotionCruiseVelocity(constants.retSpeed, 10);
		m_retPosition = constants.retFourtyFiveDeg;
//		m_retractor.set(ControlMode.PercentOutput, m_aConstants.DefaultRetractorUp);
	}
	
	public void retractorStop() {
		m_retractor.set(0);
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Pot Pos", analogPos);
		SmartDashboard.putNumber("Pot Vel", analogVel);
	}
}
