package fr.treeptik.cloudunitonitor.model.action;

import fr.treeptik.cloudunitmonitor.model.Server;
import fr.treeptik.cloudunitonitor.model.action.ServerAction;

public class ApacheAction extends ServerAction {

	private static final long serialVersionUID = 1L;

	public ApacheAction(Server parent) {
		super(parent);
	}

	@Override
	public String getServerManagerPath() {
		return "";
	}

	@Override
	public String getServerManagerPort() {
		return "";
	}

	@Override
	public String getServerPort() {
		return "80";
	}

}
