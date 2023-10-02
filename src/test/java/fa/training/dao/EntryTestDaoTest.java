package fa.training.dao;

import fa.training.dao.enums.EntryTestResults;
import fa.training.dao.impl.CandidateDaoImpl;
import fa.training.dao.impl.EntryTestDaoImpl;
import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class EntryTestDaoTest {

    static CandidateDao candidateDao;
    static EntryTestDao entryTestDao;

    @BeforeAll
    static void beforeAll() {
        candidateDao = new CandidateDaoImpl(Candidate.class);
        entryTestDao = new EntryTestDaoImpl(EntryTest.class);
    }

    @Order(1)
    @Test
    public void testInsertEntryTest() {

        EntryTest entryTest1 = EntryTest.builder()
                .time("06:00:00")
                .date(LocalDate.of(2022, 5, 1))
                .languageValuator("ABCD")
                .languageResult(BigDecimal.valueOf(8))
                .technicalValuator("XYZ")
                .technicalResult(BigDecimal.valueOf(9))
                .result(EntryTestResults.PASS)
                .remark("ABC")
                .entryTestSkill("Java")
                .candidate(candidateDao.findOne(1))
                .build();

        EntryTest entryTest2 = EntryTest.builder()
                .time("07:00:00")
                .date(LocalDate.of(2023, 5, 9))
                .languageValuator("QWER")
                .languageResult(BigDecimal.valueOf(8))
                .technicalValuator("RTYUI")
                .technicalResult(BigDecimal.valueOf(9))
                .result(EntryTestResults.FAIL)
                .remark("ABC")
                .entryTestSkill("Java")
                .candidate(candidateDao.findOne(2))
                .build();

        EntryTest entryTest3 = EntryTest.builder()
                .time("08:00:00")
                .date(LocalDate.of(2020, 10, 01))
                .languageValuator("QWER")
                .languageResult(BigDecimal.valueOf(8))
                .technicalValuator("RTYUI")
                .technicalResult(BigDecimal.valueOf(9))
                .result(EntryTestResults.PASS)
                .remark("ABC")
                .entryTestSkill("Java")
                .candidate(candidateDao.findOne(3))
                .build();

        EntryTest entryTest4 = EntryTest.builder()
                .time("09:00:00")
                .date(LocalDate.of(2020, 10, 01))
                .languageValuator("QWER")
                .languageResult(BigDecimal.valueOf(8))
                .technicalValuator("RTYUI")
                .technicalResult(BigDecimal.valueOf(9))
                .result(EntryTestResults.PASS)
                .remark("ABC")
                .entryTestSkill("Java")
                .candidate(candidateDao.findOne(4))
                .build();

        assertEquals(1, entryTestDao.save(entryTest1));
        assertEquals(2, entryTestDao.save(entryTest2));
        assertEquals(3, entryTestDao.save(entryTest3));
        assertEquals(4, entryTestDao.save(entryTest4));

    }

    @Order(2)
    @Test
    public void testGetAllEntryTest(){
        assertEquals(4, entryTestDao.getAll().size());
    }

    @Order(3)
    @Test
    public void testGetEntryTestByID(){
        assertEquals("Java", entryTestDao.findOne(1).getEntryTestSkill());
    }

    @Order(4)
    @Test
    public void testUpdatEnTryTestSucess(){

        EntryTest entryTest4Update = EntryTest.builder()
                .testId(1)
                .time("06:00:00")
                .date(LocalDate.of(2020, 10, 15))
                .languageValuator("QWER")
                .languageResult(BigDecimal.valueOf(8))
                .technicalValuator("RTYUI")
                .technicalResult(BigDecimal.valueOf(9))
                .result(EntryTestResults.PASS)
                .remark("ABC")
                .entryTestSkill("Java")
                .candidate(candidateDao.findOne(1))
                .build();

        entryTestDao.update(entryTest4Update);
        assertEquals("PASS", entryTestDao.findOne(1).getResult().toString());
    }
}
