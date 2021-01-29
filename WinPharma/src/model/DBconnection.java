package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.PortableServer.ID_UNIQUENESS_POLICY_ID;

public class DBconnection {
	public Connection conn;
	public String url = "jdbc:postgresql://localhost:5432/winpharma";
	public String username = "postgres";
	public String password = "root";

	public DBconnection() {
		super();
	}

	public Connection createConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ResultSet selectUser(Pharmacien pharmacien) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.pharmacien where username='"
				+ pharmacien.getUsername() + "' and pwd='" + pharmacien.getPwd() + "'";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public ResultSet selectAdmin(Admin admin) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.admin where username='"
				+ admin.getUsername() + "' and pwd='" + admin.getPwd() + "'";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public ResultSet selectProduit() throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.produit";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public ResultSet checkUsername(Pharmacien pharmacien) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.pharmacien where nom='" + pharmacien.getNom() + "';";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public void supprimerUtilisateur(Pharmacien pharmacien) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "UPDATE public.pharmacien SET statut=FALSE WHERE pharmacienID=" + pharmacien.getPharmacienID() + ";";
		Statement st = connection.createStatement();
		st.executeUpdate(query1);
	}
	
	public void updatePassword(String pwd) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "UPDATE public.pharmacien SET pwd='" + pwd + "' WHERE pharmacienID=" 
							+ controller.LoginController.id_utilisateur + ";";
		Statement st = connection.createStatement();
		st.executeUpdate(query1);
	}
	
	public ResultSet listUser() throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.pharmacien order by pharmacienID";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public ResultSet selectProduitPresentation(String nomProduit) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.produit where nom='" + nomProduit + "'";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public void updateProduit(Produit produit) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "select qtejour from public.produit where produitid=" + produit.getProduitID() + ";";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query1);
		int quantiter=0;
		if(rs.next()) {
			quantiter = rs.getInt("qtejour");
		}
		quantiter = produit.getQteJour() - quantiter;
		String query2 = "UPDATE public.produit SET qtejour=" + quantiter 
						+ " WHERE produitid=" + produit.getProduitID() + ";";
		st.executeUpdate(query2);
	}
	
	public int insertRecu(Recu recu) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "insert into recu (quantiter,date,pharmacienid) values ("
				+ recu.getQuantiter() + ",'" + recu.getDate() + "',"  + controller.LoginController.id_utilisateur + ") RETURNING recuid;";
		System.out.println(query1);
		Statement st = connection.createStatement();
		//st.executeUpdate(query2);
		ResultSet rs = st.executeQuery(query1);
		rs.next();
		return rs.getInt("recuid");
	}
	
	public void insertRecuProduit(RecuProduit recuProduit) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "insert into recu_produit (produitid,recuid,quantiter,montant) values ("
				+ recuProduit.getProduitID() + "," + recuProduit.getRecuID() + ","  + recuProduit.getQuatiter() + ","
				+ recuProduit.getMontant() + ");";
		System.out.println(query1);
		Statement st = connection.createStatement();
		//st.executeUpdate(query2);
		st.executeUpdate(query1);
	}
	
	public void ajoutUtilisateur(Pharmacien pharmacien) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "insert into pharmacien (nom,prenom,username,datedecreation,adminid,pwd,statut) values ('"
				+ pharmacien.getNom() + "','" + pharmacien.getPrenom() + "','"  + pharmacien.getUsername() + "','"
				+ pharmacien.getDateDeCreation() + "'," + controller.LoginController.id_utilisateur + ",'" + pharmacien.getPwd() 
				+  "'," + pharmacien.getStatut() + ");";
		System.out.println(query1);
		Statement st = connection.createStatement();
		//st.executeUpdate(query2);
		st.executeUpdate(query1);
	}
	
	public void modifierUtilisateur(Pharmacien pharmacien) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "update pharmacien set nom='" + pharmacien.getNom() + "',prenom='" + pharmacien.getPrenom() + "',username='"
				+ pharmacien.getUsername() + "' where pharmacienid=" + pharmacien.getPharmacienID() + ";";
		System.out.println(query1);
		Statement st = connection.createStatement();
		//st.executeUpdate(query2);
		st.executeUpdate(query1);
	}
	
	public ResultSet selectProduitPresentation(String nomProduit, String presentation) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query = "select * from public.produit where nom='" + nomProduit + "' and presentation='" + presentation + "'";
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
	public void ajout1(Produit pt, Historique h) throws SQLException {
		DBconnection db = new DBconnection();
		Connection connection = db.createConnection();
		String query1 = "insert into historique (quantiter,date,pharmacienid) values ("
				+ h.getQuantiter() + ",'" + h.getDate() + "',"  + controller.LoginController.id_utilisateur + ") RETURNING histid;";
		System.out.println(query1);
		Statement st = connection.createStatement();
		//st.executeUpdate(query2);
		ResultSet rs = st.executeQuery(query1);
		rs.next();
		String query2 = "insert into produit (nom,presentation,prixachat,prixventefrsp,prixventefosa,qtejour,qtenuit,montantjour,"
				+ "montantnuit,histid) values ('"
				+ pt.getNom() + "','" + pt.getPresentation() + "'," + pt.getPrixAchat() + "," + pt.getPrixVenteFRSP() + "," 
				+ pt.prixVenteFOSA + "," + pt.getQteJour() + "," +  pt.getQteNuit() + "," + pt.montantJour + "," + pt.montantNuit + "," 
				+ rs.getInt(1) + ");";
		st.executeUpdate(query2);
	}
	
}
