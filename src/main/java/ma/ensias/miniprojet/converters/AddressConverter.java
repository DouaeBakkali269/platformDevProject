package ma.ensias.miniprojet.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("addressConverter")
public class AddressConverter implements Converter<String> {

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        // Parse the formatted address back into a single string
        return value.replace("\n", ", "); // Replace newlines with commas
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        // Format the address with line breaks
        return value.replace(", ", "\n"); // Replace commas with newlines
    }
}
