package com.workbench.client.performance;

public enum EventAttribute {
		
		CONNECT_END("connectEnd"),
		CONNECT_START("connectStart"),
		DOM_COMPLETE("domComplete"),
		DOMAIN_LOOKUP_START("domainLookupStart"),
		DOM_LOADING("domLoading"),
		
		LOAD_EVENT_END("loadEventEnd"), 
		LOAD_EVENT_START("loadEventStart"),
		NAVIGATION_START("navigationStart"),
		REQUEST_START("requestStart"),
		RESPONSE_END("responseStart"), 
		RESPONSE_START("responseStart"),
		
		
		PAGE_LOAD_TIME("PageLoadTime"),
		PAGE_RENDER_TIME("PageRenderTime"),
		NETWORK_CONNECTION_TIME("ConnectTime"),
		SERVER_RESPONSE_TIME("ServerResponseTime");
		
	
	EventAttribute(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        return this.attributeValue;
    }
		
    private final String attributeValue;
	

	
}
