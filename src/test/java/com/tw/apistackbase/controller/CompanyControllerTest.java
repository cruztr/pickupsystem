package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repositories.CompanyRepository;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ActiveProfiles(profiles = "test")
public class CompanyControllerTest {
    @Autowired
    CompanyController companyController;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    public void setUp() throws Exception {
        company = new Company();
        company.setId(1L);
        company.setName("Boom");
    }

    @Test
    public void getCompany() throws Exception {
        when(companyRepository.findOneById(1L)).thenReturn(company);
        ResultActions resultActions = mockMvc.perform(get("/companies/1"));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Boom")));
    }

    @Test
    public void testGetCompany() throws Exception {
        when(companyRepository.findByNameContaining("Boo")).thenReturn(company);
        ResultActions resultActions = mockMvc.perform(get("/companies?name=Boo"));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Boom")));
    }

    @Test
    public void add() throws Exception {
        when(companyRepository.save(company)).thenReturn(company);
        ResultActions resultActions = mockMvc.perform(post("/companies")
                .content(asJsonString(company))
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        when(companyRepository.findOneById(1L)).thenReturn(company);
        ResultActions resultActions = mockMvc.perform(delete("/companies/1"));

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        when(companyRepository.findOneById(1L)).thenReturn(null);
        ResultActions resultActions = mockMvc.perform(delete("/companies/1"));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateName() throws Exception {
        when(companyRepository.findOneById(1L)).thenReturn(company);
        ResultActions resultActions = mockMvc.perform(patch("/companies/1")
        .content(asJsonString(company))
        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testUpdateNameNotFound() throws Exception {
        when(companyRepository.findOneById(1L)).thenReturn(null);
        ResultActions resultActions = mockMvc.perform(patch("/companies/1")
                .content(asJsonString(company))
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound()).andDo(print());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}