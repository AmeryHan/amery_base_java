package amery.jdk8.stream.duplicate;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class RemoveTest {


    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("张三", 1000),
                new User("张三", 1100),
                new User("张三", 1200),
                new User("李四", 1000),
                new User("李四", 1100),
                new User("王五", 2500),
                new User("赵六", 1800));
        printList(users);
        List<User> users2 = removeDuplicateUser3(users);
        printList(users2);

    }

    public static void printList(List<User> list) {
        for (User user : list) {
            System.out.println(user.getUsername() + ", " + user.getMoney());
        }
        System.out.println("-------------------------------");

    }


    public static List<User> removeDuplicateUser(List<User> users) {
        Set<User> set = new TreeSet<User>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getUsername().compareTo(o2.getUsername());
            }
        });

        set.addAll(users);
        return new ArrayList<>(set);
    }


    public static List<User> removeDuplicateUser2(List<User> users) {
        //new TreeSet<>(Comparator.comparing(User::getMoney));
        Set<User> set = new TreeSet<>(Comparator.comparing(User::getUsername));

        set.addAll(users);
        return new ArrayList<>(set);
    }


    public static List<User> removeDuplicateUser3(List<User> users) {
        Set<User> set = new TreeSet<>(Comparator.comparing(User::getUsername));

        List<User> unique = users.stream().collect(
                collectingAndThen(
                        toCollection(() -> set), ArrayList::new)
        );

        List<User> distinctClass = users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getUsername() + ";" + o.getMoney()))),
                ArrayList::new));

        return users.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(User::getUsername))), ArrayList::new));
    }


    public static List<User> removeDuplicateUser4(List<User> users) {
        Set<User> set = new TreeSet<>(Comparator.comparing(User::getUsername));

        List<User> unique = users.stream().collect(
                collectingAndThen(
                        toCollection(() -> set), ArrayList::new)
        );

        Set set1 = users.stream().collect(Collectors.toCollection(() -> set));
        return Lists.newArrayList(set1);
    }
}
