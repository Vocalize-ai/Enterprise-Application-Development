package br.com.fiap.vocatalk.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.dto.ClienteDTO;
import br.com.fiap.vocatalk.dto.FaturaDTO;
import br.com.fiap.vocatalk.dto.ItemFaturaDTO;
import br.com.fiap.vocatalk.dto.TipoPagamentoDTO;
import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.models.Cliente;
import br.com.fiap.vocatalk.models.Fatura;
import br.com.fiap.vocatalk.models.ItemFatura;
import br.com.fiap.vocatalk.models.ServicoAdicional;
import br.com.fiap.vocatalk.models.TipoPagamento;
import br.com.fiap.vocatalk.repositories.ClienteRepository;
import br.com.fiap.vocatalk.repositories.FaturaRepository;
import br.com.fiap.vocatalk.repositories.TipoPagamentoRepository;

@Service
public class FaturaService {

    @Autowired
    FaturaRepository faturaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    ItemFaturaService itemFaturaService;

    Logger log = LoggerFactory.getLogger(getClass());

    public List<FaturaDTO> getAll() {
        List<Fatura> faturas = faturaRepository.findAll();
        return faturas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FaturaDTO getById(Long id) {
        Fatura fatura = getFatura(id);
        return convertToDto(fatura);
    }

    public FaturaDTO create(FaturaDTO faturaDTO) {
        Fatura fatura = new Fatura();
        BeanUtils.copyProperties(faturaDTO, fatura);

        ItemFaturaDTO itemFaturaDTO = itemFaturaService.create(faturaDTO.getItemFatura());

        Cliente cliente = clienteRepository.findById(faturaDTO.getCliente().getId())
                .orElseThrow(() -> new RestNotFoundException("cliente não encontrado"));

        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(faturaDTO.getTipoPagamento().getId())
                .orElseThrow(() -> new RestNotFoundException("Tipo de pagamento não encontrado"));

        ItemFatura itemFatura = new ItemFatura();
        BeanUtils.copyProperties(itemFaturaDTO, itemFatura);
        log.info("taaq" + itemFatura);
        fatura.setCliente(cliente);
        fatura.setTipoPagamento(tipoPagamento);
        BigDecimal valorPlano = itemFatura.getPlano().getValorMensal();

        log.info("TESTE" + "SIM");
        if (!itemFatura.getServicosAdicionais().isEmpty()) {
            BigDecimal valorServicosAdicionais = itemFatura.getServicosAdicionais().stream()
                    .map(ServicoAdicional::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal valorTotal = valorPlano.add(valorServicosAdicionais);
            fatura.setValor(valorTotal);
        }

        fatura.setDataVencimento(LocalDateTime.now().plusDays(30));
        fatura.setValor(itemFatura.getPlano().getValorMensal());

        log.info("taaaq2 " + fatura);
        fatura.setItemFatura(itemFatura);

        log.info("taaaq3 " + fatura);
        Fatura faturaSalva = faturaRepository.save(fatura);
        FaturaDTO faturaSalvaDTO = convertToDto(faturaSalva);
        return faturaSalvaDTO;
    }

    public FaturaDTO update(Long id, FaturaDTO faturaDTO) {
        Fatura fatura = getFatura(id);
        BeanUtils.copyProperties(faturaDTO, fatura);

        Cliente cliente = clienteRepository.findById(faturaDTO.getCliente().getId())
                .orElseThrow(() -> new RestNotFoundException("Cliente não encontrado"));

        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(faturaDTO.getTipoPagamento().getId())
                .orElseThrow(() -> new RestNotFoundException("Tipo de pagamento não encontrado"));

        fatura.setCliente(cliente);
        fatura.setTipoPagamento(tipoPagamento);

        Fatura faturaAtualizada = faturaRepository.save(fatura);
        FaturaDTO faturaAtualizadaDTO = convertToDto(faturaAtualizada);
        return faturaAtualizadaDTO;
    }

    public void delete(Long id) {
        Fatura fatura = getFatura(id);
        faturaRepository.delete(fatura);
    }

    private Fatura getFatura(Long id) {
        return faturaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Fatura não encontrada: " + id));
    }

    private FaturaDTO convertToDto(Fatura fatura) {
        FaturaDTO faturaDTO = new FaturaDTO();
        BeanUtils.copyProperties(fatura, faturaDTO);

        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(fatura.getCliente(), clienteDTO);
        faturaDTO.setCliente(clienteDTO);

        TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO();
        BeanUtils.copyProperties(fatura.getTipoPagamento(), tipoPagamentoDTO);
        faturaDTO.setTipoPagamento(tipoPagamentoDTO);

        ItemFaturaDTO itemFaturaDTO = new ItemFaturaDTO();
        BeanUtils.copyProperties(fatura.getItemFatura(), itemFaturaDTO);
        faturaDTO.setItemFatura(itemFaturaDTO);

        return faturaDTO;
    }

}
