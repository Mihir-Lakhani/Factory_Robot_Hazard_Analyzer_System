package in.mihirlakhani;

import in.mihirlakhani.auditor.RobotHazardAuditor;

import java.util.Scanner;

public class FactoryRobotHazardAnalyzer {
    static void main() {
        System.out.println("Factory Robot Hazard Analyzer");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Arm Precision (0.0-0.1):");
        float armPrecision = sc.nextFloat();
        sc.nextLine(); //consume leftover newline

        System.out.println("Enter Worker Density (1-20):");
        int workerDensity = sc.nextInt();
        sc.nextLine(); //consume leftover newline

        System.out.println("Enter Machinery State (Worn/Faulty/Critical)");
        String machineryState = sc.next();

        System.out.printf("Arm Precision : %.1f\n", armPrecision);
        System.out.printf("Worker Density : %d\n", workerDensity);
        System.out.printf("Machinery State : %s\n", machineryState);



        RobotHazardAuditor auditor = new RobotHazardAuditor();

        double hazardRiskScore = auditor.calculateHazardRisk(
                armPrecision, workerDensity, machineryState
        );

        if (hazardRiskScore != -1.0) {
            System.out.printf(
                    "Hazard Risk Score: %.2f%n",
                    hazardRiskScore
            );
        }
    }




}
