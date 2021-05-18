package com.robots;

import engine.factories.GameFieldFactory;
import engine.factories.SimpleGameFieldFactory;
import engine.rules.BfsMovementRule;
import engine.rules.DfsMovementRule;
import engine.rules.MovementRule;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.GameField;
import model.Robot;
import org.junit.Assert;
import org.junit.Test;

public class TestRobotMovement {
  private final String[] TEST_MAZE_1 = {
      "...",
      "...",
      "..."
  };

  @Test
  public void testMovingRobotByBfs() {
    Point start = new Point(0, 0);
    Point end = new Point(2, 2);

    GameFieldFactory gameFieldFactory = new SimpleGameFieldFactory(TEST_MAZE_1, start, end);
    GameField testField = gameFieldFactory.create();
    MovementRule testRule = new BfsMovementRule(testField);
    Robot testRobot = new Robot(start, testRule);
    List<Point> actualRoute = new ArrayList<>();
    for (var i = 0; i < 10; i++) {
      System.out.printf("%s%n", testRobot);
      testRobot.moveByStep(end);
    }

    List<Point> expectedRoute = List.of(
        start,
        end
    );
    Assert.assertEquals(expectedRoute, actualRoute);
  }

  @Test
  public void testMovingRobotByDfs() {
    Point start = new Point(0, 0);
    Point end = new Point(2, 2);

    GameFieldFactory gameFieldFactory = new SimpleGameFieldFactory(TEST_MAZE_1, start, end);
    GameField testField = gameFieldFactory.create();
    MovementRule testRule = new DfsMovementRule(testField);
    Robot testRobot = new Robot(start, testRule);
    List<Point> actualRoute = new ArrayList<>();
    for (var i = 0; i < 10; i++) {
      System.out.printf("%s%n", testRobot);
      testRobot.moveByStep(end);
    }

    List<Point> expectedRoute = List.of(
        start,
        end
    );
    Assert.assertEquals(expectedRoute, actualRoute);
  }
}
