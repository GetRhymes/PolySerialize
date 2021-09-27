import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolySerializableTest {

    @Test
    public void serializableTest() throws IOException, ClassNotFoundException {
        MessageDTO message = new MessageDTO("2021-09-23", "Nikita", "Hello, World!", null, null);
        MessageDTO deserializableMessage =
                PolySerializable.deserializeToMessage(PolySerializable.serializeMessage(message));
        System.out.println(message);
        System.out.println(deserializableMessage);
        assertEquals(message, deserializableMessage);
    }
}
