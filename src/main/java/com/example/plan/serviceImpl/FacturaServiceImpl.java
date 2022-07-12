/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Factura;
import com.example.plan.repository.FacturaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BRITNEY
 */
@Service
public class FacturaServiceImpl implements FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Override
    public Factura create(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void delete(int id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public Factura read(int id) {
        return facturaRepository.findById(id).get();
    }

    @Override
    public List<Factura> readAll() {
        return facturaRepository.findAll();
    }
}
