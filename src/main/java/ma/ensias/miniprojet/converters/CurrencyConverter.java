package ma.ensias.miniprojet.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("currencyConverter")
public class CurrencyConverter implements Converter<Double> {

    @Override
    public Double getAsObject(FacesContext context, UIComponent component, String value) {
        // Remove the "MAD" suffix and parse the number
        String numericValue = value.replace(" MAD", "").trim();
        return Double.parseDouble(numericValue);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Double value) {
        return String.format("%.2f MAD", value); // Format as "XXXX.XX MAD"
    }
}
