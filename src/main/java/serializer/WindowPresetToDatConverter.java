package serializer;

public class WindowPresetToDatConverter {
    private final Serializer<WindowPreset> presetSerializer;
    private final Deserializer<WindowPreset> presetDeserializer;


    public WindowPresetToDatConverter() {
        presetSerializer = new WindowPresetSerializer();
        presetDeserializer = new WindowPresetDeserializer();
    }

    public void convertToFile(String filename, WindowPreset preset) {

    }

    public WindowPreset getFromFile(String filename) {

    }
}
