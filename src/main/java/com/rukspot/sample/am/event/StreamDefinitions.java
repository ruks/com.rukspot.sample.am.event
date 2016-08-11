package com.rukspot.sample.am.event;

public class StreamDefinitions {
	public static String getStreamDefinitionResponse() {

        return "{" +
               "  'name':'" +
                "org.wso2.apimgt.statistics.response" + "'," +
               "  'version':'" +
               "1.1.0" + "'," +
               "  'nickName': 'API Manager Response Data'," +
               "  'description': 'Response Data'," +
               "  'metaData':[" +
               "          {'name':'clientType','type':'STRING'}" +
               "  ]," +
               "  'payloadData':[" +
               "          {'name':'consumerKey','type':'STRING'}," +
               "          {'name':'context','type':'STRING'}," +
               "          {'name':'api_version','type':'STRING'}," +
               "          {'name':'api','type':'STRING'}," +
               "          {'name':'resourcePath','type':'STRING'}," +
               "          {'name':'resourceTemplate','type':'STRING'}," +
               "          {'name':'method','type':'STRING'}," +
               "          {'name':'version','type':'STRING'}," +
               "          {'name':'response','type':'INT'}," +
               "          {'name':'responseTime','type':'LONG'}," +
               "          {'name':'serviceTime','type':'LONG'}," +
               "          {'name':'backendTime','type':'LONG'}," +
               "          {'name':'username','type':'STRING'}," +
               "          {'name':'eventTime','type':'LONG'}," +
               "          {'name':'tenantDomain','type':'STRING'}," +
               "          {'name':'hostName','type':'STRING'}," +
               "          {'name':'apiPublisher','type':'STRING'}," +
               "          {'name':'applicationName','type':'STRING'}," +
               "          {'name':'applicationId','type':'STRING'}," +
               "          {'name':'cacheHit','type':'BOOL'}," +
               "          {'name':'responseSize','type':'LONG'}," +
               "          {'name':'protocol','type':'STRING'}," +
               "          {'name':'responseCode','type':'INT'}" +
               "  ]" +

               "}";
    }
	
	public static String getStreamDefinitionRequest() {

        /*
          Please use this comment to track the steam changes that were done.
          Current Version -
            1.1.0
          Changes -
            1.1.0 -  Added the resourceTemplate parameter.
         */
        return "{" +
                "  'name':'" +
                "org.wso2.apimgt.statistics.request" + "'," +
                "  'version':'" +
                "1.1.0" + "'," +
                "  'nickName': 'API Manager Request Data'," +
                "  'description': 'Request Data'," +
                "  'metaData':[" +
                "          {'name':'clientType','type':'STRING'}" +
                "  ]," +
                "  'payloadData':[" +
                "          {'name':'consumerKey','type':'STRING'}," +
                "          {'name':'context','type':'STRING'}," +
                "          {'name':'api_version','type':'STRING'}," +
                "          {'name':'api','type':'STRING'}," +
                "          {'name':'resourcePath','type':'STRING'}," +
                "          {'name':'resourceTemplate','type':'STRING'}," +
                "          {'name':'method','type':'STRING'}," +
                "          {'name':'version','type':'STRING'}," +
                "          {'name':'request','type':'INT'}," +
                "          {'name':'requestTime','type':'LONG'}," +
                "          {'name':'userId','type':'STRING'}," +
                "          {'name':'tenantDomain','type':'STRING'}," +
                "          {'name':'hostName','type':'STRING'}," +
                "          {'name':'apiPublisher','type':'STRING'}," +
                "          {'name':'applicationName','type':'STRING'}," +
                "          {'name':'applicationId','type':'STRING'}," +
                "          {'name':'userAgent','type':'STRING'}," +
                "          {'name':'tier','type':'STRING'}," +
                "          {'name':'throttledOut','type':'BOOL'}," +
                "          {'name':'clientIp','type':'STRING'}" +
                "  ]" +
                "}";
    }

	public static String getStreamDefinitionExecutionTtime() {

        return "{\n" +
                "  \"name\": \"" + "org.wso2.apimgt.statistics.execution.time" + "\",\n" +
                "  \"version\": \"" + "1.0.0" + "\",\n" +
                "  \"nickName\": \"Execution Time Data\",\n" +
                "  \"description\": \"This stream will persist the data which send by the mediation executions\",\n" +
                "  \"metaData\": [\n" +
                "    {\n" +
                "      \"name\": \"clientType\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"payloadData\": [\n" +
                "    {\n" +
                "      \"name\": \"api\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"api_version\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"tenantDomain\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"apiPublisher\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"mediationName\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"executionTime\",\n" +
                "      \"type\": \"LONG\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"context\",\n" +
                "      \"type\": \"STRING\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"eventTime\",\n" +
                "      \"type\": \"LONG\"\n" +
                "    }\n" +

                "  ]\n" +
                "}";
    }
    public static String getStreamDefinitionDestination() {

        /*
          Please use this comment to track the steam changes that were done.
          Current Version -
            1.1.0
          Changes -
            1.1.0 -  Added the resourceTemplate parameter.
         */
        return "{" +
                "  'name':'" +
                "org_wso2_apimgt_statistics_destination" + "'," +
                "  'version':'" +
                "1.0.0" + "'," +
                "  'nickName': 'API Manager Request Data'," +
                "  'description': 'Request Data'," +
                "  'metaData':[" +
//                "          {'name':'clientType','type':'STRING'}" +
                "  ]," +
                "  'payloadData':[" +
                "          {'name':'api','type':'STRING'}," +
                "          {'name':'version','type':'STRING'}," +
                "          {'name':'request','type':'INTEGER'}," +
                "          {'name':'apiPublisher','type':'STRING'}," +
                "          {'name':'context','type':'STRING'}," +
                "          {'name':'apiPublisher','type':'STRING'}," +
                "          {'name':'requestTime','type':'LONG'}," +
                "          {'name':'hostName','type':'STRING'}," +
                "  ]" +
                "}";
    }
}
