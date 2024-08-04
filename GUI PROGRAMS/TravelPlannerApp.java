import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

interface TravelEntity {
    void displayDetails();
}

class Trip implements TravelEntity {
    private String destination;
    private String startDate;
    private String endDate;

    public Trip(String destination, String startDate, String endDate) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Trip to " + destination + " from " + startDate + " to " + endDate);
    }
}

class Expense implements TravelEntity {
    private String category;
    private double amount;

    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    @Override
    public void displayDetails() {
        System.out.println("Expense - " + category + ": $" + amount);
    }
}

class PackingItem implements TravelEntity {
    private String itemName;

    public PackingItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void displayDetails() {
        System.out.println("Packing Item: " + itemName);
    }
}

class TravelPlannerApp extends JFrame {
    private List<TravelEntity> travelEntities = new ArrayList<>();

    public TravelPlannerApp() {
        JButton addTripButton = new JButton("Add Trip");
        JButton addExpenseButton = new JButton("Add Expense");
        JButton addPackingItemButton = new JButton("Add Packing Item");

        addTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = JOptionPane.showInputDialog("Enter Destination:");
                String startDate = JOptionPane.showInputDialog("Enter Start Date:");
                String endDate = JOptionPane.showInputDialog("Enter End Date:");

                Trip trip = new Trip(destination, startDate, endDate);
                travelEntities.add(trip);
                trip.displayDetails();
            }
        });

        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = JOptionPane.showInputDialog("Enter Expense Category:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Expense Amount:"));

                Expense expense = new Expense(category, amount);
                travelEntities.add(expense);
                expense.displayDetails();
            }
        });

        addPackingItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = JOptionPane.showInputDialog("Enter Packing Item:");

                PackingItem packingItem = new PackingItem(itemName);
                travelEntities.add(packingItem);
                packingItem.displayDetails();
            }
        });

        JPanel panel = new JPanel();
        panel.add(addTripButton);
        panel.add(addExpenseButton);
        panel.add(addPackingItemButton);

        add(panel);
        setTitle("Travel Planner");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TravelPlannerApp();
            }
        });
    }
}
