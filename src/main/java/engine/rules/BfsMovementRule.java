package engine.rules;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import model.GameField;

public class BfsMovementRule extends BaseMovementRule {
  private final Deque<Point> queue;
  private final Set<Point> visited;

  private volatile Point previousDestination;

  public BfsMovementRule(GameField gameField) {
    super(gameField);
    visited = new HashSet<>();
    queue = new ArrayDeque<>();
  }

  @Override
  public Point getNextPosition(Point currentPosition, Point destination) {
    if (currentPosition.equals(destination)) {
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
}
