/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.plan.controller;


import com.example.plan.entity.Producto;
import com.example.plan.serviceImpl.ProductoService;
import com.example.plan.util.reportes.ProductoExporterExcel;
import com.example.plan.util.reportes.ProductoExporterPDF;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author BRITNEY
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping
    public String indexProducto(Model model){
        model.addAttribute("producto", productoService.readAll());
        return "producto/listarProducto";
    }
    @GetMapping("/add")
    public String addProducto(Model model){
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("producto", new Producto());
        return "producto/addProducto";
    }
    @GetMapping("/save")
    public String saveProducto(Model model){
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("producto", new Producto());
        return "producto/addProducto";
    }
    @PostMapping("/save")
    public String addProducto(@Valid @ModelAttribute Producto producto, BindingResult result, Model model, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes attributes ) {  

        if(!imagen.isEmpty()){
            String ruta = "C://imgmeraki//productos";
            
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutacompleta = Paths.get(ruta+"//"+imagen.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);
                producto.setImagen(imagen.getOriginalFilename());
                productoService.create(producto);
            } catch (IOException e) {
                System.out.println("Error: "+e);
            }
        }
        return "redirect:/producto";
    }
    @GetMapping("/edit/{id}")
    public String editarProducto(@PathVariable("id") int idproducto, Model model) {  
        Producto producto = productoService.read(idproducto);
        model.addAttribute("titulo", "Editar");
        model.addAttribute("producto", producto);
        return "producto/addProducto";
    }
    @GetMapping("/detalle/{id}")
    public String detalleProducto(@PathVariable("id") int idproducto, Model model) {
        
        Producto producto = productoService.read(idproducto);
        model.addAttribute("titulo", "Detalle");
        model.addAttribute("producto", producto);
        return "producto/detalleProducto";
    }
    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable("id") int idproducto) {  
       productoService.delete(idproducto);
        return "redirect:/producto";
    }
    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeProductoEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Producto> productos = productoService.readAll();

        ProductoExporterPDF exporter = new ProductoExporterPDF(productos);
        exporter.exportar(response);
    }

    
    @GetMapping("/exportarExcel")
    public void exportarListadoDeProductosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Producto_" + fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);

        List<Producto> clientes =productoService.readAll();

        ProductoExporterExcel exporter = new ProductoExporterExcel(clientes);
        exporter.exportar(response);
    }
    
}

