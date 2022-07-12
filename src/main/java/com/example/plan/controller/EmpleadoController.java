/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.plan.controller;

import com.example.plan.entity.Empleado;
import com.example.plan.entity.Producto;
import com.example.plan.serviceImpl.EmpleadoService;
import com.example.plan.util.reportes.EmpleadoExporterExcel;

import com.example.plan.util.reportes.EmpleadoExporterPDF;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author KAREN
 */
@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String indexEmpleado(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("empleados", empleadoService.readAll());
        return "empleados/listarEmpleado";
    }

    @GetMapping("/add")
    public String addEmpleado(Model model) {
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("empleado", new Empleado());
        return "empleados/addEmpleado";
    }

    @GetMapping("/save")
    public String saveEmpleado(Model model) {
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("empleado", new Empleado());
        return "empleado/addEmpleado";
    }

    @PostMapping("/save")
    public String addEmpleado(@Valid @ModelAttribute Empleado empleado, BindingResult result, Model model, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes attributes ) {  

        if(!imagen.isEmpty()){
            String ruta = "C://imgmeraki//emple";
            
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutacompleta = Paths.get(ruta+"//"+imagen.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);
                empleado.setImagen(imagen.getOriginalFilename());
                empleadoService.create(empleado);
            } catch (IOException e) {
                System.out.println("Error: "+e);
            }
        }
        return "redirect:/empleado";
    }
    
    @GetMapping("/edit/{id}")
    public String editarEmpleado(@PathVariable("id") int idempleado, Model model) {
        Empleado empleado = empleadoService.read(idempleado);
        model.addAttribute("titulo", "Editar");
        model.addAttribute("empleado", empleado);
        return "empleados/addEmpleado";
    }

    @GetMapping("/detalle/{id}")
    public String detalleEmpleado(@PathVariable("id") int idempleado, Model model) {

        Empleado empleado = empleadoService.read(idempleado);
        model.addAttribute("titulo", "Detalle");
        model.addAttribute("empleado", empleado);
        return "empleados/detalleEmpleado";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmpleado(@PathVariable("id") int idempleado) {
        empleadoService.delete(idempleado);
        return "redirect:/empleado";
    }

    @GetMapping("/exportarPDF")
    public void exportarListadoDeEmpleadoEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Empleado_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Empleado> empleados = empleadoService.readAll();

        EmpleadoExporterPDF exporter = new EmpleadoExporterPDF(empleados);
        exporter.exportar(response);
    }

    @GetMapping("/exportarExcel")
    public void exportarListadoDeEmpleadosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Empleado_" + fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);

        List<Empleado> empleados = empleadoService.readAll();

        EmpleadoExporterExcel exporter = new EmpleadoExporterExcel(empleados);
        exporter.exportar(response);
    }
}
 