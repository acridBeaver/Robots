package gui;

import javax.swing.*;

public class ClosingFramePanel {
    public static void closingLogic(MainApplicationFrame frame){
        String[] options = {"Да", "Нет"};

        if (getResultForOption(frame, options) == 0) {
            frame.loadWindowPresets();
        }
    }

    private static int getResultForOption(MainApplicationFrame frame, String[] options) {
        return JOptionPane.showOptionDialog(frame, "Загрузить последнее состояние окон",
                "Подтверждение", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
    }
}
