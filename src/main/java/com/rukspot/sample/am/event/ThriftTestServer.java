package com.rukspot.sample.am.event;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.wso2.carbon.databridge.commons.Credentials;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.commons.utils.EventDefinitionConverterUtils;
import org.wso2.carbon.databridge.core.AgentCallback;
import org.wso2.carbon.databridge.core.DataBridge;
import org.wso2.carbon.databridge.core.Utils.AgentSession;
import org.wso2.carbon.databridge.core.definitionstore.InMemoryStreamDefinitionStore;
import org.wso2.carbon.databridge.core.exception.DataBridgeException;
import org.wso2.carbon.databridge.core.exception.StreamDefinitionStoreException;
import org.wso2.carbon.databridge.core.internal.authentication.AuthenticationHandler;
import org.wso2.carbon.databridge.receiver.thrift.ThriftDataReceiver;
import org.wso2.carbon.user.api.UserStoreException;

public class ThriftTestServer {
	static {
		System.setProperty("javax.net.ssl.trustStore",
				"/home/rukshan/wso2-jks/wso2carbon.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
	}

	Logger log = Logger.getLogger(ThriftTestServer.class);
	ThriftDataReceiver thriftDataReceiver;
	InMemoryStreamDefinitionStore streamDefinitionStore;
	AtomicInteger numberOfEventsReceived;
	RestarterThread restarterThread;
	Map<String, List<Event>> dataTables = new HashMap<String, List<Event>>();

	public Map<String, List<Event>> getDataTables() {
		return dataTables;
	}

	public static void main(String[] args) throws Exception {
		ThriftTestServer thriftTestServer = new ThriftTestServer();		
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionRequest(), -1234);
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionResponse(), -1234);
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionExecutionTtime(), -1234);
		thriftTestServer.addStreamDefinition(
				StreamDefinitions.getStreamDefinitionDestination(), -1234);
		thriftTestServer.start(7612);
//		List<Event> requestTable;
		
//		List<NameValuePair> list = new ArrayList<NameValuePair>();
//		list.add(new BasicNameValuePair("Authorization","Bearer 5387c3df687f20aacbd6ddd974319532"));
//		list.add(new BasicNameValuePair("Authorization","Bearer 5387c3df687f20aacbd6ddd974319532"));		
//		Response res=HTTPClientUtils.doGet("https://172.18.0.1:8243/api307/1.0.0", list);
//		System.out.println(res.getStatusCode()+" "+res.getBody());
//		
//		System.out.println("wating");
//		while (true) {
//			Thread.sleep(1000);
//			requestTable = thriftTestServer.getDataTables().get(
//					"org.wso2.apimgt.statistics.request:1.0.0");
//			if (requestTable == null) {
//				continue;
//			} else {
//				if (requestTable.isEmpty()) {
//					continue;
//				} else {
//					break;
//				}
//			}
//		}
//		System.out.println(requestTable.get(0));
	}
	
	public void resetTables(){
		this.dataTables.clear();
	}

	public void startTestServer(int port) throws DataBridgeException,
			InterruptedException {
		ThriftTestServer thriftTestServer = new ThriftTestServer();
		thriftTestServer.start(port);
		Thread.sleep(100000000);
		thriftTestServer.stop();
	}

	public void addStreamDefinition(StreamDefinition streamDefinition,
			int tenantId) throws StreamDefinitionStoreException {
		streamDefinitionStore.saveStreamDefinitionToStore(streamDefinition,
				tenantId);
	}

	public void addStreamDefinition(String streamDefinitionStr, int tenantId)
			throws StreamDefinitionStoreException,
			MalformedStreamDefinitionException {
		StreamDefinition streamDefinition = EventDefinitionConverterUtils
				.convertFromJson(streamDefinitionStr);
		getStreamDefinitionStore().saveStreamDefinitionToStore(
				streamDefinition, tenantId);
	}

	private InMemoryStreamDefinitionStore getStreamDefinitionStore() {
		if (streamDefinitionStore == null) {
			streamDefinitionStore = new InMemoryStreamDefinitionStore();
		}
		return streamDefinitionStore;
	}

	public void start(int receiverPort) throws DataBridgeException {
		DataPublisherTestUtil.setKeyStoreParams();
		streamDefinitionStore = getStreamDefinitionStore();
		numberOfEventsReceived = new AtomicInteger(0);
		DataBridge databridge = new DataBridge(new AuthenticationHandler() {
			// @Override
			public boolean authenticate(String userName, String password) {
				return true;// allays authenticate to true

			}

			// @Override
			public String getTenantDomain(String userName) {
				return "admin";
			}

			// @Override
			public int getTenantId(String tenantDomain)
					throws UserStoreException {
				return -1234;
			}

			// @Override
			public void initContext(AgentSession agentSession) {
				// To change body of implemented methods use File | Settings |
				// File Templates.
			}

			// @Override
			public void destroyContext(AgentSession agentSession) {

			}
		}, streamDefinitionStore,
				DataPublisherTestUtil.getDataBridgeConfigPath());

		thriftDataReceiver = new ThriftDataReceiver(receiverPort, databridge);

		databridge.subscribe(new AgentCallback() {
			int totalSize = 0;

			public void definedStream(StreamDefinition streamDefinition,
					int tenantId) {
				 log.info("StreamDefinition " + streamDefinition);
			}

			// @Override
			public void removeStream(StreamDefinition streamDefinition,
					int tenantId) {
				log.info("StreamDefinition remove " + streamDefinition);
			}

			// @Override
			public void receive(List<Event> eventList, Credentials credentials) {
				for (Event event : eventList) {
					System.out.println(event.getStreamId() + " "
							+ eventList.size());
					if (!dataTables.containsKey(event.getStreamId())) {
						dataTables.put(event.getStreamId(),
								new ArrayList<Event>());
					}
					dataTables.get(event.getStreamId()).add(event);
				}
				System.out.println("added to tables " + dataTables.size());

				numberOfEventsReceived.addAndGet(eventList.size());
				log.info("Received events : " + numberOfEventsReceived);
			}

		});

		String address = "localhost";
		log.info("Test Server starting on " + address);
		thriftDataReceiver.start(address);
		log.info("Test Server Started");
	}

	public int getNumberOfEventsReceived() {
		if (numberOfEventsReceived != null)
			return numberOfEventsReceived.get();
		else
			return 0;
	}

	public void resetReceivedEvents() {
		numberOfEventsReceived.set(0);
	}

	public void stop() {
		thriftDataReceiver.stop();
		log.info("Test Server Stopped");
	}

	public void stopAndStartDuration(int port, long stopAfterTimeMilliSeconds,
			long startAfterTimeMS) throws SocketException, DataBridgeException {
		restarterThread = new RestarterThread(port, stopAfterTimeMilliSeconds,
				startAfterTimeMS);
		Thread thread = new Thread(restarterThread);
		thread.start();
	}

	public int getEventsReceivedBeforeLastRestart() {
		return restarterThread.eventReceived;
	}

	class RestarterThread implements Runnable {
		int eventReceived;
		int port;

		long stopAfterTimeMilliSeconds;
		long startAfterTimeMS;

		RestarterThread(int port, long stopAfterTime, long startAfterTime) {
			this.port = port;
			stopAfterTimeMilliSeconds = stopAfterTime;
			startAfterTimeMS = startAfterTime;
		}

		// @Override
		public void run() {
			try {
				Thread.sleep(stopAfterTimeMilliSeconds);
			} catch (InterruptedException e) {
			}
			if (thriftDataReceiver != null)
				thriftDataReceiver.stop();

			eventReceived = getNumberOfEventsReceived();

			log.info("Number of events received in server shutdown :"
					+ eventReceived);
			try {
				Thread.sleep(startAfterTimeMS);
			} catch (InterruptedException e) {
			}

			try {
				if (thriftDataReceiver != null) {
					thriftDataReceiver.start(DataPublisherTestUtil.LOCAL_HOST);
				} else {
					start(port);
				}
			} catch (DataBridgeException e) {
				log.error(e);
			}

		}
	}
}
