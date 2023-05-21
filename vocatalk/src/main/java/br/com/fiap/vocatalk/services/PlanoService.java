package br.com.fiap.vocatalk.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.dto.PlanoDTO;
import br.com.fiap.vocatalk.models.Plano;
import br.com.fiap.vocatalk.repositories.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    PlanoRepository planoRepository;

    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        Plano plano = new Plano();
        BeanUtils.copyProperties(planoDTO, plano);
        Plano planoSalvo = planoRepository.save(plano);
        PlanoDTO planoSalvoDTO = new PlanoDTO();
        BeanUtils.copyProperties(planoSalvo, planoSalvoDTO);
        return planoSalvoDTO;
    }

    // Outros métodos do serviço (atualizar, excluir, buscar por ID, etc.)

}
