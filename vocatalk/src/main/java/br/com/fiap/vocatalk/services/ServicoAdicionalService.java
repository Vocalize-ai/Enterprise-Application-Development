package br.com.fiap.vocatalk.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.dto.ServicoAdicionalDTO;
import br.com.fiap.vocatalk.models.ServicoAdicional;
import br.com.fiap.vocatalk.repositories.ServicoAdicionalRepository;

@Service
public class ServicoAdicionalService {

    @Autowired
    ServicoAdicionalRepository servicoAdicionalRepository;

    public ServicoAdicionalService(ServicoAdicionalRepository servicoAdicionalRepository) {
        this.servicoAdicionalRepository = servicoAdicionalRepository;
    }

    public ServicoAdicionalDTO criarServicoAdicional(ServicoAdicionalDTO servicoAdicionalDTO) {
        ServicoAdicional servicoAdicional = new ServicoAdicional();
        BeanUtils.copyProperties(servicoAdicionalDTO, servicoAdicional);
        ServicoAdicional servicoAdicionalSalvo = servicoAdicionalRepository.save(servicoAdicional);
        ServicoAdicionalDTO servicoAdicionalSalvoDTO = new ServicoAdicionalDTO();
        BeanUtils.copyProperties(servicoAdicionalSalvo, servicoAdicionalSalvoDTO);
        return servicoAdicionalSalvoDTO;
    }

}
