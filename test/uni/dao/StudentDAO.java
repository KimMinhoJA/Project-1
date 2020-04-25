package kosta.uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kosta.uni.exception.ModifyException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.sql.MyConnection;
import kosta.uni.vo.Major;
import kosta.uni.vo.Student;

//DB연결☆

public class StudentDAO {

	/**
	 * id에 해당하는 학생 검색
	 * 
	 * @param id 아이디(학번)
	 * @return Student 객체
	 * @exception NotFoundExceprion DB연결실패이거나 아이디에 해당 고객이 없으면 예외 발생(null값 반환ㄴㄴ)
	 */
	public Student selectById(int id) throws NotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();

			String selectByIdSQL = "SELECT name, pwd, accumulated_grade, class_level, major_number, major_name, necessary_grade"
					+ " FROM studentview WHERE student_id = ?";

			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				int accumulated_grade = rs.getInt("accumulated_grade");
				int class_level = rs.getInt("class_level");
				int major_number = rs.getInt("major_number");
				String major_name = rs.getString("major_name");
				int necessary_grade = rs.getInt("necessary_grade");
				Major major = new Major(major_number, major_name, necessary_grade);

				return new Student(id, name, pwd, major, accumulated_grade, class_level);

			} else {
				throw new NotFoundException("그런학생없다구~");
			}

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

	}

	public void update(Student student) throws ModifyException { //
		Connection con = null;
		Statement stmt = null;
		try {
			con = MyConnection.getConnection();
			String updateSQL1 = "UPDATE student SET ";
			String updateSQL2 = " WHERE student_id = '" + student.getStudent_id() + "'";
			boolean flag = false;
			if (student.getName() != null) {
				if (flag) {
					updateSQL1 += ",";					
				}
				updateSQL1 += "name = '" + student.getName() + "'";
				flag = true;
			}

			if (student.getPwd() != null) {
				if (flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "pwd = '" + student.getPwd() + "'";
				flag = true;
			}
			if (student.getMajor() != null) {
				if (flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "major_number = '" + student.getMajor().getMajor_number() +"'";
				flag = true;
			}
			if (student.getAccumulated_grade() != 0) {// 0이 아니면
				if (flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "accumulated_grade = '" + student.getAccumulated_grade()+"'";
				flag = true;
			}
			if (student.getClass_level() != 0) {
				if (flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "class_level = '" + student.getClass_level() +"'";
				flag = true;
				
			}
			if (flag) {
				stmt = con.createStatement();
				stmt.executeUpdate(updateSQL1 + updateSQL2);
			}
		} catch (Exception e) {
			throw new ModifyException(e.getMessage());
		} finally {
			MyConnection.close(con, stmt,null);
		}

	}	
	
}