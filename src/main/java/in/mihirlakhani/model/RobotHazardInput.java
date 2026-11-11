package in.mihirlakhani.model;

import in.mihirlakhani.enums.MachineryState;

public class RobotHazardInput {
    private final double armPrecision;
    private final int workerDensity;
    private final MachineryState machineryState;

    public RobotHazardInput(double armPrecision,
                            int workerDensity,
                            MachineryState machineryState) {
        this.armPrecision = armPrecision;
        this.workerDensity = workerDensity;
        this.machineryState = machineryState;
    }

    public double getArmPrecision() {
        return armPrecision;
    }

    public int getWorkerDensity() {
        return workerDensity;
    }

    public MachineryState getMachineryState() {
        return machineryState;
    }
}