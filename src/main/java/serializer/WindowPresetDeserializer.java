package serializer;

import gui.model.WindowPreset;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class WindowPresetDeserializer implements Deserializer<WindowPreset> {
    @Override
    public WindowPreset deserialize(InputStream objStream)
        throws IOException, ClassNotFoundException {
        ObjectInputStream objectStream = new ObjectInputStream(objStream);
        return (WindowPreset) objectStream.readObject();
    }
}
