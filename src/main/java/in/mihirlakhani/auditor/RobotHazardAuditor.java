package in.mihirlakhani.auditor;

import java.util.*;


public class RobotHazardAuditor {

    public double calculateHazardRisk(double armPrecision,
                                      int workerDensity,
                                      String machineryState){

        float machineryRiskFactor = (machineryState.equalsIgnoreCase("worn")?
                1.3f : machineryState.equalsIgnoreCase("faulty")?
                2.0f : 3.0f);

        double hazardRisk = ((1.0 - armPrecision) * 15.0) +
                (workerDensity * machineryRiskFactor);

        return hazardRisk;
    }
}