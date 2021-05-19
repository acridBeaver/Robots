package com.robots.gui;

import com.robots.models.GameField;
import com.robots.models.MazeCell;
import com.robots.models.Robot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.*;
import javax.swing.JPanel;

public class GameVisualizer extends JPanel {
    private static final int scale = 50;
    private static final int bias = 10;

    private final GameField field;
    private final List<Robot> models;
    private final Map<Integer, Color> colors = Map.of(1, Color.RED, 2, Color.GREEN);

    public GameVisualizer(GameField field, List<Robot> models) {
        this.field = field;
        this.models = Collections.unmodifiableList(models);
        Timer m_timer = new Timer("events generator", true);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point mazePoint = getMazeCoordinates(e.getPoint());
                if (targetCanBeChanged(mazePoint)) {
                    System.out.println("Got new destination - " + mazePoint);
                    field.setMazeEnd(mazePoint);
                    for (Robot r : models) {
                        r.stopMoving();
                    }
                    repaint();
                }
            }
        });
        setDoubleBuffered(true);
    }

    private boolean targetCanBeChanged(Point newTarget) {
        return field.getMazeCellType(newTarget) == MazeCell.EMPTY
                && !newTarget.equals(field.getMazeEnd());
    }

    private int getDrovingCoordinates(int point) {
        return point * scale;
    }

    private Point getDrovingCoordinates2(Point point) {
        return new Point(point.x * scale, point.y * scale);
    }

    public Point getMazeCoordinates(Point p) {
        int x = p.x / scale;
        int y = p.y / scale;
        return new Point(x, y);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawField(g2d);
        for (Robot m : models) {
            drawModel(g2d, m);
            drawWay(m, g2d);
        }
    }

    private void drawField(Graphics2D g) {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                g.drawRect(getDrovingCoordinates(x), getDrovingCoordinates(y), scale, scale);
                if (field.getMazeCellType(x, y) == MazeCell.WALL) {
                    g.fillRect(getDrovingCoordinates(x), getDrovingCoordinates(y), scale, scale);
                }
            }
        }
        int x = getDrovingCoordinates(field.getMazeEnd().x);
        int y = getDrovingCoordinates(field.getMazeEnd().y);
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, scale, scale);
    }

    private void drawModel(Graphics2D g, Robot r) {
        Point robotPos = getDrovingCoordinates2(r.getCurrentPosition());
        AffineTransform t = AffineTransform.getRotateInstance(0, robotPos.x, robotPos.y);
        g.setTransform(t);
        int bodyDiam1 = 30;
        int bodyDiam2 = 10;
        switch (r.getDirection()) {
            case DOWN:
                visualizeDirection(r, g, bodyDiam2, bodyDiam1, 0, 10);
                break;
            case UP:
                visualizeDirection(r, g, bodyDiam2, bodyDiam1, 0, -10);
                break;
            case LEFT:
                visualizeDirection(r, g, bodyDiam1, bodyDiam2, -10, 0);
                break;
            case RIGHT:
                visualizeDirection(r, g, bodyDiam1, bodyDiam2, 10, 0);
                break;
            case OLD:
                visualizeDirection(r, g, bodyDiam1, bodyDiam1, 0, 0);
                break;
        }
    }

    private void visualizeDirection(Robot r, Graphics2D g, int diam1, int diam2, int headBiasX, int headBiasY) {
        Point robotPos = getDrovingCoordinates2(r.getCurrentPosition());
        g.setColor(colors.get(r.id));
        fillOval(g, robotPos.x + r.id * bias, robotPos.y + r.id * bias, diam1, diam2);
        g.setColor(Color.BLACK);
        drawOval(g, robotPos.x + r.id * bias, robotPos.y + r.id * bias, diam1, diam2);
        g.setColor(Color.WHITE);
        fillOval(g, robotPos.x + headBiasX + r.id * bias, robotPos.y + headBiasY + r.id * bias, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotPos.x + headBiasX + r.id * bias, robotPos.y + headBiasY + r.id * bias, 5, 5);
    }

    private void drawWay(Robot r, Graphics2D g) {
        Point[] way = r.way.toArray(new Point[0]);
        for (var i = 1; i < r.way.toArray().length; i++) {
            Point point1 = getDrovingCoordinates2(way[i - 1]);
            Point point2 = getDrovingCoordinates2(way[i]);
            g.setColor(colors.get(r.id));
            g.drawLine(point1.x + r.id * bias, point1.y + r.id * bias,
                    point2.x + r.id * bias, point2.y + r.id * bias);
        }
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
}
