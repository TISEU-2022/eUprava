package com.example.SluzbaZaposljavanja.model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class GradjaninPDFExporter {

    private Gradjanin gradjanin;

    public GradjaninPDFExporter(Gradjanin gradjanin) {
        super();
        this.gradjanin = gradjanin;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.YELLOW);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Username ", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ime", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prezime", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Firma", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Zaposlenje", font));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {

            table.addCell(String.valueOf(gradjanin.getId()));
            table.addCell(gradjanin.getKorisnickoIme());
            table.addCell(gradjanin.getIme());
            table.addCell(gradjanin.getPrezime());
            table.addCell(String.valueOf(gradjanin.getFirma().getImeFirme()));
            if(gradjanin.getFirma() == null) {
                table.addCell("Nije Zaposlen");
            }else{
                table.addCell("Zaposlen je");
            }


    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);


        Paragraph title = new Paragraph("Zaposlenje gradjanina"+gradjanin.getKorisnickoIme(),font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(9);
        table.setWidths(new float[] {3.0f, 3.0f, 3.0f, 3.0f, 3.0f,3.0f});


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }


}
