package ma.ensias.miniprojet.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("addressValidator")
public class AddressValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || !value.matches(".*\\d+.*")) { // Check for street number
            throw new ValidatorException(new FacesMessage("Address must include a street number."));
        }
    }
}