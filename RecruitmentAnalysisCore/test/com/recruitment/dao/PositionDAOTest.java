package com.recruitment.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.recruitment.model.HumanResource;
import com.recruitment.model.Position;

public class PositionDAOTest {

	@Test
	public void test() {
		Date date = new Date(2017 - 02 - 01);
		HumanResource hr = new HumanResource();
		hr.setHrId(1);
		Position position = new Position("junior java developer", 2, 3, date,
				true);
		position.setHr(hr);
		PositionDAO positionDAO = new PositionDAO();
		List<Position> actualPositions = positionDAO
				.getPositionsByMaxExperience(2);
	}

}
