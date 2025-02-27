package ma.ensias.miniprojet.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@FacesValidator("dateValidator")
public class DateValidator implements Validator<String> {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false); // Disallow invalid dates (e.g., 31/02/2023)
            sdf.parse(value);
        } catch (ParseException e) {
            throw new ValidatorException(new FacesMessage("Invalid date format. Use " + DATE_FORMAT));
        }
    }
}