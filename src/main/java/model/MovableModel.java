package model;

import java.awt.*;

public interface MovableModel extends GameModel {
    Direction getDirection();
    void moveByStep(Point destination);
    void stopMoving();
}
