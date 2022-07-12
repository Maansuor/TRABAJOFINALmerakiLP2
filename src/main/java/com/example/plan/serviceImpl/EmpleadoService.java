/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Empleado;
import java.util.List;

/**
 *
 * @author KAREN
 */
public interface EmpleadoService {
    Empleado create(Empleado empleado);
    Empleado update(Empleado empleado);
    void delete(int id);
    Empleado read(int id);
    List<Empleado> readAll();     
}