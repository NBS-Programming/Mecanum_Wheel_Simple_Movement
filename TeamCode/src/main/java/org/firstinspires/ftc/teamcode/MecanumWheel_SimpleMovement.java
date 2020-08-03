/*
Copyright 2019 FIRST Tech Challenge Team 11943

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp

public class MecanumWheel_SimpleMovement extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;

    @Override
    public void runOpMode(){
        //correctly mapped in config Hub motor port 1
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        //correctly mapped in config Hub motor port 2
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        //correctly mapped in config Hub motor port 3
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        //correctly mapped in config Hub motor port 0
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();

        double tgtPowerFront = 0;
        double tgtPowerBack = 0;
        double tgtPowerRightMvmtYaxis;
        double tgtPowerLeftMvmtYaxis;
        double tgtPowerRightMvmtXaxis;
        double tgtPowerLeftMvmtXaxis;

        while (opModeIsActive()){
            //stores the y axis input on the left gamepad
            tgtPowerLeftMvmtYaxis = this.gamepad1.left_stick_y;
            //stores the y axis input on the right gamepad
            tgtPowerRightMvmtYaxis = this.gamepad1.right_stick_y;

            //stores the x axis input on the left gamepad
            tgtPowerLeftMvmtXaxis = this.gamepad1.left_stick_x;
            //stores the x axis input on the right gamepad
            tgtPowerRightMvmtXaxis = this.gamepad1.right_stick_x;

            //pressing fwd on the left gamepad gives a value between 0 and -1
            if (tgtPowerLeftMvmtYaxis <= 0) {
                //no need to invert the negative value from the gamepad and using that value to power the motor
                frontLeft.setPower(tgtPowerLeftMvmtYaxis);
                backLeft.setPower(tgtPowerLeftMvmtYaxis);
            } else if (tgtPowerLeftMvmtYaxis > 0){
                frontLeft.setPower(tgtPowerLeftMvmtYaxis);
                backLeft.setPower(tgtPowerLeftMvmtYaxis);
            }

            //pressing fwd on the right gamepad gives a value between 0 and -1
            //we have it set to less the or equal to (<=)so that the wheels will stop when the gampad is set to 0)
            if (tgtPowerRightMvmtYaxis <= 0){
                //we are inverting the negative value from the gamepad and using that value to power the motor
                frontRight.setPower(-tgtPowerRightMvmtYaxis);
                backRight.setPower(-tgtPowerRightMvmtYaxis);
            } else if (tgtPowerRightMvmtYaxis > 0){
                frontRight.setPower(-tgtPowerRightMvmtYaxis);
                backRight.setPower(-tgtPowerRightMvmtYaxis);
            }
            // pressing left on both joysticks gives us a negative value
            if (tgtPowerLeftMvmtXaxis <= 0 && tgtPowerRightMvmtXaxis <= 0){
                frontRight.setPower(-tgtPowerLeftMvmtXaxis);
                frontLeft.setPower(-tgtPowerLeftMvmtXaxis);
                backLeft.setPower(tgtPowerLeftMvmtXaxis);
                backRight.setPower(tgtPowerRightMvmtXaxis);
            }
            // pressing right on both joysticks gives us a positive value
            else if (tgtPowerLeftMvmtXaxis >= 0 && tgtPowerRightMvmtXaxis >= 0){
                frontRight.setPower(-tgtPowerLeftMvmtXaxis);
                frontLeft.setPower(-tgtPowerLeftMvmtXaxis);
                backLeft.setPower(tgtPowerLeftMvmtXaxis);
                backRight.setPower(tgtPowerRightMvmtXaxis);
            }


        }
    }
}

