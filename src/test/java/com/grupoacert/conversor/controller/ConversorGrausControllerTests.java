package com.grupoacert.conversor.controller;

import com.grupoacert.conversor.Enum.EscalaEnum;
import com.grupoacert.conversor.dto.ResultadoDTO;
import com.grupoacert.conversor.dto.GrausDTO;
import com.grupoacert.conversor.service.impl.ConversorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.grupoacert.conversor.controller.utils.TestUtils.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ConversorGrausController.class)
public class ConversorGrausControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversorServiceImpl conversorService;

    @Test
    public void verificarEndPointConversao() throws Exception {
        ResultadoDTO resultadoDTO = new ResultadoDTO(20D,68D);

        when(conversorService.converter(any(GrausDTO.class)))
                .thenReturn(resultadoDTO);


        GrausDTO grausDTO = new GrausDTO();
        grausDTO.setConverterPara(EscalaEnum.FAHRENHEIT);
        grausDTO.setGrau(20D);
        MockHttpServletRequestBuilder request = post("/conversor/graus").
        content(asJsonString(grausDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fahrenheit").value(68D));
    }


}
