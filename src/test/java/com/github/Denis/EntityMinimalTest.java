//package com.github.Denis;
//
//import com.github.Denis.entity.Car;
//import com.github.Denis.entity.CarUser;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class EntityMinimalTest {
//
//    @Test
//    public void testCarUserEntity() {
//        Configuration configuration = new Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        configuration.addAnnotatedClass(CarUser.class);
//
//        configuration.addAnnotatedClass(com.github.Denis.entity.CarUser.class); // Регистрируем вручную
//        configuration.addAnnotatedClass(com.github.Denis.entity.Car.class); // Регистрируем связанную сущность
//
//
//
////        SessionFactory sessionFactory = configuration.buildSessionFactory();
////        try (Session session = sessionFactory.openSession()) {
////            System.out.println("Зарегистрированные сущности: " + configuration.getClass());
////            CarUser carUser = session.get(CarUser.class, 1);
////            assertNotNull(carUser, "CarUser не распознается как сущность");
////        }
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            CarUser carUser = new CarUser();
//            carUser.setName("Test User");
//            session.save(carUser); // Если ошибка возникает здесь, значит, проблема в конфигурации
//            session.getTransaction().commit();
//        }
//        finally {
////            sessionFactory.close();
//
//        }
//    }
//
//}
//
