package com.github.Denis.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.Denis.dto.SchedulePerspectiveType;
import com.github.Denis.dto.ScheduledTaskForCar;
import com.github.Denis.repository.CarRepository;
import com.github.Denis.repository.ServiceScheduleItemRepository;
import com.github.Denis.service.ServiceScheduleItemService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CarController.class)
class CarControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private ServiceScheduleItemService serviceScheduleItemService;

  @MockitoBean private ServiceScheduleItemRepository serviceScheduleItemRepository;

  @MockitoBean private CarRepository carRepository;

  @Test
  void testBadSchedulePerspective() throws Exception {
    mockMvc
        .perform(
            get(
                    "/api/cars/1/tasks/by/{perspectiveType}/{perspectiveLimit}",
                    SchedulePerspectiveType.km.name(),
                    -20)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            get(
                    "/api/cars/1/tasks/by/{perspectiveType}/{perspectiveLimit}",
                    SchedulePerspectiveType.km.name(),
                    1_000_001)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mockMvc
        .perform(
            get(
                    "/api/cars/1/tasks/by/{perspectiveType}/{perspectiveLimit}",
                    SchedulePerspectiveType.years.name(),
                    1_000_001)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mockMvc
        .perform(
            get(
                    "/api/cars/1/tasks/by/{perspectiveType}/{perspectiveLimit}",
                    SchedulePerspectiveType.years.name(),
                    -1)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    mockMvc
        .perform(
            get("/api/cars/1/tasks/by/{perspectiveType}/{perspectiveLimit}", "unknownType", -1)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetTasks() throws Exception {
    final String name = "Test schedule";

    ScheduledTaskForCar task = new ScheduledTaskForCar();
    task.setScheduleName(name);
    when(serviceScheduleItemService.getTaskList(
            eq(1), eq(SchedulePerspectiveType.km), eq(50), eq(false)))
        .thenReturn(List.of(task));

    mockMvc
        .perform(
            get(
                    "/api/cars/1/tasks/by/{perspectiveType}/{perspectiveLimit}",
                    SchedulePerspectiveType.km.name(),
                    50)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].scheduleName").value(name));
  }
}
