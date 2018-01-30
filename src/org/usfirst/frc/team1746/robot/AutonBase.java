package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonConstants;
import org.usfirst.frc.team1746.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Driverstation;


public class AutonBase {
	
	private DriveTrain m_drivetrain;
	
	SendableChooser<Int> startSelector = new SendableChooser<>();
	SendableChooser<Boolean> scaleFirstSelector = new SendableChooser<>();
	SendableChooser<String> switchSelector = new SendableChooser<>();
	SendableChooser<Boolean> startDelaySelector = new SendableChooser<>();
	SendableChooser<Boolean> switchDelaySelector = new SendableChooser<>();
	SendableChooser<Boolean> scaleDelaySelector = new SendableChooser<>();
	
	String gameData;
	String nearSwitch;
	String scale;
	String farSwitch;
	
	
	public AutonBase(DriveTrain driveTrain){
		m_drivetrain = driveTrain;
		
	}
	
	AutonConstants aConstants = new AutonConstants();
	
	public void init() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if (gameData.charAt(0) == "L") {
			nearSwitch = "Left";
		} else {
			nearSwitch = "Right";
		}
		if (gameData.charAt(1) == "L") {
			scale = "Left";
		} else {
			scale = "Right";
		}
		if (gameData.charAt(2) == "L") {
			farSwitch = "Left";
		} else {
			farSwitch = "Right";
		}
		
	}
	
	public void initSmartDashboard() {
		initStartSelector();
		initScaleSelector();
		initScaleFirstSelector();
		initStartDelaySelector();
		initSwitchDelaySelector();
		initScaleDelaySelector();
		SmartDashboard.putBoolean("Reset Auton", false);
		
	}
	public void updateSmartDashboard(){
		SmartDashboard.putBoolean("Scale First", selectedScaleFirst());
		SmartDashboard.putString("Selected Switch", selectedSwitch());
		SmartDashboard.putInt("Selected Start", selectedStart());
		SmartDashboard.putBoolean("Start Delay", selectedStartDelay());
		SmartDashboard.putBoolean("Switch Delay", selectedSwitchDelay());
		SmartDashboard.putBoolean("Scale Delay", selectedScaleDelay());
		
		if(SmartDashboard.getBoolean("Reset Auton", false)){
			resetAll();
			SmartDashboard.putBoolean("Reset Auton", false);
		}
		
	}
	
	public boolean selectedScaleFirst(){
		return scaleFirstSelector.getSelected();
	}
	public String selectedSwitch(){
		return scaleSelector.getSelected();
	}
	public int selectedStart(){
		return startSelector.getSelected();
	}
	public boolean selectedStartDelay(){
		return startDelaySelector.getSelected();
	}
	public boolean selectedSwitchDelay(){
		return switchDelaySelector.getSelected();
	}
	public boolean selectedScaleDelay(){
		return scaleDelaySelector.getSelected();
	}
	
	public void initScaleFirstSelector(){
		scaleFirstSelector.addDefault("No", false);
		scaleFirstSelector.addObject("Yes", true);
		SmartDashboard.putData("Scale First", scaleFirstSelector);
	}
	public void initSwitchSelector(){
		switchSelector.addDefault("Near", "near");
		switchSelector.addObject("Far", "far");
		switchSelector.addObject("Scale", "scale");
		SmartDashboard.putData("Switch Selector", switchSelector);
	}
	public void initStartSelector(){
		startSelector.addDefault("Left", 1);
		startSelector.addObject("Middle Left", 2);
		startSelector.addObject("Middle Right", 3);
		startSelector.addObject("Right", 4);
		SmartDashboard.putData("Start Selector", startSelector);
	}
	
	public void initstartDelaySelector(){
		startDelaySelector.addDefault("No", false);
		startDelaySelector.addObject("Yes", true);
		SmartDashboard.putData("Start Delay", startDelaySelector);
	}
	
	public void initSwitchDelaySelector(){
		switchDelaySelector.addDefault("No", false);
		switchDelaySelector.addObject("Yes", true);
		SmartDashboard.putData("Switch Delay", switchDelaySelector);
	}
	
	public void initScaleDelaySelector(){
		scaleDelaySelector.addDefault("No", false);
		scaleDelaySelector.addObject("Yes", true);
		SmartDashboard.putData("Scale Delay", scaleDelaySelector);
	}
	
	public void resetAll(){
		m_drivetrain.resetEncoders();
		m_drivetrain.resetGyro();
	}
	
	public void run(){
		
	}	
	
	
}
