package com.grupoacert.conversor.controller;

import com.grupoacert.conversor.Enum.EscalaEnum;
import com.grupoacert.conversor.dto.Resultado;
import com.grupoacert.conversor.form.GrausForm;
import com.grupoacert.conversor.service.ConversorService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ConversorGrausController.class)
public class ConversorGrausControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversorService conversorService;

    @Test
    public void verificarEndPointConversao() throws Exception {
        Resultado resultado = new Resultado(20D,68D);

        when(conversorService.converter(any(GrausForm.class)))
                .thenReturn(resultado);


        GrausForm grausForm = new GrausForm();
        grausForm.setConverterPara(EscalaEnum.FAHRENHEIT);
        grausForm.setGrau(20D);
        MockHttpServletRequestBuilder request = get("/conversor_graus").
        content(asJsonString(grausForm))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fahrenheit").value(68D));
    }


}
