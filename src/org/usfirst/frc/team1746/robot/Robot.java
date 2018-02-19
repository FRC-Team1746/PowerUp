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
    Lift m_lift;
    Intake m_intake;
    Grabber m_grabber;
    Sensor m_sensor;

    AutonBase m_autonBase;

    
	@Override
	public void robotInit() {
		m_controls = new Controls();
	 	m_driveTrain = new DriveTrain(m_controls);
	 	m_driveTrain.resetGyro();
	 	m_lift = new Lift(m_controls);
	 	m_intake = new Intake(m_controls);
	 	m_grabber = new Grabber(m_controls, m_lift);
	 	m_sensor = new Sensor();
	 	m_autonBase = new AutonBase(m_driveTrain);
	 	
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		//m_autonBase.init();
	}
	
	@Override
	public void autonomousPeriodic() {		
		//m_autonBase.run();
		//System.out.println("Auton");
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
		m_driveTrain.resetEncoders();
		m_lift.resetEncoder();
		m_driveTrain.setRampRate(.5);
		m_driveTrain.setBrakeMode(false);
		System.out.println("teleopInit");
		m_lift.setBrakeMode(true);
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_driveTrain.teleopArcadeDrive();
		m_lift.update();
		m_grabber.update();
		m_intake.update();
		updateSmartDashboard();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		m_lift.updateSmartDashboard();
		m_sensor.update();
	}
	
	public void updateSmartDashboard() {
		m_driveTrain.updateSmartDashboard();
		m_lift.updateSmartDashboard();
		m_grabber.updateSmartDashboard();
		//m_autonBase.updateSmartDashboard();
	}
}
