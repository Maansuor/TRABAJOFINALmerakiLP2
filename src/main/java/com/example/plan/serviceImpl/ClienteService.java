/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Cliente;
import java.util.List;

/**
 *
 * @author Micaela
 */
public interface ClienteService {
    Cliente create(Cliente cliente);
    Cliente update(Cliente cliente);
    void delete(int id);
    Cliente read(int id);
    List<Cliente> readAll(); 
}
