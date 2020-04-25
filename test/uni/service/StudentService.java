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
	 * ����2�� �޾� -> DAO�� �α��ν�Ų��.
	 * @param id
	 * @param pwd
	 */
	public Student login(int id, String pwd) throws NotFoundException {
		
		Student st = dao.selectById(id);
		if(st.getPwd() == null)
			throw new NotFoundException("���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		if(!st.getPwd().equals(pwd)) {
			throw new NotFoundException("���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return st;
	}
	
	/**
	 * ����1�� �޾� -> �ش��ϴ� ������ ��ȸ�Ѵ�.
	 * @param id
	 */
	public Student showPersonalInfo(int id) throws NotFoundException {		
		return dao.selectById(id);		
	}
	
	/**
	 * ����2���޾� -> �н����带 �����Ų��.
	 * @param id
	 * @param pwd
	 */
	public void changePwd(int id, String pwd) throws NotFoundException, ModifyException {
		Student st = dao.selectById(id);
		if(st.getPwd() == null) {
			throw new NotFoundException("��ϵ��� ���� ȸ���Դϴ�.");
		} else {
		st.setPwd(pwd);
		dao.update(st);
		}
	}
	
	/**
	 * ����2���޾� -> ȸ������ ��Ų��.
	 * @param id
	 * @param pwd
	 */
	public void resister(int id, String pwd) throws NotFoundException, ModifyException {
		
		Student st = dao.selectById(id);
		if(st.getPwd() != null) {
			throw new NotFoundException("�̹� ��ϵ� ȸ���Դϴ�.");
		} else {
			st.setPwd(pwd);
			dao.update(st);
		}
		
		
	}

	public void setGrade(Student student) throws ModifyException{
		dao.update(student);	
	}
	

}
