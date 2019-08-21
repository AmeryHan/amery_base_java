package amery.jdk.lambda;

import org.springframework.batch.sample.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class PeoplePredicate {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> getMaleList(Predicate<Person> predicate) {
        List<Person> res = new ArrayList<Person>();
        persons.forEach(
                person -> {
                    if (predicate.test(person)) {//调用 Predicate 的抽象方法 test
                        res.add(person);
                    }
                });
        return res;
    }
}