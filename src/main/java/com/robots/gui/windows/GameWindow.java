package com.robots.gui.windows;

import com.robots.engine.ModelMover;
import com.robots.engine.factories.GameFieldFactory;
import com.robots.engine.factories.GameFieldFromFileFactory;
import com.robots.engine.rules.BfsMovementRule;
import com.robots.engine.rules.DfsMovementRule;
import com.robots.gui.GameVisualizer;
import com.robots.gui.adapters.ClosingAdapter;
import com.robots.models.GameField;
import com.robots.models.MovableModel;
import com.robots.models.Robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;

public class GameWindow extends Window {
    private final GameVisualizer m_visualizer;

    private static final Point DEFAULT_LOCATION = new Point(200, 200);

    public GameWindow(String name) {
        super(name);
        this.setLocation(DEFAULT_LOCATION);
        setMinimumSize(new Dimension(200, 200));
        addInternalFrameListener(new ClosingAdapter());

        GameFieldFactory factory = new GameFieldFromFileFactory("maze.txt");
        GameField gameField = factory.create();
        this.setSize(new Dimension(gameField.getWidth() * 50 + 10, gameField.getHeight() * 50 + 30));
        Robot robotBfs = new Robot(1, gameField.getMazeStart(), new BfsMovementRule(gameField));
        Robot robotDfs = new Robot(2, gameField.getMazeStart(), new DfsMovementRule(gameField));
        ModelMover mover = new ModelMover(gameField);
        mover.registerModel(robotBfs);
        mover.registerModel(robotDfs);
        mover.startMovingModels();
        List<MovableModel> robotList = List.of(robotBfs, robotDfs);
        m_visualizer = new GameVisualizer(gameField, robotList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
    }

    @Override
    public void changeAdapter(InternalFrameAdapter adapter) {

    }
}