package com.ijb.beneficiarios_api.services.beneficiario;

import com.ijb.beneficiarios_api.dtos.beneficiario.PreCadastroBeneficiarioDTO;
import com.ijb.beneficiarios_api.repositories.beneficiario.BeneficiarioRepository;
import com.ijb.beneficiarios_api.repositories.beneficiario.MembroFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private MembroFamiliarRepository membroFamiliarRepository;

    ResponseEntity preCadastrarBeneficiario() {

    }
}
