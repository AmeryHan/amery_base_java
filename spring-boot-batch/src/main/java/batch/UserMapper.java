package batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class UserMapper implements FieldSetMapper<User> {

    @Override
    public User mapFieldSet(FieldSet fieldSet) throws BindException {
        User user = new User();
        user.setName(fieldSet.readString(0));
        user.setAge(fieldSet.readInt(1));
        return user;
    }
}
