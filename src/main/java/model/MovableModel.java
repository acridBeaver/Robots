package model;

public interface MovableModel extends GameModel {
    void move(double velocity, double angularVelocity, double duration);
}
