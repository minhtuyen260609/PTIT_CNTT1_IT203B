import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

enum Role{ADMIN,MODERATOR,USER}
enum Action{DELETE_USER,LOCK_USER,VIEW_PROFILE}

class User{
    Role role;
    User(Role role){
        this.role=role;
    }
}

class AccessService{
    boolean canPerformAction(User user,Action action){
        if(user.role==Role.ADMIN)return true;
        if(user.role==Role.MODERATOR){
            if(action==Action.DELETE_USER)return false;
            return true;
        }
        if(user.role==Role.USER){
            return action==Action.VIEW_PROFILE;
        }
        return false;
    }
}

public class Bai5{
    AccessService service=new AccessService();
    User user;

    @AfterEach
    void cleanup(){
        user=null;
    }

    @Test
    void adminPermissions(){
        user=new User(Role.ADMIN);
        assertTrue(service.canPerformAction(user,Action.DELETE_USER));
        assertTrue(service.canPerformAction(user,Action.LOCK_USER));
        assertTrue(service.canPerformAction(user,Action.VIEW_PROFILE));
    }

    @Test
    void moderatorPermissions(){
        user=new User(Role.MODERATOR);
        assertFalse(service.canPerformAction(user,Action.DELETE_USER));
        assertTrue(service.canPerformAction(user,Action.LOCK_USER));
        assertTrue(service.canPerformAction(user,Action.VIEW_PROFILE));
    }

    @Test
    void userPermissions(){
        user=new User(Role.USER);
        assertFalse(service.canPerformAction(user,Action.DELETE_USER));
        assertFalse(service.canPerformAction(user,Action.LOCK_USER));
        assertTrue(service.canPerformAction(user,Action.VIEW_PROFILE));
    }
}