# Application de Gestion des Utilisateurs avec JSF et PrimeFaces

## Introduction
Cette application web a été développée en utilisant **JSF (Jakarta Server Faces)** pour l'interface utilisateur et **PrimeFaces** pour les composants riches. L'application permet l'authentification des utilisateurs, la gestion des utilisateurs et la validation des données. Elle est conçue pour gérer les informations des utilisateurs, y compris leurs détails personnels, adresses et soldes de compte.

## Fonctionnalités

### 1. Authentification des Utilisateurs
- Les utilisateurs peuvent se connecter avec leurs identifiants.
- Redirection basée sur les rôles :
  - **Admin** : Redirigé vers la page de gestion des utilisateurs.
  - **User** : Redirigé vers une page affichant leurs informations détaillées.
  - **Rôle invalide** : Redirigé vers une page d'erreur.

### 2. Gestion des Utilisateurs
- Un **DataTable** affiche la liste des utilisateurs.
- Les administrateurs peuvent modifier ou supprimer des utilisateurs.
- Un bouton **"Ajouter un utilisateur"** redirige vers une page pour ajouter de nouveaux utilisateurs.
- Une **facet** pour rechercher un utilisateur.

### 3. Page d'Ajout d'Utilisateur
- Les utilisateurs peuvent saisir les détails suivants :
  - Prénom
  - Nom
  - Date de naissance (format : XX/XX/XXXX)
  - Solde du compte (en MAD)
  - Adresse (format : Numéro - Nom de la rue)
  - Ville et code postal (le code postal commence par un chiffre)
  - Pays

### 4. Convertisseurs Personnalisés
- **Date de naissance** : Convertit au format XX/XX/XXXX.
- **Solde du compte** : Affiche avec l'unité MAD (Dirham Marocain).
- **Adresse** : Extrait et formate le numéro de rue, le nom de la rue, la ville et le code postal.

### 5. Validation des Données
- Les validateurs garantissent :
  - Le format correct de la date de naissance.
  - Un solde de compte valide (numérique et positif).
  - Des champs d'adresse correctement structurés.

## Technologies Utilisées
- **JSF (Jakarta Server Faces)** : Pour construire l'interface web.
- **PrimeFaces** : Pour les composants d'interface utilisateur riches.
- **Java EE / Jakarta EE** : Pour la logique backend et la gestion des utilisateurs.
- **JPA / Hibernate** : Pour l'accès à la base de données et l'ORM.
- **Maven** : Pour la gestion des dépendances.

## Prérequis
- **Java Development Kit 17**
- **Apache Maven 3.9**
- **Serveur d'application**: WildFly 35
- **Base de données** : MySQL

## Installation et Configuration

### 1. Configuration de la Base de Données
- Nous avons configuré une **source de données (Datasource)** sur **WildFly** en utilisant **MySQL** comme base de données.
- La base de données MySQL a été configurée en utilisant **Docker**.
- La configuration de la source de données a été effectuée en mode **standalone** et **domain**.

### 2. Fichier `persistence.xml`
- Le fichier `persistence.xml` est utilisé pour configurer la persistance des données avec JPA/Hibernate.

```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
             version="3.0">
    <persistence-unit name="userPU" transaction-type="JTA">
        <jta-data-source>java:/MySqlDS</jta-data-source>
        <class>ma.ensias.miniprojet.Model.User</class>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
</persistence>
```

### 3. Dépendances Maven
- Les dépendances du projet sont gérées via le fichier `pom.xml`.

```xml
<dependencies>
    <!-- JPA & Hibernate -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.2.0.Final</version>
    </dependency>
    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
        <scope>provided</scope>
    </dependency>
    <!-- PrimeFaces -->
    <dependency>
        <groupId>org.primefaces</groupId>
        <artifactId>primefaces</artifactId>
        <version>14.0.0</version>
        <classifier>jakarta</classifier>
    </dependency>
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.26</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## Structure du Projet

### 1. Modèle (`Model`)
```java
@Entity(name = "AppUser")
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
```
### 2. Service (Service)
- **UserService** : Interface définissant les méthodes pour la gestion des utilisateurs.
- **JpaUserService** : Implémentation de l'interface `UserService`.

```java
public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User authenticate(String username, String password) throws UserNotFoundException;
    User getLoggedUser();
}
```

### 3. Contrôleurs (Controller)
- **LoginController** : Gère l'authentification des utilisateurs.
- **UserController** : Gère les fonctionnalités liées aux utilisateurs.

### 4. Convertisseurs (Converters)
- **DateConverter** : Convertit les dates au format `XX/XX/XXXX`.
- **CurrencyConverter** : Formate les soldes avec l'unité `MAD`.
- **AddressConverter** : Formate les adresses.

### 5. Validateurs (Validators)
- **DateValidator** : Valide le format de la date de naissance.
- **BalanceValidator** : Valide que le solde est numérique et positif.
- **AddressValidator** : Valide la structure de l'adresse.

### 6. Interfaces Utilisateur (XHTML)
- **login.xhtml** : Page de connexion.
- **admin.xhtml** : Page de gestion des utilisateurs pour les administrateurs.
- **user.xhtml** : Page d'affichage des informations pour les utilisateurs.
- **addUser.xhtml** : Page d'ajout d'un nouvel utilisateur.

## Captures d'écran
![Page de connexion](resources/images/login.png)
![Tableau de bord administrateur](resources/images/page admin.png)
![Formulaire d'ajout d'un utilisateur](resources/images/ajout.png)
![Formulaire de modification d'un utilisateur](resources/images/modif.png)
![Tableau de bord utilisateur](resources/images/user-interface.png)



