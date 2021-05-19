package com.robots.engine.factories;

import com.robots.models.GameField;

import java.awt.Point;

public class SimpleGameFieldFactory extends BaseGameFieldFactory {
    private final char[][] maze;
    private final Point mazeStart;
    private final Point mazeEnd;

    public SimpleGameFieldFactory(String[] maze, Point mazeStart, Point mazeEnd) {
        this.maze = parseMazeFromString(maze);
        this.mazeStart = mazeStart;
        this.mazeEnd = mazeEnd;
    }

    @Override
    public GameField create() {
        return new GameField(maze, mazeStart, mazeEnd);
    }
}
