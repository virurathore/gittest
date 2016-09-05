package org.retailmanager.init;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.retailmanager.api.request.ShopRequest;
import org.retailmanager.api.response.ShopsResponse;
import org.retailmanager.rest.modal.ShopAddress;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RetailManagerControllerTest extends BaseTestController {
  private static final String API_URI = "/api/shops";

  @Before
  public void setUp() {
    super.setUp();
  }



  @Test
  public void testGetShopsNotFound() throws Exception {

    Long lng = Long.MAX_VALUE;
    Long ltd = Long.MAX_VALUE;

    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(API_URI).param("customerLongitude", Long.toString(lng))
            .param("customerLatitude", Long.toString(ltd)).accept(MediaType.APPLICATION_JSON))
        .andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP status 204", 204, status);
    Assert.assertTrue("failure - expected HTTP response body to be empty", isEmptyString(content));

  }

  @Test
  public void testCreateShops() throws Exception {

    ShopRequest shopRequest = new ShopRequest();
    shopRequest.setShopName("ags cinema");
    ShopAddress shopAddress = new ShopAddress();
    shopAddress.setNumber(1600);
    shopAddress.setPostCode("603103");
    shopRequest.setShopAddress(shopAddress);
    String inputJson = mapToJson(shopRequest);

    MvcResult result =
        mvc.perform(MockMvcRequestBuilders.post(API_URI).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP status 201", 201, status);
    Assert.assertTrue("failure - expected HTTP response body to have avalue",
        !isEmptyString(content));

    ShopsResponse shopsResponse = super.mapFromJson(content, ShopsResponse.class);

    Assert.assertNotNull("failure - expected shops not null", shopsResponse);
    Assert.assertEquals("failure - expected shopname match", "ags cinema",
        shopsResponse.getShopsName());

  }

  @Test
  public void testGetShops() throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(API_URI).param("customerLongitude", "80.1986649")
            .param("customerLatitude", "12.7949478").accept(MediaType.APPLICATION_JSON))
        .andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

    Assert.assertEquals("failure - expected HTTP status", 200, status);
    Assert.assertTrue("failure - expected HTTP response body to have a value",
        !isEmptyString(content));

  }

}
