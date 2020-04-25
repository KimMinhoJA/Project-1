package kosta.uni.vo;

public class Grade {
	private String grade_point;
	private double score;
	public Grade() {
		super();
	}
	public Grade(String grade_point, double score) {
		super();
		this.grade_point = grade_point;
		this.score = score;
	}
	public String getGrade_point() {
		return grade_point;
	}
	public void setGrade_point(String grade_point) {
		this.grade_point = grade_point;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Grade [grade_point=" + grade_point + ", score=" + score + "]";
	}

}
