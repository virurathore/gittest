package org.retailmanager.init;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.retailmanager.api.RetailsManagerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class BaseTestController {
  protected static final Logger logger = LoggerFactory.getLogger(BaseTestController.class);

  protected MockMvc mvc;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  protected void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  protected void setUp(RetailsManagerController controller) {
    mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  protected String mapToJson(Object obj) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(obj);
  }

  protected <T> T mapFromJson(String json, Class<T> clazz)
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, clazz);
  }

  boolean isEmptyString(String str) {
    if (str == null || str.trim().isEmpty()) {
      return true;
    }
    return false;
  }

  @Test
  public void contextLoads() {
    logger.info("context loaded successfully");
  }
}
