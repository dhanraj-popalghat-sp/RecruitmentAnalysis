package com.recruitment.model;
/**
 * SkillSet class is a bean of skill set
 */
public class SkillSet {

	private int skillId;		//id of skill
	private String skillName;	//name of skill

	public SkillSet() {
		super();
	}

	public SkillSet(int skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}

	public SkillSet(String skillName) {
		super();
		this.skillName = skillName;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Override
	public String toString() {
		return "SkillSet [skillId=" + skillId + ", skillName=" + skillName
				+ "]";
	}

}
