import java.util.Date;

public class Storage {
    private String action;
    private Medicine medicine;
    private int count;
    private Date dateAction;

    public Storage ( String action, Medicine medicine, int count, Date dateAction){
        this.action = action;
        this.medicine = medicine;
        this.count = count;
        this.dateAction = dateAction;
    }

    public String getAction() {
        return action;
    }

    public Medicine getMedicine() {
        return medicine;
    }


    public int getCount() {
        return count;
    }

    public Date getDateAction() {
        return dateAction;
    }
}
