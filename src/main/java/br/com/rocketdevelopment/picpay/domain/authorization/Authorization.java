package br.com.rocketdevelopment.picpay.domain.authorization;

public record Authorization(String message) {

    public boolean isAuthorized() {
        return message != null && message.contains("Autorizado");
    }
}
