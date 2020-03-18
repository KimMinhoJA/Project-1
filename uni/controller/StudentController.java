package kosta.uni.controller;

import kosta.uni.exception.NotFoundException;
import kosta.uni.service.StudentService;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;
import kosta.uni.view.FailView;
import kosta.uni.view.SuccessView;
import kosta.uni.vo.Student;

public class StudentController {
	private static StudentController controller = new StudentController();
	private StudentController() {}
	public static StudentController getInstance() {
		return controller;
	}
	
	StudentService service = StudentService.getInstance();

	public void login(int id, String pwd) {
		
		try {
		Student st = service.login(id, pwd);
		SessionSet ss = SessionSet.getInstance();
		Session session = new Session();
		session.setSessionId(id);
		session.setAttribute("major", st.getMajor());
		ss.add(session);
		
		SuccessView.successMessage("�α��α�!");
		
		}catch(NotFoundException e) {
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	public void showPersonalInfo(int id) {
		try {
			Student st = service.showPersonalInfo(id);
			SuccessView.successMessage("��ȸ�Ͻ�" +id+"�� ���� �������~");
			SuccessView.successMessage(st.toString());//�ٽ�
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void changePwd(int id, String pwd) {
		try {
			service.changePwd(id, pwd);
			SuccessView.successMessage("��� ����̴ٱ�~");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void resister(int id, String pwd) {
		try {
			service.resister(id, pwd);
			SuccessView.successMessage("ȸ�� ���Ե̴ٱ�~");
		} catch (Exception e) {		
			FailView.errorMessage(e.getMessage());
		}
	}
	public void logout(int id) {
		SessionSet ss = SessionSet.getInstance();
		ss.remove(ss.get(id));
		SuccessView.successMessage("�α׾ƿ� �Ǿ����ϴ�.");
		
	}
	
}
