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
	 * ����2�� �޾� -> DAO�� �α��ν�Ų��.
	 * @param id
	 * @param pwd
	 */
	public Student login(int id, String pwd) throws NotFoundException {
		
		Student st = sdao.selectById(id);
		if(st.getPwd() == null)
			throw new NotFoundException("ȸ���� �ƴմϴ�.");
		if(!st.getPwd().equals(pwd)) {
			throw new NotFoundException("@@@@@������@@@@");
		}
		return st;
	}
	
	/**
	 * ����1�� �޾� -> �ش��ϴ� ������ ��ȸ�Ѵ�.
	 * @param id
	 */
	public Student showPersonalInfo(int id) throws NotFoundException {		
		return sdao.selectById(id);		
	}
	
	/**
	 * ����2���޾� -> �н����带 �����Ų��.
	 * @param id
	 * @param pwd
	 */
	public void changePwd(int id, String pwd) throws Exception {
		Student st = sdao.selectById(id);
		if(st.getPwd() == null) {
			throw new NotFoundException("ȸ�����Ժ��� �϶��~");
		} else {
		st.setPwd(pwd);
		sdao.update(st);
		}
	}
	
	/**
	 * ����2���޾� -> ȸ������ ��Ų��.
	 * @param id
	 * @param pwd
	 */
	public void resister(int id, String pwd) throws Exception {
		
		Student st = sdao.selectById(id);
		if(st.getPwd() != null) {
			throw new NotFoundException("�ش� ID �ִٱ�~");
		} else {
			st.setPwd(pwd);
			sdao.update(st);
		}
		
		
	}
	

}
