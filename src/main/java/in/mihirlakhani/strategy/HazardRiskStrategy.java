package in.mihirlakhani.strategy;

import in.mihirlakhani.model.RobotHazardInput;

public interface HazardRiskStrategy {
    double calculate(RobotHazardInput input);
}
