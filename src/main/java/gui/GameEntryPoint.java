package gui;

import gui.windows.MainWindow;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GameEntryPoint
{
    public static void main(String[] args) {
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
      });
    }}
