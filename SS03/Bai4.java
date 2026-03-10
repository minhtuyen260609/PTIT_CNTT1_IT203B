import java.util.*;
import java.util.stream.*;

record User(String username,String email){}

public class Bai4{
    public static void main(String[]args){
        List<User>users=List.of(
                new User("alice","alice@gmail.com"),
                new User("bob","bob@yahoo.com"),
                new User("alice","alice2@gmail.com"),
                new User("charlie","charlie@gmail.com"),
                new User("bob","bob2@yahoo.com")
        );

        Collection<User>uniqueUsers=users.stream()
                .collect(Collectors.toMap(
                        User::username,
                        u->u,
                        (u1,u2)->u1
                )).values();

        uniqueUsers.forEach(System.out::println);
    }
}