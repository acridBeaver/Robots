package model;

import engine.rules.MovementRule;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicReference;

public class Robot implements MovableModel {
    private final AtomicReference<Point> currentPosition;
    private final MovementRule movementRule;

    private volatile Direction direction;

    public Robot(Point initialPosition, MovementRule movementRule) {
        currentPosition = new AtomicReference<>(initialPosition);
        this.movementRule = movementRule;
        direction = Direction.OLD;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Point getCurrentPosition() {
        return currentPosition.get();
    }

    @Override
    public void moveByStep(Point destination) {
        if (destination.equals(currentPosition.get())) {
            direction = Direction.OLD;
            return;
        }

        Point nextPosition = movementRule.getNextPosition(currentPosition.get());
        direction = getNewDirection(nextPosition);
        currentPosition.set(nextPosition);
    }

    @Override
    public void stopMoving() {
        movementRule.resetCurrentPath();
        direction = Direction.OLD;
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

        return Direction.OLD;
    }

    @Override
    public String toString() {
        return "Robot{" +
            "currentPosition=" + currentPosition.get() +
            ", direction=" + direction + '}';
    }
}
