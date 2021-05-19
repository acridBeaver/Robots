package com.robots.engine.rules;

import com.robots.models.GameField;
import com.robots.models.MazeCell;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMovementRule implements MovementRule {
    protected final GameField gameField;

    public BaseMovementRule(GameField gameField) {
        this.gameField = gameField;
    }

    protected List<Point> getNeighbours(Point point) {
        List<Point> neighbours = List.of(
                new Point(point.x + 1, point.y),
                new Point(point.x, point.y + 1),
                new Point(point.x - 1, point.y),
                new Point(point.x, point.y - 1));

        return neighbours.stream()
                .filter(p -> gameField.getMazeCellType(p) == MazeCell.EMPTY)
                .collect(Collectors.toList());
    }
}
