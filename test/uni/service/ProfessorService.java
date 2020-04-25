package kosta.uni.service;

import kosta.uni.dao.ProfessorDAO;
import kosta.uni.exception.AddException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.vo.Professor;

public class ProfessorService {
	private static ProfessorService service = new ProfessorService();
	ProfessorDAO dao;
	private ProfessorService() {
		dao = new ProfessorDAO();
	}
	public static ProfessorService getInstance() {
		return service;
	}
	
	public Professor login(int id, String pwd) throws NotFoundException{
		Professor professor = dao.selectById(id);
		if(professor.getPwd() == null) {
			throw new NotFoundException("���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		if(!professor.getPwd().equals(pwd)) {
			throw new NotFoundException("���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return professor;
	}
	
	public void changePwd (int id, String pwd) throws Exception{
	    Professor professor = dao.selectById(id);
	    if(professor.getPwd()==null) {
	       throw new AddException("��ϵ��� ���� ȸ���Դϴ�.");
	    }
	    professor.setPwd(pwd);
	    dao.update(professor);
	 }
	 
	/////////////////////////////////////////////////////////////////////////
	 public void resister(int id, String pwd) throws Exception{
	    Professor professor = dao.selectById(id);
	    if(professor.getPwd()!=null) {
	       throw new AddException("�̹� ��ϵ� ȸ���Դϴ�.");
	    }
	    professor.setPwd(pwd);
	    dao.update(professor);
	 }
	public Professor setMajor(int id)throws NotFoundException{
		return dao.selectById(id);
	}
	 
}
