import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vechicle{
    private String plateNumber;
    private double rate;
    private boolean rented;

    public Vechicle(String plateNumber,double rate){
        this.plateNumber = plateNumber;
        this.rate = rate;
        this.rented = false;
    }

    public void setPlateNumber(String plateNumber){
        this.plateNumber = plateNumber;
    }
    public void setrate(double rate){
        this.rate = rate;
    }
    public void setrented(boolean rented){
        this.rented = rented;
    }
    public String getplateNumber(){
        return this.plateNumber;
    }
    public double getrate(){
        return this.rate;
    }
    public boolean isrented(){
        return this.rented;
    }

    class Car extends Vechicle{
        private int seatCapacity;

        public Car(String plateNumber,double rate,int seatCapacity){
            super(plateNumber, rate);
            this.seatCapacity = seatCapacity;
        }

        public int getseatCapacity(){
            return this.seatCapacity;
        }
    }

    class Truck extends Vechicle{
       private int seatCapacity;

        public Truck(String plateNumber,double rate,int seatCapacity){
            super(plateNumber, rate);
            this.seatCapacity = seatCapacity;
        }  
    }
}

class Customer{
    private String customerName;
    private String customerId;
    
    public Customer(String customerName,String customerId){
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public void setcustomerName(String customerName){
        this.customerName = customerName;
    }
    public void setcustomerId(String customerId){
        this.customerId = customerId;
    }
    public String getcustomerName(){
        return this.customerName;
    }
    public String getcustomerId(){
        return this.customerId;
    }   
}

class RentList{
    private List<Vechicle> vechicles;
    private List<Customer> customers;

    RentList(){
        this.vechicles  = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void AddVechicle(Vechicle vechicle){
        vechicles.add(vechicle);
    }
    public void AddCustomer(Customer customer){
        customers.add(customer);
    }

    public void Rent(String customerId,String plateNumber){
        private Customer getCustomerbyId(String customerId){
            for(Customer customer : customers){
                if(customer.getcustomerId().equals(customerId)){
                    return customer;
                }
            }
            return null;
        }
        private Vechicle getVechiclebyplateNumber(String plateNumber){
            for(Vechicle vechicle : vechicles){
                if(vechicle.getplateNumber().equals(plateNumber)){
                    return vechicle;
                }
            }
            return null;
        }
        
        public void rentVechicle(String customerId,String plateNumber){
            Customer customer = Rent.getCustomerbyId(customerId);
            Vechicle vechicle = Rent.getVechiclebyplateNumber(plateNumber);
            if(customer != null && vechicle != null && !vechicle.isrented()){
               System.out.println("Vechicle rented successfully!");
               vechicle.setrented = true;
            }
            else{
               System.out.println("Unable to rent");
            }
        }

    }
    
}