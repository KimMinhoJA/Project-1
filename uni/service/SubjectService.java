package kosta.uni.service;

public class SubjectService {
	private static SubjectService service = new SubjectService();
	private SubjectService() {}
	public static SubjectService getInstance() {
		return service;
	}
}
