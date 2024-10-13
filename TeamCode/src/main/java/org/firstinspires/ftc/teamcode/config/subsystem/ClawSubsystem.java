package org.firstinspires.ftc.teamcode.config.subsystem;
import static org.firstinspires.ftc.teamcode.config.util.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.util.RobotConstants;
import org.firstinspires.ftc.teamcode.config.util.action.Actions;
import org.firstinspires.ftc.teamcode.config.util.action.RunAction;

public class ClawSubsystem {

    public enum ClawGrabState {
        CLOSED, OPEN
    }

    public enum ClawPivotState {
        PARALLEL, PERPENDICULAR
    }


    public Servo grab,pivot;
    public ClawGrabState grabState;
    public ClawPivotState pivotState;
    public RunAction open, close, parallel, perpendicular;

    public ClawSubsystem(HardwareMap hardwareMap, ClawGrabState clawGrabState, ClawPivotState clawPivotState) {
        grab = hardwareMap.get(Servo.class, "clawGrab");
        pivot = hardwareMap.get(Servo.class, "clawPivot");
        this.grabState = clawGrabState;
        this.pivotState = clawPivotState;

        open = new RunAction(this::open);
        close = new RunAction(this::close);
        parallel = new RunAction(this::parallel);
        perpendicular = new RunAction(this::perpendicular);

    }


    public void setGrabState(ClawGrabState clawGrabState) {
        if (clawGrabState == ClawGrabState.CLOSED) {
            grab.setPosition(clawClose);
            this.grabState = ClawGrabState.CLOSED;
        } else if (clawGrabState == ClawGrabState.OPEN) {
            grab.setPosition(clawOpen);
            this.grabState = ClawGrabState.OPEN;
        }
    }

    public void switchGrabState() {
        if (grabState == ClawGrabState.CLOSED) {
            setGrabState(ClawGrabState.OPEN);
        } else if (grabState == ClawGrabState.OPEN) {
            setGrabState(ClawGrabState.CLOSED);
        }
    }

    public void setPivotState(ClawPivotState state) {
        if (state == ClawPivotState.PARALLEL) {
            pivot.setPosition(clawPivotParallel);
            this.pivotState = ClawPivotState.PARALLEL;
        } else if (state == ClawPivotState.PERPENDICULAR) {
            pivot.setPosition(clawPivotPerpendicular);
            this.pivotState = ClawPivotState.PERPENDICULAR;
        }
    }

    public void switchPivotState() {
        if (pivotState == ClawPivotState.PARALLEL) {
            setPivotState(ClawPivotState.PERPENDICULAR);
        } else if (pivotState == ClawPivotState.PERPENDICULAR) {
            setPivotState(ClawPivotState.PARALLEL);
        }
    }


    public void open() {

        setGrabState(ClawGrabState.OPEN);
    }

    public void close() {
        setGrabState(ClawGrabState.CLOSED);
    }

    public void parallel () {
        setPivotState(ClawPivotState.PARALLEL);
    }

    public void perpendicular () {
        setPivotState(ClawPivotState.PERPENDICULAR);
    }


    public void init() {

        close();
        perpendicular();
    }

    public void start() {

        close();
        perpendicular();

    }



}