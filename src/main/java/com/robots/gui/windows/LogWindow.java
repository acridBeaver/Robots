package com.robots.gui.windows;

import com.robots.gui.adapters.UnregisterAdapter;
import com.robots.log.LogChangeListener;
import com.robots.log.LogEntry;
import com.robots.log.LogWindowSource;
import com.robots.log.Logger;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;

public class LogWindow extends Window implements LogChangeListener {
    private final LogWindowSource m_logSource;
    private final TextArea m_logContent;

    public LogWindow(LogWindowSource logSource) {
        super("Work protocol");
        this.addInternalFrameListener(new UnregisterAdapter(this));
        m_logSource = logSource;
        m_logSource.registerListener(this);
        m_logContent = new TextArea("");
        m_logContent.setSize(200, 500);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        updateLogContent();

        this.setLocation(10, 10);
        this.setSize(300, 800);
        setMinimumSize(this.getSize());
        Logger.debug("Протокол работает");
    }

    private void updateLogContent() {
        StringBuilder content = new StringBuilder();
        for (LogEntry entry : m_logSource.all()) {
            content.append(entry.getMessage()).append("\n");
        }
        m_logContent.setText(content.toString());
        m_logContent.invalidate();
    }

    public LogWindowSource getLogWindowSource() {
        return m_logSource;
    }

    public void unregisterListener() {
        m_logSource.unregisterListener(this);
    }

    @Override
    public void onLogChanged() {
        EventQueue.invokeLater(this::updateLogContent);
    }

    @Override
    public void changeAdapter(InternalFrameAdapter adapter) {
    }
}
