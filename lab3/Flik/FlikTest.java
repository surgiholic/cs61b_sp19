import static org.junit.Assert.*;
import org.junit.Test;
public class FlikTest {
    @Test
    public void testIsSameNumber () {
        assertFalse(Flik.isSameNumber(1, 2));
        assertTrue(Flik.isSameNumber(129,129));
    }
}
