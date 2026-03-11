import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProcessor{
    String processEmail(String email){
        if(email==null||!email.contains("@"))throw new IllegalArgumentException();
        String[]parts=email.split("@");
        if(parts.length!=2||parts[1].isEmpty())throw new IllegalArgumentException();
        return email.toLowerCase();
    }
}

public class Bai3{
    UserProcessor processor;

    @BeforeEach
    void setup(){
        processor=new UserProcessor();
    }

    @Test
    void validEmail(){
        String result=processor.processEmail("user@gmail.com");
        assertEquals("user@gmail.com",result);
    }

    @Test
    void missingAt(){
        assertThrows(IllegalArgumentException.class,()->{
            processor.processEmail("usergmail.com");
        });
    }

    @Test
    void missingDomain(){
        assertThrows(IllegalArgumentException.class,()->{
            processor.processEmail("user@");
        });
    }

    @Test
    void normalizeEmail(){
        String result=processor.processEmail("Example@Gmail.com");
        assertEquals("example@gmail.com",result);
    }
}