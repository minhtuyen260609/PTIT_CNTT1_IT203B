import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class User{
    String email;
    User(String email){
        this.email=email;
    }
}

class UserProfile{
    String email;
    LocalDate birthDate;
    UserProfile(String email,LocalDate birthDate){
        this.email=email;
        this.birthDate=birthDate;
    }
}

class ProfileService{
    User updateProfile(User existingUser,UserProfile newProfile,List<User>allUsers){
        if(newProfile.birthDate.isAfter(LocalDate.now()))return null;
        for(User u:allUsers){
            if(!u.equals(existingUser)&&u.email.equals(newProfile.email))return null;
        }
        existingUser.email=newProfile.email;
        return existingUser;
    }
}

public class Bai6{
    ProfileService service=new ProfileService();

    @Test
    void validUpdate(){
        User u=new User("old@gmail.com");
        UserProfile p=new UserProfile("new@gmail.com",LocalDate.of(2000,1,1));
        List<User>list=new ArrayList<>();
        User result=service.updateProfile(u,p,list);
        assertNotNull(result);
    }

    @Test
    void futureBirthDate(){
        User u=new User("old@gmail.com");
        UserProfile p=new UserProfile("new@gmail.com",LocalDate.now().plusDays(1));
        List<User>list=new ArrayList<>();
        User result=service.updateProfile(u,p,list);
        assertNull(result);
    }

    @Test
    void duplicateEmail(){
        User u=new User("old@gmail.com");
        User other=new User("dup@gmail.com");
        UserProfile p=new UserProfile("dup@gmail.com",LocalDate.of(2000,1,1));
        List<User>list=Arrays.asList(other);
        User result=service.updateProfile(u,p,list);
        assertNull(result);
    }

    @Test
    void sameEmail(){
        User u=new User("same@gmail.com");
        UserProfile p=new UserProfile("same@gmail.com",LocalDate.of(2000,1,1));
        List<User>list=new ArrayList<>();
        User result=service.updateProfile(u,p,list);
        assertNotNull(result);
    }

    @Test
    void emptyUserList(){
        User u=new User("old@gmail.com");
        UserProfile p=new UserProfile("new@gmail.com",LocalDate.of(2000,1,1));
        List<User>list=new ArrayList<>();
        User result=service.updateProfile(u,p,list);
        assertNotNull(result);
    }

    @Test
    void duplicateAndFuture(){
        User u=new User("old@gmail.com");
        User other=new User("dup@gmail.com");
        UserProfile p=new UserProfile("dup@gmail.com",LocalDate.now().plusDays(1));
        List<User>list=Arrays.asList(other);
        User result=service.updateProfile(u,p,list);
        assertNull(result);
    }
}