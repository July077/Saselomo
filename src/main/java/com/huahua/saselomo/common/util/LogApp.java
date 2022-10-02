package com.huahua.saselomo.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LogApp {
	private static Logger logger = (Logger)LogManager.getLogger(LogApp.class.getName());
	public static Logger getLogger(){
		return logger;
	}
}
