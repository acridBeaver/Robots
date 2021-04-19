package serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public interface Serializer<T extends Serializable> {
    void serialize(T obj, OutputStream out) throws IOException;
}
