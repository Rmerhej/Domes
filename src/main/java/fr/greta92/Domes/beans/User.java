    package fr.greta92.Domes.beans;

    import jdk.dynalink.linker.LinkerServices;

    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.validation.constraints.Pattern;
    import javax.validation.constraints.Size;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;

    @Entity

    public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;  //((?=.*[A-Z]).{6,10})  //Le mot de passe doit avoir une majuscule, une minuscule et doit comporter entre 6 et 10 caractères
        private String mdp;

        private String login;

    public User(){}
        public User(int id,String mdp,String login){
        this.id=id;
        this.mdp=mdp;
        this.login=login;
        }
        public String getMdp() {
            return mdp;
        }

        public void setMdp(String mdp) {
            this.mdp = mdp;
        }

        /* @Pattern(regexp = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16})", message = "password doit contenir 1 numéro (0-9)\n" +
                    " 1 lettre majuscule \n" +
                    "1 lettre minuscule \n" +
                    "1 caractère spécial\n" +
                    "mot de passe de 8-16 caractèrs sans espace\n" +
                    "\n")*/


        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }


        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id && mdp.equals(user.mdp) && login.equals(user.login);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, mdp, login);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", mdp='" + mdp + '\'' +
                    ", login='" + login + '\'' +
                    '}';
        }
    }
