package com.huahua.saselomo.common.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * 工具类,序列化日期格式,用于页面展示
 * @author Lin·Y
 *
 */
public class DateJsonTypeConvert extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider arg2) throws IOException {
		//自定义日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//将数据以json格式输出
		jsonGenerator.writeString(sdf.format(date));
		
	}

}
