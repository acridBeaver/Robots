package serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class WindowPresetToDatConverter {
    private static final Path DEFAULT_DIR = Path.of("presets");

    private final Serializer<WindowPreset> presetSerializer;
    private final Deserializer<WindowPreset> presetDeserializer;


    public WindowPresetToDatConverter() {
        presetSerializer = new WindowPresetSerializer();
        presetDeserializer = new WindowPresetDeserializer();
    }

    public void convertToFile(String filename, WindowPreset preset) {
        try {
            if (!Files.exists(DEFAULT_DIR)) {
                Files.createDirectory(DEFAULT_DIR);
            }

            Path pathToPreset = DEFAULT_DIR.resolve(filename);
            File presetFile = pathToPreset.toFile();
            presetFile.createNewFile();
            presetSerializer.serialize(preset, new FileOutputStream(presetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<WindowPreset> getFromFile(String filename) {
        File file = DEFAULT_DIR.resolve(filename).toFile();
        try {
            return Optional.of(presetDeserializer.deserialize(new FileInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public boolean hasPresets() { // TODO: вызвать для проверки наличия пресетов окон (в момент открытия окна)
        File presetFolder = DEFAULT_DIR.toFile();
        return presetFolder.isDirectory()
                && Objects.requireNonNullElse(presetFolder.list(), new String[0]).length > 0;
    }
}
