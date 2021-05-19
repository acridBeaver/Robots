package com.robots.serializer;

import com.robots.gui.model.WindowPreset;
import com.robots.gui.windows.MainWindow;

import java.util.Optional;

public class MainFrameSerializer {
    private static final WindowPresetConverter converter = new WindowPresetConverter();

    public static void serialize(MainWindow frame) {
        WindowPreset mainPreset = new WindowPreset();
        mainPreset.setLocation(frame.getLocation());
        mainPreset.setMinimized(false);
        mainPreset.setSize(frame.getSize());
        converter.convertToFile("Main Window", mainPreset);
    }

    public static void deserialize(MainWindow frame) {
        Optional<WindowPreset> presetWrapper = converter.getFromFile("Main Window");
        if (presetWrapper.isEmpty()) {
            return;
        }

        WindowPreset actualPreset = presetWrapper.get();
        frame.setLocation(actualPreset.getLocation());
        frame.setSize(actualPreset.getSize());
    }
}
