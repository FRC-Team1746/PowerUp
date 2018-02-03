package org.usfirst.frc.team1746.robot;

import org.usfirst.frc.team1746.robot.AutonConstants;
import org.usfirst.frc.team1746.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;


public class AutonBase {
	
	private DriveTrain m_drivetrain;
	
	SendableChooser<Double> startSelector = new SendableChooser<>(); //Send And Use
	SendableChooser<Boolean> scaleFirstSelector = new SendableChooser<>(); // Use
	SendableChooser<String> switchSelector = new SendableChooser<>(); //Use
	SendableChooser<String> scaleSelector = new SendableChooser<>(); //Use
	SendableChooser<Boolean> startDelaySelector = new SendableChooser<>(); //Use
	SendableChooser<Boolean> switchDelaySelector = new SendableChooser<>(); //Use
	SendableChooser<Boolean> scaleDelaySelector = new SendableChooser<>(); //Use
	SendableChooser<Boolean> scoreScaleSelector = new SendableChooser<>(); //Send
	SendableChooser<Boolean> scoreSwitchSelector = new SendableChooser<>(); //Send
	SendableChooser<String> owoSelector = new SendableChooser<>(); //Our Switch Position Override
	SendableChooser<String> opoSelector = new SendableChooser<>(); //Opponent Switch Position Override
	SendableChooser<String> ocoSelector = new SendableChooser<>(); //Scale Position Override
	
	String gameData;
	char ourSwitchP;
	char scaleP;
	char oppSwitchP;
	
	public String AUTONSTATUS;
	
	private States currentStates;
	String choseAuton;
	public enum States {
		INIT,
		CHECKORDER,
		STARTDELAY,
		RUNCROSS,
		RUN2SWITCH,
		NEXTSWITCHDELAY,
		RUN2SCALE,
		NEXTSCALEDELAY,
		RUNFSWITCH,
		RUNFSCALE,
		STOP,
	}
	
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
		dtwf = new AutonDS2SwichFar(m_drivetrain,selectedStart());
//		dtwn = new AutonDS2SwichNear(m_drivetrain,selectedStart());
		none = new AutonNone();
//		ctwf = new AutonScale2SwitchFar(m_drivetrain,selectedStart());
//		ctwn = new AutonScale2SwitchNear(m_drivetrain,selectedStart());
//		wtcf = new AutonSwitch2ScaleFar(m_drivetrain,selectedStart());
//		wtcn = new AutonSwitch2ScaleNear(m_drivetrain,selectedStart());
		
		currentStates = States.INIT;
		initSmartDashboard();
	}
	
	public void initSmartDashboard() {
		initStartSelector();
		initSwitchSelector();
		initScaleSelector();
		initScaleFirstSelector();
		initStartDelaySelector();
		initSwitchDelaySelector();
		initScaleDelaySelector();
		initScoreScaleSelector();
		initScoreSwitchSelector();
		SmartDashboard.putBoolean("Reset Auton", false);
		
		initOWOSelector();
		initOCOSelector();
		initOPOSelector();
	}
	public void updateSmartDashboard(){
		SmartDashboard.putBoolean("Scale First", selectedScaleFirst());
		SmartDashboard.putString("Selected Switch", selectedSwitch());
		SmartDashboard.putString("Selected Scale", selectedSwitch());
		SmartDashboard.putNumber("Selected Start", selectedStart());
		SmartDashboard.putBoolean("Start Delay", selectedStartDelay());
		SmartDashboard.putBoolean("Switch Delay", selectedSwitchDelay());
		SmartDashboard.putBoolean("Scale Delay", selectedScaleDelay());
		SmartDashboard.putString("Our Switch Position", ourSwitchPosition());
		SmartDashboard.putString("Scale Position", scalePosition());
		SmartDashboard.putString("Opponent Switch Position", oppSwitchPosition());
		SmartDashboard.putBoolean("Score Scale At End", selectedScoreScale());
		SmartDashboard.putBoolean("Score Switch At End", selectedScoreSwitch());
		
		SmartDashboard.putString("Overide Our Switch Positions", selectedOWO());
		SmartDashboard.putString("Overide Opp Switch Positions", selectedOPO());
		SmartDashboard.putString("Overide Scale Positions", selectedOCO());
		
		if(SmartDashboard.getBoolean("Reset Auton", false)){
			resetAll();
			SmartDashboard.putBoolean("Reset Auton", false);
		}
		
		SmartDashboard.putString("SelectorState", getState());
		SmartDashboard.putString("Selected Auton", getAuton());
		SmartDashboard.putString("Auton Status", AUTONSTATUS);
	} 
	
	public String getState(){
		return currentStates.name();
	}
	
	public boolean selectedScaleFirst(){
		return scaleFirstSelector.getSelected();
	}
	public String selectedSwitch(){
		return switchSelector.getSelected();
	}
	public String selectedScale(){
		return scaleSelector.getSelected();
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
	
	public String selectedOWO() {
		return owoSelector.getSelected();
	}
	public String selectedOCO() {
		return ocoSelector.getSelected();
	}
	public String selectedOPO() {
		return opoSelector.getSelected();
	}
	
	public String oppSwitchPosition() {
		String oppSwitchPosition = null;
		if(selectedOPO() == "left") {
			oppSwitchPosition = "Left";
		}else if(selectedOPO() == "right"){
			oppSwitchPosition = "Right";
		}else {
			if(oppSwitchP == 'L') {
				oppSwitchPosition = "Left";
			}else if(oppSwitchP == 'R'){
				oppSwitchPosition = "Right";	
			}
		}
		return oppSwitchPosition;
	}
	public String scalePosition() {
		String scalePosition = null;
		if(selectedOCO() == "left") {
			scalePosition = "Left";
		}else if(selectedOCO() == "right"){
			scalePosition = "Right";
		}else {
			if(scaleP == 'L') {		
				scalePosition = "Left";
			}else if(scaleP == 'R'){
				scalePosition = "Right";	
			}
		}
		return scalePosition;
	}
	public String ourSwitchPosition() {
		String ourSwitchPosition = null;
		if(selectedOWO() == "left") {
			ourSwitchPosition = "Left";
		}else if(selectedOWO() == "right"){
			ourSwitchPosition = "Right";
		}else {
			if(ourSwitchP == 'L') {
				ourSwitchPosition = "Left";
			}else if(ourSwitchP == 'R'){
				ourSwitchPosition = "Right";	
			}
		}
		return ourSwitchPosition;
	}
	public String getAuton() {
		return choseAuton;
	}
	
	public void initScaleFirstSelector(){
		scaleFirstSelector.addDefault("No", false);
		scaleFirstSelector.addObject("Yes", true);
		SmartDashboard.putData("Scale First", scaleFirstSelector);
	}
	public void initSwitchSelector(){
		switchSelector.addDefault("None", "none");
		switchSelector.addObject("Near", "near");
		switchSelector.addObject("Both", "both");
		switchSelector.addObject("Far", "far");
		SmartDashboard.putData("Switch Selector", switchSelector);
	}
	public void initScaleSelector(){
		scaleSelector.addDefault("None", "none");
		scaleSelector.addObject("Near", "near");
		scaleSelector.addObject("Both", "both");
		scaleSelector.addObject("Far", "far");
		SmartDashboard.putData("Scale Selector", scaleSelector);
	}
	public void initStartSelector(){
		startSelector.addDefault("Left", (double)1);
		startSelector.addObject("Middle", (double)2);
		startSelector.addObject("Right", (double)3);
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
	public void initOWOSelector() {
		owoSelector.addDefault("None", "none");
		owoSelector.addObject("Left", "left");
		owoSelector.addObject("Right", "right");
	}
	public void initOPOSelector() {
		opoSelector.addDefault("None", "none");
		opoSelector.addObject("Left", "left");
		opoSelector.addObject("Right", "right");
	}
	public void initOCOSelector() {
		ocoSelector.addDefault("None", "none");
		ocoSelector.addObject("Left", "left");
		ocoSelector.addObject("Right", "right");
	}
	public void resetAll(){
		m_drivetrain.resetEncoders();
		m_drivetrain.resetGyro();
	}
	
	public void run(){
		updateSmartDashboard();
		
		switch(currentStates){
		case INIT:
			currentStates = States.STARTDELAY;
			AUTONSTATUS = "INIT";
		break;
		case STARTDELAY:
			AUTONSTATUS = "DELAY";
			currentStates = States.CHECKORDER;
		break;
		case CHECKORDER:
			if(selectedScaleFirst()) {
				currentStates = States.RUN2SCALE;
			}else if(!selectedScaleFirst()) {
				currentStates = States.RUN2SWITCH;
			}
			AUTONSTATUS = "CHECK";
		break;
		case RUN2SCALE:
			AUTONSTATUS = "RUNNING";
			if(selectedStart() == 1) {
				if(scalePosition() == "Left") {
//					dtcn.auton(); //Scale Near
					choseAuton = "DS2ScaleNear";
				} else if (scalePosition() == "Right") {
//					dtcf.auton(); //Scale Far
					choseAuton = "DS2ScaleFar";
				}
			}else if(selectedStart() == 3) {
				if(scalePosition() == "Right") {
//					dtcn.auton();
					choseAuton = "DS2ScaleNear";
				} else if (scalePosition() == "Left") {
//					dtcf.auton();
					choseAuton = "DS2ScaleFar";
				}
			}
			if(AUTONSTATUS == "NEXT") {
				currentStates = States.NEXTSCALEDELAY;
			}
		break;
		case NEXTSCALEDELAY:
			AUTONSTATUS = "DELAY";			
			currentStates = States.RUNFSCALE;
		break;
		case RUNFSCALE:
			AUTONSTATUS = "RUNNING";
			if(selectedSwitch() != "None") {
				if(scalePosition() == "Left") {
					if(ourSwitchPosition() == "Left") {
//						ctwn.auton(); //Switch Near
						choseAuton = "Scale2SwitchNear";
					} else if (ourSwitchPosition() == "Right") {
//						wtcf.auton(); //Switch Far
						choseAuton = "Scale2SwitchFar";
					}
				}else if(scalePosition() == "Right") {
					if(ourSwitchPosition() == "Right") {
//						ctwn.auton(); //Switch Near
						choseAuton = "Scale2SwitchNear";
					} else if (ourSwitchPosition() == "Left") {
//						wtcf.auton(); //Switch Far
						choseAuton = "Scale2SwitchFar";
					}
				}
			}else {
				currentStates = States.STOP;
			}
			if(AUTONSTATUS == "NEXT") {
				currentStates = States.STOP;
			}
		break;
		case RUN2SWITCH:
			if(selectedStart() == 1) {
				if(ourSwitchPosition() == "Left") {
//					dtwn.auton(); //Switch Near
					choseAuton = "DS2SwitchNear";
				} else if (ourSwitchPosition() == "Right") {
					dtwf.auton(); //Switch Far
					choseAuton = "DS2SwitchFar";
				}
			} else if (selectedStart() == 3) {
				if(ourSwitchPosition() == "Right") {
//					dtwn.auton();
					choseAuton = "DS2SwitchNear";
				} else if (ourSwitchPosition() == "Left") {
					dtwf.auton();
					choseAuton = "DS2SwitchFar";
				}
			}
			if(AUTONSTATUS == "NEXT") {
				currentStates = States.NEXTSWITCHDELAY;
			}
		break;
		case NEXTSWITCHDELAY:
			
		break;
		case RUNFSWITCH:
			AUTONSTATUS = "RUNNING";
			if(selectedScale() != "None") {
				if(ourSwitchPosition() == "Left") {
					if(scalePosition() == "Left") {
//						wtcn.auton(); //Scale Near
						choseAuton = "Switch2ScaleNear";
					} else if (scalePosition() == "Right") {
//						wtcf.auton(); //Scale Far
						choseAuton = "Switch2ScaleFar";
					}
				}else if(ourSwitchPosition() == "Right") {
					if(scalePosition() == "Right") {
//						wtcn.auton();
						choseAuton = "Switch2ScaleNear";
					} else if (scalePosition() == "Left") {
//						wtcf.auton();
						choseAuton = "Switch2ScaleFar";
					}
				}
			}else {
				currentStates = States.STOP;
			}
			if(AUTONSTATUS == "NEXT") {
				currentStates = States.STOP;
			}
		break;
		case RUNCROSS:
			
		break;
		case STOP:
		break;
		}		
	}	
	
}
