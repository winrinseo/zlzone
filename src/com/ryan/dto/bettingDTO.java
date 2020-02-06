package com.ryan.dto;

public class bettingDTO {
	private int num;
	private String team1;
	private String team2;
	private int team1_count; //배당
	private int team2_count;
	private String allo1;
	private String allo2;
	private String time;
	
	
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public int getTeam1_count() {
		return team1_count;
	}
	public void setTeam1_count(int team1_count) {
		this.team1_count = team1_count;
	}
	public int getTeam2_count() {
		return team2_count;
	}
	public void setTeam2_count(int team2_count) {
		this.team2_count = team2_count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAllo1() {
		return allo1;
	}
	public void setAllo1(String allo1) {
		this.allo1 = allo1;
	}
	public String getAllo2() {
		return allo2;
	}
	public void setAllo2(String allo2) {
		this.allo2 = allo2;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
