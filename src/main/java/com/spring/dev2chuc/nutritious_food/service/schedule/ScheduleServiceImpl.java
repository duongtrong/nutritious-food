package com.spring.dev2chuc.nutritious_food.service.schedule;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleRequest;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleRepository;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Schedule delete(Schedule schedule) {
        schedule.setStatus(Status.DEACTIVE.getValue());
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule store(ScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule();
        schedule.setName(scheduleRequest.getName());
        schedule.setDescription(scheduleRequest.getDescription());
        schedule.setPrice(scheduleRequest.getPrice());
        schedule.setImage(scheduleRequest.getImage());
        schedule.setStatus(Status.ACTIVE.getValue());

        List<Category> categories = categoryService.findAllByIdIn(scheduleRequest.getCategoryIds());
        Set<Category> categorySet = new HashSet<>(categories);
        schedule.setCategories(categorySet);

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAllByStatusIs(Integer status) {
        return scheduleRepository.findAllByStatusIs(status);
    }

    @Override
    public Schedule update(Schedule schedule, ScheduleRequest scheduleRequest) {
        if (scheduleRequest.getName() != null) schedule.setName(scheduleRequest.getName());
        if (scheduleRequest.getDescription() != null) schedule.setDescription(scheduleRequest.getDescription());
        if (scheduleRequest.getPrice() != 0) schedule.setPrice(scheduleRequest.getPrice());
        if (scheduleRequest.getImage() != null) schedule.setImage(scheduleRequest.getImage());

        if (scheduleRequest.getCategoryIds().size() > 0) {
            List<Category> categories = categoryService.findAllByIdIn(scheduleRequest.getCategoryIds());
            Set<Category> categorySet = new HashSet<>(categories);
            schedule.setCategories(categorySet);
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findByStatusAndId(Integer status, Long id) {
        return scheduleRepository.findByStatusAndId(status, id);
    }
}
