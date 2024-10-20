package org.firstinspires.ftc.teamcode.config.subsystem;
import static org.firstinspires.ftc.teamcode.config.util.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.util.RobotConstants;
import org.firstinspires.ftc.teamcode.config.util.action.Actions;
import org.firstinspires.ftc.teamcode.config.util.action.RunAction;

public class ClawSubsystem {

    public enum ClawGrabState {
        INTAKE, RELEASE
    }

    public enum ClawPivotState {
        DEAFULT, ALIGNED //deafult: parallel to ground/starting position, aligned: aligned with the arm
    }


    public Servo roll1,roll2, pivot1, pivot2;
    public ClawGrabState grabState;
    public ClawPivotState pivotState;
    public RunAction release, intake, deafult, aligned;

    public ClawSubsystem(HardwareMap hardwareMap, ClawGrabState clawGrabState, ClawPivotState clawPivotState) {
        roll1 = hardwareMap.get(Servo.class, "activeIntake1");
        roll2 = hardwareMap.get(Servo.class, "activeIntake2");
        pivot1 = hardwareMap.get(Servo.class, "pivot1");
        pivot2 = hardwareMap.get(Servo.class, "pivot2");
        this.grabState = clawGrabState;
        this.pivotState = clawPivotState;

        release = new RunAction(this::release);
        intake = new RunAction(this::intake);
        deafult = new RunAction(this::deafult);
        aligned = new RunAction(this::aligned);

    }


    public void setGrabState(ClawGrabState clawGrabState) {
        if (clawGrabState == ClawGrabState.INTAKE) {
            roll1.setPosition(clawIntake);
            roll2.setPosition(clawIntake);
            this.grabState = ClawGrabState.INTAKE;
        } else if (clawGrabState == ClawGrabState.RELEASE) {
            roll1.setPosition(clawRelease);
            roll2.setPosition(clawRelease);
            this.grabState = ClawGrabState.RELEASE;
        }
    }

    public void switchGrabState() {
        if (grabState == ClawGrabState.INTAKE) {
            setGrabState(ClawGrabState.RELEASE);
        } else if (grabState == ClawGrabState.RELEASE) {
            setGrabState(ClawGrabState.INTAKE);
        }
    }

    public void setPivotState(ClawPivotState state) {
        if (state == ClawPivotState.DEAFULT) {
            pivot1.setPosition(clawPivotDeafult);
            pivot2.setPosition(clawPivotDeafult);
            this.pivotState = ClawPivotState.DEAFULT;
        } else if (state == ClawPivotState.ALIGNED) {
            pivot1.setPosition(clawPivotAligned);
            pivot2.setPosition(clawPivotAligned);
            this.pivotState = ClawPivotState.ALIGNED;
        }
    }

    public void switchPivotState() {
        if (pivotState == ClawPivotState.DEAFULT) {
            setPivotState(ClawPivotState.ALIGNED);
        } else if (pivotState == ClawPivotState.ALIGNED) {
            setPivotState(ClawPivotState.DEAFULT);
        }
    }


    public void release() {

        setGrabState(ClawGrabState.RELEASE);
    }

    public void intake() {

        setGrabState(ClawGrabState.INTAKE);
    }

    public void deafult () {

        setPivotState(ClawPivotState.DEAFULT);
    }

    public void aligned () {
        setPivotState(ClawPivotState.ALIGNED);
    }


    public void init() {

        intake();
        deafult();
    }

    public void start() {

        intake();
        deafult();

    }



}