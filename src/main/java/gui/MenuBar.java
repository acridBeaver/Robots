package gui;

import gui.windows.MainWindow;
import log.Logger;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBar {
    private final MainWindow frame;

    public MenuBar(MainWindow frame){
        this.frame = frame;
    }

    public JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createVisionMenu());
        menuBar.add(createClosingMenu());
        menuBar.add(createTestMenu());
        return menuBar;
    }

    private JMenu createVisionMenu(){
        JMenu displayModeMenu = createMenu(
                "Режим отображения",
                KeyEvent.VK_V,
                "Управление режимом отображения приложения");

        JMenuItem systemMenuItem = createMenuItem(
                "Системная схема",
                (event) -> {
                    setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    frame.invalidate();
                });

        JMenuItem crossPlatformMenuItem = createMenuItem(
                "Универсальная схема",
                (event) -> {
                    setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    frame.invalidate();
                });

        displayModeMenu.add(systemMenuItem);
        displayModeMenu.add(crossPlatformMenuItem);
        return displayModeMenu;
    }

    private JMenu createClosingMenu(){
        JMenu closingMenu = createMenu(
                "Выход",
                KeyEvent.VK_C,
                "Выход из приложения"
        );

        JMenuItem closeIt = createMenuItem(
                "Выходи",
                (event) -> System.exit(0));

        closingMenu.add(closeIt);
        return closingMenu;
    }

    private JMenu createTestMenu(){
        JMenu testMenu = createMenu("Тесты", KeyEvent.VK_T, "Тестовые команды");

        JMenuItem logMessageItem = createMenuItem(
                "Сообщение в лог",
                (event) -> Logger.debug("Новая строка"));

        testMenu.add(logMessageItem);
        return testMenu;
    }

    private JMenuItem createMenuItem(String text, ActionListener listener) {
        JMenuItem item = new JMenuItem(text, KeyEvent.VK_S);
        item.addActionListener(listener);
        return item;
    }

    private JMenu createMenu(String title, int mnemonic, String description) {
        JMenu menu = new JMenu(title);
        menu.setMnemonic(mnemonic);
        menu.getAccessibleContext().setAccessibleDescription(description);
        return menu;
    }

    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(frame);
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}
