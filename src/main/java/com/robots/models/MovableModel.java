package com.robots.models;

import java.awt.Point;
import java.util.List;

public interface MovableModel extends GameModel {
    Direction getDirection();

    void moveByStep(Point destination);

    void stopMoving();

    List<Point> getRoute();
}
