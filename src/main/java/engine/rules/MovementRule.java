package engine.rules;

import java.awt.Point;

public interface MovementRule {
  Point getNextPosition(Point currentPosition, Point destination);

  void resetCurrentPath();
}
