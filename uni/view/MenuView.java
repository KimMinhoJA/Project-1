package kosta.uni.view;

import java.util.Scanner;

import kosta.uni.controller.ComSubController;
import kosta.uni.controller.ProSubController;
import kosta.uni.controller.ProfessorController;
import kosta.uni.controller.StudentController;
import kosta.uni.controller.SubjectController;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;
import kosta.uni.vo.Major;
import kosta.uni.vo.Subject;

public class MenuView {
	private ProfessorController proController = ProfessorController.getInstance();
	private ProSubController proSubController = ProSubController.getInstance();
	private ComSubController comSubController = ComSubController.getInstance();
	private SubjectController subController = SubjectController.getInstance();
	private StudentController stuController = StudentController.getInstance();

	final static String THISTERM = "2020-1";

	Scanner sc = new Scanner(System.in);
	boolean outMenu = true;

	public void menu() {
		while (outMenu) {
			System.out.println("=============================================");
			System.out.println("=============Kosta University================");
			System.out.println("====== 1. 로그인  2. 회원가입  9. 종료 ======");
			System.out.println("=============================================");
			System.out.print("입력 >> ");
			switch (sc.nextLine()) {
			case "1":
				login();
				break;
			case "2":
				resister();
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("Error : 번호를 잘못 입력하셨습니다.");
				System.out.println();
				break;
			}
		}
	}

	public void login() {
		System.out.println();

		System.out.println("=============================================");
		System.out.println("=================== 로그인 ==================");
		
		while (outMenu) {
			System.out.println("== 1. 학생 로그인  2. 교수 로그인  9. 나가기 ==");
			System.out.println("=============================================");
			System.out.print("입력 >> ");
			switch (sc.nextLine()) {
			case "1":
				stuLogin();
				break;
			case "2":
				proLogin();
				break;
			case "9":
				System.out.println("나가기");
				outMenu = false;
				break;
			default:
				System.out.println("Error : 번호를 잘못 입력하셨습니다.");
			}
		}
		outMenu = true;
	}

	public void resister() {
		System.out.println();
		System.out.println("=============================================");
		System.out.println("================== 회원가입 =================");
		while (outMenu) {
			System.out.println("=============================================");
			System.out.println("======== 1. 학생  2. 교수  9. 나가기 ========");
			System.out.println("=============================================");
			System.out.print("입력 >> ");
			switch (sc.nextLine()) {
			case "1":
				stuResister();
				break;
			case "2":
				proResister();
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("Error : 번호를 잘못 입력하셨습니다.");
				System.out.println();
			}
		}
		outMenu = true;
	}

	public void stuLogin() {
		System.out.println("==학생모드 로그인==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pwd = sc.nextLine();

		stuController.login(id, pwd);
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);

		if (session == null)
			return;

		while (outMenu) {
			System.out.println("1. 수강신청 및 변경");
			System.out.println("2. 성적 조회");
			System.out.println("3. 신상 조회");
			System.out.println("4. 시간표");
			System.out.println("5. 비밀번호 변경");
			System.out.println("9. 로그아웃");

			switch (sc.nextLine()) {
			case "1":
				applyChangeSubject(id);
				break;
			case "2":
				showGrade(id);
				break;
			case "3":
				showPersonalInfo(id);
				break;
			case "4":
				showStudentSchedule(id);
				break;
			case "5":
				System.out.println("==비밀번호 변경==");
				System.out.println("현재 비밀번호를 입력하세요.");
				System.out.print("입력 >> ");
				String newpwd = sc.nextLine();

				if (!pwd.equals(newpwd)) {
					System.out.println("비밀번호가 올바르지 않습니다.");
					break;
				}
				System.out.println("변경하실 비밀번호를 입력하세요.");
				System.out.println("입력 >> ");
				newpwd = sc.nextLine();
				if(newpwd.length() == 0) {
					System.out.println("미입력.");
					break;
				}
				pwd = newpwd;
				stuController.changePwd(id, pwd);
				break;
			case "9":
				stuController.logout(id);
				outMenu = false;
				break;
			default : 
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
		outMenu = true;
	}

	public void proLogin() {
		System.out.println("==교수모드 로그인==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pwd = sc.nextLine();

		proController.login(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		if (session == null)
			return;

		while (outMenu) {
			System.out.println("1. 성적부여");
			System.out.println("2. 맡은 강의 학생들 성적 조회");
			System.out.println("3. 시간표");
			System.out.println("4. 비밀번호 변경");
			System.out.println("9. 로그아웃");
			System.out.print("입력 >> ");
			switch (sc.nextLine()) {
			case "1":
				grantMySubjectStudent(id);
				break;
			case "2":
				proSubController.showMySubject(id);
				System.out.println("성적 확인할 과목코드를 입력하세요.");
				System.out.print("입력 >> ");
				String subject_code = sc.nextLine();
				proSubController.isMySubject(id, subject_code);
				if (session.getAttribute("subject") == null) {
					System.out.println("해당과목의 담당교수가 아닙니다.");
					break;
				}
				comSubController.showMySubjectStudentGrade(id, subject_code, THISTERM);
				break;
			case "3":
				proSubController.showSchedule(id);
				break;
			case "4":
				System.out.println("==비밀번호 변경==");
				System.out.println("현재 비밀번호를 입력하세요.");
				System.out.print("입력 >> ");
				String newpwd = sc.nextLine();

				if (!pwd.equals(newpwd)) {
					System.out.println("비밀번호가 올바르지 않습니다.");
					return;
				}
				System.out.println("변경하실 비밀번호를 입력하세요.");
				System.out.println("입력 >> ");
				newpwd = sc.nextLine();
				if(newpwd.length() == 0) {
					System.out.println("미입력.");
					break;
				}
				pwd = newpwd;
				proController.changePwd(id, pwd);
				break;
			case "9":
				proController.logout(id);
				outMenu = false;
				break;
			default:
				System.out.println("잘못된 번호 입력");
				break;
			}
		}
		outMenu = true;
	}

	public void stuResister() {
		System.out.println();
		System.out.println("=============================================");
		System.out.println("=============== 학생 회원가입 ===============");
		System.out.println("========== 학생 번호를 입력하세요. ==========");
		System.out.print("입력 >> ");
		String pro_id = sc.nextLine();
		System.out.println("========== 비밀번호를 입력하세요. ===========");
		System.out.print("입력 >> ");
		String pwd = sc.nextLine();
		stuController.resister(pro_id, pwd);
		outMenu = false;
	}

	public void proResister() {
		System.out.println("==교수 회원가입==");
		System.out.println("교수 번호를 입력하세요.");
		System.out.print("입력 >> ");
		String pro_id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		System.out.print("입력 >> ");
		String pwd = sc.nextLine();
		proController.resister(pro_id, pwd);
		outMenu = false;
	}

////////////////////////////////////////////////////
////////학생모드 기능
	public void applyChangeSubject(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		while (outMenu) {
			System.out.println("================수강신청=================");
			System.out.println("===1.과목조회 2.신청 3.신청 내역 삭제 9.나가기====");
			System.out.print("입력 >> ");
			switch (sc.nextLine()) {
			case "1":
				System.out.println("==과목 조회==");
				System.out.println("1. 전체 과목  2. 전공 과목  3. 교양 과목  9. 나가기");
				System.out.print("입력 >> ");
				switch (sc.nextLine()) {
				case "1":
					subController.showAllSubject();
					break;
				case "2":
					if (!(session.getAttribute("major") instanceof Major)) {
						System.out.println("학과 오류");
						break;
					}
					subController.showMajorSubject(((Major) session.getAttribute("major")).getMajor_number());
					break;
				case "3" : 
					subController.showMajorSubject(0);
					break;
				case "9":
					break;
				}
				break;
			case "2":
				System.out.println("==수강신청==");
				System.out.println("신청할 과목코드 입력");
				System.out.print("입력 >> ");
				String subject_code = sc.nextLine();
				subController.limitCheck(subject_code, id);
				if(!(session.getAttribute("check") instanceof Boolean)) {
					break;
				}
				comSubController.duplicateCheck(id, subject_code, THISTERM);
				if(!(session.getAttribute("check") instanceof Boolean)) {
					break;
				}
				comSubController.scheduleCheck(id, subject_code, THISTERM);
				if(!(session.getAttribute("check") instanceof Boolean)) {
					break;
				}
				comSubController.applySubject(subject_code, id, THISTERM);
				break;
			case "3":
				System.out.println("==수강내역 삭제==");
				comSubController.showMyApplyInfo(id, THISTERM);
				if(!(session.getAttribute("check") instanceof Boolean)) {
					break;
				}
				
				System.out.println("삭제할 과목번호 입력");
				System.out.print("입력 >> ");
				comSubController.deleteApply(sc.nextLine(), id);
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("잘못된 번호 입력");
			}
		}
		outMenu = true;
	}

	public void showGrade(String id) {
		System.out.println("==성적 조회==");
		System.out.println("1. 전체 성적  2. 학기별 성적  9. 나가기");
		System.out.print("입력 >> ");
		switch (sc.nextLine()) {
		case "1":
			comSubController.showAllGrade(id);
			break;
		case "2":
			System.out.println("조회할 학기 입력(\"YYYY-S\" YYYY는 년도, S는 학기)");
			System.out.print("입력 >> ");
			String term = sc.nextLine();
			comSubController.showGradeByTerm(id, term);
			break;
		case "9":
			break;
		default : 
			System.out.println("잘못된 번호입력, 상위 메뉴로 나갑니다.");
		}
	}

	public void showPersonalInfo(String id) {
		System.out.println("==개인정보 조회==");
		stuController.showPersonalInfo(id);
	}

	public void showStudentSchedule(String id) {
		System.out.println("==시간표 조회==");
		comSubController.showSchedule(id, THISTERM);
	}

	/////////////////////////////////////////////////
	/// 교수모드 기능
	public void grantMySubjectStudent(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		System.out.println("==성적 부여==");

		while (outMenu) {
			System.out.println("1. 맡은 강의 목록  2. 강의 출석부  3. 학생 성적 부여  9. 나가기");
			System.out.print("입력 >> ");
			switch (sc.nextLine()) {
			case "1":
				proSubController.showMySubject(id);
				break;
			case "2":
				System.out.println("출석부를 확인할 과목코드를 입력하세요.");
				System.out.print("입력 >> ");
				String subject_code = sc.nextLine();
				proSubController.isMySubject(id, subject_code);

				if (session.getAttribute("subject") == null) {
					System.out.println("해당과목의 담당교수가 아닙니다.");
					break;
				}
				comSubController.showMySubjectStudent(id, subject_code, THISTERM);
				break;
			case "3":
				System.out.println("성적 부여할 과목 입력하세요.");
				System.out.print("입력 >> ");
				proSubController.isMySubject(id, sc.nextLine());

				if (session.getAttribute("subject") == null) {
					System.out.println("해당과목의 담당교수가 아닙니다.");
					break;
				}

				System.out.println("성적을 입력할 학생번호 입력하세요.");
				System.out.print("입력 >> ");
				String student_id = sc.nextLine();

				System.out.println("부여할 성적을 입력하세요.");
				System.out.print("입력 >> ");
				String grade = sc.nextLine();

				comSubController.grantGrade(id, student_id, ((Subject)(session.getAttribute("subject"))).getSubject_code(), grade, THISTERM);
				if(!(session.getAttribute("check") instanceof Boolean)) {
					stuController.setGrade(student_id, grade, ((Subject)(session.getAttribute("subject"))).getCredit());
				}
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("잘못된 번호를 입력하셨습니다.");
				break;
			}
		}
		outMenu = true;
	}
}
