/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.controller;


import com.example.plan.entity.Detalle;

import javax.validation.Valid;
import com.example.plan.serviceImpl.DetalleService;
import com.example.plan.serviceImpl.ProductoService;



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
@RequestMapping("/detalle")
public class DetalleController {
     @Autowired
     private DetalleService detalleService;
     @Autowired
     private ProductoService productoService;
     
    @GetMapping
    public String indexDetalle(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("detalles", detalleService.readAll());
        return "detalles/listarDetalle";
    }
    
    @GetMapping("/add")
    public String addDetalle(Model model){
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("detalle", new Detalle());
        model.addAttribute("producto", productoService.readAll());
        return "detalles/addDetalle";
    }
    
    @GetMapping("/save")
    public String saveDetalle(Model model){
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("detalle", new Detalle());
        return "detalles/addDetalle";
    }
    
       
    @PostMapping("/save")
    public String addDetalle(@Valid @ModelAttribute Detalle detalle, BindingResult result, Model model, RedirectAttributes attributes ) {  
        detalleService.create(detalle);
        return "redirect:/detalle";
    }
    
    @GetMapping("/edit/{id}")
    public String editarDetalle(@PathVariable("id") int iddetalle, Model model) {  
        Detalle detalle = detalleService.read(iddetalle);
        model.addAttribute("titulo", "Editar");
        model.addAttribute("detalle", detalle);
        model.addAttribute("producto", productoService.readAll()); 
        return "detalles/addDetalle";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteDetalle(@PathVariable("id") int iddetalle) {  
        detalleService.delete(iddetalle);
        return "redirect:/detalle";
    }
    
    
  /**  @GetMapping("/exportarPDF")
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
    }**/
}
