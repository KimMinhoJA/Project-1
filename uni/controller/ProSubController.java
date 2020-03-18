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
	 * ����id�� ����ϴ� ������� ���
	 * @param id
	 */
	public void showMySubject(int id) {
		try {
			List<ProfessorSubject> subjects = service.selectMySubject(id);
			SuccessView.printList(subjects);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(id+"�� �ش��ϴ� ������ ���� : "+e.getMessage());
		}
	}//�޼ҵ峡
	
	/**
	 * ������ �ð�ǥ ���
	 * @param id
	 */
	public void showSchedule(int id){
		try {
			List<ProfessorSubject> subjects = service.selectMySubject(id);
			SuccessView.printSchedule(subjects);
		} catch (NotFoundException e) {
			e.printStackTrace();
			FailView.errorMessage("�ð�ǥ�� ã�� �� ����"+e.getMessage());
		}
	}

	
	/**
	 * ���� id�� ���� ������ ����ϴ� ���������� Ȯ��
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
