package se.andreaslagerstrom.service;

import se.andreaslagerstrom.data.Box;

import java.util.List;
import java.util.Random;

/**
 * RandomBoxSelector is an implementation of BoxSelector where boxes are randomly chosen.
 */
public class RandomBoxSelector implements BoxSelector {
    private Random random = new Random();

    @Override
    public Box getPrizeBox(List<Box> boxes) {
        return getRandomBox(boxes);
    }

    @Override
    public Box getSelectedBox(List<Box> boxes) {
        return getRandomBox(boxes);
    }

    private Box getRandomBox(List<Box> boxes) {
        return boxes.get(random.nextInt(boxes.size()));
    }
}
