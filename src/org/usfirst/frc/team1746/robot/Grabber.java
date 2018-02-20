package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabber {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	Lift m_lift;
	PowerDistributionPanel m_pdp;
	private WPI_TalonSRX m_grabberRight;
	private WPI_TalonSRX m_grabberLeft;
	private double grabberOut;
	private double grabberInHard;
	private double grabberInSoft;
	
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
		if (m_lift.getLiftPosition() <= 15 || m_lift.getLiftPosition() >= 6000){
			if (m_controls.oper_LB_Button()){
				m_grabberLeft.set(grabberOut);
			} else if(m_pdp.getCurrent(7) < 1){
				m_grabberLeft.set(grabberInHard);
			} else if(m_pdp.getCurrent(7) >= 1){
				m_grabberLeft.set(grabberInSoft);
			}
		
			if (m_controls.oper_RB_Button()){
				m_grabberRight.set(-grabberOut);
			} else if(m_pdp.getCurrent(4) < 1){
				m_grabberRight.set(-grabberInHard);
			} else if(m_pdp.getCurrent(4) >= 1){
				m_grabberRight.set(-grabberInSoft);
			}
		} else {
			m_grabberLeft.set(1);
			m_grabberRight.set(1);
		}
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("PDP 7", m_pdp.getCurrent(7));
		SmartDashboard.putNumber("PDP 4", m_pdp.getCurrent(4));
	}
}