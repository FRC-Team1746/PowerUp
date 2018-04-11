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
    AutonConstants autonConstants;
    Lift m_lift;
    Intake m_intake;
    Retractor m_retractor;
    StringBuilder m_commandsToDoDuringAutonomous = new StringBuilder();
    Pattern m_pattern;
    Matcher m_matcher;
    Timer m_timer;
    VisionCube m_vision;
    AutonBase m_autonBase;
    PandaAutonFarScaleLeftMeghan m_pandaAutonScaleRight;
    
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
		
		m_controls = new Controls();
		m_vision = new VisionCube();
		m_lift = new Lift(m_controls);
		m_retractor = new Retractor(m_controls);
	 	m_driveTrain = new DriveTrain(m_controls, m_vision);
	 	autonConstants = new AutonConstants();
	 	m_intake =  new Intake(m_controls);
	 	m_driveTrain.resetGyro();
	    m_timer = new Timer();
	 	m_autonBase = new AutonBase(m_driveTrain, m_lift, m_intake, m_retractor);
	    
		m_lift.setEncoderToStart();

	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_driveTrain.initHeading();
		m_lift.setEncoderToStart();
	}
	
	@Override
	public void autonomousPeriodic() {
		m_intake.update();
		m_lift.update();
		m_retractor.update();
		m_vision.trackObject();
		System.out.println(m_vision.Tracking());
		m_autonBase.auton();
		updateAutonSmartDashboard();
	}
	
	/**This function is called once each time the robot enters teleoperated mode.**/
	@Override
	public void teleopInit() {
	 	m_driveTrain.initTele();
		m_driveTrain.setRampRate(.5);
		m_driveTrain.setBrakeMode(false);
		m_lift.setBrakeMode(true);
		
	}

	/**This function is called periodically during teleoperated mode.**/
	@Override
	public void teleopPeriodic() {
		m_lift.update();
		m_intake.update();
		m_retractor.update();
		m_driveTrain.teleopArcadeDrive();
		m_vision.trackObject();
		System.out.println(m_vision.Tracking());
		m_vision.updateSmartdashboard();
		updateSmartDashboard();
	}

	@Override
	public void testInit() {
		driverCommandComplete = true;
		elevatorGrabberCommandComplete = true;
	}

	/**This function is called periodically during test mode.**/
	@Override
	public void testPeriodic() {

	}
	
	public void updateSmartDashboard() {
		m_driveTrain.updateSmartDashboard();
		m_lift.updateSmartDashboard();
		m_retractor.updateSmartDashboard();
	}
	public void updateAutonSmartDashboard() {
		m_vision.updateSmartdashboard();
		m_driveTrain.updateSmartDashboard();
		m_lift.updateSmartDashboard();
		m_retractor.updateSmartDashboard();
	}
}