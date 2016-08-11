package com.rukspot.sample.am.event;

import java.io.File;

public class DataPublisherTestUtil {
	public static final String LOCAL_HOST = "localhost";

	public static void setTrustStoreParams() {
		File filePath = new File("src" + File.separator + "test"
				+ File.separator + "resources");
		if (!filePath.exists()) {
			filePath = new File("components" + File.separator + "data-bridge"
					+ File.separator + "org.wso2.carbon.databridge.agent"
					+ File.separator + "src" + File.separator + "test"
					+ File.separator + "resources");
		}
		if (!filePath.exists()) {
			filePath = new File("resources");
		}
		if (!filePath.exists()) {
			filePath = new File("test" + File.separator + "resources");
		}
		String trustStore = filePath.getAbsolutePath();
		System.setProperty("javax.net.ssl.trustStore", trustStore
				+ File.separator + "client-truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");

	}

	public static void setKeyStoreParams() {
		File filePath = new File("src" + File.separator + "test"
				+ File.separator + "resources");
		if (!filePath.exists()) {
			filePath = new File("components" + File.separator + "data-bridge"
					+ File.separator + "org.wso2.carbon.databridge.agent"
					+ File.separator + "src" + File.separator + "test"
					+ File.separator + "resources");
		}
		if (!filePath.exists()) {
			filePath = new File("resources");
		}
		if (!filePath.exists()) {
			filePath = new File("test" + File.separator + "resources");
		}
		String keyStore = filePath.getAbsolutePath();
		System.setProperty("Security.KeyStore.Location", keyStore
				+ File.separator + "wso2carbon.jks");
		System.setProperty("Security.KeyStore.Password", "wso2carbon");
	}

	public static String getDataAgentConfigPath() {
		File filePath = new File("src" + File.separator + "test"
				+ File.separator + "resources");
		if (!filePath.exists()) {
			filePath = new File("components" + File.separator + "data-bridge"
					+ File.separator + "org.wso2.carbon.databridge.agent"
					+ File.separator + "src" + File.separator + "test"
					+ File.separator + "resources");
		}
		if (!filePath.exists()) {
			filePath = new File("resources");
		}
		if (!filePath.exists()) {
			filePath = new File("test" + File.separator + "resources");
		}
		return filePath.getAbsolutePath() + File.separator
				+ "data-agent-config.xml";
	}

	public static String getDataBridgeConfigPath() {
		File filePath = new File("src" + File.separator + "test"
				+ File.separator + "resources");
		if (!filePath.exists()) {
			filePath = new File("components" + File.separator + "data-bridge"
					+ File.separator + "org.wso2.carbon.databridge.agent"
					+ File.separator + "src" + File.separator + "test"
					+ File.separator + "resources");
		}
		if (!filePath.exists()) {
			filePath = new File("resources");
		}
		if (!filePath.exists()) {
			filePath = new File("test" + File.separator + "resources");
		}
		return filePath.getAbsolutePath() + File.separator
				+ "data-bridge-config.xml";
	}
}
