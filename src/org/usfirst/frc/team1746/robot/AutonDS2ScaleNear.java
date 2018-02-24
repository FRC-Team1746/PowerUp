package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonDS2ScaleNear {
	private TeleopDriveTrain m_driveTrain;
	private States currentState;
	
	public enum States {
		INIT,
		DRIVEFWD,
		TURN,
		DRIVEFWD2,
		
	}
	public AutonDS2ScaleNear(TeleopDriveTrain driveTrain) {
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(.5);
	}
	
	public void auton() {
		switch(currentState) {
		case INIT:
			m_driveTrain.resetEncoders();
			m_driveTrain.setBrakeMode(true);
			currentState = States.DRIVEFWD;
		break;
		case DRIVEFWD:
			m_driveTrain.autonDriveStraight(.5);
			if (m_driveTrain.getEncoderLeftInches() > 150){
				currentState = States.TURN;
			}
		break;
		case TURN:
			
		}
	}
}
