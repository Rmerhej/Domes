package fr.greta92.Domes.beans;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_client;
    @NotEmpty(message = "ce champ ne peut pas être vide")
    private String nom_client;
    @NotEmpty(message = "ce champ ne peut pas être vide")
    private String prenom_client;
    @Size(min=10, message = "vous devez saisir au moins 10 chiffres")
    private String telephone_client;
    private String email_client;
    private String login;
    /*@Pattern(regexp = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16})", message = "password doit contenir 1 numéro (0-9)\n" +
            " 1 lettre majuscule \n" +
            "1 lettre minuscule \n" +
            "1 caractère spécial\n" +
            "mot de passe de 8-16 caractèrs sans espace\n" +
            "\n")*/
    private String mdp_client;
    @ElementCollection
    @CollectionTable(name = "adresses", joinColumns = @JoinColumn(name = "client_id"))
    private List<Adresse> adresses = new ArrayList<>();

    public Client(){}
    public Client(int id_client,String nom_client,String prenom_client,String telephone_client,String email_client,String login,String mdp_client){
        this.id_client=id_client;
        this.nom_client=nom_client;
        this.prenom_client=prenom_client;
        this.telephone_client=telephone_client;
        this.email_client=email_client;
        this.login=login;
        this.mdp_client=mdp_client;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getMdp_client() {
        return mdp_client;
    }

    public void setMdp_client(String mdp_client) {
        this.mdp_client = mdp_client;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public String getTelephone_client() {
        return telephone_client;
    }

    public void setTelephone_client(String telephone_client) {
        this.telephone_client = telephone_client;
    }

    public String getEmail_client() {
        return email_client;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }
    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id_client == client.id_client && nom_client.equals(client.nom_client) && prenom_client.equals(client.prenom_client) && telephone_client.equals(client.telephone_client) && email_client.equals(client.email_client) && login.equals(client.login) && mdp_client.equals(client.mdp_client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_client, nom_client, prenom_client, telephone_client, email_client, login, mdp_client);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom_client='" + nom_client + '\'' +
                ", prenom_client='" + prenom_client + '\'' +
                ", telephone_client='" + telephone_client + '\'' +
                ", email_client='" + email_client + '\'' +
                ", login_client='" + login + '\'' +
                ", mdp_client='" + mdp_client + '\'' +
                '}';
    }
}
