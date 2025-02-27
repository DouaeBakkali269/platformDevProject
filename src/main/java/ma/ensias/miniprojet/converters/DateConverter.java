package ma.ensias.miniprojet.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter<Date> {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public Date getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use " + DATE_FORMAT);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Date value) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(value);
    }
}