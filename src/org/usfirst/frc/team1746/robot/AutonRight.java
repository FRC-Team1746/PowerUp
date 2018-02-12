package org.usfirst.frc.team1746.robot;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutonRight {
	AutonConstants aConstants;
	private DriveTrain m_driveTrain;
	private States currentState;
	private double m_turningSpeed;
	private boolean done;
	private double m_initialHeading;
	
	public enum States {
		INIT,
		TURN_RIGHT,
		TURN_STOP,
		END,
	}

	public  AutonRight(DriveTrain driveTrain){
		aConstants = new AutonConstants();
		m_driveTrain = driveTrain;
		currentState = States.INIT;
		m_driveTrain.setRampRate(aConstants.DefaultRampRate);
	}
	
	public boolean auton(String m_args){
		done=false;
		switch(currentState){
		case INIT: 
			m_initialHeading = m_driveTrain.getHeading();
			m_driveTrain.setBrakeMode(true);									// May want to warn if this is not changed by a passed argument
			m_turningSpeed = aConstants.DefaultTurningSpeed;
			if (m_args.length()!=0) {
				String[] stringArray = m_args.split(",");
	 		    double tmpDouble;
	 		    for (int j = 0; j < stringArray.length; j++) {
	 		       String numberAsString = stringArray[j];
 		    	   tmpDouble = Double.parseDouble(numberAsString);
 		    	   if (j == 0) m_turningSpeed = tmpDouble;
	 		    }
	 		}
			currentState = States.TURN_RIGHT;
		break;
		case TURN_RIGHT:
			m_driveTrain.autonDriveTurn(-m_turningSpeed);
			if (m_driveTrain.getHeading() > 88 + m_initialHeading) {  // We need to make this more accurate !!!!  (and calibrate)
				currentState = States.TURN_STOP;							
			}		

		break;
		case TURN_STOP:
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
//		SmartDashboard.putNumber("Left Encoder", m_driveTrain.getEncoderLeftInches());
//		SmartDashboard.putNumber("Right Encoder", m_driveTrain.getEncoderRightInches());
		SmartDashboard.putString("AutonState", getState());
		SmartDashboard.putNumber("Gyro", m_driveTrain.getHeading());
	}
}
