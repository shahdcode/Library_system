/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Supplier extends User {
    private String sName;
    private int contactInfo;
    private static int numOfOrders;

    public  void setNumOfOrders(int numOfOrders) {
        Supplier.numOfOrders = numOfOrders;
    }
    private static double totalRevenue;

    public Supplier(String name, int contactInfo) {
        this.sName = name;
        this.contactInfo = contactInfo;
       // setScontactInfo(contactInfo);
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

    public  int getScontactInfo() {
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
                String title = raf.readUTF();
                String  author = raf.readUTF();
                String  category = raf.readUTF();
                            double price = raf.readDouble();

                System.out.println("Title: " + title+"\t\tAuthor: "+author +"\tCategory: "+category+ "\tPrice: " + price+"$");}} catch (IOException e) {
        }}
public void receiveOrder(Book book, int numberOfCopies) {
    
    double totalCost = 0.0;
 
    String pricesFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sName + "_Prices.dat";
    String infoFileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+ sName + "_info.dat";

    try (DataInputStream pricesInputStream = new DataInputStream(new FileInputStream(pricesFileName));
               RandomAccessFile raf = new RandomAccessFile(pricesFileName, "r")) {

        while (pricesInputStream.available() > 0) {
                String title = raf.readUTF();
                String  author = raf.readUTF();
                String  category = raf.readUTF();
            String storedBookTitle = pricesInputStream.readUTF().trim(); 
                            double price = raf.readDouble();


                   if (storedBookTitle.equals(book.getTitle().trim())) {
                    

                totalCost += (price * numberOfCopies);
               //Librarian.setOrderId(Librarian.getOrderId()+1);
                saveSupplierInfo();
                break;
            }
        }} catch (IOException e) {
                        System.out.println(e);

        }
   totalRevenue=totalRevenue+totalCost;
   setSTotalRevenue(totalRevenue);
                numOfOrders++;
                setNumOfOrders(numOfOrders);
}

    public void  settingPrices() {
        String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + sName + "_Prices.dat";
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            for (int i = 0; i < Book.getBooks().size(); i++) {
                Book book = Book.getBooks().get(i);
                double price = Math.random() * (41) + 10;
               raf.writeUTF( book.getTitle()+"\t");
               raf.writeUTF(book.getAuthor()+"\t");
               raf.writeUTF( book.getCategory()+"\t");

                raf.writeDouble(price);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void displayBooks() {
        String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + sName + "_Prices.dat";
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                String title = raf.readUTF();
                double price = raf.readDouble();
                System.out.println("Title: " + title + "\tPrice: " + price+" $");
            }
        } catch (IOException e) {
            System.out.println(e);
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
            System.out.println(e);
        }
    }

    public void displaySupplierInfo() {


        
    String pricesFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+getSName() + "_info.dat";

    try (DataInputStream pricesInputStream = new DataInputStream(new FileInputStream(pricesFileName));
               RandomAccessFile raf = new RandomAccessFile(pricesFileName, "r")) {

        //while (pricesInputStream.available() > 0) {
                String  name = raf.readUTF();
                int  contact = raf.readInt();
                double price = raf.readDouble();
                int   orders = raf.readInt();
         
                System.out.println("Name: "+name+"\nContact Info: "+contact+"\nRevenue: "+price+"\nNumber Of Orders: "+orders);
            //    String newLinee = inputStream.readUTF();  
          //  }
            }catch (IOException e) {
            System.out.println(e);
        }
            }     

    public void saveSupplierInfo() {
        settingPrices();
             String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + sName + "_info.dat";
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
           
                raf.writeUTF( sName);
                raf.writeInt( contactInfo);
                // writeString(raf,"\t");
                 raf.writeDouble(totalRevenue);
               // writeString(raf, "\t");

                raf.writeInt( numOfOrders);

            
        } catch (IOException e) {
            System.out.println(e);
        }}

  
    public void edit( int con){
 String pricesFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+getSName() + "_info.dat";

    try (DataInputStream pricesInputStream = new DataInputStream(new FileInputStream(pricesFileName));
               RandomAccessFile raf = new RandomAccessFile(pricesFileName, "r")) {


                String  name = raf.readUTF();
                int  contact = raf.readInt();
                double price = raf.readDouble();
                int   orders = raf.readInt();
         
            
        try (RandomAccessFile raf2 = new RandomAccessFile(pricesFileName, "rw")) {
           
                raf2.writeUTF( sName);
                raf2.writeInt( con);
                raf2.writeDouble(price);
                raf2.writeInt( orders);

            
        } catch (IOException e) {
            System.out.println(e);
        }
            }catch (IOException e) {
            System.out.println(e);
        }
    }
}