
package com.example.plan.util.reportes;

import com.example.plan.entity.Empleado;
import com.example.plan.entity.Factura;
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
public class FacturaExporterPDF {
    private List<Factura> listaFacturas;
    
    public FacturaExporterPDF(List<Factura> listaFacturas) {
        super();
        this.listaFacturas = listaFacturas;
    }
    
    
    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Cliente", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Total", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("IGV", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Total_Neto", fuente));
        tabla.addCell(celda);
        
        
    }
    
    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (Factura factura : listaFacturas) {
            tabla.addCell(String.valueOf(factura.getId()));
            tabla.addCell(factura.getCliente().getNombre());
            tabla.addCell(String.valueOf(factura.getDetalle().getImporte()));
            tabla.addCell(String.valueOf(factura.getIgv()));
            tabla.addCell(String.valueOf(factura.getTotalneto()));
            
        }
    }

    
    
    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Factura de compra", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f, 2.3f, 2.3f});
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
    
}