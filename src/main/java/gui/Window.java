package gui;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;

public abstract class Window extends JInternalFrame {
    public Window(String name){
        super(name, true, true, true, true);
    }


    public abstract void changeAdapter(InternalFrameAdapter adapter);
}
