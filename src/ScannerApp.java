import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class ScannerApp {


	PreparedStatement pstmt;
	ResultSet rs=null;


	public void SqlStatement() throws FileNotFoundException{
		
		boolean response=true;
		Scanner scan= new Scanner (System.in);
		while(response){
		System.out.println("Enter the Query for the results");
		
		
		String query=scan.nextLine();
		System.out.println("Enter a name for your Query");
		String queryName=scan.nextLine();
		PrintWriter writer=null;
		try {
				
				writer = new PrintWriter(new FileOutputStream ("SavedData.txt",true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				writer.print(queryName + "|" + query );
				writer.flush();
				writer.println();
				writer.flush();
				
				System.out.println("Do you want to continue");
				String resp= scan.nextLine();
				if(resp.equalsIgnoreCase("no")){
					response=false;
				}
				
				
		}

		}

	
	
	
		public void RetrieveStatement(Connection con){
			
			System.out.println("Here are the queries you saved");
			Scanner sc;
			
			try {
			sc = new Scanner(new File("/home/oracle/workspace/SqlScannerApp/SavedData.txt"));
			sc.useDelimiter("\\|");
			
			while(sc.hasNext()){
			
				String name=(sc.next());
				sc.nextLine();
				System.out.println(name);
			}
			
			System.out.println("Enter the Query name you want to execute");
			Scanner scan = new Scanner (System.in);
			String userquery=scan.nextLine();
			
			while(sc.hasNextLine()){
			
				
				String name=(sc.next());
				System.out.println(name);
				
				if(name.equalsIgnoreCase(userquery)){
				System.out.println("matvj found");
				String query =(sc.next());
				System.out.println(query);
				Execute(query,con);
					
		
				}
				
				else{
					System.out.println("Query Not found");
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		}
		
		public void Execute(String x,Connection con){
			try {
				pstmt=con.prepareStatement(x);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					System.out.println(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
