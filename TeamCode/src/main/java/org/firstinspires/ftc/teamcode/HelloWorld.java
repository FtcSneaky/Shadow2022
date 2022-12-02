package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class HelloWorld extends OpMode {
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo clawMotor = null;
    double position;
    private DcMotor elevatorMotor = null;
    private DistanceSensor distanceSensor = null;

    @Override
    public void init() {
        telemetry.addData("POS",position);
        leftDrive = hardwareMap.get(DcMotor.class, "left_motor");
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive = hardwareMap.get(DcMotor.class, "right_motor");
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawMotor = hardwareMap.get(Servo.class, "Claw motor");
        position = 0;
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevator_motor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "color_sensor");

    }

    @Override
    public void loop(){
        double pos;
        double leftTrigger;
        double rightTrigger;
        double leftPower;
        leftPower = gamepad1.left_stick_y;
        double rightPower;
        rightPower = -gamepad1.right_stick_y;
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        if (gamepad2.a) {
            position = 0.25;
            clawMotor.setPosition(position);
        }
        if (gamepad2.b) {
            position = 0;
            clawMotor.setPosition(position);
        }
        leftTrigger = gamepad2.left_trigger;
        rightTrigger = gamepad2.right_trigger;

        //Check for bottom limit
        pos = elevatorMotor.getCurrentPosition();

        telemetry.addData("Left Trigger", leftTrigger);
        telemetry.addData("Right Trigger", rightTrigger);
        telemetry.update();

        if(pos >= 0){ //It is too low
            leftTrigger = 0;
        }
        else {
            leftTrigger = gamepad2.left_trigger;
        }

        if(pos <= -10850){
            rightTrigger = 0;
        }
        else {
            rightTrigger = gamepad2.right_trigger;
        }


        elevatorMotor.setPower(leftTrigger - rightTrigger);

        telemetry.addData("Starting at", pos);
        telemetry.update();


    }
}


