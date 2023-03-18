    package fr.greta92.Domes.services;

    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.beans.LignePanier;
    import fr.greta92.Domes.repository.PanierRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class PanierService {

        List<Article> articlesAjoutes=new ArrayList<>();

        public List<Article> getArticles() {
            return articlesAjoutes;
        }

        public void addArticle(Article article) {
            articlesAjoutes.add(article);
        }
        public Article deleteArticle(Article article){
            articlesAjoutes.remove(article);
            return article;
        }
        public Double montantPanier(List<LignePanier> articlesDunPanier) {
            Double montantTotalPanier = 0.0;
            for (LignePanier lignePanier : articlesDunPanier) {
                Double totalLigne = lignePanier.prixLigne(lignePanier.getArticleLignePanier(), lignePanier.getQuantite());
                montantTotalPanier += totalLigne;
            }
            return montantTotalPanier;
        }


        @Autowired
        PanierRepository panierRepository;
        public Article fetchArticle(int id_article){
           Article article =  panierRepository.findById(id_article);
            return article;
        }
       public List<Article> ajoutAuPanierArticles(Article article){
           List<Article> panierArticles=new ArrayList<>();
           panierArticles.add(article);
           return panierArticles;

       }
        public List<Article> supprimerDuPanier(Article article){
            List<Article> panierArticles=new ArrayList<>();
            panierArticles.remove(article);
            return panierArticles;
        }

    }
