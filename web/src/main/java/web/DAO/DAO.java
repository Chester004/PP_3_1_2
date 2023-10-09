package web.DAO;

import web.models.User;

import java.util.List;

public interface DAO {
    List<User> index();
    void save(User user);
    User show(Long id);
    void update(Long id, User updatedUser);
    void delete(Long id);
}
