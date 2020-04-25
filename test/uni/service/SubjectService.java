package kosta.uni.service;

import java.util.List;

import kosta.uni.dao.SubjectDAO;
import kosta.uni.exception.NotFoundException;
import kosta.uni.vo.Subject;

public class SubjectService {
	private static SubjectService service = new SubjectService();
	SubjectDAO dao = new SubjectDAO();

	private SubjectService() {
	}

	public static SubjectService getInstance() {
		return service;
	}

	/**
	 * 전체 과목 조회
	 * @return
	 * @throws NotFoundException
	 */
	public List<Subject> showAllSubject() throws NotFoundException {
		List<Subject> list = dao.selectALL();
		return list;
	}

	/**
	 * 학과별 전공 조회
	 * @param major_number
	 * @return
	 * @throws NotFoundException
	 */
	public List<Subject> showMajorSubject(int major_number) throws NotFoundException {
		return dao.selectByMajor(major_number);
	}

	public Subject limitCheck(String subject_code) throws NotFoundException{
		return dao.selectByCode(subject_code);
	}
}
