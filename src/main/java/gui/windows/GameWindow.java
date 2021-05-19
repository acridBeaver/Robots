package gui.windows;

import engine.ModelMover;
import engine.factories.GameFieldFactory;
import engine.factories.GameFieldFromFileFactory;
import engine.rules.BfsMovementRule;
import engine.rules.DfsMovementRule;
import gui.adapters.ClosingAdapter;
import gui.GameVisualizer;
import model.GameField;
import model.Robot;

import java.awt.*;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;

public class GameWindow extends Window
{
    private final GameVisualizer m_visualizer;

    private static final Dimension DEFAULT_SIZE = new Dimension(400, 400);
    private static final Point DEFAULT_LOCATION = new Point(200, 200);

    public GameWindow(String name)
    {
        super(name);
        this.setLocation(DEFAULT_LOCATION);
        setMinimumSize(new Dimension(200, 200));
        addInternalFrameListener(new ClosingAdapter());

        GameFieldFactory factory = new GameFieldFromFileFactory("maze.txt");
        GameField gameField = factory.create();
        this.setSize(new Dimension(gameField.getWidth() * 50 + 10, gameField.getHeight() * 50 + 30));
        Robot robotBfs = new Robot(gameField.getMazeStart(), new BfsMovementRule(gameField), 1);
        Robot robotDfs = new Robot(gameField.getMazeStart(), new DfsMovementRule(gameField), 2);
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