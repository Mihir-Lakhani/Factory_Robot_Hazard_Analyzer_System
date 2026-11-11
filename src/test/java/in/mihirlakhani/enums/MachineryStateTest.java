package in.mihirlakhani.enums;

import in.mihirlakhani.exceptions.RobotSafetyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MachineryStateTest {

    @Test
    void mapsSupportedNamesToTheirStatesAndRiskFactors()
            throws RobotSafetyException {
        assertEquals(MachineryState.WORN, MachineryState.fromString("Worn"));
        assertEquals(1.3, MachineryState.fromString("Worn").getRiskFactor());

        assertEquals(MachineryState.FAULTY, MachineryState.fromString("Faulty"));
        assertEquals(2.0, MachineryState.fromString("Faulty").getRiskFactor());

        assertEquals(MachineryState.CRITICAL,
                MachineryState.fromString("Critical"));
        assertEquals(3.0,
                MachineryState.fromString("Critical").getRiskFactor());
    }

    @Test
    void rejectsUnsupportedOrIncorrectlyCasedNames() {
        assertThrows(RobotSafetyException.class,
                () -> MachineryState.fromString("worn"));
        assertThrows(RobotSafetyException.class,
                () -> MachineryState.fromString("Broken"));
    }
}
