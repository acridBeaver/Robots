package com.robots.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public interface Deserializer<T extends Serializable> {
    T deserialize(InputStream objStream) throws IOException, ClassNotFoundException;
}
