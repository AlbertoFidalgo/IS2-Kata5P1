package kata5p1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailListReader {
    
    public static List<String> read(String fileName) throws FileNotFoundException{
        Scanner scanner = new Scanner(new FileReader(fileName));
        List<String> mailList = new ArrayList<>(); 
        while(scanner.hasNext()){
            String string = scanner.next();
            if(string.contains("@")){
                mailList.add(string);
            }
        }
        
        return mailList;   
    }
}
