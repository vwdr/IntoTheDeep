package org.firstinspires.ftc.teamcode.config;


/** Everything that we want to store globally, for example positions of servos, motors, etc. goes in here. **/
public class RobotConstants {


    /** Variables are positions for the claw servos. **/
    public static double closedL = 0.32; //33
    public static double closedR = 0.38; //37
    public static double openL = 0.47;//.42
    public static double openR = 0.23;//.28
    public static double startClaw = 0.174;
    public static double groundClaw = 0.835; //.815
    public static double scoringClaw = 0.25;


    /**Vision Constants**/
    public static final int apriltag = 0;
    public static final int blue = 2;
    public static final int red = 3;
    public static final int yellow = 1;
    public static final int obstacleDetection = 4;

    /**Limelight Constants**/
    // how many degrees back is your limelight rotated from perfectly vertical?
    public static final double limelightMountAngleDegrees = 0;

    // distance from the center of the Limelight lens to the floor
    public static final double limelightLensHeightInches = 4.0;

    // distance from the target to the floor
    public static final double goalHeightInches = 0.0;
}
