package fa.training.dao;

import fa.training.dao.enums.EntryTestResults;
import fa.training.dao.impl.CandidateDaoImpl;
import fa.training.entities.Candidate;
import fa.training.dao.CandidateDao;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CandidateDaoTest {
    static CandidateDao candidateDao;

    @BeforeAll
    static void beforeAll(){

        candidateDao = new CandidateDaoImpl(Candidate.class);
    }

    @Order(1)
    @Test
    public void testSaveCandidateSuccess() {
        Candidate candidate1 = Candidate.builder()
                .fullName("QUYEN")
                .dateOfBirth(LocalDate.of(2001,05,06))
                .gender(1)
                .graduationYear(LocalDate.of(2022,05,04))
                .phone("09879876")
                .email("Quyen@gmail.com")
                .skill("Angluar")
                .foreignLanguage("Japanese")
                .level(2)
                .cv("Developer")
                .allocationStatus(12)
                .remark("ABC")
                .build();
        Candidate candidate2 = Candidate.builder()
                .fullName("Khai")
                .dateOfBirth(LocalDate.of(2001,05,06))
                .gender(0)
                .graduationYear(LocalDate.of(2022,05,04))
                .phone("098876")
                .email("Khai@gmail.com")
                .skill("Java")
                .foreignLanguage("Japanese")
                .level(2)
                .cv("Developer")
                .allocationStatus(12)
                .remark("ABC")
                .build();
        Candidate candidate3 = Candidate.builder()
                .fullName("Tien")
                .dateOfBirth(LocalDate.of(2001,05,06))
                .gender(1)
                .graduationYear(LocalDate.of(2022,05,04))
                .phone("0958776")
                .email("Tien@gmail.com")
                .skill("Angluar")
                .foreignLanguage("Japanese")
                .level(2)
                .cv("Developer")
                .allocationStatus(12)
                .remark("ABC")
                .build();
        Candidate candidate4 = Candidate.builder()
                .fullName("Thuan")
                .dateOfBirth(LocalDate.of(2001,05,06))
                .gender(0)
                .graduationYear(LocalDate.of(2022,05,04))
                .phone("098798")
                .email("Thuan@gmail.com")
                .skill("Python/ML")
                .foreignLanguage("Japanese")
                .level(2)
                .cv("Developer")
                .allocationStatus(12)
                .remark("ABC")
                .build();

        assertEquals(1, candidateDao.save(candidate1));
        assertEquals(2, candidateDao.save(candidate2));
        assertEquals(3, candidateDao.save(candidate3));
        assertEquals(4, candidateDao.save(candidate4));
    }


    @Order(2)
    @Test
    public void testGetAll(){
        assertEquals(4,  candidateDao.getAll().size());
    }

    @Order(3)
    @Test
    public void testGetOne(){
        assertEquals("QUYEN", candidateDao.findOne(1).getFullName());
    }

    @Order(4)
    @Test
    public void testUpdateSuccess(){
        Candidate candidate1Update = Candidate.builder()
                .candidateId(2)
                .fullName("Cao Van Quyen")
                .dateOfBirth(LocalDate.of(2000,01,01))
                .gender(1)
                .graduationYear(LocalDate.of(2021,01,01))
                .phone("123456")
                .email("1@abc")
                .skill("Java")
                .foreignLanguage("Vietnamese")
                .level(4)
                .cv("cv1")
                .allocationStatus(1)
                .remark("ABC")
                .build();
        Candidate candidate2Update = Candidate.builder()
                .candidateId(1)
                .fullName("Cao Van Quyen")
                .dateOfBirth(LocalDate.of(2000,01,01))
                .gender(1)
                .graduationYear(LocalDate.of(2020,01,01))
                .phone("098723456")
                .email("hello@gmail.com")
                .skill("Angluar")
                .foreignLanguage("English")
                .level(2)
                .cv("cv1")
                .allocationStatus(1)
                .remark("ABC")
                .build();

        candidateDao.update(candidate1Update);
        candidateDao.update(candidate2Update);
        assertEquals("2021-01-01", candidateDao.findOne(2).getGraduationYear().toString());
        assertEquals("Angluar", candidateDao.findOne(1).getSkill().toString());

    }

    //disable to test save cinema room detail and seat with room id 1
    @Disabled
    @Order(5)
    @Test
    public void testDeleteSuccess(){
        candidateDao.deleteById(4);
        assertNull(candidateDao.findOne(4));
    }


    @Order(6)
    @Test
    public void testGetCandidateBySkillAndLevelSuccess(){

        assertEquals(2, candidateDao.getCandidateBySkillAndLevel("Angluar", 2).size());
    }

    @Order(6)
    @Test
    public void testGetCandidateByLanguageAndSkillSuccess() {
        assertEquals(1, candidateDao.getCandidateByLanguageAndSkill("Japanese", "Python/ML").size());
    }


    @Order(7)
    @Test
    public void testGetCandidateBySkillAndResultEntryTestSuccess() {
        List<Integer> expectedIds = Arrays.asList(2);
        List<Candidate> candidates =
                candidateDao.getCandidateBySkillAndResultEntryTest("Java", LocalDate.of(2020,10,1), EntryTestResults.PASS);
        List<Integer> actualIds = candidates.stream()
                .map(candidate -> candidate.getCandidateId())
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedIds, actualIds);

        //assertEquals(2, candidateDao.getCandidateBySkillAndResultEntryTest("Java", "1-Oct-2020").size());
    }


    @Order(8)
    @Test
    public void testGetCandidatePassInterviewSuccess() {
        assertEquals(1, candidateDao.getCandidatePassInterview("15-Oct-2020").size());
    }

    @Order(9)
    @Test
    public void testGetCandidateWithPagingOperation() {

        List<Candidate> candidates = candidateDao.getCandidateWithPagingOperation(2, 2);

        assertAll(
                () -> assertEquals(1, candidates.size()),
                () -> assertEquals(4, candidates.get(0).getCandidateId())
        );
    }

}
