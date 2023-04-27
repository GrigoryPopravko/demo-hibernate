package by.itacademy;

import by.itacademy.entity.AuthorEntity;
import by.itacademy.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try (SessionFactory sessionFactory = configuration.configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
//            session.persist();
//            session.merge();
//            session.remove();
//            session.get();


            session.persist(BookEntity.builder()
                    .title("Punishment")
                    .pages(300)
                    .build());
            session.persist(AuthorEntity.builder()
                    .fullName("Fedor Dostoevskii")
                    .build());

            transaction.commit();
        }
    }
}