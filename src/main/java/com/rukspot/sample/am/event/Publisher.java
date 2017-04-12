package com.rukspot.sample.am.event;

import org.wso2.carbon.apimgt.usage.publisher.dto.DataBridgeRequestPublisherDTO;
import org.wso2.carbon.apimgt.usage.publisher.dto.RequestPublisherDTO;
import org.wso2.carbon.databridge.agent.DataPublisher;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.core.exception.DataBridgeException;
import org.wso2.carbon.databridge.core.exception.StreamDefinitionStoreException;

public class Publisher {
	static {
		System.setProperty("javax.net.ssl.trustStore","/mnt/172.30.9.75/wum/wso2am-2.0.0/repository/resources/security/wso2carbon.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
	}

	public static void main(String[] args) throws DataBridgeException,
			InterruptedException, StreamDefinitionStoreException,
			MalformedStreamDefinitionException {

		System.setProperty("carbon.home","/mnt/172.30.9.75/wum/wso2am-2.0.0");

		try {
//			DataPublisher p = new DataPublisher("tcp://localhost:7612","admin", "admin");
			String url ="tcp://172.30.9.144:7611";
			DataPublisher p = new DataPublisher(url, "admin","bA3RxeyU6gvt");
			
			int i = 0;
			int s = 0;
			for (int j = s; j < s + 1; j++) {
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
				Object[] ar = (Object[]) dt.createPayload();
				
				String id = "org.wso2.apimgt.statistics.request:1.1.0";
				System.out.println("sending...");
				p.publish(id, new Object[] { "external" }, null, ar);
				System.out.println("sent "+i);
				
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
