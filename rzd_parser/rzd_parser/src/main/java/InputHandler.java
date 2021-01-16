import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputHandler {
    List<String> from = new ArrayList<>();
    List<String> to = new ArrayList<>();
    Map<String, String> cityToKeysMap = new HashMap<>();
    Date fromDate;
    Date toDate;


    public InputHandler() throws IOException, ParseException {
        readInput();
        readCityToKeys();
    }

    private void readCityToKeys() throws IOException {
        BufferedReader keysReader = new BufferedReader(new FileReader("rzdCityKeys.txt"));
        String u = keysReader.readLine();
        while (u != null) {
            cityToKeysMap.put(u.split("-")[0], u.split("-")[1]);
            u = keysReader.readLine();
        }
    }

    private void readInput() throws IOException, ParseException {
        BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"));
        String fromStr = fileReader.readLine();
        String toStr = fileReader.readLine();
        String dateStr = fileReader.readLine();
        from.addAll(Arrays.asList(fromStr.split(", ")));
        to.addAll(Arrays.asList(toStr.split(", ")));
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        fromDate = format.parse(dateStr.split("-")[0]);
        toDate = format.parse(dateStr.split("-")[1]);
    }

    public static void main(String[] args) throws IOException, ParseException {
        InputHandler inputHandler = new InputHandler();
        UriHandler uriHandler = new UriHandler(inputHandler);
    }
}
