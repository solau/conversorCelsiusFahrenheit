package com.grupoacert.conversor.controller.service;

import com.grupoacert.conversor.dto.ResultadoDTO;
import com.grupoacert.conversor.model.Historico;
import com.grupoacert.conversor.repository.HistoricoRepository;
import com.grupoacert.conversor.service.impl.HistoricoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(HistoricoServiceImpl.class)
public class HistoricoServiceTest {

    @Autowired
    private HistoricoServiceImpl historicoService;

    @MockBean
    private HistoricoRepository historicoRepository;

    @Test
    public void verificarSalvarHistorico(){
        Historico historico = new Historico(new ResultadoDTO(20D,68D),"celsius");
        historicoService.salvar(historico);
        when(historicoRepository.save(any(Historico.class))).thenReturn(historico);
        verify(historicoRepository).save(any(Historico.class));
    }

}
