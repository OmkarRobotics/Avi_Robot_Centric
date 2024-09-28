package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ControllerVibrate", group = "Linear OpMode")
public class robotcentric extends LinearOpMode {
    GamepadEx gamepad;

    Motor m_motor_1 = new Motor(hardwareMap, "frontLeft");
    Motor m_motor_2 = new Motor(hardwareMap, "frontRight");
    Motor m_motor_3 = new Motor(hardwareMap, "backLeft");
    Motor m_motor_4 = new Motor(hardwareMap, "backRight");
    // Invert right motors for proper movement
    // input motors exactly as shown below
    MecanumDrive mecanum = new MecanumDrive(m_motor_1, m_motor_2, m_motor_3,
            m_motor_4);
    // grab the internal DcMotor object
    DcMotor motorOne = m_motor_1.motor;
    @Override
    public void runOpMode() {
        gamepad = new GamepadEx(gamepad1);
        waitForStart();
        while (opModeIsActive()) {
            double y = -gamepad.getLeftY();  // Forward/backward input
            double x = gamepad.getLeftX();   // Strafing input
            double rotation = gamepad.getRightX();  // Turning input
            double frontRightPower = y + x + rotation;
            double frontLeftPower = (y - x - rotation); * -1
            double backLeftPower = y - x + rotation;
            double backRightPower = (y + x - rotation); * -1
            m_motor_1.set(frontLeftPower);
            m_motor_2.set(frontRightPower);
            m_motor_3.set(backLeftPower);
            m_motor_4.set(backRightPower);


        }
    }
}
