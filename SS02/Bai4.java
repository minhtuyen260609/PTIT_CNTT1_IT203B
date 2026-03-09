import java.util.*;
import java.util.function.*;

class User{
    String username;
    User(){
        this.username="guest";
    }
    User(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public String toString(){
        return username;
    }
}

public class Bai4{
    public static void main(String[]args){
        List<User>users=Arrays.asList(
                new User("minh"),
                new User("anh"),
                new User("tuan")
        );

        Function<User,String>getUsername=User::getUsername;
        Consumer<String>printer=System.out::println;
        Supplier<User>createUser=User::new;

        for(User u:users){
            printer.accept(getUsername.apply(u));
        }

        User newUser=createUser.get();
        printer.accept(newUser.getUsername());
    }
}