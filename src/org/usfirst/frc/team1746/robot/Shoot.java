package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

public class Shoot {
	
	ElectricalConstants m_eConstants;
	Controls m_controls;
	
	Solenoid m_shootIn;
	Solenoid m_shootOut;
	
	
	public Shoot(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		m_shootIn = new Solenoid(m_eConstants.SHOOTIN);
		m_shootOut = new Solenoid(m_eConstants.SHOOTOUT);
		}
	
	public void shootOut(){
		m_shootIn.set(false);
		m_shootOut.set(true);
	}
	
	public void shootIn(){
		m_shootOut.set(false);
		m_shootIn.set(true);
	}	
	
	public void update() {
		if(m_controls.oper_B_Button()){
			shootOut();
		}else {
			shootIn();
		}	
	}
}