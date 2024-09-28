package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "robotcentric", group = "Linear OpMode")
public class robotcentric extends LinearOpMode {
    private GamepadEx gamepad;

    private Motor frontLeft;
    private Motor frontRight;
    private Motor backLeft;
    private Motor backRight;
    @Override
    public void runOpMode() {
        try{
            gamepad = new GamepadEx(gamepad1);
            frontLeft = new Motor(hardwareMap, "FL");
            frontRight = new Motor(hardwareMap, "FR");
            backLeft  = new Motor(hardwareMap, "BL");
            backRight = new Motor(hardwareMap, "BR");
        }catch (Error e){
            telemetry.addData("Error: ", e.getMessage());
            telemetry.update();
        }
        waitForStart();
        while (opModeIsActive()) {
            double y = -gamepad.getLeftY();  // Forward/backward input
            double x = gamepad.getRightX();   // strafing
            double rotation = gamepad.getLeftX();  // turning
            double frontRightPower = (y + x + rotation) *-1;
            double frontLeftPower = (y - x - rotation);
            double backLeftPower = (y - x + rotation);
            double backRightPower = (y + x - rotation) *-1;
            frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            frontLeft.set(frontLeftPower);
            frontRight.set(frontRightPower);
            backLeft.set(backLeftPower);
            backRight.set(backRightPower);



        }
    }
}
