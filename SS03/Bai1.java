import java.util.*;

record user(String username,String email,String status){}

public class Bai1{
    public static void main(String[]args){
        List<user>users=List.of(
                new user("alice","alice@email.com","ACTIVE"),
                new user("bob","bob@email.com","INACTIVE"),
                new user("charlie","charlie@email.com","ACTIVE")
        );
        users.forEach(System.out::println);
    }
}