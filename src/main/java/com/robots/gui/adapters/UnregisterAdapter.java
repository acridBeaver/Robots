package com.robots.gui.adapters;

import static com.robots.gui.panels.ClosingPanel.closingPanelLogic;

import com.robots.gui.windows.LogWindow;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class UnregisterAdapter extends InternalFrameAdapter {
    private final LogWindow window;

    public UnregisterAdapter(LogWindow window) {
        this.window = window;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent event) {
        super.internalFrameClosing(event);
        closingPanelLogic(event);
        window.unregisterListener();
    }
}
