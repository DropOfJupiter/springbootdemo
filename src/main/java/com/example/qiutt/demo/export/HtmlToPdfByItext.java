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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-06-17 18:09
 */
@SpringBootTest
@Slf4j
public class HtmlToPdfByItext {

	public static String FILE_PATH = "D:/";

	public static String IMAGE_PATH="F:\\靠谱云\\02 工作内容\\2020\\06 海外X云\\01 财务服务\\u4573.png";

	@Test
	public void test() throws IOException, DocumentException {
		//先删除
//		File file=new File(FILE_PATH+"HtmlToPdfByItext.pdf");
//		file.deleteOnExit();
		// 设置字体(由于自己缺少这个包)
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
		// 第一步，创建document对象
		Rectangle rectPageSize = new Rectangle(PageSize.A4);

		//下面代码设置页面横置
		//rectPageSize = rectPageSize.rotate();

		//创建document对象并指定边距
		//Document document = new Document(rectPageSize,50,50,50,50);
		Document document = new Document(rectPageSize,50,50,50,50);
		try
		{
			// 第二步,将Document实例和文件输出流用PdfWriter类绑定在一起
			//从而完成向Document写，即写入PDF文档
			PdfWriter.getInstance(document,new FileOutputStream(FILE_PATH+System.currentTimeMillis()+".pdf"));
			//第3步,打开文档
			document.open();
			document.addTitle("我的pdf");
			document.addSubject("Bill");
			document.addKeywords("this is a keyword");
			document.addAuthor("qiutt");
			document.addCreator("creator");
			document.addProducer();
			document.addCreationDate();
			document.addHeader("my header","this is a header");
			//第3步,向文档添加文字. 文档由段组成

			//1、第一段：左边图标
			Image image = Image.getInstance(IMAGE_PATH);
//			image.setAlignment(Element.ALIGN_LEFT);
//			Chunk chunk=new Chunk("Bill");
			document.add(image);
		//	document.add(chunk);
			//2、右边BILL
			document.add(new Paragraph("14 Cliffwood Ave Suite 300"));
			document.add(new Paragraph("Matawan, NJ 07747"));
			document.add(new Paragraph("United States "));

			PdfPTable table = new PdfPTable(3);
			// 生成表格   TODO 其他操作请自己百度
			for(int i=0;i<12;i++)
			{
				if (i == 0)
				{
					PdfPCell cell = new PdfPCell();
					cell.setBorderColor(BaseColor.BLACK);
					cell.setBorderWidth(1.5f);
					cell.setColspan(3);
					//cell.setBackgroundColor(new Color(180,180,180));
					cell.addElement(new Paragraph("表格头" ,FontChinese));
					table.addCell(cell);
				}
				else
				{
					PdfPCell cell = new PdfPCell();
					cell.addElement(new Paragraph("表格内容",FontChinese ));
					table.addCell(cell);
				}
			}
			document.add(table);
		}
		catch (DocumentException de)
		{
			System.err.println(de.getMessage());
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		//关闭document
		document.close();

		System.out.println("PDF生成成功!");
	}

	@Test
	public void chunk() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document , new FileOutputStream(FILE_PATH));
		document.open();

		//建块
		Chunk chunk1 = new Chunk("Cat");
		Chunk chunk2 = new Chunk("DOG");

		//建短语
		Phrase phrase = new Phrase();
		phrase.add(chunk1);
		phrase.add(chunk2);
		phrase.add("Hello world");

		//建段落
		Paragraph paragraph = new Paragraph();
		paragraph.add(phrase);
		paragraph.add("Hello World");

		//设置段落对齐方式
		paragraph.setAlignment(Element.ALIGN_LEFT);
		//设置缩进
		paragraph.setIndentationLeft(100f);

		Paragraph paragraph1 = new Paragraph();
		paragraph1.add("AA");

		//注意增加段落时会自动换行
		document.add(paragraph);
		document.add(paragraph1);

		document.close();

	}

	@Test
	public void fromHtml() throws IOException, DocumentException, InterruptedException {
		final File htmlFile = File.createTempFile("temp", ".pdf");//创建临时文件
		FileOutputStream fos = new FileOutputStream(htmlFile);
		Document document = new Document();
		//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE_PATH+System.currentTimeMillis()+".pdf"));
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		document.open();
		//String htmlString = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html;  charset=\"UTF-8\"/><title>用户总账</title></head><style type=\"text/css\">body {font-family:'Microsoft YaHei';width:1000px;margin:0 auto;}tr{height:38px}</style><body><div><img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=573484643,3618883016&fm=58&s=9133CC329420DF114CE799C6030010BA&bpow=121&bpoh=75\" /></div><div><h2 style=\"text-align: center;font-weight: bolder;\">year年month月资源结算单</h2></div><div style=\"font-weight: bolder;font-size: 18px;\"><table><tr><td style='width:150px;font-weight: bolder;'>客户名称：</td><td style=\"width:370px\">fullName</td><td style='width:220px;font-weight: bolder;'>本期消费总计：</td><td style='width:150px;text-align: right;'>￥0</td></tr><tr><td style='width:150px;font-weight: bolder;'>用户账号：</td><td style=\"width:370px\">userName</td><td style='width:220px;font-weight: bolder;'>本期应还总计：</td><td style='width:150px;text-align: right;'>￥0</td></tr><tr><td style='width:150px;font-weight: bolder;'>账单周期：</td><td style=\"width:370px\">billDate</td><td style='font-weight: bolder;width:220px;'>账户余额：</td><td style='width:150px;text-align: right;'>￥0</td></tr></table></div><div style=\"margin-top:50px\"><h3>收款账户</h3></div><div><table><tr><td style=\"width:120px\">户名</td><td>靠谱云股份有限公司</td></tr><tr><td style=\"width:120px\">账号</td><td>3510 1511 0010 5000 2349</td></tr><tr><td style=\"width:120px\">开户行</td><td>中国建设银行股份有限公司厦门文灶支行</td></tr></table></div><div style=\"margin-top:50px\"><h3>本期消费账单</h3></div><div><table style=\"width:100%;\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"font-weight:bolder;background-color:#b3d2f9;padding:5px;height:30px\"><td style=\"text-align:left;width:300px\">产品线</td><td style=\"text-align:left;width:300px\">付费模式</td><td style=\"text-align:right;width:400px\">消费金额(含代金券)</td></tr><tr><td styel='height:30px;'>0</td><td styel='height:30px;'>1</td><td style=\"text-align:right;padding-right:10px;height:30px;\">￥2</td></tr><tr><td styel='height:30px;'>0</td><td styel='height:30px;'>1</td><td style=\"text-align:right;padding-right:10px;height:30px;\">￥2</td></tr><tr><td styel='height:30px;'>0</td><td styel='height:30px;'>1</td><td style=\"text-align:right;padding-right:10px;height:30px;\">￥2</td></tr></table></div><div><p style=\"text-align:right;padding-right:150px;font-weight:bolder\">本期消费总计(含代金券)：￥0</p></div><div><p>本期应还总计：￥<span>0</span></p><p>=本期消费欠款 ￥<span>0</span> + 历史欠款 ￥<span>0</span></p></div><div><p style='color:red;font-size: 12px;'>*注意： 请在收到结算单后，及时核对确认，如在三个工作日内没有收到您的回复，则视为同意上述结算信息。</p></div><div style=\"margin-top:50px\"><p style=\"text-align:right;padding-right:180px;font-weight:bolder\">盖章处</p></div></body></html>";
		//String htmlString ="<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html;\"  charset=\"UTF-8\"/><title>账单</title></head><style>            body,            html {                font-family: 'Microsoft YaHei';                background-color: rgb(245, 245, 245);                margin: 0;                height: 100%;            }            .main {                width: 1000px;                height: 100%;                box-sizing: border-box;                margin: 0 auto;                background-color: #fff;            }            .content {                padding: 20px;                background-color: #fff;            }            .fr {                float: right;            }            .top .title {                position: relative;                top: 10px;                font-size: 40px;                font-weight: 500;            }            table {                margin-top: 10px;                border-collapse: collapse;                            }            table,            td,            th {                text-align: left;                border: 1px solid black;            }            table tr .textRight {                text-align: right;            }            td,            th {                padding: 0 5px;                height: 30px;                line-height: 30px;            }            .smallCol {                width: 120px;            }            .bigCol {                width: 480px;                max-width: 480px;                overflow: hidden;                text-overflow: ellipsis;                white-space: normal;            }            .bottomTip {                margin-top: 20px;            }            .totalPrice {                font-size: 20px;                font-weight: 600;            }</style><body><div class=\"main\"><div class=\"content\"><div class=\"top\"><img src=\"./logo.jpg\" alt=\"logo\" /><span class=\"title fr\">Bill</span></div><div>14 Cliffwood Ave Suite 300</div><div>                    Matawan, NJ 07747<span class=\"fr\">Bill Date：05-2020</span></div><div>                    United States<span class=\"fr\">useraccount@163.com</span></div><table><tr><th class=\"smallCol\">Start</th><th class=\"smallCol\">End</th><th class=\"bigCol\">Description</th><th class=\"smallCol textRight\">Quantity</th><th class=\"smallCol textRight\">Price</th></tr><tr><td class=\"smallCol\">06-22 12:12</td><td class=\"smallCol\">09-22 12:12</td><td class=\"bigCol\">aajakdfbkjadgfaehb</td><td class=\"smallCol textRight\">200</td><td class=\"smallCol textRight\">$200</td></tr><tr><td colspan=\"5\"><div class=\"totalPrice fr\">Total(in USD)：$200</div></td></tr></table><div class=\"bottomTip\">                    To make a payment, view your billing history, or ask                    questions about your account, please login to                    https://xxxx.com.</div></div></div></body></html>";
		//String htmlString ="<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /><meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" /><title>账单</title><style>body,html {font-family: 'Microsoft YaHei';background-color: rgb(245, 245, 245); margin: 0;height: 100%;}.main {width: 1000px;height: 100%;box-sizing: border-box;margin: 0 auto;background-color: #fff;}.content {padding: 20px;background-color: #fff;}.fr {float: right;}.top .title {position: relative;top: 10px;font-size: 40px;font-weight: 500;}table {margin-top: 10px;border-collapse: collapse;}table,td,th {text-align: left;border: 1px solid black;}table tr .textRight {text-align: right;} td,th {padding: 0 5px;height: 30px;line-height: 30px;}.smallCol {width: 120px;}.bigCol {width: 480px;max-width: 480px;overflow: hidden;text-overflow: ellipsis;white-space: normal;}.bottomTip {margin-top: 20px;}.totalPrice {font-size: 20px;font-weight: 600;}</style></head><body><div class=\"main\"><div class=\"content\"><div class=\"top\"><img src=\"./logo.jpg\" alt=\"logo\" /><span class=\"title fr\">Bill</span></div><div>14 Cliffwood Ave Suite 300</div><div>                    Matawan, NJ 07747<span class=\"fr\">Bill Date：05-2020</span></div><div>                    United States<span class=\"fr\">useraccount@163.com</span></div><table><tr><th class=\"smallCol\">Start</th><th class=\"smallCol\">End</th><th class=\"bigCol\">Description</th><th class=\"smallCol textRight\">Quantity</th><th class=\"smallCol textRight\">Price</th></tr><tr><td class=\"smallCol\">06-22 12:12</td><td class=\"smallCol\">09-22 12:12</td><td class=\"bigCol\">aajakdfbkjadgfaehb</td><td class=\"smallCol textRight\">200</td><td class=\"smallCol textRight\">$200</td></tr><tr><td colspan=\"5\"><div class=\"totalPrice fr\">Total(in USD)：$200</div></td></tr></table><div class=\"bottomTip\">                    To make a payment, view your billing history, or ask                    questions about your account, please login to                    https://xxxx.com.</div></div></div></body></html>";
//		String htmlString="<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\" />\n" +
//				"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
//				"        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" /><title>账单</title></head>" +
//				"<style type=\"text/css\">body,\n" +
//				"            html {\n" +
//				"                font-family: 'Microsoft YaHei';\n" +
//				"                background-color: rgb(245, 245, 245);\n" +
//				"                margin: 0;\n" +
//				"                height: 100%;\n" +
//				"            }</style><body><div style=\"background-color: #000;\">1111</div></body></html>";
		String htmlString="";
// ByteArrayInputStream bin = new ByteArrayInputStream(htmlString.getBytes("GBK"));
//		//-----------------
//		OutputStream os = new FileOutputStream(FILE_PATH+System.currentTimeMillis()+".pdf");
//		POIFSFileSystem fs = new POIFSFileSystem();
//		fs.createDocument(bin, "WordDocument");
//		fs.writeFilesystem(os);
////            os.close();
////            is.close();
//		//------------------
//		XMLWorkerHelper.getInstance().parseXHtml(mPdfWriter, document, bin, null, new PdfExportUtils.ChinaFontProvide());
		XMLWorkerHelper xmlWorkerHelper=XMLWorkerHelper.getInstance();
		xmlWorkerHelper.parseXHtml(writer, document, new StringReader(htmlString));
//
//		byte[] byteArray = htmlString.getBytes(Charset.forName("UTF-8"));
//		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, byteArrayInputStream, Charset.forName("UTF-8"));
//		Thread.sleep(3000l);
		document.close();
		log.info("临时文件所在的本地路径：{},大小{}" , htmlFile.getPath(),htmlFile.length());

	}

	@Test
	public void tmp() throws IOException {
		final File htmlFile = File.createTempFile("temp", ".txt");//创建临时文件
		log.info("临时文件所在的本地路径：" + htmlFile.getPath());
		FileOutputStream fos = new FileOutputStream(htmlFile);
		try {
			//这里处理业务逻辑
		} finally {
			//关闭临时文件
			fos.flush();
			fos.close();

			//htmlFile.deleteOnExit();//程序退出时删除临时文件
		}
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
