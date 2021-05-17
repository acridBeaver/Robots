package model;

import engine.Const;

import java.awt.geom.Point2D;

public class Robot implements MovableModel {
    private volatile int x;
    private volatile int y;
    private volatile Direction direction;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(double velocity, double angularVelocity, double duration) {

    }
}
