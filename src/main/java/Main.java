import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        log.setUseParentHandlers(false);
        Handler fileHandler = new FileHandler("Logs",true);
        log.addHandler(fileHandler);

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите адрес web-страницы");
        String url = sc.nextLine();//https://www.simbirsoft.com/

        try {

            Document document = Jsoup.connect(url).get();
            Elements elements = document.getAllElements();

            String str = elements.text();
            String[] words = str.split("[' ', ',', '.', '! ', '?','\"', ';', ':', '[', ']', '(', ')', '\\n', '\\r', '\\t']");

            Statistics statistics = new Statistics();

            for(String word : words){
                statistics.addRegisterOn(word);//.addRegisterOff(word) - уникальное слово без учета регистра
            }

            try {
                MySqlDatabase mySqlDatabase = new MySqlDatabase();
                mySqlDatabase.dropTable();
                mySqlDatabase.createTable();
                mySqlDatabase.addInTable(statistics.getMap());
                //System.out.println(mySqlDatabase.showTable());

            } catch (SQLException | ClassNotFoundException throwables ) {
                log.log(Level.SEVERE,"Exception: ", throwables);
            } finally {
                FileWriter fileWriter = new FileWriter("Statistics.txt");
                fileWriter.write(statistics.printStr());
                fileWriter.close();
            }
        } catch (IOException e) {
            log.log(Level.INFO,"Exception: ", e);
        }
    }
}
