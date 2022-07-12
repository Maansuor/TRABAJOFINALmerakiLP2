/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Factura;
import java.util.List;

/**
 *
 * @author BRITNEY
 */
public interface FacturaService {
    Factura create(Factura factura);
    Factura update(Factura factura);
    void delete(int id);
    Factura read(int id);
    List<Factura> readAll();
}
