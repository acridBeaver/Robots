package gui.adapters;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import static gui.panels.ClosingPanel.closingPanelLogic;

public class ClosingAdapter extends InternalFrameAdapter {
    @Override
    public void internalFrameClosing(InternalFrameEvent event){
        super.internalFrameClosing(event);
        closingPanelLogic(event);
    }
}