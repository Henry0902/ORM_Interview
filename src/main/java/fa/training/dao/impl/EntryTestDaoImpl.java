package fa.training.dao.impl;

import fa.training.dao.BaseDao;
import fa.training.dao.EntryTestDao;
import fa.training.entities.EntryTest;

public class EntryTestDaoImpl extends BaseDaoImpl<EntryTest,Integer> implements EntryTestDao {

    public EntryTestDaoImpl(Class<EntryTest> classType) {
        super(classType);
    }
}
