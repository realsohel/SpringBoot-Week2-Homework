package com.springBootweek2Homework.week2Homework.controllers;

import com.springBootweek2Homework.week2Homework.dtos.DepartmentDTO;
import com.springBootweek2Homework.week2Homework.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){ // Depdendency Injection
        this.departmentService = departmentService;

    }

    @GetMapping(path = "")
    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentDTO> allDepartments= departmentService.getAllDepartments();
        return allDepartments;
    }

    @GetMapping(path = "/{departmentId}")
    public Optional<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        return departmentService.getDepartmentById(id);
    }


    @PostMapping(path = "")
    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO inpDep){
        DepartmentDTO departmentDTO = departmentService.addDepartment( inpDep);
//
        return departmentDTO;
    }

    @PutMapping(path = "/{departmentId}")
    public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO dep, @PathVariable(name = "departmentId") Long id){
        DepartmentDTO departmentDTO = departmentService.updateDepartment(dep,id);
        return departmentDTO;
    }

    @DeleteMapping(path = "/{departmentId}")
    public Boolean deleteDepartment( @PathVariable(name = "departmentId") Long id){
        return departmentService.deleteDepartment(id);
    }

    @PatchMapping(path = "/{departmentId}")
    public DepartmentDTO patchDepartment(@RequestBody Map<String, Object> updates, @PathVariable(name = "departmentId") Long id){
        DepartmentDTO departmentDTO = departmentService.patchDepartment(updates,id);
        return departmentDTO;
    }



}
