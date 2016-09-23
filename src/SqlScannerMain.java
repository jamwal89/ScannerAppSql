import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class SqlScannerMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		ScannerApp sa =new ScannerApp();
		Connection con = null;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
		Scanner sc = new Scanner (System.in);
		System.out.println("This is App is to enter and save your Queries?");
		
			boolean answer= true;
				while(answer){
						sa.SqlStatement();
						System.out.println("Do you want to enter and save more Queries?");
						
						String response = sc.nextLine();
						if(response.equalsIgnoreCase("no")){
							System.out.println("Your queries have been saved");
							answer=false;
							break;
						}
		}
				System.out.println("Do you want to execute queries");
				String option =sc.nextLine();
				if(option.equalsIgnoreCase("yes")){
					sa.RetrieveStatement(con);
				}
				else{
					System.out.println("Thank You");
				}

	}

}
