package gui.windows;

import engine.Const;
import engine.ModelMover;
import engine.factories.GameFieldFactory;
import engine.factories.GameFieldFromFileFactory;
import engine.rules.BfsMovementRule;
import engine.rules.DfsMovementRule;
import gui.ClosingAdapter;
import gui.GameVisualizer;
import model.GameField;
import model.Robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
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

        GameFieldFactory factory = new GameFieldFromFileFactory("maze.txt");
        GameField gameField = factory.create();
        Robot robotBfs = new Robot(gameField.getMazeStart(), new BfsMovementRule(gameField));
        Robot robotDfs = new Robot(gameField.getMazeStart(), new DfsMovementRule(gameField));
        ModelMover mover = new ModelMover(gameField);
        mover.registerModel(robotBfs);
        mover.registerModel(robotDfs);
        mover.startMovingModels();
        List<Robot> robotList = List.of(robotBfs, robotDfs);
        m_visualizer = new GameVisualizer(gameField, robotList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
    }

    @Override
    public void changeAdapter(InternalFrameAdapter adapter) {

    }
}