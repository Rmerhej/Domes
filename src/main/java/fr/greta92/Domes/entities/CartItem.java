package fr.greta92.Domes.entities;

public class CartItem {
    private Produit produit;
    private int quantite;

    public CartItem(Produit produit) {
        this.produit = produit;
        this.quantite = 1;}


        public Produit getProduit () {
            return produit;
        }
        public void incrementQuantite () {
            this.quantite++;
        }

        public int getQuantite () {
            return quantite;
        }
        public void setQuantity ( int quantite){
            if (quantite > 0) {
                this.quantite = quantite;
            }
        }

        public void decrementQuantity () {
            if (this.quantite > 1) {
                this.quantite--;
            }
        }

    public double getPrice() {
        return produit != null ? produit.getPrice() : 0.0;
    }

    public double getTotalPrice() {
        return produit != null ? produit.getPrice() * quantite : 0.0;
    }

    // ==================== Getters & Setters ====================
    public Produit getProduct() {
        return produit;
    }

    public void setProduct(Produit product) {
        this.produit = product;
    }

    }
