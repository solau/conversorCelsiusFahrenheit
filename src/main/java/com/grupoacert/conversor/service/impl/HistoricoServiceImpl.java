package com.grupoacert.conversor.service.impl;

import com.grupoacert.conversor.model.Historico;
import com.grupoacert.conversor.repository.HistoricoRepository;
import com.grupoacert.conversor.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoServiceImpl implements HistoricoService {

    @Autowired
    HistoricoRepository historicoRepository;

    public Historico salvar(Historico historico){
        return historicoRepository.save(historico);
    }
}
