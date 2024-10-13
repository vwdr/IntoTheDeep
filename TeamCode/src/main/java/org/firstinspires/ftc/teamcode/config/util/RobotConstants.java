package org.firstinspires.ftc.teamcode.config.util;


/** Everything that we want to store globally, for example positions of servos, motors, etc. goes in here. **/
public class RobotConstants {


    /** Variables are positions for the claw servos. **/
    public static double clawClose = 0.0675;
    public static double clawOpen = 0.25;
    public static double clawPivotPerpendicular = 0;
    public static double clawPivotParallel = 1;

    /** Variables are positions for the arm. **/
    public static int armRotationCollection = 0;
    public static double armExtensionHighBasket;
    public static double armExtensionLowBasket;
    public static double armExtensionChamber;
    public static int armRotationChamber;
    public static int armRotationHighBasket;
    public static int armRotationLowBasket;

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
    public static final double limelightLensHeightInches = 4.0  ;

    // distance from the target to the floor
    public static final double goalHeightInches = 0.0;
}
