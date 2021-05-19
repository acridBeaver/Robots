package com.robots.serializer;

import com.robots.gui.model.WindowPreset;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class WindowPresetSerializer implements Serializer<WindowPreset> {
    @Override
    public void serialize(WindowPreset preset, OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(preset);
    }
}
