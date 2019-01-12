package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Instrum {


	public static void Process(WPI_TalonSRX tal)
	{
		/* smart dash plots */
		SmartDashboard.putNumber("SensorVel", tal.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
		SmartDashboard.putNumber("SensorPos", tal.getSelectedSensorPosition(Constants.kPIDLoopIdx));
		SmartDashboard.putNumber("MotorOutputPercent", tal.getMotorOutputPercent());
		SmartDashboard.putNumber("ClosedLoopError", tal.getClosedLoopError(Constants.kPIDLoopIdx));
		
		
		/* print the Active Trajectory Point Motion Magic is servoing towards */
		SmartDashboard.putNumber("ClosedLoopTarget", tal.getClosedLoopTarget(Constants.kPIDLoopIdx));
    	SmartDashboard.putNumber("ActTrajVelocity", tal.getActiveTrajectoryVelocity());
    	SmartDashboard.putNumber("ActTrajPosition", tal.getActiveTrajectoryPosition());
    	SmartDashboard.putNumber("ActTrajHeading", tal.getActiveTrajectoryHeading());

	}
}
