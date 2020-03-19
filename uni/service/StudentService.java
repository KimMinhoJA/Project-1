package kosta.uni.service;

import kosta.uni.dao.StudentDAO;
import kosta.uni.exception.ModifyException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.vo.Student;

public class StudentService {
	
	private static StudentService stservice = new StudentService();
	private static StudentDAO dao;
	
	private StudentService() {
		dao = new StudentDAO();
	}
	
	public static StudentService getInstance() {
		return stservice;
	}
	
	
	
	/**
	 * 변수2개 받아 -> DAO에 로그인시킨다.
	 * @param id
	 * @param pwd
	 */
	public Student login(int id, String pwd) throws NotFoundException {
		
		Student st = dao.selectById(id);
		if(st.getPwd() == null)
			throw new NotFoundException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		if(!st.getPwd().equals(pwd)) {
			throw new NotFoundException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		return st;
	}
	
	/**
	 * 변수1개 받아 -> 해당하는 정보를 조회한다.
	 * @param id
	 */
	public Student showPersonalInfo(int id) throws NotFoundException {		
		return dao.selectById(id);		
	}
	
	/**
	 * 변수2개받아 -> 패스워드를 변경시킨다.
	 * @param id
	 * @param pwd
	 */
	public void changePwd(int id, String pwd) throws NotFoundException, ModifyException {
		Student st = dao.selectById(id);
		if(st.getPwd() == null) {
			throw new NotFoundException("등록되지 않은 회원입니다.");
		} else {
		st.setPwd(pwd);
		dao.update(st);
		}
	}
	
	/**
	 * 변수2개받아 -> 회원가입 시킨다.
	 * @param id
	 * @param pwd
	 */
	public void resister(int id, String pwd) throws NotFoundException, ModifyException {
		
		Student st = dao.selectById(id);
		if(st.getPwd() != null) {
			throw new NotFoundException("이미 등록된 회원입니다.");
		} else {
			st.setPwd(pwd);
			dao.update(st);
		}
		
		
	}

	public void setGrade(Student student) throws ModifyException{
		dao.update(student);	
	}
	

}
