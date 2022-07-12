/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Empleado;
import com.example.plan.repository.EmpleadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KAREN
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public Empleado create(Empleado empleado) {
       return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado update(Empleado empleado) {
       return empleadoRepository.save(empleado);
    }

    @Override
    public void delete(int id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado read(int id) {
        return empleadoRepository.findById(id).get();
    }

    @Override
    public List<Empleado> readAll() {
        return empleadoRepository.findAll();
    }
}