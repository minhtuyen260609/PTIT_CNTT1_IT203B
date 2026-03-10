import java.util.*;

record users(String username,String email){}

public class Bai2{
    public static void main(String[]args){
        List<users>users=List.of(
                new users("alice","alice@gmail.com"),
                new users("bob","bob@yahoo.com"),
                new users("charlie","charlie@gmail.com")
        );

        users.stream()
                .filter(u->u.email().endsWith("gmail.com"))
                .forEach(u->System.out.println(u.username()));
    }
}