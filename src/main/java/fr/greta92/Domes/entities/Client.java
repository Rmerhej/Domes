package fr.greta92.Domes.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Client {

    @Id
    // Compteur commun "hibernate_sequence", comme avant la migration (voir AlignementIdentifiants)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client")
    @SequenceGenerator(name = "seq_client", sequenceName = "hibernate_sequence", allocationSize = 1)
    private int id_client;

    private String nom_client;

    private String prenom_client;
    @Size(min=10, message = "vous devez saisir au moins 10 chiffres")
    private String telephone_client;
    private String email_client;
    private String login;

    private String mdp;
    @ElementCollection
    @CollectionTable(name = "adresses", joinColumns = @JoinColumn(name = "client_id"))
    private List<Adresse> adresses = new ArrayList<>();

    public Client(){}
    public Client(int id_client,String nom_client,String prenom_client,String telephone_client,String email_client,String login,String mdp){
        this.id_client=id_client;
        this.nom_client=nom_client;
        this.prenom_client=prenom_client;
        this.telephone_client=telephone_client;
        this.email_client=email_client;
        this.login=login;
        this.mdp = mdp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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
        return id_client == client.id_client && nom_client.equals(client.nom_client) && prenom_client.equals(client.prenom_client) && telephone_client.equals(client.telephone_client) && email_client.equals(client.email_client) && login.equals(client.login) && mdp.equals(client.mdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_client, nom_client, prenom_client, telephone_client, email_client, login, mdp);
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
                ", mdp_client='" + mdp + '\'' +
                '}';
    }
}
