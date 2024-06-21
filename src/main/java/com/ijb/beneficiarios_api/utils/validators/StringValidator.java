package com.ijb.beneficiarios_api.utils.validators;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class StringValidator {

    public boolean validarCpf(String cpf) {
        if (cpf == null || cpf == "") {
            return false;
        }

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Verifica o comprimento
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Cálculo do primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = (sum * 10) % 11;
        if (firstDigit == 10) {
            firstDigit = 0;
        }

        // Cálculo do segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = (sum * 10) % 11;
        if (secondDigit == 10) {
            secondDigit = 0;
        }

        // Verifica se os dígitos verificadores estão corretos
        return firstDigit == Character.getNumericValue(cpf.charAt(9)) &&
                secondDigit == Character.getNumericValue(cpf.charAt(10));
    }

    public boolean validarEmail(String email) {
        if (email == null || email == "") {
            return false;
        }

        // Regex para validar e-mail
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
