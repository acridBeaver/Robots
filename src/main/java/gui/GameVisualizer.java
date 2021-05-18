package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import engine.Const;
import model.GameField;
import model.MazeCell;
import model.Robot;

public class GameVisualizer extends JPanel
{
    private final GameField field;
    private final List<Robot> models;

    private static Timer initTimer() 
    {
        return new Timer("events generator", true);
    }

    public GameVisualizer(GameField field, List<Robot> models)
    {
        this.field = field;
        this.models = models;
        Timer m_timer = initTimer();
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Point mazePoint = translate(e.getPoint());
                System.out.println("Got new destination - " + mazePoint);
                field.setMazeEnd(mazePoint);
                for (Robot r : models) {
                    r.stopMoving();
                }

                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    private int getDrovingCoordinates(int point){
        return point * 50;
    }

    private Point getDrovingCoordinates2(Point point){
        return new Point(point.x * 50, point.y * 50);
    }

    public Point translate(Point p)
    {
        int x = p.x / 50;
        int y = p.y / 50;
        return new Point(x,y);
    }

    
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
    
    private static int round(double value)
    {
        return (int)(value + 0.5);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawField(g2d);
        for (Robot r : models) {
            drawRobot(g2d, r);
        }

    }
    
    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    
    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawField(Graphics2D g)
    {
        for (int x = 0; x < field.getWidth(); x++)
            for (int y = 0; y < field.getHeight(); y++){
                g.drawRect(getDrovingCoordinates(x), getDrovingCoordinates(y),50,50);
                if (field.getMazeCellType(x, y) == MazeCell.WALL){
                    g.fillRect(getDrovingCoordinates(x), getDrovingCoordinates(y), 50, 50);
                }
            }
    }
    
    private void drawRobot(Graphics2D g, Robot r)
    {
        Point robotPos = getDrovingCoordinates2(r.getCurrentPosition());
        AffineTransform t = AffineTransform.getRotateInstance(0, robotPos.x, robotPos.y);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotPos.x + 25, robotPos.y + 25, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotPos.x + 25, robotPos.y + 25, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotPos.x  + 35, robotPos.y + 25, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotPos.x  + 35, robotPos.y + 25, 5, 5);
    }
    
    private void drawTarget(Graphics2D g, int x, int y)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0); 
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}
