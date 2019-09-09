package com.grupoacert.conversor.dto;

import com.grupoacert.conversor.Enum.EscalaEnum;

public class GrausDTO {

    private Double grau;
    private EscalaEnum converterPara;

    public Double getGrau() {
        return grau;
    }

    public void setGrau(Double grau) {
        this.grau = grau;
    }

    public EscalaEnum getConverterPara() {
        return converterPara;
    }

    public void setConverterPara(EscalaEnum converterPara) {
        this.converterPara = converterPara;
    }
}
