package com.workbench.client.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.workbench.client.performance.NavigationTiming;

public class FileUtil {
	private static Properties properties = new Properties();
	private static OutputStream outputStream;
	private static InputStream inputStream;
	private static File file;
	private static FileWriter fileWriter;
	private static BufferedWriter bufferedWriter;
	private static Parameters parameters;
	private static FileBasedConfigurationBuilder<FileBasedConfiguration> builder;
	private static Configuration configuration;
	private static JSONObject jsonObject;
	private static JSONArray jsonList;

	public static void writeToPropertyFile(String propertyFilePath, String propertyName, String propertyValue) {
		try {
			outputStream = new FileOutputStream(propertyFilePath);
			properties.setProperty(propertyName, propertyValue);
			properties.store(outputStream, null);
			outputStream.close();
			jsonObject = new JSONObject();
			jsonList = new JSONArray();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static void updatePropertyFile(String propertyFilePath, String propertyName, String propertyValue) {
		try {
			inputStream = new FileInputStream(propertyFilePath);
			properties.load(inputStream);
			inputStream.close();
			outputStream = new FileOutputStream(propertyFilePath);
			properties.setProperty(propertyName, propertyValue);
			properties.store(outputStream, null);
			outputStream.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static String readFromPropertyFile(String propertyFilePath, String propertyName) {
		try {
			properties.load(new FileInputStream(propertyFilePath));
			return properties.getProperty(propertyName) != null ? properties.getProperty(propertyName).trim()
					: properties.getProperty(propertyName);
		} catch (IOException io) {
			io.printStackTrace();
		}
		return null;
	}

	public static void writeOnFile(String filePath, int startCount, int endCount, String data) {
		file = new File(filePath);
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = startCount; i < endCount; i++) {
				bufferedWriter.write(data + " " + i + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void initPropertiesFile(String fileName) {
		try {
			parameters = new Parameters();
			builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
					.configure(parameters.properties().setFileName(fileName));
			configuration = builder.getConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readProperty(String fileName, String propertyName) {
		initPropertiesFile(fileName);
		return configuration.getString(propertyName);
	}

	public static void writeProperty(String fileName, String propertyName, String propertyValue) {
		try {
			initPropertiesFile(fileName);
			configuration.setProperty(propertyName, propertyValue);
			builder.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void writePerformanceAttributeToJSON(String EventName, String JSONfilePath) {
		HashMap<String, Long> performanceMatrix = NavigationTiming.loadData();
		jsonList = new JSONArray();
		for (String eventName : performanceMatrix.keySet()) {
			JSONObject JObject = new JSONObject();
			JObject.put(eventName, performanceMatrix.get(eventName));
			jsonList.add(JObject);
		}
		jsonObject =  new JSONObject();
		jsonObject.put(EventName, jsonList);

		try {
			String jsoncontent = jsonObject.toJSONString();

			File file = new File(JSONfilePath);

			if (!(file.exists())) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file, true);
			PrintWriter printer = new PrintWriter(writer);
			if (file.exists() && file.isFile()) {
				printer.println(jsoncontent);
				printer.flush();
				writer.flush();
				printer.close();
				writer.close();
			} else {
				System.out.println("Please provide a valid path to desitnation Jsonfile");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void writeToExcel(String Name) throws IOException {
	       ExcelUtils excel = new ExcelUtils(Config.Performance_ExcelFile);
	       HashMap<String, Long> performanceMatrix = NavigationTiming.loadData();
	       int count = excel.getRowCount("PerformanceMatrix")+1;
	       excel.setCellData("PerformanceMatrix", "EventName", count,Name );
	       excel.setCellData("PerformanceMatrix", "PageLoadTime", count, String.valueOf(performanceMatrix.get("PageLoadTime")));
	       excel.setCellData("PerformanceMatrix", "PageRenderTime", count, String.valueOf(performanceMatrix.get("PageRenderTime")));
	       excel.setCellData("PerformanceMatrix", "ServerResponseTime", count, String.valueOf(performanceMatrix.get("ServerResponseTime")));
	       excel.setCellData("PerformanceMatrix", "ConnectTime", count, String.valueOf(performanceMatrix.get("ConnectTime")));	
	}
}


