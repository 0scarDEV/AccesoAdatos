package fich24.oscarfp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(FileOutputStream fileOutputStream) throws IOException {
        super(fileOutputStream);
    }

    @Override protected void writeStreamHeader() throws IOException {
        reset();
    }
}
