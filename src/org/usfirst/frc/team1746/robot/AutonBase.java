package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonConstants;
import org.usfirst.frc.team1746.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;


public class AutonBase {
	
	private DriveTrain m_drivetrain;
	
	SendableChooser<Double> startSelector = new SendableChooser<>();
	SendableChooser<Boolean> scaleFirstSelector = new SendableChooser<>();
	SendableChooser<String> switchSelector = new SendableChooser<>();
	SendableChooser<Boolean> startDelaySelector = new SendableChooser<>();
	SendableChooser<Boolean> switchDelaySelector = new SendableChooser<>();
	SendableChooser<Boolean> scaleDelaySelector = new SendableChooser<>();
	SendableChooser<Boolean> scoreScaleSelector = new SendableChooser<>();
	SendableChooser<Boolean> scoreSwitchSelector = new SendableChooser<>(); 
	
	String gameData;
	char nearSwitchP;
	char scaleP;
	char farSwitchP;
	
	
	public AutonBase(DriveTrain driveTrain){
		m_drivetrain = driveTrain;
		
	}
	
	AutonConstants aConstants = new AutonConstants();
	
	public void init() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		nearSwitchP = gameData.charAt(0);
		scaleP = gameData.charAt(1);
		farSwitchP = gameData.charAt(2);
	}
	
	public void initSmartDashboard() {
		initStartSelector();
		initSwitchSelector();
		initScaleFirstSelector();
		initStartDelaySelector();
		initSwitchDelaySelector();
		initScaleDelaySelector();
		initScoreScaleSelector();
		initScoreSwitchSelector();
		SmartDashboard.putBoolean("Reset Auton", false);
		
	}
	public void updateSmartDashboard(){
		SmartDashboard.putBoolean("Scale First", selectedScaleFirst());
		SmartDashboard.putString("Selected Switch", selectedSwitch());
		SmartDashboard.putNumber("Selected Start", selectedStart());
		SmartDashboard.putBoolean("Start Delay", selectedStartDelay());
		SmartDashboard.putBoolean("Switch Delay", selectedSwitchDelay());
		SmartDashboard.putBoolean("Scale Delay", selectedScaleDelay());
		SmartDashboard.putString("Near Switch Position", nSwitchPosition());
		SmartDashboard.putString("Scale Position", scalePosition());
		SmartDashboard.putString("Far Switch Position", fSwitchPosition());
		SmartDashboard.putBoolean("Score Scale At End", selectedScoreScale());
		SmartDashboard.putBoolean("Score Switch At End", selectedScoreSwitch());
		
		if(SmartDashboard.getBoolean("Reset Auton", false)){
			resetAll();
			SmartDashboard.putBoolean("Reset Auton", false);
		}
		
	}
	
	public boolean selectedScaleFirst(){
		return scaleFirstSelector.getSelected();
	}
	public String selectedSwitch(){
		return switchSelector.getSelected();
	}
	public double selectedStart(){
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
	public boolean selectedScoreScale(){
		return scoreScaleSelector.getSelected();
	}
	public boolean selectedScoreSwitch(){
		return scoreSwitchSelector.getSelected();
	}
	
	public String fSwitchPosition() {
		String fSwitchPosition = null;
		if(farSwitchP == 'L') {
			fSwitchPosition = "Left";
		}else if(farSwitchP == 'R'){
			fSwitchPosition = "Right";	
		}
		return fSwitchPosition;
	}
	public String scalePosition() {
		String scalePosition = null;
		if(scaleP == 'L') {
			scalePosition = "Left";
		}else if(scaleP == 'R'){
			scalePosition = "Right";	
		}
		return scalePosition;
	}
	public String nSwitchPosition() {
		String nSwitchPosition = null;
		if(nearSwitchP == 'L') {
			nSwitchPosition = "Left";
		}else if(nearSwitchP == 'R'){
			nSwitchPosition = "Right";	
		}
		return nSwitchPosition;
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
		startSelector.addDefault("Left", (double)1);
		startSelector.addObject("Middle Left", (double)2);
		startSelector.addObject("Middle Right", (double)3);
		startSelector.addObject("Right", (double)4);
		SmartDashboard.putData("Start Selector", startSelector);
	}
	
	public void initStartDelaySelector(){
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
	public void initScoreScaleSelector(){
		scaleDelaySelector.addDefault("No", false);
		scaleDelaySelector.addObject("Yes", true);
		SmartDashboard.putData("Score Scale At End", scoreScaleSelector);
	}
	public void initScoreSwitchSelector(){
		scaleDelaySelector.addDefault("No", false);
		scaleDelaySelector.addObject("Yes", true);
		SmartDashboard.putData("Score Switch At End", scoreSwitchSelector);
	}
	public void resetAll(){
		m_drivetrain.resetEncoders();
		m_drivetrain.resetGyro();
	}
	
	public void run(){
		
	}	
	
	
}
