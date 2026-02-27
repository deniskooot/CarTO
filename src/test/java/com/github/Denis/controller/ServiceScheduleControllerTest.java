package com.github.Denis.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.repository.ServiceScheduleItemRepository;
import com.github.Denis.service.ServiceScheduleItemService;
import com.github.Denis.service.ServiceScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ServiceScheduleController.class)
class ServiceScheduleControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ServiceScheduleService userService;

  @MockitoBean private ServiceScheduleItemService serviceScheduleItemService;

  @MockitoBean private ServiceScheduleItemRepository serviceScheduleItemRepository;

  @Test
  void getById() throws Exception {
    final String name = "Test User";
    final int id = 1;
    final long durationDays = 52;

    ServiceSchedule result = new ServiceSchedule();
    result.setId(id);
    result.setName(name);
    result.setDefaultPeriodTimeDays(durationDays);

    // Define the behavior of the mocked service
    when(userService.getServiceScheduleByID(id)).thenReturn(result);

    mockMvc
        .perform(get("/api/serviceSchedules/{id}", id).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.name").value(name))
        .andExpect(jsonPath("$.default_period_time_days").value(durationDays));
  }
}
