package kosta.uni.vo;

public class CompleteSubject {
	private Student student;
	private Subject subject;
	private Grade grade;
	private String term;
	public CompleteSubject() {
		super();
	}
	public CompleteSubject(Student student, Subject subject, Grade grade, String term) {
		super();
		this.student = student;
		this.subject = subject;
		this.grade = grade;
		this.term = term;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	@Override
	public String toString() {
		return "CompleteSubject [student=" + student + ", subject=" + subject + ", grade=" + grade + ", term=" + term
				+ "]";
	}
	
	
}
