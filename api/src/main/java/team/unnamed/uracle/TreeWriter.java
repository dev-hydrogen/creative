package team.unnamed.uracle;

import java.io.OutputStream;

public interface TreeWriter {

    Context enter(ResourceLocation location, String suffix);

    Context enter(ResourceLocation location, String suffix, String extension);

    Context enter(String location);

    abstract class Context extends OutputStream implements AutoCloseable {

        public abstract void startObject();

        public abstract void endObject();

        public abstract void startArray();

        public abstract void endArray();

        public abstract void writeKey(String key);

        public abstract void writeStringField(String key, String value);

        public abstract void writeBooleanField(String key, boolean value);

        public abstract void writeIntField(String key, int value);

        public abstract void writeSeparator();

        public abstract void writeStringValue(String value);

        public abstract void writeIntValue(int value);

        public void writePart(Element.Part value) {
            value.write(this);
        }

        @Override
        public abstract void close();

    }

}
