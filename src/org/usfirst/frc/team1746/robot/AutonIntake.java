package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonIntake {
	
	AutonConstants aConstants;
	private States currentState;
	private Intake m_Intake;
	private boolean done;
    Timer m_timer;
    private double finishTime;
	
	
	public enum States {
		INIT,
		MOVE,
		STOP,
		END,
		
	}
	
    public AutonIntake(Intake intake) {
    	aConstants = new AutonConstants();
    	m_Intake = intake;
    	currentState = States.INIT;
    	m_timer = new Timer();
    }
    public boolean auton(String command) {
    	done=false;
    	switch(currentState) {
    	case INIT:
    		System.out.println("Init Intake");
			m_timer.reset();
			m_timer.start();
    		currentState = States.MOVE;
    	break;
    	case MOVE:
    		System.out.println("Intake Moving");
    		if (command.equals("I")) {
    			m_Intake.intakeIn();
    			finishTime = aConstants.IntakeInTime;
    			System.out.println("Intake In");
    		} else {
    			m_Intake.intakeOut();
    			finishTime = aConstants.IntakeOutTime;
    			System.out.println("Intake Out");
    		}
    		if (m_timer.get() > finishTime) {
        		currentState = States.END;
    		}
        break;    		
    	case END:
    		System.out.println("Intake Stopping");
    		m_Intake.intakeStop();
    		done = true;
    		currentState = States.INIT;
    	break;
		}
		return done;
    }
}
			