package com.cg.uas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.uas.entities.ApplicationBean;
import com.cg.uas.entities.LoginBean;
import com.cg.uas.entities.ParticipantBean;
import com.cg.uas.entities.ProgramOfferedBean;
import com.cg.uas.entities.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.IAdminService;
import com.cg.uas.service.IApplicantService;
import com.cg.uas.service.IMACService;


@Controller
public class UniversityController {
	
	@Autowired
	IApplicantService service;
	
	@Autowired
	IAdminService adminService;
	
	
	@Autowired
	IMACService macservice;
	
	
	
	
	/*******
	 * 
	 * 
	 * Applicant Code & login code
	 * 
	 * 
	 * 
	 */
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session)
	{
		session.setAttribute("user", "notthere");
		ModelAndView model = new ModelAndView("Home");
		
		return model;	
	}
	
	@RequestMapping(value="/index")
	public String showpage()
	{
		
		return "Home";
	}
	@RequestMapping("/showLogin.obj")
	public ModelAndView showlogin()
	{
		ModelAndView mnv=new ModelAndView();
		mnv.addObject("login", new LoginBean());
		mnv.setViewName("Login");
		return mnv;
	}
	@RequestMapping("/checkLogin")
	public ModelAndView checkLogin(@ModelAttribute("login") @Valid LoginBean l,BindingResult result,Model model)  throws UniversityException   
	{
		String role=service.checkuser(l);

		if(role==null)
		{
		
			return new ModelAndView("error","message","USER NOT FOUND");
		}
		else 
		{
			if("admin".equals(role))
			{

				
		
				return new ModelAndView("AdminHome","user","admin");
				
				
			}
				else if("mac".equals(role))
				{
		
					return new ModelAndView("MACHome","user","mac");
				}
				else
					return new ModelAndView("error","message","USER NOT FOUND");	
		}
	
}
	
	@RequestMapping(value="/adminHome")
	public String getAdminHome(){
		
		return "AdminHome";
	}
	
	
	@RequestMapping("/showApplicant")
	public String showApplicant(){
		return "ApplicantHome";
	}
	@RequestMapping("/showCheckStatus")
	public ModelAndView showCheckStatus(){
		ModelAndView mnv=new ModelAndView();
		mnv.addObject("applicant", new ApplicationBean());
		mnv.setViewName("viewStatus");
		return mnv;
	}
	@RequestMapping("/viewStatusById")
	public ModelAndView viewStatusById(@RequestParam("applicationId") int appId) throws UniversityException{

		ModelAndView model=new ModelAndView();
		ApplicationBean applicant=null;
		try{applicant=service.viewStatusById(appId);
		if(applicant==null)
		{
			
			model.setViewName("error");
			model.addObject("message","SOMETHING WENT WRONG");
		}
		else
		{
			
			model.setViewName("viewStatusById");
			model.addObject("applicant", applicant);
		}
		
	} catch (UniversityException e) {
		
		model.setViewName("error");
		model.addObject("message", e.getMessage());
	}
	
	return model;

	}
	@RequestMapping("/showApplyOnline.obj")
	public ModelAndView showApplyOnline() throws UniversityException{
		ModelAndView mnv=new ModelAndView();
		List<ProgramScheduledBean> list;
		list=service.viewAllScheduledProgram();
		ArrayList<String> ids=new ArrayList<String>();
		for (ProgramScheduledBean s:list){
			ids.add(s.getScheduledProgramID());
		
		}
		mnv.addObject("ids",ids);
		mnv.setViewName("ApplyOnline");
		mnv.addObject("applicant", new ApplicationBean());
		return mnv;
	}
	
	@RequestMapping("/addApplicant.obj")
	public ModelAndView addApplicant(@ModelAttribute("applicant") ApplicationBean applicant,BindingResult result) throws UniversityException{
		ModelAndView model = new ModelAndView();
		applicant=service.addApplicant(applicant);
		
		if(applicant!=null)
			model.setViewName("successApplicant");
		else{
			model.setViewName("error");
			model.addObject("message", "NO RECORDS FOUND");
		}
		return model;
		
	}
	@RequestMapping("/showAllScheduledProgram.obj")
	public ModelAndView showAllScheduledProgram(){
	ModelAndView model=new ModelAndView();
	List<ProgramScheduledBean> list;
	try{
	list=service.viewAllScheduledProgram();
	if(list.size()<1)
	{
		model.setViewName("error");
		model.addObject("message", "NO RECORDS FOUND");
	}
	else
	{
		model.setViewName("showAllScheduledProgram");
		
		model.addObject("scheduleList",list );
	}
	
} catch (UniversityException e) {
	model.setViewName("error");
	model.addObject("message", e.getMessage());
}

return model;
	}
	
		

	/*
	 * Apply for the program
	 */
	@RequestMapping("/showApplyOnline")
	public ModelAndView applyprogram(){
		ModelAndView mnv=new ModelAndView();
		mnv.addObject("applicant", new ApplicationBean());
		mnv.setViewName("ApplyOnline");
		return mnv;
	}
	
	@RequestMapping("/checkRegister")
	public ModelAndView registerApplicant(@ModelAttribute ("applicant") @Valid ApplicationBean applicant,BindingResult result) throws UniversityException{
		ModelAndView mnv=new ModelAndView();
	
		if(result.hasErrors()){
			
			mnv.addObject("applicant", applicant);
			mnv.setViewName("ApplyOnline");
		}
		else{
			try {
				applicant=service.addApplicant(applicant);
				mnv.setViewName("successApplicant");// add the applicant object to the success page
			} catch (UniversityException e) {
				
				mnv.setViewName("error");
				mnv.addObject("message", e.getMessage());
			}
			
				
		}
		
		return mnv;
	}
	
/*******************************************************************************************************************************/	
	
	

	/*
	 * admin controller code
	 * 
	 * 
	 * 
	 */
	
	@RequestMapping(value="/addProgramsOffered")
	public String getAddProgramsOfferedPage(Model model){
		
		model.addAttribute("programOfferedBean",new ProgramOfferedBean());
		return "addProgramsOffered";
	}
	
	@RequestMapping(value="/validateProgramOfferedDetails")
	public ModelAndView validateProgramOfferedDetails(@ModelAttribute("programOfferedBean")@Valid ProgramOfferedBean programOfferedBean,BindingResult result) throws UniversityException{
		
		ModelAndView modelAndView= new ModelAndView();
		if(result.hasErrors()){
			
			modelAndView.addObject("programOfferedBean", programOfferedBean);
			modelAndView.setViewName("addProgramsOffered");
		}else{
			String programName = adminService.addProgramOffered(programOfferedBean).getProgramName();
			modelAndView.addObject("programName",programName);
			modelAndView.addObject("programOfferedBean", new ProgramOfferedBean());
			modelAndView.setViewName("addProgramsOffered");
			
		}
		
		
		return modelAndView;
	}
	
	
	

	/*@RequestMapping(value="/viewProgramsOffered")
	public String viewProgramsOffered(Model model) throws UniversityException{
		
		List<ProgramOfferedBean> programsOfferedList=adminService.viewProgramsOffered();
		model.addAttribute("programsOfferedList",programsOfferedList);
		return "viewProgramsOffered";
	}*/
	
	
	
	
	
	/*******************************************************************************************************************/
	

	/*
	 * Update/Delete Programs Offered
	 */

	@RequestMapping("/beforeAddSchedule")
	public ModelAndView beforeAddSchedule()
	{
		List<ProgramOfferedBean> list;
		try {
			list = adminService.viewProgramsOffered();
			ModelAndView model;
			if(list.size()<1)
			{
				 model = new ModelAndView("error");
				model.addObject("message", "No records found");
				return model;
			}
			else{

				model = new ModelAndView("beforeAddSchedule");
				model.addObject("list", list);
				return model;
			}
		} catch (UniversityException e) {
			return new ModelAndView("error");
		}
		
				
	}
	
	/*
	 * Update/Delete Programs Offered
	 */

	@RequestMapping("/updateDelete")
	public ModelAndView viewUpdate()
	{
		List<ProgramOfferedBean> list;
		try {
			list = adminService.viewProgramsOffered();
			ModelAndView model;
			if(list.size()<1)
			{
				 model = new ModelAndView("error");
				model.addObject("message", "No records found");
				return model;
			}
			else{

				model = new ModelAndView("updateDelete");
				model.addObject("list", list);
				return model;
			}
		} catch (UniversityException e) {
			return new ModelAndView("error");
		}
		
				
	}
	
	
	
	@RequestMapping("/delete")
	public ModelAndView deleteProgram(@RequestParam("programName") String name)
	{
		
		ModelAndView model= new ModelAndView();
		try {
			boolean offeredFlag= adminService.deleteOffered(name);
			if(offeredFlag==true){
				model.setViewName("deleted");
			}else{
				model.addObject("message", "Cannot be deleted");
				model.setViewName("error");
			}
		} catch (UniversityException e) {
			model = new ModelAndView("error");
			model.addObject("message", e.getMessage());
			
		}
		return model;
		
	}
	
	/*
	 * Finds the offered program based on name which is to be updated
	 */
	@RequestMapping("/update")
	public ModelAndView updateProgram(@RequestParam("programName") String name)
	{
		ModelAndView model;
		try {
			model =  new ModelAndView("updateOffered");
			model.addObject("offered",adminService.findoffered(name) );
			model.addObject("program", new ProgramOfferedBean());
		} catch (UniversityException e) {
			model =  new ModelAndView("error");
			model.addObject("message", e.getMessage());
		}
	return model;
		
	}
	/*
	 * Updates the program from Programs Offered
	 */
	@RequestMapping("/updateOffered")
	public ModelAndView updateofferedProgram(@ModelAttribute("program")@Valid ProgramOfferedBean bean,BindingResult result) throws UniversityException
	{
		ModelAndView modelAndView= new ModelAndView();
		System.out.println("Present in updateOfffered.obj");
		if(result.hasErrors()){
			System.out.println("UPdated values has errors");
			modelAndView.addObject("program", bean);
			modelAndView.setViewName("updateOffered");
			return modelAndView;
		}else{
			System.out.println("Passed the error!!");
			return new ModelAndView("updated","offered",adminService.updateOffered(bean));
			
		}
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	 * gangotry code
	 * 
	 * 
	 * 
	*/
	//Adding Schedule
		@RequestMapping(value = "/prepareAddProgramSchedule.obj")
		public String prepareAddProgramSchedule(@RequestParam("programName")String programName,Model model){
			ProgramScheduledBean psb= new ProgramScheduledBean();
			psb.setProgramName(programName);
			model.addAttribute("programScheduledBean", psb);
			model.addAttribute("programName", programName);
			return "addProgramSchedule";
		}
		
		@RequestMapping(value = "/addProgramSchedule.obj")
		public ModelAndView addProgramSchedule(@ModelAttribute("programScheduledBean")@Valid ProgramScheduledBean programScheduledBean,BindingResult result ) throws UniversityException{
			ModelAndView mnv = new ModelAndView();
			if(result.hasErrors()){
				//mnv.addObject("message", "Error Occured");
				mnv.addObject("programScheduledBean",programScheduledBean );
				mnv.setViewName("addProgramSchedule");
			}else{
				//System.out.println(programScheduledBean.setProgramName(prprogramName));
				ProgramScheduledBean bean  = adminService.addSchedule(programScheduledBean);
				mnv.addObject("programScheduledBean", new ProgramScheduledBean());
				mnv.addObject("programAdded", bean);
				mnv.setViewName("addProgramSchedule");
			}
			
			return mnv;
		}
		
		
		//delete Schedule
		@RequestMapping(value = "/prepareDeleteProgramSchedule.obj")
		public ModelAndView prepareDeleteProgramSchedule(){
			
			List<ProgramScheduledBean> list;
			try {
				list = adminService.viewProgramsScheduled();
				ModelAndView model;
				if(list.size()<1)
				{
					 model = new ModelAndView("error");
					model.addObject("message", "No records found");
					return model;
				}
				else{

					model = new ModelAndView("deleteProgramSchedule");
					model.addObject("programScheduledBean", list);
					return model;
				}
			} catch (UniversityException e) {
				return new ModelAndView("error");
			}
			
			
		}
		
		@RequestMapping(value = "/deleteProgramSchedule.obj")
		public ModelAndView deleteProgramSchedule( @RequestParam("scheduledProgramID") String scheduledProgramID)throws UniversityException{
			
			
			ModelAndView model=new ModelAndView("deletedScheduled");
			try {
				ProgramScheduledBean flagBean= adminService.deleteSchedule(scheduledProgramID);
				if(flagBean==null){
					model.addObject("message", "Cannot be deleted!!");
					model.setViewName("error");
				}else{
					model.setViewName("deletedScheduled");
				}
				
			} catch (UniversityException e) {
				model = new ModelAndView("error");
				model.addObject("message", e.getMessage());
				
			}
			return model;
		}
			
			
			
			/*
			ModelAndView mnv = new ModelAndView();
			if(result.hasErrors()){
				mnv.addObject("message", "Error Occured");
				mnv.setViewName("error");

			}else{
				ProgramScheduledBean bean  = adminService.deleteSchedule(programScheduledBean.getScheduledProgramID());
				mnv.addObject("programScheduledBean", new ProgramScheduledBean());
				mnv.addObject("programDeleted", bean);
				mnv.setViewName("deleteProgramSchedule");
			}
			return mnv;
		}*/
		
		
		//View Schedule
		@RequestMapping(value = "/prepareViewByDateProgramSchedule.obj")
		public String prepareViewByDateProgramSchedule(Model model){
			model.addAttribute("programScheduledBean", new ProgramScheduledBean());
			return "ViewByDateProgramSchedule";
		}
		
		
		@RequestMapping(value = "/viewByDateProgramSchedule.obj")
		public ModelAndView viewByDateSchedule(@ModelAttribute("programScheduledBean")@Valid ProgramScheduledBean programScheduledBean,BindingResult result ) throws UniversityException{
			ModelAndView mnv = new ModelAndView();
			
			if(result.hasErrors()){
				mnv.addObject("programScheduledBean", programScheduledBean);
				mnv.setViewName("ViewByDateProgramSchedule");

			}else{
				Date startDate =   programScheduledBean.getStartDate();
				Date endDate = programScheduledBean.getEndDate();
				List<ProgramScheduledBean> beans  = adminService.viewSchedule(startDate,endDate);
//				for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
//					ProgramScheduledBean programScheduledBean2 = (ProgramScheduledBean) iterator.next();
//					System.out.println(programScheduledBean2.getProgramName());
//				}
				mnv.addObject("programViewByDate", beans);
				mnv.addObject("programScheduledBean", new ProgramScheduledBean());
				mnv.setViewName("ViewByDateProgramSchedule");
			}
			return mnv;
		}
	
	
		
	
	
	
	/*******************************************************************************************************************/
	
	
	
	

	/* *******************
	 * 
	 *  
	 *  
	 *  
	 *  MAC MAPPINGS
	 *  
	 *  
	 *  
	 * ******************* 
	 */
	
	
	
	
	// Shows the list of scheduled programs
	
	@RequestMapping("/showScheduledPrograms.obj")
	public ModelAndView showScheduledPrograms() {
	
		ModelAndView model = new ModelAndView();
		List<ProgramScheduledBean> programScheduledList;
		try {
			programScheduledList = macservice.viewAllScheduledPrograms();
			if(programScheduledList.size() < 1) {
				model.setViewName("error");
				model.addObject("message", "NO RECORDS FOUND");
			}
			else {
				model.setViewName("showSchedule");
				model.addObject("programScheduledList", programScheduledList );
			}
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}
	
	
	// Shows the list of applicant details based on programId
	 
	@RequestMapping("/search.obj")
	public ModelAndView applicantsList(@RequestParam("programId") String programId){
		ModelAndView model = new ModelAndView();
		List<ApplicationBean> applicationList;
		try {
			applicationList = macservice.viewApplicant(programId);
			if(applicationList.size() < 1) {
				model.setViewName("error");
				model.addObject("message", "NO RECORDS FOUND");
			}
			else {
				model.setViewName("viewApplicants");
				model.addObject("applicantList", applicationList);
			}
		}
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}
	
	
	// Shows the form to enter interview date

	@RequestMapping("/accept.obj")
	public ModelAndView showInterviewForm(@RequestParam("appId") Integer appId) {
		ModelAndView model = new ModelAndView();	
		
		model.setViewName("interviewDate");
		model.addObject("applicant", appId);
		
		return model;
	}
	
	
	// Shows the date of interview for applicant
	
	@RequestMapping("/interview.obj")
	public ModelAndView interviewDate(@RequestParam("appId") Integer appId, @RequestParam("dateOfInterview") Date date) {
		ModelAndView model = new ModelAndView();
		try {
			macservice.accept(appId);
			macservice.interview(appId, date);
			model.setViewName("MACHome");
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}
	
	
	// Rejects the applicant

	@RequestMapping("/reject.obj")
	public ModelAndView reject(@RequestParam("appId") Integer appId) {
		ModelAndView model = new ModelAndView();
		try {
			macservice.reject(appId);
			model.setViewName("MACHome");
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}
	
	
	// Shows the list of the applicants after interview

	@RequestMapping("/viewAllApplicants.obj")
	public ModelAndView applicantStatus() {
		ModelAndView model = new ModelAndView();
		List<ProgramScheduledBean> programScheduledList;
		System.out.println("xxxxxxxx");
		try {
			programScheduledList = macservice.viewAllScheduledPrograms();
			if(programScheduledList.size() < 1) {
				System.out.println("yyyyyyy");
				model.setViewName("error");
				model.addObject("message", "NO RECORD FOUND");
			}
			else {
				System.out.println("zzzzzzz");
				model.setViewName("ScheduledProgramsAfterInterview");
				model.addObject("programScheduledList", programScheduledList );
			}
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}	
		return model;
	}
	
	
	//Shows the list of the applicant after interview
	 
	@RequestMapping("/afterInterview.obj")
	public ModelAndView afterInterviewStatus(@RequestParam("programId") String programId) {
		ModelAndView model = new ModelAndView();
		List<ApplicationBean> list;
		//System.out.println("aaaaaaaaaa");
		try {
			list = macservice.confirmedApplicants(programId);
			if(list.size() < 1) {
				model.setViewName("error");
				model.addObject("message", "NO RECORD FOUND");
			}
			else {
				model.setViewName("viewApplicantsAfterInterview");
				model.addObject("applicantList", list);
			}
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}
	
	
	// Accepts the applicant for the interview
	  
	@RequestMapping("/confirm.obj")
	public ModelAndView confirm(@RequestParam("appId") Integer appId) {
		ModelAndView model = new ModelAndView();
		try {
			macservice.confirm(appId);
			model.setViewName("MACHome");
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}
	
	
	// Rejects the applicant
	 
	/* @RequestMapping("/rejectAfterInterview.obj")
	public ModelAndView rejectAfterInterview(@RequestParam("appId") Integer appId) {
		ModelAndView model = new ModelAndView();
		try {
			macservice.reject(appId);
			model.setViewName("MACHome");
		} 
		catch (UniversityException e) {
			model.setViewName("error");
			model.addObject("message", e.getMessage());
		}
		return model;
	} */
	
	
	
	// Shows the table with program ID and program name with action to show confirmed students
	
	@RequestMapping("/viewAllConfirmedApplicants.obj")
	public ModelAndView confirmedApplicants() {
		
			ModelAndView model = new ModelAndView();
			List<ProgramScheduledBean> programScheduledList;
			try {
				programScheduledList = macservice.viewAllScheduledPrograms();
				if(programScheduledList.size() < 1) {
					model.setViewName("error");
					model.addObject("message", "NO RECORDS FOUND");
				}
				else {
					model.setViewName("showAllSchedule");
					model.addObject("programScheduledList", programScheduledList );
				}
			} 
			catch (UniversityException e) {
				model.setViewName("error");
				model.addObject("message", e.getMessage());
			}
			return model;
		}
	
	
	
	
	
	// Shows the confirmed list of applicants

	@RequestMapping("/searchConfirmedApplicants")
	public ModelAndView confirmedApplicantsNow(@RequestParam("programId") String programId) {
			ModelAndView model = new ModelAndView();
			List<ParticipantBean> confirmedList;
			try {
				confirmedList = macservice.viewConfirmedApplicants(programId);
				if(confirmedList.size() < 1) {
					model.setViewName("error");
					model.addObject("message", "NO RECORD FOUND");
				}
				else {
					model.setViewName("ConfirmedApplicants");
					model.addObject("confirmedList", confirmedList);
				}
			} 
			catch (UniversityException e) {
				model.setViewName("error");
				model.addObject("message", e.getMessage());
			}	
			return model;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	@RequestMapping(value="/viewApplicantsById")
	public ModelAndView fetchApplicantById(){
		ModelAndView modelAndView= new ModelAndView();
		List<ProgramScheduledBean> programScheduledList;
		try {
			programScheduledList = adminService.viewProgramsScheduled();//           viewAllScheduledPrograms();
			if(programScheduledList.size() < 1) {
				modelAndView.setViewName("error");
				modelAndView.addObject("message", "NO RECORDS FOUND");
			}
			else {
				modelAndView.setViewName("beforeApplicantSchedule");
				modelAndView.addObject("programScheduledList", programScheduledList );
			}
		} 
		catch (UniversityException e) {
			modelAndView.setViewName("error");
			modelAndView.addObject("message", e.getMessage());
		}
		
		
		return modelAndView;
	}
	
	
	
}
