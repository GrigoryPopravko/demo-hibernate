package by.itacademy;

import by.itacademy.entity.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalTime;

public class HibernateRunner {

    private static final Logger logger = LogManager.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try (SessionFactory sessionFactory = configuration.configure().buildSessionFactory()) {
            LocalTime start = LocalTime.now();
            Session session = sessionFactory.openSession();
            logger.error("Открыл сессию");
            Transaction transaction = session.beginTransaction();

            BookEntity book = session.get(BookEntity.class, 300L);
            logger.warn("Достал книжку");
            if (book != null) {
                logger.debug("Достаю тайтл");
                System.out.println(book.getTitle());
                logger.debug("Достал тайтл: " + book.getTitle());
            } else {
                logger.debug("Книжки нет достаю другую");
                book = session.get(BookEntity.class, 6L);
                logger.trace("Достал тайтл: " + book.getTitle());
            }


            transaction.commit();
            System.out.println("Закрыл транзакцию");

            //get
            //persist
            //merge
            //remove

            LocalTime end = LocalTime.now();
            System.out.println("Время выполнения: " + (end.getNano() - start.getNano()));
        }
    }
}