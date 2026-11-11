package in.mihirlakhani.auditor;

import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.exceptions.RobotSafetyException;
import in.mihirlakhani.model.RobotHazardInput;
import in.mihirlakhani.strategy.HazardRiskStrategy;
import in.mihirlakhani.strategy.StandardHazardRiskStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RobotHazardAuditorTest {

    @Test
    void delegatesRiskCalculationToInjectedStrategy()
            throws RobotSafetyException {
        HazardRiskStrategy fixedResultStrategy = input -> 42.0;
        RobotHazardAuditor auditor =
                new RobotHazardAuditor(fixedResultStrategy);
        RobotHazardInput input = new RobotHazardInput(
                0.8, 5, MachineryState.WORN);

        assertEquals(42.0, auditor.calculateHazardRisk(input));
    }

    @Test
    void rejectsInvalidInputBeforeCalculatingRisk() {
        RobotHazardAuditor auditor = new RobotHazardAuditor(
                new StandardHazardRiskStrategy());
        RobotHazardInput input = new RobotHazardInput(
                1.5, 5, MachineryState.WORN);

        assertThrows(RobotSafetyException.class,
                () -> auditor.calculateHazardRisk(input));
    }
}
