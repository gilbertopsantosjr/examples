package org.mail.box;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mail.box.config.SpringMongoConfig;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringMongoConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadBoxMailTest {

	
}
