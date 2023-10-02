package fa.training.dao;

import fa.training.dao.enums.EntryTestResults;
import fa.training.dao.impl.CandidateDaoImpl;
import fa.training.dao.impl.EntryTestDaoImpl;
import fa.training.dao.impl.InterviewDaoImpl;
import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import fa.training.entities.Interview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewDaoTest {
    static CandidateDao candidateDao;
    static InterviewDao interviewDao;

    @BeforeAll
    static void beforeAll() {
        candidateDao = new CandidateDaoImpl(Candidate.class);
        interviewDao = new InterviewDaoImpl(Interview.class);
    }
    @Order(1)
    @Test
    public void testInsertInterview() {

        Interview interview1 = Interview.builder()
                .time("06:00:00")
                .date(LocalDate.of(2020, 10, 01))
                .interviewer("Long")
                .comments("ABC")
                .interviewResult(EntryTestResults.PASS)
                .remark("XTZ")
                .candidate(candidateDao.findOne(1))
                .build();

        Interview interview2 = Interview.builder()
                .time("07:00:00")
                .date(LocalDate.of(2020, 10, 01))
                .interviewer("Thanh")
                .comments("ABC")
                .interviewResult(EntryTestResults.PASS)
                .remark("XYZ")
                .candidate(candidateDao.findOne(2))
                .build();

        Interview interview3 = Interview.builder()
                .time("08:00:00")
                .date(LocalDate.of(2020, 10, 03))
                .interviewer("Hung")
                .comments("ABC")
                .interviewResult(EntryTestResults.PASS)
                .remark("XYZ")
                .candidate(candidateDao.findOne(3))
                .build();

        Interview interview4 = Interview.builder()
                .time("09:00:00")
                .date(LocalDate.of(2020, 10, 01))
                .interviewer("Quang")
                .comments("ABC")
                .interviewResult(EntryTestResults.PASS)
                .remark("XYZ")
                .candidate(candidateDao.findOne(4))
                .build();



        assertEquals(1, interviewDao.save(interview1));
        assertEquals(2, interviewDao.save(interview2));
        assertEquals(3, interviewDao.save(interview3));
        assertEquals(4, interviewDao.save(interview4));

    }

    @Order(2)
    @Test
    public void testGetAllInterview(){
        assertEquals(4, interviewDao.getAll().size());
    }

    @Order(3)
    @Test
    public void testGetInterviewByID(){
        assertEquals("PASS", interviewDao.findOne(2).getInterviewResult().toString());
    }

    @Order(4)
    @Test
    public void testUpdateInterview(){

        Interview interview2Update = Interview.builder()
                .interviewId(3)
                .time("10:00:00")
                .date(LocalDate.of(2020, 10, 01))
                .interviewer("Dieu Linh")
                .comments("ABC")
                .interviewResult(EntryTestResults.PASS)
                .remark("XYZ")
                .candidate(candidateDao.findOne(2))
                .build();

        interviewDao.update(interview2Update);
        assertEquals("Dieu Linh", interviewDao.findOne(3).getInterviewer());
    }
}
