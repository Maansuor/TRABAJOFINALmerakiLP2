/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Cliente;
import com.example.plan.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Micaela
 */
@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public Cliente create(Cliente cliente) {
       return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
       return clienteRepository.save(cliente);
    }

    @Override
    public void delete(int id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente read(int id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public List<Cliente> readAll() {
        return clienteRepository.findAll();
    }
    
}
