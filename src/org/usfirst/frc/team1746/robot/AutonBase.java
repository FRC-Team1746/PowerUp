package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonBase {
	String gameData;
	int robotPosition;
	private int m_delayCounter;

	Timer delayTimer;
	
	PandaAutonCenterSwitchLeft m_pandaAutonCenterSwitchLeft;
	PandaAutonCenterSwitchRight m_pandaAutonCenterSwitchRight;
	PandaAutonScaleRight m_pandaAutonScaleRight;
	PandaAutonScaleLeft m_pandaAutonScaleLeft;
	PandaAutonSwitchRight m_pandaAutonSwitchRight;
	PandaAutonSwitchLeft m_pandaAutonSwitchLeft;
	PandaAutonStraight m_pandaAutonStraight;
	PandaAutonFarScaleRight m_pandaAutonFarScaleRight;
	PandaAutonFarScaleLeftHook m_pandaAutonFarScaleLeft;
	
	boolean scalePrioritized;
	
	SendableChooser<Double> startSelector = new SendableChooser<>();
	SendableChooser<Boolean> scalePrioritySelector = new SendableChooser<>();
	SendableChooser<String> switchSelector = new SendableChooser<>();
	SendableChooser<String> scaleSelector = new SendableChooser<>();
	
	public AutonBase(DriveTrain m_driveTrain, Lift m_lift, Intake m_intake, Retractor m_retractor) {
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		robotPosition = 3;
		scalePrioritized = true;
		delayTimer = new Timer();
		
		m_pandaAutonCenterSwitchRight = new PandaAutonCenterSwitchRight(m_driveTrain,m_lift, m_intake, m_retractor);
	    m_pandaAutonCenterSwitchLeft = new PandaAutonCenterSwitchLeft(m_driveTrain,m_lift, m_intake, m_retractor);
	    m_pandaAutonScaleRight = new PandaAutonScaleRight(m_driveTrain, m_lift, m_intake, m_retractor);
	    m_pandaAutonScaleLeft = new PandaAutonScaleLeft(m_driveTrain, m_lift, m_intake, m_retractor);
	    m_pandaAutonSwitchLeft = new PandaAutonSwitchLeft(m_driveTrain, m_lift, m_intake, m_retractor);
	    m_pandaAutonSwitchRight = new PandaAutonSwitchRight(m_driveTrain, m_lift, m_intake, m_retractor);
	    m_pandaAutonStraight = new PandaAutonStraight(m_driveTrain, m_lift, m_intake);
	    m_pandaAutonFarScaleRight = new PandaAutonFarScaleRight(m_driveTrain, m_lift, m_intake, m_retractor);   
	    m_pandaAutonFarScaleLeft = new PandaAutonFarScaleLeftHook(m_driveTrain, m_lift, m_intake, m_retractor);   
	    
	    m_pandaAutonCenterSwitchRight.init();
	    m_pandaAutonCenterSwitchLeft.init();
	    m_pandaAutonScaleRight.init();
	    m_pandaAutonScaleLeft.init();
	    m_pandaAutonSwitchLeft.init();
	    m_pandaAutonSwitchRight.init();
	    m_pandaAutonStraight.init();
	    m_pandaAutonFarScaleRight.init();
	    m_pandaAutonFarScaleLeft.init();
	    
	    initScalePrioritySelector();
	    initScaleSelector();
	    initStartSelector();
	    initSwitchSelector();
	    SmartDashboard.putNumber("Delay By", 0);
	    
	    SmartDashboard.putBoolean("Reset Auton", false);
	}
	
	

	public boolean selectedScalePriority(){
		return scalePrioritySelector.getSelected();
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
	public double selectedStartDelay(){
		return SmartDashboard.getNumber("Delay By", 0);
	}
	
	public void initScalePrioritySelector(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INITSCALEFIRSTSELECTOR");
		scalePrioritySelector.addDefault("No", false);
		scalePrioritySelector.addObject("Yes", true);
		SmartDashboard.putData("Scale First", scalePrioritySelector);
	}
	
	public void initSwitchSelector(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INITSWITCHSELECTOR");
		switchSelector.addDefault("None", "none");
		switchSelector.addObject("Near", "near");
		switchSelector.addObject("Both", "both");
		switchSelector.addObject("Far", "far");
		SmartDashboard.putData("Switch Selector", switchSelector);
	}
	public void initScaleSelector(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INITSCALESELECTOR");
		scaleSelector.addDefault("None", "none");
		scaleSelector.addObject("Near", "near");
		scaleSelector.addObject("Both", "both");
		scaleSelector.addObject("Far", "far");
		SmartDashboard.putData("Scale Selector", scaleSelector);
	}
	public void initStartSelector(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INITSTARTSELECTOR");
		startSelector.addDefault("Left", (double)1);
		startSelector.addObject("Middle", (double)2);
		startSelector.addObject("Right", (double)3);
		SmartDashboard.putData("Start Selector", startSelector);
	}
	
	public void resetAll(){
		m_pandaAutonCenterSwitchRight.init();
	    m_pandaAutonCenterSwitchLeft.init();
	    m_pandaAutonScaleRight.init();
	    m_pandaAutonScaleLeft.init();
	    m_pandaAutonSwitchLeft.init();
	    m_pandaAutonSwitchRight.init();
	    m_pandaAutonStraight.init();
	    m_pandaAutonFarScaleRight.init();
	    m_pandaAutonFarScaleLeft.init();
	    delayTimer.stop();
	    delayTimer.reset();
	}
	
	
	public void auton() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
//		robotPosition = (int) selectedStart();
		robotPosition = 3;
		scalePrioritized = true;
		
		delayTimer.start();
		
//		if (m_delayCounter ++ >= 750) {
			if(delayTimer.get() >= selectedStartDelay()) {
				if(robotPosition == 1) {	//Bot On Left DriverStation Position
					if (scalePrioritized) { //Scale Is Prioritized
						if (gameData.charAt(1) == 'L') { //Game Data Says Scale Is Left Side
							m_pandaAutonScaleLeft.auton();
						} else {// Assume Right Side Scale
	//						m_pandaAutonFarScaleRight.auton();
	//						m_pandaAutonStraight.auton();
	//						if(gameData.charAt(0) == 'L') { //Game Data Says Switch Is Left
	//							m_pandaAutonSwitchLeft.auton();
	//						}else {
								m_pandaAutonStraight.auton();//Drive Straight Or Do Nothing
	//						}
						}
					}else {//Switch Is Prioritized
						if (gameData.charAt(0) == 'L') { //Game Data Says Switch Is Left Side
							m_pandaAutonSwitchLeft.auton();
						} else {// Assume Right Side Switch
							if (gameData.charAt(1) == 'L') { //Game Data Says Scale Is Left Side
								m_pandaAutonScaleLeft.auton();
							} else {// Assume Right Side Scale
								m_pandaAutonStraight.auton();//Do Nothing Or Drive Straight
							}
						}
					}
				}else if(robotPosition == 2) { //Bot Next To Exchange
					if(gameData.charAt(0) == 'L') { //Game Data Says Switch Is Left
						m_pandaAutonCenterSwitchLeft.auton();
					}else { //Game Data Says Switch Is Right
						m_pandaAutonCenterSwitchRight.auton();
					}
				}else if(robotPosition == 3) { //Bot On Right Side DriverStation
					if (scalePrioritized) { //Scale Is Prioritized
						if (gameData.charAt(1) == 'R') { //Game Data Says Scale Is Right Side
							m_pandaAutonScaleRight.auton();
						} else {// Assume Left Side Scale
							if(gameData.charAt(0) == 'R') { //Game Data Says Switch Is Right
								m_pandaAutonSwitchRight.auton();
							}else {
								m_pandaAutonStraight.auton();//Drive Straight Or Do Nothing
							}
						}
					}else {//Switch Is Prioritized
						if (gameData.charAt(0) == 'R') { //Game Data Says Switch Is Right Side
							m_pandaAutonSwitchRight.auton();
						} else {// Assume Left Side Switch
							if (gameData.charAt(1) == 'R') { //Game Data Says Scale Is Right Side
								m_pandaAutonScaleRight.auton();
							} else {// Assume Left Side Scale
								m_pandaAutonStraight.auton();//Do Nothing Or Drive Straight
							}	
						}
					}			
				}
			} 
//		}else {
//			
//		}
	}	
	
	public void updateSmartDashboard() {
		
		SmartDashboard.putNumber("Start Postion", selectedStart());
		SmartDashboard.putBoolean("Scale Priority", selectedScalePriority());
		SmartDashboard.putString("Switch Selected", selectedSwitch());
		SmartDashboard.putString("Scale Selected", selectedScale());
		SmartDashboard.putNumber("Delay Timer Is ", delayTimer.get());
		
		if(SmartDashboard.getBoolean("Reset Auton", false)){ //If Reset Auton Is Set True(Default Value Is Set As False) Than ResetAll And Put Reset Auton As False
			resetAll();
			SmartDashboard.putBoolean("Reset Auton", false);
		}
		
	}
}
