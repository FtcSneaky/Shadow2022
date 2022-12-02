package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class SimpleAuton extends LinearOpMode {
    private int zone;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo clawMotor = null;
    double position;
    private DcMotor elevatorMotor = null;
    private ColorSensor colorSensor = null;
    private DistanceSensor distanceSensor = null;

    @Override
    public void runOpMode() {
        String color;
        telemetry.addData("POS", position);
        leftDrive = hardwareMap.get(DcMotor.class, "left_motor");
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive = hardwareMap.get(DcMotor.class, "right_motor");
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawMotor = hardwareMap.get(Servo.class, "Claw motor");
        position = 0;
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevator_motor");
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "color_sensor");

        waitForStart();

        while (opModeIsActive()){
            position = 0;
            clawMotor.setPosition(position);

            while(distanceSensor.getDistance(DistanceUnit.CM) > 3){
                leftDrive.setPower (-0.05);
                rightDrive.setPower (0.05);
            }

            color = getColor();

            telemetry.addData("Color:", getColor());
            telemetry.update();

            if (color.equals("red")){
                goForward();
                turnLeft();
                move(800,-0.25, 0.25);

            }
            else if(color.equals("green")){
                goForward();

            }else if(color.equals("blue")){
                goForward();
                turnRight();
                move(700,-0.25, 0.25);

            }

            while(true){
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }
            //goForward();
            //turnLeft();
            //goForward();
            //turnRight();

        }
    }

    /*
    //Dear Future Bobby, I'm sorry I wrote this bad code. Please change it. Love, Past Bobby
    @Override
    public void loop() {

        String currentColor = getColor();
        currentColor = "red";
        if (currentColor.equals("red")) {
            goForward();
            turnLeft();
            goForward();
        } else if (currentColor.equals("green")) {
            goForward();
            goForward();
        } else if (currentColor.equals("blue")) {
            goForward();
            turnRight();
            goForward();
        } else {
            telemetry.addData("Color Sensor Status: ", "Failed to read color sensor");
            telemetry.update();
        }

        //Infinite Loop so we don't repeat ourselves.
        while (true) {
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }

    }

     */


    private void turnRight() {
        move(500, -0.25, -0.25);
    }

    private void turnLeft() {
        move(360, 0.25, 0.25);
    }

    private void goForward(){
        move(500,-0.25, 0.25);
    }


    private void move(double distance, double leftPower, double rightPower){
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double pos1 = leftDrive.getCurrentPosition();
        double pos2 = 0;

        telemetry.addData("Status: ", "Going Forward");
        telemetry.update();
        while (Math.abs(Math.abs(pos2) - Math.abs(pos1)) < distance) {
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            pos2 = leftDrive.getCurrentPosition();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);}



    private String getColor() {

        if ((colorSensor.red() > colorSensor.green()) && (colorSensor.red() > colorSensor.blue())) {
            return "red";
        } else if ((colorSensor.green() > colorSensor.blue()) && (colorSensor.green() > colorSensor.red())) {
            return "green";
        } else if ((colorSensor.blue() > colorSensor.green()) && (colorSensor.blue() > colorSensor.red())) {
            return "blue";
        } else {
            return "red";
        }
    }
}
