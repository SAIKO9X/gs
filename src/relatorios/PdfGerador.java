/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorios;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;


/**
 *
 * @author newgu
 */
public class PdfGerador {

  public static void gerarRelatorio(String titulo, List<String> linhas, String caminho) {
    try {
      Document doc = new Document();
      PdfWriter.getInstance(doc, new FileOutputStream(caminho));
      doc.open();

      // Título
      Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
      Paragraph p = new Paragraph(titulo, tituloFont);
      p.setAlignment(Element.ALIGN_CENTER);
      doc.add(p);

      doc.add(new Paragraph(" "));

      // todo o corpo do pdf menos o titulo
      for (String linha : linhas) {
        doc.add(new Paragraph(linha));
      }

      doc.close();
      System.out.println("PDF gerado com sucesso em: " + caminho);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
