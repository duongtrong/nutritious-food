package com.spring.dev2chuc.nutritious_food.service.schedule;

import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleRequest;

import java.util.List;

public interface ScheduleService {

    Schedule findById(Long id);

    Schedule delete(Schedule schedule);

    Schedule store(ScheduleRequest scheduleRequest);

    List<Schedule> findAllByStatusIs(Integer status);

    Schedule update(Schedule schedule, ScheduleRequest scheduleRequest);

    Schedule findByStatusAndId(Integer status, Long id);
}
