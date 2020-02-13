package se.andreaslagerstrom.application;

import se.andreaslagerstrom.service.BoxSelector;
import se.andreaslagerstrom.service.MontyHallSimulationService;
import se.andreaslagerstrom.service.RandomBoxSelector;

/**
 * Entry point for Monty Hall simulation
 */
public class MontyHallSimulationApp {

    /**
     * Run a monty hall simulation,
     * and print out the probabilities of winning
     * if the player chooses to switch box or not, respectively.
     *
     * Finally, a verdict saying if you should choose to switch box or not,
     * if you want to maximize your chance of winning the prize,
     * will be printed.
     */
    public static void main(String[] args) {
        BoxSelector boxSelector = new RandomBoxSelector();
        var montyHallSimulationService = new MontyHallSimulationService(boxSelector);
        runMontyHallSimulation(montyHallSimulationService, 10000);
    }

    private static void runMontyHallSimulation(
            MontyHallSimulationService simulationService,
            int numberOfTrials
    ) {
        System.out.println("Monty hall simulation");
        System.out.println("Running " + numberOfTrials + " simulations.\n");
        int numberOfPrizesWithSwitch = 0;
        int numberOfPrizesWithoutSwitch = 0;

        for (int i = 0; i < numberOfTrials; i++) {
            if (simulationService.runSimulationWithSwitch()) {
                numberOfPrizesWithSwitch++;
            }
            if (simulationService.runSimulationWithoutSwitch()) {
                numberOfPrizesWithoutSwitch++;
            }
        }

        double chanceOfWinningWithSwitch = (double) numberOfPrizesWithSwitch / numberOfTrials;
        double chanceOfWinningWithoutSwitch = (double) numberOfPrizesWithoutSwitch / numberOfTrials;

        System.out.println("Probability of winning if you switch box: " + chanceOfWinningWithSwitch);
        System.out.println("Probability of winning if you don't switch box: " + chanceOfWinningWithoutSwitch);
        System.out.println();
        if (chanceOfWinningWithSwitch > chanceOfWinningWithoutSwitch) {
            System.out.println("You should switch box.");
        } else {
            System.out.println("You should not switch box.");
        }
    }
}
