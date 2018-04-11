package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Climber {

	ElectricalConstants m_eConstants;
	Controls m_controls;
	Constants constants;

	private VictorSPX m_climber1;
	private VictorSPX m_climber2;
	private VictorSPX m_climber3;
//	private double m_liftPosition;
//	private boolean moving;
	
//	private Encoder m_liftEncoder;
	
	public Climber(Controls controls) {
		m_controls = controls;
		m_eConstants =  new ElectricalConstants();
		constants = new Constants();
		m_climber1 = new VictorSPX(m_eConstants.CLIMBER_1);
		m_climber2 = new VictorSPX(m_eConstants.CLIMBER_2);
		m_climber3 = new VictorSPX(m_eConstants.CLIMBER_3);
		m_climber2.follow(m_climber1);
		m_climber3.follow(m_climber1);
	}
}
