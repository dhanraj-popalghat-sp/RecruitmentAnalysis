/**
 * 
 */
package com.recruitment.services;

import java.util.List;

import com.recruitment.dao.HumanResourceDAO;
import com.recruitment.model.HumanResource;

/**
 * @author User
 *
 */
public class HRServiceImpl implements GenericServiceProvider<HumanResource> {

	HumanResourceDAO humanResourceDAO = new HumanResourceDAO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#add(java.lang.Object)
	 */
	@Override
	public HumanResource add(HumanResource humanResource) {
		return humanResourceDAO.add(humanResource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#delete(int)
	 */
	@Override
	public boolean delete(int hrID) {
		return humanResourceDAO.delete(hrID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#update(java.lang.Object)
	 */
	@Override
	public HumanResource update(HumanResource humanResource) {
		return humanResourceDAO.update(humanResource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieveAll()
	 */
	@Override
	public List<HumanResource> retrieveAll() {
		return humanResourceDAO.retrieveAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieve(int)
	 */
	@Override
	public HumanResource retrieve(int hrID) {
		return humanResourceDAO.retrieve(hrID);
	}

}
