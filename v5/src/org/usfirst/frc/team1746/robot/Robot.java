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
    TeleopDriveTrain m_driveTrainT;
    Lift m_lift;
    Intake m_intake;
    Grabber m_grabber;
    Shoot m_shoot;
    
    

    
	@Override
	public void robotInit() {
		m_controls = new Controls();
	 	m_lift = new Lift(m_controls);
	 	m_intake = new Intake(m_controls);
	 	m_grabber = new Grabber(m_controls, m_lift);
	 	m_shoot = new Shoot(m_controls);
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
	
	}
	
	@Override
	public void autonomousPeriodic() {		
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
	 	m_driveTrainT = new TeleopDriveTrain(m_controls);
		m_driveTrainT.setRampRate(.5);
		m_driveTrainT.setBrakeMode(false);
		System.out.println("teleopInit");
		m_lift.setBrakeMode(true);
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_lift.update();
//		m_grabber.update();
		m_intake.update();
		m_driveTrainT.teleopArcadeDrive();
		m_shoot.update();
		
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		m_lift.updateSmartDashboard();
	}
	
	public void updateSmartDashboard() {
		m_driveTrainT.updateSmartDashboard();
		m_lift.updateSmartDashboard();
		m_grabber.updateSmartDashboard();
		//m_autonBase.updateSmartDashboard();
	}
}
