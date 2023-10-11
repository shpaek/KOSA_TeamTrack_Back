package com.my.team.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.notice.dto.NoticeDTO;
import com.my.task.dto.TaskDTO;
import com.my.team.dto.AttendanceDTO;
import com.my.team.dto.SignupTeamDTO;
import com.my.team.dto.TeamDTO;

public class TeamDAOImpl implements TeamDAO {

	private SqlSessionFactory sqlSessionFactory;

	public TeamDAOImpl() {

		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;

		try {

			inputStream = Resources.getResourceAsStream(resource);

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch

	} // constructor

	//	---------------------------------------------------------------------------------

	// 서현웅니

	@Override
	public int selectCount() throws FindException{

		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			int count = session.selectOne("com.my.team.TeamMapper.selectCount");
			return count;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public TeamDTO selectByTeamNo(int teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			TeamDTO team = session.selectOne("com.my.team.TeamMapper.selectByTeamNo", teamNo);
			if(team != null) {
				return team;
			}else {
				throw new FindException("해당하는 팀이 없습니다");
			}
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public TeamDTO selectByTeamName(String teamName) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			TeamDTO team = session.selectOne("com.my.team.TeamMapper.selectByTeamName", teamName);
			if(team != null) {
				return team;
			}else {
				throw new FindException("해당하는 팀이 없습니다");
			}
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TeamDTO> selectByHashtag(String hashtag, int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("hashtag", hashtag);
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectByHashtag", map);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TeamDTO> selectByCondition(String column, int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("column", column);
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectByCondition", map);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void createTeam(Map<String, Object> params) throws AddException {

		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			session.selectOne("com.my.team.TeamMapper.createTeam", params);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateTeam(TeamDTO team) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.team.TeamMapper.updateTeam", team);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deleteTeam(int teamNo) throws RemoveException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.team.TeamMapper.deleteTeam", teamNo);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}

	}


	@Override
	public void deleteHashtag(int teamNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.team.TeamMapper.deleteHashtag", teamNo);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateHashtag(Map<String, Object> params) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			session.update("com.my.team.TeamMapper.insertHashtag", params);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	/*
	@Override
	public void updateViewCnt(int teamNo) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			session.update("com.my.team.TeamMapper.updateViewCnt", teamNo);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	 */

	//	---------------------------------------------------------------------------------

	//워니 침입

	@Override
	public String selectLeaderId(Integer teamNo) throws FindException{
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			String leaderId = session.selectOne("com.my.team.TeamMapper.selectLeaderId", teamNo);
			return leaderId;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		}		
	}

	//	---------------------------------------------------------------------------------

	// 셍나

	// 팀 메인 페이지 - 팀 소개글 보여주기
	@Override
	public String selectTeamInfoByTeamNo(int teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			String selectedTeamInfo = session.selectOne("com.my.team.TeamMapper.selectTeamInfoByTeamNo", teamNo);

			if(selectedTeamInfo != null) {
				return selectedTeamInfo;
			} else {
				throw new FindException("선택하신 팀의 소개글이 존재하지 않습니다.");
			} // if-else
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectByTeamInfo()

	// 팀 메인 페이지 - 팀 공지사항 보여주기
	@Override
	public List<NoticeDTO> selectNoticeListByTeamNo(int teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			List<NoticeDTO> noticeList = session.selectList("com.my.team.TeamMapper.selectNoticeListByNoticeNo", teamNo);

			if(noticeList != null) {
				return noticeList;
			} else {
				throw new FindException("선택하신 팀의 공지사항이 존재하지 않습니다.");
			} // if-else

		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectNoticeListByNoticeNo

	// 팀 메인 페이지 - 팀 가입하기
	@Override
	public void insertSignUpTeam(SignupTeamDTO signupTeamDTO) throws AddException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.insert("com.my.team.TeamMapper.insertIntoSignupTeam", signupTeamDTO);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // insertSignUpTeam()

	// 팀 메인 페이지 - 팀 나가기 #1
	@Override
	public void updateTeamMemberStatusResign(String id) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.update("com.my.team.TeamMapper.updateTeamMemberStatusResign", id);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateTeamMemberStatus()

	// 팀 메인 페이지 - 팀 나가기 #2
	@Override
	public void deleteSignupTeam(String id) throws RemoveException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.delete("com.my.team.TeamMapper.deleteSignupTeam", id);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // deleteSignupTeam()

	// 팀 메인 페이지 -  팀 나가기 -> (두 작업을 하나의 트랜잭션으로 처리)
	@Override
	public void leaveTeam(String id) throws Exception {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			// 팀 나가기 #1
			session.update("com.my.team.TeamMapper.updateTeamMemberStatus", id);

			// 팀 나가기 #2
			session.delete("com.my.team.TeamMapper.deleteSignupTeam", id);

			// 두 작업 모두 성공하면 커밋
			session.commit();

		} catch (Exception e) {
			// 어떤 작업이든 실패하면 롤백
			if (session != null) {
				session.rollback();
			} // if
			throw new Exception("팀 나가기 실패: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // leaveTeam

	// 팀 메인 페이지 - 팀 멤버 출력하기
	@Override
	public List<String> selectNicknameByTeamNo(int teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			List<String> selectedNickname = session.selectList("com.my.team.TeamMapper.selectNicknameByTeamNo", teamNo);

			if (selectedNickname != null) {
				return selectedNickname;
			} else {
				throw new FindException("선택하신 팀의 팀 멤버가 존재하지 않습니다.");
			} // if-else
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectNicknameByTeamNo()

	// 팀 메인 페이지 - 조회수 카운트
	@Override
	public void updateViewCnt(int teamNo) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.update("com.my.team.TeamMapper.updateViewCnt", teamNo);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateTeamMemberStatus()

	// 팀 메인 페이지 - 조회수 출력
	@Override
	public int selectViewCnt(int teamNo) throws FindException {
	    SqlSession session = null;
	    
	    try {
	        session = sqlSessionFactory.openSession();
	        
	        int viewCount = session.selectOne("com.my.team.TeamMapper.selectViewCnt", teamNo);

	        return viewCount;
	    } catch(Exception e) {
	        throw new FindException(e.getMessage());
	    } finally {
	        if(session != null) {
	            session.close();
	        } // if
	    } // try-catch-finally
	} //selectViewCnt()
	
	//	---------------------------

	// 팀 출석부 페이지 - 출석하기
	@Override
	public void insertAttendanceById(Integer teamNo, String id) throws AddException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			Map<String, Object> map = new HashMap<>();

			map.put("teamNo", teamNo);
			map.put("id", id);

			session.update("com.my.team.TeamMapper.insertAttendanceById", map);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // insertAttendance

	// 팀 출석부 페이지 - 출석 내역 조회
	@Override
	public List<AttendanceDTO> selectAttendanceById(Integer teamNo, String id) throws FindException {
		SqlSession session = null;

		List<AttendanceDTO> attendanceList = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession();

			Map<String, Object> map = new HashMap<>();

			map.put("teamNo", teamNo);
			map.put("id", id);

			attendanceList = session.selectList("com.my.team.TeamMapper.selectAttendanceById", map);

			return attendanceList;
		} catch (Exception e) {
			throw new FindException("선택하신 팀에 대한 출석 내역이 존재하지 않습니다.");
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} //selectAttendanceById()

	//	---------------------------

	// 팀 관리 페이지(현재 팀원 관리) - 현재 팀원들 정보 확인 (아이디, 닉네임, 자기소개)
	@Override
	public List<Map<String, Object>> selectMemberInfo(Integer teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			// map 객체에 teamNo 값을 넣어서 전달
			Map<String, Object> map = new HashMap<>();
			map.put("teamNo", teamNo);

			List<Map<String, Object>> selectedMemberInfo = session.selectList("com.my.team.TeamMapper.selectMemberInfo", map);

			return selectedMemberInfo;
		} catch (Exception e) {
			throw new FindException("선택하신 팀에 팀원 내역이 존재하지 않습니다.");
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectMemberInfo()

	// 팀 관리 페이지(현재 팀원 관리) - 팀원 방출
	@Override
	public void updateTeamMemberStatusDismiss(Map<String, Object> map) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.update("com.my.team.TeamMapper.updateTeamMemberStatusDismiss", map);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateTeamMemberStatus()

	// 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 확인
	@Override
	public List<Map<String, Object>> selectRequestInfo(Integer teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			List<Map<String, Object>> requestInfo = session.selectList("com.my.team.TeamMapper.selectRequestInfo", teamNo);

			return requestInfo;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectRequestInfo()

	// 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 승인
	@Override
	public void updateRequestInfoApprove(Map<String, Object> map) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.update("com.my.team.TeamMapper.updateRequestInfoApprove", map);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateRequestInfoApprove()

	// 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 거절
	@Override
	public void updateRequestInfoReject(Map<String, Object> map) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.update("com.my.team.TeamMapper.updateRequestInfoReject", map);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateRequestInfoReject()

	// 팀 관리 페이지(출제자 선정) - 출제자 선정
	@Override
	public void insertExaminer(TaskDTO taskDTO) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			session.insert("com.my.team.TeamMapper.insertExaminer", taskDTO);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // insertExaminer()

//	################ Test ################
	
	public static void main(String[] args) {
		
		// 근데 생각해보면 나 메소드 넘 많은데 이거 테스트 언제 다 해바...? 눈물 줄줄
		
	}
	
} // end class
