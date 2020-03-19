package kosta.uni.controller;

import java.util.List;

import kosta.uni.service.ProSubService;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;
import kosta.uni.view.FailView;
import kosta.uni.view.SuccessView;
import kosta.uni.vo.ProfessorSubject;

public class ProSubController {
	private static ProSubController controller = new ProSubController();
	private ProSubController() {}
	
	public static ProSubController getInstance() {
		return controller;
	}
	
ProSubService service = ProSubService.getInstance();
	
	/**
	 * 교수id에 담당하는 과목들을 출력
	 * @param id
	 */
	public void showMySubject(String id) {
		try {
			List<ProfessorSubject> subjects = service.selectMySubject(Integer.parseInt(id));
			SuccessView.printList(subjects);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}//메소드끝
	
	/**
	 * 교수의 시간표 출력
	 * @param id
	 */
	public void showSchedule(String id){
		try {
			List<ProfessorSubject> subjects = service.selectMySubject(Integer.parseInt(id));
			SuccessView.printSchedule(subjects);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	
	/**
	 * 교수 id를 통해 교수가 담당하는 과목인지를 확인
	 * @param id
	 * @param subject_code
	 */
	public void isMySubject(String id, String code){
		ProfessorSubject subject;
		try {
			subject = service.isMySubject(Integer.parseInt(id), code);
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.setAttribute("subject", subject.getSubject());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
