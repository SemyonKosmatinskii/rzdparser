package mainLesson;

import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        LocalDate start = LocalDate.parse("2021-01-01");
        LocalDate end = LocalDate.parse("2021-06-01");

	for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1))
        System.out.println(date);

    }
}
