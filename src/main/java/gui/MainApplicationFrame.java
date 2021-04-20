package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import log.Logger;
import serializer.WindowPreset;
import serializer.WindowPresetToDatConverter;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается.
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 *
 */
public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final Map<String, Window> windowRegistry;
    private final WindowPresetToDatConverter converter;

    private static final int PIXEL_INSET = 50;

    public MainApplicationFrame() {
        windowRegistry = new HashMap<>();
        converter = new WindowPresetToDatConverter();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(PIXEL_INSET, PIXEL_INSET,
            screenSize.width  - PIXEL_INSET * 2,
            screenSize.height - PIXEL_INSET * 2);

        setContentPane(desktopPane);

        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        addWindow(logWindow);

        Window gameWindow = new GameWindow("Game Field");
        addWindow(gameWindow);

        Menu menuBar = new Menu(this);
        setJMenuBar(menuBar.generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addWindow(Window frame)
    {
        windowRegistry.put(frame.getTitle(), frame);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void saveWindowPresets() { // TODO: вызвать при событии закрытия окна MainApplicationFrame
        for (Map.Entry<String, Window> entry : windowRegistry.entrySet()) {
            if (entry.getValue().isClosed()) {
                continue;
            }

            WindowPreset currentPreset = entry.getValue().getPreset();
            converter.convertToFile(entry.getKey(), currentPreset);
        }
    }

    private void loadWindowPresets() { // TODO: вызвать при событии открытия / запуска окна MainApplicationFrame, если нажали ДА в диалоге
        for (Map.Entry<String, Window> entry : windowRegistry.entrySet()) {
            Optional<WindowPreset> preset = converter.getFromFile(entry.getKey());
            preset.ifPresent(p -> entry.getValue().applyPreset(p));
        }
    }
}
