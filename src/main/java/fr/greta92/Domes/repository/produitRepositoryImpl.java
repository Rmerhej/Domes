package fr.greta92.Domes.repository;

import fr.greta92.Domes.entities.Produit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class produitRepositoryImpl implements ProduitRepository{

    @Override
    public Produit findById(int id){
        String sql="SELECT * FROM produits where id=?";
        try(connection conn=DBConnection.getConnection();
        PreparedState ps=conn.prepareStatement(sql)){

            ps.setInt(i,id);
            ResultSet rs=ps.executeQuery();

            if(rs.next){
                return mapToProduit(rs);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return null;
        }

        private Produit mapToProduit(ResutSet rs)throws SQLException{
            Produit p = new produit();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setImage(rs.getString("image")),
        return p;

        }
    }

}
