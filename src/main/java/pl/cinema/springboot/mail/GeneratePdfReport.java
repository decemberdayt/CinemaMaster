package pl.cinema.springboot.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cinema.springboot.model.views.PurchaseSummary;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.List;

public class GeneratePdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static DataSource ticketsConfirmation(List<PurchaseSummary> purchaseSummary) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(5);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("IdTicket", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("reduced", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("IdSeat", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("hallRow", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (PurchaseSummary summary : purchaseSummary) {
                PdfPCell cell;

                /* idTicket */
                cell = new PdfPCell(new Phrase(String.valueOf(summary.getIdTicket())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                /* price */
                cell = new PdfPCell(new Phrase(String.valueOf(summary.getPrice())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                /* reduced */
                cell = new PdfPCell(new Phrase(String.valueOf(summary.getReduced())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                /* idSeat */
                cell = new PdfPCell(new Phrase(String.valueOf(summary.getIdSeat())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                /* hallRow */
                cell = new PdfPCell(new Phrase(String.valueOf(summary.getHallRow())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();

            //Paragraph paragraph = new Paragraph();
            Paragraph paragraph = null;
            paragraph = new Paragraph(new Chunk("Purchase confirmation: ", headFont));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk("Buyer Name: " + purchaseSummary.get(0).getBuyerName()));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk("Buyer Surname: " + purchaseSummary.get(0).getBuyerSurname()));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk("Title: " + purchaseSummary.get(0).getTitle()));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk("Show date: " + purchaseSummary.get(0).getShowDate() + " " + purchaseSummary.get(0).getShowTime()));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk("Duration (min): " + purchaseSummary.get(0).getDurationMin()));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk("Hall name: " + purchaseSummary.get(0).getHallName()));
            document.add(paragraph);

            paragraph = new Paragraph(new Chunk(" "));
            document.add(paragraph);

            document.add(table);
            document.close();

        } catch (Exception ex) {
            logger.error("Error occurred: {0}", ex);
        }
        DataSource dataSource = new ByteArrayDataSource(out.toByteArray(), "application/pdf");
        return dataSource;
    }
}
