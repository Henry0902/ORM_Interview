package fa.training.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HibernateUtilsTest {
    @Test
    public void testBuildSessionFactory(){
        Assertions.assertNotNull(HibernateUtils.getSessionFactory());
    }
}
