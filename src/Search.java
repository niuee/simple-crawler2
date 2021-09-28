import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vincent on 4/9/2017.
 */
public class Search {
    
    public static List<Page> pageList;
    public static List<Word> wordList;
    public static List<Result> resultSet;
    
    private String wordListFile;
    private String pageListFile;
    
    public Search(String wordListFile, String pageListFile){
        this.wordListFile = wordListFile;
        this.pageListFile = pageListFile;
        resultSet = Collections.synchronizedList(new ArrayList<Result>());
        setUp(wordListFile,pageListFile);
    }
    
    public void setUp(String wordListFile, String pageListFile){
        FileUtils fus = new FileUtils();
        wordList = (ArrayList<Word>) fus.getWordList(wordListFile);
        pageList = (ArrayList<Page>) fus.getPageList(pageListFile);
    }
    
    public void nullCheck(){
        if(wordList == null || pageList == null){
            setUp(wordListFile,pageListFile);
        }
    }
    
    public void sort(){
        Collections.sort(resultSet);
    }
    
    public List<Result>  executeQuery(String query){
        //System.out.println("Result test get to here ");
        String [] terms = query.split("\\s+");
        for(int i = 0; i < terms.length;i++) {
            terms[i] = terms[i].toLowerCase();
        }
        nullCheck();
        int wordSize = wordList.size();
        int splitInterval;
        //int overflow;
        Thread SearchThreads [] = new Thread [5];
        splitInterval = wordSize / 5 - 1;
        
        SearchThreads[0] = new Thread(new SearchThread(0,splitInterval,terms));
        SearchThreads[1] = new Thread(new SearchThread(splitInterval + 1, 2*splitInterval + 1, terms));
        SearchThreads[2] = new Thread(new SearchThread(2*splitInterval + 2, 3*splitInterval + 2, terms));
        SearchThreads[3] = new Thread(new SearchThread(3*splitInterval + 3, 4*splitInterval+3, terms));
        SearchThreads[4] = new Thread(new SearchThread(4*splitInterval + 4, wordSize - 1 , terms));
        
        for(Thread SearchThread: SearchThreads) {
            SearchThread.start();
        }
        
        for(Thread SearchThread: SearchThreads){
            try {
                SearchThread.join();
            }catch (InterruptedException e){
                //System.out.println("Thread Join Fails!");
            }
        }
        
        Collections.sort(resultSet);
        
        for(Result result:resultSet){
            //System.out.println("URL: " + result.getURL());
            //System.out.println("Score: " + result.getScore());
        }
        
        return resultSet;
    }
}
