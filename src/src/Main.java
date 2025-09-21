import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        StorageManager manager = new StorageManager();
        Scanner scanner = new Scanner(System.in);

        CategoryMedicine antibiotics = new CategoryMedicine("Antibiotic");
        CategoryMedicine painkillers = new CategoryMedicine("Painkiller");
        CategoryMedicine vitamins = new CategoryMedicine("Vitamins");
        CategoryMedicine skincare = new CategoryMedicine("Skin care");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Create a new medicine");
            System.out.println("2. View all medicines in the storage");
            System.out.println("3. Record a storage movement (import/export) and update stock");
            System.out.println("4. View export movements for a specific medicine and date range");
            System.out.println("5. Exit");

            System.out.print("\nEnter a number (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the medicine: ");
                    String name = scanner.nextLine().trim();;
                    if (name.isEmpty()) {
                        System.out.println("Error: Medicine name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter the medicine code: ");
                    String code = scanner.nextLine().trim();;
                    if (code.isEmpty()) {
                        System.out.println("Error: Medicine code cannot be empty.");
                        break;
                    }

                    if (manager.isMedicineCodeExists(code)) {
                        System.out.println("Error: A medicine with this code already exists.");
                        break;
                    }

                    System.out.print("Enter the price of the medicine: ");
                    String priceInput = scanner.nextLine().trim();
                    if (priceInput.isEmpty()) {
                        System.out.println("Error: Price cannot be empty.");
                        break;
                    }
                    double price = 0;
                    try {
                        price = Double.parseDouble(priceInput);
                        if (price <= 0) {
                            System.out.println("Error: Price must be a positive value.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid price input. Please enter a valid number.");
                        break;
                    }

                    System.out.print("Enter stock for the medicine: ");
                    String stockInput = scanner.nextLine().trim();
                    if (stockInput.isEmpty()) {
                        System.out.println("Error: Stock cannot be empty.");
                        break;
                    }

                    int stock = 0;
                    try {
                        stock = Integer.parseInt(stockInput);
                        if (stock < 0) {
                            System.out.println("Error: Stock cannot be negative.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid stock input. Please enter a valid integer.");
                        break;
                    }

                    System.out.println("Select a category for the medicine:");
                    System.out.println("1. Antibiotic");
                    System.out.println("2. Painkiller");
                    System.out.println("3. Vitamins");
                    System.out.println("4. Skin care");
                    System.out.print("Enter the category number (1/2/3/4): ");
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
                        case 4:
                            selectedCategory = skincare;
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to 'Antibiotic'.");
                            selectedCategory = antibiotics;
                    }

                    Medicine medicine = new Medicine(name, code, price, stock, selectedCategory);
                    manager.addMedicine(medicine);
                    System.out.println("Medicine added to the storage.");

                    break;

                case 2:
                    System.out.println("\nMedicines in storage:");
                    for (Medicine m : manager.getAllMedicines()) {
                        System.out.println("- " + m.getName() + " (" + m.getCode() + ") | Stock: " + m.getStock());
                    }
                    break;

                case 3:
                    System.out.print("\nEnter action for the medicine (IMPORT/EXPORT): ");
                    String action = scanner.nextLine().trim().toUpperCase();

                    if (!action.equals("IMPORT") && !action.equals("EXPORT")) {
                        System.out.println("Error: Invalid action. Please enter either 'IMPORT' or 'EXPORT'.");
                        break;
                    }

                    System.out.print("Enter quantity for the action: ");
                    String quantityInput = scanner.nextLine().trim();
                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(quantityInput);
                        if (quantity <= 0) {
                            System.out.println("Error: Quantity must be a positive integer.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid quantity input. Please enter a valid integer.");
                        break;
                    }

                    System.out.print("Enter date for the action (yyyy-MM-dd): ");
                    String actionDate = scanner.nextLine().trim();
                    Date date = null;
                    try {
                        date = dateFormat.parse(actionDate);
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid date format. Please enter the date in the format 'yyyy-MM-dd'.");
                        break;
                    }

                    System.out.println("\nSelect the medicine:");
                    for (Medicine m : manager.getAllMedicines()) {
                        System.out.println("- " + m.getName() + " (" + m.getCode() + ")");
                    }
                    System.out.print("Enter the medicine code: ");
                    String selectedCode = scanner.nextLine().trim();

                    Medicine selectedMedicine = manager.getMedicineByCode(selectedCode);
                    if (selectedMedicine != null) {
                        Storage storageMovement = new Storage(action, selectedMedicine, quantity, date);
                        manager.recordMovement(storageMovement);
                        System.out.println("The movement has been recorded.");
                    } else {
                        System.out.println("Error: No medicine found with the given code.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter the medicine code: ");
                    String codeForExport = scanner.nextLine();

                    Medicine exportMedicine = manager.getMedicineByCode(codeForExport);
                    if (exportMedicine != null) {
                        System.out.print("Enter start date for the search (yyyy-MM-dd): ");
                        String startDate = scanner.nextLine();
                        Date from = dateFormat.parse(startDate);

                        System.out.print("Enter end date for the search (yyyy-MM-dd): ");
                        String endDate = scanner.nextLine();
                        Date to = dateFormat.parse(endDate);

                        List<Storage> exports = manager.getExportsForMedicineInDateRange(exportMedicine, from, to);
                        if (exports.isEmpty()) {
                            System.out.println("No exports found for this medicine in the given date range.");
                        } else {
                            System.out.println("\nExports for " + exportMedicine.getName() + " from " + startDate + " to " + endDate + ":");
                            for (Storage s : exports) {
                                System.out.println("- " + s.getAction() + " | " + s.getMedicine().getName() + " | Quantity: " + s.getCount() + " | Date: " + dateFormat.format(s.getDateAction()));
                            }
                        }
                    } else {
                        System.out.println("No medicine found with that code.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
                    break;
            }

        }
    }
}
