/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.util.reportes;

import com.example.plan.entity.Empleado;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KAREN
 */
public class EmpleadoExporterPDF {
    private List<Empleado> listaEmpleados;
    
    
    public EmpleadoExporterPDF(List<Empleado> listaEmpleados) {
        super();
        this.listaEmpleados = listaEmpleados;
    }
    
    
    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nombre", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("DNI", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Sueldo", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Cargo", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Teléfono", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Direccion", fuente));
        tabla.addCell(celda);

    }
    
    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (Empleado empleado : listaEmpleados) {
            tabla.addCell(String.valueOf(empleado.getId()));
            tabla.addCell(empleado.getNombres());
            tabla.addCell(String.valueOf(empleado.getDni()));
            tabla.addCell(String.valueOf(empleado.getSueldo()));
            tabla.addCell(empleado.getCargo());
            tabla.addCell(String.valueOf(empleado.getTelefono()));
            tabla.addCell(empleado.getDireccion());
        }
    }

    
    
    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de Empleados", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f, 2.3f, 2.3f, 2.3f, 5f});
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
    
}