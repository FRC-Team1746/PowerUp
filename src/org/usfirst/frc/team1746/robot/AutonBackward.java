package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class AutonBackward {
	AutonConstants aConstants;
	private DriveTrain m_driveTrain;
	private States currentState;
	private double m_drivingDistance;
	private double m_drivingSpeed;
	private boolean done;
	
	public enum States {
		INIT,
		DRIVE_BACKWARD,
		DRIVE_STOP,
		END,
	}

	public  AutonBackward(DriveTrain driveTrain){
		aConstants = new AutonConstants();
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(aConstants.DefaultRampRate);
	}
	
	public boolean auton(String m_args){
		done=false;
		switch(currentState){
		case INIT: 
			m_driveTrain.resetEncoders();
			m_driveTrain.setBrakeMode(true);
			m_drivingDistance = 0;										// May want to warn if this is not changed by a passed argument
			m_drivingSpeed = aConstants.DefaultBackwardsSpeed;
			if (m_args.length()!=0) {
				String[] stringArray = m_args.split(",");
	 		    double tmpDouble;
	 		    for (int j = 0; j < stringArray.length; j++) {
	 		       String numberAsString = stringArray[j];
 		    	   tmpDouble = Double.parseDouble(numberAsString);
 		    	   if (j == 0) m_drivingDistance = tmpDouble;
 		    	   if (j == 1) m_drivingSpeed = tmpDouble;
	 		    }
	 		}
			currentState = States.DRIVE_BACKWARD;
		break;
		case DRIVE_BACKWARD:
			m_driveTrain.autonDriveStraight(-m_drivingSpeed);
			if (Math.abs(m_driveTrain.getEncoderLeftInches()) > m_drivingDistance) {    // We need to make this more accurate !!!!  (and calibrate)
				currentState = States.DRIVE_STOP;
			}
		break;
		case DRIVE_STOP:
			m_driveTrain.autonDriveStraight(0);
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