package web.DAO;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import java.util.List;

@Repository
public class UserDAO implements DAO {

    private final EntityManager em;

    @Autowired
    public UserDAO(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    public List<User> index() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Transactional(readOnly = true)
    public User show(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void update(Long id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    @Transactional
    public void delete(Long id) {
        em.remove(show(id));


    }
}
