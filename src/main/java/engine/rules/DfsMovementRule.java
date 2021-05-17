package engine.rules;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.GameField;
import model.MazeCell;

public class DfsMovementRule implements MovementRule {
  private final Deque<Point> stack;
  private final Set<Point> visited;
  private final GameField gameField;

  private volatile Point previousDestination;

  public DfsMovementRule(GameField gameField) {
    this.gameField = gameField;
    stack = new ArrayDeque<>();
    visited = new HashSet<>();
  }

  @Override
  public Point getNextPosition(Point currentPosition, Point destination) {
    if (currentPosition.equals(destination)) {
      return currentPosition;
    }

    if (stack.isEmpty()) {
      stack.add(currentPosition);
    }

    Point nextPosition = stack.pop();
    for (Point neighbour : getNeighbours(nextPosition)) {
      if (!visited.contains(neighbour)) {
        stack.add(neighbour);
      }
    }

    visited.add(nextPosition);
    return nextPosition;
  }

  private List<Point> getNeighbours(Point point) {
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
