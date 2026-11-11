package in.mihirlakhani.validator;

import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.exceptions.RobotSafetyException;


public class Validator {

    public static MachineryState validate(double armPrecision,
                                               int workerDensity,
                                               String state) throws RobotSafetyException {
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
//            System.out.println(
//                    "Error: Arm precision must be 0.0-1.0");
//            return false;
        } else if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException("Error: Worker density must be 1-20");
//            System.out.println(
//                    "Error: Worker density must be 1-20");
//            return false;
        }

        return MachineryState.fromString(state);
    }
}