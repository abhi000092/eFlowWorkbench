package com.workbench.client.core;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	
	

		// Initialize Log4j logs
	private static Logger Log;
			public static Logger setLogger(String ClassName) {
				PropertyConfigurator.configure(System.getProperty("user.dir")+"/PropertiesFiles/Log.properties");
				return  Log = Logger.getLogger(ClassName.getClass());
				
			}
		
		 public static void info(String message) {

				Log.info(message);

				}

		 public static void warn(String message) {

		    Log.warn(message);

			}

		 public static void error(String message) {

		    Log.error(message);

			}

		 public static void fatal(String message) {

		    Log.fatal(message);

			}

		 public static void debug(String message) {

		    Log.debug(message);

			}

		}