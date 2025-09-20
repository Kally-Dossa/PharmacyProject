import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageManager {

    private List<Medicine> medicines;
    private List<Storage> movements;

    public StorageManager() {
        medicines = new ArrayList<>();
        movements = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return medicines;
    }

    public List<Storage> getAllMovements() {
        return movements;
    }

    public void recordMovement(Storage movement) {
        movements.add(movement);

        Medicine med = movement.getMedicine();
        int quantity = movement.getCount();

        if (movement.getAction().equalsIgnoreCase("IMPORT")) {
            med.setStock(med.getStock() + quantity);
        } else if (movement.getAction().equalsIgnoreCase("EXPORT")) {
            med.setStock(med.getStock() - quantity);
        }
    }

    public List<Storage> getExportsForMedicineInDateRange(Medicine medicine, Date from, Date to) {
        List<Storage> result = new ArrayList<>();

        for (Storage m : movements) {
            if (
                    m.getAction().equalsIgnoreCase("EXPORT") &&
                            m.getMedicine().equals(medicine) &&
                            !m.getDateAction().before(from) &&
                            !m.getDateAction().after(to)
            ) {
                result.add(m);
            }
        }

        return result;
    }
}
