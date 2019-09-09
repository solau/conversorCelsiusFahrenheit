package com.grupoacert.conversor.service;

import com.grupoacert.conversor.dto.GrausDTO;
import com.grupoacert.conversor.dto.ResultadoDTO;

public interface ConversorService {

    public ResultadoDTO converter(GrausDTO grausDTO);
}
