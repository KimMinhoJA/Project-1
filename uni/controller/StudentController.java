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
	 * 학생 로그인
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

			SuccessView.successMessage("로그인 성공");

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage("id입력 오류");
		}
	}

	/**
	 * 학생 신상조회
	 * @param id
	 */
	public void showPersonalInfo(String id) {
		try {
			Student st = service.showPersonalInfo(Integer.parseInt(id));
			SuccessView.successMessage("조회하신" + id + "에 대한 정보");
			SuccessView.printPersonalInfo(st);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 학생 비번 변경
	 * @param id
	 * @param pwd
	 */
	public void changePwd(String id, String pwd) {
		try {
			service.changePwd(Integer.parseInt(id), pwd);
			SuccessView.successMessage("비밀번호가 변경되었습니다.");
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 학생 회원가입
	 * @param id
	 * @param pwd
	 */
	public void resister(String id, String pwd) {
		try {
			service.resister(Integer.parseInt(id), pwd);
			SuccessView.successMessage("☆★회원가입을 축하합니다★☆");
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		} catch (ModifyException e) {
			FailView.errorMessage(e.getMessage());
		} catch (Exception e) {
			FailView.errorMessage("id입력 오류");
		}
	}
	
	/**
	 * 학생 로그아웃
	 * @param id
	 */
	public void logout(String id) {
		SessionSet ss = SessionSet.getInstance();
		ss.remove(ss.get(id));
		SuccessView.successMessage("로그아웃 되었습니다.");
	}

	/**
	 * 학생의 누적 학점을 세팅
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
