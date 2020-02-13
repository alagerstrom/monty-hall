package se.andreaslagerstrom.service;

import se.andreaslagerstrom.data.Box;

import java.util.List;

/**
 * BoxSelector is used by MontyHallSimulationService to determine
 * which box that contains the prize, and which box that the player selects.
 * The purpose of making this interface is to enable deterministic testing.
 */
public interface BoxSelector {
    /**
     * @param boxes A list of boxes used in the simulation
     * @return the box that contains the prize
     */
    Box getPrizeBox(List<Box> boxes);

    /**
     * @param boxes A list of boxes used in the simulation
     * @return the box that the player selects
     */
    Box getSelectedBox(List<Box> boxes);
}
