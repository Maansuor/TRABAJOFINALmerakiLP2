/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;


import com.example.plan.entity.Detalle;
import java.util.List;

/**
 *
 * @author Micaela
 */
public interface DetalleService {
    Detalle create(Detalle detalle);
    Detalle update(Detalle detalle);
    void delete(int id);
    Detalle read(int id);
    List<Detalle> readAll(); 
}
