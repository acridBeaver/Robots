package com.robots.gui.adapters;

import static com.robots.gui.panels.ClosingPanel.closingPanelLogic;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class ClosingAdapter extends InternalFrameAdapter {
    @Override
    public void internalFrameClosing(InternalFrameEvent event) {
        super.internalFrameClosing(event);
        closingPanelLogic(event);
    }
}