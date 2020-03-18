package kosta.uni.vo;

public class Professor{
	private int professor_id;
	private String name;
	private String pwd;
	private Major major;
	public Professor() {
		super();
	}
	public Professor(int professor_id, String name, String pwd, Major major) {
		super();
		this.professor_id = professor_id;
		this.name = name;
		this.pwd = pwd;
		this.major = major;
	}
	public int getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(int professor_id) {
		this.professor_id = professor_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Professor [professor_id=" + professor_id + ", name=" + name + ", pwd=" + pwd + ", major=" + major + "]";
	}
	
}

