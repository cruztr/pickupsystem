package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.MiniPackage;
import com.tw.apistackbase.service.PackageService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PackageControllerTest {

    @Autowired
    private PackageController packageController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PackageService packageService;

    private MiniPackage miniPackage;

    @BeforeEach
    void setUp() {
        miniPackage = new MiniPackage();
        miniPackage.setId(1);
        miniPackage.setWaybillNumber("123123123");
        miniPackage.setRecipient("11111111");
        miniPackage.setPhone(123456789);
        miniPackage.setStatus("active");
    }

    @Test
    public void name() throws Exception {
        when(packageService.findAll()).thenReturn(Collections.singletonList(miniPackage));
        ResultActions resultActions = mockMvc.perform(get("/packages"));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.waybillNumber", is("123123123")))
                .andExpect(jsonPath("$.recipient", is("11111111")))
                .andExpect(jsonPath("$.phone", is(123456789)))
                .andExpect(jsonPath("$.status", is("active")));
    }
}