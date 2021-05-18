package model;

import engine.rules.MovementRule;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicReference;

public class Robot {
    private final AtomicReference<Point> currentPosition;
    private final MovementRule movementRule;

    private volatile Direction direction;

    public Robot(Point initialPosition, MovementRule movementRule) {
        currentPosition = new AtomicReference<>(initialPosition);
        this.movementRule = movementRule;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getCurrentPosition() {
        return currentPosition.get();
    }

    public void moveByStep(Point destination) {
        Point nextPosition = movementRule.getNextPosition(currentPosition.get(), destination);
        direction = getNewDirection(nextPosition);
        currentPosition.set(nextPosition);
    }

    private Direction getNewDirection(Point newPosition) {
        Point pos = new Point(newPosition.x - currentPosition.get().x,
            newPosition.y - currentPosition.get().y);
        if (pos.y < 0) {
            return Direction.UP;
        } else if (pos.y > 0) {
            return Direction.DOWN;
        }

        if (pos.x < 0) {
            return Direction.LEFT;
        } else if (pos.x > 0) {
            return Direction.RIGHT;
        }

        return Direction.NONE;
    }

    @Override
    public String toString() {
        return "Robot{" +
            "currentPosition=" + currentPosition.get() +
            ", direction=" + direction + '}';
    }
}
