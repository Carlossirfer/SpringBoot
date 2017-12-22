//package com.ciber.springBoot.HolaSpringBoot.util.json;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//public class GeneratePdfReport {
//
//    public static ByteArrayInputStream citiesReport(List<Usuario> usuarios) {
//
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//
//            PdfPTable table = new PdfPTable(3);
//            table.setWidthPercentage(60);
//            table.setWidths(new int[]{1, 3, 3});
//
//            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//
//            PdfPCell hcell;
//
//            hcell = new PdfPCell(new Phrase("Nombre", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Apellidos", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            for (Usuario usuario : usuarios) {
//
//                PdfPCell cell;
//
//                cell = new PdfPCell(new Phrase(usuario.getNombre()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(usuario.getApellidos()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//             
//            }
//
//            PdfWriter.getInstance(document, out);
//            document.open();
//            document.add(table);
//            
//            document.close();
//            
//        } catch (DocumentException ex) {
//        
//            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//}