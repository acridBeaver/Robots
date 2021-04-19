package gui;

import engine.Const;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;

public class GameWindow extends Window
{
    private final GameVisualizer m_visualizer;

    public GameWindow(String name)
    {
        super(name);
        this.addInternalFrameListener(new ClosingAdapter());
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        this.setLocation(50,50);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Const.GAME_FIELD_HEIGHT = getHeight() - 10;
                Const.GAME_FIELD_WIDTH = getWidth() - 10;
            }
        });
    }

    @Override
    public void changeAdapter(InternalFrameAdapter adapter) {

    }
}