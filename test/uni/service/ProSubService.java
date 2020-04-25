package kosta.uni.service;

import java.util.List;

import kosta.uni.dao.ProfessorSubjectDAO;
import kosta.uni.exception.NotFoundException;
import kosta.uni.vo.ProfessorSubject;

public class ProSubService {
	private static ProSubService service = new ProSubService();
	ProfessorSubjectDAO dao;
	private ProSubService() {
		dao = new ProfessorSubjectDAO();
	}
	public static ProSubService getInstance() {
		return service;
	}
	
	/**
	 * ����id�� ����ϴ� �����(List) ���
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public List<ProfessorSubject> selectMySubject(int id) throws NotFoundException {
		return dao.selectById(id);
	}
	/**
	 * ���� id�� ���� ������ ����ϴ� ���������� Ȯ��
	 * @param id
	 * @param subject_code
	 * @return
	 * @throws NotFoundException
	 */
	public ProfessorSubject isMySubject(int id, String code) throws NotFoundException {
		ProfessorSubject subject = dao.selectByIdCode(id, code);
		return subject;
	}
	
	
}
