/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.plan.controller;


import com.example.plan.entity.Factura;
import com.example.plan.serviceImpl.ClienteService;
import com.example.plan.serviceImpl.DetalleService;
import com.example.plan.serviceImpl.FacturaService;
import com.example.plan.util.reportes.FacturaExporterPDF;
import com.lowagie.text.DocumentException;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
 * @author BRITNEY
 */
@Controller
@RequestMapping("/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private DetalleService detalleService;
    
    @GetMapping
    public String indexFactura(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("facturas", facturaService.readAll());
        return "facturas/listarFactura";
    }

    @GetMapping("/add")
    public String addFactura(Model model) {
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("factura", new Factura());
        model.addAttribute("clientes", clienteService.readAll());
        model.addAttribute("detalles", detalleService.readAll());
        return "facturas/addFactura";
    }

    @GetMapping("/save")
    public String saveFactura(Model model) {
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("factura", new Factura());
        return "facturas/addFactura";
    }
    @PostMapping("/save")
    public String addFactura(@Valid @ModelAttribute Factura factura, BindingResult result, Model model, RedirectAttributes attributes ) {  
        facturaService.create(factura);
        return "redirect:/factura";
    }


    
    
    @GetMapping("/delete/{id}")
    public String deleteFactura(@PathVariable("id") int idfactura) {
        facturaService.delete(idfactura);
        return "redirect:/factura";
    }

    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeFacturaEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Factura_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Factura> facturas = facturaService.readAll();

        FacturaExporterPDF exporter = new FacturaExporterPDF(facturas);
        exporter.exportar(response);
    }
/**
    @GetMapping("/exportarExcel")
    public void exportarListadoDeEmpleadosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Libro_" + fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);

        List<Libro> libros = libroService.readAll();

        LibroExporterExcel exporter = new LibroExporterExcel(libros);
        exporter.exportar(response);
    }
    * */
}
