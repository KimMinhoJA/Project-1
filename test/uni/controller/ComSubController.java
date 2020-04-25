package kosta.uni.controller;

import java.util.List;

import kosta.uni.exception.NotFoundException;
import kosta.uni.service.ComSubService;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;
import kosta.uni.view.FailView;
import kosta.uni.view.SuccessView;
import kosta.uni.vo.CompleteSubject;
import kosta.uni.vo.Grade;
import kosta.uni.vo.Student;
import kosta.uni.vo.Subject;

public class ComSubController {
	private static ComSubController controller = new ComSubController();
	private ComSubService service = ComSubService.getInstance();

	private ComSubController() {
	}

	public static ComSubController getInstance() {
		return controller;
	}

	/**
	 * ������ �л��� ���� �ο�
	 * @param pro_id
	 * @param stu_id
	 * @param code
	 * @param grade
	 * @param term
	 */
	public void grantGrade(String pro_id, String stu_id, String code, String grade, String term) {
		
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(pro_id);
			try {
			
			CompleteSubject cs = new CompleteSubject();
			cs.setGrade(new Grade());
			cs.setStudent(new Student());
			cs.setSubject(new Subject());
			cs.setTerm(term);
			cs.getGrade().setGrade_point(grade);
			cs.getStudent().setStudent_id(Integer.parseInt(stu_id));
			cs.getSubject().setSubject_code(code);

			service.grantGrade(cs);
			SuccessView.successMessage("���� �ο� ����");
			session.setAttribute("check", true);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			session.remove("subject");
		}
	}

	/**
	 * ������û
	 * @param code
	 * @param id
	 * @param term
	 */
	public void applySubject(String code, String id, String term) {
		try {
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.remove("check");
			session.remove("subject");
			CompleteSubject cs = new CompleteSubject();
			cs.setStudent(new Student());
			cs.getStudent().setStudent_id(Integer.parseInt(id));
			cs.setTerm(term);
			cs.setSubject(new Subject());
			cs.getSubject().setSubject_code(code);
			service.applySubject(cs);

			SuccessView.successMessage("������û ����!");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * �������� ��ȸ
	 * @param id
	 * @param term
	 */
	public void showMyApplyInfo(String id, String term) {
		try {
			List<CompleteSubject> list = service.showMyAppInfo(Integer.parseInt(id), term);
			SuccessView.printApplySubject(list);
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);

			session.setAttribute("check", true);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �������� ����
	 * 
	 * @param code
	 * @param id
	 */
	public void deleteApply(String code, String id) {
		try {
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.remove("check");
			service.deleteApply(Integer.parseInt(id), code);
			SuccessView.successMessage("�����Ǿ����ϴ�.");

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ���� ��ȸ
	 * @param id
	 */
	public void showAllGrade(String id) {
		try {
			List<CompleteSubject> list = service.showAllGrade(Integer.parseInt(id));
			SuccessView.PrintGradeMessage(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �ش��б� ���� ��ȸ
	 * @param id
	 * @param thisterm
	 */
	public void showGradeByTerm(String id, String term) {
		try {
			if (term.length() != 6) {
				throw new Exception("�ùٸ��� ���� �б� �Է�");
			} else {
				try {
					Integer.parseInt(term.substring(0, 4));
					if (Integer.parseInt(term.substring(5)) < 0 || Integer.parseInt(term.substring(5)) > 2)
						throw new Exception("�ùٸ��� ���� �б� �Է�");
				} catch (Exception e) {
					throw new Exception("�ùٸ��� ���� �б� �Է�");
				}
			}
			List<CompleteSubject> list = service.showGradeByTerm(Integer.parseInt(id), term);
			SuccessView.PrintGradeTermMessage(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �ð�ǥ ��ȸ
	 * @param id
	 * @param term
	 */
	public void showSchedule(String id, String term) {
		try {
			List<CompleteSubject> list = service.showSchedule(Integer.parseInt(id), term);
			System.out.println(term + " �б��� ������������");
			SuccessView.printStudentSchedule(list);
			
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �ش� �б��� �ش� ���� �л��� ��ȸ
	 * @param code
	 * @param term
	 */
	public void showMySubjectStudent(String id, String code, String term) {
		try {
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.remove("subject");

			List<CompleteSubject> students = service.showMySubjectStudent(code, term);
			SuccessView.printStudentList(students);
		} catch (NotFoundException e) {
			FailView.errorMessage("���Ǹ� �����ϴ� �л� ������ �����ϴ�.");
		}
	}

	/**
	 * �ش� �б��� �ش���� �л��� ���� ��ȸ
	 * @param code
	 * @param term
	 */
	public void showMySubjectStudentGrade(String id, String code, String term) {
		try {
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.remove("subject");
			List<CompleteSubject> students = service.showMySubjectStudentGrade(code, term);
			SuccessView.printMySubjectStudentGrade(students);
		} catch (Exception e) {
			FailView.errorMessage("�ش��б� ���� �ο��� �л��� �����ϴ�.");
		}
	}

	/**
	 * ������û �ߺ� üũ
	 * @param id
	 * @param subject_code
	 */
	public void duplicateCheck(String id, String subject_code, String term) {
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.remove("check");
			
		try {
			service.duplicateCheck(Integer.parseInt(id), subject_code, term);
			session.remove("subject");
			
			FailView.errorMessage("�̹� ������ �����Դϴ�.");
		}catch (Exception e) {
			session.setAttribute("check", true);
		}
	}

	/**
	 * �ð�ǥ �ߺ� üũ
	 * @param id
	 * @param code
	 * @param thisterm
	 */
	public void scheduleCheck(String id, String term) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		Subject subject = (Subject)session.getAttribute("subject");
		session.remove("check");
		
		try {
			service.scheduleCheck(subject, Integer.parseInt(id), term);
			session.remove("subject");
			FailView.errorMessage("�ð�ǥ �ߺ��Դϴ�.");
		}catch (Exception e) {
			session.setAttribute("check", true);
		}
	}

	/**
	 * �ִ� ��û���� ����
	 * @param id
	 * @param term
	 */
	public void limitCreditCheck(String id, String term) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		Subject subject = (Subject)session.getAttribute("subject");
		session.remove("check");
		try {
			service.limitCreditCheck(Integer.parseInt(id), term, subject.getCredit());
			session.setAttribute("check", true);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			session.remove("subject");
		}
	}
}
