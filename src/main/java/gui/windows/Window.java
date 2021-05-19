package gui.windows;

import java.beans.PropertyVetoException;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import gui.model.WindowPreset;

public abstract class Window extends JInternalFrame {
    public Window(String name){
        super(name, false, true, false, true);
    }

    public abstract void changeAdapter(InternalFrameAdapter adapter);

    public void applyPreset(WindowPreset preset) {
        setSize(preset.getSize());
        setLocation(preset.getLocation());
        try {
            setIcon(preset.isMinimized());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public WindowPreset getPreset() {
        WindowPreset result = new WindowPreset();
        result.setLocation(getLocation());
        result.setSize(getSize());
        result.setMinimized(isIcon());
        return result;
    }
}
