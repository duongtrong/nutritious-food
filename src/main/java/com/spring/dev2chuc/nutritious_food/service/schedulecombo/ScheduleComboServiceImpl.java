package com.spring.dev2chuc.nutritious_food.service.schedulecombo;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.ScheduleCombo;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleComboRequest;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleRequest;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleComboRepository;
import com.spring.dev2chuc.nutritious_food.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleComboServiceImpl implements ScheduleComboService {

    @Autowired
    ScheduleComboRepository scheduleComboRepository;

    @Override
    public ScheduleCombo findById(Long id) {
        return scheduleComboRepository.findById(id).orElseThrow(null);
    }

    @Override
    public List<ScheduleCombo> findAllByCombo(Combo combo) {
        List<ScheduleCombo> list = scheduleComboRepository.findAllByCombo(combo);
        if (list == null) {
            return null;
        }
        return list;
    }

    @Override
    public List<ScheduleCombo> findAllBySchedule(Schedule schedule) {
        List<ScheduleCombo> list = scheduleComboRepository.findAllBySchedule(schedule);
        if (list == null) {
            return null;
        }
        return list;
    }

    @Override
    public ScheduleCombo update(ScheduleCombo scheduleCombo, ScheduleComboRequest scheduleComboRequest) {
        return null;
    }

    @Override
    public ScheduleCombo merge(ScheduleCombo scheduleCombo, ScheduleComboRequest scheduleComboRequest) {
        scheduleCombo.setDay(scheduleComboRequest.getDay());
        scheduleCombo.setType(scheduleComboRequest.getType());
        ScheduleCombo result = scheduleComboRepository.save(scheduleCombo);
        return result;
    }
}
