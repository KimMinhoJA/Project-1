package kosta.uni.vo;

public class Major{
	private int major_number;
	private String major_name;
	private int necessary_grade;
	public Major() {
		super();
	}
	public Major(int major_number, String major_name, int necessary_grade) {
		super();
		this.major_number = major_number;
		this.major_name = major_name;
		this.necessary_grade = necessary_grade;
	}
	public int getMajor_number() {
		return major_number;
	}
	public void setMajor_number(int major_number) {
		this.major_number = major_number;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public int getNecessary_grade() {
		return necessary_grade;
	}
	public void setNecessary_grade(int necessary_grade) {
		this.necessary_grade = necessary_grade;
	}
	@Override
	public String toString() {
		return "Major [major_number=" + major_number + ", major_name=" + major_name + ", necessary_grade="
				+ necessary_grade + "]";
	}
	
	
}

