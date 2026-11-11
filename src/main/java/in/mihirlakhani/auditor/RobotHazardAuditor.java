package in.mihirlakhani.auditor;

import in.mihirlakhani.exceptions.RobotSafetyException;

public class RobotHazardAuditor {

    private void validate(double armPrecision,
                             int workerDensity,
                             String machineryState) throws RobotSafetyException {

        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
//            System.out.println(
//                    "Error: Arm precision must be 0.0-1.0");
//            return false;
        }
        else if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException("Error: Worker density must be 1-20");
//            System.out.println(
//                    "Error: Worker density must be 1-20");
//            return false;
        }
        else if (!machineryState.equalsIgnoreCase("Worn")
                && !machineryState.equalsIgnoreCase("Faulty")
                && !machineryState.equalsIgnoreCase("Critical")) {
            throw new RobotSafetyException("Error: Unsupported machinery state");
//            System.out.println(
//                    "Error: Unsupported machinery state");
//            return false;
        }

    }

    public double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineryState) throws RobotSafetyException {

        validate(armPrecision, workerDensity, machineryState);

        double machineRiskFactor =
                machineryState.equals("Worn") ? 1.3 :
                        machineryState.equals("Faulty") ? 2.0 : 3.0;

        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}