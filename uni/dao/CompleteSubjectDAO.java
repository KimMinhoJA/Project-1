package kosta.uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kosta.uni.exception.AddException;
import kosta.uni.exception.DeleteException;
import kosta.uni.exception.ModifyException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.sql.MyConnection;
import kosta.uni.vo.CompleteSubject;
import kosta.uni.vo.Grade;
import kosta.uni.vo.Major;
import kosta.uni.vo.Student;
import kosta.uni.vo.Subject;

public class CompleteSubjectDAO {
	/**
	 * ���� �ڵ带 �̿��Ͽ� ��û�� �������� ��ȯ
	 * 
	 * @param subject_code
	 * @param term
	 * @return
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> selectByCodeTerm(String subject_code, String term) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();
			String selectByCodeSQL = "SELECT student_id, grade_point, subject_date" + " , score"
					+ " , name, pwd, major_number, accumulated_grade, class_level" + " , major_name, necessary_grade"
					+ " , subject_code, subject_name, credit, limit, start_time, run_time" + " FROM OUTERCOMSUBVIEW"
					+ " WHERE subject_code = ? AND subject_date = ?";

			pstmt = con.prepareStatement(selectByCodeSQL);
			pstmt.setString(1, subject_code);
			pstmt.setString(2, term);
			rs = pstmt.executeQuery();

			List<CompleteSubject> subjectList = new ArrayList<>();
			while (rs.next()) {
				Major major = new Major(rs.getInt("major_number"), rs.getString("major_name"),
						rs.getInt("necessary_grade"));
				Student student = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("pwd"), major,
						rs.getInt("accumulated_grade"), rs.getInt("class_level"));
				Grade grade = new Grade(rs.getString("grade_point"), rs.getInt("score"));
				Subject subject = new Subject(rs.getString("subject_code"), rs.getString("subject_name"),
						rs.getInt("credit"), rs.getInt("limit"), rs.getString("start_time"), major,
						rs.getInt("run_time"));
				CompleteSubject cs = new CompleteSubject(student, subject, grade, rs.getString("subject_date"));
				subjectList.add(cs);
			}
			if (subjectList.size() == 0) {
				throw new NotFoundException();
			}

			return subjectList;

		} catch (Exception e) {
			throw new NotFoundException("");
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	/**
	 * ������ �ٲ� �� �ִ� �޼ҵ�(���� ����)
	 * 
	 * @param completeSubject
	 * @throws ModifyException
	 */
	public void update(CompleteSubject completeSubject) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			String updateSQL = "UPDATE complete_subject SET grade_point = ? WHERE student_id = ? AND com_subject_code = ? AND grade_point IS NULL";
			pstmt = con.prepareStatement(updateSQL);

			if (completeSubject.getGrade() == null) {
				throw new ModifyException("������ �Է����� �ʾҽ��ϴ�.");
			}

			pstmt.setString(1, completeSubject.getGrade().getGrade_point());
			pstmt.setInt(2, completeSubject.getStudent().getStudent_id());
			pstmt.setString(3, completeSubject.getSubject().getSubject_code());

			if (pstmt.executeUpdate() == 0) {
				throw new ModifyException("������ �����ϼ̽��ϴ�.");
			}

		} catch (Exception e) {
			throw new ModifyException("�������� ���� ����");
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}

	/**
	 * id�� ���ؼ� �л��� �������� ��� ������ ��ȸ
	 * 
	 * @param id
	 * @return
	 */
	public List<CompleteSubject> selectById(int id) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();
			String selectByIdSQL = "SELECT student_id, grade_point, subject_date" + " , score"
					+ " , name, pwd, major_number, accumulated_grade, class_level" + " , major_name, necessary_grade"
					+ " , subject_code, subject_name, credit, limit, start_time, run_time" + " FROM COMSUBVIEW"
					+ " WHERE student_id = ?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			List<CompleteSubject> subjectList = new ArrayList<>();
			while (rs.next()) {
				Major major = new Major(rs.getInt("major_number"), rs.getString("major_name"),
						rs.getInt("necessary_grade"));
				Student student = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("pwd"), major,
						rs.getInt("accumulated_grade"), rs.getInt("class_level"));
				Grade grade = new Grade(rs.getString("grade_point"), rs.getDouble("score"));
				Subject subject = new Subject(rs.getString("subject_code"), rs.getString("subject_name"),
						rs.getInt("credit"), rs.getInt("limit"), rs.getString("start_time"), major,
						rs.getInt("run_time"));
				CompleteSubject cs = new CompleteSubject(student, subject, grade, rs.getString("subject_date"));
				subjectList.add(cs);
			}

			if (subjectList.size() == 0) {
				throw new NotFoundException("�ش� �л��� ���������� �������� �ʽ��ϴ�.");
			}

			return subjectList;

		} catch (Exception e) {
			throw new NotFoundException("id�� ã�� ����");
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	/**
	 * id�� term�б⿡ �ش��ϴ� ���Ǹ�� ��ȸ
	 * 
	 * @param id
	 * @param term
	 * @return
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> selectByIdTerm(int id, String term) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();
			String selectByIdSQL = "SELECT student_id, grade_point, subject_date" + " , score"
					+ " , name, pwd, major_number, accumulated_grade, class_level" + " , major_name, necessary_grade"
					+ " , subject_code, subject_name, credit, limit, start_time, run_time" + " FROM OUTERCOMSUBVIEW"
					+ " WHERE student_id = ? AND subject_date = ?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, id);
			pstmt.setString(2, term);
			rs = pstmt.executeQuery();

			List<CompleteSubject> subjectList = new ArrayList<>();
			while (rs.next()) {
				Major major = new Major(rs.getInt("major_number"), rs.getString("major_name"),
						rs.getInt("necessary_grade"));
				Student student = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("pwd"), major,
						rs.getInt("accumulated_grade"), rs.getInt("class_level"));
				Grade grade = new Grade(rs.getString("grade_point"), rs.getDouble("score"));
				Subject subject = new Subject(rs.getString("subject_code"), rs.getString("subject_name"),
						rs.getInt("credit"), rs.getInt("limit"), rs.getString("start_time"), major,
						rs.getInt("run_time"));
				CompleteSubject cs = new CompleteSubject(student, subject, grade, rs.getString("subject_date"));
				subjectList.add(cs);
			}

			if (subjectList.size() == 0) {
				throw new NotFoundException("�ش� �л��� " + term + "�б��� ���������� �������� �ʽ��ϴ�.");
			}

			return subjectList;

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	/**
	 * ������û�� �޼ҵ� complete_subject�� ������û�� �л��� ������ �߰������ش�.
	 * 
	 * @param cs
	 * @throws AddException
	 */
	public void insert(CompleteSubject cs) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO complete_subject(student_id, com_subject_code, subject_date) VALUES(?, ?, ?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, cs.getStudent().getStudent_id());
			pstmt.setString(2, cs.getSubject().getSubject_code());
			pstmt.setString(3, cs.getTerm());

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new AddException("�̹� ������ �����Դϴ�.");
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}

	/**
	 * id�� code�� �ش��ϴ� complete_subject�� ��ȸ
	 * 
	 * @param id
	 * @param code
	 * @return
	 * @throws NotFoundException
	 */
	public CompleteSubject selectByIdCode(int id, String code) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectByIdCodeSQL = "SELECT student_id, grade_point, subject_date" + " , score"
					+ " , name, pwd, major_number, accumulated_grade, class_level" + " , major_name, necessary_grade"
					+ " , subject_code, subject_name, credit, limit, start_time, run_time" + " FROM OUTERCOMSUBVIEW"
					+ " WHERE student_id = ? AND subject_code = ?";
			pstmt = con.prepareStatement(selectByIdCodeSQL);
			pstmt.setInt(1, id);
			pstmt.setString(2, code);
			rs = pstmt.executeQuery();

			CompleteSubject cs = null;

			if (rs.next()) {
				Major major = new Major(rs.getInt("major_number"), rs.getString("major_name"),
						rs.getInt("necessary_grade"));
				Student student = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("pwd"), major,
						rs.getInt("accumulated_grade"), rs.getInt("class_level"));
				Grade grade = new Grade(rs.getString("grade_point"), rs.getInt("score"));
				Subject subject = new Subject(rs.getString("subject_code"), rs.getString("subject_name"),
						rs.getInt("credit"), rs.getInt("limit"), rs.getString("start_time"), major,
						rs.getInt("run_time"));
				cs = new CompleteSubject(student, subject, grade, rs.getString("subject_date"));
			}
			if (cs == null) {
				throw new NotFoundException("�ش� �л��� ���� ������ �������� �ʽ��ϴ�.");
			}
			return cs;
		} catch (Exception e) {
			throw new NotFoundException("Id, Code�˻� ����");
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	/**
	 * ������û���� ���� �޼ҵ� id,code�� �ش��ϴ� ���������� �����Ѵ�. ���� �̹� ������ �ִ°�� ���� �Ұ�
	 * 
	 * @param id
	 * @param code
	 * @throws DeleteException
	 */
	public void delete(int id, String code) throws DeleteException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			selectByIdCode(id, code);
			con = MyConnection.getConnection();
			String deleteSQL = "DELETE complete_subject where student_id = ? AND com_subject_code = ? AND grade_point IS NULL";
			pstmt = con.prepareStatement(deleteSQL);

			pstmt.setInt(1, id);
			pstmt.setString(2, code);
			if (pstmt.executeUpdate() == 0) {
				throw new DeleteException("");
			}

		} catch (NotFoundException e) {
			throw new DeleteException("�ش� ���� ��û ������ �����ϴ�.");
		} catch (Exception e) {
			throw new DeleteException("�ش� ������ ����ó���� �����Ǿ����ϴ�.");
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
}
