package com.recruitment.dao.util;

public class DBConstants {

	/* All table name constants */

	// HR table name
	public static final String TBL_HR = "hr";

	// Applicant table name
	public static final String TBL_APPLICANT = "applicant";

	// Interview table name
	public static final String TBL_INTERVIEW = "interview";

	// Post table name
	public static final String TBL_POST = "post";

	// Skillset table name
	public static final String TBL_SKILLSET = "skillset";

	// Applicant_Apply_Post table name
	public static final String TBL_APPLICANT_APPLY_POST = "applicant_apply_post";

	// Applicant_Skillset table name
	public static final String TBL_APPLICANT_SKILLSET = "applicant_skillset";

	// Post_Skillset table name
	public static final String TBL_POST_SKILLSET = "post_skillset";

	/* All table attribute names */

	// All HR table attributes
	public static final String COL_HR_ID = "hr_id";
	public static final String COL_HR_NAME = "name";
	public static final String COL_DEPARTMENT = "department";
	public static final String COL_CONTACT = "contact";
	public static final String COL_PASSWORD = "password";

	// All Applicant table attributes
	public static final String COL_APPLICANT_ID = "applicant_id";
	public static final String COL_APPLICANT_NAME = "name";
	public static final String COL_EXPERIENCE = "experience";
	public static final String COL_HIGHEST_QUALIFICATION = "highest_qual";
	public static final String COL_EMAIL = "email";

	// All interview table attributes
	public static final String COL_DATE_OF_INTERVIEW = "date_of_interview";
	public static final String COL_DATE_OF_JOINING = "date_of_joining";
	public static final String COL_LOCATION = "location";
	public static final String COL_APPLICANT_RESULT = "applicant_result";
	public static final String COL_JOINING_STATUS = "joining_status";

	// All post table attributes
	public static final String COL_POST_ID = "post_id";
	public static final String COL_POST_NAME = "post_name";
	public static final String COL_NO_OF_POSTS = "no_of_posts";
	public static final String COL_DATE_OF_POST = "date_of_post";
	public static final String COL_STATUS = "status";

	// All skillset table attributes
	public static final String COL_SKILL_ID = "skill_id";
	public static final String COL_SKILL_NAME = "skill_name";

	// All application_apply_post table attributes
	public static final String COL_DATE_OF_APPLY = "apply_date";

}
