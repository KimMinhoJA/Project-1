package kosta.uni.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private String sessionId;
	private Map<String, Object> attributes = new HashMap<>();
	public Session() {}

	public Session(String sessionId, Map<String, Object> attributes) {
		super();
		this.sessionId = sessionId;
		this.attributes = attributes;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
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
	
	public void remove(String name) {
		attributes.remove(name);
	}
	
	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", attributes=" + attributes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
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
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

	
}
