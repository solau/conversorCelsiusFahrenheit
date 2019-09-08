package com.grupoacert.conversor.controller.service;

import com.grupoacert.conversor.Enum.EscalaEnum;
import com.grupoacert.conversor.dto.Resultado;
import com.grupoacert.conversor.exception.EscalaDesconhecidaException;
import com.grupoacert.conversor.form.GrausForm;
import com.grupoacert.conversor.model.Historico;
import com.grupoacert.conversor.service.ConversorService;
import com.grupoacert.conversor.service.HistoricoService;
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
@WebMvcTest(ConversorService.class)
public class ConversorServiceTests {

    @Autowired
    private ConversorService conversorService;

    @MockBean
    HistoricoService historicoService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void retornarConversaoCelsiusFahrenheit(){
        GrausForm grausForm = new GrausForm();
        grausForm.setGrau(20D);
        grausForm.setConverterPara(EscalaEnum.FAHRENHEIT);
        Resultado resultadoEsperado = new Resultado(20D,68D);
        Historico historico = new Historico(resultadoEsperado,grausForm.getConverterPara().getDescricao());
        historico.setId(1L);

        when(historicoService.salvar(any(Historico.class)))
                .thenReturn(historico);
        Resultado resultado = conversorService.converter(grausForm);
        assertThat(resultado.getFahrenheit(),equalTo(resultadoEsperado.getFahrenheit()));
        assertThat(resultado.getCelsius(),equalTo(resultadoEsperado.getCelsius()));
    }

    @Test
    public void retornarConversaoFahrenheitCelsius(){
        GrausForm grausForm = new GrausForm();
        grausForm.setGrau(50D);
        grausForm.setConverterPara(EscalaEnum.CELSIUS);
        Resultado resultadoEsperado = new Resultado(10D,50D);
        Historico historico = new Historico(resultadoEsperado,grausForm.getConverterPara().getDescricao());
        historico.setId(1L);

        when(historicoService.salvar(any(Historico.class)))
                .thenReturn(historico);
        Resultado resultado = conversorService.converter(grausForm);
        assertThat(resultado.getFahrenheit(),equalTo(resultadoEsperado.getFahrenheit()));
        assertThat(resultado.getCelsius(),equalTo(resultadoEsperado.getCelsius()));
    }

    public void retornarEscaladesconhecidaException(){

        GrausForm grausForm = new GrausForm();
        grausForm.setGrau(50D);
        grausForm.setConverterPara(EscalaEnum.valueOf("kelvin"));
        Resultado resultadoEsperado = new Resultado(10D,50D);
        Historico historico = new Historico(resultadoEsperado,grausForm.getConverterPara().getDescricao());
        historico.setId(1L);

        when(historicoService.salvar(any(Historico.class)))
                .thenReturn(historico);

        this.expectedException.expect(EscalaDesconhecidaException.class);
        this.expectedException.expectMessage("O valor converterPara é inválido, valores válidos: CELSIUS, FAHRENHEIT");

        this.conversorService.converter(grausForm);

    }
}
