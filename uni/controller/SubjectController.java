package kosta.uni.controller;

import java.util.List;

import kosta.uni.exception.NotFoundException;
import kosta.uni.service.SubjectService;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;
import kosta.uni.view.FailView;
import kosta.uni.view.SuccessView;
import kosta.uni.vo.Major;
import kosta.uni.vo.Subject;

public class SubjectController {
	private static SubjectController controller = new SubjectController();
	private SubjectService service = SubjectService.getInstance();

	private SubjectController() {
	}

	public static SubjectController getInstance() {
		return controller;
	}

	/**
	 * ��� ���� ���� ��ȸ
	 */
	public void showAllSubject() {
		try {
			List<Subject> list = service.showAllSubject();
			SuccessView.printAllSubject(list);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public void showMajorSubject(int major_number) {
		try {
			List<Subject> list = service.showMajorSubject(major_number);
			SuccessView.printMajorSubject(list);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public void limitCheck(String subject_code, String id) {
		try {
			Subject subject = service.limitCheck(subject_code);
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			if (session.getAttribute("major") instanceof Major) {
				if (subject.getMajor().getMajor_number() == 0 || subject.getMajor()
						.getMajor_number() == ((Major) session.getAttribute("major")).getMajor_number()) {

					if ((session.getAttribute("level") instanceof Integer)) {
						if (subject.getLimit() <= (Integer) session.getAttribute("level")) {
							session.setAttribute("check", true);
							session.setAttribute("subject", subject);
						} else
							throw new NotFoundException("���� ��û�� �� ���� �����Դϴ�.");
					} else {
						throw new NotFoundException("�г� ������ �����ϴ�.");
					}
				} else {
					throw new NotFoundException("�а��� ���� �ʽ��ϴ�.");
				}
			} else {
				throw new NotFoundException("�а������� �����ϴ�.");
			}

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
