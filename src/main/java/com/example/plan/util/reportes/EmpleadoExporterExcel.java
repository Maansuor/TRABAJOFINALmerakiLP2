/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.util.reportes;

import com.example.plan.entity.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author KAREN
 */
public class EmpleadoExporterExcel {
    private XSSFWorkbook empleado;
    private XSSFSheet hoja; 
    
    private List<Empleado> listaEmpleados; 
    
    public EmpleadoExporterExcel(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
        empleado = new XSSFWorkbook();
        hoja = empleado.createSheet("Empleados");
    }
    private void escribirCabeceraDeTabla() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = empleado.createCellStyle();
        XSSFFont fuente = empleado.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Nombres");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("DNI");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("Sueldo");
        celda.setCellStyle(estilo);

        celda = fila.createCell(4);
        celda.setCellValue("Cargo");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(5);
        celda.setCellValue("Teléfono");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(6);
        celda.setCellValue("Direccion");
        celda.setCellStyle(estilo);
    }
    
        private void escribirDatosDeLaTabla() {
        int nueroFilas = 1;

        CellStyle estilo = empleado.createCellStyle();
        XSSFFont fuente = empleado.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for (Empleado lib : listaEmpleados) {
            Row fila = hoja.createRow(nueroFilas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(lib.getId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue(lib.getNombres());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue(lib.getDni());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(3);
            celda.setCellValue(lib.getSueldo());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(4);
            celda.setCellValue(lib.getCargo());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(5);
            celda.setCellValue(lib.getTelefono());
            hoja.autoSizeColumn(5);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(6);
            celda.setCellValue(lib.getDireccion());
            hoja.autoSizeColumn(6);
            celda.setCellStyle(estilo);
        }
    }
    public void exportar(HttpServletResponse response) throws IOException {
        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();
        
        ServletOutputStream outPutStream = response.getOutputStream();
        empleado.write(outPutStream);

        empleado.close();
        outPutStream.close();
    }       
}