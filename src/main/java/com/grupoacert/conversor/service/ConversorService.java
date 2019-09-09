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


    @Autowired
    HistoricoService historicoService;

    public Resultado converter(GrausForm grausForm){
        Resultado resultado = null;
        if(grausForm.getConverterPara().getDescricao().equals(EscalaEnum.FAHRENHEIT.getDescricao())){
            resultado = converterCelsiusFahrenheit(grausForm.getGrau());
        }else if(grausForm.getConverterPara().getDescricao().equals(EscalaEnum.CELSIUS.getDescricao())){
            resultado = converterFahrenheitCelsius(grausForm.getGrau());
        }else{
            throw new EscalaDesconhecidaException(EscalaEnum.MENSAGEM_ESCALA_DESCONHECIDA);
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
            Double fahrenheit = (celsius * EscalaEnum.NOVE/EscalaEnum.CINCO) + EscalaEnum.TRINTA_E_DOIS;
            return new Resultado(celsius,fahrenheit);
        }catch (Exception e){
            throw new ConversorException(e);
        }
    }

    public Resultado converterFahrenheitCelsius(Double fahrenheit){
        try{
            Double celsius = (fahrenheit - EscalaEnum.TRINTA_E_DOIS) * EscalaEnum.CINCO/EscalaEnum.NOVE;
            return new Resultado(celsius,fahrenheit);
        }catch (Exception e){
            throw new ConversorException(e);
        }
    }
}
