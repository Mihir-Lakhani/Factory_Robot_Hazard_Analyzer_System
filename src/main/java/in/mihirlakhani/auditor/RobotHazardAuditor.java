package in.mihirlakhani.auditor;

import in.mihirlakhani.validator.Validator;
import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.exceptions.RobotSafetyException;

public class RobotHazardAuditor {

    public double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineryState) throws RobotSafetyException {

        MachineryState state = Validator.validate(armPrecision, workerDensity, machineryState);

        double machineRiskFactor = state.getRiskFactor();

//        double machineRiskFactor =
//                machineryState.equals("Worn") ? 1.3 :
//                        machineryState.equals("Faulty") ? 2.0 : 3.0;

        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}