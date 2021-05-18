package model;

import java.awt.Point;

public class GameField {
    private final char[][] maze;
    private final Point mazeStart;
    private Point mazeEnd;

    public GameField(char[][] maze, Point mazeStart, Point mazeEnd) {
        this.maze = maze;
        this.mazeStart = mazeStart;
        this.mazeEnd = mazeEnd;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public int getHeight() {
        return maze.length;
    }

    public MazeCell getMazeCellType(int x, int y) {
        if (x >= getWidth() || x < 0 || y >= getHeight()
            || y < 0 || maze[y][x] == '0') {

            return MazeCell.WALL;
        } else if (maze[y][x] == '.') {
            return MazeCell.EMPTY;
        }

        return null;
    }

    public MazeCell getMazeCellType(Point point) {
        return getMazeCellType(point.x, point.y);
    }

    public Point getMazeEnd() {
        return mazeEnd;
    }

    public void setMazeEnd(Point mazeEnd) {
        this.mazeEnd = mazeEnd;
    }

    public Point getMazeStart() {
        return mazeStart;
    }
}
