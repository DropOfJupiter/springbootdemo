package com.example.qiutt.demo.export;

import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-07-08 14:03
 */
public class ResourceLoaderUserAgent extends ITextUserAgent {
	public ResourceLoaderUserAgent(ITextOutputDevice outputDevice) {
		super(outputDevice);
	}

	protected InputStream resolveAndOpenStream(String uri) {

		InputStream is = super.resolveAndOpenStream(uri);
		String fileName = "";
		try {
			String[] split = uri.split("/");
			fileName = split[split.length - 1];
		} catch (Exception e) {
			return null;
		}

		if (is == null) {
			// Resource is on the classpath
			try {
				is = ResourceLoaderUserAgent.class.getResourceAsStream("export-template/" + fileName);
			} catch (Exception e) {
			}

			if (is == null) {
				// Resource is in the file system
				try {
					is = new FileInputStream(new File("G:\\IntelliJCode\\gitlab\\springbootdemo\\src\\main\\resources\\export-template\\" + fileName));
				} catch (Exception e) {
				}
			}
			return is;
		}
		return is;
	}
}
