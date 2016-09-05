package org.retailmanager.geocode.restclient;

import org.retailmanager.api.RetailsManagerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Viru.
 *
 */
@Component
@ConfigurationProperties("google")
public class GoogleKey {
  private static Logger logger = LoggerFactory.getLogger(RetailsManagerController.class);
  String key;

  public String getKey() {
    logger.info("google key returning :" + key);
    return key;
  }

  public void setKey(String key) {
    logger.info("google key from properties :" + key);
    this.key = key;
  }
}
