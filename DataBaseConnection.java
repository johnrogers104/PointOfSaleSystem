//John Rogers (the j-tees)
// cse216
// DBConnection
package ProcessSale;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection {
    private static Connection con;
    private static DataBaseConnection db = null;
    /*
      The DataBaseConnection constructor, which opens a connection to our database.
      Note that the connection MUST be closed by the close() method listed below after
      the DataBaseConnection object that this constructor creates is done being used.
     */

    private DataBaseConnection(){
	try{
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", "jeg416cse216", "jtees216");   
	}
	catch(ClassNotFoundException ex){
	    System.out.println("error with driver");
	}
	catch(SQLException ex){
	    System.out.println("log in error");
	}
    }
    
    /*
      static method to return the single instance of DataBaseConnection to be used
      by our system.  
     */

    public static DataBaseConnection getInstance(){
	if(db == null){
	    db = new DataBaseConnection();
	    return db;
	}
	else{
	    return db;
	}
    }

    /*
      The close() method is a simple method to close the connection
      to the database that was opened when we create a new 
      DataBaseConnection object.  Returns true if success, false
      on failure.
     */

    public void commit(){
	try{
	    con.commit();
	}
	catch(SQLException ex){
	    System.out.println("could not commit!");
	    System.exit(1);
	}
    }

    
    public boolean close(){
	try{
	    con.close();
	    return true;
	}
	catch(SQLException ex){
	    System.out.println("error closing connection");
	    return false;
	}
    }
  
    /*
      newUpdateQuery (below) is the method to call when an we  want
      to perform a query that in any way updates a relation. This 
      can be either an insert, delete, or update query (not a generic
      select ... where ... from ... query). Does not return anything.
     */
    public boolean newUpdateQuery(String q){
	try{
	    Statement s = con.createStatement();
	    s.executeUpdate(q);
	    s.close();
	    commit();
	}
	catch(SQLException ex){
	    ex.printStackTrace(System.out);
	    return false;
	}
	return true;
    }

    /*
      The newQuery() method is for those generic select ... from ...
      where ... queries that needs to return a ResultSet.  Since returning
      ResultSets causes problems with keeping statements open, and the event in
      which a query returns an empty result (since ResultSets can not be null), 
      this method instead places the result relation in an ArrayList of ArrayLists, 
      so that statements can be properly closed and there is no data leakage of 
      relations between classes. Note that in order to do this, you must provide 
      with the query the number of columns that the query returns (the number of 
      attributes in the select clause) so that the inner ArrayList (which simulates 
      each row) knows how long it needs to be.
     */

    public ArrayList<ArrayList<String>> newQuery(String q, int count){
	ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	try{
	    Statement s = con.createStatement();
	    ResultSet r = s.executeQuery(q);
	    ResultSetMetaData rsmd = r.getMetaData();
	    while(r.next()){
		ArrayList<String> row = new ArrayList<String>();
		for(int i=1; i<= count; i++){
		    row.add(r.getString(rsmd.getColumnName(i)));
		}
		table.add(row);
	    }
	    s.close();
	    return table;
      	}
	catch(SQLException ex){
	    ex.printStackTrace();
	}
	table = null;
	return table;
    }

    public boolean isInDataBase(String str, String tableName){
	try{
	    Statement s = con.createStatement();
	    ResultSet r = s.executeQuery("select * from "+tableName);
	    ResultSetMetaData rsmd = r.getMetaData();
	    String primaryKey = rsmd.getColumnName(1);
	    String query = "select * from "+tableName+" where "+primaryKey+" = '"+str+"'";
	    int c = rsmd.getColumnCount();
	
	    if(!newQuery(query, c).isEmpty()){
		return true;
	    }
	    else{
		return false;
	    }
	}
	catch(SQLException ex){
	    System.out.println("error in isInDataBase");
	    return false;
	}
    }

    public boolean verifyMatch(String attribute1, String attribute2, String tableName, String columnName1, String columnName2){
	try{
	    Statement s = con.createStatement();
	    ResultSet r = s.executeQuery("select * from "+tableName);
	    ResultSetMetaData rsmd = r.getMetaData();
	    int c = rsmd.getColumnCount();
	    String query = "select * from "+tableName+" where "+columnName1+" = '"+attribute1+"' and "+columnName2+" = '"+attribute2+"'";
	    if(!newQuery(query, c).isEmpty()){
		return true;
	    }
	    else{
		return false;
	    }
	}
	catch(SQLException ex){
	    System.out.println("error in verifyMatch");
	    return false;
	}
    }
<<<<<<< Updated upstream
}
=======
} 
>>>>>>> Stashed changes
