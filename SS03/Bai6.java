import java.util.*;
import java.util.stream.*;

record Post(List<String>tags){}

public class Bai6{
    public static void main(String[]args){
        List<Post>posts=List.of(
                new Post(List.of("java","backend")),
                new Post(List.of("python","data"))
        );

        List<String>allTags=posts.stream()
                .flatMap(p->p.tags().stream())
                .collect(Collectors.toList());

        System.out.println(allTags);
    }
}