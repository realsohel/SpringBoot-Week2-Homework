package com.springBootweek2Homework.week2Homework.services;

import com.springBootweek2Homework.week2Homework.dtos.DepartmentDTO;
import com.springBootweek2Homework.week2Homework.entities.DepartmentEntity;
import com.springBootweek2Homework.week2Homework.exceptions.ResourceNotFoundException;
import com.springBootweek2Homework.week2Homework.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> allDepartments= departmentRepository.findAll();

        return allDepartments
                .stream()
                .map((dep)-> modelMapper.map(dep,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        isExistedByid(id);
        return departmentRepository.findById(id)
                .map((dep)-> modelMapper.map(dep, DepartmentDTO.class));
    }

    public DepartmentDTO addDepartment(DepartmentDTO inputDep){
        DepartmentEntity departmentEntity = modelMapper.map(inputDep,DepartmentEntity.class);
        DepartmentEntity saveDep = departmentRepository.save(departmentEntity);

        return modelMapper.map(saveDep, DepartmentDTO.class);

    }

    public DepartmentDTO updateDepartment(DepartmentDTO dep,Long Id) {
        DepartmentEntity departmentEntity = modelMapper.map(dep,DepartmentEntity.class);
        departmentEntity.setId(Id);
        System.out.println(departmentEntity.getTitle() + " " + departmentEntity.getIsActive());

        DepartmentEntity saveDep = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveDep,DepartmentDTO.class);

    }

    public void isExistedByid(Long Id){
        Boolean exists = departmentRepository.existsById(Id);
        if(!exists)
            throw  new ResourceNotFoundException("Employee not found with Id " + Id);
    }
    public Boolean deleteDepartment(Long id) {
        isExistedByid(id);

        departmentRepository.deleteById(id);

        return true;

    }


    public DepartmentDTO patchDepartment(Map<String, Object> updates, Long id) {
        isExistedByid(id);

        DepartmentEntity departmentEntity = departmentRepository.findById(id).get();

        updates.forEach((field,value)->{
            Field fieldUpdate = ReflectionUtils.findField(DepartmentEntity.class,field);
            fieldUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldUpdate,departmentEntity,value);
        });

        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);


    }
}
