package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

public class Sensor {

	ElectricalConstants eConstants = new ElectricalConstants();
	
	private DigitalInput m_sensorIn;
	private DigitalOutput m_sensorOut;
	private AnalogInput m_analogIn;
	
	
	public Sensor(){
		 m_sensorIn = new DigitalInput(eConstants.dio9);
		 m_sensorOut = new DigitalOutput(eConstants.dio8);
		 m_analogIn = new AnalogInput(0);
	}
	
	public void update(){
		//m_sensorOut.set(m_sensorIn.get());
//		if (m_analogIn.getVoltage() > 2){
//			m_sensorOut.set(false);
//		} else{
//			m_sensorOut.set(true);
//		}
		m_sensorOut.set(m_analogIn.getValue()> 700);
		SmartDashboard.putNumber("Analog In",m_analogIn.getValue());
	}
}
