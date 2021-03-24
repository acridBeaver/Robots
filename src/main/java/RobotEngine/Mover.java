package RobotEngine;

public class Mover {
    private static void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = applyLimits(velocity, 0, Const.maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -Const.maxAngularVelocity, Const.maxAngularVelocity);
        double newX = Const.m_robotPositionX + velocity / angularVelocity *
                (Math.sin(Const.m_robotDirection  + angularVelocity * duration) -
                        Math.sin(Const.m_robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = Const.m_robotPositionX + velocity * duration * Math.cos(Const.m_robotDirection);
        }
        double newY = Const.m_robotPositionY - velocity / angularVelocity *
                (Math.cos(Const.m_robotDirection  + angularVelocity * duration) -
                        Math.cos(Const.m_robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = Const.m_robotPositionY + velocity * duration * Math.sin(Const.m_robotDirection);
        }
        Const.m_robotPositionX = newX;
        Const.m_robotPositionY = newY;
        Const.m_robotDirection = asNormalizedRadians(Const.m_robotDirection + angularVelocity * duration);
    }

    public static void onModelUpdateEvent()
    {
        double distance = distance(Const.m_targetPositionX, Const.m_targetPositionY,
                Const.m_robotPositionX, Const.m_robotPositionY);
        if (distance < 0.5)
        {
            return;
        }
        double velocity = Const.maxVelocity;
        double angleToTarget = angleTo(Const.m_robotPositionX, Const.m_robotPositionY,
                Const.m_targetPositionX, Const.m_targetPositionY);
        double angularVelocity = 0;
        if (angleToTarget > Const.m_robotDirection)
        {
            angularVelocity = Const.maxAngularVelocity;
        }
        if (angleToTarget < Const.m_robotDirection)
        {
            angularVelocity = -Const.maxAngularVelocity;
        }

        moveRobot(velocity, angularVelocity, 10);
    }

    private static double applyLimits(double value, double min, double max)
    {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    private static double distance(double x1, double y1, double x2, double y2)
    {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }
}
