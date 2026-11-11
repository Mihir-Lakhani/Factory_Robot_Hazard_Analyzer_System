package in.mihirlakhani.auditor;

import in.mihirlakhani.exceptions.RobotSafetyException;
import in.mihirlakhani.model.RobotHazardInput;
import in.mihirlakhani.strategy.HazardRiskStrategy;
import in.mihirlakhani.validator.Validator;

public class RobotHazardAuditor {

    private final HazardRiskStrategy riskStrategy;

    public RobotHazardAuditor(HazardRiskStrategy riskStrategy) {
        if (riskStrategy == null) {
            throw new IllegalArgumentException("Risk strategy must not be null");
        }
        this.riskStrategy = riskStrategy;
    }

    public double calculateHazardRisk(RobotHazardInput input)
            throws RobotSafetyException {

        Validator.validate(input);
        return riskStrategy.calculate(input);
    }
}
