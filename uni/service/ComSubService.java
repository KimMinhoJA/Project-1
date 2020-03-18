package kosta.uni.service;

public class ComSubService {
	private static ComSubService service = new ComSubService();
	private ComSubService() {}
	public static ComSubService getInstance() {
		return service;
	}
}
