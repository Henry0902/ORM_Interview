package fa.training.dao;

import fa.training.dao.enums.EntryTestResults;
import fa.training.entities.Candidate;

import java.time.LocalDate;
import java.util.List;

public interface CandidateDao extends BaseDao<Candidate,Integer> {
    List<Candidate> getCandidateBySkillAndLevel(String Skill, Integer Level);

    List<Candidate> getCandidateByLanguageAndSkill(String foreignLanguage, String skill);

    List<Candidate> getCandidateBySkillAndResultEntryTest(String skillName, LocalDate entryTestDate, EntryTestResults entryTestResult);

    List<Candidate> getCandidatePassInterview(String passDate);

    public void updateRemark();

    List<Candidate> getCandidateWithPagingOperation(int pageNumber, int pageSize);


}
