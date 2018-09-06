package com.cg.uas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.cg.uas.entities.ApplicationBean;
import com.cg.uas.entities.LoginBean;
import com.cg.uas.entities.ProgramOfferedBean;
import com.cg.uas.entities.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;

@Repository
@Transactional

public class ApplicantDaoImpl implements IApplicantDao {
	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public ApplicationBean addApplicant(ApplicationBean applicant)
			throws UniversityException {
		// TODO Auto-generated method stub
		applicant.setStatus("Awaited");
		entityManager.persist(applicant);
		entityManager.flush();
		return applicant;
	}

	@Override
	public ApplicationBean viewStatusById(Integer appid) throws UniversityException {
		// TODO Auto-generated method stub
		ApplicationBean app = entityManager.find(ApplicationBean.class, appid);
		return app;
	}

	@Override
	public String checkuser(LoginBean l) throws UniversityException {
		// TODO Auto-generated method stub
		LoginBean login = entityManager.find(LoginBean.class,l.getUserName());
						
		System.out.println("in dao");
		if(login==null){
			System.out.println("login is null");
			throw new UniversityException("user name doesn't exist");}
		else{
			if(l.getPassword().equals(login.getPassword())){
				return login.getRole();}
				else
					return "null";
				
			}
		}

	@Override
	public List<ProgramScheduledBean> viewAllScheduledProgram()
			throws UniversityException {
		// TODO Auto-generated method stub
		TypedQuery<ProgramScheduledBean> query = entityManager.createQuery("SELECT p from ProgramScheduledBean p", ProgramScheduledBean.class);
		List<ProgramScheduledBean> list = query.getResultList();
		return list;
	}

	
	}


