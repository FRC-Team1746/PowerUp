package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonLiftMove {
	
	AutonConstants aConstants;
	private States currentState;
	private Lift m_lift;
	private boolean done;
	private int m_position;
	
	
	public enum States {
		INIT,
		MOVE,
		STOP,
		END,
		
	}
	
    public AutonLiftMove(Lift lift) {
    	aConstants = new AutonConstants();
    	m_lift = lift;
    	currentState = States.INIT;
    }
    public boolean auton(String m_args) {
    	done=false;
    	switch(currentState) {
    	case INIT:
    		if (m_args.length()!=0) {
				String[] stringArray = m_args.split(",");
	 		    double tmpDouble;
	 		    for (int j = 0; j < stringArray.length; j++) {
	 		       String numberAsString = stringArray[j];
 		    	   tmpDouble = Double.parseDouble(numberAsString);
 		    	   if (j == 0) m_position = (int) (tmpDouble);
	 		    }
	 		}
//    		System.out.println("Updating m_position to: " + m_position);
    		m_lift.updatePosition(m_position);
    		currentState = States.MOVE;
    	break;
    	case MOVE:
//    		System.out.println("Lift Updating");
    		if (m_lift.updatePosition(m_position)) currentState = States.END;
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
			