package org.usfirst.frc.team1746.robot;

class Constants {
	//Lift
														// 1 Inch around 678	
	public static final double liftEncoderPosition0 = -6666;
	public static final double liftEncoderPosition1 = 19666;//29 Inches
	public static final double liftEncoderPosition2 = 40666;//5 feet
	public static final double liftEncoderPosition3 = 50000;//6 feet
	public static final double liftEncoderPosition4 = 56999;//7 feet
	public static final double liftBumpUp = 666;
	public static final double liftBumpDown = 666; 
	public static final double liftEncoderTolerance = 333;
	public static final double climbingBarHeight = 81;           //In Inches
	//Retractor
	public static final double retZeroDeg = 0; // In 5 Volts Out Of 1023  Units 
	public static final double retFourtyFiveDeg = 127.88;
	public static final double retNinetyDeg = 255.75; 
	public static final double retCrashDeg = 200;
	public static final int retSpeed = 20;
	
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/*
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */ 
	public static final int kTimeoutMs = 10;
}
