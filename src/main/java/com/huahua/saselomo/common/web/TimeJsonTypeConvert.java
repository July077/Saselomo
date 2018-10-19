package com.huahua.saselomo.common.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * springmvc返回的json串中，Date类型属性默认返回一个Long型的时间戳。
 * 通过此类继承JsonSerializer来自定义格式，
 * 	之后在实体类对应的date类型的字段的getter方法上添加注解。
 * 	@JsonSerialize(using=DateJsonTypeConvert.class)
 * 	这样会将long型转化为需要的格式显示。
 * 
 * 工具类,序列化时间格式,用于页面展示
 * 
 * @author Lin·Y
 *
 */
public class TimeJsonTypeConvert extends JsonSerializer<Date>{
	//用于序列化字符串(例如转换为json格式)
	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider arg2) throws IOException {
		//自己定义日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		//将数据以json格式输出
		jsonGenerator.writeString(sdf.format(date));
	}

}
