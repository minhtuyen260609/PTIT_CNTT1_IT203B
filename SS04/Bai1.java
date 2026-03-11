import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserValidator{
    boolean isValidUsername(String username){
        if(username==null)return false;
        if(username.length()<6||username.length()>20)return false;
        if(username.contains(" "))return false;
        return true;
    }
}

public class Bai1{
    UserValidator validator=new UserValidator();

    @Test
    void TC01(){
        boolean result=validator.isValidUsername("user123");
        assertTrue(result);
    }

    @Test
    void TC02(){
        boolean result=validator.isValidUsername("abc");
        assertFalse(result);
    }

    @Test
    void TC03(){
        boolean result=validator.isValidUsername("user name");
        assertFalse(result);
    }
}