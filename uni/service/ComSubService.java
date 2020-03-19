package kosta.uni.service;

import java.util.List;

import kosta.uni.dao.CompleteSubjectDAO;
import kosta.uni.exception.AddException;
import kosta.uni.exception.DeleteException;
import kosta.uni.exception.ModifyException;
import kosta.uni.exception.NotFoundException;
import kosta.uni.vo.CompleteSubject;
import kosta.uni.vo.Subject;

public class ComSubService {
	private static ComSubService service = new ComSubService();
	CompleteSubjectDAO dao;

	private ComSubService() {
		dao = new CompleteSubjectDAO();
	}

	public static ComSubService getInstance() {
		return service;
	}

	/**
	 * 학생의 해당학기 성적 조회
	 * 
	 * @param id   학생id로 입력 받아서
	 * @param term 해당 학기 성적을 조회
	 * @return list로 반환
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> showGradeByTerm(int id, String term) throws NotFoundException {
		List<CompleteSubject> list = dao.selectByIdTerm(id, term);
		// List<completeSubject> list = csdao.selectByCodeTerm(term)
		return list;
	};

	/**
	 * 수강신청
	 * @param subject_code 과목코드
	 * @param id           학생id
	 * @throws NotFoundException
	 */
	public void applySubject(CompleteSubject cs) throws AddException {
		dao.insert(cs);
	}

	/**
	 * 이번학기 시간표보기
	 * 
	 * @param id
	 */
	public List<CompleteSubject> showSchedule(int id, String term) throws NotFoundException {
		return dao.selectByIdTerm(id, term);

	}

	/**
	 * 수강취소
	 * 
	 * @param subject_code
	 * @param id
	 * @throws DeleteException
	 */
	public void deleteApply(int id, String code) throws DeleteException {
		dao.delete(id, code);
	}

	/**
	 * 현재 내 수강신청 내역을 보여주기
	 * 
	 * @param id
	 * @param term
	 * @return
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> showMyAppInfo(int id, String term) throws NotFoundException {
		return dao.selectByIdTerm(id, term);
	}

	/**
	 * 내 총 학점 계산
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> showAllGrade(int id) throws NotFoundException {
		return dao.selectById(id);
	}

	/**
	 * 내 학생들 성적 조회
	 * @param code
	 * @param term
	 * @return
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> showMySubjectStudentGrade(String code, String term) throws NotFoundException {
		return dao.selectByCodeTerm(code, term);
	}
	
	/**
	 * 학생 출석부 조회
	 * @param code
	 * @param term
	 * @return
	 * @throws NotFoundException
	 */
	public List<CompleteSubject> showMySubjectStudent(String code, String term) throws NotFoundException {
		return dao.selectByCodeTerm(code, term);
	}

	public void grantGrade(CompleteSubject cs) throws ModifyException{
		dao.update(cs);
	}

	public void duplicateCheck(int id, String subject_code, String term) throws NotFoundException{
		dao.selectByIdCode(id, subject_code);
	}

	public void scheduleCheck(Subject subject, int id, String term) throws NotFoundException{
		List<CompleteSubject> list = dao.selectByIdTerm(id, term);
		int sub_start = new Integer(subject.getStart_time().substring(1));
		int sub_end = sub_start + subject.getRun_time();
		String sub_day = subject.getStart_time().substring(0,1);
		boolean check = false;
		
		for(CompleteSubject cs : list) {
			int start = new Integer(cs.getSubject().getStart_time().substring(1));
			int end = cs.getSubject().getRun_time() + start;
			String day = cs.getSubject().getStart_time().substring(0,1);
			if(!sub_day.equals(day))
				continue;
			
			if(start > sub_start) {
				if(sub_end > start) {
					check = true;
					break;
				}
			}else {
				if(end > sub_start) {
					check = true;
					break;
				}
			}
		}
		
		if(!check) {
			throw new NotFoundException();
		}
	}
}
