import static java.lang.System.out;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
 
public class FileReader {
    public String FileReader(String fileName) throws Exception {
        try{
            readAllBytes(get(fileName));
        }
        catch(Exception err){}
        return new String(readAllBytes(get(fileName)));
    }
}