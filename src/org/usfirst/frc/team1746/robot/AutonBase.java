package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonConstants;
import org.usfirst.frc.team1746.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;


public class AutonBase {
	
	private DriveTrain m_drivetrain;
	
	SendableChooser<Double> startSelector = new SendableChooser<>(); //Send And Use
	SendableChooser<Boolean> scaleFirstSelector = new SendableChooser<>(); // ?
	SendableChooser<String> switchSelector = new SendableChooser<>(); //Use
	SendableChooser<Boolean> startDelaySelector = new SendableChooser<>(); //Send
	SendableChooser<Boolean> switchDelaySelector = new SendableChooser<>(); //Send
	SendableChooser<Boolean> scaleDelaySelector = new SendableChooser<>(); //Send
	SendableChooser<Boolean> scoreScaleSelector = new SendableChooser<>(); //Send
	SendableChooser<Boolean> scoreSwitchSelector = new SendableChooser<>(); //Send
	
	String gameData;
	char ourSwitchP;
	char scaleP;
	char oppSwitchP;
	
	public AutonBase(DriveTrain driveTrain){
		m_drivetrain = driveTrain;
		
	}
	
	AutonConstants aConstants = new AutonConstants();
	
	AutonCrossTheLine crtl;
	AutonDS2ScaleFar dtcf;
	AutonDS2ScaleNear dtcn;
	AutonDS2SwichFar dtwf;
	AutonDS2SwichNear dtwn;
	AutonNone none;
	AutonScale2SwitchFar ctwf;
	AutonScale2SwitchNear ctwn;
	AutonSwitch2ScaleFar wtcf;
	AutonSwitch2ScaleNear wtcn;
	
	public void init() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		ourSwitchP = gameData.charAt(0);
		scaleP = gameData.charAt(1);
		oppSwitchP = gameData.charAt(2);
		
		crtl = new AutonCrossTheLine(m_drivetrain);
//		dtcf = new AutonDS2ScaleFar(m_drivetrain,selectedStart());
//		dtcn = new AutonDS2ScaleNear(m_drivetrain,selectedStart());
//		dtwf = new AutonDS2SwichFar(m_drivetrain,selectedStart());
//		dtwn = new AutonDS2SwichNear(m_drivetrain,selectedStart());
		none = new AutonNone();
//		ctwf = new AutonScale2SwitchFar(m_drivetrain,selectedStart());
//		ctwn = new AutonScale2SwitchNear(m_drivetrain,selectedStart());
//		wtcf = new AutonSwitch2ScaleFar(m_drivetrain,selectedStart());
//		wtcn = new AutonSwitch2ScaleNear(m_drivetrain,selectedStart());
		
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
		SmartDashboard.putString("Our Switch Position", ourSwitchPosition());
		SmartDashboard.putString("Scale Position", scalePosition());
		SmartDashboard.putString("Opponent Switch Position", oppSwitchPosition());
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
	
	public String oppSwitchPosition() {
		String oppSwitchPosition = null;
		if(oppSwitchP == 'L') {
			oppSwitchPosition = "Left";
		}else if(oppSwitchP == 'R'){
			oppSwitchPosition = "Right";	
		}
		return oppSwitchPosition;
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
	public String ourSwitchPosition() {
		String ourSwitchPosition = null;
		if(ourSwitchP == 'L') {
			ourSwitchPosition = "Left";
		}else if(ourSwitchP == 'R'){
			ourSwitchPosition = "Right";	
		}
		return ourSwitchPosition;
	}
	
	public void initScaleFirstSelector(){
		scaleFirstSelector.addDefault("No", false);
		scaleFirstSelector.addObject("Yes", true);
		SmartDashboard.putData("Scale First", scaleFirstSelector);
	}
	public void initSwitchSelector(){
		switchSelector.addDefault("None", "none");
		switchSelector.addObject("Switch", "switch");
		switchSelector.addObject("cross", "cross");
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
		if(selectedSwitch() == "scale") {
			if(selectedStart() >= 2) { //If Mid Left Or Left
				if(scalePosition() == "Left") {
//					dtcn.auton();
				} else if (scalePosition() == "Right") {
//					dtcf.auton();
				}
			}else if(selectedStart() <= 3) { // If Mid Right Or Right
				if(scalePosition() == "Right") {
//					dtcn.auton();
				} else if (scalePosition() == "Left") {
//					dtcf.auton();
				}
			}
		}else if(selectedSwitch() == "switch") {
			if(selectedStart() >= 2) { //If Mid Left Or Left
				if(ourSwitchPosition() == "Left") {
//					dtwn.auton();
				} else if (ourSwitchPosition() == "Right") {
//					dtwf.auton();
				}
			}else if(selectedStart() <= 3) { // If Mid Right Or Right
				if(ourSwitchPosition() == "Right") {
//					dtwn.auton();
				} else if (ourSwitchPosition() == "Left") {
//					dtwf.auton();
				}
			}
		}else if(selectedSwitch() == "both") {
			if(selectedScaleFirst()) {
//				Scale First
			}else {
//				Switch First
			}
		}else if(selectedSwitch() == "cross") {
			crtl.auton();
		}else{
//			none.auton();
		}
	}		
}
