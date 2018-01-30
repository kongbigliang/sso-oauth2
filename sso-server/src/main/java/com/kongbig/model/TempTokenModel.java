package com.kongbig.model;

public class TempTokenModel {

	private String tempToken;
	private String clientID;
	private String redirectURIIP;
	private String returnURI;

	public TempTokenModel() {
		
	}

	public TempTokenModel(String tempToken, String clientID, String redirectURIIP, String returnURI) {
		this.tempToken = tempToken;
		this.clientID = clientID;
		this.redirectURIIP = redirectURIIP;
		this.returnURI = returnURI;
	}

	public String getTempToken() {
		return tempToken;
	}

	public void setTempToken(String tempToken) {
		this.tempToken = tempToken;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getRedirectURIIP() {
		return redirectURIIP;
	}

	public void setRedirectURIIP(String redirectURIIP) {
		this.redirectURIIP = redirectURIIP;
	}

	public String getReturnURI() {
		return returnURI;
	}

	public void setReturnURI(String returnURI) {
		this.returnURI = returnURI;
	}

	@Override
	public String toString() {
		return "TempTokenModel [tempToken=" + tempToken + ", clientID=" + clientID + ", redirectURIIP=" + redirectURIIP
				+ ", returnURI=" + returnURI + "]";
	}

}
