package se.andreaslagerstrom.data;

import java.util.Objects;

/**
 * A Box is a POJO that represents a box in the game, identified by its id,
 * it can either contain the prize or not, and be removed or not.
 */
public class Box {
    private long id;
    private boolean containsPrize = false;
    private boolean removed = false;

    public Box(long id) {
        this.id = id;
    }

    public boolean containsPrize() {
        return containsPrize;
    }

    public void setContainsPrize() {
        this.containsPrize = true;
    }

    public long getId() {
        return id;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return id == box.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
