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


    public Servo grab;
    public ClawGrabState grabState;
    public RunAction open, close;

    public ClawSubsystem(HardwareMap hardwareMap, ClawGrabState clawGrabState) {
        grab = hardwareMap.get(Servo.class, "clawGrab");
        this.grabState = clawGrabState;

        open = new RunAction(this::open);
        close = new RunAction(this::close);

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

    public void open() {
        setGrabState(ClawGrabState.OPEN);
    }

    public void close() {
        setGrabState(ClawGrabState.CLOSED);
    }


    public void init() {
        close();
    }

    public void start() {
        close();

    }



}