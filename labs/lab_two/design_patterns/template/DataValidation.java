package labs.lab_two.design_patterns.template;

abstract class DataValidator {

    // Template method
    public final boolean validate(String data) {
        if (data == null) {
            return false;
        }

        boolean result = performBasicValidation(data);
        if (result) {
            result = performAdditionalValidation(data);
        }

        return result;
    }

    // Abstract methods to be implemented by subclasses
    protected abstract boolean performBasicValidation(String data);

    protected abstract boolean performAdditionalValidation(String data);
}

class EmailValidator extends DataValidator {

    @Override
    protected boolean performBasicValidation(String data) {
        // Basic email format validation
        return data.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    @Override
    protected boolean performAdditionalValidation(String data) {
        // Additional specific email validations (if any)
        return true;
    }
}


class PhoneNumberValidator extends DataValidator {

    @Override
    protected boolean performBasicValidation(String data) {
        // Basic phone number format validation
        return data.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    @Override
    protected boolean performAdditionalValidation(String data) {
        // Additional specific phone number validations (if any)
        return true;
    }
}

public class DataValidation {
    public static void main(String[] args) {
        DataValidator emailValidator = new EmailValidator();
        DataValidator phoneValidator = new PhoneNumberValidator();

        String email = "example@email.com";
        String phone = "123-456-7890";

        System.out.println("Validating Email:");
        boolean emailResult = emailValidator.validate(email);
        System.out.println("Email validation result: " + emailResult);

        System.out.println("\nValidating Phone Number:");
        boolean phoneResult = phoneValidator.validate(phone);
        System.out.println("Phone number validation result: " + phoneResult);
    }
}
