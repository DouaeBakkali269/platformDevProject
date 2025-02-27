package ma.ensias.miniprojet.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Entity(name = "AppUser") // Rename the table
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nom;
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private Double soldeCompte;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private String role;

}

