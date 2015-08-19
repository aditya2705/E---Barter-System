package bartersystem;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class UserDatabaseAccess{      
	

        private String[] items,itemsuser,messages,trades;
        private int[] productids,idNots,tradeids;
        
	private final String userid = "root";
	private final String password = "";
	static String url = "jdbc:mysql://localhost:3306/barter";	

	private Connection con;

     // constructor 
	public UserDatabaseAccess(){
                getConnection();	
	}

	public Connection getConnection(){
				
		try {
			Class.forName("com.mysql.jdbc.Driver");	

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url, userid, password);
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		
		return con;
	}

	public boolean savePerson(UserInfo person){
            boolean validation = true;
		try
		{
                        String sql = "SELECT emailid from registeredusers where emailid = ?";

			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
	
			ps.setString(1, person.getEmail());
                        
			ResultSet rs = ps.executeQuery();
                        
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Email Already Registered");
                            validation = false;
                        }
                        else{
                        
                            try{
                   
                            String sql1 = "INSERT INTO registeredusers(name, emailid, password) VALUES (?,?,?) ";

                            // Create a Preparedstatement
                            PreparedStatement ps1 = con.prepareStatement(sql1);
	
                            ps1.setString(1, person.getName());
                            ps1.setString(2, person.getEmail());
                            ps1.setString(3, person.getPassword());
                        
                            ps1.executeUpdate();
                            }
                            catch(Exception e){
                            System.out.println(e);
                            }
                        }
                        
		}
		catch(Exception e){
			System.out.println(e);
		}
                return validation;
                
	}
        
        public boolean check(UserInfo person){
            boolean logged = false;
                try
		{
			String sql = "SELECT name,id from registeredusers where emailid = ? and password = ? ";

			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
	
			ps.setString(1, person.getEmail());
			ps.setString(2, person.getPassword());
                        
			ResultSet rs = ps.executeQuery();
                        
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Person Logged in");
                            logged = true;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No such entry");
                            logged = false;
                        }
                        
		}
		catch(SQLException | HeadlessException e){
			System.out.println(e);
		}
                
                return logged;
            
        }
        
        public boolean saveProduct(ProductInfo product) throws IOException{
        
            try{
                   
                  String sql1 = "INSERT INTO userproducts(user_id, product_name, product_type,"
                          + "product_image,quantity, product_description,expectation) VALUES (?,?,?,?,?,?,?) ";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
	
                  ps1.setInt(1, product.getUser_id());
                  ps1.setString(2, product.getName());
                  ps1.setString(3, product.getType());
                  
                  //image upload
                  FileInputStream fs;
                try {
                    fs = new FileInputStream(product.getImageFile());
                    ps1.setBinaryStream(4,fs,fs.available());
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UserDatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
                  
                  
                  ps1.setInt(5, product.getQuantity());
                  ps1.setString(6, product.getDescription());
                  ps1.setString(7, product.getExpectation());
                  
                        
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
            
            return true;
        }
        
        public String[] getUserItems(int user_id){
            
            itemsuser = null;
            
            
            try {
                
                List<String> ItemsList = new ArrayList<>();
                
                String sql = "SELECT product_name from userproducts where user_id = ?";
                
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setInt(1,user_id);
                
                ResultSet results = ps.executeQuery();
                
                while (results.next()){
                    
                    String product = results.getString(1);
                    ItemsList.add(product);
                    
                }
                
                itemsuser = new String[ItemsList.size()];
                
                for(int i = 0; i < ItemsList.size(); i++){
                    itemsuser[i] = ItemsList.get(i);
                }
                
                
                results.close();
                ps.close();
                 
            } catch (SQLException ex) {
                Logger.getLogger(UserDatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return itemsuser; 
        
        }
        
        //login details
        public String getPersonName(String email, String password){
            String user_name = "";
            try{
            String sql = "SELECT name from registeredusers where emailid = ? and password = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
                        ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            user_name = rs.getString("name");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return user_name;
        }
        
        
        public int getPersonId(String email,String password){
            int id = 0;
            try{
            String sql = "SELECT id from registeredusers where emailid = ? and password = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
                        ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            id = rs.getInt("id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return id;
        }
        
        
        //User Details
        public int getUserId(int index){
            int id = 0;
            try{
            String sql = "SELECT user_id from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            id = rs.getInt("user_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return id;
        }
        
        public String getUserName(int index){
            String user_name = "";
            try{
            String sql = "SELECT name from registeredusers where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            user_name = rs.getString("name");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return user_name;
        }
        
        public String getUserEmail(int index){
            String user_email = "";
            try{
            String sql = "SELECT emailid from registeredusers where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            user_email = rs.getString("emailid");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return user_email;
        }
        
        
        
        //Product Details
        
        
        
        public int getProductId(int i, int userIndex){
            int productIndex=0;
            String product_name = itemsuser[i];
            try{
            String sql = "SELECT product_id from userproducts where user_id = ? and product_name = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userIndex);
                        ps.setString(2, product_name);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            productIndex = rs.getInt("product_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return productIndex;
        }
        
        public String getProductName(int index){
            String product_name = "";
            try{
            String sql = "SELECT product_name from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            product_name = rs.getString("product_name");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return product_name;
        }
        
        public String getProductCategory(int index){
            String product_type = "";
            try{
            String sql = "SELECT product_type from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            product_type = rs.getString("product_type");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return product_type;
        }
        
        public int getQuantity(int index){
            int quantity=0;
            try{
            String sql = "SELECT quantity from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            quantity = rs.getInt("quantity");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return quantity;
        }
        
        public String getProductDescription(int index){
            String description = "";
            try{
            String sql = "SELECT product_description from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            description = rs.getString("product_description");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return description;
        }
        
        public String getProductExpectation(int index){
            String e = "";
            try{
            String sql = "SELECT expectation from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            e = rs.getString("expectation");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return e;
        }
        
        public BufferedImage getProductImage(int index) throws IOException{
            BufferedImage img = null;
            try{
            String sql = "SELECT product_image from userproducts where product_id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        
                        if(rs.next()){
                            
                            Blob blob = rs.getBlob(1);
                            int blobLength = (int) blob.length();
                            byte[] blobAsBytes = blob.getBytes(1, blobLength);
                            img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return img;
        }
        
        
        public void deleteProduct(int index){
        
            try{
                   
                  String sql1 = "DELETE FROM userproducts WHERE product_id = ?";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
	
                  ps1.setInt(1, index);
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
        }
        
        
        //search Tab
        
        
        public String[] getUserSearch(String p_type, int user_id){
            
            items = null;
            productids = null;
            
            try {
                
                List<String> ItemsList = new ArrayList<>();
                List<String> ProductIDs = new ArrayList<>();
                
                
                String sql = "SELECT product_id,product_name from userproducts where product_type = ? and user_id <> ?";
                
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setString(1,p_type);
                ps.setInt(2, user_id);
                
                ResultSet results = ps.executeQuery();
                
                while (results.next()){
                    
                    String id = results.getInt(1)+"";
                    String product = results.getString(2);
                    
                    ProductIDs.add(id);
                    ItemsList.add(product);
                    
                }
                
                productids = new int[ProductIDs.size()];
                items = new String[ItemsList.size()];
                
                
                for(int i = 0; i < ItemsList.size(); i++){
                    productids[i] = Integer.parseInt(ProductIDs.get(i));
                    items[i] = ItemsList.get(i);
                }
                
                
                results.close();
                ps.close();
                 
            } catch (SQLException ex) {
                Logger.getLogger(UserDatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return items; 
        
        }
        
        public int getProductSearchId(int i){
            return productids[i];
        }
        
        
        
        // Notification Tab
        
        //getNotifications
        
        public String[] getNotifications(int id){
            
            messages = null;
            idNots = null;
            
            try {
                
                List<String> NotifsList = new ArrayList<>();
                List<String> NotIDs = new ArrayList<>();
                
                String sql = "SELECT id,message from notifications where to_id = ?";
                
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setInt(1,id);
                
                ResultSet results = ps.executeQuery();
                
                while (results.next()){
                    
                    String ids = results.getInt("id")+"";
                    String msg = results.getString("message");
                    
                    NotIDs.add(ids);
                    NotifsList.add(msg);
                    
                    
                }
                
                messages = new String[NotifsList.size()];
                idNots = new int[NotIDs.size()];
                
                
                for(int i = 0; i < NotifsList.size(); i++){
                    messages[i] = NotifsList.get(i);
                    idNots[i] = Integer.parseInt(NotIDs.get(i));
                }
                
                
                results.close();
                ps.close();
                 
            } catch (SQLException ex) {
                Logger.getLogger(UserDatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return messages; 
        
        }
        
        public int getNotificationIndex(int i){
            return idNots[i];
            
        }
        
        public void sendNotification(int from_id,int to_id,int prd_id, int offered_id){
        
        String msg = "You have a notification from ";
        
        msg+= getUserName(from_id);
            
        try{
                   
                  String sql1 = "INSERT INTO notifications(from_id, to_id, product_id, offered_product_id, message) VALUES (?,?,?,?,?) ";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
	
                  ps1.setInt(1, from_id);
                  ps1.setInt(2, to_id);
                  ps1.setInt(3, prd_id);
                  ps1.setInt(4, offered_id);
                  ps1.setString(5, msg);
                  
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
            
        }
        
        public void ClearNotifications(int user_id){
        
            try{
                   
                  String sql1 = "DELETE FROM `notifications` WHERE to_id = ?";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
	
                  ps1.setInt(1, user_id);
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
            
        
        
        }
        
        public boolean checkNotificationNotAlreadySent(int from_id,int to_id,int prd_id){
            boolean check = false;
                try
		{
			String sql = "SELECT message from notifications where from_id = ? and to_id = ? and product_id = ? ";

			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
	
			ps.setInt(1, from_id);
			ps.setInt(2, to_id);
                        ps.setInt(3, prd_id);
                        
			ResultSet rs = ps.executeQuery();
                        
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Notified Already");
                            check=false;
                        }
                        else{
                            check=true;
                        }
                        
		}
		catch(SQLException | HeadlessException e){
			System.out.println(e);
		}
                
                return check;
            
        }
        
        public int getProductFromNotification(int index){
            int i=0;
            try{
            String sql = "SELECT product_id from notifications where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            i = rs.getInt("product_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return i;
        }
        
        
        public int getOfferedProductFromNotification(int index){
            int i=0;
            try{
            String sql = "SELECT offered_product_id from notifications where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            i = rs.getInt("offered_product_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return i;
        }
        
        public int getTraderFromNotification(int index){
            int i=0;
            try{
            String sql = "SELECT from_id from notifications where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            i = rs.getInt("from_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return i;
        }
        
        
        //Trades tab
        public String[] getTrades(int id){
            
            trades = null;
            tradeids = null;
            
            try {
                
                List<String> TradesList = new ArrayList<>();
                List<String> TradeIDs = new ArrayList<>();
                
                String sql = "SELECT id,status from trades where trader1_id = ? OR trader2_id = ?";
                
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setInt(1,id);
                ps.setInt(2, id);
                
                ResultSet results = ps.executeQuery();
                
                while (results.next()){
                    
                    String ids = results.getInt("id")+"";
                    String msg = results.getString("status");
                    
                    TradeIDs.add(ids);
                    TradesList.add(msg);
                    
                    
                }
                
                trades = new String[TradesList.size()];
                tradeids = new int[TradeIDs.size()];
                
                
                for(int i = 0; i < TradesList.size(); i++){
                    trades[i] = "Trade ID: "+TradeIDs.get(i)+" Status :"+TradesList.get(i);
                    tradeids[i] = Integer.parseInt(TradeIDs.get(i));
                }
                
                
                results.close();
                ps.close();
                 
            } catch (SQLException ex) {
                Logger.getLogger(UserDatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return trades; 
        
        }
        
        public int getTradeIndex(int i){
            return tradeids[i];
            
        }
        
        
        public void addTradeStatus(int user_id, int trader_id, int product1_id, int product2_id, String status){
            
            try{
                
                String sql = "SELECT id from trades where trader1_id = ? AND trader2_id = ? AND product1_id = ? AND product2_id = ?";
                
                // Create a Preparedstatement
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setInt(1, user_id);
                ps.setInt(2, trader_id);
                ps.setInt(3, product1_id);
                ps.setInt(4, product2_id);
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Trade Status Already Obtained");
                }
                else{
                    try{
                    
                        String sql1 = "INSERT INTO trades(trader1_id, trader2_id, product1_id, product2_id, status"
                            + ") VALUES (?,?,?,?,?) ";
                    
                        // Create a Preparedstatement
                        PreparedStatement ps1 = con.prepareStatement(sql1);
                    
                        ps1.setInt(1, user_id);
                        ps1.setInt(2, trader_id);
                        ps1.setInt(3, product1_id);
                        ps1.setInt(4, product2_id);
                        ps1.setString(5, status);
                    
                        ps1.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null, "Trade Status Obtained");
                    
                    }
                    catch(SQLException e){
                        System.out.println(e);
                    }
                }  
            }
              
            catch(SQLException ex){
                  Logger.getLogger(UserDatabaseAccess.class.getName()).log(Level.SEVERE, null,ex);
            }
            
        }
        
        public int getProductOneFromTrade(int index){
            int i=0;
            try{
            String sql = "SELECT product1_id from trades where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            i = rs.getInt("product1_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return i;
        }
        
        public int getProductTwoFromTrade(int index){
            int i=0;
            try{
            String sql = "SELECT product2_id from trades where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            i = rs.getInt("product2_id");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return i;
        }
        
        public String getTradeStatus(int index){
            String status = null;
            try{
            String sql = "SELECT status from trades where id = ?";
			// Create a Preparedstatement
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            status = rs.getString("status");
                        }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            return status;
        }
        
        public void ClearTrades(int user_id){
        
            try{
                   
                  String sql1 = "UPDATE trades SET trader1_id=0 where trader1_id = ?";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
	
                  ps1.setInt(1, user_id);
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
            
            try{
                   
                  String sql1 = "UPDATE trades SET trader2_id=0 where trader2_id = ?";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
	
                  ps1.setInt(1, user_id);
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
            
            try{
                   
                  String sql1 = "DELETE FROM trades WHERE trader1_id = 0 AND trader2_id = 0";

                  // Create a Preparedstatement
                  PreparedStatement ps1 = con.prepareStatement(sql1);
                  ps1.executeUpdate();
                  
            }
            catch(SQLException e){
                  System.out.println(e);
            }
            
            
        
        }
        
        
        
   
        

	

}// end class UserDatabaseAccess