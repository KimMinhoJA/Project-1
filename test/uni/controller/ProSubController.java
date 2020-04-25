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
	 * ����id�� ����ϴ� ������� ���
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
	}//�޼ҵ峡
	
	/**
	 * ������ �ð�ǥ ���
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
	 * ���� id�� ���� ������ ����ϴ� ���������� Ȯ��
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
