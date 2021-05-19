package com.robots.models;

import java.awt.Point;

public interface MovableModel extends GameModel {
    Direction getDirection();

    void moveByStep(Point destination);

    void stopMoving();
}
