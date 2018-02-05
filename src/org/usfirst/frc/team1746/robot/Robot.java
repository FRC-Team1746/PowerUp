/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1746.robot;

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
    AutonCrossTheLine m_autonCrossLine;

    AutonDS2SwichFar m_autonSwichFar;

//brockhampton is good music group or not

    AutonDS2ScaleFar m_autonDS2ScaleFar;
    AutonDS2SwichNear m_autonDS2SwitchNear;
    AutonBase m_autonBase;

    
	@Override
	public void robotInit() {
		m_controls = new Controls();
	 	m_driveTrain = new DriveTrain(m_controls);
	 	m_driveTrain.resetGyro();
	 	
	 	m_autonSwichFar = new AutonDS2SwichFar(m_driveTrain, 1  );
	 	m_autonCrossLine = new AutonCrossTheLine(m_driveTrain);
	 	m_autonDS2ScaleFar = new AutonDS2ScaleFar(m_driveTrain, 1);
	 	m_autonDS2SwitchNear = new AutonDS2SwichNear(m_driveTrain);
	 	m_autonBase = new AutonBase(m_driveTrain);
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_autonBase.init();
	}
	
	@Override
	public void autonomousPeriodic() {		
		m_autonBase.run();

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
		m_autonBase.updateSmartDashboard();
	}
}
