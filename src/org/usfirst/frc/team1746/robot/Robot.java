/**
 * Example demonstrating the velocity closed-loop servo.
 * Tested with Logitech F350 USB Gamepad inserted into Driver Station]
 * 
 * Be sure to select the correct feedback sensor using configSelectedFeedbackSensor() below.
 *
 * After deploying/debugging this to your RIO, first use the left Y-stick 
 * to throttle the Talon manually.  This will confirm your hardware setup.
 * Be sure to confirm that when the Talon is driving forward (green) the 
 * position sensor is moving in a positive direction.  If this is not the cause
 * flip the boolean input to the setSensorPhase() call below.
 *
 * Once you've ensured your feedback device is in-phase with the motor,
 * use the button shortcuts to servo to target velocity.  
 *
 * Tweak the PID gains accordingly.
 */
package org.usfirst.frc.team1746.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.*;

public class Robot extends IterativeRobot {
	Joystick _joy = new Joystick(0);
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
	private WPI_TalonSRX m_LeftMaster;
	private WPI_TalonSRX m_LeftFollowerA;
	private WPI_TalonSRX m_LeftFollowerB;
	private WPI_TalonSRX m_RightMaster;
	private WPI_TalonSRX m_RightFollowerA;
	private WPI_TalonSRX m_RightFollowerB;
	ElectricalConstants eConstants;
	

	public void robotInit() {
		eConstants = new ElectricalConstants();
		m_LeftMaster = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_MASTER);
		m_LeftFollowerA = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_FOLLOWER_A);
		m_LeftFollowerB = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_LEFT_FOLLOWER_B);
		m_RightMaster = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_MASTER);
		m_RightFollowerA = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_FOLLOWER_A);
		m_RightFollowerB = new WPI_TalonSRX(eConstants.MOTOR_DRIVE_RIGHT_FOLLOWER_B);
		
		m_LeftFollowerA.follow(m_LeftMaster);
		m_LeftFollowerB.follow(m_LeftMaster);
		m_RightFollowerA.follow(m_RightMaster);
		m_RightFollowerB.follow(m_RightMaster);
		
		/* first choose the sensor */
		m_LeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.kTimeoutMs);
		m_LeftMaster.setSensorPhase(true);

		/* set the peak, nominal outputs */
		m_LeftMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
		m_LeftMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
		m_LeftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_LeftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* set closed loop gains in slot0 */
		m_LeftMaster.config_kF(Constants.kPIDLoopIdx, 0.1097, Constants.kTimeoutMs);
		m_LeftMaster.config_kP(Constants.kPIDLoopIdx, 0.113333, Constants.kTimeoutMs);
		m_LeftMaster.config_kI(Constants.kPIDLoopIdx, 0, Constants.kTimeoutMs);
		m_LeftMaster.config_kD(Constants.kPIDLoopIdx, 0, Constants.kTimeoutMs);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		/* get gamepad axis */
		double leftYstick = _joy.getY();
		double motorOutput = m_LeftMaster.getMotorOutputPercent();
		/* prepare line to print */
		_sb.append("\tout:");
		_sb.append(motorOutput);
		_sb.append("\tspd:");
		_sb.append(m_LeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
		_sb.append("\tpos:");
		_sb.append(m_LeftMaster.getSensorCollection().getQuadraturePosition());

		if (_joy.getRawButton(1)) {
			/* Speed mode */
			/* Convert 500 RPM to units / 100ms.
			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * 1440 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			double targetVelocity_UnitsPer100ms = leftYstick * 500.0 * 1440 / 600;
			/* 500 RPM in either direction */
			m_LeftMaster.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

			/* append more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(m_LeftMaster.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetVelocity_UnitsPer100ms);
		} else {
			/* Percent voltage mode */
			m_LeftMaster.set(ControlMode.PercentOutput, leftYstick);
		}

		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}
		_sb.setLength(0);
	}
}