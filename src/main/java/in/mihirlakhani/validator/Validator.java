package in.mihirlakhani.validator;

import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.exceptions.RobotSafetyException;
import in.mihirlakhani.model.RobotHazardInput;

public class Validator {

    public static void validate(RobotHazardInput input)
            throws RobotSafetyException {

        if (input == null) {
            throw new RobotSafetyException("Error: Hazard input is missing");
        }

        if (!Double.isFinite(input.getArmPrecision())
                ||input.getArmPrecision() < 0.0
                || input.getArmPrecision() > 1.0) {
            throw new RobotSafetyException(
                    "Error: Arm precision must be 0.0-1.0");
        }

        if (input.getWorkerDensity() < 1
                || input.getWorkerDensity() > 20) {
            throw new RobotSafetyException(
                    "Error: Worker density must be 1-20");
        }

        if (input.getMachineryState() == null) {
            throw new RobotSafetyException(
                    "Error: Unsupported machinery state");
        }
    }
}