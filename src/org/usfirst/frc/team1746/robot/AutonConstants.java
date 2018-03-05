package org.usfirst.frc.team1746.robot;

public class AutonConstants {

	   /*
	    * 		We are currently assuming two horizontal driving lanes at the middles of the end of the switch and of the end of the scale (see	ScalePlacementAdjustment),
	    *               and two other horizontal driving lanes for traversing the field, called "Lane1" (which is between the alliance station and the switch) and
	    *               "Lane2" (which is between the switch and the scale).  The position of the last two driving lanes can be easily altered simply by changing
	    *               WallToLane1 or WallToLane2 below.
	    * 
		*		We are currently assuming that all vertical driving during autonomous will be done in lanes which line up with the following:
	   	*				a) a robot starting position
	   	*				b) a cube placement site (side of switch or scale or middle of the exchange)
	   	*				c) a cube pickup site
	    */
	
	   /*
	    * Chosen Distances in inches   (These are distances we need to choose as a team.... we probably ought to put these on a screen for adjustment at match time.)
	    */

	   public static final double AngleToRobot = 0;					// distance from start of flat wall to edge of robot (position "1" or "3") in inches
	   																//    the "flat wall" is comprised of the three operator stations and the exchange
	   public static final double MiddleOffset = 7;					// distance from the middle of the field to the middle of the robot (position "2") in inches
	   																//    the offset is towards the side of the field that doesn't have the exchange
	   																//		^^ IMPORTANT WHEN STARTING IN POSITION 2 !!!!!
	   public static final double WallToLane1 = 67;					// distance from wall to the middle of the first horizontal driving lane
	   public static final double WallToLane2 = 240;				// distance from wall to the middle of the second horizontal driving lane
	   public static final double PlacementBuffer = 1;				/* Still undetermined so a guestimate for now	   <<<< MODIFY VALUE AND COMMENT
	   																		distance from the front of the robot to the target (e.g. switch or scale or exchange)
	   																		when preparing to place a cube
	   																			Note: may want to break this into Switch, Scale, & Exchange placement buffers */
	   public static final double PickupBuffer = 1;					/* Still undetermined so a guestimate for now	   <<<< MODIFY VALUE AND COMMENT
	   																		distance from the front of the robot to the target (i.e. cube on floor)	when
	   																		preparing to pick up a cube */
	   public static final double InsideSwitch = 4;					// when placing cubes on the switch from the side, how far from the end to place them
	   public static final double InsideScale = 2;					// when placing cubes on the scale from the side, how far from the end to place them
	   public static final double ScalePlacementAdjustment = 16;    /* When this is zero, cubes placed at target positions 18 & 19 will be placed in the middle of
	    																	the end of the scale.  Setting this to a number means "place the cube this many inches
	    																	closer to our end of the field than the middle." Values greater than 17 should
	    																	definitely be tested. */

	   /*
	    * Other Autonomous Choices
	    */
	   
	   public static final double DefaultDrivingSpeed = .4;  // 1 means drive at maxVelocity by default, 0.75 means drive at three-quarters of maxVelocity by default
	   public static final double DefaultTurningSpeed = .4;  // 1 means turn at maxVelocity by default, 0.50 means turn at half of maxVelocity by default
	   public static final double AccelerationMultiplier = 2;  /* At what multiple of maximum velocity do you want to accelerate -- 1 means reach maximum velocity in 1 second,
	   																2 means reach maximum velocity in one-half second. */
	   public static final double DefaultIntakeSpeed = 0.5;	// Default speed for intake in and out for auton
	   public static final double IntakeInTime = .75;			// Amount of time until intakeIn finishes
	   public static final double IntakeOutTime = 2;		// Amount of time until intakeOut finishes	

	   public static final int velocityTolerance = 3;				/* Velocity Tolerance in ticks per 100 milliseconds - this is used to determine when we have actually
	         																started moving (at the beginning of driving or turning) and when we are finished moving (at
		     																at the end of driving and turning.  We have this to prevent a particular possible scenario
		     																from screwing everything up:  An encoder flickers back and forth a little bit when the robot
		     																is actually just sitting still. */
	   public static final int zeroVelocitiesTillDone = 2;			/* This is how many times in a row we need to see a velocity (on both the left and the right sides) 
	   																     	of less than the velocityTolerance to assume that we are done driving/turning.  */
	   
	   /*
	    * Robot Distances in inches
	    */

	   public static final double RobotWidth = 35;					// Measured from competition robot with bumpers
	   public static final double RobotLength = 35;					// Measured from competition robot with bumpers
	   public static final double wheelDiameter = 4;
	   public static final double BackToTurningAxis = 14.625;		// 180 degree test with competition robot        <<-- NEEDS TO BE RE-TESTED AND CONFIRMED
	   																	//     distance from the back of the robot to the axis of turning
	   							/*
	   							 * NOTE:  This code currently assumes that the axis of turning will be halfway between the left and
	   							 *        right sides of the robot and that the elevator/grabber is likewise centered !!!!
	   							 *        
	   							 *        ^^ THIS ASSUMPTION SHOULD BE TESTED AND VERIFIED
	   							 */

	   /*
	    * Encoder Based Constants
	    */
	   
	   public static final double ticksPerRevolution = 4000; 
	   public static final double maxVelocity = 3000;			/* The max velocity in encoder ticks per 100 milliseconds 
												       (NOTE:  The robot needs to be able to travel this speed under all circumstances)*/
	   public static final double defaultAcceleration = AccelerationMultiplier * maxVelocity;  /* I think this is in encoder ticks per 100 milliseconds per second... If so, a value
																									twice maxVelocity will cause the robot to reach maxVelocity in half of a second,
																									and a value thrice maxVelocity will get it there in one third of a second, etc.
																									This is used for driving and turning in Motion Magic mode, and is similar to the
																									"Ramp Rate" when just using the normal % of full power methodology except that
																									ramp rate is expressed in number of seconds to reach the given % of full power.
																									So ramp rate uses a slower acceleration when going up to a slower speed, whereas
																									configuring acceleration for Motion Magic uses a constant acceleration (I think).  */
	   public static final double ticksPerInch = ticksPerRevolution/(Math.PI * wheelDiameter);
	   public static final double encoderTicksPer90Degrees = 6750;		/* We move both wheels this number of encoder ticks (in opposite directions, of course) in
																				in order to make a 90 degree turn.
																				^^ VERY IMPORTANT WHEN USING MOTION MAGIC FOR TURNING -- TEST AND VERIFY  */

	   /*
	    * Arcade Distances in inches  --  Only change if the arcade is not setup according to specifications
	    */
	   
	   public static final double CubeSize = 13;					// From Manual
	   
	   		// Vertical Distances	   
	   
	   public static final double WallToLine = 120;					// From Diagram
	   public static final double WallToSwitch = 140;				// From Diagram
//	   public static final double WallToPlatform = 261.47;			// From Diagram
	   public static final double WallToScale = 299.65;				// From Diagram
	   
	   public static final double SwitchWidth = 56;					// From Diagram
	   public static final double ScaleWidth = 48;					// From Manual
	   
	   		// Horizontal Distances
	   
	   public static final double MiddleToAngle = 132.31;			// Field Width of 27 feet (from Manual) / 2 - rail to flat wall (from Diagram)
	   public static final double MiddleToScaleEnd = 90;			// Scale Length (from Manual) / 2
	   public static final double MiddleToSwitchEnd = 76.75;		// Switch Length (from Manual) / 2
	   public static final double ExchangeOffset = 22;				// Guess based on Diagram & Manual (middle of field to middle of exchange)  <<<< MODIFY VALUE AND COMMENT
	   																		//  ^^ ONLY USED WHEN PLACING IN THE EXCHANGE DURING AUTONOMOUS
	   		// Heights
	   
//	   public static final double SwitchHeight = 18.75;				// From Manual
//	   public static final double ScaleAvgHeight = 60;				// From Manual
//	   public static final double ScaleMaxHeight = 72;				// From Manual
//	   public static final double ScaleMinHeight = 48;				// From Manual
	   	   	   
	   /*
	    * Intermediate Constants  (Derived and used in Operating Constants, but not intended to be used directly in Commands.)
	    */
	   
	   public static final double MiddleToOutsideLane = MiddleToAngle - AngleToRobot - (RobotWidth * 0.5);
	   public static final double FrontToTurningAxis = RobotLength - BackToTurningAxis;
	   public static final double BetweenCubes = ((MiddleToSwitchEnd * 2 ) - CubeSize) / 5;

	   /*
	    * Operating Constants.  These constants are intended to be used directly in commands.
	    */
	   
	   		// Vertical Driving Distances
	   
	   public static final double StartToMidSwitch = WallToSwitch + (SwitchWidth * 0.5) - BackToTurningAxis;
	   public static final double StartToMidScale = WallToScale + (ScaleWidth * 0.5) - ScalePlacementAdjustment - BackToTurningAxis;
	   
	   public static final double StartToLane1 = WallToLane1 - BackToTurningAxis;
	   public static final double StartToLane2 = WallToLane2 - BackToTurningAxis;
	   
	   public static final double Lane1ToMidSwitch = StartToMidSwitch - StartToLane1;
	   public static final double Lane1ToMidScale = StartToMidScale - StartToLane1;
	   public static final double Lane2ToMidSwitch = StartToLane2 - StartToMidSwitch;
	   public static final double Lane2ToMidScale = StartToMidScale - StartToLane2;

	   public static final double Lane1ToSwitch = WallToSwitch - WallToLane1 - FrontToTurningAxis - PlacementBuffer;
	   public static final double Lane2ToSwitchForPlacement = WallToLane2 - WallToSwitch - SwitchWidth - FrontToTurningAxis - CubeSize;			// Assumes, for now, cube will be in the way
	   public static final double Lane2ToSwitchForPickup = WallToLane2 - WallToSwitch - SwitchWidth - FrontToTurningAxis - CubeSize - PickupBuffer;
	   public static final double Lane2ToScale = WallToScale - WallToLane2 - FrontToTurningAxis - PlacementBuffer;
	   public static final double Lane1ToExchange = WallToLane1 - FrontToTurningAxis - PlacementBuffer;		// May want to change to a new placement buffer
	   public static final double Lane1ToLane2 = WallToLane2 - WallToLane1;
	   public static final double Lane1ToCube = WallToSwitch - WallToLane1 - (CubeSize * 3) - FrontToTurningAxis - PickupBuffer;		// Front cube at position 5
	   
	   		// Horizontal Driving Distances

	   public static final double OutsideToSwitch = MiddleToOutsideLane - MiddleToSwitchEnd - FrontToTurningAxis - PlacementBuffer;
	   public static final double OutsideToScale = MiddleToOutsideLane - MiddleToScaleEnd - FrontToTurningAxis - PlacementBuffer;
	   public static final double OutsideToSwitchSideNear = MiddleToOutsideLane + InsideSwitch + (CubeSize * 0.5) - MiddleToSwitchEnd;
	   public static final double OutsideToScaleSideNear = MiddleToOutsideLane + InsideScale + (CubeSize * 0.5) - MiddleToScaleEnd;
	   public static final double OutsideToMiddle = MiddleToOutsideLane;
	   public static final double OutsideToOutside = MiddleToOutsideLane * 2;
	   public static final double OutsideToSwitchSideFar = OutsideToOutside - OutsideToSwitchSideNear;
	   public static final double OutsideToScaleSideFar = OutsideToOutside - OutsideToScaleSideNear;
	   public static final double OutsideToFirstCube = MiddleToOutsideLane - MiddleToSwitchEnd + (CubeSize * 0.5);
	   public static final double OutsideToSecondCube = OutsideToFirstCube + BetweenCubes;
	   public static final double OutsideToThirdCube = OutsideToFirstCube + (BetweenCubes * 2);
	   public static final double OutsideToFourthCube = OutsideToFirstCube + (BetweenCubes * 3);
	   public static final double OutsideToFifthCube = OutsideToFirstCube + (BetweenCubes * 4);
	   public static final double OutsideToSixthCube = OutsideToFirstCube + (BetweenCubes * 5);
	   
	   public static final double OutsideToExchangeNear = MiddleToOutsideLane - ExchangeOffset;
	   public static final double OutsideToExchangeFar = MiddleToOutsideLane + ExchangeOffset;

	   public static final double OutsideToRobot2Near = MiddleToOutsideLane - MiddleOffset;		// Distance from outside lane to the middle of the robot when it is at starting position 2
	   public static final double OutsideToRobot2Far = MiddleToOutsideLane + MiddleOffset;		//	 One outside lane is closer to the robot than the other


	   /*
	    * Note: If you travel a distance of OutsideToSecondCube from one outside lane, you can continue for a distance of OutSideToFifthCube
	    *       in the same direction to reach the other outside lane.  In other words, if the robot is on the left outside lane and is currently
	    *       pointed along lane2, then the following command snippet causes the robot to travel from the left outside lane (along lane2), turn
	    *       to pick up the second cube, return to lane2, and proceed along lane2 to the right outside lane:
	    *       
	    *            "A"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup+"<commands_to_pickup_cube>B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube
	    *            
	    *       In yet other words, each of the following is equivalent to OutsideToOutSide:
	    *       
	    *       	 OutsideToFirstCube + OutsideToSixthCube
	    *       	 OutsideToSecondCube + OutsideToFifthCube
	    *       	 OutsideToThirdCube + OutsideToFourthCube
	    */
	   
	   /*
	    * Note: This program currently assumes that six cubes are placed equidistant from one another along the side of the switch with the
	    *       first and sixth cubes aligned with the end of the switch.
	    */
	   
	   /*
	    * Commands:
	    * 
	    *     A - Ahead (drive straight at the default driving speed for the given number of inches (Accepts override speed))
	    *     B - Backward (drive straight in reverse at the default speed for the given number of inches (Accepts override speed))
	    *         (Note: As of 2/24/18 "A-" is the same as "B" and "B-" is the same as "A" - e.g. "A-20" and "B20" both say "backup 20 inches".)
	    *     R - Right (turn right at the default turning speed exactly 90 degrees (Accepts override speed))
	    *     L - Left (turn left at the default turning speed exactly 90 degrees (Accepts override speed))
	    *     
	    *     H - Height (move the elevator at the default speed to the specified height position: 0=bottom, 1=above switch, 2=above scale)
	    *     
	    *     O - Output (put the cube out at the default speed)
	    *     I - Input (get a cube in at the default speed)
	    *     
	    *     W - Wait (Wait for all command(s) to finish and/or wait at least a certain number of given seconds)
	    *     
	    */
	   public static final String driveCommands = "ABRL";
	   public static final String elevatorGrabberCommands = "HIO";
	   public static final String specialCommands = "W";
	   public static final String[][] commands = new String[][] {
		   {																																// FROM 1
			   "IA"+StartToLane1+"RA"+OutsideToExchangeNear+"RA"+Lane1ToExchange+"WOW",														//        TO 4
		       "!",																															//        TO 5
			   "IA"+StartToLane1+"RA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW",													//        TO 6
			   "IA"+StartToLane1+"RA"+OutsideToSwitchSideFar+"LH1WA"+Lane1ToSwitch+"WOW",													//        TO 7
			   "IA"+StartToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",																			//        TO 8
			   "IA"+StartToLane2+"RA"+OutsideToOutside+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",									//        TO 9
// Lane 1	   "IA"+StartToLane1+"RA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",									//        TO 9
			   "IA"+StartToLane2+"RA"+OutsideToFirstCube+"RH1WA"+Lane2ToSwitchForPlacement+"WOW",											//        TO 10
			   "IA"+StartToLane2+"RA"+OutsideToSixthCube+"RH1WA"+Lane2ToSwitchForPlacement+"WOW",											//        TO 11
// Lane 1	   "IA"+StartToLane1+"RA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LH1WA"+Lane2ToSwitchForPlacement+"WOW",	//        TO 11
			   "IA"+StartToLane2+"RA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup,														//        TO 12
			   "IA"+StartToLane2+"RA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup,														//        TO 13
			   "IA"+StartToLane2+"RA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup,														//        TO 14
			   "IA"+StartToLane2+"RA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup,														//        TO 15
			   "IA"+StartToLane2+"RA"+OutsideToScaleSideNear+"LH2WA"+Lane2ToScale+"WOW",													//        TO 16
			   "IA"+StartToLane2+"RA"+OutsideToScaleSideFar+"LH2WA"+Lane2ToScale+"WOW",														//        TO 17
			   "IA"+StartToMidScale+"RH2WA"+OutsideToScale+"WOW", 																			//        TO 18
			   "IA"+StartToLane2+"RA"+OutsideToOutside+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",									//        TO 19		       
// Lane 1      "IA"+StartToLane1+"RA"+OutsideToOutside+"LA"+Lane1ToMidScale+"LH2WA"+OutsideToScale+"WOW",									//        TO 19		       
		   },
		   {																																// FROM 2
			   "IA"+StartToLane1+"LA"+(ExchangeOffset+MiddleOffset)+"LA"+Lane1ToExchange+"WOW",												//        TO 4
		       "!",																															//        TO 5
		       "IA"+StartToLane1+"LA"+(OutsideToRobot2Far-OutsideToSwitchSideNear)+"RH1WA"+Lane1ToSwitch+"WOW",								//        TO 6
		       "IA"+StartToLane1+"RA"+(OutsideToRobot2Near-OutsideToSwitchSideNear)+"LH1WA"+Lane1ToSwitch+"WOW",							//        TO 7
		       "IA"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",								//        TO 8
		       "IA"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",								//        TO 9
			   "IA"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RH1WA"+Lane2ToSwitchForPlacement+"WOW", //        TO 10
			   "IA"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LH1WA"+Lane2ToSwitchForPlacement+"WOW", //       TO 11
		       "!",																															//        TO 12
		       "!",																															//        TO 13
		       "!",																															//        TO 14
		       "!",																															//        TO 15
		       "IA"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToLane2+"RA"+OutsideToScaleSideNear+"LH2WA"+Lane2ToScale+"WOW",			//        TO 16
		       "IA"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToLane2+"LA"+OutsideToScaleSideNear+"RH2WA"+Lane2ToScale+"WOW",			//        TO 17
		       "IA"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToMidScale+"RH2WA"+OutsideToScale+"WOW",									//        TO 18
		       "IA"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToMidScale+"LH2WA"+OutsideToScale+"WOW",								//        TO 19		       
		   },
		   {																																// FROM 3
		       "IA"+StartToLane1+"LA"+OutsideToExchangeFar+"LA"+Lane1ToExchange+"WOW",														//        TO 4
		       "!",																															//        TO 5
		       "IA"+StartToLane1+"LA"+OutsideToSwitchSideFar+"RH1WA"+Lane1ToSwitch+"WOW",													//        TO 6
		       "IA"+StartToLane1+"RA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW",													//        TO 7
		       "IA"+StartToLane2+"LA"+OutsideToOutside+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",									//        TO 8
// Lane 1	   "IA"+StartToLane1+"LA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",									//        TO 8
		       "IA"+StartToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",																			//        TO 9
			   "IA"+StartToLane2+"LA"+OutsideToSixthCube+"LWA"+Lane2ToSwitchForPickup+"WOW",												//        TO 10
// Lane 1	   "IA"+StartToLane1+"LA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPlacement+"WOW",		//        TO 10
			   "IA"+StartToLane2+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"WOW",												//        TO 11
			   "IA"+StartToLane2+"LA"+OutsideToFifthCube+"LA"+Lane2ToSwitchForPickup,														//        TO 12
			   "IA"+StartToLane2+"LA"+OutsideToSecondCube+"LA"+Lane2ToSwitchForPickup,														//        TO 13
			   "IA"+StartToLane2+"LA"+OutsideToFourthCube+"LA"+Lane2ToSwitchForPickup,														//        TO 14
			   "IA"+StartToLane2+"LA"+OutsideToThirdCube+"LA"+Lane2ToSwitchForPickup,														//        TO 15
			   "IA"+StartToLane2+"LA"+OutsideToScaleSideFar+"RH2WA"+Lane2ToScale+"WOW",														//        TO 16
			   "IA"+StartToLane2+"LA"+OutsideToScaleSideNear+"RH2WA"+Lane2ToScale+"WOW",													//        TO 17
			   "IA"+StartToLane2+"LA"+OutsideToOutside+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",									//        TO 18
// Lane 1      "IWA"+StartToLane1+"LA"+OutsideToOutside+"RA"+Lane1ToMidScale+"RH2WA"+OutsideToScale+"WOW",									//        TO 18		       
			   "IA"+StartToMidScale+"LH2WA"+OutsideToScale+"WOW",																			//        TO 19		       
		   },
		   {																																// FROM 4
			   "!",																															//        TO 4
		       "B"+Lane1ToExchange+"LA"+ExchangeOffset+"LA"+Lane1ToCube+"IW",																//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup+"IW",	//        TO 10
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToSixthCube+"RA"+Lane2ToSwitchForPickup+"IW",	//        TO 11
// Lane 1      "B"+Lane1ToExchange+"LA"+OutsideToExchangeFar+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup+"IW",	//        TO 11
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup+"IW",	//        TO 12
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup+"IW",	//        TO 13
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup+"IW",	//        TO 14
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup+"IW",	//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 5
			   "B"+Lane1ToCube+"LA"+ExchangeOffset+"LA"+Lane1ToExchange,																	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane1ToCube+"LA"+(OutsideToMiddle-OutsideToSwitchSideNear)+"RH1WA"+Lane1ToSwitch+"WOW",									//        TO 6
		       "B"+Lane1ToCube+"RA"+(OutsideToMiddle-OutsideToSwitchSideNear)+"LH1WA"+Lane1ToSwitch+"WOW",									//        TO 7
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",									//        TO 8
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",									//        TO 9
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RH1WA"+Lane2ToSwitchForPlacement+"WOW",		//        TO 10
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LH1WA"+Lane2ToSwitchForPlacement+"WOW",		//        TO 11
		       "!",																															//        TO 12
		       "!",																															//        TO 13
		       "!",																															//        TO 14
		       "!",																															//        TO 15
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToLane2+"RA"+OutsideToScaleSideNear+"LH2WA"+Lane2ToScale+"WOW",				//        TO 16
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToLane2+"LA"+OutsideToScaleSideNear+"RH2WA"+Lane2ToScale+"WOW",				//        TO 17
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToMidScale+"RH2WA"+OutsideToScale+"WOW",										//        TO 18
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToMidScale+"LH2WA"+OutsideToScale+"WOW",										//        TO 19		       
		   },
		   {																																// FROM 6
			   "!",																															//        TO 4
		       "B"+Lane1ToSwitch+"RH0A"+(OutsideToMiddle-OutsideToSwitchSideNear)+"LWA"+Lane1ToCube+"IW",									//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW", //       TO 10
			   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToSixthCube+"RWA"+Lane2ToSwitchForPickup+"IW", //       TO 11
// Lane 1	   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideFar+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW",	 //       TO 11
			   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToSecondCube+"RWA"+Lane2ToSwitchForPickup+"IW", //      TO 12
			   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToFifthCube+"RWA"+Lane2ToSwitchForPickup+"IW", //       TO 13
			   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToThirdCube+"RWA"+Lane2ToSwitchForPickup+"IW", //       TO 14
			   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToFourthCube+"RWA"+Lane2ToSwitchForPickup+"IW", //      TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 7
			   "!",																															//        TO 4
		       "B"+Lane1ToSwitch+"LH0A"+(OutsideToMiddle-OutsideToSwitchSideNear)+"RWA"+Lane1ToCube+"IW",									//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToSixthCube+"LWA"+Lane2ToSwitchForPickup+"IW", //       TO 10
// Lane 1	   "B"+Lane1ToSwitch+"LH0A"+OutsideToSwitchSideFar+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW",	//        TO 10
			   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW", //       TO 11
			   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToFifthCube+"LWA"+Lane2ToSwitchForPickup+"IW", //       TO 12
			   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToSecondCube+"LWA"+Lane2ToSwitchForPickup+"IW", //      TO 13
			   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToFourthCube+"LWA"+Lane2ToSwitchForPickup+"IW", //      TO 14
			   "B"+Lane1ToSwitch+"RH0A"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToThirdCube+"LWA"+Lane2ToSwitchForPickup+"IW", //       TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },	
		   {																																// FROM 8
			   "!",																															//        TO 4
		       "B"+OutsideToSwitch+"RH0A"+Lane1ToMidSwitch+"LA"+OutsideToMiddle+"LWA"+Lane1ToCube+"IW",										//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+OutsideToSwitch+"LH0A"+Lane2ToMidSwitch+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 10
			   "B"+OutsideToSwitch+"LH0A"+Lane2ToMidSwitch+"RA"+OutsideToSixthCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 11
//Lane 1	   "B"+OutsideToSwitch+"RH0A"+Lane1ToMidSwitch+"LA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW", // TO 11
			   "B"+OutsideToSwitch+"LH0A"+Lane2ToMidSwitch+"RA"+OutsideToSecondCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 12
			   "B"+OutsideToSwitch+"LH0A"+Lane2ToMidSwitch+"RA"+OutsideToFifthCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 13
			   "B"+OutsideToSwitch+"LH0A"+Lane2ToMidSwitch+"RA"+OutsideToThirdCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 14
			   "B"+OutsideToSwitch+"LH0A"+Lane2ToMidSwitch+"RA"+OutsideToFourthCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 9
			   "!",																															//        TO 4
		       "B"+OutsideToSwitch+"LH0A"+Lane1ToMidSwitch+"RA"+OutsideToMiddle+"RWA"+Lane1ToCube+"IW",										//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+OutsideToSwitch+"RH0A"+Lane2ToMidSwitch+"LA"+OutsideToSixthCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 10
// Lane 1	   "B"+OutsideToSwitch+"LH0A"+Lane1ToMidSwitch+"RA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW", // TO 10
			   "B"+OutsideToSwitch+"RH0A"+Lane2ToMidSwitch+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 11
			   "B"+OutsideToSwitch+"RH0A"+Lane2ToMidSwitch+"LA"+OutsideToFifthCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 12
			   "B"+OutsideToSwitch+"RH0A"+Lane2ToMidSwitch+"LA"+OutsideToSecondCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 13
			   "B"+OutsideToSwitch+"RH0A"+Lane2ToMidSwitch+"LA"+OutsideToFourthCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 14
			   "B"+OutsideToSwitch+"RH0A"+Lane2ToMidSwitch+"LA"+OutsideToThirdCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 10
			   "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToExchangeNear+"RA"+Lane1ToExchange+"WOW",	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW", //      TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSixthCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RH1WA"+Lane1ToSwitch+"WOW", //      TO 7
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideFar+"LH1WA"+Lane1ToSwitch+"WOW", //       TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",						//        TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSixthCube+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",						//        TO 9
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW", // TO 9
			   "H1WA"+(Lane2ToSwitchForPlacement-Lane2ToSwitchForPickup)+"WOWB"+(Lane2ToSwitchForPlacement-Lane2ToSwitchForPickup),			//        TO 10
		       "B"+Lane2ToSwitchForPickup+"H0LA"+(OutsideToSixthCube-OutsideToFirstCube)+"RWA"+Lane2ToSwitchForPickup+"IW",					//        TO 11
		       "B"+Lane2ToSwitchForPickup+"H0LA"+(OutsideToSecondCube-OutsideToFirstCube)+"RWA"+Lane2ToSwitchForPickup+"IW",				//        TO 12
		       "B"+Lane2ToSwitchForPickup+"H0LA"+(OutsideToFifthCube-OutsideToFirstCube)+"RWA"+Lane2ToSwitchForPickup+"IW",					//        TO 13
		       "B"+Lane2ToSwitchForPickup+"H0LA"+(OutsideToFourthCube-OutsideToFirstCube)+"RWA"+Lane2ToSwitchForPickup+"IW",				//        TO 14
		       "B"+Lane2ToSwitchForPickup+"H0LA"+(OutsideToThirdCube-OutsideToFirstCube)+"RWA"+Lane2ToSwitchForPickup+"IW",					//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToFirstCube-OutsideToScaleSideNear)+"RH2WA"+Lane2ToScale+"WOW",						//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToSixthCube-OutsideToScaleSideNear)+"LH2WA"+Lane2ToScale+"WOW",						//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",						//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSixthCube+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",						//        TO 19		       
		   },
		   {																																// FROM 11
			   "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToExchangeFar+"LA"+Lane1ToExchange+"WOW",	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSixthCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW", //      TO 6
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideFar+"RH1WA"+Lane1ToSwitch+"WOW", //       TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RH1WA"+Lane1ToSwitch+"WOW", //      TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSixthCube+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",						//        TO 8
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW", // TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",						//        TO 9
		       "WB"+Lane2ToSwitchForPickup+"RH0A"+(OutsideToSixthCube-OutsideToFirstCube)+"LWA"+Lane2ToSwitchForPickup+"IW",				//        TO 10
			   "H1WA"+(Lane2ToSwitchForPlacement-Lane2ToSwitchForPickup)+"WOWB"+(Lane2ToSwitchForPlacement-Lane2ToSwitchForPickup),			//        TO 11
			   "WB"+Lane2ToSwitchForPickup+"RH0A"+(OutsideToFifthCube-OutsideToFirstCube)+"LWA"+Lane2ToSwitchForPickup+"IW",				//        TO 12
			   "WB"+Lane2ToSwitchForPickup+"RH0A"+(OutsideToSecondCube-OutsideToFirstCube)+"LWA"+Lane2ToSwitchForPickup+"IW",				//        TO 13
			   "WB"+Lane2ToSwitchForPickup+"RH0A"+(OutsideToFourthCube-OutsideToFirstCube)+"LWA"+Lane2ToSwitchForPickup+"IW",				//        TO 14
			   "WB"+Lane2ToSwitchForPickup+"RH0A"+(OutsideToThirdCube-OutsideToFirstCube)+"LWA"+Lane2ToSwitchForPickup+"IW",				//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToSixthCube-OutsideToScaleSideNear)+"RH2WA"+Lane2ToScale+"WOW",						//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToFirstCube-OutsideToScaleSideNear)+"LH2WA"+Lane2ToScale+"WOW",						//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSixthCube+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",						//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",						//        TO 19		       
		   },
		   {																																// FROM 12
			   "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToExchangeNear+"RA"+Lane1ToExchange+"WOW",	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW", //     TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RH1WA"+Lane1ToSwitch+"WOW", //      TO 7
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideFar+"LH1WA"+Lane1ToSwitch+"WOW", //      TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",						//        TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",						//        TO 9
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW", // TO 9
			   "!",																															//        TO 10
		       "!",																															//        TO 11
		       "!",																															//        TO 12
		       "!",																															//        TO 13
		       "!",																															//        TO 14
		       "!",																															//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToSecondCube-OutsideToScaleSideNear)+"RH2WA"+Lane2ToScale+"WOW",						//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToFifthCube-OutsideToScaleSideNear)+"LH2WA"+Lane2ToScale+"WOW",						//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",						//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",						//        TO 19		       
		   },
		   {																																// FROM 13
			   "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToExchangeFar+"LA"+Lane1ToExchange+"WOW",	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFifthCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW", //      TO 6
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideFar+"RH1WA"+Lane1ToSwitch+"WOW", //      TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RH1WA"+Lane1ToSwitch+"WOW", //     TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFifthCube+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",						//        TO 8
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW", // TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",						//        TO 9
			   "!",																															//        TO 10
		       "!",																															//        TO 11
		       "!",																															//        TO 12
		       "!",																															//        TO 13
		       "!",																															//        TO 14
		       "!",																															//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToFifthCube-OutsideToScaleSideNear)+"RH2WA"+Lane2ToScale+"WOW",						//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToSecondCube-OutsideToScaleSideNear)+"LH2WA"+Lane2ToScale+"WOW",						//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFifthCube+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",						//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",						//        TO 19		       
		   },
		   {																																// FROM 14
			   "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToExchangeNear+"RA"+Lane1ToExchange+"WOW",	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW", //      TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFourthCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RH1WA"+Lane1ToSwitch+"WOW", //     TO 7
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideFar+"LH1WA"+Lane1ToSwitch+"WOW", //       TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",						//        TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFourthCube+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",						//        TO 9
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW", // TO 9
			   "!",																															//        TO 10
		       "!",																															//        TO 11
		       "!",																															//        TO 12
		       "!",																															//        TO 13
		       "!",																															//        TO 14
		       "!",																															//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToThirdCube-OutsideToScaleSideNear)+"RH2WA"+Lane2ToScale+"WOW",						//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToFourthCube-OutsideToScaleSideNear)+"LH2WA"+Lane2ToScale+"WOW",						//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",						//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFourthCube+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",						//        TO 19		       
		   },
		   {																																// FROM 15
			   "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToExchangeFar+"LA"+Lane1ToExchange+"WOW",	//        TO 4
		       "!",																															//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFourthCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LH1WA"+Lane1ToSwitch+"WOW", //     TO 6
// Lane 1		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideFar+"RH1WA"+Lane1ToSwitch+"WOW", // TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RH1WA"+Lane1ToSwitch+"WOW", //      TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFourthCube+"LA"+Lane2ToMidSwitch+"LH1WA"+OutsideToSwitch+"WOW",						//        TO 8
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW", // TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane2ToMidSwitch+"RH1WA"+OutsideToSwitch+"WOW",						//        TO 9
			   "!",																															//        TO 10
		       "!",																															//        TO 11
		       "!",																															//        TO 12
		       "!",																															//        TO 13
		       "!",																															//        TO 14
		       "!",																															//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToFourthCube-OutsideToScaleSideNear)+"RH2WA"+Lane2ToScale+"WOW",						//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToThirdCube-OutsideToScaleSideNear)+"LH2WA"+Lane2ToScale+"WOW",						//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFourthCube+"RA"+Lane2ToMidScale+"RH2WA"+OutsideToScale+"WOW",						//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"LA"+Lane2ToMidScale+"LH2WA"+OutsideToScale+"WOW",						//        TO 19		       
		   },
		   {																																// FROM 16
			   "!",																															//        TO 4
		       "B"+Lane2ToScale+"LH0A"+OutsideToScaleSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToMiddle+"LWA"+Lane1ToCube+"IW",				//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+Lane2ToScale+"RH0A"+(OutsideToFirstCube-OutsideToScaleSideNear)+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 10
			   "B"+Lane2ToScale+"RH0A"+(OutsideToSixthCube-OutsideToScaleSideNear)+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 11
//Lane 1	   "B"+Lane2ToScale+"LH0A"+OutsideToScaleSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW", // TO 11
			   "B"+Lane2ToScale+"RH0A"+(OutsideToSecondCube-OutsideToScaleSideNear)+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 12
			   "B"+Lane2ToScale+"RH0A"+(OutsideToFifthCube-OutsideToScaleSideNear)+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 13
			   "B"+Lane2ToScale+"RH0A"+(OutsideToThirdCube-OutsideToScaleSideNear)+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 14
			   "B"+Lane2ToScale+"RH0A"+(OutsideToFourthCube-OutsideToScaleSideNear)+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 17
			   "!",																															//        TO 4
		       "B"+Lane2ToScale+"RH0A"+OutsideToScaleSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToMiddle+"RWA"+Lane1ToCube+"IW",				//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+Lane2ToScale+"LH0A"+(OutsideToSixthCube-OutsideToScaleSideNear)+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 10
//Lane 1	   "B"+Lane2ToScale+"RH0A"+OutsideToScaleSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW", // TO 10
			   "B"+Lane2ToScale+"LH0A"+(OutsideToFirstCube-OutsideToScaleSideNear)+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 11
			   "B"+Lane2ToScale+"LH0A"+(OutsideToFifthCube-OutsideToScaleSideNear)+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 12
			   "B"+Lane2ToScale+"LH0A"+(OutsideToSecondCube-OutsideToScaleSideNear)+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 13
			   "B"+Lane2ToScale+"LH0A"+(OutsideToFourthCube-OutsideToScaleSideNear)+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 14
			   "B"+Lane2ToScale+"LH0A"+(OutsideToThirdCube-OutsideToScaleSideNear)+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 18
			   "!",																															//        TO 4
		       "B"+OutsideToScale+"RH0A"+Lane1ToMidScale+"LA"+OutsideToMiddle+"LWA"+Lane1ToCube+"IW",										//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+OutsideToScale+"RH0A"+Lane2ToMidScale+"LA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW",							//        TO 10
			   "B"+OutsideToScale+"RH0A"+Lane2ToMidScale+"LA"+OutsideToSixthCube+"RWA"+Lane2ToSwitchForPickup+"IW",							//        TO 11
//Lane 1	   "B"+OutsideToScale+"RH0A"+Lane1ToMidScale+"LA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW",	// TO 11
			   "B"+OutsideToScale+"RH0A"+Lane2ToMidScale+"LA"+OutsideToSecondCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 12
			   "B"+OutsideToScale+"RH0A"+Lane2ToMidScale+"LA"+OutsideToFifthCube+"RWA"+Lane2ToSwitchForPickup+"IW",							//        TO 13
			   "B"+OutsideToScale+"RH0A"+Lane2ToMidScale+"LA"+OutsideToThirdCube+"RWA"+Lane2ToSwitchForPickup+"IW",							//        TO 14
			   "B"+OutsideToScale+"H0RA"+Lane2ToMidScale+"LA"+OutsideToFourthCube+"RWA"+Lane2ToSwitchForPickup+"IW",						//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
		   {																																// FROM 19
			   "!",																															//        TO 4
		       "B"+OutsideToScale+"LH0A"+Lane1ToMidScale+"RA"+OutsideToMiddle+"RWA"+Lane1ToCube+"IW",										//        TO 5
			   "!",																															//        TO 6
			   "!",																															//        TO 7
			   "!",																															//        TO 8
			   "!",																															//        TO 9
			   "B"+OutsideToScale+"LH0A"+Lane2ToMidScale+"RA"+OutsideToSixthCube+"LWA"+Lane2ToSwitchForPickup+"IW",							//        TO 10
//Lane 1	   "B"+OutsideToScale+"LH0A"+Lane1ToMidScale+"RA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RWA"+Lane2ToSwitchForPickup+"IW",	// TO 10
			   "B"+OutsideToScale+"LH0A"+Lane2ToMidScale+"RA"+OutsideToFirstCube+"LWA"+Lane2ToSwitchForPickup+"IW",							//        TO 11
			   "B"+OutsideToScale+"LH0A"+Lane2ToMidScale+"RA"+OutsideToFifthCube+"LWA"+Lane2ToSwitchForPickup+"IW",							//        TO 12
			   "B"+OutsideToScale+"LH0A"+Lane2ToMidScale+"RA"+OutsideToSecondCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 13
			   "B"+OutsideToScale+"LH0A"+Lane2ToMidScale+"RA"+OutsideToFourthCube+"LWA"+Lane2ToSwitchForPickup+"IW",						//        TO 14
			   "B"+OutsideToScale+"LH0A"+Lane2ToMidScale+"RA"+OutsideToThirdCube+"LWA"+Lane2ToSwitchForPickup+"IW",							//        TO 15
		       "!",																															//        TO 16
		       "!",																															//        TO 17
		       "!",																															//        TO 18
		       "!",																															//        TO 19		       
		   },
	   };
	   
	   public void dump() {
		   System.out.println("");
		   System.out.println("---------- Chosen Distances in inches -------");
		   System.out.println("");
		   System.out.println("             AngleToRobot: "+AngleToRobot);
		   System.out.println("             MiddleOffset: "+MiddleOffset);
		   System.out.println("              WallToLane1: "+WallToLane1);
		   System.out.println("              WallToLane2: "+WallToLane2);
		   System.out.println("          PlacementBuffer: "+PlacementBuffer);
		   System.out.println("             PickupBuffer: "+PickupBuffer);
		   System.out.println("             InsideSwitch: "+InsideSwitch);
		   System.out.println("              InsideScale: "+InsideScale);
		   System.out.println(" ScalePlacementAdjustment: "+ScalePlacementAdjustment);
		   System.out.println("");
		   System.out.println("---------- Other Autonomous Choices ---------");
		   System.out.println("");
		   System.out.println("      DefaultDrivingSpeed: "+DefaultDrivingSpeed);
		   System.out.println("      DefaultTurningSpeed: "+DefaultTurningSpeed);
		   System.out.println("   AccelerationMultiplier: "+AccelerationMultiplier);
		   System.out.println("       DefaultIntakeSpeed: "+DefaultIntakeSpeed);
		   System.out.println("             IntakeInTime: "+IntakeInTime);
		   System.out.println("            IntakeOutTime: "+IntakeOutTime);
		   System.out.println("        velocityTolerance: "+velocityTolerance);
		   System.out.println("   zeroVelocitiesTillDone: "+zeroVelocitiesTillDone);
		   System.out.println("");
		   System.out.println("---------- Robot Distances in inches --------");
		   System.out.println("");
		   System.out.println("               RobotWidth: "+RobotWidth);
		   System.out.println("              RobotLength: "+RobotLength);
		   System.out.println("            wheelDiameter: "+wheelDiameter);
		   System.out.println("        BackToTurningAxis: "+BackToTurningAxis);
		   System.out.println("");
		   System.out.println("----------- Encoder Based Constants ---------");
		   System.out.println("");
		   System.out.println("       ticksPerRevolution: "+ticksPerRevolution);
		   System.out.println("              maxVelocity: "+maxVelocity);
		   System.out.println("      defaultAcceleration: "+defaultAcceleration);
		   System.out.println("             ticksPerInch: "+ticksPerInch);
		   System.out.println(" encoderTicksPer90Degrees: "+encoderTicksPer90Degrees);
		   System.out.println("");
		   System.out.println("---------- Arcade Distances in inches -------");
		   System.out.println("");
		   System.out.println("                 CubeSize: "+CubeSize);
		   System.out.println("               WallToLine: "+WallToLine);
		   System.out.println("             WallToSwitch: "+WallToSwitch);
//		   System.out.println("           WallToPlatform: "+WallToPlatform);
		   System.out.println("              WallToScale: "+WallToScale);
		   System.out.println("              SwitchWidth: "+SwitchWidth);
		   System.out.println("               ScaleWidth: "+ScaleWidth);
		   System.out.println("            MiddleToAngle: "+MiddleToAngle);
		   System.out.println("         MiddleToScaleEnd: "+MiddleToScaleEnd);
		   System.out.println("        MiddleToSwitchEnd: "+MiddleToSwitchEnd);
		   System.out.println("           ExchangeOffset: "+ExchangeOffset);
		   System.out.println("");
		   System.out.println("------------ Intermediate Constants ---------");
		   System.out.println("");
		   System.out.println("      MiddleToOutsideLane: "+MiddleToOutsideLane);
		   System.out.println("       FrontToTurningAxis: "+FrontToTurningAxis);
		   System.out.println("             BetweenCubes: "+BetweenCubes);
		   System.out.println("");
		   System.out.println("---------- Vertical Driving Distances -------");
		   System.out.println("");
		   System.out.println("         StartToMidSwitch: "+StartToMidSwitch);
		   System.out.println("          StartToMidScale: "+StartToMidScale);
		   System.out.println("             StartToLane1: "+StartToLane1);
		   System.out.println("             StartToLane2: "+StartToLane2);
		   System.out.println("         Lane1ToMidSwitch: "+Lane1ToMidSwitch);
		   System.out.println("          Lane1ToMidScale: "+Lane1ToMidScale);
		   System.out.println("         Lane2ToMidSwitch: "+Lane2ToMidSwitch);
		   System.out.println("          Lane2ToMidScale: "+Lane2ToMidScale);
		   System.out.println("            Lane1ToSwitch: "+Lane1ToSwitch);
		   System.out.println("Lane2ToSwitchForPlacement: "+MiddleToOutsideLane);
		   System.out.println("   Lane2ToSwitchForPickup: "+Lane2ToSwitchForPickup);
		   System.out.println("             Lane2ToScale: "+Lane2ToScale);
		   System.out.println("          Lane1ToExchange: "+Lane1ToExchange);
		   System.out.println("             Lane1ToLane2: "+Lane1ToLane2);
		   System.out.println("              Lane1ToCube: "+Lane1ToCube);
		   System.out.println("");
		   System.out.println("--------- Horizontal Driving Distances ------");
		   System.out.println("");
		   System.out.println("         OutsideToSwitch: "+OutsideToSwitch);
		   System.out.println("          OutsideToScale: "+OutsideToScale);
		   System.out.println(" OutsideToSwitchSideNear: "+OutsideToSwitchSideNear);
		   System.out.println("  OutsideToScaleSideNear: "+OutsideToScaleSideNear);
		   System.out.println("         OutsideToMiddle: "+OutsideToMiddle);
		   System.out.println("        OutsideToOutside: "+OutsideToOutside);
		   System.out.println("  OutsideToSwitchSideFar: "+OutsideToSwitchSideFar);
		   System.out.println("   OutsideToScaleSideFar: "+OutsideToScaleSideFar);
		   System.out.println("      OutsideToFirstCube: "+OutsideToFirstCube);
		   System.out.println("     OutsideToSecondCube: "+OutsideToSecondCube);
		   System.out.println("      OutsideToThirdCube: "+OutsideToThirdCube);
		   System.out.println("     OutsideToFourthCube: "+OutsideToFourthCube);
		   System.out.println("      OutsideToFifthCube: "+OutsideToFifthCube);
		   System.out.println("      OutsideToSixthCube: "+OutsideToSixthCube);
		   System.out.println("   OutsideToExchangeNear: "+OutsideToExchangeNear);
		   System.out.println("    OutsideToExchangeFar: "+OutsideToExchangeFar);
		   System.out.println("     OutsideToRobot2Near: "+OutsideToRobot2Near);
		   System.out.println("      OutsideToRobot2Far: "+OutsideToRobot2Far);
		   System.out.println("");
		   System.out.println("---------------- Command Matrix -------------");
		   System.out.println("");
		   for(int i=0;i<19;i++) {
			   for(int j=0;j<16;j++) {
				   System.out.println("From "+(i+1)+" To "+(j+4)+" -> "+commands[i][j]+" <-");
			   }
		   }

	   }
}