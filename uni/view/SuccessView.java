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
	 * ������ ��� ���� ����Ʈ ���
	 */
	public static void printList(List<ProfessorSubject> subjects) {
		String profName = subjects.get(0).getProfessor().getName();
		System.out.println("====" + profName + "���� ���� ���=====");
		for (ProfessorSubject ps : subjects) {
			System.out.println(ps.getSubject().getSubject_name() + "(" + ps.getSubject().getSubject_code() + ")");
		}
		System.out.println();
	}

	/**
	 * �ð�ǥ ���
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
		String[] days = { "��", "ȭ", "��", "��", "��" };

		System.out.println("=======�ð�ǥ========");
		for (ProfessorSubject ps : subjects) {
			String start = ps.getSubject().getStart_time();// ��12
			int time = Integer.parseInt(start.substring(1));// 12
			String day = start.substring(0, 1); // ��

			String message = ps.getSubject().getSubject_name() + "/" + time + "~"
					+ (time + ps.getSubject().getRun_time());
			switch (day) {
			case "��":
				monList.put(time, message);
				break;
			case "ȭ":
				tuesList.put(time, message);
				break;
			case "��":
				wendList.put(time, message);
				break;
			case "��":
				thurList.put(time, message);
				break;
			case "��":
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
	 * subjects����Ʈ�� ��� ���� ��� ���
	 * 
	 * @param subjects
	 */
	public static void printAllSubject(List<Subject> list) {

		System.out.println(

				"================================================================================================");

		System.out.println("�ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١� �� ü �� �� �١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١�");

		System.out.println(

				"================================================================================================");

		System.out.println("| �����ڵ�  |��������������������������� | ���� | �г� |�����������С���   ��|�����ð�ǥ����|");

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

					str += "��";

				}

			}

			if (major_name.length() != b) {

				int dcba = b - (major_name.length());

				for (int t = 0; t <= dcba; t++) {

					str2 += "��";

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
	 * �̹��б� ������û ���� Ȯ��
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
	         
	         String show = "�����ڵ� : [" + subject_code + "] ����� : [" +subject_name + "] ���� : [" + Integer.toString(credit) +"]" +
	                     " �ð�ǥ : [" + frontsiganpho + startsiganpho + " ~ " + Integer.toString(backsiganpho) + "]";
	         System.out.println(show);
	      }
	      System.out.println();
	   }

	/**
	 * list�� ������ �л� ����ǥ ���·� ���
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
		System.out.println("�ڡ١ڡ١ڡ١�" + name + "�л�����ǥ�١ڡ١ڡ١ڡ١ڡ�");
		System.out.println("================================================");
		for (CompleteSubject cs : list) {
			String subject_name = cs.getSubject().getSubject_name();
			String grade = cs.getGrade().getGrade_point();
			Double dou = cs.getGrade().getScore();
			int cre = cs.getSubject().getCredit();
			if (subject_name.length() != a) {
				int ab = a - (subject_name.length());
				for (int t = 0; t <= ab; t++) {
					str += "��";
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
		System.out.println("�ؽ�û����:[" + isum + "] ��������:[" + ac + "] �������:[" + avg + "]��");
		System.out.println();
	}

	/**
	 * �б⺰ ���� ��ȸ
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
		System.out.println("�ڡ١ڡ١ڡ١�" + name + "�л�����ǥ�١ڡ١ڡ١ڡ١ڡ�");
		System.out.println("================================================");
		for (CompleteSubject cs : list) {
			String subject_name = cs.getSubject().getSubject_name();
			String grade = cs.getGrade().getGrade_point();
			Double dou = cs.getGrade().getScore();
			int cre = cs.getSubject().getCredit();
			if (subject_name.length() != a) {
				int ab = a - (subject_name.length());
				for (int t = 0; t <= ab; t++) {
					str += "��";
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
		System.out.println("�ؽ�û����:[" + isum + "] ��������:[" + ac + "] �������:[" + avg + "]��");

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
		String[] days = { "��", "ȭ", "��", "��", "��" };

		System.out.println("=======�ð�ǥ========");
		for (CompleteSubject cs : subjects) {
			String start = cs.getSubject().getStart_time();// ��12
			int time = Integer.parseInt(start.substring(1));// 12
			String day = start.substring(0, 1); // ��

			String message = cs.getSubject().getSubject_name() + "/" + time + "~"
					+ (time + cs.getSubject().getRun_time());
			switch (day) {
			case "��":
				monList.put(time, message);
				break;
			case "ȭ":
				tuesList.put(time, message);
				break;
			case "��":
				wendList.put(time, message);
				break;
			case "��":
				thurList.put(time, message);
				break;
			case "��":
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
	 * ������ ��� ���� ���� �л����(�⼮��) �й�, �̸�, �а�, �г� ������ ���
	 * 
	 * @param students
	 */
	public static void printStudentList(List<CompleteSubject> students) {
		CompleteSubject subject = students.get(0);
		String subjectName = subject.getSubject().getSubject_name(); // �����̸�
		String term = subject.getTerm(); // �б�

		System.out.println("=========" + subjectName + " " + term + "�б� �⼮��========");
		for (CompleteSubject cs : students) {
			System.out.println(cs.getStudent().getName() + " | " // �л��̸�
					+ cs.getStudent().getMajor().getMajor_name() + " | " // �а��̸�
					+ cs.getStudent().getStudent_id() + " | " // �й�
					+ cs.getStudent().getClass_level() + "�г�"); // �г�
		}
		System.out.println();
	}// �޼ҵ峡

	/**
	 * �Ż����� ���
	 * 
	 * @param student
	 */
	public static void printPersonalInfo(Student student) {
		int necessary = student.getMajor().getNecessary_grade();
		int accmulated = student.getAccumulated_grade();
		int remained = (necessary - accmulated);
		System.out.println("--�̸�:" + student.getName() + "\n" + "--����:" + student.getMajor().getMajor_name() + "\n"
				+ "--�г�:" + student.getClass_level() + "\n" + "--�̼�����:" + accmulated + "\n" + "--�������� " + remained
				+ "���� ���ҽ��ϴ�.");
		System.out.println();

	}
	
	public static void printMajorSubject(List<Subject> list) {
	      int a = 12;
	      String str = "";
	      String newstartsigan = "";
	      String major_name = list.get(0).getMajor().getMajor_name();
	      String newMajor_name ="";
	      if(major_name.length() <= 2) {
	         newMajor_name = "�١ڡ١� "+major_name+" ";
	      }  else if(major_name.length() <=4) {
	         newMajor_name = "�١� " + major_name + " ";
	      } else {
	         newMajor_name = major_name;
	      }
	      
	      
	      System.out.println("==========================================================================================================");
	      System.out.println("�١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١�" + newMajor_name + "���� �١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�");
	      System.out.println("==========================================================================================================");
	      System.out.println("| "+" �����ڵ� |\t"+" \t"+" �����\t"+"\t      |      "+"����   "+" |    " +"   �г�\t |"+"\t   " +"�ð�ǥ"+" \t |");
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
	                str += "��";
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
