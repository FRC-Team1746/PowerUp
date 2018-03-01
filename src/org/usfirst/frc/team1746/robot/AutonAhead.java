 package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonAhead {
	AutonConstants aConstants;
	private DriveTrain m_driveTrain;
	private States currentState;
	private double m_drivingDistance;
	private double m_drivingSpeed;
	private boolean done;
	
	public enum States {
		INIT,
		DRIVE_AHEAD,
		DRIVE_STOP,
		END,
	}

	public  AutonAhead(DriveTrain driveTrain){
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
			m_drivingSpeed = aConstants.DefaultDrivingSpeed;
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
			System.out.println("Driving Distance: " + m_drivingDistance);
			System.out.println("Driving Speed: " + m_drivingSpeed);
			currentState = States.DRIVE_AHEAD;
		break;
		case DRIVE_AHEAD:
			System.out.println("About to Drive");
			m_driveTrain.autonDriveStraight(m_drivingSpeed);
			System.out.println("Driving right now");
			System.out.println("Distance to Travel: " + m_drivingDistance + "             Right Inches: " + m_driveTrain.getEncoderRightInches() + "             Left Inches: " + m_driveTrain.getEncoderLeftInches());
			if (m_driveTrain.getEncoderLeftInches() > m_drivingDistance) {    // We need to make this more accurate !!!!  (and calibrate)
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
