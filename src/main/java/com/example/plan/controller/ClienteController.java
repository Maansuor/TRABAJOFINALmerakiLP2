/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.controller;

import com.example.plan.entity.Cliente;

import javax.validation.Valid;
import com.example.plan.serviceImpl.ClienteService;
import com.example.plan.util.reportes.ClienteExporterExcel;
import com.example.plan.util.reportes.ClienteExporterPDF;

import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Micaela
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {
     @Autowired
     private ClienteService clienteService;
     
    @GetMapping
    public String indexCliente(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("clientes", clienteService.readAll());
        return "clientes/listarCliente";
    }
    
    @GetMapping("/add")
    public String addCliente(Model model){
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("cliente", new Cliente());
        return "clientes/addCliente";
    }
    
    @GetMapping("/save")
    public String saveCliente(Model model){
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("cliente", new Cliente());
        return "cliente/addCliente";
    }
    
       
    @PostMapping("/save")
    public String addCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes ) {  
        clienteService.create(cliente);
        return "redirect:/cliente";
    }
    
    @GetMapping("/edit/{id}")
    public String editarCliente(@PathVariable("id") int idcliente, Model model) {  
        Cliente cliente = clienteService.read(idcliente);
        model.addAttribute("titulo", "Editar");
        model.addAttribute("cliente", cliente);
        return "clientes/addCliente";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleCliente(@PathVariable("id") int idcliente, Model model) {
        
        Cliente cliente = clienteService.read(idcliente);
        model.addAttribute("titulo", "Detalle");
        model.addAttribute("cliente", cliente);
        return "clientes/detalleEditorial";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable("id") int idcliente) {  
       clienteService.delete(idcliente);
        return "redirect:/cliente";
    }
    
    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeClientesEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Cliente> clientes = clienteService.readAll();

        ClienteExporterPDF exporter = new ClienteExporterPDF(clientes);
        exporter.exportar(response);
    }

    
    @GetMapping("/exportarExcel")
    public void exportarListadoDeClientesEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Cliente_" + fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);

        List<Cliente> clientes =clienteService.readAll();

        ClienteExporterExcel exporter = new ClienteExporterExcel(clientes);
        exporter.exportar(response);
    }
}
