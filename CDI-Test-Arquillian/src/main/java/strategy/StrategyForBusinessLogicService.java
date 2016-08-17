package strategy;

import model.Person;
import strategy.cdi.BusinessLogicType;

import javax.inject.Inject;

/**
 * Created by gsantos on 14/09/15.
 * Service class can access many and only BO class
 */
public class StrategyForBusinessLogicService {

    @Inject
    @BusinessLogicType(BusinessLogicType.LogicType.TWO)
    BusinessLogic logic;

    public boolean doIt(Person person, BusinessLogic businessLogic){
         return businessLogic.doIt(person);
    }

    public boolean doIt(Person person){
        return doIt(person, logic);
    }

}
