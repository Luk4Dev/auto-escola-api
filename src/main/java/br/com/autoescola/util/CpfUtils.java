package br.com.autoescola.util;

public final class CpfUtils {

    private CpfUtils() {
    }

    public static boolean isValid(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        int primeiroDigito = calcularDigito(cpf.substring(0, 9), 10);
        int segundoDigito = calcularDigito(cpf.substring(0, 9) + primeiroDigito, 11);

        return cpf.equals(cpf.substring(0, 9) + primeiroDigito + segundoDigito);
    }

    private static int calcularDigito(String base, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < base.length(); i++) {
            soma += Character.getNumericValue(base.charAt(i)) * (pesoInicial - i);
        }
        int resto = (soma * 10) % 11;
        return resto == 10 ? 0 : resto;
    }
}
