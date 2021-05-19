package com.robots.engine.factories;

import com.robots.models.GameField;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class GameFieldFromFileFactory extends BaseGameFieldFactory {
    private final File fieldFile;

    public GameFieldFromFileFactory(String filename) {
        this.fieldFile = new File(filename);
    }

    @Override
    public GameField create() {
        try {
            return parseGameFieldFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private GameField parseGameFieldFromFile() throws IOException {
        FileInputStream contentStream = new FileInputStream(fieldFile);
        String content = new String(contentStream.readAllBytes());
        String[] lines = content.split("\\r?\\n");
        Point start = parsePointFromString(lines[0]);
        Point end = parsePointFromString(lines[1]);
        char[][] maze = parseMazeFromString(Arrays.copyOfRange(lines, 2, lines.length));
        return new GameField(maze, start, end);
    }
}
