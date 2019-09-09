package com.grupoacert.conversor.model;

import com.grupoacert.conversor.dto.ResultadoDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double celsius;
    private Double fahrenheit;
    private String converterPara;
    private Date dataConsulta;

    public Historico(ResultadoDTO resultadoDTO, String converterPara) {
        this.celsius = resultadoDTO.getCelsius();
        this.fahrenheit = resultadoDTO.getFahrenheit();
        this.dataConsulta = new Date();
        this.converterPara = converterPara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCelsius() {
        return celsius;
    }

    public void setCelsius(Double celsius) {
        this.celsius = celsius;
    }

    public Double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(Double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public String getConverterPara() {
        return converterPara;
    }

    public void setConverterPara(String converterPara) {
        this.converterPara = converterPara;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}
