package br.com.fiap.vocatalk.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.dto.ItemFaturaDTO;
import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.models.ItemFatura;
import br.com.fiap.vocatalk.models.Plano;
import br.com.fiap.vocatalk.models.ServicoAdicional;
import br.com.fiap.vocatalk.repositories.ItemFaturaRepository;
import br.com.fiap.vocatalk.repositories.PlanoRepository;
import br.com.fiap.vocatalk.repositories.ServicoAdicionalRepository;

@Service
public class ItemFaturaService {

    @Autowired
    ItemFaturaRepository itemFaturaRepository;

    @Autowired
    PlanoRepository planoRepository;

    @Autowired
    ServicoAdicionalRepository servicoAdicionalRepository;

    Logger log = LoggerFactory.getLogger(getClass());

    public List<ItemFaturaDTO> getAll() {
        List<ItemFatura> itemFaturas = itemFaturaRepository.findAll();
        return itemFaturas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ItemFaturaDTO create(ItemFaturaDTO itemFaturaDTO) {
        ItemFatura itemFatura = new ItemFatura();
        BeanUtils.copyProperties(itemFaturaDTO, itemFatura);

        Plano plano = planoRepository.findById(itemFaturaDTO.getPlano().getId())
                .orElseThrow(() -> new RestNotFoundException("Plano não encontrado"));

        itemFatura.setStatus('A');
        itemFatura.setAdicionado(LocalDateTime.now());

        List<ServicoAdicional> servicosAdicionais = new ArrayList<>();

        if (itemFaturaDTO.getServicosAdicionais() != null && !itemFaturaDTO.getServicosAdicionais().isEmpty()) {
            for (ServicoAdicional servicoAdicional : itemFaturaDTO.getServicosAdicionais()) {
                ServicoAdicional servicoAdicionalEncontrado = servicoAdicionalRepository
                        .findById(servicoAdicional.getId())
                        .orElseThrow(() -> new RestNotFoundException(
                                "Serviço adicional não encontrado: " + servicoAdicional.getId()));
                servicosAdicionais.add(servicoAdicionalEncontrado);
            }
            itemFatura.setServicosAdicionais(servicosAdicionais);
        }

        log.info("cadastrando servicos: " + servicosAdicionais);
        itemFatura.setPlano(plano);

        ItemFatura itemFaturaSalvo = itemFaturaRepository.save(itemFatura);
        ItemFaturaDTO itemFaturaSalvoDTO = new ItemFaturaDTO();
        BeanUtils.copyProperties(itemFaturaSalvo, itemFaturaSalvoDTO);
        return itemFaturaSalvoDTO;
    }

    public ItemFaturaDTO getById(Long id) {
        ItemFatura itemFatura = itemFaturaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Item da Fatura não encontrado: " + id));
        return convertToDto(itemFatura);
    }

    public ItemFaturaDTO update(Long id, ItemFaturaDTO itemFaturaDTO) {
        ItemFatura itemFatura = itemFaturaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Item da Fatura não encontrado: " + id));

        Plano plano = planoRepository.findById(itemFaturaDTO.getPlano().getId())
                .orElseThrow(() -> new RestNotFoundException("Plano não encontrado"));

        itemFatura.setStatus(itemFaturaDTO.getStatus());
        itemFatura.setAdicionado(LocalDateTime.now());
        itemFatura.setPlano(plano);

        List<ServicoAdicional> servicosAdicionais = new ArrayList<>();
        for (ServicoAdicional servicoAdicional : itemFaturaDTO.getServicosAdicionais()) {
            ServicoAdicional servicoAdicionalEncontrado = servicoAdicionalRepository.findById(servicoAdicional.getId())
                    .orElseThrow(() -> new RestNotFoundException(
                            "Serviço adicional não encontrado: " + servicoAdicional.getId()));
            servicosAdicionais.add(servicoAdicionalEncontrado);
        }
        itemFatura.setServicosAdicionais(servicosAdicionais);

        ItemFatura itemFaturaAtualizado = itemFaturaRepository.save(itemFatura);
        return convertToDto(itemFaturaAtualizado);
    }

    public void deletePorID(Long id) {
        ItemFatura itemFatura = itemFaturaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Item da Fatura não encontrado: " + id));
        itemFaturaRepository.delete(itemFatura);
    }

    private ItemFaturaDTO convertToDto(ItemFatura itemFatura) {
        ItemFaturaDTO itemFaturaDTO = new ItemFaturaDTO();
        itemFaturaDTO.setId(itemFatura.getId());
        itemFaturaDTO.setStatus(itemFatura.getStatus());
        itemFaturaDTO.setAdicionado(itemFatura.getAdicionado());
        itemFaturaDTO.setPlano(itemFatura.getPlano());
        itemFaturaDTO.setServicosAdicionais(itemFatura.getServicosAdicionais());
        return itemFaturaDTO;
    }

}
