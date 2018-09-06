package com.cg.uas.entities;

import java.io.Serializable;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name ="PROGRAMS_SCHEDULED")
public class ProgramScheduledBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	//@NotEmpty(message="Field cannot be empty")
	@Pattern(regexp="[A-Za-z0-9]{1,5}",message="Please enter a valid id")
	@Column(name = "Scheduled_program_id")
	private String scheduledProgramID;
	
	//@NotEmpty(message="Field cannot be empty")
	@Column(name = "ProgramName")
	private String programName;
	
	//@NotEmpty(message="Field cannot be empty")
	@Column(name = "Location")
	private String location;
	
	@Column(name = "start_date")
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	//@NotNull(message="Field cannot be empty")
	@Column(name = "sessions_per_week")
	@Min(value=1,message="min 1")
	@Max(value=6,message="max 6")
	private Integer sessionsPerWeek;
	
	public String getScheduledProgramID() {
		return scheduledProgramID;
	}
	public void setScheduledProgramID(String scheduledProgramID) {
		this.scheduledProgramID = scheduledProgramID;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getSessionsPerWeek() {
		return sessionsPerWeek;
	}
	public void setSessionsPerWeek(Integer sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}
	@Override
	public String toString() {
		return "ProgramScheduledBean [scheduledProgramID=" + scheduledProgramID
				+ ", programName=" + programName + ", location=" + location
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", sessionsPerWeek=" + sessionsPerWeek + "]";
	}

	
}
