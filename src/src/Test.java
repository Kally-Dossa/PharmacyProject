//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//public class Test {
//
//    StorageManager manager = new StorageManager();
//
//    CategoryMedicine antibiotics = new CategoryMedicine("Antibiotic");
//    CategoryMedicine painkillers = new CategoryMedicine("Painkiller");
//
//    Medicine augmentin = new Medicine("Augmentin", "AUG123", 12.5, 100, antibiotics);
//    Medicine depon = new Medicine("Depon", "DEP456", 3.2, 50, painkillers);
//    Medicine algofren = new Medicine("Algofren", "ALG789", 4.2, 90, painkillers);
//
//
//        manager.addMedicine(augmentin);
//        manager.addMedicine(depon);
//        manager.addMedicine(algofren);
//
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Date date1 = dateFormat.parse("2025-09-10");
//    Date date2 = dateFormat.parse("2025-09-15");
//    Date date3 = dateFormat.parse("2025-09-20");
//
//        manager.recordMovement(new Storage("IMPORT", augmentin, 30, date1));
//        manager.recordMovement(new Storage("EXPORT", depon, 10, date2));
//        manager.recordMovement(new Storage("EXPORT", depon, 5, date3));
//        manager.recordMovement(new Storage("IMPORT", algofren, 60, date1));
//
//        System.out.println("Medicines:");
//        for (Medicine m : manager.getAllMedicines()) {
//        System.out.println("- " + m.getName() + " (" + m.getCode() + ") | Stock: " + m.getStock());
//    }
//
//        System.out.println("\nActions:");
//        for (Storage s : manager.getAllMovements()) {
//        System.out.println("- " + s.getAction() + " | " + s.getMedicine().getName() + " | Count: " + s.getCount() + " | Date: " + dateFormat.format(s.getDateAction()));
//    }
//
//        System.out.println("\n Export for Depon from 2025-09-12 to 2025-09-21:");
//    Date from = dateFormat.parse("2025-09-12");
//    Date to = dateFormat.parse("2025-09-21");
//
//    List<Storage> exports = manager.getExportsForMedicineInDateRange(depon, from, to);
//        for (Storage s : exports) {
//        System.out.println("- " + s.getMedicine().getName() + " | " + s.getCount() + " pieces | " + dateFormat.format(s.getDateAction()));
//    }
//
//}
