/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.util.reportes;

import com.example.plan.entity.Cliente;
import com.lowagie.text.Paragraph;
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
 * @author Micaela
 */
public class ClienteExporterExcel {
    private XSSFWorkbook cliente;
    private XSSFSheet hoja;

    private List<Cliente> listaClientes;  
    
    public ClienteExporterExcel(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        cliente = new XSSFWorkbook();
        hoja = cliente.createSheet("Clientes");
    }
    
    
    private void escribirCabeceraDeTabla() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = cliente.createCellStyle();
        XSSFFont fuente = cliente.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Nombre");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("DNI");
        celda.setCellStyle(estilo);

        celda = fila.createCell(3);
        celda.setCellValue("Teléfono");
        celda.setCellStyle(estilo);
    }
    
    
    private void escribirDatosDeLaTabla() {
        int nueroFilas = 1;

        CellStyle estilo = cliente.createCellStyle();
        XSSFFont fuente = cliente.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for (Cliente lib : listaClientes) {
            Row fila = hoja.createRow(nueroFilas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(lib.getId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue(lib.getNombre());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue(lib.getDni());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);

            celda = fila.createCell(3);
            celda.setCellValue(lib.getTelefono());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);


        }
    }
    
    
    public void exportar(HttpServletResponse response) throws IOException {
        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();
        
        ServletOutputStream outPutStream = response.getOutputStream();
        cliente.write(outPutStream);

        cliente.close();
        outPutStream.close();
    }
}
