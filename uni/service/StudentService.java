package kosta.uni.service;

import kosta.uni.dao.StudentDAO;
import kosta.uni.exception.NotFoundException;
import kosta.uni.vo.Student;

public class StudentService {
	
	private static StudentService stservice = new StudentService();
	private static StudentDAO sdao;
	
	private StudentService() {
		sdao = new StudentDAO();
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
		
		Student st = sdao.selectById(id);
		if(st.getPwd() == null)
			throw new NotFoundException("회원이 아닙니다.");
		if(!st.getPwd().equals(pwd)) {
			throw new NotFoundException("@@@@@저리가@@@@");
		}
		return st;
	}
	
	/**
	 * 변수1개 받아 -> 해당하는 정보를 조회한다.
	 * @param id
	 */
	public Student showPersonalInfo(int id) throws NotFoundException {		
		return sdao.selectById(id);		
	}
	
	/**
	 * 변수2개받아 -> 패스워드를 변경시킨다.
	 * @param id
	 * @param pwd
	 */
	public void changePwd(int id, String pwd) throws Exception {
		Student st = sdao.selectById(id);
		if(st.getPwd() == null) {
			throw new NotFoundException("회원가입부터 하라규~");
		} else {
		st.setPwd(pwd);
		sdao.update(st);
		}
	}
	
	/**
	 * 변수2개받아 -> 회원가입 시킨다.
	 * @param id
	 * @param pwd
	 */
	public void resister(int id, String pwd) throws Exception {
		
		Student st = sdao.selectById(id);
		if(st.getPwd() != null) {
			throw new NotFoundException("해당 ID 있다규~");
		} else {
			st.setPwd(pwd);
			sdao.update(st);
		}
		
		
	}
	

}
