package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AndroidStudioTestJavaOpMode extends LinearOpMode {
    private DcMotor motor = null;
    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotor.class, "testMotor");

        telemetry.addData("Status", "Intialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Status","Running");
            telemetry.update();
            motor.setPower(1.0);
        }

    }
}
