package databasemodel;

import javax.persistence.Entity;

@Entity(name = "information_schema.GLOBAL_STATUS")
class DatabaseInfo {
	
	private Integer Uptime;
	
	DatabaseInfo() {	
	}

	public Integer getUptime() {
		return Uptime;
	}

	public void setUptime(Integer uptime) {
		Uptime = uptime;
	}
	
	
	

}
