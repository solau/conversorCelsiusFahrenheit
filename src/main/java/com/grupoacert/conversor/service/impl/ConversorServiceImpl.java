package com.grupoacert.conversor.service.impl;

import com.grupoacert.conversor.Enum.EscalaEnum;
import com.grupoacert.conversor.dto.GrausDTO;
import com.grupoacert.conversor.dto.ResultadoDTO;
import com.grupoacert.conversor.exception.ConversorException;
import com.grupoacert.conversor.exception.EscalaDesconhecidaException;
import com.grupoacert.conversor.model.Historico;
import com.grupoacert.conversor.service.ConversorService;
import com.grupoacert.conversor.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversorServiceImpl implements ConversorService {


    @Autowired
    HistoricoService historicoService;

    public ResultadoDTO converter(GrausDTO grausDTO){
        ResultadoDTO resultadoDTO = null;
        if(grausDTO.getConverterPara().getDescricao().equals(EscalaEnum.FAHRENHEIT.getDescricao())){
            resultadoDTO = converterCelsiusFahrenheit(grausDTO.getGrau());
        }else if(grausDTO.getConverterPara().getDescricao().equals(EscalaEnum.CELSIUS.getDescricao())){
            resultadoDTO = converterFahrenheitCelsius(grausDTO.getGrau());
        }else{
            throw new EscalaDesconhecidaException(EscalaEnum.MENSAGEM_ESCALA_DESCONHECIDA);
        }
        try{
            historicoService.salvar(new Historico(resultadoDTO, grausDTO.getConverterPara().getDescricao()));
            return resultadoDTO;
        }catch (Exception e){
            throw  new ConversorException(e);
        }
    }

    private ResultadoDTO converterCelsiusFahrenheit(Double celsius){
        try {
            Double fahrenheit = (celsius * EscalaEnum.NOVE/EscalaEnum.CINCO) + EscalaEnum.TRINTA_E_DOIS;
            return new ResultadoDTO(celsius,fahrenheit);
        }catch (Exception e){
            throw new ConversorException(e);
        }
    }

    private ResultadoDTO converterFahrenheitCelsius(Double fahrenheit){
        try{
            Double celsius = (fahrenheit - EscalaEnum.TRINTA_E_DOIS) * EscalaEnum.CINCO/EscalaEnum.NOVE;
            return new ResultadoDTO(celsius,fahrenheit);
        }catch (Exception e){
            throw new ConversorException(e);
        }
    }
}
