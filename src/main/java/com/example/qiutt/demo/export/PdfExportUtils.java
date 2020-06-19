package com.example.qiutt.demo.export;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-06-19 15:24
 */
public class PdfExportUtils {
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
}
