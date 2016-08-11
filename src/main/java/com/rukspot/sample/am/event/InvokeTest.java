package com.rukspot.sample.am.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.wso2.carbon.databridge.commons.Event;

import com.rukspot.httpclient.HTTPClientUtils;
import com.rukspot.httpclient.Response;

public class InvokeTest {
	public static void main(String[] args) throws Exception {
		ThriftTestServer thriftTestServer = new ThriftTestServer();		
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionRequest(), -1234);
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionResponse(), -1234);
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionExecutionTtime(), -1234);
		thriftTestServer.start(7612);
		List<Event> requestTable;

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("Authorization",
				"Bearer 5387c3df687f20aacbd6ddd974319532"));
		list.add(new BasicNameValuePair("Authorization",
				"Bearer 5387c3df687f20aacbd6ddd974319532"));
		Response res = HTTPClientUtils.doGet(
				"https://172.18.0.1:8243/api307/1.0.0", list);
		System.out.println(res.getStatusCode() + " " + res.getBody());

		System.out.println("wating");
		while (true) {
			Thread.sleep(1000);
			requestTable = thriftTestServer.getDataTables().get(
					"org.wso2.apimgt.statistics.request:1.1.0");
			if (requestTable == null) {
				continue;
			} else {
				if (requestTable.isEmpty()) {
					continue;
				} else {
					break;
				}
			}
		}
		System.out.println(requestTable.get(0));
		
		while (true) {
			Thread.sleep(1000);
			requestTable = thriftTestServer.getDataTables().get(
					"org.wso2.apimgt.statistics.response:1.1.0");
			if (requestTable == null) {
				continue;
			} else {
				if (requestTable.isEmpty()) {
					continue;
				} else {
					break;
				}
			}
		}
		System.out.println(requestTable.get(0));
		thriftTestServer.stop();
		System.exit(0);
	}
}
