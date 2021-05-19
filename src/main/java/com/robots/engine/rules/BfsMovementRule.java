package com.robots.engine.rules;

import com.robots.models.GameField;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class BfsMovementRule extends BaseMovementRule {
    private final Deque<Point> queue;
    private final Set<Point> visited;

    public BfsMovementRule(GameField gameField) {
        super(gameField);
        visited = new HashSet<>();
        queue = new ArrayDeque<>();
    }

    @Override
    public Point getNextPosition(Point currentPosition) {
        if (currentPosition.equals(gameField.getMazeEnd())) {
            return currentPosition;
        }

        if (queue.isEmpty()) {
            queue.add(currentPosition);
        }

        Point nextPosition = queue.pollFirst();
        for (Point neighbour : getNeighbours(nextPosition)) {
            if (!visited.contains(neighbour)) {
                queue.addLast(neighbour);
            }
        }

        visited.add(nextPosition);
        return nextPosition;
    }

    @Override
    public void resetCurrentPath() {
        System.out.println("Resetting path for BFS search!");
        visited.clear();
        queue.clear();
    }
}
