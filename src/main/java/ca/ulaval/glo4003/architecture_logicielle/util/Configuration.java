package ca.ulaval.glo4003.architecture_logicielle.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Configuration {
	private static Map<String, String> configs;
	private static String configFile = "database/config.txt";

	private Configuration() {
	}

	public static String getConfig(String key) {
		if (configs == null) {
			configs = new HashMap<String, String>();
			loadConfig();
		}
		return configs.get(key);
	}

	private static void loadConfig() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					configFile));
			String line = reader.readLine();
			while (line != null) {
				parseToMap(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parseToMap(String line) {
		if (line.length() != 0) {
			StringTokenizer tokenizer = new StringTokenizer(line, ",");
			String configName = tokenizer.nextToken();
			String configValue = tokenizer.nextToken();
			configs.put(configName, configValue);
		}
	}
}
