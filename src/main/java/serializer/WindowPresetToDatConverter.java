package serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class WindowPresetToDatConverter {
    private static final String DEFAULT_PRESET_FOLDER = "presets/";

    private final Serializer<WindowPreset> presetSerializer;
    private final Deserializer<WindowPreset> presetDeserializer;


    public WindowPresetToDatConverter() {
        presetSerializer = new WindowPresetSerializer();
        presetDeserializer = new WindowPresetDeserializer();
    }

    public void convertToFile(String filename, WindowPreset preset) {
        File file = new File(DEFAULT_PRESET_FOLDER + filename);
        try {
            file.createNewFile();
            presetSerializer.serialize(preset, new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<WindowPreset> getFromFile(String filename) {
        File file = new File(DEFAULT_PRESET_FOLDER + filename);
        try {
            return Optional.of(presetDeserializer.deserialize(new FileInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
    }
}
