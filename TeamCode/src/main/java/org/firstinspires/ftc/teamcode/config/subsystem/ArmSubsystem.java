
package org.firstinspires.ftc.teamcode.config.subsystem;

import static org.firstinspires.ftc.teamcode.config.util.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.util.FieldConstants;
import org.firstinspires.ftc.teamcode.config.util.RobotConstants;
import org.firstinspires.ftc.teamcode.config.util.action.RunAction;
import org.java_websocket.extensions.IExtension;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.config.subsystem.VisionSubsystem;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.PoseUpdater;

import java.lang.reflect.Field;


public class ArmSubsystem {

    public enum ArmState {
        HIGHBASKET, LOWBASKET, COLLECTION, CHAMBER, DEFAULT
    }

    double TICKS_PER_REVOLUTION = 537.6; // Example TPR for GoBILDA motor
    double INCHES_PER_REVOLUTION = 0.31496; // Example distance moved per revolution (e.g., lead screw pitch)
    double degreesPerRev = 360.0; // 1 revolution = 360 degrees
    public ArmState state;
    public RunAction toBlueHighBasket, toBlueLowBasket, toBlueChamber, toRedHighBasket, toRedLowBasket, toRedChamber, toCollection, toDefault;
    private DcMotorEx rotation, extension;
    private VisionSubsystem visionSubsystem;
    public Pose pose;



    public ArmSubsystem(HardwareMap hardwareMap, ArmState state) {
        rotation = hardwareMap.get(DcMotorEx.class, "rotation");
        extension = hardwareMap.get(DcMotorEx.class, "extension");


        rotation.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        extension.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        rotation.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        extension.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


        this.state = state;

        toBlueHighBasket = new RunAction(this::toBlueHighBasket);
        toBlueLowBasket = new RunAction(this::toBlueLowBasket);
        toBlueChamber = new RunAction(this::toBlueChamber);
        toRedHighBasket = new RunAction(this::toRedHighBasket);
        toRedLowBasket = new RunAction(this::toRedLowBasket);
        toRedChamber= new RunAction(this::toRedChamber);
        toCollection = new RunAction(this::toCollection);
        toDefault = new RunAction(this::toDefault);

    }

    // State //
    public void setState(ArmState armState, boolean isBlue) {
        switch (armState) {
            case HIGHBASKET:
                armCalculator(armState, isBlue);
                moveExtensionToPosition(armExtensionHighBasket);
                moveRotationToPosition(armRotationHighBasket);
                this.state = ArmState.HIGHBASKET;
                break;
            case LOWBASKET:
                armCalculator(armState, isBlue);
                moveExtensionToPosition(armExtensionLowBasket);
                moveRotationToPosition(armRotationLowBasket);
                this.state = ArmState.LOWBASKET;
                break;
            case CHAMBER:
                moveExtensionToPosition(armExtensionChamber);
                moveRotationToPosition(armRotationChamber);
                this.state = ArmState.CHAMBER;
                break;
            case COLLECTION:
                moveRotationToPosition(armRotationCollection);
                moveExtensionToPosition(visionSubsystem.distanceCalc());
                this.state = ArmState.COLLECTION;
                break;
            case DEFAULT:
                moveRotationToPosition(0);
                moveExtensionToPosition(0);
                this.state = ArmState.DEFAULT;
                break;
        }
    }


    // Method to move the rotation using encoders
    public void moveRotationToPosition(int position) {
        rotation.setTargetPosition(position);
        rotation.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rotation.setPower(0.5);
    }

    public void armCalculator(ArmState armState, boolean isBlue) {
        // Get the robot's current pose (position) on the field


        double botX = pose.getX();
        double botY = pose.getY();

        double basketX = isBlue ? FieldConstants.blueBucketPose.getX() : FieldConstants.redBucketPose.getX();
        double basketY = isBlue ? FieldConstants.blueBucketPose.getY() : FieldConstants.redBucketPose.getY();
        double chamberX = isBlue ?  48.0 : 96.0; //48 = x coordinate of blue chamber, 96 coordinate of red chamber

        double height;

        double horizontalDistance = Math.sqrt(Math.pow(botX - basketX, 2) + Math.pow(botY - basketY, 2));
        double horizontalChamberDistance = Math.sqrt(Math.pow(botX - chamberX, 2) + Math.pow(botY, 2));

        switch(armState) {
            case HIGHBASKET:
                height = FieldConstants.highBucketHeight;
                armExtensionHighBasket = Math.sqrt(Math.pow(horizontalDistance, 2) + Math.pow(height, 2));
                armRotationHighBasket = angleToTicks(Math.toDegrees(Math.atan2(height, horizontalDistance)));
                break;
            case LOWBASKET:
                height = FieldConstants.lowBucketHeight;
                armExtensionLowBasket = Math.sqrt(Math.pow(horizontalDistance, 2) + Math.pow(height, 2));
                armRotationHighBasket = angleToTicks(Math.toDegrees(Math.atan2(height, horizontalDistance)));
                break;
            case CHAMBER:
                height = FieldConstants.topChamberHeight;
                armExtensionChamber = Math.sqrt(Math.pow(horizontalChamberDistance, 2) + Math.pow(height, 2));
                armRotationChamber = angleToTicks(Math.toDegrees(Math.atan2(height, horizontalChamberDistance)));
                break;
        }
    }

    public int angleToTicks (double angleDegrees) {
        double ticksPerDegree = TICKS_PER_REVOLUTION / degreesPerRev;
        return (int) (angleDegrees * ticksPerDegree);
    }

    // Method to move the extension using encoders
    public void moveExtensionToPosition(double inches) {
        int targetTicks = (int) (inches / INCHES_PER_REVOLUTION * TICKS_PER_REVOLUTION);

        extension.setTargetPosition(targetTicks);
        extension.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        extension.setPower(0.5); // Adjust power based on your requirements
    }

    // Preset //

    public void toBlueHighBasket() {
        setState(ArmState.HIGHBASKET,true);
    }

    public void toBlueLowBasket() {
        setState(ArmState.LOWBASKET,true);
    }

    public void toBlueChamber() {
        setState(ArmState.CHAMBER,true);
    }

    public void toRedHighBasket() {
        setState(ArmState.HIGHBASKET,false);
    }

    public void toRedLowBasket() {
        setState(ArmState.LOWBASKET,false);
    }

    public void toRedChamber() {
        setState(ArmState.CHAMBER,false);
    }

    public void toCollection() {
        setState(ArmState.COLLECTION,true);
    }
    public void toDefault() {
        setState(ArmState.DEFAULT, true);
    }

    // Util //
    public void setPos(int rotation, double extension) {
        moveRotationToPosition(rotation);
        moveExtensionToPosition(extension);
    }

    // Init + Start //
    public void init() {
        toCollection();
    }

    public void start() {
        toCollection();
    }

}
