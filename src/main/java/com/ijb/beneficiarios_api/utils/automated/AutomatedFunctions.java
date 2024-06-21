package com.ijb.beneficiarios_api.utils.automated;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Component
public class AutomatedFunctions {

    @Autowired
    BeneficiarioService beneficiarioService;

    public boolean isMoreThanSixMonthsOld(BeneficiarioEntity beneficiario) {
        LocalDateTime date = beneficiario.getDtAtualizacao();
        if (date == null) {
            return false; // ou lance uma exceção, dependendo do seu caso de uso
        }

        LocalDateTime sixMonthsAgo = LocalDateTime.now().minus(Period.ofMonths(6));
        return date.isBefore(sixMonthsAgo);
    }

    @Scheduled(cron = "0 0 0 1 */6 ?")
    public void ValidarDadosBeneficiario() {
        List<BeneficiarioEntity> beneficiarios = beneficiarioService.getAll();

        beneficiarios.stream()
                .forEach(beneficiario -> {
                    if(isMoreThanSixMonthsOld(beneficiario)) {
                        beneficiarioService.notificarAttDados();
                    }
                });
    }
}
