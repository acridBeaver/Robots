package model;

import engine.Const;

import java.awt.geom.Point2D;

public class Robot implements MovableModel {
    private volatile double x;
    private volatile double y;
    private volatile double direction;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Position coordinates cannot be less than 0");
        }

        this.x = x;
        this.y = y;
    }

    public double getDirection() {
        return direction;
    }

    public void changeDirection(double newDirection) {
        this.direction = newDirection;
    }

    @Override
    public void move(double velocity, double angularVelocity, double duration) {
        velocity = applyLimits(velocity, 0, Const.maxVelocity);
        angularVelocity = applyLimits(angularVelocity,
                -Const.maxAngularVelocity, Const.maxAngularVelocity);

        Point2D.Double newPosition = getNewRobotPosition(velocity, angularVelocity, duration);
        Const.m_robotDirection = getNewRobotDirection(newPosition, angularVelocity, duration);

        if (outOfBounds(newPosition)) {
            newPosition = getNewRobotPosition(velocity, angularVelocity, duration);
        }

        Const.m_robotPositionX = newPosition.x;
        Const.m_robotPositionY = newPosition.y;
    }
}
