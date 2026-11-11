package in.mihirlakhani.auditor;

public class RobotHazardAuditor {

    private boolean validate(double armPrecision,
                             int workerDensity,
                             String machineryState) {

        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println(
                    "Error: Arm precision must be 0.0-1.0");
            return false;
        }
        else if (workerDensity < 1 || workerDensity > 20) {
            System.out.println(
                    "Error: Worker density must be 1-20");
            return false;
        }
        else if (!machineryState.equals("Worn")
                && !machineryState.equals("Faulty")
                && !machineryState.equals("Critical")) {

            System.out.println(
                    "Error: Unsupported machinery state");
            return false;
        }

        return true;
    }

    public double calculateHazardRisk(
            double armPrecision,
            int workerDensity,
            String machineryState) {

        if (!validate(armPrecision, workerDensity, machineryState)) {
            return -1.0;
        }

        double machineRiskFactor =
                machineryState.equals("Worn") ? 1.3 :
                        machineryState.equals("Faulty") ? 2.0 : 3.0;

        return ((1.0 - armPrecision) * 15.0)
                + (workerDensity * machineRiskFactor);
    }
}