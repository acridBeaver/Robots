package com.robots;

import com.robots.engine.factories.GameFieldFactory;
import com.robots.engine.factories.SimpleGameFieldFactory;
import com.robots.engine.rules.BfsMovementRule;
import com.robots.engine.rules.DfsMovementRule;
import com.robots.engine.rules.MovementRule;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.robots.models.GameField;
import com.robots.models.Robot;
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
    Robot testRobot = new Robot(start, testRule, 1);
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
    Robot testRobot = new Robot(start, testRule, 1);
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
