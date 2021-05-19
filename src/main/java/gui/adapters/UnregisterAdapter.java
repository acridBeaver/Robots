package gui.adapters;

import gui.windows.LogWindow;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import static gui.panels.ClosingPanel.closingPanelLogic;

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
