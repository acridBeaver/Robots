package engine.rules;

import java.awt.Point;

public interface MovementRule {
  Point getNextPosition(Point currentPosition);

  void resetCurrentPath();
}
