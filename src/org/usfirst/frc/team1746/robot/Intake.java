package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	AutonConstants m_aConstants;

	private VictorSPX m_intakeLeft;
	private VictorSPX m_intakeRight;
	
	public Intake(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		m_aConstants = new AutonConstants();
		m_intakeLeft = new VictorSPX(m_eConstants.CUBE_INTAKE_LEFT);
		m_intakeRight = new VictorSPX(m_eConstants.CUBE_INTAKE_RIGHT);
		m_intakeRight.configOpenloopRamp(0, 5);
		m_intakeLeft.configOpenloopRamp(0, 5);
	}
	
	public void setRampRate(double rate){
		m_intakeRight.configOpenloopRamp(rate, 5);
		m_intakeLeft.configOpenloopRamp(rate, 5);
		
	}
	
	public void setBrakeMode(boolean brake) {
		if (brake){
			m_intakeRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
			m_intakeLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake); 
		}
	}
	public void setCoast(boolean coast){
		if (coast) {
			m_intakeRight.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
			m_intakeLeft.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
	}
	
	public void update(){
		if (m_controls.oper_RT_Axis() > .1) { //Spin In
			m_intakeLeft.set(ControlMode.PercentOutput, -m_controls.oper_RT_Axis());
			m_intakeRight.set(ControlMode.PercentOutput, m_controls.oper_RT_Axis());
		}else if (m_controls.oper_LT_Axis() > .1){ //Spin Out
			m_intakeLeft.set(ControlMode.PercentOutput, m_controls.oper_LT_Axis());
			m_intakeRight.set(ControlMode.PercentOutput, -m_controls.oper_LT_Axis());
		}else if (m_controls.oper_LEFT_DPAD()) {
			m_intakeLeft.set(ControlMode.PercentOutput, -.5);
			m_intakeRight.set(ControlMode.PercentOutput, -.5);
		}else if (m_controls.oper_RIGHT_DPAD()) {
			m_intakeLeft.set(ControlMode.PercentOutput, .5);
			m_intakeRight.set(ControlMode.PercentOutput, .5);
		}else { //Nope
			m_intakeRight.set(ControlMode.PercentOutput, 0);
			m_intakeLeft.set(ControlMode.PercentOutput, 0);
		}
	}
	
	public void intakeIn() {
		m_intakeLeft.set(ControlMode.PercentOutput, -m_aConstants.DefaultIntakeSpeed);
		m_intakeRight.set(ControlMode.PercentOutput, m_aConstants.DefaultIntakeSpeed);
		
	}
	public void intakeOut() {
		m_intakeLeft.set(ControlMode.PercentOutput,  m_aConstants.DefaultIntakeSpeed);
		m_intakeRight.set(ControlMode.PercentOutput, -m_aConstants.DefaultIntakeSpeed);
		
	}
	public void intakeStop() {
		m_intakeRight.set(ControlMode.PercentOutput, 0);
		m_intakeLeft.set(ControlMode.PercentOutput, 0);
	}
	
}