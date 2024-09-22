package com.quest.etna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import  org.mockito.Mockito.*;
//import  org.mockito.Mockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quest.etna.model.ImageModel;
import com.quest.etna.model.OrderInput;
import com.quest.etna.model.OrderProductQuantity;
import com.quest.etna.model.Product;
import com.quest.etna.service.OrderDetailService;
import com.quest.etna.service.ProductService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	private OrderDetailService orderDetailService;
	

	@MockBean
	private ProductService productService;

	//@Test
	@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/test-authenticate.sql")
	protected void testAuthenticate() throws Exception {
		this.mockMvc
			.perform(
					MockMvcRequestBuilders.post("/registerNewUser")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\n" +
                            "    \"userName\":\"mocktest\",\n" +
                            "    \"userFirstName\":\"mocktest\",\n" +
                            "    \"userLastName\":\"mocktest\",\n" +
                            "    \"userPassword\": \"test\"\n" +
                            "}")
			)
			.andDo(print())
			.andExpect(status().isOk());
		
		this.mockMvc
		.perform(
				MockMvcRequestBuilders.post("/registerNewUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
                        "    \"userName\":\"mocktest\",\n" +
                        "    \"userFirstName\":\"mocktest\",\n" +
                        "    \"userLastName\":\"mocktest\",\n" +
                        "    \"userPassword\": \"test\"\n" +
                        "}")
		)
		.andDo(print())
		.andExpect(status().isConflict());
				
		//MvcResult result = 
		this.mockMvc
		.perform(
				MockMvcRequestBuilders.post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
                        "    \"userName\":\"mocktest\",\n" +
                        "    \"userPassword\": \"test\"\n" +
                        "}")
		)
		.andExpect(status().isOk()) 
		.andExpect(jsonPath("$.jwtToken").exists())
		.andReturn();
		
	}
	
	
	///////////
	
	//@Test
    //@WithMockUser(roles = "User")
    public void placeOrder_shouldReturnSuccess() throws Exception {
        // create a sample OrderInput object
        OrderInput orderInput = new OrderInput();
        orderInput.setFullName("John Doe");
        orderInput.setFullAddress("123 Main St");
        orderInput.setContactNumber("551234");
        orderInput.setAlternateContactNumber("555678");
        List<OrderProductQuantity> orderProductQuantityList = new ArrayList<>();
        OrderProductQuantity orderProductQuantity = new OrderProductQuantity();
        orderProductQuantity.setProductId(1L);
        orderProductQuantity.setQuantity(2);
        orderProductQuantityList.add(orderProductQuantity);
        orderInput.setOrderProductQuantityList(orderProductQuantityList);
        
        //this.register("tata", "tata", "tata", "tata");
        //String jwtToken = this.getToken("tata", "tata");
        
        /*String jwtToken = Jwts.builder()
                .setSubject("user123")
                .setExpiration(new Date(System.currentTimeMillis() + (3600 * 5)))
                .signWith(SignatureAlgorithm.HS512, "etna_quest")
                .compact();*/
        /*Map<String, Object> claims = new HashMap<>();
        String jwtToken = Jwts.builder()
        		.setClaims(claims)
        		.setSubject("tata")
        		.setIssuedAt(new Date(System.currentTimeMillis()))
        		.setExpiration(new Date(System.currentTimeMillis() + (3600 * 5) * 1000))
        		.signWith(SignatureAlgorithm.HS512, "etna_quest")
        		.compact();*/

       /* System.out.println(jwtToken);
        mockMvc.perform(MockMvcRequestBuilders.post("/placeOrder/"+true)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + jwtToken)
            .content(new ObjectMapper().writeValueAsString(orderInput)))
            .andExpect(status().isOk());
        // verify that the placeOrder method in the OrderDetailService was called with the correct arguments
        verify(orderDetailService).placeOrder(orderInput, true);*/
       Boolean isSingleProductCheckout = true;

        //System.out.println(jwtToken);
        
        MvcResult result = 
        		this.mockMvc
        		.perform(
        				MockMvcRequestBuilders.post("/authenticate")
        				.contentType(MediaType.APPLICATION_JSON)
        				.content("{\n" +
                                "    \"userName\":\"tata\",\n" +
                                "    \"userPassword\": \"tata\"\n" +
                                "}")
        		)
        		.andExpect(status().isOk()) 
        		.andExpect(jsonPath("$.jwtToken").exists())
        		.andReturn();
        
		var mvcResult = result.getResponse();
		var content = mvcResult.getContentAsString();
		var json = new ObjectMapper().readTree(content);
		var token = json.get("jwtToken").asText();
		
        
        mockMvc.perform(
        		MockMvcRequestBuilders.post("/placeOrder/"+isSingleProductCheckout )
        	    	.contentType(MediaType.APPLICATION_JSON)
        	    	.header("Authorization", "Bearer "+token)
        	    	.content(new ObjectMapper().writeValueAsString(orderInput)))
        	    	.andExpect(status().isCreated());
    }

    //@Test
    public void placeOrder_shouldReturnUnauthorized() throws Exception {
        // create a sample OrderInput object
        OrderInput orderInput = new OrderInput();
        orderInput.setFullName("John Doe");
        orderInput.setFullAddress("123 Main St");
        orderInput.setContactNumber("555-1234");
        orderInput.setAlternateContactNumber("555-5678");
        List<OrderProductQuantity> orderProductQuantityList = new ArrayList<>();
        OrderProductQuantity orderProductQuantity = new OrderProductQuantity();
        orderProductQuantity.setProductId(1L);
        orderProductQuantity.setQuantity(2);
        orderProductQuantityList.add(orderProductQuantity);
        orderInput.setOrderProductQuantityList(orderProductQuantityList);

        mockMvc.perform(post("/placeOrder/true")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(orderInput)))
            .andExpect(status().isUnauthorized());
        
        
    }
	
	
	
	
	
	
	
	
	
	//////////////
	
	
	
	
	
	//@Test
	//@WithMockUser(username = "etna", password = "etna", roles = "User")
	protected void testOrderDetail() throws Exception {
		
        //OrderInput orderInput = new OrderInput();
       /* boolean isSingleProductCheckout = true;
        
		this.mockMvc
		.perform(
				MockMvcRequestBuilders.post("/placeOrder/" + isSingleProductCheckout)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
                        "    \"fullName\":\"mocktest\",\n" +
                        "    \"fullAddress\":\"mocktest\",\n" +
                        "    \"contactNumber\":\"0707070707\",\n" +
                        "    \"alternateContactNumber\": \"0707070707\"\n" +
                        "    \"alternateContactNumber\": \"0707070707\"\n" +
                        "}")
		)
		.andDo(print())
		.andExpect(status().isOk());*/
		
		// Create a mock order input
       /* OrderInput orderInput = new OrderInput();
        orderInput.setFullName("John Smith");
        orderInput.setFullAddress("123 Main St");
        orderInput.setContactNumber("555-1234");
        orderInput.setAlternateContactNumber("555-5678");
        OrderProductQuantity orderProductQuantity = new OrderProductQuantity();
        orderProductQuantity.setProductId(1);
        orderProductQuantity.setQuantity(2);
        orderInput.setOrderProductQuantityList(Arrays.asList(orderProductQuantity));*/

        // Mock the service method
        //Mockito.doNothing().when(orderDetailService).placeOrder(orderInput, true);

        // Perform the request and assert the response status
        
        var token = this.getToken("etna", "etna");
        mockMvc.perform(post("/placeOrder/"+true)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content("{\"orderId\": 1, \"product\": \"Product1\", \"quantity\": 2}"))
                //.content("{\"fullName\":\"John Smith\",\"fullAddress\":\"123 Main St\",\"contactNumber\":\"555-1234\",\"alternateContactNumber\":\"555-5678\",\"orderProductQuantityList\":[{\"productId\":1,\"quantity\":2}]}"))
                .andExpect(status().isOk());
    }
	
	  
	  
	  @Test
	  //@WithMockUser(roles = "Admin")
	  public void testAddNewProdu() throws Exception {
	      // Créer un produit et un fichier image de test
		  String token = this.getToken("menad", "menad");
	      Product product = new Product(27, "Test Product", "Description", 10.0, 20.0);
	      MockMultipartFile productJson = new MockMultipartFile("product", "",
	              "application/json", new ObjectMapper().writeValueAsBytes(product));

	      MockMultipartFile imageFile = new MockMultipartFile("imageFile", "test.png",
	              "image/png", new byte[]{});
	      
	      //with user not admin
	      mockMvc.perform(MockMvcRequestBuilders.multipart("/addNewProduct")
	              .file(imageFile)
	              .file(productJson)
	              .header("Authorization", "Bearer ")
	              .contentType(MediaType.MULTIPART_FORM_DATA))
	              .andExpect(status().isUnauthorized());
	      
	      //with user Admin
	      mockMvc.perform(MockMvcRequestBuilders.multipart("/addNewProduct")
	              .file(imageFile)
	              .file(productJson)
	              .header("Authorization", "Bearer "+token)
	              .contentType(MediaType.MULTIPART_FORM_DATA))
	              .andExpect(status().isOk());

	      
	      mockMvc.perform(MockMvcRequestBuilders.get("/getAllProducts")
	              .contentType(MediaType.MULTIPART_FORM_DATA))
	              .andExpect(status().isOk());
	      
	      mockMvc.perform(MockMvcRequestBuilders.get("/getProductDetailsById/3")
	              .contentType(MediaType.MULTIPART_FORM_DATA))
	              .andExpect(status().isOk());
	      
	      mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProductDetails/1")
	    		  .header("Authorization", "Bearer ")
	              .contentType(MediaType.MULTIPART_FORM_DATA))
	              .andExpect(status().isUnauthorized());
	      
	      mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProductDetails/1")
	    		  .header("Authorization", "Bearer "+token)
	              .contentType(MediaType.MULTIPART_FORM_DATA))
	              .andExpect(status().isOk());
	      

	  }

	  		
    
    
    // Register
    protected void register(String userName, String userFirstName, String userLastName, String userPassword) throws Exception {

        this.mockMvc
                .perform(
                		MockMvcRequestBuilders.post("/registerNewUser")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content("{\n" +
                                                  "    \"userName\":\"" + userName + "\",\n" +
                                                  "    \"userFirstName\":\"" + userFirstName + "\",\n" +
                                                  "    \"userLastName\":\"" + userLastName + "\",\n" +
                                                  "    \"userPassword\": \"" + userPassword + "\"\n" +
                                                  "}")
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }
    
    // Get token
    private String getToken(String userName, String userPassword) throws Exception {
    	
    	MvcResult result = this.mockMvc
                .perform(
                		MockMvcRequestBuilders.post("/authenticate")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content("{\n" +
                                                  "    \"userName\":\"" + userName +"\",\n" +
                                                  "    \"userPassword\": \"" + userPassword + "\"\n" +
                                                  "}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwtToken").exists())
                .andReturn();
    	
		var mvcResult = result.getResponse();
		var content = mvcResult.getContentAsString();
		var json = new ObjectMapper().readTree(content);
		var token = json.get("jwtToken").asText();

        return token;
    }

}
