package in.mihirlakhani.strategy;

import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.model.RobotHazardInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardHazardRiskStrategyTest {

    private final StandardHazardRiskStrategy strategy =
            new StandardHazardRiskStrategy();

    @Test
    void calculatesRiskUsingPrecisionDensityAndMachineryFactor() {
        RobotHazardInput input = new RobotHazardInput(
                0.8, 5, MachineryState.WORN);

        assertEquals(9.5, strategy.calculate(input), 0.000001);
    }

    @Test
    void calculatesRiskForCriticalMachinery() {
        RobotHazardInput input = new RobotHazardInput(
                1.0, 2, MachineryState.CRITICAL);

        assertEquals(6.0, strategy.calculate(input), 0.000001);
    }
}
