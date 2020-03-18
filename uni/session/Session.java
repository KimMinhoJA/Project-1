package kosta.uni.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private int sessionId;
	private Map<String, Object> attributes = new HashMap<>();
	public Session() {}

	public Session(int sessionId, Map<String, Object> attributes) {
		super();
		this.sessionId = sessionId;
		this.attributes = attributes;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}
	
	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", attributes=" + attributes + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sessionId;
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
		Session other = (Session) obj;
		if (sessionId != other.sessionId)
			return false;
		return true;
	}

	
}
