package br.ufpe.ntvru.portaria.model;

public class WebCamInfo {

	
	private String webCamName;
	private int webCamIndex;
	public String getWebCamName() {
		return webCamName;
	}
	public void setWebCamName(String webCamName) {
		this.webCamName = webCamName;
	}
	public int getWebCamIndex() {
		return webCamIndex;
	}
	public void setWebCamIndex(int webCamIndex) {
		this.webCamIndex = webCamIndex;
	}
	@Override
	public String toString() {
		return "WebCamInfo [webCamName=" + webCamName + ", webCamIndex=" + webCamIndex + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + webCamIndex;
		result = prime * result + ((webCamName == null) ? 0 : webCamName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebCamInfo other = (WebCamInfo) obj;
		if (webCamIndex != other.webCamIndex)
			return false;
		if (webCamName == null) {
			if (other.webCamName != null)
				return false;
		} else if (!webCamName.equals(other.webCamName))
			return false;
		return true;
	}
	
	
}
