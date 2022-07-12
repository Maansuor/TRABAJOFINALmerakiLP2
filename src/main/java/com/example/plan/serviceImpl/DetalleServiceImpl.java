/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Detalle;
import com.example.plan.repository.DetalleRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Micaela
 */
@Service
public class DetalleServiceImpl implements DetalleService{
    @Autowired
    private DetalleRepository detalleRepository;
    
    @Override
    public Detalle create(Detalle detalle) {
       return detalleRepository.save(detalle);
    }

    @Override
    public Detalle update(Detalle detalle) {
       return detalleRepository.save(detalle);
    }

    @Override
    public void delete(int id) {
        detalleRepository.deleteById(id);
    }

    @Override
    public Detalle read(int id) {
        return detalleRepository.findById(id).get();
    }

    @Override
    public List<Detalle> readAll() {
        return detalleRepository.findAll();
    }
    
}
