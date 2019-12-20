package com.example.qiutt.demo.transienttest;

import java.io.Serializable;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-06-19 14:01
 */
public class Rectangle implements Serializable {
	private static final long serialVersionUID = 2582883024670040357L;
	private Integer width;
	private Integer height;
	private transient Integer area;//因为面积=长度*宽度，可以通过可序列化的两个字段得知，因此面积可以不用序列化

	public Rectangle (Integer width, Integer height){
		this.width = width;
		this.height = height;
		this.area = width * height;
	}

	public void setArea(){
		this.area = this.width * this.height;
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer(40);
		sb.append("width : ");
		sb.append(this.width);
		sb.append("\nheight : ");
		sb.append(this.height);
		sb.append("\narea : ");
		sb.append(this.area);
		return sb.toString();
	}
}
