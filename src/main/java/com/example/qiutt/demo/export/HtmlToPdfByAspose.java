package com.example.qiutt.demo.export;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlLoadOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author qiutt
 * @description:
 * aspose 样式支持不好，html里的图片无法显示
 * https://blog.aspose.com/2020/01/30/convert-html-to-pdf-in-java/
 * @create 2020-07-01 13:52
 */
@Slf4j
public class HtmlToPdfByAspose {



	@Test
	public void aspose() throws IOException {
		HtmlLoadOptions htmlOptions = new HtmlLoadOptions();
		Document doc = new Document(PdfExportUtils.HTML_PATH, htmlOptions);
		doc.save("toPDFByAspose.pdf");
	}

	@Test
	public void asposeFromURL() throws IOException {
		// Create and initialize URL
		URL oracleURL = new URL("https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html");
		// Get web page as input stream
		InputStream is = oracleURL.openStream();
		// Initialize HTML load options
		HtmlLoadOptions htmloptions = new HtmlLoadOptions();
		// Load stream into Document object
		Document pdfDocument = new Document(is, htmloptions);
		// Save output as HtmlToPdfByItext format
		pdfDocument.save("toPDFByAsposeFROMURL.pdf");
	}
}
