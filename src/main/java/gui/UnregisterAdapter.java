package gui;

import log.LogChangeListener;
import log.LogWindowSource;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import static gui.ClosingPanel.closingPanelLogic;

public class UnregisterAdapter extends InternalFrameAdapter {
    private final LogWindow window;
    public UnregisterAdapter(LogWindow window) {
        this.window = window;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent event){
        super.internalFrameClosing(event);
        closingPanelLogic(event);
        window.unregisterListener();
        }
}
