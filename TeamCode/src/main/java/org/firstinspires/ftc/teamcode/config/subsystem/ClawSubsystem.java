package org.firstinspires.ftc.teamcode.config.subsystem;
import static org.firstinspires.ftc.teamcode.config.util.RobotConstants.*;

import com.qualcomm.robotcore.hardware.CRServo;
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


    public CRServo intakeServo, pivot1, pivot2;
    public ClawGrabState grabState;
    public ClawPivotState pivotState;
    public RunAction release, intake, deafult, aligned;

    public ClawSubsystem(HardwareMap hardwareMap, ClawGrabState clawGrabState, ClawPivotState clawPivotState) {
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
        pivot1 = hardwareMap.get(CRServo.class, "pivot1");
        pivot2 = hardwareMap.get(CRServo.class, "pivot2");
        this.grabState = clawGrabState;
        this.pivotState = clawPivotState;

        release = new RunAction(this::release);
        intake = new RunAction(this::intake);
        deafult = new RunAction(this::deafult);
        aligned = new RunAction(this::aligned);

    }


    public void setGrabState(ClawGrabState clawGrabState) {
        if (clawGrabState == ClawGrabState.INTAKE) {
            intakeServo.setPower(clawIntake);
            this.grabState = ClawGrabState.INTAKE;
        } else if (clawGrabState == ClawGrabState.RELEASE) {
            intakeServo.setPower(clawRelease);
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
            pivot1.setPower(clawPivotDeafult);
            pivot2.setPower(clawPivotDeafult);
            this.pivotState = ClawPivotState.DEAFULT;
        } else if (state == ClawPivotState.ALIGNED) {
            pivot1.setPower(clawPivotAligned);
            pivot2.setPower(clawPivotAligned);
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