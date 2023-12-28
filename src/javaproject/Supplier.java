package javaproject;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Supplier extends User {
    private String sName;
    private static int contactInfo;
    private static int numOfOrders;

    public  void setNumOfOrders(int numOfOrders) {
        Supplier.numOfOrders = numOfOrders;
    }
    private static double totalRevenue;

    public Supplier(String name, int contactInfo) {
        this.sName = name;
        this.contactInfo = contactInfo;
        setScontactInfo(contactInfo);
        this.totalRevenue = 0.0;
        this.numOfOrders = 0;
        //settingPrices();
    }

    public void setSTotalRevenue(double totalRevenue) {
        Supplier.totalRevenue = totalRevenue;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public void setScontactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Supplier(String name) {
 
       this.sName = name;
        this.contactInfo =getScontactInfo();

      //  settingPrices();

    }

    public static int getSNumberOfOrders() {
        return numOfOrders;
    }

    public String getSName() {
        return sName;
    }

    public static int getScontactInfo() {
        return contactInfo;
    }

    public double getSTotalRevenue() {
        return totalRevenue;
    }

    public int getNumberOfOrders() {
        return numOfOrders;
    }

    public boolean searchBook(String title) {
        boolean found = false;
        if (Book.searchBook(title) != null) {
            found = true;
        }
        return found;
    }
public void displayPrices(){
        String pricesFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sName + "_Prices.dat";

    try (DataInputStream pricesInputStream = new DataInputStream(new FileInputStream(pricesFileName));
               RandomAccessFile raf = new RandomAccessFile(pricesFileName, "r")) {

        while (pricesInputStream.available() > 0) {
                String title = readString(raf);
                String  author = readString(raf);
                String  category = readString(raf);
          //  String storedBookTitle = pricesInputStream.readUTF().trim(); 
                            double price = raf.readDouble();

                System.out.println("Title: " + title+"\t\tAuthor: "+author +"\tCategory: "+category+ "\tPrice: " + price+"$");}} catch (IOException e) {
           // System.err.println("Error reading file2: " + e.getMessage());
        }}
public void receiveOrder(Book book, int numberOfCopies) {
                  //  settingPrices();
   //totalRevenue=0;
    double totalCost = 0.0;
   // if(Admin.searchSupplier( sName)){
    String pricesFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sName + "_Prices.dat";
    String infoFileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+ sName + "_info.dat";

    try (DataInputStream pricesInputStream = new DataInputStream(new FileInputStream(pricesFileName));
               RandomAccessFile raf = new RandomAccessFile(pricesFileName, "r")) {

        while (pricesInputStream.available() > 0) {
                String title = readString(raf);
                String  author = readString(raf);
                String  category = readString(raf);
            String storedBookTitle = pricesInputStream.readUTF().trim(); 
                            double price = raf.readDouble();

             //   System.out.println("Title: " + title+"\t\tAuthor: "+author +"\tCategory: "+category+ "\tPrice: " + price+"$");

                   if (storedBookTitle.equals(book.getTitle().trim())) {
                    

                totalCost += (price * numberOfCopies);
               Admin.setOrderId(Admin.getOrderId()+1);
                saveSupplierInfo();
                break;
            }
        }} catch (IOException e) {
            System.err.println("Error reading file2: " + e.getMessage());
        }
   totalRevenue=totalRevenue+totalCost;
   setSTotalRevenue(totalRevenue);
     // setSTotalRevenue(getSTotalRevenue() +totalCost) ;
     // System.out.println(totalRevenue);
                numOfOrders++;
                setNumOfOrders(numOfOrders);
}

    public void  settingPrices() {
        String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + sName + "_Prices.dat";
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            for (int i = 0; i < Book.getBooks().size(); i++) {
                Book book = Book.getBooks().get(i);
                double price = Math.random() * (41) + 10;
                writeString(raf, book.getTitle()+"\t");
                writeString(raf, book.getAuthor()+"\t");
                writeString(raf, book.getCategory()+"\t");

                raf.writeDouble(price);
            }
        } catch (IOException e) {
            System.out.println("Error saving prices to file: " + e.getMessage());
        }
    }

    public void displayBooks() {
        String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + sName + "_Prices.dat";
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                String title = readString(raf);
                double price = raf.readDouble();
                System.out.println("Title: " + title + "\tPrice: " + price+" $");
            }
        } catch (IOException e) {
            System.err.println("Error reading file5: " + e.getMessage());
        }
    }


    public void displayOrders() {
        String ordersFileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + sName + "_Orders.dat";

        try (RandomAccessFile raf = new RandomAccessFile(ordersFileName, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String orderInfo = raf.readUTF();
                System.out.println(orderInfo);
            }
        } catch (IOException e) {
            System.out.println("Error displaying orders: " + e.getMessage());
        }
    

    }

    public void displaySupplierInfo() {
        String infoFileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" +getSName() + "_Info.dat";

        try (DataInputStream infoInputStream = new DataInputStream(new FileInputStream(infoFileName))) {
         
            System.out.println("Name: " + sName);
            System.out.println("Contact Info: " + getScontactInfo());
            System.out.println("Total Revenue: " + totalRevenue);
            System.out.println("Number of Orders: " + numOfOrders);
        } catch (IOException e) {
            System.out.println("Error displaying supplier information: " + e.getMessage());
        }
    }

    public void saveSupplierInfo() {
        settingPrices();
           try (RandomAccessFile raf = new RandomAccessFile( "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+getSName() + "_info.dat", "rw")) {
            raf.seek(raf.length());

            raf.writeUTF( "name: "+ sName+"\tcontactinfo: " + contactInfo + "\trevenue: "+totalRevenue+"\tnumoforders: " + numOfOrders+"\n");
        } catch (IOException e) {
            System.err.println("Error reading file1: " + e.getMessage());
        }
            
    }

    private void writeString(RandomAccessFile raf, String value) throws IOException {
        raf.writeUTF(value);
    }

    private String readString(RandomAccessFile raf) throws IOException {
        return raf.readUTF();
    }
    public void edit(){
this.totalRevenue=getSTotalRevenue();  
this.numOfOrders=getNumberOfOrders();
    }
}
