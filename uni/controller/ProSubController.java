package kosta.uni.controller;

import java.util.List;

import kosta.uni.exception.NotFoundException;
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
	public void showMySubject(int id) {
		try {
			List<ProfessorSubject> subjects = service.selectMySubject(id);
			SuccessView.printList(subjects);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(id+"에 해당하는 과목이 없음 : "+e.getMessage());
		}
	}//메소드끝
	
	/**
	 * 교수의 시간표 출력
	 * @param id
	 */
	public void showSchedule(int id){
		try {
			List<ProfessorSubject> subjects = service.selectMySubject(id);
			SuccessView.printSchedule(subjects);
		} catch (NotFoundException e) {
			e.printStackTrace();
			FailView.errorMessage("시간표를 찾을 수 없음"+e.getMessage());
		}
	}

	
	/**
	 * 교수 id를 통해 교수가 담당하는 과목인지를 확인
	 * @param id
	 * @param subject_code
	 */
	public void isMySubject(int id, String code){
		ProfessorSubject subject;
		try {
			subject = service.isMySubject(id, code);
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.setAttribute("subject", subject);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
