package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonLiftMove.States;
import edu.wpi.first.wpilibj.Timer; 

public class AutonRetractor {
	
	AutonConstants aConstants;
	private States currentState;
	private Retractor m_retractor;
	private boolean done;
	private Timer m_timer;
//	private int m_position;
	
	
	public enum States {
		INIT,
		MOVE,
		STOP,
		END,
		
	}
	
    public AutonRetractor(Retractor retractor) {
    	aConstants = new AutonConstants();
    	m_retractor = retractor;
    	m_timer = new Timer();
    	currentState = States.INIT;
    }
//    public boolean auton(String m_args) {
    public boolean auton(String command) {
    	done=false;
    	switch(currentState) {
    	case INIT:
//    		if (m_args.length()!=0) {
//				String[] stringArray = m_args.split(",");
//	 		    double tmpDouble;
//	 		    for (int j = 0; j < stringArray.length; j++) {
//	 		       String numberAsString = stringArray[j];
// 		    	   tmpDouble = Double.parseDouble(numberAsString);
//		    	   if (j == 0) m_position = (int) (tmpDouble);
//	 		    }
//	 		}
//    		System.out.println("Updating m_position to: " + m_position);
    		m_timer.reset();
    		m_timer.start();
    		currentState = States.MOVE;
    	break;
    	case MOVE:
//    		System.out.println("Retractor Updating");
			if (command.equals("D")) {
				if (m_timer.get() < aConstants.RetractorDownTime) {
					m_retractor.retractorDown();
				} else {
					m_retractor.retractorStop();
					currentState = States.END;
				}
    		} else {
				if (m_timer.get() < aConstants.RetractorUpTime) {
					m_retractor.retractorUp();
				} else {
					m_retractor.retractorStop();
					currentState = States.END;
				}    			
    		}
        break;    		
    	case END:
//    		System.out.println("Lift done");
    		done = true;
    		currentState = States.INIT;
    	break;
		}
		return done;
    }
}
	