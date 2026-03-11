import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserService{
    boolean checkRegistrationAge(int age){
        if(age<0)throw new IllegalArgumentException();
        return age>=18;
    }
}

public class Bai2{
    UserService service=new UserService();

    @Test
    void age18(){
        boolean result=service.checkRegistrationAge(18);
        assertEquals(true,result);
    }

    @Test
    void age17(){
        boolean result=service.checkRegistrationAge(17);
        assertEquals(false,result);
    }

    @Test
    void ageNegative(){
        assertThrows(IllegalArgumentException.class,()->{
            service.checkRegistrationAge(-1);
        });
    }
}