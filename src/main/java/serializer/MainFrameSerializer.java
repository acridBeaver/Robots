package serializer;

import gui.MainApplicationFrame;
import gui.model.WindowPreset;
import java.util.Optional;

public class MainFrameSerializer {
  private static final WindowPresetConverter converter = new WindowPresetConverter();

  public static void serialize(MainApplicationFrame frame) {
    WindowPreset mainPreset = new WindowPreset();
    mainPreset.setLocation(frame.getLocation());
    mainPreset.setMinimized(false);
    mainPreset.setSize(frame.getSize());
    converter.convertToFile("Main Window", mainPreset);
  }

  public static void deserialize(MainApplicationFrame frame) {
    Optional<WindowPreset> presetWrapper = converter.getFromFile("Main Window");
    if (presetWrapper.isEmpty()) {
      return;
    }

    WindowPreset actualPreset = presetWrapper.get();
    frame.setLocation(actualPreset.getLocation());
    frame.setSize(actualPreset.getSize());
  }
}
