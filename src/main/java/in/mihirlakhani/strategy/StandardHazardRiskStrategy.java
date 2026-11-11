package in.mihirlakhani.strategy;

import in.mihirlakhani.model.RobotHazardInput;

public class StandardHazardRiskStrategy implements HazardRiskStrategy {

    @Override
    public double calculate(RobotHazardInput input) {
        return ((1.0 - input.getArmPrecision()) * 15.0)
                + (input.getWorkerDensity()
                * input.getMachineryState().getRiskFactor());
    }
}
