package se.andreaslagerstrom.service;

import se.andreaslagerstrom.data.Box;

import java.util.*;

/**
 * MontyHallSimulationService
 * <p>
 * This class is used to run a simulation of the Monty Hall problem,
 * using either runSimulationWithSwitch() or runSimulationWithoutSwitch()
 */
public class MontyHallSimulationService {
    private final int NUMBER_OF_BOXES = 3;
    private final BoxSelector boxSelector;

    /**
     * Construct a MontyHallSimulationService
     *
     * @param boxSelector is responsible for selecting boxes
     */
    public MontyHallSimulationService(BoxSelector boxSelector) {
        this.boxSelector = boxSelector;
    }

    /**
     * Run a simulation where the player chooses to switch box.
     *
     * @return true if the selected box contains the prize.
     */
    public boolean runSimulationWithSwitch() {
        return runSimulation(true);
    }

    /**
     * Run a simulation where the player chooses to not switch box.
     *
     * @return true if the selected box contains the prize.
     */
    public boolean runSimulationWithoutSwitch() {
        return runSimulation(false);
    }

    /**
     * Run a simulation
     *
     * @param switchBox is used to determine if the player switches box or not
     * @return true if the selected box contains the prize.
     */
    private boolean runSimulation(boolean switchBox) {
        var boxes = initializeBoxes();
        Box selectedBox = boxSelector.getSelectedBox(boxes);
        removeOneEmptyUnselectedBox(selectedBox, boxes);
        if (switchBox) {
            Box newBox = switchBox(selectedBox, boxes);
            return newBox.containsPrize();
        }
        return selectedBox.containsPrize();
    }

    /**
     * Create a representation of the boxes in the game.
     *
     * @return a list of boxes where one box contains a prize.
     */
    private List<Box> initializeBoxes() {
        List<Box> boxes = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_BOXES; i++) {
            boxes.add(new Box(i));
        }

        boxSelector
                .getPrizeBox(boxes)
                .setContainsPrize();

        return boxes;
    }

    /**
     * Mark one box that is not the selected box,
     * and does not contain the prize as removed.
     *
     * @param selectedBox The box that has been selected
     * @param boxes       A list of all boxes
     * @throws RuntimeException if there is no box that is not selected
     *                          and does not contain the prize
     */
    private void removeOneEmptyUnselectedBox(Box selectedBox, List<Box> boxes) {
        boxes.stream()
                .filter(box -> !box.equals(selectedBox))
                .filter(box -> !box.containsPrize())
                .findAny()
                .orElseThrow(() -> new RuntimeException("Found no unselected boxes that did not contain the prize"))
                .setRemoved(true);
    }

    /**
     * Select the only box that is not the selected box, and has not been removed.
     *
     * @param selectedBox The box that has been selected
     * @param boxes       A list of all boxes
     * @return The only box that is not selected and not removed
     * @throws RuntimeException if there is no box that is not selected and not removed
     */
    private Box switchBox(Box selectedBox, List<Box> boxes) {
        return boxes.stream()
                .filter(box -> !box.equals(selectedBox))
                .filter(box -> !box.isRemoved())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Found no unselected boxes that were not removed"));
    }
}
