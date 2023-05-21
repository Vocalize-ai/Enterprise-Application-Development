package br.com.fiap.vocatalk.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.vocatalk.models.Plano;
import br.com.fiap.vocatalk.models.ServicoAdicional;
import br.com.fiap.vocatalk.models.TipoPagamento;
import br.com.fiap.vocatalk.repositories.PlanoRepository;
import br.com.fiap.vocatalk.repositories.ServicoAdicionalRepository;
import br.com.fiap.vocatalk.repositories.TipoPagamentoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    PlanoRepository planoRepository;

    @Autowired
    ServicoAdicionalRepository servicoAdicionalRepository;

    @Override
    public void run(String... args) throws Exception {

        TipoPagamento tipoPagamento1 = new TipoPagamento("Boleto", "Forma de pagamento boleto");
        tipoPagamentoRepository.save(tipoPagamento1);

        Plano plano1 = new Plano("Combo loco", 25, 13, BigDecimal.valueOf(234), "Plano com internet e ligações");
        planoRepository.save(plano1);

        Plano plano2 = new Plano("Combo dasdsaddsa", 25, 13, BigDecimal.valueOf(123), "Plano com internet e DOIDO");
        planoRepository.save(plano2);

        ServicoAdicional servicoAdicional1 = new ServicoAdicional("Pacote internet - 3GB", BigDecimal.valueOf(23.3), "Pacote de internet 3GB");
        servicoAdicionalRepository.save(servicoAdicional1);

        ServicoAdicional servicoAdicional2 = new ServicoAdicional("Pacote internet - 6GB", BigDecimal.valueOf(58.3), "Pacote de internet 6GB");
        servicoAdicionalRepository.save(servicoAdicional2);

    }

}
