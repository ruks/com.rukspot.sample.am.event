package com.rukspot.sample.am.event;

import java.util.List;
import java.util.Map;

import org.wso2.carbon.apimgt.usage.publisher.DataPublisherUtil;
import org.wso2.carbon.apimgt.usage.publisher.dto.DataBridgeRequestPublisherDTO;
import org.wso2.carbon.apimgt.usage.publisher.dto.RequestPublisherDTO;
import org.wso2.carbon.databridge.agent.DataPublisher;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.core.exception.DataBridgeException;
import org.wso2.carbon.databridge.core.exception.StreamDefinitionStoreException;

public class Publisher {
	static {
		System.setProperty("javax.net.ssl.trustStore",
				"/home/rukshan/wso2-jks/wso2carbon.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
	}

	public static void main(String[] args) throws DataBridgeException,
			InterruptedException, StreamDefinitionStoreException,
			MalformedStreamDefinitionException {

		System.setProperty("carbon.home",
				"/home/rukshan/apim/2.0.x/wso2analytics-apim-1.0.0-SNAPSHOT");

		String STREAM_NAME = "org.wso2.esb.MediatorStatistics";
		String VERSION = "1.0.0";

		String STREAM_DEFN = "{" + "  'name':'" + STREAM_NAME + "',"
				+ "  'version':'" + VERSION + "',"
				+ "  'nickName': 'Stock Quote Information',"
				+ "  'description': 'Some Desc'," + "  'tags':['foo', 'bar'],"
				+ "  'metaData':["
				+ "          {'name':'ipAdd','type':'STRING'}" + "  ],"
				+ "  'payloadData':["
				+ "          {'name':'symbol','type':'STRING'},"
				+ "          {'name':'price','type':'DOUBLE'},"
				+ "          {'name':'volume','type':'INT'},"
				+ "          {'name':'max','type':'DOUBLE'},"
				+ "          {'name':'min','type':'Double'}" + "  ]" + "}";

		String testStream = "{\n" + "  \"name\": \"test\",\n"
				+ "  \"version\": \"1.0.0\",\n" + "  \"nickName\": \"\",\n"
				+ "  \"description\": \"\",\n" + "  \"payloadData\": [\n"
				+ "    {\n" + "      \"name\": \"num\",\n"
				+ "      \"type\": \"INT\"\n" + "    }\n" + "  ]\n" + "} ";

		// ThriftTestServer thriftTestServer = new ThriftTestServer();
		// thriftTestServer.start(7613);
		// thriftTestServer.addStreamDefinition(STREAM_DEFN, -1234);
		// thriftTestServer.addStreamDefinition(testStream, -1234);
		// thriftTestServer.addStreamDefinition(DataBridgeRequestPublisherDTO.getStreamDefinition(),
		// -1234);

		try {
			DataPublisher p = new DataPublisher("tcp://localhost:7612",
					"admin", "admin");

			int i = 0;
			int s = 0;
			for (int j = s; j < s + 5000; j++) {
				i = j;
				String consumerKey = "Md7pgErNWgtR6p6Cxlv016kzueI" + i;
				String username = "admin";
				String applicationName = "app" + i;
				String applicationId = i + "";
				String applicationOwner = "admin";
				String tier = "Unlimited";
				String hostName = "Host";
				String userAgent = "Firefox";
				String apiPublisher = "admin";
				String tenantDomain = "carbon.super";
				String api = "api" + i;
				String version = "1.0.0";
				String resource = "/" + applicationName;
				String resourceTemplate = "/*";
				String method = "GET";
				boolean throttleOutHappened = false;
				long currentTime = System.currentTimeMillis();
				String clientIp = "127.0.0.1";
				String context = "/" + api + "/" + version;
				String apiVersion = apiPublisher + "--" + api + ":v" + version;

				RequestPublisherDTO requestPublisherDTO = new RequestPublisherDTO();
				requestPublisherDTO.setConsumerKey(consumerKey);
				requestPublisherDTO.setContext(context);
				requestPublisherDTO.setApiVersion(apiVersion);
				requestPublisherDTO.setApi(api);
				requestPublisherDTO.setVersion(version);
				requestPublisherDTO.setResourcePath(resource);
				requestPublisherDTO.setResourceTemplate(resourceTemplate);
				requestPublisherDTO.setMethod(method);
				requestPublisherDTO.setRequestTime(currentTime);
				requestPublisherDTO.setUsername(username);
				requestPublisherDTO.setTenantDomain(tenantDomain);
				requestPublisherDTO.setHostName(hostName);
				requestPublisherDTO.setApiPublisher(apiPublisher);
				requestPublisherDTO.setApplicationName(applicationName);
				requestPublisherDTO.setApplicationId(applicationId);
				requestPublisherDTO.setUserAgent(userAgent);
				requestPublisherDTO.setTier(tier);
				requestPublisherDTO
						.setContinuedOnThrottleOut(throttleOutHappened);
				requestPublisherDTO.setClientIp(clientIp);
				requestPublisherDTO.setApplicationOwner(applicationOwner);

				DataBridgeRequestPublisherDTO dt = new DataBridgeRequestPublisherDTO(
						requestPublisherDTO);
//				Object[] ar = (Object[]) dt.createPayload();
				Object[] ar= new Object[]{"key","nbame","version"};
				
				String id = "org.wso2.apimgt.statistics.request:1.1.0";
				p.publish(id, new Object[] { "external" }, null, ar);
				System.out.println("sent "+i);
			}
			/*
			 * while(thriftTestServer.getNumberOfEventsReceived()<1);
			 * System.out.println(thriftTestServer.getNumberOfEventsReceived());
			 * 
			 * Map<String, List<Event>>
			 * dataTables=thriftTestServer.getDataTables();
			 * System.out.println("t :"+dataTables.size());
			 * System.out.println("t :"
			 * +dataTables.get(id).get(0).getTimeStamp());
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
