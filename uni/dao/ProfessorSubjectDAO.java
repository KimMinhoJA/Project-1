package kosta.uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kosta.uni.exception.NotFoundException;
import kosta.uni.sql.MyConnection;
import kosta.uni.vo.Major;
import kosta.uni.vo.Professor;
import kosta.uni.vo.ProfessorSubject;
import kosta.uni.vo.Subject;

public class ProfessorSubjectDAO {

	/**
	 * id�� ���ؼ� ������ ������� ��ȸ
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public static List<ProfessorSubject> selectById(int id) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();

			String selectByIdSQL = "select name, pwd, major_number, major_name"
					+ " ,necessary_grade, subject_code, subject_name, credit, limit, start_time, run_time"
					+ " from PROSUBVIEW where professor_id=?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			List<ProfessorSubject> subjects = new ArrayList<>();
			Professor professor = new Professor();
			Major major = new Major();
			boolean isFirst = true;

			while (rs.next()) {
				if (isFirst) {
					professor.setProfessor_id(id);
					professor.setName(rs.getString("name"));
					professor.setPwd("pwd");
					major.setMajor_number(rs.getInt("major_number"));
					major.setMajor_name(rs.getString("major_name"));
					major.setNecessary_grade(rs.getInt("necessary_grade"));
					professor.setMajor(major); // Professor��
					isFirst = false;
				}
				Subject subject = new Subject();
				subject.setSubject_code(rs.getString("subject_code"));
				subject.setSubject_name(rs.getString("subject_name"));
				subject.setCredit(rs.getInt("credit"));
				subject.setLimit(rs.getInt("limit"));
				subject.setStart_time(rs.getString("start_time"));
				subject.setRun_time(rs.getInt("run_time")); // Subject��

				ProfessorSubject professorSubject = new ProfessorSubject();
				professorSubject.setProfessor(professor);
				professorSubject.setSubject(subject);// ProfessorSubject��

				subjects.add(professorSubject);
			}
			
			if (subjects.size() == 0) {
				throw new NotFoundException("id�� ����ϴ� ������ �����ϴ�.");
			} 
			return subjects;
		} catch (Exception e) {
			throw new NotFoundException("id�˻� ���� : ");
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}// �޼ҵ峡

	/**
	 * id�� code�� ����ؼ� �ش� ���� ��ȸ
	 * 
	 * @param id
	 * @param code
	 * @return
	 * @throws NotFoundException
	 */
	public ProfessorSubject selectByIdCode(int id, String code) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProfessorSubject professorSubject = null;
		try {
			con = MyConnection.getConnection();

			String selectByIdSQL = "select name, pwd, major_number, major_name, necessary_grade"
					+ " ,subject_code, subject_name, credit, limit, start_time, run_time"
					+ " from PROSUBVIEW where professor_id=? AND subject_code=?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, id);
			pstmt.setString(2, code);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				Professor professor = new Professor();
				professor.setName(rs.getString("name"));
				professor.setPwd(rs.getString("pwd"));
				Major major = new Major();
				major.setMajor_number(rs.getInt("major_number"));
				major.setMajor_name(rs.getString("major_name"));
				major.setNecessary_grade(rs.getInt("necessary_grade"));
				professor.setMajor(major);

				Subject subject = new Subject();
				subject.setSubject_code(rs.getString("subject_code"));
				subject.setSubject_name(rs.getString("subject_name"));
				subject.setCredit(rs.getInt("credit"));
				subject.setLimit(rs.getInt("limit"));
				subject.setStart_time(rs.getString("start_time"));
				subject.setRun_time(rs.getInt("run_time")); // Subject��

				professorSubject = new ProfessorSubject();
				professorSubject.setProfessor(professor);
				professorSubject.setSubject(subject);// ProfessorSubject��
			}
			if (professorSubject == null) {
				throw new NotFoundException("�ش� id�� ���� code�� �ش��ϴ� ������ �����ϴ�.");
			} else {
				return professorSubject;
			}

		} catch (Exception e) {
			throw new NotFoundException("id, code �˻� ���� : ");
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}
}