package br.com.rocketdevelopment.picpay.domain.notification;

public record Notification(boolean message) {

    public boolean isNotified() {
        return message;
    }
}
