package fa.training.dao.impl;

import fa.training.dao.InterviewDao;
import fa.training.entities.Interview;

public class InterviewDaoImpl extends BaseDaoImpl<Interview,Integer> implements InterviewDao {
    public InterviewDaoImpl(Class<Interview> interviewClass) {
        super(Interview.class);
    }
}
