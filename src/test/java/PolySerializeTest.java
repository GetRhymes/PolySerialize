import com.poly.models.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolySerializeTest {

    @Test
    public void serializeTest() {
        Message message = new Message(
                "2021-09-23", "Nikita", "", null, null);
        Message messageAfterParse = new Message();
        messageAfterParse.parseToMessage(message.toTransferString());
        Assertions.assertEquals(message, messageAfterParse);
    }
}
