package fr.greta92.Domes.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Photos envoyées depuis l'espace admin.
 *
 * - Rangées dans le dossier domes.uploads.dir (UPLOADS_DIR, par défaut "uploads"),
 *   en dehors du code : un nouveau déploiement ne les efface pas.
 * - Servies à l'adresse /uploads/... (voir ApplicationConfig) ; en base, l'animal
 *   garde le chemin "uploads/xxx.jpg", comme les photos fournies ("images/xxx.jpg").
 *
 * Sécurité :
 * - le nom choisi par l'utilisateur n'est jamais utilisé (pas de "../../" possible) :
 *   chaque fichier reçoit un nom aléatoire ;
 * - le format est vérifié sur le CONTENU du fichier (ses premiers octets),
 *   pas sur son extension, qu'il est facile de falsifier.
 */
@Service
public class StockagePhotos {

    public static final String PREFIXE = "uploads/";
    public static final long TAILLE_MAX = 5L * 1024 * 1024;   // 5 Mo

    private static final String MOTIF_NOM = "[0-9a-f-]{36}\\.(jpg|png|webp)";

    private final Path dossier;

    public StockagePhotos(@Value("${domes.uploads.dir:uploads}") String dossier) {
        this.dossier = Paths.get(dossier).toAbsolutePath().normalize();
    }

    public Path getDossier() {
        return dossier;
    }

    /**
     * Enregistre la photo et renvoie son chemin, à stocker dans l'animal.
     *
     * @throws IllegalArgumentException fichier vide, trop lourd ou qui n'est pas une photo
     * @throws IOException              écriture impossible sur le disque
     */
    public String enregistrer(MultipartFile fichier) throws IOException {
        if (fichier == null || fichier.isEmpty()) {
            throw new IllegalArgumentException("Le fichier envoyé est vide.");
        }
        if (fichier.getSize() > TAILLE_MAX) {
            throw new IllegalArgumentException("La photo dépasse 5 Mo. Choisissez une image plus légère.");
        }
        String extension;
        try (InputStream entree = fichier.getInputStream()) {
            extension = detecterFormat(entree.readNBytes(12));
        }
        if (extension == null) {
            throw new IllegalArgumentException("Ce fichier n'est pas une photo JPEG, PNG ou WebP.");
        }

        Files.createDirectories(dossier);
        String nom = UUID.randomUUID() + "." + extension;
        try (InputStream entree = fichier.getInputStream()) {
            Files.copy(entree, dossier.resolve(nom));
        }
        return PREFIXE + nom;
    }

    /** Les photos déjà envoyées, sous la forme "uploads/xxx.jpg". */
    public List<String> photosEnvoyees() {
        List<String> photos = new ArrayList<>();
        if (!Files.isDirectory(dossier)) {
            return photos;
        }
        try (DirectoryStream<Path> fichiers = Files.newDirectoryStream(dossier)) {
            for (Path fichier : fichiers) {
                String nom = fichier.getFileName().toString();
                if (nom.matches(MOTIF_NOM)) {
                    photos.add(PREFIXE + nom);
                }
            }
        } catch (IOException e) {
            // liste incomplète : sans gravité, l'admin peut envoyer une nouvelle photo
        }
        photos.sort(null);
        return photos;
    }

    /** Supprime une photo envoyée ("uploads/..."). Les photos fournies avec le site ne sont jamais touchées. */
    public void supprimer(String chemin) {
        if (chemin == null || !chemin.startsWith(PREFIXE)) {
            return;
        }
        String nom = chemin.substring(PREFIXE.length());
        if (!nom.matches(MOTIF_NOM)) {
            return;
        }
        try {
            Files.deleteIfExists(dossier.resolve(nom));
        } catch (IOException e) {
            // fichier verrouillé ou déjà absent : il restera sur le disque, sans conséquence pour le site
        }
    }

    /** Reconnaît JPEG, PNG et WebP à leur "signature" (premiers octets du fichier). */
    static String detecterFormat(byte[] debut) {
        if (debut.length >= 3 && (debut[0] & 0xFF) == 0xFF && (debut[1] & 0xFF) == 0xD8 && (debut[2] & 0xFF) == 0xFF) {
            return "jpg";
        }
        if (debut.length >= 8 && (debut[0] & 0xFF) == 0x89 && debut[1] == 'P' && debut[2] == 'N' && debut[3] == 'G') {
            return "png";
        }
        if (debut.length >= 12 && debut[0] == 'R' && debut[1] == 'I' && debut[2] == 'F' && debut[3] == 'F'
                && debut[8] == 'W' && debut[9] == 'E' && debut[10] == 'B' && debut[11] == 'P') {
            return "webp";
        }
        return null;
    }
}
