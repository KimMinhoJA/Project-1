package kosta.uni.view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import kosta.uni.vo.CompleteSubject;
import kosta.uni.vo.ProfessorSubject;
import kosta.uni.vo.Student;
import kosta.uni.vo.Subject;

public class SuccessView {
	public static void successMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	/**
	 * 교수의 담당 과목 리스트 출력
	 */
	public static void printList(List<ProfessorSubject> subjects) {
		String profName = subjects.get(0).getProfessor().getName();
		System.out.println("====" + profName + "님의 강의 목록=====");
		for (ProfessorSubject ps : subjects) {
			System.out.println(ps.getSubject().getSubject_name() + "(" + ps.getSubject().getSubject_code() + ")");
		}
		System.out.println();
	}

	/**
	 * 시간표 출력
	 * 
	 * @param subjects
	 */
	public static void printSchedule(List<ProfessorSubject> subjects) {
		Map<Integer, Map<Integer, String>> schedule = new TreeMap<Integer, Map<Integer, String>>();
		Map<Integer, String> monList = new TreeMap<Integer, String>();
		Map<Integer, String> tuesList = new TreeMap<Integer, String>();
		Map<Integer, String> wendList = new TreeMap<Integer, String>();
		Map<Integer, String> thurList = new TreeMap<Integer, String>();
		Map<Integer, String> friList = new TreeMap<Integer, String>();
		schedule.put(0, monList);
		schedule.put(1, tuesList);
		schedule.put(2, wendList);
		schedule.put(3, thurList);
		schedule.put(4, friList);
		String[] days = { "월", "화", "수", "목", "금" };

		System.out.println("=======시간표========");
		for (ProfessorSubject ps : subjects) {
			String start = ps.getSubject().getStart_time();// 월12
			int time = Integer.parseInt(start.substring(1));// 12
			String day = start.substring(0, 1); // 월

			String message = ps.getSubject().getSubject_name() + "/" + time + "~"
					+ (time + ps.getSubject().getRun_time());
			switch (day) {
			case "월":
				monList.put(time, message);
				break;
			case "화":
				tuesList.put(time, message);
				break;
			case "수":
				wendList.put(time, message);
				break;
			case "목":
				thurList.put(time, message);
				break;
			case "금":
				friList.put(time, message);
				break;
			}

		}

		for (int d : schedule.keySet()) {
			System.out.print(days[d] + " : ");
			for (int da : schedule.get(d).keySet()) {
				String message = schedule.get(d).get(da);
				System.out.printf("%-20s", message);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * subjects리스트의 모든 강의 목록 출력
	 * 
	 * @param subjects
	 */
	public static void printAllSubject(List<Subject> list) {

		System.out.println(

				"================================================================================================");

		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★ 전 체 과 목 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");

		System.out.println(

				"================================================================================================");

		System.out.println("| 과목코드  |　　　과목명　　　　　　　　　 | 학점 | 학년 |　　전공구분　　   　|　　시간표　　|");

		int a = 12;

		int b = 7;

		String str = "";

		String str2 = "";

		String newstartsigan = "";

		for (Subject subject : list) {

			String subject_code = subject.getSubject_code();

			String subject_name = subject.getSubject_name();

			int credit = subject.getCredit();

			int limit = subject.getLimit();

			String major_name = subject.getMajor().getMajor_name();

			String start_time = subject.getStart_time();

			int run_time = subject.getRun_time();

			if (subject_name.length() != a) {

				int abcd = a - (subject_name.length());

				for (int t = 0; t <= abcd; t++) {

					str += "　";

				}

			}

			if (major_name.length() != b) {

				int dcba = b - (major_name.length());

				for (int t = 0; t <= dcba; t++) {

					str2 += "　";

				}

			}

			String frontsiganpho = start_time.substring(0, 1);

			String startsiganpho = start_time.substring(1);

			int startsigan = Integer.parseInt(startsiganpho);

			int length = (int) (Math.log10(startsigan) + 1);

			if (length == 1) {

				newstartsigan = startsigan + " ";

			} else {

				newstartsigan = startsigan + "";

			}

			int backsiganpho = startsigan + run_time;

			String show = "|  [" + subject_code + "]   |   [" + subject_name + "]" + str + "|  ["

					+ Integer.toString(credit) + "] |  [" + Integer.toString(limit) + "] |  [" + major_name + "]" + str2

					+ " |  [" + frontsiganpho + newstartsigan + " ~ " + Integer.toString(backsiganpho) + "] |";

			System.out.println(

					"================================================================================================");

			System.out.println(show);

			str = "";

			str2 = "";

		}

		System.out.println(
				"================================================================================================");
		System.out.println();
	}

	/**
	 * 이번학기 수강신청 내역 확인
	 * 
	 * @param subjects
	 */
	public static void printApplySubject(List<CompleteSubject> subjects) {
	      for (CompleteSubject subject : subjects) {
	         String subject_code = subject.getSubject().getSubject_code();
	         String subject_name = subject.getSubject().getSubject_name();
	         int credit = subject.getSubject().getCredit();
	         String start_time = subject.getSubject().getStart_time();
	         int run_time = subject.getSubject().getRun_time();
	         
	         String frontsiganpho = start_time.substring(0, 1);
	         String startsiganpho = start_time.substring(1);
	         int backsiganpho = Integer.parseInt(startsiganpho) + run_time;
	         
	         String show = "과목코드 : [" + subject_code + "] 과목명 : [" +subject_name + "] 학점 : [" + Integer.toString(credit) +"]" +
	                     " 시간표 : [" + frontsiganpho + startsiganpho + " ~ " + Integer.toString(backsiganpho) + "]";
	         System.out.println(show);
	      }
	      System.out.println();
	   }

	/**
	 * list의 내용을 학생 성적표 형태로 출력
	 * 
	 * @param list
	 */
	public static void PrintGradeMessage(List<CompleteSubject> list) {
		String name = list.get(0).getStudent().getName();
		int ac = list.get(0).getStudent().getAccumulated_grade();

		int a = 11;
		int isum = 0;
		String str = "";
		Double dsum = 0.0;

		System.out.println("================================================");
		System.out.println("★☆★☆★☆★" + name + "학생성적표☆★☆★☆★☆★☆");
		System.out.println("================================================");
		for (CompleteSubject cs : list) {
			String subject_name = cs.getSubject().getSubject_name();
			String grade = cs.getGrade().getGrade_point();
			Double dou = cs.getGrade().getScore();
			int cre = cs.getSubject().getCredit();
			if (subject_name.length() != a) {
				int ab = a - (subject_name.length());
				for (int t = 0; t <= ab; t++) {
					str += "　";
				}
			}
			dsum = dsum + (dou * (double) cre);
			isum = isum + cre;
			String show = "    [" + subject_name + "]" + str + "            " + grade;
			System.out.println("------------------------------------------------");
			System.out.println(show);
			str = "";
		}
		Double avg = Math.round(dsum * 100 / (double) isum) / 100.0;
		System.out.println("================================================");
		System.out.println("※신청학점:[" + isum + "] 인정학점:[" + ac + "] 총점평균:[" + avg + "]※");
		System.out.println();
	}

	/**
	 * 학기별 성적 조회
	 * 
	 * @param list
	 */
	public static void PrintGradeTermMessage(List<CompleteSubject> list) {
		String name = list.get(0).getStudent().getName();
		int a = 11;
		int isum = 0;
		int ac = 0;
		String str = "";
		Double dsum = 0.0;

		System.out.println("================================================");
		System.out.println("★☆★☆★☆★" + name + "학생성적표☆★☆★☆★☆★☆");
		System.out.println("================================================");
		for (CompleteSubject cs : list) {
			String subject_name = cs.getSubject().getSubject_name();
			String grade = cs.getGrade().getGrade_point();
			Double dou = cs.getGrade().getScore();
			int cre = cs.getSubject().getCredit();
			if (subject_name.length() != a) {
				int ab = a - (subject_name.length());
				for (int t = 0; t <= ab; t++) {
					str += "　";
				}
			}
			dsum = dsum + (dou * (double) cre);
			isum = isum + cre;
			if (dou != 0) {
				ac += cre;
			}

			String show = "    [" + subject_name + "]" + str + "            " + grade;
			System.out.println("------------------------------------------------");
			System.out.println(show);
			str = "";
		}
		Double avg = Math.round(dsum * 100 / isum) / 100.0;
		System.out.println("================================================");
		System.out.println("※신청학점:[" + isum + "] 인정학점:[" + ac + "] 총점평균:[" + avg + "]※");

		System.out.println();
	}

	public static void printMySubjectStudentGrade(List<CompleteSubject> students) {
		for (CompleteSubject student : students) {
			System.out.println(student.getStudent().getStudent_id() + " : " + student.getStudent().getName() + "\t\t"
					+ student.getGrade().getGrade_point());
		}
		System.out.println();
	}

	public static void printStudentSchedule(List<CompleteSubject> subjects) {
		Map<Integer, Map<Integer, String>> schedule = new TreeMap<Integer, Map<Integer, String>>();
		Map<Integer, String> monList = new TreeMap<Integer, String>();
		Map<Integer, String> tuesList = new TreeMap<Integer, String>();
		Map<Integer, String> wendList = new TreeMap<Integer, String>();
		Map<Integer, String> thurList = new TreeMap<Integer, String>();
		Map<Integer, String> friList = new TreeMap<Integer, String>();
		schedule.put(0, monList);
		schedule.put(1, tuesList);
		schedule.put(2, wendList);
		schedule.put(3, thurList);
		schedule.put(4, friList);
		String[] days = { "월", "화", "수", "목", "금" };

		System.out.println("=======시간표========");
		for (CompleteSubject cs : subjects) {
			String start = cs.getSubject().getStart_time();// 월12
			int time = Integer.parseInt(start.substring(1));// 12
			String day = start.substring(0, 1); // 월

			String message = cs.getSubject().getSubject_name() + "/" + time + "~"
					+ (time + cs.getSubject().getRun_time());
			switch (day) {
			case "월":
				monList.put(time, message);
				break;
			case "화":
				tuesList.put(time, message);
				break;
			case "수":
				wendList.put(time, message);
				break;
			case "목":
				thurList.put(time, message);
				break;
			case "금":
				friList.put(time, message);
				break;
			}

		}

		for (int d : schedule.keySet()) {
			System.out.print(days[d] + " : ");
			for (int da : schedule.get(d).keySet()) {
				String message = schedule.get(d).get(da);
				System.out.printf("%-20s", message);
			}
			System.out.println();
		}

		System.out.println();
	}

	/**
	 * 교수의 담당 과목 수강 학생목록(출석부) 학번, 이름, 학과, 학년 정보를 출력
	 * 
	 * @param students
	 */
	public static void printStudentList(List<CompleteSubject> students) {
		CompleteSubject subject = students.get(0);
		String subjectName = subject.getSubject().getSubject_name(); // 과목이름
		String term = subject.getTerm(); // 학기

		System.out.println("=========" + subjectName + " " + term + "학기 출석부========");
		for (CompleteSubject cs : students) {
			System.out.println(cs.getStudent().getName() + " | " // 학생이름
					+ cs.getStudent().getMajor().getMajor_name() + " | " // 학과이름
					+ cs.getStudent().getStudent_id() + " | " // 학번
					+ cs.getStudent().getClass_level() + "학년"); // 학년
		}
		System.out.println();
	}// 메소드끝

	/**
	 * 신상정보 출력
	 * 
	 * @param student
	 */
	public static void printPersonalInfo(Student student) {
		int necessary = student.getMajor().getNecessary_grade();
		int accmulated = student.getAccumulated_grade();
		int remained = (necessary - accmulated);
		System.out.println("--이름:" + student.getName() + "\n" + "--전공:" + student.getMajor().getMajor_name() + "\n"
				+ "--학년:" + student.getClass_level() + "\n" + "--이수학점:" + accmulated + "\n" + "--졸업까지 " + remained
				+ "학점 남았습니다.");
		System.out.println();

	}
	
	public static void printMajorSubject(List<Subject> list) {
	      int a = 12;
	      String str = "";
	      String newstartsigan = "";
	      String major_name = list.get(0).getMajor().getMajor_name();
	      String newMajor_name ="";
	      if(major_name.length() <= 2) {
	         newMajor_name = "☆★☆★ "+major_name+" ";
	      }  else if(major_name.length() <=4) {
	         newMajor_name = "☆★ " + major_name + " ";
	      } else {
	         newMajor_name = major_name;
	      }
	      
	      
	      System.out.println("==========================================================================================================");
	      System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★" + newMajor_name + "과목 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
	      System.out.println("==========================================================================================================");
	      System.out.println("| "+" 과목코드 |\t"+" \t"+" 과목명\t"+"\t      |      "+"학점   "+" |    " +"   학년\t |"+"\t   " +"시간표"+" \t |");
	      newMajor_name = "";      
	      for (Subject sub : list) {
	         String subCode = sub.getSubject_code();
	         String subName = sub.getSubject_name();
	         int subCredit = sub.getCredit();
	         int subLimit = sub.getLimit();
	         String subTime = sub.getStart_time();
	         int subRuntime = sub.getRun_time();
	         
	         if(subName.length() != a) {
	             int ab = a-(subName.length());
	             for(int t=0 ; t<=ab ; t++) {
	                str += "　";
	             }
	         }
	          String frontsiganpho = subTime.substring(0, 1);
	            String startsiganpho = subTime.substring(1);
	            int startsigan = Integer.parseInt(startsiganpho);
	            int length = (int) (Math.log10(startsigan) + 1);
	            if (length == 1) {
	               newstartsigan = startsigan + " ";
	            } else {
	               newstartsigan = startsigan + "";
	            }

	             int backsiganpho = startsigan + subRuntime;
	         System.out.println("==========================================================================================================");
	         String show = "|  ["+subCode+ "]   |\t[" +subName+ "]"+ str +"  |      ["+subCredit +"]     |        ["  + subLimit +"] \t | \t[" 
	                  + frontsiganpho + newstartsigan + " ~ " + Integer.toString(backsiganpho)+ "] \t |";
	         System.out.println(show);
	         str = "";
	      }
	      System.out.println("==========================================================================================================");
	   }


}
