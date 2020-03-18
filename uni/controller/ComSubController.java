package kosta.uni.controller;

import kosta.uni.session.Session;

public class ComSubController {
	private static ComSubController controller = new ComSubController();
	private ComSubController() {}
	public static ComSubController getInstance() {
		return controller;
	}
	public void grantGrade(int student_id, Object attribute, String grade, String thisterm) {
		
	}
	public void showMySubjectStudentGrade(Session session, String thisterm) {
		// TODO Auto-generated method stub
		
	}
	public void applySubject(String nextLine, int id) {
		// TODO Auto-generated method stub
		
	}
	public void shoMyApplyInfo(int id, String thisterm) {
		// TODO Auto-generated method stub
		
	}
	public void deleteApply(String nextLine, int id) {
		// TODO Auto-generated method stub
		
	}
	public void showAllGrade(int id) {
		// TODO Auto-generated method stub
		
	}
	public void showGradeByTerm(int id, String thisterm) {
		// TODO Auto-generated method stub
		
	}
	public void showSchedule(int id, String thisterm) {
		// TODO Auto-generated method stub
		
	}
	public void showMySubjectStudent(String thisterm) {
		// TODO Auto-generated method stub
		
	}
}
