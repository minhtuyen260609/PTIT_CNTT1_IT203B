import java.util.*;

record User(String username,String email){}

public class Bai5{
    public static void main(String[]args){
        List<User>users=List.of(
                new User("alexander","a@gmail.com"),
                new User("charlotte","c@gmail.com"),
                new User("Benjamin","b@gmail.com"),
                new User("tom","t@gmail.com"),
                new User("anna","a2@gmail.com")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u)->u.username().length()).reversed())
                .limit(3)
                .forEach(u->System.out.println(u.username()));
    }
}