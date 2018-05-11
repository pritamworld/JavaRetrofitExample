
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;

//http://seleniummaster.com/sitecontent/index.php/code-tutorial/java-tutorial/java-pdf/307-create-pdf-in-java
public class CreatePdfDocument {

    public static void main(String[] args)
    {
        // Create a new empty document
        PDDocument pdDocument=new PDDocument();
        // Create a new blank page in letter size and add it to the document
        PDPage pdPage=new PDPage(PDPage.PAGE_SIZE_LETTER);
        pdDocument.addPage( pdPage );
        //get the pdf dpi
        PDRectangle cropBox = pdPage.findCropBox();
        Dimension dimension = cropBox.createDimension();
        System.out.println("dpi is :    "+dimension);
        //get the page width and page height
        int pageWidth=dimension.width;
        int pageHeight=dimension.height;
        //set page top and bottom margin
        int topMargin=20;
        int bottomMargin=20;
        int leftMargin=20;
        int rightMargin=20;
        //pdf size
        //maximum Y position
        float maxY=(pageHeight-topMargin);
        //minimum y position
        float minY=bottomMargin;
        //maximum x position
        float maxX=(pageWidth-rightMargin);
        //minimum x position
        float minX=leftMargin;
        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.TIMES_ROMAN;
        try
        {
            // Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
            //set font size 12
            contentStream.setFont( font, 12 );
            //set line height 15
            float lineHeight=15;
            //begin text
            contentStream.beginText();
            //move cursor to initial x and y position from the top
            contentStream.moveTextPositionByAmount(minX, maxY);
            //write a line
            contentStream.drawString("This is the line at position   "+ minX +" ,"+maxY);
            //contentStream.drawLine(10,10,100,10);
            //loop to print multiple lines
            for(int line=2;line<maxY;line++)
            {
                //maxY reduce by line height
                maxY=maxY-lineHeight;
                //move cursor to down by line height
                contentStream.moveTextPositionByAmount(0, -lineHeight);
                //write a line
                contentStream.drawString("This is the line at position   "+ minX +" ,"+maxY);
                //break if maxY is less than min Y
                if(maxY<=minY)
                    break;
            }
            //close the text
            contentStream.endText();
            //close the stream
            contentStream.close();
            try
            {
                //save the file
                pdDocument.save("test.pdf");
                //close the file
                pdDocument.close();
            }
            catch (COSVisitorException e)
            {
                e.printStackTrace();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


