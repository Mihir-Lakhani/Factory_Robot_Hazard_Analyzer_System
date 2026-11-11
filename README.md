# Factory Robot Hazard Analyzer

A small Java console application that estimates a factory robot's hazard risk from its arm precision, the number of nearby workers, and the machinery's condition.

## What it does

When you run the program, it asks for:

- **Arm precision:** a value from `0.0` to `1.0`
- **Worker density:** a whole number from `1` to `20`
- **Machinery state:** `Worn`, `Faulty`, or `Critical`

It validates the values, calculates a risk score, and prints the result to two decimal places. Machinery state names are case-sensitive, so enter `Worn`, not `worn`.

## Risk calculation

The current strategy uses this formula:

```text
hazard risk = ((1 - arm precision) × 15) + (worker density × machinery risk factor)
```

| Machinery state | Risk factor |
| --- | ---: |
| Worn | 1.3 |
| Faulty | 2.0 |
| Critical | 3.0 |

For example, an arm precision of `0.8`, worker density of `5`, and state `Worn` gives a score of `9.50`.

These values and the formula are the rules implemented by this project. They are examples for the analyzer, not a substitute for a real factory safety assessment.

## Run it

The project uses Maven and is configured for Java 27. From the project directory, build it with:

```powershell
mvn clean package
```

Then start the console application:

```powershell
java -cp target/classes in.mihirlakhani.FactoryRobotHazardAnalyzer
```

Example session:

```text
Factory Robot Hazard Analyzer
Enter Arm Precision (0.0-1.0):
0.8
Enter Worker Density (1-20):
5
Enter Machinery State (Worn/Faulty/Critical):
Worn
Hazard Risk Score: 9.50
```

## Project layout

```text
src/main/java/in/mihirlakhani/
├── FactoryRobotHazardAnalyzer.java
├── auditor/
│   └── RobotHazardAuditor.java
├── enums/
│   └── MachineryState.java
├── exceptions/
│   └── RobotSafetyException.java
├── model/
│   └── RobotHazardInput.java
├── strategy/
│   ├── HazardRiskStrategy.java
│   └── StandardHazardRiskStrategy.java
└── validator/
    └── Validator.java
```

## How the pieces fit together

- `FactoryRobotHazardAnalyzer` reads input, converts the machinery name to an enum, creates the model, and prints the score or an error message.
- `RobotHazardInput` holds the three inputs. Its fields are private and final, and callers read them through getters.
- `Validator` checks that an input exists, arm precision is finite and between `0.0` and `1.0`, worker density is between `1` and `20`, and a machinery state is present. Invalid values raise `RobotSafetyException`.
- `MachineryState` lists the supported states and stores each state's risk factor. Its `fromString` method matches the displayed names exactly and throws `RobotSafetyException` for anything else.
- `RobotHazardAuditor` validates the model, then asks the selected risk strategy to calculate the score.
- `HazardRiskStrategy` defines the calculation contract. `StandardHazardRiskStrategy` contains the current formula. A different approved formula can be added as another strategy without putting the calculation into `main()`.
- `RobotSafetyException` carries validation and unsupported-state messages back to `main()`.

## Design ideas in the project

The project keeps console input/output, data, validation, state mapping, and risk calculation in separate classes. This demonstrates encapsulation, abstraction, modularity, and the Single Responsibility Principle. Validation errors use a custom exception, and the calculation uses the **Strategy Pattern** so the auditor can work with different risk formulas.

The current version has one strategy: `StandardHazardRiskStrategy`. New machinery states can be added to `MachineryState` with a display name and risk factor. New calculation rules can be added as implementations of `HazardRiskStrategy`.

## Use case progression

The project was built up in small use cases:

1. **Display a hazard message**
2. **Accept robot hazard inputs**
3. **Calculate a hazard risk score**
4. **Validate inputs with conditional logic**
5. **Move validation into a separate method**
6. **Report invalid input with a custom exception**
7. **Map machinery states to risk factors**
8. **Modularize the analyzer with object-oriented design and a strategy for the risk formula**

## Current limits

The console prompts expect numeric input for precision and worker density. The program handles values outside the accepted ranges and unsupported machinery names, but it does not currently provide a custom message for non-numeric input.
