package RobotEngine;

import java.awt.geom.Point2D;

public class Mover {

    public static void onModelUpdateEvent() {
        double distance = distance(Const.m_targetPositionX, Const.m_targetPositionY,
                Const.m_robotPositionX, Const.m_robotPositionY);
        if (distance < 0.5) {
            return;
        }
        double angleToTarget = angleTo(Const.m_robotPositionX, Const.m_robotPositionY,
                Const.m_targetPositionX, Const.m_targetPositionY);
        double angularVelocity = 0;
        if (angleToTarget > Const.m_robotDirection) {
            angularVelocity = Const.maxAngularVelocity;
        }
        if (angleToTarget < Const.m_robotDirection) {
            angularVelocity = -Const.maxAngularVelocity;
        }

        moveRobot(Const.maxVelocity, angularVelocity, 10);
    }

    private static void moveRobot(double velocity, double angularVelocity, double duration) {
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

    private static Point2D.Double getNewRobotPosition(double velocity,
                                                      double angularVelocity,
                                                      double duration) {
        double x = Const.m_robotPositionX + velocity / angularVelocity *
                (Math.sin(Const.m_robotDirection + angularVelocity * duration) -
                        Math.sin(Const.m_robotDirection));
        if (!Double.isFinite(x)) {
            x = Const.m_robotPositionX + velocity * duration * Math.cos(Const.m_robotDirection);
        }

        double y = Const.m_robotPositionY - velocity / angularVelocity *
                (Math.cos(Const.m_robotDirection + angularVelocity * duration) -
                        Math.cos(Const.m_robotDirection));
        if (!Double.isFinite(y)) {
            y = Const.m_robotPositionY + velocity * duration * Math.sin(Const.m_robotDirection);
        }

        return new Point2D.Double(x, y);

    }

    private static boolean outOfBounds(Point2D.Double point) {
        int widthOffset = 5;
        int heightOffset = 30;
        return point.x + widthOffset > Const.GAME_FIELD_WIDTH || point.x < 5
                || point.y + heightOffset > Const.GAME_FIELD_HEIGHT || point.y < 5;
    }

    private static double getNewRobotDirection(Point2D.Double position,
                                               double angularVelocity,
                                               double duration) {
        if (outOfBounds(position)) {
            double wallAngle = position.x > Const.GAME_FIELD_WIDTH || position.x < 5 ? Math.PI : 0;
            return wallAngle - Const.m_robotDirection;
        }

        return asNormalizedRadians(Const.m_robotDirection + angularVelocity * duration);
    }

    private static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double asNormalizedRadians(double angle) {
        while (angle < 0) {
            angle += 2 * Math.PI;
        }
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }
}