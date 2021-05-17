package engine;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import model.GameField;

public class GameFieldFromFileFactory implements GameFieldFactory {
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

  private Point parsePointFromString(String str) {
    String[] components = str.split(" ");
    int x = Integer.parseInt(components[0]);
    int y = Integer.parseInt(components[1]);
    return new Point(x, y);
  }

  private char[][] parseMazeFromString(String[] strArray) {
    int height = strArray.length;
    int width = strArray[0].length();
    char[][] result = new char[height][width];
    for (int i = 0; i < height; i++) {
      result[i] = strArray[i].toCharArray();
    }

    return result;
  }

}
