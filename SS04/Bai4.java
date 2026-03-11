import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordService{
    String evaluatePasswordStrength(String password){
        if(password.length()<8)return"Yếu";
        boolean upper=password.matches(".*[A-Z].*");
        boolean lower=password.matches(".*[a-z].*");
        boolean digit=password.matches(".*[0-9].*");
        boolean special=password.matches(".*[^a-zA-Z0-9].*");
        if(upper&&lower&&digit&&special)return"Mạnh";
        if(password.length()>=8&&(upper||lower||digit||special))return"Trung bình";
        return"Yếu";
    }
}

public class Bai4{
    PasswordService service=new PasswordService();

    @Test
    void testPasswords(){
        assertAll(
                ()->assertEquals("Mạnh",service.evaluatePasswordStrength("Abc123!@")),
                ()->assertEquals("Trung bình",service.evaluatePasswordStrength("abc123!@")),
                ()->assertEquals("Trung bình",service.evaluatePasswordStrength("ABC123!@")),
                ()->assertEquals("Trung bình",service.evaluatePasswordStrength("Abcdef!@")),
                ()->assertEquals("Trung bình",service.evaluatePasswordStrength("Abc12345")),
                ()->assertEquals("Yếu",service.evaluatePasswordStrength("Ab1!")),
                ()->assertEquals("Yếu",service.evaluatePasswordStrength("password")),
                ()->assertEquals("Yếu",service.evaluatePasswordStrength("ABC12345"))
        );
    }
}