package org.usfirst.frc.team1746.robot;

public class AutonConstants {

	   public static final double DefaultDrivingSpeed = 1;  // 1 means drive at maxVelocity by default, 0.75 means drive at three-quarters of maxVelocity by default
//	   public static final double DefaultBackwardsSpeed = 1;
//	   public static final double DefaultRampRate = .25;		// The number of seconds to reach maxVelocity
//	   public static final double DefaultTurningSpeed = 0.5;
//	   public static final double DefaultElevatorSpeed = 0.3;
//	   public static final double DefaultInOutSpeed = 0.4;
	   public static final double wheelDiameter = 4;
	   public static final double ticksPerRevolution = 4000; 
	   public static final double maxVelocity = 5000;			/* The max velocity in encoder ticks per 100 milliseconds 
															(The robot needs to be able to travel this speed under all circumstances)*/	   
	   public static final double defaultAcceleration = 2 * maxVelocity;  // I think this is in encoder ticks per 100 milliseconds per second... If so a value
	   																	  //     twice maxVelocity will cause the robot to reach maxVelocity in half of a second.
	   public static final double ticksPerInch = ticksPerRevolution/(Math.PI * wheelDiameter);
//	   public static final double distanceTolerance = 0.5;			// Distance from target allowed in inches
	   public static final int velocityTolerance = 5;				// Velocity Tolerance in ticks per 100 milliseconds
	   
	   public static final double encoderTicksPer90Degrees = 5612;
	   
	   public static final int zeroVelocitiesTillDone = 2;
	   
	   /*
	    * Arcade Distances in inches
	    */
	   
	   public static final double CubeSize = 13;					// From Manual
	   
	   		// Vertical Distances	   
	   
	   public static final double WallToLine = 120;					// From Diagram
	   public static final double WallToSwitch = 140;				// From Diagram
	   public static final double WallToPlatform = 261.47;			// From Diagram
	   public static final double WallToScale = 299.65;				// From Diagram
	   
	   public static final double SwitchWidth = 56;					// From Diagram
	   public static final double ScaleWidth = 48;					// From Manual
	   
	   		// Horizontal Distances
	   
	   public static final double MiddleToAngle = 132.31;			// Field Width of 27 feet (from Manual) / 2 - rail to flat wall (from Diagram)
	   public static final double MiddleToScaleEnd = 90;			// Scale Length (from Manual) / 2
	   public static final double MiddleToSwitchEnd = 76.75;		// Switch Length (from Manual) / 2
	   public static final double ExchangeOffset = 22;				// Guess based on Diagram & Manual  (middle of field to middle of exchange)
	   
	   		// Heights
	   
	   public static final double SwitchHeight = 18.75;				// From Manual
	   public static final double ScaleAvgHeight = 60;				// From Manual
	   public static final double ScaleMaxHeight = 72;				// From Manual
	   public static final double ScaleMinHeight = 48;				// From Manual
	   
	   /*
	    * Robot Distances in inches
	    */

	   public static final double RobotWidth = 24;					// Measured from practice robot without bumpers
	   public static final double RobotLength = 24;					// Measured from practice robot without bumpers
	   public static final double BackToTurningAxis = 9;			// estimate from practice robot
	   																//     distance from the back of the robot to the axis of turning
	   							/*
	   							 * NOTE:  This code currently assumes that the axis of turning will be halfway between the left and
	   							 *        right sides of the robot and that the elevator/grabber is likewise centered !!!!
	   							 */
	   
	   public static final double PlacementBuffer = 3;				// Still undetermined so a guestimate for now
	   																//     distance from the front of the robot to the target (e.g. switch)
																	//          when preparing to place a cube
	   																// Note: may want to break this into Switch, Scale, & Exchange placement buffers
	   public static final double PickupBuffer = 3;					// Still undetermined so a guestimate for now
	   																//     distance from the front of the robot to the target (i.e. cube on floor)
	   																//          when preparing to pick up a cube

	   
	   /*
	    * Chosen Distances in inches
	    */

	   public static final double AngleToRobot = 1;					// distance from start of flat wall to edge of robot (position "1" or "3") in inches
	   																//    the "flat wall" is comprised of the three operator stations and the exchange
	   public static final double MiddleOffset = 7;					// distance from the middle of the field to the middle of the robot (position "2") in inches
	   																//    the offset is towards the side of the field that doesn't have the exchange
	   public static final double WallToLane1 = 67;					// distance from wall to the middle of the first horizontal driving lane
	   public static final double WallToLane2 = 235;				// distance from wall to the middle of the second horizontal driving lane
	   public static final double InsideSwitch = 4;					// when placing cubes on the switch from the side, how far from the end to place them
	   public static final double InsideScale = 2;					// when placing cubes on the scale from the side, how far from the end to place them
	   
	   /*
	    * 		We are currently assuming two other horizontal driving lanes at the middles of the end of the switch and of the end of the scale, and
	    *               two horizontal driving lanes for traversing the field, called "Lane1" (which is between the exchange and the switch) and
	    *               "Lane2" (which is between the switch and the scale).
	    * 
		*		We are currently assuming that vertical driving will be done in lanes which line up with the following:
	   	*				a) a robot starting position
	   	*				b) a cube placement site (side of switch or scale or middle of the exchange)
	   	*				c) a cube pickup site
	    */
	   
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
	   public static final double StartToMidScale = WallToScale + (ScaleWidth * 0.5) - BackToTurningAxis;
	   
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
	   public static final double Lane1ToLane2 = WallToLane1 - WallToLane2;
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

	   public static final double OutsideToRobot2Near = MiddleToOutsideLane - MiddleOffset;		// Distance from outside lane to the middle of the robot when its at position 2
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
	    *     A - Ahead (drive straight at the default speed for the given number of inches(Accepts override speed))
	    *     B - Backward (drive straight in reverse at the default speed for the given number of inches(Accepts override speed))
	    *         (Note: As of 2/24/18 "A-" is the same as "B" and "B-" is the same as "A" - e.g. "A-20" and "B20" both say "backup 20 inches".)
	    *     R - Right (turn right at the default speed exactly 90 degrees(Accepts override speed))
	    *     L - Left (turn left at the default speed exactly 90 degrees(Accepts override speed))
	    *     
	    *     U - Up (raise the elevator at the default speed for the given number of inches)
	    *     D - Down (lower the elevator at the default speed for the given number of inches)
	    *     
	    *     O - Output (put the cube out at the default speed)
	    *     I - Input (get a cube in at the default speed)
	    *     T - Retract (Retract the grabber arms)
	    *     E - Extend (Extend the grabber arms)
	    *     
	    *     W - Wait (Wait for all command(s) to finish)
	    *     
	    */
	   public static final String driveCommands = "ABRL";
	   public static final String elevatorCommands = "UD";
	   public static final String grabberCommands = "IOTE";
	   public static final String specialCommands = "W";
	   public static final String[][] commands = new String[][] {
		   {																															// FROM 1
			   "A"+StartToLane1+"RA"+OutsideToExchangeNear+"RA"+Lane1ToExchange,														//        TO 4
		       "!",																														//        TO 5
			   "A"+StartToLane1+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,														//        TO 6
			   "A"+StartToLane1+"RA"+OutsideToSwitchSideFar+"LA"+Lane1ToSwitch,															//        TO 7
			   "A"+StartToMidSwitch+"RA"+OutsideToSwitch,																				//        TO 8
			   "A"+StartToLane2+"RA"+OutsideToOutside+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,										//        TO 9
// Lane 1	   "A"+StartToLane1+"RA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LA"+OutsideToSwitch,										//        TO 9
			   "A"+StartToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPlacement,													//        TO 10
			   "A"+StartToLane2+"RA"+OutsideToSixthCube+"RA"+Lane2ToSwitchForPlacement,													//        TO 11
// Lane 1	   "A"+StartToLane1+"RA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPlacement,			//        TO 11
			   "A"+StartToLane2+"RA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup,													//        TO 12
			   "A"+StartToLane2+"RA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup,													//        TO 13
			   "A"+StartToLane2+"RA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup,													//        TO 14
			   "A"+StartToLane2+"RA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup,													//        TO 15
		       "A"+StartToLane2+"RA"+OutsideToScaleSideNear+"LA"+Lane2ToScale,															//        TO 16
		       "A"+StartToLane2+"RA"+OutsideToScaleSideFar+"LA"+Lane2ToScale,															//        TO 17
		       "A"+StartToMidScale+"RA"+OutsideToScale, //+"U"+ScaleHeight+"O",															//        TO 18
		       "A"+StartToLane2+"RA"+OutsideToOutside+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,											//        TO 19		       
// Lane 1      "A"+StartToLane1+"RA"+OutsideToOutside+"LA"+Lane1ToMidScale+"LA"+OutsideToScale,											//        TO 19		       
		   },
		   {																															// FROM 2
			   "A"+StartToLane1+"LA"+(ExchangeOffset+MiddleOffset)+"LA"+Lane1ToExchange,												//        TO 4
		       "!",																														//        TO 5
			   "A"+StartToLane1+"LA"+(OutsideToRobot2Far-OutsideToSwitchSideNear)+"RA"+Lane1ToSwitch,									//        TO 6
			   "A"+StartToLane1+"RA"+(OutsideToRobot2Near-OutsideToSwitchSideNear)+"LA"+Lane1ToSwitch,									//        TO 7
			   "A"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToMidSwitch+"RA"+OutsideToSwitch,										//        TO 8
			   "A"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToMidSwitch+"LA"+OutsideToSwitch,									//        TO 9
			   "A"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPlacement,		//        TO 10
			   "A"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPlacement,		//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "A"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToLane2+"RA"+OutsideToScaleSideNear+"LA"+Lane2ToScale,				//        TO 16
		       "A"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToLane2+"LA"+OutsideToScaleSideNear+"RA"+Lane2ToScale,				//        TO 17
		       "A"+StartToLane1+"LA"+OutsideToRobot2Far+"RA"+Lane1ToMidScale+"RA"+OutsideToScale,										//        TO 18
		       "A"+StartToLane1+"RA"+OutsideToRobot2Near+"LA"+Lane1ToMidScale+"LA"+OutsideToScale,										//        TO 19		       
		   },
		   {																															// FROM 3
		       "A"+StartToLane1+"LA"+OutsideToExchangeFar+"LA"+Lane1ToExchange,															//        TO 4
		       "!",																														//        TO 5
		       "A"+StartToLane1+"LA"+OutsideToSwitchSideFar+"RA"+Lane1ToSwitch,															//        TO 6
		       "A"+StartToLane1+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,														//        TO 7
			   "A"+StartToLane2+"LA"+OutsideToOutside+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,										//        TO 8
// Lane 1	   "A"+StartToLane1+"LA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RA"+OutsideToSwitch,										//        TO 8
			   "A"+StartToMidSwitch+"LA"+OutsideToSwitch,																				//        TO 9
			   "A"+StartToLane2+"LA"+OutsideToSixthCube+"LA"+Lane2ToSwitchForPickup,													//        TO 10
// Lane 1	   "A"+StartToLane1+"LA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPlacement,			//        TO 10
			   "A"+StartToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,													//        TO 11
			   "A"+StartToLane2+"LA"+OutsideToFifthCube+"LA"+Lane2ToSwitchForPickup,													//        TO 12
			   "A"+StartToLane2+"LA"+OutsideToSecondCube+"LA"+Lane2ToSwitchForPickup,													//        TO 13
			   "A"+StartToLane2+"LA"+OutsideToFourthCube+"LA"+Lane2ToSwitchForPickup,													//        TO 14
			   "A"+StartToLane2+"LA"+OutsideToThirdCube+"LA"+Lane2ToSwitchForPickup,													//        TO 15
			   "A"+StartToLane2+"LA"+OutsideToScaleSideFar+"RA"+Lane2ToScale,															//        TO 16
			   "A"+StartToLane2+"LA"+OutsideToScaleSideNear+"RA"+Lane2ToScale,															//        TO 17
		       "A"+StartToLane2+"LA"+OutsideToOutside+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,											//        TO 18
// Lane 1      "A"+StartToLane1+"LA"+OutsideToOutside+"RA"+Lane1ToMidScale+"RA"+OutsideToScale,											//        TO 18		       
		       "A"+StartToMidScale+"LA"+OutsideToScale,																					//        TO 19		       
		   },
		   {																															// FROM 4
			   "!",																														//        TO 4
		       "B"+Lane1ToExchange+"LA"+ExchangeOffset+"LA"+Lane1ToCube,																//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup,	//        TO 10
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToSixthCube+"RA"+Lane2ToSwitchForPickup,	//        TO 11
// Lane 1      "B"+Lane1ToExchange+"LA"+OutsideToExchangeFar+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,		//        TO 11
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup,	//        TO 12
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup,	//        TO 13
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup,	//        TO 14
			   "B"+Lane1ToExchange+"RA"+OutsideToExchangeNear+"RA"+Lane1ToLane2+"RA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup,	//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 5
			   "B"+Lane1ToCube+"LA"+ExchangeOffset+"LA"+Lane1ToExchange,																//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane1ToCube+"LA"+(OutsideToMiddle-OutsideToSwitchSideNear)+"RA"+Lane1ToSwitch,										//        TO 6
		       "B"+Lane1ToCube+"RA"+(OutsideToMiddle-OutsideToSwitchSideNear)+"LA"+Lane1ToSwitch,										//        TO 7
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToMidSwitch+"RA"+OutsideToSwitch,											//        TO 8
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToMidSwitch+"LA"+OutsideToSwitch,											//        TO 9
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPlacement,			//        TO 10
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPlacement,			//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToLane2+"RA"+OutsideToScaleSideNear+"LA"+Lane2ToScale,					//        TO 16
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToLane2+"LA"+OutsideToScaleSideNear+"RA"+Lane2ToScale,					//        TO 17
		       "B"+Lane1ToCube+"LA"+OutsideToMiddle+"RA"+Lane1ToMidScale+"RA"+OutsideToScale,											//        TO 18
		       "B"+Lane1ToCube+"RA"+OutsideToMiddle+"LA"+Lane1ToMidScale+"LA"+OutsideToScale,											//        TO 19		       
		   },
		   {																															// FROM 6
			   "!",																														//        TO 4
		       "B"+Lane1ToSwitch+"RA"+(OutsideToMiddle-OutsideToSwitchSideNear)+"LA"+Lane1ToCube,										//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup,	//        TO 10
			   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToSixthCube+"RA"+Lane2ToSwitchForPickup,	//        TO 11
// Lane 1	   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideFar+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,		//        TO 11
			   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup,	//        TO 12
			   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup,	//        TO 13
			   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup,	//        TO 14
			   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup,	//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 7
			   "!",																														//        TO 4
		       "B"+Lane1ToSwitch+"LA"+(OutsideToMiddle-OutsideToSwitchSideNear)+"RA"+Lane1ToCube,										//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToSixthCube+"LA"+Lane2ToSwitchForPickup,	//        TO 10
// Lane 1	   "B"+Lane1ToSwitch+"LA"+OutsideToSwitchSideFar+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup,		//        TO 10
			   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,	//        TO 11
			   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToFifthCube+"LA"+Lane2ToSwitchForPickup,	//        TO 12
			   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToSecondCube+"LA"+Lane2ToSwitchForPickup,	//        TO 13
			   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToFourthCube+"LA"+Lane2ToSwitchForPickup,	//        TO 14
			   "B"+Lane1ToSwitch+"RA"+OutsideToSwitchSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToThirdCube+"LA"+Lane2ToSwitchForPickup,	//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 8
			   "!",																														//        TO 4
		       "B"+OutsideToSwitch+"RA"+Lane1ToMidSwitch+"LA"+OutsideToMiddle+"LA"+Lane1ToCube,											//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+OutsideToSwitch+"LA"+Lane2ToMidSwitch+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup,							//        TO 10
			   "B"+OutsideToSwitch+"LA"+Lane2ToMidSwitch+"RA"+OutsideToSixthCube+"RA"+Lane2ToSwitchForPickup,							//        TO 11
// Lane 1	   "B"+OutsideToSwitch+"RA"+Lane1ToMidSwitch+"LA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup, // TO 11
			   "B"+OutsideToSwitch+"LA"+Lane2ToMidSwitch+"RA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup,							//        TO 12
			   "B"+OutsideToSwitch+"LA"+Lane2ToMidSwitch+"RA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup,							//        TO 13
			   "B"+OutsideToSwitch+"LA"+Lane2ToMidSwitch+"RA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup,							//        TO 14
			   "B"+OutsideToSwitch+"LA"+Lane2ToMidSwitch+"RA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup,							//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 9
			   "!",																														//        TO 4
		       "B"+OutsideToSwitch+"LA"+Lane1ToMidSwitch+"RA"+OutsideToMiddle+"RA"+Lane1ToCube,											//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+OutsideToSwitch+"RA"+Lane2ToMidSwitch+"LA"+OutsideToSixthCube+"LA"+Lane2ToSwitchForPickup,							//        TO 10
// Lane 1	   "B"+OutsideToSwitch+"LA"+Lane1ToMidSwitch+"RA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup, // TO 10
			   "B"+OutsideToSwitch+"RA"+Lane2ToMidSwitch+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,							//        TO 11
			   "B"+OutsideToSwitch+"RA"+Lane2ToMidSwitch+"LA"+OutsideToFifthCube+"LA"+Lane2ToSwitchForPickup,							//        TO 12
			   "B"+OutsideToSwitch+"RA"+Lane2ToMidSwitch+"LA"+OutsideToSecondCube+"LA"+Lane2ToSwitchForPickup,							//        TO 13
			   "B"+OutsideToSwitch+"RA"+Lane2ToMidSwitch+"LA"+OutsideToFourthCube+"LA"+Lane2ToSwitchForPickup,							//        TO 14
			   "B"+OutsideToSwitch+"RA"+Lane2ToMidSwitch+"LA"+OutsideToThirdCube+"LA"+Lane2ToSwitchForPickup,							//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 10
			   "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToExchangeNear+"RA"+Lane1ToExchange,	//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,	//        TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSixthCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RA"+Lane1ToSwitch,	//        TO 7
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideFar+"LA"+Lane1ToSwitch,		//        TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,							//        TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSixthCube+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,							//        TO 9
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LA"+OutsideToSwitch, // TO 9
			   "!",																														//        TO 10
		       "!",																														//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToFirstCube-OutsideToScaleSideNear)+"RA"+Lane2ToScale,							//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToSixthCube-OutsideToScaleSideNear)+"LA"+Lane2ToScale,							//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFirstCube+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,								//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSixthCube+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,								//        TO 19		       
		   },
		   {																															// FROM 11
			   "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToExchangeFar+"LA"+Lane1ToExchange,		//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSixthCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,	//        TO 6
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideFar+"RA"+Lane1ToSwitch,		//        TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RA"+Lane1ToSwitch,	//        TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSixthCube+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,							//        TO 8
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RA"+OutsideToSwitch, // TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,							//        TO 9
			   "!",																														//        TO 10
		       "!",																														//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToSixthCube-OutsideToScaleSideNear)+"RA"+Lane2ToScale,							//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToFirstCube-OutsideToScaleSideNear)+"LA"+Lane2ToScale,							//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSixthCube+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,								//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFirstCube+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,								//        TO 19		       
		   },
		   {																															// FROM 12
			   "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToExchangeNear+"RA"+Lane1ToExchange,	//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,	//        TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RA"+Lane1ToSwitch,	//        TO 7
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideFar+"LA"+Lane1ToSwitch,	//        TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,							//        TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,							//        TO 9
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LA"+OutsideToSwitch, // TO 9
			   "!",																														//        TO 10
		       "!",																														//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToSecondCube-OutsideToScaleSideNear)+"RA"+Lane2ToScale,							//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToFifthCube-OutsideToScaleSideNear)+"LA"+Lane2ToScale,							//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToSecondCube+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,							//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFifthCube+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,								//        TO 19		       
		   },
		   {																															// FROM 13
			   "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToExchangeFar+"LA"+Lane1ToExchange,	//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFifthCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,	//        TO 6
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideFar+"RA"+Lane1ToSwitch,	//        TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RA"+Lane1ToSwitch,	//        TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFifthCube+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,							//        TO 8
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RA"+OutsideToSwitch, // TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,							//        TO 9
			   "!",																														//        TO 10
		       "!",																														//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToFifthCube-OutsideToScaleSideNear)+"RA"+Lane2ToScale,							//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToSecondCube-OutsideToScaleSideNear)+"LA"+Lane2ToScale,							//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFifthCube+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,								//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToSecondCube+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,							//        TO 19		       
		   },
		   {																															// FROM 14
			   "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToExchangeNear+"RA"+Lane1ToExchange,	//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,	//        TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFourthCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RA"+Lane1ToSwitch,	//        TO 7
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideFar+"LA"+Lane1ToSwitch,		//        TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,							//        TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFourthCube+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,							//        TO 9
// Lane 1      "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToMidSwitch+"LA"+OutsideToSwitch, // TO 9
			   "!",																														//        TO 10
		       "!",																														//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToThirdCube-OutsideToScaleSideNear)+"RA"+Lane2ToScale,							//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToFourthCube-OutsideToScaleSideNear)+"LA"+Lane2ToScale,							//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToThirdCube+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,								//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToFourthCube+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,							//        TO 19		       
		   },
		   {																															// FROM 15
			   "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToExchangeFar+"LA"+Lane1ToExchange,		//        TO 4
		       "!",																														//        TO 5
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFourthCube+"LA"+Lane1ToLane2+"LA"+OutsideToSwitchSideNear+"LA"+Lane1ToSwitch,	//        TO 6
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideFar+"RA"+Lane1ToSwitch,		//        TO 6
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToSwitchSideNear+"RA"+Lane1ToSwitch,	//        TO 7
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFourthCube+"LA"+Lane2ToMidSwitch+"LA"+OutsideToSwitch,							//        TO 8
// Lane 1      "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToMidSwitch+"RA"+OutsideToSwitch, // TO 8
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"RA"+Lane2ToMidSwitch+"RA"+OutsideToSwitch,							//        TO 9
			   "!",																														//        TO 10
		       "!",																														//        TO 11
		       "!",																														//        TO 12
		       "!",																														//        TO 13
		       "!",																														//        TO 14
		       "!",																														//        TO 15
		       "B"+Lane2ToSwitchForPickup+"RA"+(OutsideToFourthCube-OutsideToScaleSideNear)+"RA"+Lane2ToScale,							//        TO 16
		       "B"+Lane2ToSwitchForPickup+"LA"+(OutsideToThirdCube-OutsideToScaleSideNear)+"LA"+Lane2ToScale,							//        TO 17
		       "B"+Lane2ToSwitchForPickup+"RA"+OutsideToFourthCube+"RA"+Lane2ToMidScale+"RA"+OutsideToScale,							//        TO 18
		       "B"+Lane2ToSwitchForPickup+"LA"+OutsideToThirdCube+"LA"+Lane2ToMidScale+"LA"+OutsideToScale,								//        TO 19		       
		   },
		   {																															// FROM 16
			   "!",																														//        TO 4
		       "B"+Lane2ToScale+"LA"+OutsideToScaleSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToMiddle+"LA"+Lane1ToCube,					//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+Lane2ToScale+"RA"+(OutsideToFirstCube-OutsideToScaleSideNear)+"RA"+Lane2ToSwitchForPickup,							//        TO 10
			   "B"+Lane2ToScale+"RA"+(OutsideToSixthCube-OutsideToScaleSideNear)+"RA"+Lane2ToSwitchForPickup,							//        TO 11
// Lane 1	   "B"+Lane2ToScale+"LA"+OutsideToScaleSideNear+"LA"+Lane1ToLane2+"LA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup, // TO 11
			   "B"+Lane2ToScale+"RA"+(OutsideToSecondCube-OutsideToScaleSideNear)+"RA"+Lane2ToSwitchForPickup,							//        TO 12
			   "B"+Lane2ToScale+"RA"+(OutsideToFifthCube-OutsideToScaleSideNear)+"RA"+Lane2ToSwitchForPickup,							//        TO 13
			   "B"+Lane2ToScale+"RA"+(OutsideToThirdCube-OutsideToScaleSideNear)+"RA"+Lane2ToSwitchForPickup,							//        TO 14
			   "B"+Lane2ToScale+"RA"+(OutsideToFourthCube-OutsideToScaleSideNear)+"RA"+Lane2ToSwitchForPickup,							//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 17
			   "!",																														//        TO 4
		       "B"+Lane2ToScale+"RA"+OutsideToScaleSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToMiddle+"RA"+Lane1ToCube,					//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+Lane2ToScale+"LA"+(OutsideToSixthCube-OutsideToScaleSideNear)+"LA"+Lane2ToSwitchForPickup,							//        TO 10
// Lane 1	   "B"+Lane2ToScale+"RA"+OutsideToScaleSideNear+"RA"+Lane1ToLane2+"RA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup, // TO 10
			   "B"+Lane2ToScale+"LA"+(OutsideToFirstCube-OutsideToScaleSideNear)+"LA"+Lane2ToSwitchForPickup,							//        TO 11
			   "B"+Lane2ToScale+"LA"+(OutsideToFifthCube-OutsideToScaleSideNear)+"LA"+Lane2ToSwitchForPickup,							//        TO 12
			   "B"+Lane2ToScale+"LA"+(OutsideToSecondCube-OutsideToScaleSideNear)+"LA"+Lane2ToSwitchForPickup,							//        TO 13
			   "B"+Lane2ToScale+"LA"+(OutsideToFourthCube-OutsideToScaleSideNear)+"LA"+Lane2ToSwitchForPickup,							//        TO 14
			   "B"+Lane2ToScale+"LA"+(OutsideToThirdCube-OutsideToScaleSideNear)+"LA"+Lane2ToSwitchForPickup,							//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 18
			   "!",																														//        TO 4
		       "B"+OutsideToScale+"RA"+Lane1ToMidScale+"LA"+OutsideToMiddle+"LA"+Lane1ToCube,											//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+OutsideToScale+"RA"+Lane2ToMidScale+"LA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup,								//        TO 10
			   "B"+OutsideToScale+"RA"+Lane2ToMidScale+"LA"+OutsideToSixthCube+"RA"+Lane2ToSwitchForPickup,								//        TO 11
// Lane 1	   "B"+OutsideToScale+"RA"+Lane1ToMidScale+"LA"+OutsideToOutside+"LA"+Lane1ToLane2+"LA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,	// TO 11
			   "B"+OutsideToScale+"RA"+Lane2ToMidScale+"LA"+OutsideToSecondCube+"RA"+Lane2ToSwitchForPickup,							//        TO 12
			   "B"+OutsideToScale+"RA"+Lane2ToMidScale+"LA"+OutsideToFifthCube+"RA"+Lane2ToSwitchForPickup,								//        TO 13
			   "B"+OutsideToScale+"RA"+Lane2ToMidScale+"LA"+OutsideToThirdCube+"RA"+Lane2ToSwitchForPickup,								//        TO 14
			   "B"+OutsideToScale+"RA"+Lane2ToMidScale+"LA"+OutsideToFourthCube+"RA"+Lane2ToSwitchForPickup,							//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
		   {																															// FROM 19
			   "!",																														//        TO 4
		       "B"+OutsideToScale+"LA"+Lane1ToMidScale+"RA"+OutsideToMiddle+"RA"+Lane1ToCube,											//        TO 5
			   "!",																														//        TO 6
			   "!",																														//        TO 7
			   "!",																														//        TO 8
			   "!",																														//        TO 9
			   "B"+OutsideToScale+"LA"+Lane2ToMidScale+"RA"+OutsideToSixthCube+"LA"+Lane2ToSwitchForPickup,								//        TO 10
// Lane 1	   "B"+OutsideToScale+"LA"+Lane1ToMidScale+"RA"+OutsideToOutside+"RA"+Lane1ToLane2+"RA"+OutsideToFirstCube+"RA"+Lane2ToSwitchForPickup,	// TO 10
			   "B"+OutsideToScale+"LA"+Lane2ToMidScale+"RA"+OutsideToFirstCube+"LA"+Lane2ToSwitchForPickup,								//        TO 11
			   "B"+OutsideToScale+"LA"+Lane2ToMidScale+"RA"+OutsideToFifthCube+"LA"+Lane2ToSwitchForPickup,								//        TO 12
			   "B"+OutsideToScale+"LA"+Lane2ToMidScale+"RA"+OutsideToSecondCube+"LA"+Lane2ToSwitchForPickup,							//        TO 13
			   "B"+OutsideToScale+"LA"+Lane2ToMidScale+"RA"+OutsideToFourthCube+"LA"+Lane2ToSwitchForPickup,							//        TO 14
			   "B"+OutsideToScale+"LA"+Lane2ToMidScale+"RA"+OutsideToThirdCube+"LA"+Lane2ToSwitchForPickup,								//        TO 15
		       "!",																														//        TO 16
		       "!",																														//        TO 17
		       "!",																														//        TO 18
		       "!",																														//        TO 19		       
		   },
	   };
	   
}
