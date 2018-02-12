package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonUp {
	AutonConstants aConstants;
	private DriveTrain m_driveTrain;
	private States currentState;
	private double m_elevatorDistance;
	private double m_elevatorSpeed;
	private boolean done;
	
	public enum States {
		INIT,
		DRIVE_AHEAD,
		DRIVE_STOP,
		END,
	}

	public  AutonUp(DriveTrain driveTrain){
		aConstants = new AutonConstants();
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(aConstants.DefaultRampRate);
	}
	
	public boolean auton(String m_args){
		done=false;
		switch(currentState){
		case INIT: 
			m_driveTrain.resetEncoders();  // Elevator Train not Drive
			m_driveTrain.setBrakeMode(true);
			m_elevatorDistance = 0;										// May want to warn if this is not changed by a passed argument
			m_elevatorSpeed = aConstants.DefaultElevatorSpeed;
			if (m_args.length()!=0) {
				String[] stringArray = m_args.split(",");
	 		    double tmpDouble;
	 		    for (int j = 0; j < stringArray.length; j++) {
	 		       String numberAsString = stringArray[j];
 		    	   tmpDouble = Double.parseDouble(numberAsString);
 		    	   if (j == 1) m_elevatorDistance = tmpDouble;
 		    	   if (j == 2) m_elevatorSpeed = tmpDouble;
	 		    }
	 		}
			currentState = States.DRIVE_AHEAD;
		break;
		case DRIVE_AHEAD:
			m_driveTrain.autonDriveStraight(m_elevatorSpeed);  // Elevator move not drive strait
			if (m_driveTrain.getEncoderLeftInches() > m_elevatorDistance) {    // We need to make this more accurate !!!!  (and calibrate)
				currentState = States.DRIVE_STOP;
			}
		break;
		case DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);  // same as above
			currentState = States.END;
		break;
		case END:
			done=true;
			currentState = States.INIT;     // Set up for getting called again
		break;
		}
		return done;
	}
	
	public String getState(){
		return currentState.name();
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
		SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
		SmartDashboard.putString("AutonState", getState());
	}
}
