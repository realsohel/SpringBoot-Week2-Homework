package com.springBootweek2Homework.week2Homework.controllers;

import com.springBootweek2Homework.week2Homework.dtos.DepartmentDTO;
import com.springBootweek2Homework.week2Homework.exceptions.ResourceNotFoundException;
import com.springBootweek2Homework.week2Homework.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){ // Depdendency Injection
        this.departmentService = departmentService;

    }

    @GetMapping(path = "")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> allDepartments= departmentService.getAllDepartments();
        return  ResponseEntity.ok(allDepartments);
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);

        return departmentDTO.map(
                (dep)->ResponseEntity.ok(dep))
                .orElseThrow(()-> new ResourceNotFoundException("Department not Found"));

    }


    @PostMapping(path = "")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody @Valid DepartmentDTO inpDep){
        DepartmentDTO departmentDTO = departmentService.addDepartment( inpDep);
//
        return new ResponseEntity<>(departmentDTO,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO dep, @PathVariable(name = "departmentId") Long id){
        DepartmentDTO departmentDTO = departmentService.updateDepartment(dep,id);
        return ResponseEntity.ok(departmentDTO);
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment( @PathVariable(name = "departmentId") Long id){
        Boolean del =  departmentService.deleteDepartment(id);
        if(del)
            return  ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();

    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> patchDepartment(@RequestBody @Valid Map<String, Object> updates, @PathVariable(name = "departmentId") Long id){
        DepartmentDTO departmentDTO = departmentService.patchDepartment(updates,id);
        if(departmentDTO==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(departmentDTO);
    }



}
