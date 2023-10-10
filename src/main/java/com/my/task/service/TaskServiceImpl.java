package com.my.task.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.task.dao.TaskDAO;
import com.my.task.dao.TaskDAOImpl;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;
import com.my.util.PageGroup;

public class TaskServiceImpl implements TaskService {

	private TaskDAO taskDAO;
	private static TaskServiceImpl service=new TaskServiceImpl();

	private TaskServiceImpl() {
		taskDAO=new TaskDAOImpl();
	}

	public static TaskServiceImpl getInstance() {
		return service;
	}
	
	public List<TaskDTO> findMainTaskList(Integer teamNo, String id) throws FindException {
		List<TaskDTO> list=taskDAO.selectMainTaskList(teamNo, id);
		return list;
	}

	@Override
	public PageGroup<TaskDTO> findAllTaskList(Integer teamNo, int currentPage, boolean desc) throws FindException {
		if(currentPage<1) currentPage=1;
		int cntPerPage=10;

		int startRow;
		int endRow;
		endRow=currentPage*cntPerPage;
		startRow=(currentPage-1)*cntPerPage+1;


		List<TaskDTO> list = taskDAO.selectAllTaskList(teamNo, startRow, endRow, desc);
		int totalCnt=taskDAO.selectAllTaskCount(teamNo);
		PageGroup<TaskDTO> pg=new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}

	@Override
	public PageGroup<MemberTaskDTO> findCompleteTaskList(Integer teamNo, String id, int currentPage, boolean desc) throws FindException {
		if(currentPage<1) currentPage=1;
		int cntPerPage=10;

		int startRow;
		int endRow;
		endRow=currentPage*cntPerPage;
		startRow=(currentPage-1)*cntPerPage+1;


		List<MemberTaskDTO> list = taskDAO.selectCompleteTaskList(teamNo, id, startRow, endRow, desc);
		int totalCnt=taskDAO.selectCompleteTaskCount(teamNo, id);
		PageGroup<MemberTaskDTO> pg=new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}

	@Override
	public PageGroup<TaskDTO> findMyTaskList(Integer teamNo, String id, int currentPage, boolean desc) throws FindException {
		if(currentPage<1) currentPage=1;
		int cntPerPage=10;

		int startRow;
		int endRow;
		endRow=currentPage*cntPerPage;
		startRow=(currentPage-1)*cntPerPage+1;


		List<TaskDTO> list = taskDAO.selectMyTaskList(teamNo, id, startRow, endRow, desc);
		int totalCnt=taskDAO.selectMyTaskCount(teamNo, id);
		PageGroup<TaskDTO> pg=new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}

	@Override
	public void ModifyTask(Integer teamNo, String title, String enddate, Integer taskNo) throws ModifyException {
		taskDAO.updateTask(teamNo, title, enddate, taskNo);
	}

	@Override
	public void AddQuizAnswer(Integer teamNo, Integer questionNo, Integer taskNo, int answer) throws AddException {
		taskDAO.insertQuizAnswer(teamNo, questionNo, taskNo, answer);
	}

	@Override
	public void ModifyQuizAnswer(Integer teamNo, Integer questionNo, Integer taskNo, int answer)
			throws ModifyException {
		taskDAO.updateQuizAnswer(teamNo, questionNo, taskNo, answer);
	}

	@Override
	public void removeQuizAnswer(Integer teamNo, Integer questionNo, Integer taskNo) throws RemoveException {
		taskDAO.deleteQuizAnswer(teamNo, questionNo, taskNo);
	}

//	public static void main(String[] args) throws FindException, ModifyException, AddException, RemoveException {
//		TaskServiceImpl t=new TaskServiceImpl();
//		System.out.println("======================\n메인과제리스트");
//		List<TaskDTO> list=t.findMainTaskList(9999, true);
//		for(int i=0;i<list.size();i++) {
//			System.out.print("과제 타이틀 : "+list.get(i).getTitle()+" | ");
//			System.out.print("출제자 : "+list.get(i).getId()+" | ");
//			System.out.print("과제 생성일 : "+list.get(i).getRegDate()+" | ");
//			System.out.println("과제 마감일 : "+list.get(i).getEndDate());
//		}
//
//		System.out.println("======================\n전체과제리스트");
//		PageGroup<TaskDTO> pg=t.findAllTaskList(9999, 1, true);
//		for(int i=0;i<pg.getList().size();i++) {
//			System.out.print("과제 타이틀 : "+pg.getList().get(i).getTitle()+" | ");
//			System.out.print("출제자 : "+pg.getList().get(i).getId()+" | ");
//			System.out.println("과제 생성일 : "+pg.getList().get(i).getRegDate());
//		}
//
//		System.out.println("======================\n완료과제리스트");
//		PageGroup<MemberTaskDTO> pg2=t.findCompleteTaskList(9999, "nwh2023", 1, true);
//		for(int i=0;i<pg2.getList().size();i++) {
//			System.out.print("과제 타이틀 : "+pg2.getList().get(i).getTitle()+" | ");
//			System.out.print("출제자 : "+pg2.getList().get(i).getId()+" | ");
//			System.out.println("과제 제출일 : "+pg2.getList().get(i).getSubmitDate());
//		}
//
//		System.out.println("======================\n출제과제리스트");
//		PageGroup<TaskDTO> pg3=t.findMyTaskList(9999, "khb2023", 1, true);
//		for(int i=0;i<pg3.getList().size();i++) {
//			System.out.print("과제 타이틀 : "+pg3.getList().get(i).getTitle()+" | ");
//			System.out.print("평점 : "+pg3.getList().get(i).getAvgReviewscore()+" | ");
//			System.out.println("과제 생성일 : "+pg3.getList().get(i).getRegDate());
//		}
//
//		System.out.println("======================\n과제출제-과제정보업뎃");
//		t.ModifyTask(9999, "내가만든과제란다", "2023-10-09", 9);
//		System.out.println("======================\n과제출제-답생성");
////		t.AddQuizAnswer(9999, 1, 9, 2);
////		t.AddQuizAnswer(9999, 2, 9, 1);
////		t.AddQuizAnswer(9999, 3, 9, 2);
//		System.out.println("======================\n과제출제-답수정");
////		t.ModifyQuizAnswer(9999, 2, 9, 3);
//		System.out.println("======================\n과제출제-답삭제");
////		t.removeQuizAnswer(9999, 3, 9);
//	}

}
