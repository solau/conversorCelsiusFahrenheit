package com.grupoacert.conversor.controller.service;

import com.grupoacert.conversor.Enum.EscalaEnum;
import com.grupoacert.conversor.dto.ResultadoDTO;
import com.grupoacert.conversor.exception.EscalaDesconhecidaException;
import com.grupoacert.conversor.dto.GrausDTO;
import com.grupoacert.conversor.model.Historico;
import com.grupoacert.conversor.service.impl.ConversorServiceImpl;
import com.grupoacert.conversor.service.impl.HistoricoServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ConversorServiceImpl.class)
public class ConversorServiceTests {

    @Autowired
    private ConversorServiceImpl conversorService;

    @MockBean
    HistoricoServiceImpl historicoService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void retornarConversaoCelsiusFahrenheit(){
        GrausDTO grausDTO = new GrausDTO();
        grausDTO.setGrau(20D);
        grausDTO.setConverterPara(EscalaEnum.FAHRENHEIT);
        ResultadoDTO resultadoDTOEsperado = new ResultadoDTO(20D,68D);
        Historico historico = new Historico(resultadoDTOEsperado, grausDTO.getConverterPara().getDescricao());
        historico.setId(1L);

        when(historicoService.salvar(any(Historico.class)))
                .thenReturn(historico);
        ResultadoDTO resultadoDTO = conversorService.converter(grausDTO);
        assertThat(resultadoDTO.getFahrenheit(),equalTo(resultadoDTOEsperado.getFahrenheit()));
        assertThat(resultadoDTO.getCelsius(),equalTo(resultadoDTOEsperado.getCelsius()));
    }

    @Test
    public void retornarConversaoFahrenheitCelsius(){
        GrausDTO grausDTO = new GrausDTO();
        grausDTO.setGrau(50D);
        grausDTO.setConverterPara(EscalaEnum.CELSIUS);
        ResultadoDTO resultadoDTOEsperado = new ResultadoDTO(10D,50D);
        Historico historico = new Historico(resultadoDTOEsperado, grausDTO.getConverterPara().getDescricao());
        historico.setId(1L);

        when(historicoService.salvar(any(Historico.class)))
                .thenReturn(historico);
        ResultadoDTO resultadoDTO = conversorService.converter(grausDTO);
        assertThat(resultadoDTO.getFahrenheit(),equalTo(resultadoDTOEsperado.getFahrenheit()));
        assertThat(resultadoDTO.getCelsius(),equalTo(resultadoDTOEsperado.getCelsius()));
    }

    public void retornarEscaladesconhecidaException(){

        GrausDTO grausDTO = new GrausDTO();
        grausDTO.setGrau(50D);
        grausDTO.setConverterPara(EscalaEnum.valueOf("kelvin"));
        ResultadoDTO resultadoDTOEsperado = new ResultadoDTO(10D,50D);
        Historico historico = new Historico(resultadoDTOEsperado, grausDTO.getConverterPara().getDescricao());
        historico.setId(1L);

        when(historicoService.salvar(any(Historico.class)))
                .thenReturn(historico);

        this.expectedException.expect(EscalaDesconhecidaException.class);
        this.expectedException.expectMessage("O valor converterPara é inválido, valores válidos: CELSIUS, FAHRENHEIT");

        this.conversorService.converter(grausDTO);

    }
}
