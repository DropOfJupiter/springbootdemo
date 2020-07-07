package com.example.qiutt.demo.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-06-17 18:09
 */
@SpringBootTest
@Slf4j
public class HtmlToPdfByItextRenderer {

	public static String FILE_PATH = "D:/";

	public static String IMAGE_PATH="F:\\靠谱云\\02 工作内容\\2020\\06 海外X云\\01 财务服务\\u4573.png";

	@Test
	public void iTextRenderer() throws IOException, DocumentException {
		//先根据导出模板生成HTML
		String html=PdfExportUtils.initHTML();
//		final File htmlTmpFile = File.createTempFile("temp", ".html");//创建临时文件
//		htmlTmpFile.
		// step 1
		String url = new File(PdfExportUtils.HTML_PATH).toURI().toURL().toString();
		System.out.println(url);

		// step 2
		OutputStream os = new FileOutputStream(PdfExportUtils.PDF_PATH);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);
		// 图片为本地的绝对路径时,如http://www.baidu.com/a.jpg,则为<img src="a.jpg" />
//		renderer.getSharedContext().setBaseURL("http://www.baidu.com/");
//		// 图片为HTTP链接时，src只需填写相对路径，如D:/a.jpg,则为<img src="a.jpg" />
//		renderer.getSharedContext().setBaseURL("file:/D:/");

		// step 3 解决中文支持
		ITextFontResolver fontResolver = renderer.getFontResolver();
//		if("linux".equals(getCurrentOperatingSystem())){
//			fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//		}else{
//		fontResolver.addFont("c:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//		}

		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}


	@Test
	public void fromHtmlToPDF() throws IOException {
		try {
			String oriHTML = FileUtils.readFileToString(new File("G:\\IntelliJCode\\gitlab\\springbootdemo\\src\\main\\resources\\export-template\\month_consumption_bill_template.html"));
			log.info("oriHTML:{}",oriHTML);
			oriHTML=oriHTML.replace("${BILL_DATE}","05-2020");
			oriHTML=oriHTML.replace("${EMAIL}","useraccount@163.com");
			StringBuffer tableInfos=new StringBuffer();
			/**
			 * <tr>
			 <td class="smallCol">06-22 12:12</td>
			 <td class="smallCol">09-22 12:12</td>
			 <td class="bigCol">aajakdfbkjadgfaehb</td>
			 <td class="smallCol textRight">200</td>
			 <td class="smallCol textRight">$200</td>
			 </tr>
			 */
			for(int i=0;i<5;i++){
				tableInfos.append("<tr>");
				tableInfos.append("<td class=\"smallCol\">06-22 12:12</td>");
				tableInfos.append("<td class=\"smallCol\">09-22 12:12</td>");
				tableInfos.append("<td class=\"bigCol\">aajakdfbkjadgfaehb</td>");
				tableInfos.append("<td class=\"smallCol textRight\">200</td>");
				tableInfos.append("<td class=\"smallCol textRight\">$200</td>");
				tableInfos.append("</tr>");
			}
			oriHTML=oriHTML.replace("${FOR_EACH}",tableInfos.toString());
			oriHTML=oriHTML.replace("${TOTAL}","$1000");
			log.info("oriHTML:{}",oriHTML);
			final File htmlFile = File.createTempFile("temp", ".pdf");//创建临时文件
			log.info("临时文件所在的本地路径：" + htmlFile.getPath());
			FileOutputStream fos = new FileOutputStream(htmlFile);
			Document document = new Document();
			//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE_PATH+System.currentTimeMillis()+".pdf"));
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.open();
			String htmlString = oriHTML;
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(htmlString));
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
