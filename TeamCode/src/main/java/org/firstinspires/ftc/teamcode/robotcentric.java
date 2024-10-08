package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.bosch.BNO055IMU;

@TeleOp(name = "robotcentric", group = "Linear OpMode")
public class robotcentric extends LinearOpMode {
    private GamepadEx gamepad;

    private Motor frontLeft;
    private Motor frontRight;
    private Motor backLeft;
    private Motor backRight;
    private BNO055IMU imu;
    @Override
    public void runOpMode() {
        try{
            gamepad = new GamepadEx(gamepad1);
            frontLeft = new Motor(hardwareMap, "FL");
            frontRight = new Motor(hardwareMap, "FR");
            backLeft  = new Motor(hardwareMap, "BL");
            backRight = new Motor(hardwareMap, "BR");
            frontRight.setInverted(true);
            backRight.setInverted(true);
            imu = hardwareMap.get(BNO055IMU.class, "imu");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            imu.initialize(parameters);
        }catch (Error e){
            telemetry.addData("Error: ", e.getMessage());
            telemetry.update();
        }
        waitForStart();
        while (opModeIsActive()) {
            double y = -gamepad.getLeftY();  // Forward/backward input
            double x = gamepad.getRightX();   // strafing
            double rotation = gamepad.getLeftX();  // turning
            double heading = imu.getAngularOrientation().firstAngle;
            double rotatedStrafe = (x * Math.cos(-heading)) - (y * Math.sin(-heading));
            double rotatedVerticalMovement = (x * Math.sin(-heading)) + (y * Math.cos(-heading));
            double maintainRatio = Math.max(Math.abs(rotatedVerticalMovement) + Math.abs(rotatedStrafe) + Math.abs(rotation), 1);

            frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            frontLeft.set((rotatedVerticalMovement + rotatedStrafe + rotation) / maintainRatio);
            frontRight.set((rotatedVerticalMovement - rotatedStrafe - rotation) / maintainRatio);
            backLeft.set((rotatedVerticalMovement - rotatedStrafe + rotation) / maintainRatio);
            backRight.set((rotatedVerticalMovement + rotatedStrafe - rotation) / maintainRatio);



        }
    }
}
