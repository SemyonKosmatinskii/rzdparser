import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.function.Consumer;

public class DataIterator implements Iterator<Date> {


    private Date dateFrom;
    private Date dateTo;
    private Date currentDate;

    public DataIterator(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.currentDate = dateFrom;
    }

    @Override
    public boolean hasNext() {
        return currentDate.before(dateTo);
    }

    @Override
    public Date next() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        currentDate = cal.getTime();
        return currentDate;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer action) {

    }
}
