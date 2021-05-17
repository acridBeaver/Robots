package engine.factories;

import java.awt.Point;

public abstract class BaseGameFieldFactory implements GameFieldFactory {
  protected Point parsePointFromString(String str) {
    String[] components = str.split(" ");
    int x = Integer.parseInt(components[0]);
    int y = Integer.parseInt(components[1]);
    return new Point(x, y);
  }

  protected char[][] parseMazeFromString(String[] strArray) {
    int height = strArray.length;
    int width = strArray[0].length();
    char[][] result = new char[height][width];
    for (int i = 0; i < height; i++) {
      result[i] = strArray[i].toCharArray();
    }

    return result;
  }
}
