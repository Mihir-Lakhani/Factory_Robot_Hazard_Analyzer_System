package in.mihirlakhani;

import in.mihirlakhani.auditor.RobotHazardAuditor;
import in.mihirlakhani.enums.MachineryState;
import in.mihirlakhani.exceptions.RobotSafetyException;
import in.mihirlakhani.model.RobotHazardInput;
import in.mihirlakhani.strategy.StandardHazardRiskStrategy;

import java.util.Scanner;

public class FactoryRobotHazardAnalyzer {
    public static void main(String[] args) {
        System.out.println("Factory Robot Hazard Analyzer");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Arm Precision (0.0-1.0):");
        double armPrecision = sc.nextDouble();

        System.out.println("Enter Worker Density (1-20):");
        int workerDensity = sc.nextInt();

        System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
        String stateText = sc.next();

        try {
            MachineryState state = MachineryState.fromString(stateText);

            RobotHazardInput input =
                    new RobotHazardInput(armPrecision, workerDensity, state);

            RobotHazardAuditor auditor = new RobotHazardAuditor(
                    new StandardHazardRiskStrategy());

            double hazardRiskScore = auditor.calculateHazardRisk(input);

            System.out.printf("Hazard Risk Score: %.2f%n", hazardRiskScore);
        } catch (RobotSafetyException e) {
            System.out.println(e.getMessage());
        }


    }
}
