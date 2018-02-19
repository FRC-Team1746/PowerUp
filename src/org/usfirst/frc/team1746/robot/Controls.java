package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {
	ElectricalConstants electricalConstants = new ElectricalConstants();
	
	double x_axisSquared;
	Joystick xbox_driver;
	Joystick xbox_oper;
	
	public Controls()
	{
		xbox_driver = new Joystick(electricalConstants.JOYSTICK_DRIVER);
		xbox_oper = new Joystick(electricalConstants.JOYSTICK_OPERATOR);
	}
		

// Driver
	// Intake
	public boolean grabCube(){
		return xbox_driver.getRawButton(5);
	}	
	// Left Axis
	public double driver_X_Axis(){
		x_axisSquared = xbox_driver.getRawAxis(0);
		x_axisSquared = x_axisSquared * x_axisSquared;
		if (xbox_driver.getRawAxis(0) < 0) {
			x_axisSquared = x_axisSquared * -1;
		}
		return x_axisSquared;
		//return xbox_driver.getRawAxis(0);
		
	}
	public double driver_Y_Axis(){
		return xbox_driver.getRawAxis(1);

	}
	public double driver_YR_Axis(){
		return xbox_driver.getRawAxis(5);

	}
	public boolean oper_Y_Button(){
		return xbox_oper.getRawButton(4);
		
	}	
	public boolean oper_X_Button(){
		return xbox_oper.getRawButton(3);
		
	}	
	public boolean oper_A_Button(){
		return xbox_oper.getRawButton(1);
		
	}
	public boolean oper_LB_Button(){
		return xbox_oper.getRawButton(5);
		
	}	
	public boolean oper_RB_Button(){
		return xbox_oper.getRawButton(6);
		
	}
	public double oper_LT_Axis(){
		return xbox_oper.getRawAxis(2);
		
	}
	public double oper_RT_Axis(){
		return xbox_oper.getRawAxis(3);
		
	}
}

	

/*********************************************************************************/	
/* 
 * Xbox Controller Layout
 * 
 * 	Buttons
 *    1   - A                   - driver intake - operator 
 *    2   - B                   - driver outake - operator
 *    3   - X                   - driver
 *    4   - Y                   - driver
 *    5   - Left Bumper         - driver flaps out
 *    6   - Right Bumper        - driver flaps in
 *    7   - Select              - driver tracking off
 *    8   - Start               - driver tracking on
 *    9   - Left Analog Button  - driver
 *    10  - Right Analog Button - driver
 *  
 *  Axis
 *    0   - Left Stick X        - driver
 *    1   - Left Stick Y        - driver
 *    2   - Left Trigger        - driver
 *    3   - Right Trigger       - driver
 *    4   - Right Stick X       - driver
 *    5   - Right Stick Y       - driver
 *  
 *  POV (DPAD)
 *    0   - DPAD Up             - driver
 *    45  - DPAD Up/Right       - driver
 *    90  - DPAD Right          - driver
 *    135 - DPAD Down/Right     - driver
 *    180 - DPAD Down           - driver
 *    225 - DPAD Down/Left      - driver
 *    270 - DPAD Left           - driver 
 *    315 - DPAD Up/Left        - driver
 *    
*/
/*********************************************************************************/

