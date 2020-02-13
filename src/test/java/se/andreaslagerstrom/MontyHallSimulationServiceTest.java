package se.andreaslagerstrom;

import org.junit.Before;
import org.junit.Test;
import se.andreaslagerstrom.data.Box;
import se.andreaslagerstrom.service.BoxSelector;
import se.andreaslagerstrom.service.MontyHallSimulationService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for MontyHallSimulationService
 */
public class MontyHallSimulationServiceTest {

    private DeterministicBoxSelector boxSelector;
    private MontyHallSimulationService simulationService;

    @Before
    public void setup() {
        boxSelector = new DeterministicBoxSelector();
        simulationService = new MontyHallSimulationService(boxSelector);
    }

    /**
     * If the player selects the wrong box,
     * and chooses to switch, the player should win
     */
    @Test
    public void givenThatThePlayerSelectsABoxWithoutAPrize_whenThePlayerSwitchesBox_thenThePlayerShouldWin() {
        // given
        boxSelector
                .setPrizeBoxIndex(1)
                .setSelectedBoxIndex(0);

        // when
        var wonPrize = simulationService.runSimulationWithSwitch();

        // then
        assertThat(wonPrize, is(true));
    }

    @Test
    public void givenThatThePlayerSelectsABoxWithoutAPrize_whenThePlayerDoesNotSwitchBox_thenThePlayerShouldLoose() {
        // given
        boxSelector
                .setPrizeBoxIndex(1)
                .setSelectedBoxIndex(0);

        // when
        var wonPrize = simulationService.runSimulationWithoutSwitch();

        // then
        assertThat(wonPrize, is(false));
    }

    @Test
    public void givenThatThePlayerSelectsTheBoxThatContainsThePrize_whenThePlayerSwitchesBox_thenThePlayerShouldLoose() {
        // given
        boxSelector
                .setPrizeBoxIndex(1)
                .setSelectedBoxIndex(1);

        // when
        var wonPrize = simulationService.runSimulationWithSwitch();

        // then
        assertThat(wonPrize, is(false));
    }

    @Test
    public void givenThatThePlayerSelectsTheBoxThatContainsThePrize_whenThePlayerDoesNotSwitchBox_thenThePlayerShouldWin() {
        // given
        boxSelector
                .setPrizeBoxIndex(1)
                .setSelectedBoxIndex(1);

        // when
        var wonPrize = simulationService.runSimulationWithoutSwitch();

        // then
        assertThat(wonPrize, is(true));
    }

    private static class DeterministicBoxSelector implements BoxSelector {
        private int prizeBoxIndex;
        private int selectedBoxIndex;

        public DeterministicBoxSelector setPrizeBoxIndex(int prizeBoxIndex) {
            this.prizeBoxIndex = prizeBoxIndex;
            return this;
        }

        public DeterministicBoxSelector setSelectedBoxIndex(int selectedBoxIndex) {
            this.selectedBoxIndex = selectedBoxIndex;
            return this;
        }

        @Override
        public Box getPrizeBox(List<Box> boxes) {
            return boxes.get(prizeBoxIndex);
        }

        @Override
        public Box getSelectedBox(List<Box> boxes) {
            return boxes.get(selectedBoxIndex);
        }
    }
}
