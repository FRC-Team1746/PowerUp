/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1746.robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Preferences prefs;
	
	boolean m_liveMatch;
	String m_simulatedGameData;
	String m_switchLeftScaleLeft;
	String m_switchLeftScaleRight;
	String m_switchRightScaleLeft;
	String m_switchRightScaleRight;
	String chosenPath;
	String gameData;
	
    Controls m_controls;
    DriveTrain m_driveTrain;
    AutonLiftMove m_autonLiftMove;
    AutonRetractor m_autonRetractor;
    AutonConstants autonConstants;
    Lift m_lift;
    AutonIntake m_autonIntake;
    Intake m_intake;
    Retractor m_retractor;
    AutonGo m_autonGo;
    AutonTurn m_autonTurn;
    StringBuilder m_commandsToDoDuringAutonomous = new StringBuilder();
    Pattern m_pattern;
    Matcher m_matcher;
    Timer m_timer;
    
    double waitTime;
    
    boolean driverCommandComplete = true;
	boolean elevatorGrabberCommandComplete = true;
	boolean specialCommandComplete = true;
	boolean allCommandsLoaded = false;
	boolean teleopDriveTrainCreated = false;
	
	String currentDriverCommand="!";
	String currentDriverCommandArgs="";
	String currentElevatorGrabberCommand="!";
	String currentElevatorGrabberCommandArgs="";
	String currentSpecialCommand="!";
	String currentSpecialCommandArgs="";
	
    
	@Override
	public void robotInit() {
		prefs = Preferences.getInstance();
		
		m_liveMatch = prefs.getBoolean("This a Live Match", true);
		m_simulatedGameData = prefs.getString("Simulated Game Data", "LL");
		m_switchLeftScaleLeft = "";
		m_switchLeftScaleRight = "";
		m_switchRightScaleLeft = "";
		m_switchRightScaleRight = "";
		
		m_controls = new Controls();
		m_lift = new Lift(m_controls);
		m_retractor = new Retractor(m_controls);
	 	m_driveTrain = new DriveTrain(m_controls);
	 	m_autonLiftMove = new AutonLiftMove(m_lift);
	 	m_autonRetractor = new AutonRetractor(m_retractor);
	 	autonConstants = new AutonConstants();
	 	m_intake =  new Intake(m_controls);
	 	m_autonIntake =  new AutonIntake(m_intake);
	 	m_driveTrain.initAuto();
	 	m_driveTrain.resetGyro();
	 	m_autonGo = new AutonGo(m_driveTrain);
	    m_autonTurn = new AutonTurn(m_driveTrain);
	    m_timer = new Timer();
	 	
	    m_driveTrain.initTele();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_driveTrain.resetGyro();
	    driverCommandComplete = true;
		elevatorGrabberCommandComplete = true;
		specialCommandComplete = true;
		allCommandsLoaded = false;
		teleopDriveTrainCreated = false;
		
		currentDriverCommand="!";
		currentDriverCommandArgs="";
		currentElevatorGrabberCommand="!";
		currentElevatorGrabberCommandArgs="";
		currentSpecialCommand="!";
		currentSpecialCommandArgs="";

		prefs = Preferences.getInstance();
		
//		m_liveMatch = prefs.getBoolean("This a Live Match", false);
//		m_simulatedGameData = prefs.getString("Simulated Game Data", "LL");
//		m_switchLeftScaleLeft = prefs.getString("Switch Left, Scale Left", "1,16,10,10");
//		m_switchLeftScaleRight = prefs.getString("Switch Left, Scale Right", "1,8,10,17");
//		m_switchRightScaleLeft = prefs.getString("Switch Right, Scale Left", "1,16,11,11");
//		m_switchRightScaleRight = prefs.getString("Switch Right, Scale Right", "1,17,11,11");
		m_liveMatch = true;
		m_simulatedGameData = "RL";	
		
		//////// From Position 1 ///////
		
		m_switchLeftScaleLeft = "1,18";
		m_switchLeftScaleRight = "1,8";
		m_switchRightScaleLeft = "1,18";
		m_switchRightScaleRight = "4,4";
//		
		////////From Position 2 ///////
		
//		m_switchLeftScaleLeft = "2,6";
//		m_switchLeftScaleRight = "2,6";
//		m_switchRightScaleLeft = "2,7";
//		m_switchRightScaleRight = "2,7";

		////////////Go Straight////////
//		m_switchLeftScaleLeft = "4,4";
//		m_switchLeftScaleRight = "4,4";
//		m_switchRightScaleLeft = "4,4";
//		m_switchRightScaleRight = "4,4";
		
		////////From Position 3 ///////
		    
//		m_switchLeftScaleLeft = "3,9";
//		m_switchLeftScaleRight = "3,9";
//		m_switchRightScaleLeft = "3,19";
//		m_switchRightScaleRight = "4,4";
		
		m_pattern = Pattern.compile("([A-Z])([^A-Z]*)");
		
		if (m_liveMatch) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		} else {
			gameData = m_simulatedGameData;
		}
	    if(gameData.length() > 0) {
	       	if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
	       		chosenPath = m_switchLeftScaleLeft;
	       	} else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
	       		chosenPath = m_switchLeftScaleRight;
	       	} else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
	       		chosenPath = m_switchRightScaleLeft;
	       	} else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
		   		chosenPath = m_switchRightScaleRight;
		    } else System.out.println("Invalid Game Data Received: " + gameData);
	    } else System.out.println("No Game Data Received !!!");
		
	    String[] tmpStringArray = chosenPath.split(",");
	    int[] commandsToDo = new int[tmpStringArray.length];
	    for (int i = 0; i < tmpStringArray.length; i++) {
	        String tmpNumberAsString = tmpStringArray[i];
	        commandsToDo[i] = Integer.parseInt(tmpNumberAsString);
	    }		
		
		int from=-1;
		for(int to: commandsToDo) {
			if (from>0) {
				m_commandsToDoDuringAutonomous.append(autonConstants.commands[from-1][to-4]);
			}
			from=to;
		}
		m_matcher = m_pattern.matcher(m_commandsToDoDuringAutonomous);
		System.out.println("Commands: #"+m_commandsToDoDuringAutonomous+"#");
		if (!m_matcher.find()) allCommandsLoaded = true;		// get the next Command
	}
	
	@Override
	public void autonomousPeriodic() {
//		System.out.println("Driver command complete: " + driverCommandComplete + "   All Commands Loaded: " + allCommandsLoaded);
        if (!allCommandsLoaded && driverCommandComplete && AutonConstants.driveCommands.contains(m_matcher.group(1))) {
        	currentDriverCommand = m_matcher.group(1);
        	currentDriverCommandArgs = m_matcher.group(2);
        	driverCommandComplete = false;
        	System.out.println("command: #"+currentDriverCommand+"#"+currentDriverCommandArgs);
        	if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        }
        if (!allCommandsLoaded && elevatorGrabberCommandComplete && AutonConstants.elevatorGrabberCommands.contains(m_matcher.group(1))) {
        	currentElevatorGrabberCommand = m_matcher.group(1);
        	currentElevatorGrabberCommandArgs = m_matcher.group(2);
        	elevatorGrabberCommandComplete = false;
        	if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        }
        if (!allCommandsLoaded && specialCommandComplete && AutonConstants.specialCommands.contains(m_matcher.group(1))) {
        	currentSpecialCommand = m_matcher.group(1);
        	currentSpecialCommandArgs = m_matcher.group(2);
        	specialCommandComplete = false;
        	if (currentSpecialCommand.equals("W")) {
        		waitTime = 0;
        		if (currentSpecialCommandArgs.length()>0) waitTime=Double.parseDouble(currentSpecialCommandArgs);
        		if (waitTime > 0) {
        			m_timer.reset();
        			m_timer.start();
        		}
        	} else {
	        	if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        	}
        }
        
		if (!driverCommandComplete && currentDriverCommand.equals("A")) {
//			System.out.println("Run Ahead Command");
			driverCommandComplete = m_autonGo.auton(1,currentDriverCommandArgs);
//			System.out.println(driverCommandComplete);
				
		} else if (!driverCommandComplete && currentDriverCommand.equals("B")) {
//			System.out.println("Run Backwards Command");
			driverCommandComplete = m_autonGo.auton(-1,currentDriverCommandArgs);
		} else if (!driverCommandComplete && currentDriverCommand.equals("R")) {
//			System.out.println("Turn Right Command");
			driverCommandComplete = m_autonTurn.auton(1,currentDriverCommandArgs);
		} else if (!driverCommandComplete && currentDriverCommand.equals("L")) {
//			System.out.println("Turn Left Command");
			driverCommandComplete = m_autonTurn.auton(-1,currentDriverCommandArgs);
		} 
		if (!elevatorGrabberCommandComplete && currentElevatorGrabberCommand.equals("H")) {
			elevatorGrabberCommandComplete = m_autonLiftMove.auton(currentElevatorGrabberCommandArgs);
		}
		if (!elevatorGrabberCommandComplete && currentElevatorGrabberCommand.equals("D")) {
			elevatorGrabberCommandComplete = m_autonRetractor.auton("D");
		}
		if (!elevatorGrabberCommandComplete && currentElevatorGrabberCommand.equals("U")) {
			elevatorGrabberCommandComplete = m_autonRetractor.auton("U");
		}
		if (!elevatorGrabberCommandComplete && currentElevatorGrabberCommand.equals("I")) {
			elevatorGrabberCommandComplete = m_autonIntake.auton("I");
		}
		if (!elevatorGrabberCommandComplete && currentElevatorGrabberCommand.equals("O")) {
			elevatorGrabberCommandComplete = m_autonIntake.auton("O");
		}
		
		if (!specialCommandComplete && currentSpecialCommand.equals("W")) {
			if (driverCommandComplete && elevatorGrabberCommandComplete && (waitTime == 0 || (m_timer.get() - waitTime) > 0)) {
				specialCommandComplete = true;
				if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
			}
		}
			
		// <--- other commands go here
			
//		} else {
//			throw new UnsupportedOperationException("An invalid Command was encountered in AutonConstants.commands.");
//		}
//		updateAutonSmartDashboard();
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
	 	m_driveTrain.initTele();
		m_driveTrain.setRampRate(.5);
		m_driveTrain.setBrakeMode(false);
		m_lift.setBrakeMode(true);
		
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_lift.update();
		m_intake.update();
		m_retractor.update();
		m_driveTrain.teleopArcadeDrive();
//		m_shoot.update();
		
		updateSmartDashboard();
	}

	@Override
	public void testInit() {
		driverCommandComplete = true;
		elevatorGrabberCommandComplete = true;
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override

	public void testPeriodic() {
		if (driverCommandComplete && elevatorGrabberCommandComplete) {
			if (m_controls.driver_UP_DPAD() || m_controls.oper_UP_DPAD()) {
				currentDriverCommand = "A";
				currentDriverCommandArgs = "205";
				driverCommandComplete = false;
			}else if (m_controls.driver_DOWN_DPAD() || m_controls.oper_DOWN_DPAD()) {
				currentDriverCommand = "B";
				currentDriverCommandArgs = "50";
				driverCommandComplete = false;
			}else if (m_controls.driver_LEFT_DPAD() || m_controls.oper_LEFT_DPAD()) {
					currentDriverCommand = "L";
					currentDriverCommandArgs = "";
					driverCommandComplete = false;
		    }else if (m_controls.driver_RIGHT_DPAD() || m_controls.oper_RIGHT_DPAD()) {
					currentDriverCommand = "R";
					currentDriverCommandArgs = "";
					driverCommandComplete = false;
			}else if (m_controls.driver_Y_Button() || m_controls.oper_Y_Button()) {
				currentElevatorGrabberCommand = "H";
				currentElevatorGrabberCommandArgs = "2";
				elevatorGrabberCommandComplete = false;
			}else if (m_controls.driver_X_Button() || m_controls.oper_X_Button()) {
				currentElevatorGrabberCommand = "H";
				currentElevatorGrabberCommandArgs = "1";
				elevatorGrabberCommandComplete = false;
			}else if (m_controls.driver_A_Button() || m_controls.oper_A_Button()) {
				currentElevatorGrabberCommand = "H";
				currentElevatorGrabberCommandArgs = "0";
				elevatorGrabberCommandComplete = false;
			}else if (m_controls.driver_LB_Button() || m_controls.oper_LB_Button()) {
				currentElevatorGrabberCommand = "O";									
				elevatorGrabberCommandComplete = false;				
			}else if (m_controls.driver_RB_Button() || m_controls.oper_RB_Button()) {
				currentElevatorGrabberCommand = "I";									
				elevatorGrabberCommandComplete = false;				
			}else if (m_controls.driver_B_Button() || m_controls.oper_B_Button()) {
				currentElevatorGrabberCommand = "D";									
				elevatorGrabberCommandComplete = false;				
			}
		}
		if (!driverCommandComplete || !elevatorGrabberCommandComplete) {
			allCommandsLoaded = true;						// fake out autonomousPeriodic for testing
			autonomousPeriodic();
			allCommandsLoaded = false;						// prepare for the real Autonomous mode
		}
	}
	
	
	public void updateSmartDashboard() {
		m_driveTrain.updateSmartDashboard();
		
	}
	public void updateAutonSmartDashboard() {
//		m_driveTrain.updateSmartDashboard();
		
	}
}