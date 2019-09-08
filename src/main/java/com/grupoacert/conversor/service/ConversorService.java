package com.grupoacert.conversor.service;

import com.grupoacert.conversor.Enum.EscalaEnum;
import com.grupoacert.conversor.dto.Resultado;
import com.grupoacert.conversor.exception.ConversorException;
import com.grupoacert.conversor.exception.EscalaDesconhecidaException;
import com.grupoacert.conversor.form.GrausForm;
import com.grupoacert.conversor.model.Historico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversorService {

    private static final String MENSAGEM_ESCALA_DESCONHECIDA = "O valor converterPara é inválido, valores válidos: CELSIUS, FAHRENHEIT";
    private static final Integer TRINTA_E_DOIS = 32;
    private static final Integer NOVE = 9;
    private static final Integer CINCO = 5;

    @Autowired
    HistoricoService historicoService;

    public Resultado converter(GrausForm grausForm){
        Resultado resultado = null;
        if(grausForm.getConverterPara().getDescricao().equals(EscalaEnum.FAHRENHEIT.getDescricao())){
            resultado = converterCelsiusFahrenheit(grausForm.getGrau());
        }else if(grausForm.getConverterPara().getDescricao().equals(EscalaEnum.CELSIUS.getDescricao())){
            resultado = converterFahrenheitCelsius(grausForm.getGrau());
        }else{
            throw new EscalaDesconhecidaException(MENSAGEM_ESCALA_DESCONHECIDA);
        }
        try{
            historicoService.salvar(new Historico(resultado,grausForm.getConverterPara().getDescricao()));
            return resultado;
        }catch (Exception e){
            throw  new ConversorException(e);
        }
    }

    public Resultado converterCelsiusFahrenheit(Double celsius){
        try {
            Double fahrenheit = (celsius * NOVE/CINCO) + TRINTA_E_DOIS;
            return new Resultado(celsius,fahrenheit);
        }catch (Exception e){
            throw new ConversorException(e);
        }
    }

    public Resultado converterFahrenheitCelsius(Double fahrenheit){
        try{
            Double celsius = (fahrenheit - TRINTA_E_DOIS) * CINCO/NOVE;
            return new Resultado(celsius,fahrenheit);
        }catch (Exception e){
            throw new ConversorException(e);
        }
    }
}
