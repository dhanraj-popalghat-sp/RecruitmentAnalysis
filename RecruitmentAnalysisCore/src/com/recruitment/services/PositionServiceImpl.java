/**
 * 
 */
package com.recruitment.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.PositionDAO;
import com.recruitment.model.Interview;
import com.recruitment.model.Position;

/**
 * @author User
 *
 */
public class PositionServiceImpl implements GenericServiceProvider<Position> {

	private PositionDAO positionDAO = new PositionDAO();
	private InterviewServiceImpl interviewServiceImpl = new InterviewServiceImpl();
	private static Logger logger = Logger.getLogger(PositionServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#add(java.lang.Object)
	 */
	@Override
	public Position add(Position position) {
		return positionDAO.add(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#delete(int)
	 */
	@Override
	public boolean delete(int positionId) {
		return positionDAO.delete(positionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#update(java.lang.Object)
	 */
	@Override
	public Position update(Position position) {
		return positionDAO.update(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieveAll()
	 */
	@Override
	public List<Position> retrieveAll() {
		return positionDAO.retrieveAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieve(int)
	 */
	@Override
	public Position retrieve(int positionId) {
		return positionDAO.retrieve(positionId);
	}

	public void changePositionStatusFromJoiningStatus() {

		List<Position> positions = positionDAO.retrieveAll();
		List<Interview> getshortlistedApplicantByJoiningStatus = interviewServiceImpl
				.getshortlistedApplicantByJoiningStatus();
		logger.debug(getshortlistedApplicantByJoiningStatus);
		for (Position position : positions) {
			int positionId = position.getPostId();
			int count = 0;
			for (Interview interview : getshortlistedApplicantByJoiningStatus) {
				if (interview.getPosition().getPostId() == positionId) {
					count++;
				} // positionId if
			} // interview for

			if (count == position.getNoOfPosts()) {
				int noOfPosition = position.getNoOfPosts();
				int resultPosition = noOfPosition - count;
				logger.debug(resultPosition);
				position.setNoOfPosts(resultPosition);
				position.setStatus(false);

				positionDAO.update(position);
			} // noOfPosition if
		} // position for
	}

}
