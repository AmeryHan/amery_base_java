package amery.entity;

import amery.jdk.lambda.PersonInterface;
import org.springframework.batch.sample.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> getMaleList(PersonInterface filter) {
        List<Person> res = new ArrayList<Person>();
        persons.forEach(
                (Person person) ->
                {
                    if (filter.test(person)) {//调用 PersonInterface 的方法
                        res.add(person);
                    }
                }
        );
        return res;
    }

}
