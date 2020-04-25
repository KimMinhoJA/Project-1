package kosta.uni.vo;

public class ProfessorSubject {
	private Professor professor;
	private Subject subject;
	
	public ProfessorSubject() {
		super();
	}

	public ProfessorSubject(Professor professor, Subject subject) {
		super();
		this.professor = professor;
		this.subject = subject;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "ProfessorSubject [professor=" + professor + ", subject=" + subject + "]";
	}
	
}
