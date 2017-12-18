package services;

public class ServiceMessage {

	private Object uriParams;
	private Object serviceName;
	
	public ServiceMessage(Object uriParams,
			Object serviceName) {
		this.uriParams = uriParams;
		this.serviceName = serviceName;
	}
	public Object getServiceName() {
		return serviceName;
	}
	public void setServiceName(Object serviceName) {
		this.serviceName = serviceName;
	}
	public void setMessagesURIParams(Object uriParams){
		this.uriParams = uriParams;
	}
	public Object getMessagesURIParams(){
		return uriParams;
	}
}
