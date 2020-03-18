package kosta.uni.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import kosta.uni.vo.ProfessorSubject;

public class SuccessView {
	public static void successMessage(String message) {
		System.out.println(message);
	}

	/**
	 * ������ ��� ���� ����Ʈ ���
	 */
	public static void printList(List<ProfessorSubject> subjects) {
		// ps���� prof�� ������ 1���� �����ֵ��� ���� �ʿ�
		for (ProfessorSubject ps : subjects) {
			System.out.println(ps);
		}
	}

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
		String[] days = {"��", "ȭ", "��", "��", "��"};

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

	}
}
