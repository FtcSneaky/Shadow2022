package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous()
public class Gary extends LinearOpMode {
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    @Override
    public void runOpMode(){
        leftDrive = hardwareMap.get(DcMotor.class, "left_motor");
        rightDrive = hardwareMap.get(DcMotor.class, "right_motor");
        waitForStart();
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);
    }
}

