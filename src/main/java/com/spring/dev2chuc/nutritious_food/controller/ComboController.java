package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.response.*;
import com.spring.dev2chuc.nutritious_food.payload.ComboRequest;
import com.spring.dev2chuc.nutritious_food.service.combo.ComboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/combo")
public class ComboController {

    @Autowired
    ComboService comboService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ComboRequest comboRequest) {
        Combo current = new Combo();
        Combo result = comboService.merge(current, comboRequest);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.CREATED.value(), "Create success", new ComboResponse(result)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Combo> combos = comboService.findAll();
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", combos.stream().map(x -> new ComboResponse(x)).collect(Collectors.toList())), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ComboRequest comboRequest, @PathVariable Long id) {
        Combo combo = comboService.findById(id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }

        Combo result = comboService.update(combo, comboRequest);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Update success",  new ComboResponse(result)), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Combo combo = comboService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK",  new ComboResponse(combo)), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Combo combo = comboService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponseCustom(HttpStatus.OK.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }
        combo.setStatus(Status.DEACTIVE.getValue());
        comboService.merge(combo);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "form", required = false) String form,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit){

        Specification specification = Specification.where(null);
        if (search != null && search.length() > 0) {
            specification = specification
                    .and(new SpecificationAll(new SearchCriteria("name", ":", search)))
                    .or(new SpecificationAll(new SearchCriteria("description", ":", search)));
        }

        specification = specification
                .and(new SpecificationAll(new SearchCriteria("status", ":", Status.ACTIVE.getValue())));

        Page<Combo> combos = comboService.foodsWithPaginate(specification, page, limit);
        return new ResponseEntity<>(new ApiResponsePage<>(
                HttpStatus.OK.value(), "OK", combos.stream().map(x -> new OnlyComboResponse(x)).collect(Collectors.toList()),
                new RESTPagination(page, limit, combos.getTotalPages(), combos.getTotalElements())), HttpStatus.OK);
    }
}
