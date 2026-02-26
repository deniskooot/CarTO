package com.github.Denis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Проверяет что вообще приложение загружается нормально. Используется временный контейнер postgres
 * для тестов, прогоняются миграции.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("tc")
@Testcontainers
public class GeneralTest {

  @Test
  void contextLoads() {}
}
