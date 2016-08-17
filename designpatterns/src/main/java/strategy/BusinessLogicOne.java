package strategy;

import model.BaseEntity;
import strategy.cdi.BusinessLogicType;

/**
 * Created by gsantos on 14/09/15.
 * Strongly coupling
 * Business logic class, can access many and only DAO class
 */
@BusinessLogicType(BusinessLogicType.LogicType.ONE)
public class BusinessLogicOne implements BusinessLogic {

    @Override
    public boolean doIt(BaseEntity baseEntity) {
        // any algorithm that doing something logic
        return (baseEntity.getId() == 1 ? true : false);
    }
}
