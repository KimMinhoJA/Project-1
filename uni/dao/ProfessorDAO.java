package kosta.uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kosta.uni.exception.ModifyException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.sql.MyConnection;
import kosta.uni.vo.Major;
import kosta.uni.vo.Professor;

public class ProfessorDAO {
	/**
	 * ID를 이용해서 교수정보 반환
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public Professor selectById(int id) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectByIdSQL = "SELECT professor_id, name, pwd, major_number" + " ,major_name, necessary_grade"
					+ " FROM professorview" + " WHERE professor_id = ?";

			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			Professor prof = null;

			if (rs.next()) {
				prof = new Professor();
				prof.setProfessor_id(rs.getInt("professor_id"));
				prof.setName(rs.getString("name"));
				prof.setPwd(rs.getString("pwd"));

				// Major객체만들기
				Major major = new Major();
				major.setMajor_number(rs.getInt("major_number"));
				major.setMajor_name(rs.getString("major_name"));
				major.setNecessary_grade(rs.getInt("necessary_grade"));
				prof.setMajor(major);

			}

			if (prof == null) {
				throw new NotFoundException(id + "에 해당하는 교수가 없습니다.");
			}

			return prof;

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	/**
	 * 이름과 암호, 학과의 정보를 수정
	 * 
	 * @param professor
	 * @throws ModifyException
	 */
	public void update(Professor professor) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			String updateSQL = "UPDATE professor SET name = ?, pwd = ?, major_number = ? WHERE professor_id = ? ";
			pstmt = con.prepareStatement(updateSQL);

			String name = professor.getName();
			String pwd = professor.getPwd();
			Professor p = selectById(professor.getProfessor_id());
			
			int major_number = p.getMajor().getMajor_number();

			if (name == null) {
				name = p.getName();
			}
			if (pwd == null) {
				pwd = p.getPwd();
			}
			if (professor.getMajor() != null) {
				major_number = professor.getMajor().getMajor_number();
			}

			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			pstmt.setInt(3, major_number);
			pstmt.setInt(4, professor.getProfessor_id());

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ModifyException("수정 실패");
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}

}
