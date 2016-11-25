package com.fmr.lead.spark;

public class Issue {
	
	private String issueId;
	private String project;
	private String team;
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", project=" + project + ", team=" + team + "]";
	}
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

}
