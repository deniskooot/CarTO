//package com.github.Denis;
//
//import com.github.Denis.entity.CarUser;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

//import static org.junit.jupiter.api.Assertions.assertNotNull;
////@SpringBootTest Эта аннотация указывает Spring Boot на запуск всего контекста приложения, включая сканирование сущностей и настройку базы данных.
//@SpringBootTest
//public class EntityTest {
////@PersistenceContextУказывает, что Spring должен внедрить объект EntityManager, который используется для выполнения операций с сущностями.
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Test
//    public void testCarUserEntity() {
//        // Выполняем запрос к EntityManager для проверки
//        //Выполняет поиск записи в базе данных с указанным типом сущности (CarUser) и ID (1).
//        CarUser carUser = entityManager.find(CarUser.class, 1); // Здесь 1 — это ID записи в таблице car_users
//        //Проверяет, что сущность найдена в базе данных. Если null, значит JPA не распознал CarUser как сущность.
//        assertNotNull(carUser, "CarUser не распознается как сущность");
//    }
//}