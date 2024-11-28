package web.dao;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDAOImpl {
    private static int PEOPLE_COUNT;
    private List<User> users;

    {
        users = new ArrayList<User>();
        users.add(new User(++PEOPLE_COUNT, "Oleg"));
        users.add(new User(++PEOPLE_COUNT, "Andrey"));
        users.add(new User(++PEOPLE_COUNT, "James"));
        users.add(new User(++PEOPLE_COUNT, "Mary"));
    }

    public List<User> allUsers() {
        return users;
    }

    public void add(User user) {
        user.setId(++PEOPLE_COUNT);
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
        --PEOPLE_COUNT;
    }

    public void edit(User user) {
        // Находим пользователя по ID
        for (User userToBeEdited : users) {
            if (userToBeEdited.getId() == user.getId()) {
                userToBeEdited.setName(user.getName());  // Обновляем имя
                return;  // Завершаем выполнение, как только нашли нужного пользователя
            }
        }
    }

    public User getById(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}
