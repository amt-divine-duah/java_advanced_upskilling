package labs.lab_two.design_patterns.facade;

class EmailComposerService {
    private String recipient;
    private String subject;
    private String body;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
        System.out.println("Recipient set to: " + recipient);
    }

    public void setSubject(String subject) {
        this.subject = subject;
        System.out.println("Subject set to: " + subject);
    }

    public void setBody(String body) {
        this.body = body;
        System.out.println("Body set to: " + body);
    }

    public Email getEmail() {
        return new Email(recipient, subject, body);
    }
}

class EmailSenderService {
    public void sendEmail(Email email) {
        // Logic to send email
        System.out.println("Sending email to " + email.getRecipient());
        System.out.println("Subject: " + email.getSubject());
        System.out.println("Body: " + email.getBody());
        // Assume email is sent successfully
    }
}
class EmailManagerService {
    public void logEmail(Email email) {
        // Logic to log email details
        System.out.println("Logging email to " + email.getRecipient());
    }

    public void updateEmailStatus(Email email, String status) {
        // Logic to update email status
        System.out.println("Updating email status to: " + status);
    }
}
class Email {
    private String recipient;
    private String subject;
    private String body;

    public Email(String recipient, String subject, String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}

class EmailFacade {
    private final EmailComposerService emailComposerService;
    private final EmailSenderService emailSenderService;
    private final EmailManagerService emailManagerService;

    public EmailFacade() {
        this.emailComposerService = new EmailComposerService();
        this.emailSenderService = new EmailSenderService();
        this.emailManagerService = new EmailManagerService();
    }

    public void composeEmail(String recipient, String subject, String body) {
        emailComposerService.setRecipient(recipient);
        emailComposerService.setSubject(subject);
        emailComposerService.setBody(body);
    }

    public void sendEmail() {
        Email email = emailComposerService.getEmail();
        emailSenderService.sendEmail(email);
        emailManagerService.logEmail(email);
        emailManagerService.updateEmailStatus(email, "Sent");
    }
}

public class EmailClient {

    public static void main(String[] args) {
        EmailFacade emailFacade = new EmailFacade();

        // Compose an email
        emailFacade.composeEmail("john.doe@example.com", "Meeting Reminder", "Don't forget about the meeting at 3 PM.");

        // Send the composed email
        emailFacade.sendEmail();
    }
}
