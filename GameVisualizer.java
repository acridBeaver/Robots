package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import engine.Mover;
import engine.Const;
import model.GameField;
import model.MazeCell;
/* TODO: Перепиши класс чтобы:
1. В конструктор передавался объект класса GameField

1. В методе paint() нарисовался лабиринт на основании GameField:
    2.1 лабиринт является двумерным массивом char и имеет целочисленные координаты
    2.2 у лабиринта есть свободные клетки (MazeCell.EMPTY) и стены (MazeCell.WALL). Для свободной
    клетки пусть будет пустой квадрат, а для стены закрашенный

3. Продумай ещё, что тебе надо будет по координатам окна определять на какую клетку
нажали (то есть мы нажимаем на пустую клетку лабиринта и туда пойдут роботы => тебе нужно по
координатам Point понять на что нажали)

4. Также напиши метод который наоборот по целочисленным координатам в лабиринте вычисляет
Point в окне (понадобится чтоб понять куда перемещать робота в окне)

 */

public class GameVisualizer extends JPanel
{
    private final GameField field;

    private static Timer initTimer() 
    {
        return new Timer("events generator", true);
    }

    public GameVisualizer(GameField field)
    {
        this.field = field;
        Timer m_timer = initTimer();
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Mover.onModelUpdateEvent();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    private int getDrovingCoordinates(int point){
        return point*5;
    }

    protected void setTargetPosition(Point p)
    {
        Const.m_targetPositionX = p.x;
        Const.m_targetPositionY = p.y;
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
        drawRobot(g2d, round(Const.m_robotPositionX), round(Const.m_robotPositionY), Const.m_robotDirection);
        drawTarget(g2d,Const.m_targetPositionX, Const.m_targetPositionY);
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
        for (int x = 0; x <= field.getMazeEnd().x; x++)
            for (int y = 0; y <= field.getMazeEnd().y; y++){
                if (field.getMazeCellType(x, y) == MazeCell.WALL){
                    g.fillRect(x, y, 5, 5);
                }
            }
    }
    
    private void drawRobot(Graphics2D g, int x, int y, double direction)
    {
        int robotCenterX = round(Const.m_robotPositionX);
        int robotCenterY = round(Const.m_robotPositionY);
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY); 
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
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
