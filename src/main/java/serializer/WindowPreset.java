package serializer;

import java.io.Serializable;

public class WindowPreset implements Serializable {
    private int width;
    private int height;
    private boolean isMinimized;

    public boolean isMinimized() {
        return isMinimized;
    }

    public void setMinimized(boolean minimized) {
        isMinimized = minimized;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Window height cannot be less than zero");
        }

        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Window width cannot be less than zero");
        }

        this.width = width;
    }
}
