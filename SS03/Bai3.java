import java.util.*;

record Users(String username,String email){}

class UserRepository{
    List<Users>users=List.of(
            new Users("alice","alice@gmail.com"),
            new Users("bob","bob@yahoo.com"),
            new Users("charlie","charlie@gmail.com")
    );

    Optional<User>findUserByUsername(String username){
        return users.stream().filter(u->u.username().equals(username)).findFirst();
    }
}

public class Main{
    public static void main(String[]args){
        UserRepository repo=new UserRepository();

        Users user=repo.findUserByUsername("alice")
                .orElse(new User("Guest","guest@email.com"));

        if(!user.username().equals("Guest")){
            System.out.println("Welcome "+user.username());
        }else{
            System.out.println("Guest login");
        }
    }
}