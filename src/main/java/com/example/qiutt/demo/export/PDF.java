package com.example.qiutt.demo.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-06-17 18:09
 */
@SpringBootTest
@Slf4j
public class PDF {

	public static String FILE_PATH = "D:/";

	public static String IMAGE_PATH="F:\\靠谱云\\02 工作内容\\2020\\06 海外X云\\01 财务服务\\u4573.png";

	@Value("age")
	private String age;

	@Test
	public void age(){
		log.info("age{}",age);
	}

	@Test
	public void test() throws IOException, DocumentException {
		//先删除
//		File file=new File(FILE_PATH+"PDF.pdf");
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
	public void fromHtml() throws IOException, DocumentException {
		final File htmlFile = File.createTempFile("temp", ".pdf");//创建临时文件
		log.info("临时文件所在的本地路径：" + htmlFile.getPath());
		FileOutputStream fos = new FileOutputStream(htmlFile);
		Document document = new Document();
		//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE_PATH+System.currentTimeMillis()+".pdf"));
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		document.open();
		String htmlString = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html;  charset=\"UTF-8\"/><title>用户总账</title></head><style type=\"text/css\">body {font-family:'Microsoft YaHei';width:1000px;margin:0 auto;}tr{height:38px}</style><body><div><img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=573484643,3618883016&fm=58&s=9133CC329420DF114CE799C6030010BA&bpow=121&bpoh=75\" /></div><div><h2 style=\"text-align: center;font-weight: bolder;\">year年month月资源结算单</h2></div><div style=\"font-weight: bolder;font-size: 18px;\"><table><tr><td style='width:150px;font-weight: bolder;'>客户名称：</td><td style=\"width:370px\">fullName</td><td style='width:220px;font-weight: bolder;'>本期消费总计：</td><td style='width:150px;text-align: right;'>￥0</td></tr><tr><td style='width:150px;font-weight: bolder;'>用户账号：</td><td style=\"width:370px\">userName</td><td style='width:220px;font-weight: bolder;'>本期应还总计：</td><td style='width:150px;text-align: right;'>￥0</td></tr><tr><td style='width:150px;font-weight: bolder;'>账单周期：</td><td style=\"width:370px\">billDate</td><td style='font-weight: bolder;width:220px;'>账户余额：</td><td style='width:150px;text-align: right;'>￥0</td></tr></table></div><div style=\"margin-top:50px\"><h3>收款账户</h3></div><div><table><tr><td style=\"width:120px\">户名</td><td>靠谱云股份有限公司</td></tr><tr><td style=\"width:120px\">账号</td><td>3510 1511 0010 5000 2349</td></tr><tr><td style=\"width:120px\">开户行</td><td>中国建设银行股份有限公司厦门文灶支行</td></tr></table></div><div style=\"margin-top:50px\"><h3>本期消费账单</h3></div><div><table style=\"width:100%;\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"font-weight:bolder;background-color:#b3d2f9;padding:5px;height:30px\"><td style=\"text-align:left;width:300px\">产品线</td><td style=\"text-align:left;width:300px\">付费模式</td><td style=\"text-align:right;width:400px\">消费金额(含代金券)</td></tr><tr><td styel='height:30px;'>0</td><td styel='height:30px;'>1</td><td style=\"text-align:right;padding-right:10px;height:30px;\">￥2</td></tr><tr><td styel='height:30px;'>0</td><td styel='height:30px;'>1</td><td style=\"text-align:right;padding-right:10px;height:30px;\">￥2</td></tr><tr><td styel='height:30px;'>0</td><td styel='height:30px;'>1</td><td style=\"text-align:right;padding-right:10px;height:30px;\">￥2</td></tr></table></div><div><p style=\"text-align:right;padding-right:150px;font-weight:bolder\">本期消费总计(含代金券)：￥0</p></div><div><p>本期应还总计：￥<span>0</span></p><p>=本期消费欠款 ￥<span>0</span> + 历史欠款 ￥<span>0</span></p></div><div><p style='color:red;font-size: 12px;'>*注意： 请在收到结算单后，及时核对确认，如在三个工作日内没有收到您的回复，则视为同意上述结算信息。</p></div><div style=\"margin-top:50px\"><p style=\"text-align:right;padding-right:180px;font-weight:bolder\">盖章处</p></div></body></html>";
//		ByteArrayInputStream bin = new ByteArrayInputStream(htmlString.getBytes("GBK"));
//		//-----------------
//		OutputStream os = new FileOutputStream(FILE_PATH+System.currentTimeMillis()+".pdf");
//		POIFSFileSystem fs = new POIFSFileSystem();
//		fs.createDocument(bin, "WordDocument");
//		fs.writeFilesystem(os);
////            os.close();
////            is.close();
//		//------------------
//		XMLWorkerHelper.getInstance().parseXHtml(mPdfWriter, document, bin, null, new PdfExportUtils.ChinaFontProvide());
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(htmlString));

		document.close();
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
}
