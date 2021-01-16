import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UriHandler {

    private final String URI_TEMPLATE = "https://ticket.rzd.ru/searchresults/v/1/%s/%s/%s";
    private InputHandler inputHandler;
    List<String> uriList = new ArrayList<>();

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public UriHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        SimpleDateFormat uriFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(String from : inputHandler.from) {
            for(String to : inputHandler.to) {
                if (from.equals(to)) continue;
                for (DataIterator it = new DataIterator(inputHandler.fromDate, inputHandler.toDate); it.hasNext(); ) {
                    Date date = it.next();

                    String uri = String.format(URI_TEMPLATE, inputHandler.cityToKeysMap.get(from), inputHandler.cityToKeysMap.get(to), uriFormat.format(date));


                    System.out.println(uri);
                }
            }
        }
    }
}
