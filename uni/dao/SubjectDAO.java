package kosta.uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kosta.uni.exception.NotFoundException;
import kosta.uni.sql.MyConnection;
import kosta.uni.vo.Major;
import kosta.uni.vo.Subject;

public class SubjectDAO {

	/**
	 * 과목코드를 입력받아 DB연결후 해당 과목에 대한 정보를 리턴한다.
	 * 
	 * @param subject_code
	 * @return
	 * @throws NotFoundException
	 */
	public Subject selectByCode(String subject_code) throws NotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();

			String selectByCodeSQL = "SELECT subject_name, credit, limit, start_time"
					+ ", major_number, major_name, necessary_grade, run_time FROM SUBJECTVIEW WHERE subject_code=?";
			pstmt = con.prepareStatement(selectByCodeSQL);
			pstmt.setString(1, subject_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("subject_name");
				int credit = rs.getInt("credit");
				int limit = rs.getInt("limit");
				String start = rs.getString("start_time");
				int run_time = rs.getInt("run_time");
				Major major = new Major();
				major.setMajor_name(rs.getString("major_name"));
				major.setMajor_number(rs.getInt("major_number"));
				major.setNecessary_grade(rs.getInt("necessary_grade"));
				
				return new Subject(subject_code, name, credit, limit, start, major, run_time);

			} else {
				throw new NotFoundException("해당과목번호 없다규~");
			}
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

	}

	/**
	 * 과목에 해당하는 과목번호를 입력받아 해당 과목번호에 해당하는 정보를 리턴
	 * 
	 * @param major
	 * @return
	 */
	public List<Subject> selectByMajor(int major_number) throws NotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Subject> list = new ArrayList<Subject>();

		try {
			con = MyConnection.getConnection();

			String selectByMajor = "SELECT * FROM SUBJECTVIEW WHERE major_number = ?";
			pstmt = con.prepareStatement(selectByMajor);
			pstmt.setInt(1, major_number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Subject(rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("credit"),
						rs.getInt("limit"), rs.getString("start_time"), new Major(rs.getInt("major_number"), rs.getString("major_name")
								, rs.getInt("necessary_grade")), rs.getInt("run_time")));
			}
			if (list.size() == 0) {
				throw new NotFoundException("과목정보없음");
			}
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return list;

	}

	/**
	 * 과목에 대한 DB의 전체검색
	 * 
	 * @return
	 * @throws NotFoundException
	 */
	public List<Subject> selectALL() throws NotFoundException {

		List<Subject> list = new ArrayList<Subject>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();

			String selectALLSQL = "SELECT * FROM SUBJECTVIEW";
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String subject_code = rs.getString("subject_code");
				String subject_name = rs.getString("subject_name");
				int credit = rs.getInt("credit");
				int limit = rs.getInt("limit");
				String start_time = rs.getString("start_time");
				Major major = new Major();
				major.setMajor_name(rs.getString("major_name"));
				major.setMajor_number(rs.getInt("major_number"));
				major.setNecessary_grade(rs.getInt("necessary_grade"));
				int run_time = rs.getInt("run_time");
				Subject subject = new Subject(subject_code, subject_name, credit, limit, start_time, major,
						run_time);
				list.add(subject);
			}
			if (list.size() == 0) {
				throw new NotFoundException("전체정보가 없어~");
			}
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
		return list;
	}
}
