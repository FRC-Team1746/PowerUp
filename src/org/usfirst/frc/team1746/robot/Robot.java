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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Controls m_controls;
    DriveTrain m_driveTrain;
    AutonAhead m_autonAhead;
    AutonBackward m_autonBackward;
    AutonRight m_autonRight;
    AutonLeft m_autonLeft;
    StringBuilder m_commandsToDoDuringAutonomous = new StringBuilder();
    Pattern m_pattern;
    Matcher m_matcher;
    
    boolean driverCommandComplete = true;
	boolean elevatorCommandComplete = true;
	boolean grabberCommandComplete = true;
	boolean specialCommandComplete = true;
	boolean allCommandsLoaded = false;
	
	String currentDriverCommand="!";
	String currentDriverCommandArgs="";
	String currentElevatorCommand="!";
	String currentElevatorCommandArgs="";
	String currentGrabberCommand="!";
	String currentGrabberCommandArgs="";
	String currentSpecialCommand="!";
	String currentSpecialCommandArgs="";
	
    
	@Override
	public void robotInit() {
		m_controls = new Controls();
	 	m_driveTrain = new DriveTrain(m_controls);
	 	m_driveTrain.resetGyro();

	 	m_autonAhead = new AutonAhead(m_driveTrain);
	    m_autonBackward = new AutonBackward(m_driveTrain);
	    m_autonRight = new AutonRight(m_driveTrain);
	    m_autonLeft = new AutonLeft(m_driveTrain);
	 	
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		System.out.println("Inside Autonomous INIT");
		AutonConstants autonConstants = new AutonConstants();
		m_pattern = Pattern.compile("([A-Z])([^A-Z]*)");
		int[] commandsToDo = { 1, 4 };
		int from=-1;
		for(int to: commandsToDo) {
			if (from>0) {
				m_commandsToDoDuringAutonomous.append(autonConstants.commands[from-1][to-4]);
			}
			from=to;
		}
		m_matcher = m_pattern.matcher(m_commandsToDoDuringAutonomous);
		m_matcher.find();  // Load of the first command  --  "Prime the pump"
		System.out.println("Commands: #"+m_commandsToDoDuringAutonomous+"#   group 1: #"+m_matcher.group(1)+"#   group 2: #"+m_matcher.group(2)+"#");
		
	}
	
	@Override
	public void autonomousPeriodic() {
		System.out.println("Driver command complete: " + driverCommandComplete + "   All Commands Loaded: " + allCommandsLoaded);
		/*
		 * Initialize the currently loaded command if there is no command of that type (driver, elevator, grabber, special) already executing
		 */
        if (!allCommandsLoaded && driverCommandComplete && AutonConstants.driveCommands.contains(m_matcher.group(1))) {
        	currentDriverCommand = m_matcher.group(1);
        	currentDriverCommandArgs = m_matcher.group(2);
        	driverCommandComplete = false;
        	System.out.println("command: #"+currentDriverCommand+"#"+currentDriverCommandArgs);
        	if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        }
        if (!allCommandsLoaded && elevatorCommandComplete && AutonConstants.elevatorCommands.contains(m_matcher.group(1))) {
        	currentElevatorCommand = m_matcher.group(1);
        	currentElevatorCommandArgs = m_matcher.group(2);
        	elevatorCommandComplete = false;
        	if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        }
        if (!allCommandsLoaded && grabberCommandComplete && AutonConstants.grabberCommands.contains(m_matcher.group(1))) {
        	currentGrabberCommand = m_matcher.group(1);
        	currentGrabberCommandArgs = m_matcher.group(2);
        	grabberCommandComplete = false;
        	if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        }
        if (!allCommandsLoaded && specialCommandComplete && AutonConstants.specialCommands.contains(m_matcher.group(1))) {
        	currentSpecialCommand = m_matcher.group(1);
        	currentSpecialCommandArgs = m_matcher.group(2);
        	specialCommandComplete = false;
        	if (!currentSpecialCommand.equals("W")) {
        		if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
        	}
        }
        /*
         * Continue Processing the Commands that are in Progress
         */
		if (!driverCommandComplete && currentDriverCommand.equals("A")) {
			System.out.println("Run Ahead Command");
			driverCommandComplete = m_autonAhead.auton(currentDriverCommandArgs);
			System.out.println(driverCommandComplete);
		} else if (!driverCommandComplete && currentDriverCommand.equals("B")) {
			driverCommandComplete = m_autonBackward.auton(currentDriverCommandArgs);
		} else if (!driverCommandComplete && currentDriverCommand.equals("R")) {
			System.out.println("Turn Right Command");
			driverCommandComplete = m_autonRight.auton(currentDriverCommandArgs);
		} else if (!driverCommandComplete && currentDriverCommand.equals("L")) {
			System.out.println("Turn Left Command");
			driverCommandComplete = m_autonLeft.auton(currentDriverCommandArgs);
		} 
//		if (!elevatorCommandComplete && currentElevatorCommand.equals("U")) {
//			elevatorCommandComplete = m_autonUp.auton(currentElevatorCommandArgs);
//		} else if ()
		
		if (!specialCommandComplete && currentSpecialCommand.equals("W")) {
			if (driverCommandComplete && elevatorCommandComplete && grabberCommandComplete) {
				specialCommandComplete = true;
				if (!m_matcher.find()) allCommandsLoaded = true;   // get the next Command
			}
			
		}
			
		// <--- other commands go here
		
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override 
	public void teleopInit() {
		m_driveTrain.resetEncoders();
		m_driveTrain.setRampRate(0);
		m_driveTrain.setBrakeMode(false);
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_driveTrain.teleopArcadeDrive();
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void updateSmartDashboard() {
		m_driveTrain.updateSmartDashboard();
		
	}
}