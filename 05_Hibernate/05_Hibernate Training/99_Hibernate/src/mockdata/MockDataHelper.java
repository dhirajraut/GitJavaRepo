package mockdata;

import java.util.ArrayList;
import java.util.List;

import entities.Skill;

public class MockDataHelper {
	private static String[] skillRepository = { "Java", "J2EE", "Spring", "Hibernate"};

	public static List<Skill> getSkillList() {
		
		List <Skill> skillList = new ArrayList<Skill>();
		for (int ctr = 0; ctr < skillRepository.length; ctr++) {
			Skill skill = new Skill();
			skill.setName(skillRepository[ctr]);
			skillList.add(skill);
		}
		return skillList;
	}
}
