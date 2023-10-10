package control;

import com.my.team.service.TeamServiceImpl;

public abstract class TeamController implements Controller {
	
	protected TeamServiceImpl service;

	public TeamController() {
		service = TeamServiceImpl.getInstance();
	}

} // end class
