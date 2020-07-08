package com.example.qiutt.demo.export;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-06-19 15:24
 */
@Slf4j
public class PdfExportUtils {

	public static final String HTML_PATH = "G:\\IntelliJCode\\gitlab\\springbootdemo\\src\\main\\resources\\export-template\\month_consumption_bill_template.html";
	public static final String PDF_PATH = "G:\\IntelliJCode\\gitlab\\springbootdemo\\src\\main\\resources\\export-template\\month_consumption_bill_template.pdf";
	public static final String FONT_PATH = "G:\\IntelliJCode\\gitlab\\springbootdemo\\src\\main\\resources\\export-template\\simsun.ttc";


	/**
	 * 解决中文字体
	 * <p>Title: ChinaFontProvide</p>
	 * @author  Liyan
	 * @date    2017年4月1日 下午6:30:48
	 */
	public static final class ChinaFontProvide implements FontProvider {

		@Override
		public Font getFont(String arg0, String arg1, boolean arg2, float arg3, int arg4, BaseColor arg5) {
			BaseFont bfChinese = null;
			try {
				bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				//也可以使用Windows系统字体(TrueType)
//                bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//                String prefixFont = "";
//                String os = System.getProperties().getProperty("os.name");
//                if (os.startsWith("win") || os.startsWith("Win")) {
//                    prefixFont = "C:\\Windows\\Fonts" + File.separator;
//                } else {
//                    prefixFont = "/usr/share/fonts/chinese" + File.separator;
//                }
//                bfChinese = BaseFont.createFont(prefixFont + "msyh.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Font FontChinese = new Font(bfChinese);
			return FontChinese;
		}

		@Override
		public boolean isRegistered(String arg0) {
			return false;
		}
	}



	public static String initHTML() throws IOException {
		File template= ResourceUtils.getFile("classpath:export-template/month_consumption_bill_template.html");
		String oriHTML = FileUtils.readFileToString(template);
		log.info("oriHTML:{}", oriHTML);
		oriHTML = oriHTML.replace("${BILL_DATE}", "05-2020");
		oriHTML = oriHTML.replace("${EMAIL}", "useraccount@163.com");
		StringBuffer tableInfos = new StringBuffer();
		/**
		 * <tr>
		 <td class="smallCol">06-22 12:12</td>
		 <td class="smallCol">09-22 12:12</td>
		 <td class="bigCol">aajakdfbkjadgfaehb</td>
		 <td class="smallCol textRight">200</td>
		 <td class="smallCol textRight">$200</td>
		 </tr>
		 */
		for (int i = 0; i < 5; i++) {
			tableInfos.append("<tr>");
			tableInfos.append("<td class=\"smallCol\">06-22 12:12</td>");
			tableInfos.append("<td class=\"smallCol\">09-22 12:12</td>");
			tableInfos.append("<td class=\"bigCol\">aajakdfbkjadgfaehb</td>");
			tableInfos.append("<td class=\"smallCol\">200</td>");
			tableInfos.append("<td class=\"smallCol\">$200</td>");
			tableInfos.append("</tr>");
		}
		oriHTML = oriHTML.replace("${FOR_EACH}", tableInfos.toString());
		oriHTML = oriHTML.replace("${TOTAL}", "$1000");
		log.info("oriHTML:{}", oriHTML);
		return oriHTML;
	}
}
