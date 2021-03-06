package com.swayam.demo.pdfbox2;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * https://svn.apache.org/viewvc/pdfbox/trunk/examples/src/main/java/org/apache/pdfbox/examples/pdmodel/HelloWorldTTF.java?view=markup
 * 
 * @author paawak
 *
 */
public class BanglaPdfGenerationTest {

	/**
	 * The unicode of this is given below:
	 * 
	 * <pre>
	 * \u0986\u09ae\u09bf  \u0995\u09cb\u09a8 \u09aa\u09a5\u09c7  \u0995\u09cd\u09b7\u09c0\u09b0\u09c7\u09b0 \u09b7\u09a8\u09cd\u09a1  \u09aa\u09c1\u09a4\u09c1\u09b2 \u09b0\u09c1\u09aa\u09cb  \u0997\u0999\u09cd\u0997\u09be \u098b\u09b7\u09bf
	 * </pre>
	 * 
	 */
	private static final String BANGLA_TEXT_1 = "আমি কোন পথে ক্ষীরের লক্ষ্মী ষন্ড পুতুল রুপো গঙ্গা ঋষি";
	private static final String BANGLA_TEXT_2 = "দ্রুত গাঢ় শেয়াল অলস কুকুর জুড়ে জাম্প ধুর্ত  হঠাৎ ভাঙেনি মৌলিক ঐশি দৈ   ঋষি কল্লোল ব্যাস নির্ভয় ";

	static {
		System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
	}

	public static void main(String[] args) throws IOException {

		String filename = System.getProperty("user.home") + "/a.pdf";

		System.err.println("filename=" + filename);

		PDDocument doc = new PDDocument();
		try {

			PDPage page1 = new PDPage();
			doc.addPage(page1);

			// PDFont font = PDType0Font.load(doc, BanglaPdfGenerationTest.class
			// .getResourceAsStream("/font/ttf/bengali/SOLAIMANLIPI_22-02-2012.TTF"),
			// false);

			PDFont font = PDType0Font.load(doc,
					BanglaPdfGenerationTest.class.getResourceAsStream("/font/ttf/bengali/Lohit-Bengali.ttf"), true);

			PDPageContentStream contents1 = new PDPageContentStream(doc, page1);
			contents1.setLineWidth(400);
			contents1.beginText();
			contents1.setFont(font, 12);
			contents1.newLineAtOffset(10, 700);
			contents1.showText(BANGLA_TEXT_1);
			contents1.newLineAtOffset(0, -30);
			contents1.showText(BANGLA_TEXT_2);
			contents1.endText();
			contents1.close();

			PDPage page2 = new PDPage();
			doc.addPage(page2);
			PDPageContentStream contents2 = new PDPageContentStream(doc, page2);
			PDImageXObject pdImage = PDImageXObject
					.createFromFile(BanglaPdfGenerationTest.class.getResource("/image/bangla-text.png").getFile(), doc);
			contents2.drawImage(pdImage, 20, 20, pdImage.getWidth(), pdImage.getHeight());
			contents2.close();

			doc.save(filename);
		} finally {
			doc.close();
		}

		System.out.println("------------------------");
		printHexEncodedUnicodeChars(BANGLA_TEXT_2);
	}

	private static void printHexEncodedUnicodeChars(String text) {
		for (char unicodeChar : text.toCharArray()) {
			System.out.println("\\u0" + Integer.toHexString(unicodeChar).toUpperCase());
		}
	}

}
