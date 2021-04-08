package ru.detskiostrov.addresses;

/**
 * This class is sending email and SMS to remind about events (birthdays, events ...)
 * Author: Grigorii Andreev
 * Date: 11 March 2021
 */
public class EmailAndSmsSender {

    private String clientEmail;
    private String fromEmail;
    private String clientPhoneNumber;
    private String fromPhoneNumber;
    private String emailMessage;
    private String smsMessage;

    public EmailAndSmsSender(String email, String phoneNumber, String emailMessage, String smsMessage) {
        this.clientEmail = email;
        this.clientPhoneNumber = phoneNumber;
        this.emailMessage = emailMessage;
        this.smsMessage = smsMessage;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    //Connect to mail server to send from
    public void mailSender() {

    }

}
