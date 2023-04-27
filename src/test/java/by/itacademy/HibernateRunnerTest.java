package by.itacademy;

import by.itacademy.entity.Role;
import by.itacademy.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HibernateRunnerTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
    private static UserEntity user;

    @Test
    public void aTestSaveUser() {
        user = UserEntity.builder()
                .username("TestUser")
                .password("TestPassword")
                .role(Role.ADMIN)
                .birth(LocalDate.now())
                .build();

        Session session = FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);

        Assert.assertNotNull(session.get(UserEntity.class, user.getId()));
        transaction.commit();
    }

    @Test
    public void bTestUpdateUser() {
        user.setRole(Role.USER);
        Session session = FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);

        Assert.assertNotNull(session.get(UserEntity.class, user.getId()));
        Assert.assertEquals(user.getRole(), Role.USER);
        transaction.commit();
    }

    @Test
    public void cTestDeleteUser() {
        Session session = FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);

        UserEntity userById = session.get(UserEntity.class, 1L);
        Assert.assertNull(userById);
        transaction.commit();
    }

}