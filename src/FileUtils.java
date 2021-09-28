import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 4/8/2017.
 */
public class FileUtils {
    
    public FileUtils(){
        
    }
    
    public boolean saveWordTable(List<Word> wordTable, String filePath){
        try {
            if(wordTable == null){
                return false;
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(wordTable);
            oos.close();
            fos.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    
    public boolean savePageTable(List<Page> pageTable, String filePath){
        try{
            if(pageTable == null){
                return false;
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pageTable);
            oos.close();
            fos.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    
    public List<Word> getWordList(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Word> retrieved = (ArrayList<Word>) ois.readObject();
            ois.close();
            fis.close();
            if(retrieved == null){
                return null;
            }
            else {
                return retrieved;
            }
        }catch (Exception e){
            return null;
        }
    }
    
    public List<Page> getPageList(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Page> readPage = (ArrayList<Page>) ois.readObject();
    
            ois.close();
            fis.close();
            if(readPage == null){
                return null;
            }
            else{
                return readPage;
            }
        }catch (Exception e){
            return null;
        }
    }
}
