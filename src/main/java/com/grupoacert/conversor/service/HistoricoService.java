package com.grupoacert.conversor.service;

import com.grupoacert.conversor.model.Historico;
import com.grupoacert.conversor.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    @Autowired
    HistoricoRepository historicoRepository;

    public Historico salvar(Historico historico){
        return historicoRepository.save(historico);
    }
}
