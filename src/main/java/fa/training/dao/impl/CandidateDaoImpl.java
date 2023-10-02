package fa.training.dao.impl;

import fa.training.dao.CandidateDao;
import fa.training.dao.enums.EntryTestResults;
import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CandidateDaoImpl extends BaseDaoImpl<Candidate,Integer> implements CandidateDao {
    public CandidateDaoImpl(Class<Candidate> candidateClass) {
        super(Candidate.class);

    }
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public List<Candidate> getCandidateBySkillAndLevel(String Skill, Integer Level) {
        try(Session session = sessionFactory.getCurrentSession()){
            List<Candidate> candidates;
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("from Candidate C " +
                    "where C.skill = :skill and C.level = :Level");
            query.setParameter("skill", Skill);
            query.setParameter("Level", Level);

            //tương tự với executeQuery
            candidates = query.getResultList();
            session.getTransaction().commit();

            return candidates;
        }
    }

    @Override
    public List<Candidate> getCandidateByLanguageAndSkill(String foreignLanguage, String skill) {
        try(Session session = sessionFactory.getCurrentSession()){
            List<Candidate> candidates;
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("from Candidate as C " +
                    "where C.skill = :skill and C.foreignLanguage = :foreignLanguage");
            query.setParameter("skill", skill);
            query.setParameter("foreignLanguage", foreignLanguage);

            //tương tự với executeQuery
            candidates = query.getResultList();
            session.getTransaction().commit();

            return candidates;
        }
    }

    @Override
    public List<Candidate> getCandidateBySkillAndResultEntryTest(String skill, LocalDate entryTestDate, EntryTestResults entryTestResult) {
        List<Candidate> candidates;
        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Query<Candidate> query = session.createQuery("select distinct C from Candidate as C join C.entryTests as E "
                    + "where C.skill = :skill and E.date = :entryTestDate and E.result = :entryTestResult");
            query.setParameter("skill", skill);
            query.setParameter("entryTestDate", entryTestDate);
            query.setParameter("entryTestResult", entryTestResult);

//            Query<Candidate> query = session
//                    .createQuery("SELECT C FROM Candidate C JOIN EntryTest ET ON C.candidateId = ET.candidate WHERE ((ET.entryTestSkill = :skill) AND (ET.result = 'pass') AND (ET.date = :passDate))", Candidate.class);
//
//            query.setParameter("skill", skill);
//            query.setParameter("passDate", LocalDate.parse(passDate, DateTimeFormatter.ofPattern("d-MMM-yyyy")));
//
//            Query<Candidate> query2 = session.createQuery("SELECT C FROM Candidate C WHERE phone LIKE ''", Candidate.class);

            candidates = query.getResultList();
            session.getTransaction().commit();

            return candidates;
        }
    }
        @Override
        public List<Candidate> getCandidatePassInterview(String passDate) {
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();

                CriteriaBuilder cb = session.getCriteriaBuilder();

                CriteriaQuery<Candidate> cq = cb.createQuery(Candidate.class);

                Root<Candidate> cddRoot = cq.from(Candidate.class);

                Root<Interview> itvRoot = cq.from(Interview.class);

                Predicate join = cb.equal(cddRoot.get("candidateId"), itvRoot.get("candidate"));

                Predicate pass = cb.equal(itvRoot.get("interviewResult"), EntryTestResults.PASS);

                Predicate date = cb.equal(itvRoot.get("date"), LocalDate.parse(passDate, DateTimeFormatter.ofPattern("d-MMM-yyyy")));

                cq.select(cddRoot);

                cq.where(cb.and(join, pass, date));

                Query<Candidate> query = session.createQuery(cq);

                List<Candidate> candidates = query.getResultList();

                return candidates;
            }
        }

    @Override
    public void updateRemark() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaUpdate<Candidate> cu = cb.createCriteriaUpdate(Candidate.class);

            Root<Candidate> root = cu.from(Candidate.class);

            cu.set("remark", "inactive");

            cu.where(cb.like(root.get("phone"), "' '"));

            session.createQuery(cu).executeUpdate();
        }
    }

    public List<Candidate> getCandidateWithPagingOperation(int pageNumber, int pageSize) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Candidate> cq = cb.createQuery(Candidate.class);

            Root<Candidate> root = cq.from(Candidate.class);

            cq.select(root);

            Query<Candidate> query = session.createQuery(cq);

            query.setFirstResult((pageNumber - 1) * pageSize);

            query.setMaxResults(pageSize);

            List<Candidate> candidates = query.getResultList();

            return candidates;
        }
    }
}


