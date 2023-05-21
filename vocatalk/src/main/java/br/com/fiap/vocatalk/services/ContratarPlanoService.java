package br.com.fiap.vocatalk.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.dto.ClienteDTO;
import br.com.fiap.vocatalk.dto.ContratarPlanoDTO;
import br.com.fiap.vocatalk.dto.FaturaDTO;
import br.com.fiap.vocatalk.dto.ItemFaturaDTO;
import br.com.fiap.vocatalk.dto.TipoPagamentoDTO;
import br.com.fiap.vocatalk.exceptions.CustomerHasPlanException;
import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.models.Cliente;
import br.com.fiap.vocatalk.models.ItemFatura;
import br.com.fiap.vocatalk.models.Plano;
import br.com.fiap.vocatalk.models.TipoPagamento;
import br.com.fiap.vocatalk.repositories.ClienteRepository;
import br.com.fiap.vocatalk.repositories.PlanoRepository;
import br.com.fiap.vocatalk.repositories.TipoPagamentoRepository;

@Service
public class ContratarPlanoService {

        @Autowired
        ClienteRepository clienteRepository;

        @Autowired
        PlanoRepository planoRepository;

        @Autowired
        FaturaService faturaService;

        @Autowired
        TipoPagamentoRepository tipoPagamentoRepository;

        Logger log = LoggerFactory.getLogger(getClass());

        public FaturaDTO contratarPlano(ContratarPlanoDTO contratarPlanoDTO) {
                Long clienteId = contratarPlanoDTO.getCliente();
                Long planoId = contratarPlanoDTO.getPlano();
                Long tipoPagamentoId = contratarPlanoDTO.getTipoPagamento();

                Cliente cliente = clienteRepository.findById(clienteId)
                                .orElseThrow(() -> new RestNotFoundException("Cliente não encontrado"));

                if (!cliente.getFatura().isEmpty()) {
                        throw new CustomerHasPlanException("Cliente já tem um plano");
                }

                Plano plano = planoRepository.findById(planoId)
                                .orElseThrow(() -> new RestNotFoundException("Plano não encontrado"));

                TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(tipoPagamentoId)
                                .orElseThrow(() -> new RestNotFoundException("Tipo pagamento não encontrado"));

                ItemFatura itemFatura = new ItemFatura();
                itemFatura.setPlano(plano);
                itemFatura.setServicosAdicionais(new ArrayList<>());

                FaturaDTO faturaDTO = new FaturaDTO();

                faturaDTO.setCliente(new ClienteDTO(cliente));
                faturaDTO.setItemFatura(new ItemFaturaDTO(itemFatura));
                faturaDTO.setTipoPagamento(new TipoPagamentoDTO(tipoPagamento));
                log.info("Testeaq: " + faturaDTO);

                return faturaService.create(faturaDTO);
        }
}
