package kosta.uni.session;

import java.util.HashSet;
import java.util.Set;

public class SessionSet {
	private static SessionSet ss = new SessionSet();
	Set<Session> set = null;
	private SessionSet() {
		set = new HashSet<>();
	}
	
	public static SessionSet getInstance() {
		return ss;
	}
	
	public void add(Session session) {
		set.add(session);
	}
	
	public void remove(Session session) {
		set.remove(session);
	}
	
	/**
	 * sessionId�� ��ġ�ϴ� session��ȯ
	 * @param sessionId
	 * @return
	 */
	public Session get(int sessionId) {
		for(Session session : set) {
			if(session.getSessionId() ==sessionId) {
				return session;
			}
		}
		return null;
	}
	
	/**
	 * ���� ���ǵ� ��ȯ
	 * @return
	 */
	public Set<Session> getSet() {
		return set;
	}
}
