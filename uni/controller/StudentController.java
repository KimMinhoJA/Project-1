package kosta.uni.controller;

import kosta.uni.exception.ModifyException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.service.StudentService;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;
import kosta.uni.view.FailView;
import kosta.uni.view.SuccessView;
import kosta.uni.vo.Student;

public class StudentController {
	private static StudentController controller = new StudentController();

	private StudentController() {
		
	}

	public static StudentController getInstance() {
		return controller;
	}

	StudentService service = StudentService.getInstance();

	/**
	 * �л� �α���
	 * @param id
	 * @param pwd
	 */
	public void login(String id, String pwd) {
		try {
			Student st = service.login(Integer.parseInt(id), pwd);
			SessionSet ss = SessionSet.getInstance();
			Session session = new Session();
			session.setSessionId(id);
			session.setAttribute("major", st.getMajor());
			session.setAttribute("level", st.getClass_level());
			ss.add(session);

			SuccessView.successMessage("�α��� ����");

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage("id�Է� ����");
		}
	}

	/**
	 * �л� �Ż���ȸ
	 * @param id
	 */
	public void showPersonalInfo(String id) {
		try {
			Student st = service.showPersonalInfo(Integer.parseInt(id));
			SuccessView.successMessage("��ȸ�Ͻ�" + id + "�� ���� ����");
			SuccessView.printPersonalInfo(st);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �л� ��� ����
	 * @param id
	 * @param pwd
	 */
	public void changePwd(String id, String pwd) {
		try {
			service.changePwd(Integer.parseInt(id), pwd);
			SuccessView.successMessage("��й�ȣ�� ����Ǿ����ϴ�.");
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �л� ȸ������
	 * @param id
	 * @param pwd
	 */
	public void resister(String id, String pwd) {
		try {
			service.resister(Integer.parseInt(id), pwd);
			SuccessView.successMessage("�١�ȸ�������� �����մϴ١ڡ�");
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (ModifyException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage("id�Է� ����");
		}
	}
	
	/**
	 * �л� �α׾ƿ�
	 * @param id
	 */
	public void logout(String id) {
		SessionSet ss = SessionSet.getInstance();
		ss.remove(ss.get(id));
		SuccessView.successMessage("�α׾ƿ� �Ǿ����ϴ�.");
	}

	/**
	 * �л��� ���� ������ ����
	 * @param id
	 * @param grade
	 * @param credit
	 */
	public void setGrade(String pro_id, String stud_id, String grade, int credit) {
		try {
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(pro_id);
			session.remove("subject");
			session.remove("check");
			if("F0".equals(grade) || grade == null) {
				return;
			}
			Student student = service.showPersonalInfo(Integer.parseInt(stud_id));
			student.setAccumulated_grade(student.getAccumulated_grade() + credit);
			service.setGrade(student);
			
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
