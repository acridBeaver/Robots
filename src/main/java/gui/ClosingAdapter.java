package gui;

import log.LogChangeListener;
import log.LogWindowSource;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import static gui.ClosingPanel.closingPanelLogic;

public class ClosingAdapter extends InternalFrameAdapter {
    @Override
    public void internalFrameClosing(InternalFrameEvent event){
        super.internalFrameClosing(event);
        closingPanelLogic(event);
    }
}