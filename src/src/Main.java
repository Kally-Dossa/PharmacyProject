import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        StorageManager manager = new StorageManager();
        Scanner scanner = new Scanner(System.in);

        CategoryMedicine antibiotics = new CategoryMedicine("Antibiotic");
        CategoryMedicine painkillers = new CategoryMedicine("Painkiller");
        CategoryMedicine vitamins = new CategoryMedicine("Vitamins");

        System.out.print("Enter name for the medicine: ");
        String name = scanner.nextLine();
        System.out.print("Enter code for the medicine: ");
        String code = scanner.nextLine();
        System.out.print("Enter price for the medicine: ");
        String priceInput = scanner.nextLine();
        double price = 0;
        try {
            price = Double.parseDouble(priceInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for price. Please enter a valid number.");
        }        System.out.print("Enter stock for the medicine: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select category for the medicine:");
        System.out.println("1. Antibiotic");
        System.out.println("2. Painkiller");
        System.out.println("3. Vitamins");
        System.out.print("Enter the number corresponding to the category (1/2/3): ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        CategoryMedicine selectedCategory = null;
        switch (categoryChoice) {
            case 1:
                selectedCategory = antibiotics;
                break;
            case 2:
                selectedCategory = painkillers;
                break;
            case 3:
                selectedCategory = vitamins;
                break;
            default:
                System.out.println("Invalid choice. Setting default category to 'Antibiotic'.");
                selectedCategory = antibiotics;
        }


        Medicine medicine = new Medicine(name, code, price, stock, selectedCategory);
        manager.addMedicine(medicine);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("\nEnter action for the medicine (IMPORT/EXPORT): ");
        String action = scanner.nextLine();
        System.out.print("Enter quantity for the action: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter date for the action (yyyy-MM-dd): ");
        String actionDate = scanner.nextLine();
        Date date = dateFormat.parse(actionDate);

        Storage storageMovement = new Storage(action, medicine, quantity, date);
        manager.recordMovement(storageMovement);

        System.out.println("\nMedicines in the storage:");
        for (Medicine m : manager.getAllMedicines()) {
            System.out.println("- " + m.getName() + " (" + m.getCode() + ") | Stock: " + m.getStock());
        }

        System.out.println("\nStorage movements:");
        for (Storage s : manager.getAllMovements()) {
            System.out.println("- " + s.getAction() + " | " + s.getMedicine().getName() + " | Quantity: " + s.getCount() + " | Date: " + dateFormat.format(s.getDateAction()));
        }

        System.out.print("\nEnter the start date for export query (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();
        Date from = dateFormat.parse(startDate);

        System.out.print("Enter the end date for export query (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();
        Date to = dateFormat.parse(endDate);

        System.out.println("\nExports for " + medicine.getName() + " from " + startDate + " to " + endDate + ":");
        List<Storage> exports = manager.getExportsForMedicineInDateRange(medicine, from, to);
        if (exports.isEmpty()) {
            System.out.println("No exports found for this medicine in the given date range.");
        } else {
            for (Storage s : exports) {
                System.out.println("- " + s.getMedicine().getName() + " | " + s.getCount() + " pieces | " + dateFormat.format(s.getDateAction()));
            }
        }

        scanner.close();

    }
}
