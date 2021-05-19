package com.robots.gui.model;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

public class WindowPreset implements Serializable {
    private Dimension size;
    private Point location;
    private boolean isMinimized;

    public boolean isMinimized() {
        return isMinimized;
    }

    public void setMinimized(boolean minimized) {
        isMinimized = minimized;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
}
