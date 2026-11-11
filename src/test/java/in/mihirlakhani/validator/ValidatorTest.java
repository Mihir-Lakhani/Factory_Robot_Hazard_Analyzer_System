package in.mihirlakhani.validator;

import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.exceptions.RobotSafetyException;
import in.mihirlakhani.model.RobotHazardInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    @Test
    void acceptsValidInput() {
        RobotHazardInput input = new RobotHazardInput(
                0.8, 5, MachineryState.WORN);

        assertDoesNotThrow(() -> Validator.validate(input));
    }

    @Test
    void rejectsMissingInput() {
        assertThrows(RobotSafetyException.class,
                () -> Validator.validate(null));
    }

    @Test
    void rejectsArmPrecisionOutsideRangeOrNotFinite() {
        assertInvalidInput(-0.01, 5, MachineryState.WORN);
        assertInvalidInput(1.01, 5, MachineryState.WORN);
        assertInvalidInput(Double.NaN, 5, MachineryState.WORN);
        assertInvalidInput(Double.POSITIVE_INFINITY, 5, MachineryState.WORN);
    }

    @Test
    void rejectsWorkerDensityOutsideRange() {
        assertInvalidInput(0.8, 0, MachineryState.WORN);
        assertInvalidInput(0.8, 21, MachineryState.WORN);
    }

    @Test
    void rejectsMissingMachineryState() {
        assertInvalidInput(0.8, 5, null);
    }

    private void assertInvalidInput(double armPrecision,
                                    int workerDensity,
                                    MachineryState machineryState) {
        RobotHazardInput input = new RobotHazardInput(
                armPrecision, workerDensity, machineryState);

        assertThrows(RobotSafetyException.class,
                () -> Validator.validate(input));
    }
}
