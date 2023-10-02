package fa.training.suite;

import fa.training.dao.CandidateDaoTest;
import fa.training.dao.EntryTestDaoTest;
import fa.training.dao.InterviewDaoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CandidateDaoTest.class, EntryTestDaoTest.class, InterviewDaoTest.class})
public class DaoTestSuite {
}
