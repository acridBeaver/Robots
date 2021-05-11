package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import log.Logger;
import gui.model.WindowPreset;
import serializer.MainFrameSerializer;
import serializer.WindowPresetConverter;
import static gui.ClosingFramePanel.closingLogic;

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
    private final WindowPresetConverter converter;

    private static final int PIXEL_INSET = 50;

    public MainApplicationFrame() {
        windowRegistry = new HashMap<>();
        converter = new WindowPresetConverter();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(PIXEL_INSET, PIXEL_INSET,
            screenSize.width  - PIXEL_INSET * 2,
            screenSize.height - PIXEL_INSET * 2);

        setContentPane(desktopPane);

        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        addWindow(logWindow);

        Window gameWindow = new GameWindow("Game Field");
        addWindow(gameWindow);

        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar.generateMenuBar());
        if (converter.hasPresets()){
            closingLogic(this);
            MainFrameSerializer.deserialize(this);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new ClosingFrameAdapter());
    }

    private void addWindow(Window frame)
    {
        windowRegistry.put(frame.getTitle(), frame);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void saveWindowPresets() {
        for (Map.Entry<String, Window> entry : windowRegistry.entrySet()) {
            if (entry.getValue().isClosed()) {
                continue;
            }

            WindowPreset currentPreset = entry.getValue().getPreset();
            converter.convertToFile(entry.getKey(), currentPreset);
        }
    }

    public void loadWindowPresets() {
        for (Map.Entry<String, Window> entry : windowRegistry.entrySet()) {
            Optional<WindowPreset> preset = converter.getFromFile(entry.getKey());
            preset.ifPresent(p -> entry.getValue().applyPreset(p));
        }
    }

    public class ClosingFrameAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            saveWindowPresets();
            MainFrameSerializer.serialize(MainApplicationFrame.this);
        }
    }
}
