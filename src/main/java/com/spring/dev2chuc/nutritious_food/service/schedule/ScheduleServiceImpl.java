package com.spring.dev2chuc.nutritious_food.service.schedule;

import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleRequest;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Schedule merge(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule merge(Schedule schedule, ScheduleRequest scheduleRequest) {
        schedule.setName(scheduleRequest.getName());
        schedule.setDescription(scheduleRequest.getDescription());
        schedule.setPrice(scheduleRequest.getPrice());
        schedule.setImage(scheduleRequest.getImage());
        schedule.setStatus(Status.ACTIVE.getValue());
        Schedule result = scheduleRepository.save(schedule);
        return result;
    }

    @Override
    public List<Schedule> findAllByStatusIs(Integer status) {
        List<Schedule> list = scheduleRepository.findAllByStatusIs(status);
        if (list == null) {
            return null;
        }
        return list;
    }

    @Override
    public Schedule update(Schedule schedule, ScheduleRequest scheduleRequest) {
        if (scheduleRequest.getName() != null) schedule.setName(scheduleRequest.getName());
        if (scheduleRequest.getDescription() != null)  schedule.setDescription(scheduleRequest.getDescription());
        if (scheduleRequest.getPrice() != 0) schedule.setPrice(scheduleRequest.getPrice());
        if (scheduleRequest.getImage() != null)  schedule.setImage(scheduleRequest.getImage());
        Schedule result = scheduleRepository.save(schedule);
        return result;
    }

    @Override
    public Schedule findByStatusAndId(Integer status, Long id) {
        Schedule schedule = scheduleRepository.findByStatusAndId(status, id);
        if (schedule == null) {
            return null;
        }
        return schedule;
    }
}
