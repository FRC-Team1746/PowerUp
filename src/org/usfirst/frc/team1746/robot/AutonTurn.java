package org.usfirst.frc.team1746.robot;


import org.usfirst.frc.team1746.robot.AutonGo.States;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutonTurn {
	AutonConstants aConstants;
	private DriveTrain m_driveTrain;
	private States currentState;
	private double m_turningSpeed;
	private boolean done;
	private boolean m_movingYet;
	private double m_initialHeading;
	private double m_turn;
	private int m_countZeroVelocity;
	private double m_finalHeading;
	
	public enum States {
		INIT,
		TURN_NOW,
		TURN_STOP,
		END,
	}

	public  AutonTurn(DriveTrain driveTrain){
		aConstants = new AutonConstants();
		m_driveTrain = driveTrain;
		currentState = States.INIT;
//		m_autonDriveTrain.setRampRate(AutonConstants.DefaultRampRate);
		m_turn = 0; //-1 should be left, 1 should be right
	}
	
	public boolean auton(int turn,String m_args){
		done=false;
		switch(currentState){
		case INIT: 
			m_movingYet = false;
			m_driveTrain.resetEncoders();
			m_initialHeading = m_driveTrain.getHeading();
			m_driveTrain.setBrakeMode(true);
			m_turningSpeed = aConstants.DefaultTurningSpeed;
			m_countZeroVelocity = 0;
			if (m_args.length()!=0) {
				String[] stringArray = m_args.split(",");
	 		    double tmpDouble;
	 		    for (int j = 0; j < stringArray.length; j++) {
	 		       String numberAsString = stringArray[j];
 		    	   tmpDouble = Double.parseDouble(numberAsString);
 		    	   if (j == 0) m_turningSpeed = tmpDouble;
	 		    }
	 		}
			m_turn = turn;
			currentState = States.TURN_NOW;
		break;
		case TURN_NOW:
//			if (m_autonDriveTrain.autonDriveTurn(m_turn)) {
			System.out.println("       Right Inches: " + m_driveTrain.getEncoderRightInches() + "       Left Inches: " + m_driveTrain.getEncoderLeftInches());
//			if (m_driveTrain.getHeading() < -88 + m_initialHeading) {  // We need to make this more accurate !!!!  (and calibrate)
//				currentState = States.TURN_STOP;							
//			}		
			m_driveTrain.autonDriveTurn(m_turn, m_initialHeading);
			if (!m_movingYet){
				if (Math.abs(m_driveTrain.getEncoderRightVelocity()) > aConstants.velocityTolerance && 
						Math.abs(m_driveTrain.getEncoderLeftVelocity()) > aConstants.velocityTolerance){
					m_movingYet=true;
				} 
//				System.out.println("Not Moving Yet");
			} else {
				if (Math.abs(m_driveTrain.getEncoderRightVelocity()) < aConstants.velocityTolerance && 
						Math.abs(m_driveTrain.getEncoderLeftVelocity()) < aConstants.velocityTolerance){
					m_countZeroVelocity++;
//					System.out.println(m_countZeroVelocity);
				} else {
					m_countZeroVelocity = 0;
//					System.out.println(m_countZeroVelocity);
				}
				if (m_countZeroVelocity >= aConstants.zeroVelocitiesTillDone){
					currentState = States.TURN_STOP;
//					System.out.println("Stopping");
				}
			}
		break;
		case TURN_STOP:
			m_driveTrain.autonDriveStraight(0);
//			m_finalHeading = m_autonDriveTrain.getHeading();
//			System.out.println("Degrees Moved"+ (m_finalHeading - m_initialHeading));
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
