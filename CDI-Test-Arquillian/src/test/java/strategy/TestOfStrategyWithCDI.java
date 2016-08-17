package strategy;

import model.Person;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;

/**
 * Created by gsantos on 14/09/15.
 */
@RunWith(Arquillian.class)
public class TestOfStrategyWithCDI {

    @Inject
    StrategyForBusinessLogicService strategyForBusinessLogic;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory().importBuildOutput()
                .as(WebArchive.class);
    }

    @Test
    public void testBusinessLogicWithInjectCDI(){
        Person person1 = new Person(1);
        assertFalse("should be false ", strategyForBusinessLogic.doIt(person1));
    }
}
