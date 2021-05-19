package engine.rules;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import model.GameField;

public class DfsMovementRule extends BaseMovementRule {
  private final Deque<Point> stack;
  private final Set<Point> visited;

  public DfsMovementRule(GameField gameField) {
    super(gameField);
    stack = new ArrayDeque<>();
    visited = new HashSet<>();
  }

  @Override
  public Point getNextPosition(Point currentPosition) {
    if (currentPosition.equals(gameField.getMazeEnd())) {
      return currentPosition;
    }

    if (stack.isEmpty()) {
      stack.add(currentPosition);
    }

    Point nextPosition = stack.removeFirst();
    for (Point neighbour : getNeighbours(nextPosition)) {
      if (!visited.contains(neighbour)) {
        stack.addFirst(neighbour);
      }
    }

    visited.add(nextPosition);
    return nextPosition;
  }

  @Override
  public void resetCurrentPath() {
    System.out.println("Resetting path for DFS search!");
    visited.clear();
    stack.clear();
  }
}
