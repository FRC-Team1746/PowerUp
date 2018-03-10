package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Retractor {	
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	Lift m_lift;
	Constants constants;
	AutonConstants m_aConstants;
	
	private WPI_TalonSRX m_retractor;
//	private WPI_TalonSRX m_retractorLeft;

	public Retractor(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		m_aConstants = new AutonConstants();
//		m_retractorLeft = new WPI_TalonSRX(m_eConstants.RETRACTOR_LEFT);
		m_retractor = new WPI_TalonSRX(m_eConstants.RETRACTOR);
	}
	//Positive Direction May Be Down
	
	public void update(){
		if (m_controls.oper_YR_Axis() > .15 || m_controls.oper_YR_Axis() < -.15) {
//			System.out.println("Moving Retractor in Teleop  -  Output Percentage: " + (-m_controls.oper_YR_Axis()/3*4));
			m_retractor.set(ControlMode.PercentOutput, -m_controls.oper_YR_Axis()/3*4);
			m_retractor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}else{
			m_retractor.set(0);
		}
	}
	
	public void retractorDown() {
		m_retractor.set(ControlMode.PercentOutput, -m_aConstants.DefaultRetractorDown);
	}
	public void retractorUp() {
		m_retractor.set(ControlMode.PercentOutput, m_aConstants.DefaultRetractorUp);
	}
	
	public void retractorStop() {
		m_retractor.set(0);
	}
}
