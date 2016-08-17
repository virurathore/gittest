package org.retailmanager.init;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.retailmanager.api.request.ShopRequestVO;
import org.retailmanager.api.response.ShopsResponseVO;
import org.retailmanager.rest.modal.ShopAddress;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RetailManagerControllerTest extends BaseControllerTest {
	// To check context loading or not
	@Ignore
	@Test
	public void contextLoads() {
		System.out.println("Conext loaded");
	}

	@Before
	public void setUp() {
		super.setUp();
	}

	private static final String uri = "/api/shops";

	@Test
	public void testGetShops() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).param("customerLongitude", "432")
				.param("customerLatitude", "941").accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

	}

	@Test
	public void testGetShopsNotFound() throws Exception {

		Long lng = Long.MAX_VALUE;
		Long ltd = Long.MAX_VALUE;

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).param("customerLongitude", Long.toString(lng))
				.param("customerLatitude", Long.toString(ltd)).accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 204", 204, status);
		Assert.assertTrue("failure - expected HTTP response body to be empty", content.trim().length() == 0);

	}

	@Test
	public void testCreateShops() throws Exception {

		ShopRequestVO shopRequestVO = new ShopRequestVO();
		shopRequestVO.setShopName("testShop111");
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber(3456);
		shopAddress.setPostCode("124577");
		shopRequestVO.setShopAddress(shopAddress);
		String inputJson = mapToJson(shopRequestVO);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have avalue", content.trim().length() > 0);

		ShopsResponseVO shopsResponseVO = super.mapFromJson(content, ShopsResponseVO.class);

		Assert.assertNotNull("failure - expected shops not null", shopsResponseVO);
		Assert.assertEquals("failure - expected shopname match", "testShop111", shopsResponseVO.getShopsName());

	}
}
