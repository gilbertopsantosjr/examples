package strategy;

import model.Person;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by gsantos on 14/09/15.
 */
public class TestOfStrategy {

    @Test
    public void testNewBusinessLogic(){

        BusinessLogic logicOne = new BusinessLogicOne();
        BusinessLogic logicTwo = new BusinessLogicTwo();

        Person person1 = new Person(1);
        Person person2 = new Person(3);

        StrategyForBusinessLogicService strategyForBusinessLogic = new StrategyForBusinessLogicService();

        assertTrue("should be true ", strategyForBusinessLogic.doIt(person1, logicOne) );
        assertFalse("should be false", strategyForBusinessLogic.doIt(person2, logicTwo) );
    }


}
