package org.usfirst.frc.team1746.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class AutonBase {
	String gameData;
	int robotPosition;
	
	PandaAutonCenterSwitchLeft m_pandaAutonCenterSwitchLeft;
	PandaAutonCenterSwitchRight m_pandaAutonCenterSwitchRight;
	PandaAutonScaleRight m_pandaAutonScaleRight;
	PandaAutonScaleLeft m_pandaAutonScaleLeft;
	PandaAutonSwitchRight m_pandaAutonSwitchRight;
	PandaAutonSwitchLeft m_pandaAutonSwitchLeft;
	PandaAutonStraight m_pandaAutonStraight;
	PandaAutonFarScaleRight m_pandaAutonFarScaleRight;
	
	boolean scalePrioritized;
	
	
	public AutonBase(DriveTrain m_driveTrain, Lift m_lift, Intake m_intake) {
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		robotPosition = 1;
		scalePrioritized = true;
		
		m_pandaAutonCenterSwitchRight = new PandaAutonCenterSwitchRight(m_driveTrain,m_lift, m_intake);
	    m_pandaAutonCenterSwitchLeft = new PandaAutonCenterSwitchLeft(m_driveTrain,m_lift, m_intake);
	    m_pandaAutonScaleRight = new PandaAutonScaleRight(m_driveTrain, m_lift, m_intake);
	    m_pandaAutonScaleLeft = new PandaAutonScaleLeft(m_driveTrain, m_lift, m_intake);
	    m_pandaAutonSwitchLeft = new PandaAutonSwitchLeft(m_driveTrain, m_lift, m_intake);
	    m_pandaAutonSwitchRight = new PandaAutonSwitchRight(m_driveTrain, m_lift, m_intake);
	    m_pandaAutonStraight = new PandaAutonStraight(m_driveTrain, m_lift, m_intake);
	    m_pandaAutonFarScaleRight = new PandaAutonFarScaleRight(m_driveTrain, m_lift, m_intake);   
	    
	    m_pandaAutonCenterSwitchRight.init();
	    m_pandaAutonCenterSwitchLeft.init();
	    m_pandaAutonScaleRight.init();
	    m_pandaAutonScaleLeft.init();
	    m_pandaAutonSwitchLeft.init();
	    m_pandaAutonSwitchRight.init();
	    m_pandaAutonStraight.init();
	    m_pandaAutonFarScaleRight.init();
	}
	public void auton() {
		if(robotPosition == 1) {	//Bot On Left DriverStation Position
			if (scalePrioritized) { //Scale Is Prioritized
				if (gameData.charAt(1) == 'L') { //Game Data Says Scale Is Left Side
					m_pandaAutonScaleLeft.auton();
				} else {// Assume Right Side Scale
					m_pandaAutonFarScaleRight.auton();
//					if(gameData.charAt(0) == 'L') { //Game Data Says Switch Is Left
//						m_pandaAutonSwitchLeft.auton();
//					}else {
//						m_pandaAutonStraight.auton();//Drive Straight Or Do Nothing
//					}
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
}
