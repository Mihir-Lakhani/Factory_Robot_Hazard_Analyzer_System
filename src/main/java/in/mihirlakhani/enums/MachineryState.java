package in.mihirlakhani.enums;

import in.mihirlakhani.exceptions.RobotSafetyException;

public enum MachineryState {
    WORN("Worn", 1.3),
    FAULTY("Faulty", 2.0),
    CRITICAL("Critical", 3.0);

    private final String displayName;
    private final double riskFactor;

    MachineryState(String displayName, double riskFactor) {
        this.displayName = displayName;
        this.riskFactor = riskFactor;
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    public static MachineryState fromString(String value)
            throws RobotSafetyException {
        for (MachineryState state : MachineryState.values()) {
            if (state.displayName.equals(value)) {
                return state;
            }
        }

        throw new RobotSafetyException(
                "Error: Unsupported machinery state");
    }
}