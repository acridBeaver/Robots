package gui.windows;

import engine.Const;

import gui.ClosingAdapter;
import gui.GameVisualizer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;

public class GameWindow extends Window
{
    private final GameVisualizer m_visualizer;

    private static final Dimension DEFAULT_SIZE = new Dimension(400, 400);
    private static final Point DEFAULT_LOCATION = new Point(50, 50);

    public GameWindow(String name)
    {
        super(name);
        this.setSize(DEFAULT_SIZE);
        this.setLocation(DEFAULT_LOCATION);
        setMinimumSize(new Dimension(200, 200));
        addInternalFrameListener(new ClosingAdapter());
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);

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