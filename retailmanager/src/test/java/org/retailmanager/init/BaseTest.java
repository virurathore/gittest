package org.retailmanager.init;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
// @SpringBootTest(classes = Application.class)
public abstract class BaseTest {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
