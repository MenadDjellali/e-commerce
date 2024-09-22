package com.quest.etna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.quest.etna.controller.OrderDetailController;
import com.quest.etna.model.OrderDetail;
import com.quest.etna.model.OrderInput;
import com.quest.etna.service.OrderDetailService;
import org.springframework.security.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderDetailController.class)
public class OrderDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDetailService orderDetailService;

    //@Test
    @WithMockUser(roles = "User")
    public void shouldPlaceOrder() throws Exception {
        OrderInput orderInput = new OrderInput();
        boolean isSingleProductCheckout = true;

        mockMvc.perform(post("/placeOrder/" + isSingleProductCheckout)
                .contentType("application/json")
                .content("{\"orderId\": 1, \"product\": \"Product1\", \"quantity\": 2}"))
                .andExpect(status().isOk());

        Mockito.verify(orderDetailService).placeOrder(orderInput, isSingleProductCheckout);
    }

    //@Test
    @WithMockUser(roles = "User")
    public void shouldGetOrderDetails() throws Exception {
        /*List<OrderDetail> orderDetails = Arrays.asList(
                new OrderDetail(1L, "Product1", 2),
                new OrderDetail(2L, "Product2", 3)
        );

        Mockito.when(orderDetailService.getOrderDetails()).thenReturn(orderDetails);

        mockMvc.perform(get("/getOrderDetails"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"product\":\"Product1\",\"quantity\":2},{\"id\":2,\"product\":\"Product2\",\"quantity\":3}]"));
    }

    @Test
    @WithMockUser(roles = "Admin")
    public void shouldGetAllOrderDetails() throws Exception {
        List<OrderDetail> orderDetails = Arrays.asList(
                new OrderDetail(1L, "Product1", 2),
                new OrderDetail(2L, "Product2", 3)
        );

        Mockito.when(orderDetailService.getAllOrderDetails()).thenReturn(orderDetails);

        mockMvc.perform(get("/getAllOrderDetails"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"product\":\"Product1\",\"quantity\":2},{\"id\":2,\"product\":\"Product2\",\"quantity\":3}]"));
    */
    }
}
