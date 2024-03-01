import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
public class Main{
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String name = "root";
    private static final String password = "";
    public static void main(String[] args)throws ClassNotFoundException,SQLException{
              try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, name, password);
                while(true){
                    System.out.println();
                    System.out.println("1.)Reserve a room");
                    System.out.println("2.)View Reservation");
                    System.out.println("3.)Update Reservation");
                    System.out.println("4.)Delete Reservation");
                    System.out.println("5.)Exit");

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Choice the option");
                    int choice = scanner.nextInt();

                    switch(choice){
                        case 1 : 
                            reserveRoom(connection,scanner);
                            break;
                        case 2 :
                             viewReservation(connection,scanner);
                             break;   
                        case 3 :
                             updateReservation(connection,scanner);
                             break;
                        case 4 :
                            deleteReservation(connection,scanner);
                            break;
                        case 5 :
                             exit();
                             break;
                             
                        case 6: 
                            System.out.println("invalid ");
                            break;     
                    }
                }
              }
              catch(Exception e){
                System.out.println(e.toString());
              }

    }
    private static void  reserveRoom(Connection conn,Scanner sc){
        try{
        System.out.println("enter the Guest name");
         String guestName = sc.next();
        System.out.println("enter the room number");
        int roomNum = sc.nextInt();
        System.out.println("enter the contact number");
        Long contactNum = sc.nextLong();
        String sql = "INSERT INTO reservation (geust_name,room_num,contact_num)"+
        "VALUES('"+guestName+"',"+roomNum+",'"+contactNum+"')";
        Statement stm = conn.createStatement();
        int row = stm.executeUpdate(sql);

        if(row> 0){
            System.out.println("Room reserver");
        }
        else{
            System.out.println("not reserve");
        }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void  viewReservation(Connection conn,Scanner sc){
         try{
            String sql = "SELECT * FROM reservation WHERE 1" ;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getInt("reservation_id"));
                System.out.print("\t");
                System.out.print(rs.getString("geust_name"));
            }
            System.out.println();
         }
         catch(Exception e){
            System.out.println(e);
         }
    }
 
    private static void  updateReservation(Connection conn,Scanner sc){
        try{
           System.out.println("enter the id");
           int reservationId = sc.nextInt();
           
          System.out.println("enter the new guest");
          String newGuestName = sc.next();
          System.out.println("enter the new room");
          int neRoomNum = sc.nextInt();
          System.out.println("enter the newContact Number");
          Long newContactNum = sc.nextLong();
          String sql = "UPDATE reservation SET geust_name = '" + newGuestName + "', " +
          "room_num = " + neRoomNum + ", " +
          "contact_num = '" + newContactNum + "' " +
          "WHERE reservation_id = " + reservationId;
          Statement stm = conn.createStatement();
          int row = stm.executeUpdate(sql);

          if(row>0){
            System.out.println("Record updated successfully!");
          }
          else{
            System.out.println("record not Update");
          }
       }
       catch(Exception e){
        System.out.println(e.getMessage());
       }
    }

    private static void  deleteReservation(Connection conn,Scanner sc){
      try{
        System.out.println("enter the id");
      int id = sc.nextInt();
      String sql = "DELETE FROM reservation WHERE reservation_id ="+id;
      Statement stm =  conn.createStatement();
      int row = stm.executeUpdate(sql);
      if(row>0){
        System.out.println("Delete Success!");
      }
      else{
        System.out.println("not  Delete Success!");
      }
      }
      catch(Exception e){
        System.out.println(e.getMessage());
      }
    }
    public static void exit() throws InterruptedException {
        System.out.print("Exit Hotel");
        int i =5;
        while(i != 0){
            System.out.print(".\t");
            i--;
            Thread.sleep(1000);
        }
        System.out.println("Thank you  for using  our services!");
    }
}