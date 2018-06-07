package ru.job4j.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.model.User;

import java.util.List;

public class ImportUser {

    private final Storage storage;

    public ImportUser(Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }

    public List<User> getAll() {
        return this.storage.getAll();
    }

    public void delete(User user) {
        this.storage.delete(user);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ImportUser importUser = (ImportUser) context.getBean("import");
        User anton = new User("Anton");
        importUser.add(anton);
        importUser.add(new User("Boris"));
        importUser.add(new User("Victor"));
        importUser.delete(anton);

        List<User> users = importUser.getAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
