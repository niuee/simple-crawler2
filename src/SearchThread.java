/**
 * Created by Vincent on 4/8/2017.
 */
public class SearchThread implements Runnable {
    
    
    public int start;
    public int finish;
    public String [] terms;
    
    public SearchThread(int start, int finish, String [] terms){
        this.start = start;
        this.finish = finish;
        this.terms = terms;
    }
    
    public void run(){
        for(String term: terms){
            Word found =findTerm(term);
                if(found != null) {
                    for (Integer id : found.getList()) {
                        //System.out.println("Term: " + found.getWord());
                        //System.out.println("URLID: " + id);
                        Result r = new Result(Search.pageList.get(id).getURL(), id);
                        if(Search.resultSet != null) {
                            if (Search.resultSet.contains(r)) {
                                //System.out.println("Before: " + Search.resultSet.get(Search.resultSet.indexOf(r)).getScore());
                                //System.out.println("Before: " + Search.resultSet.indexOf(r));
                                Search.resultSet.get(Search.resultSet.indexOf(r)).incrementScore();
                                //System.out.println("After: " + Search.resultSet.get(Search.resultSet.indexOf(r)).getScore());
                                //System.out.println("After: " + Search.resultSet.indexOf(r));
                            } else {
                                Search.resultSet.add(r);
                                //System.out.println("Before: " + Search.resultSet.get(Search.resultSet.indexOf(r)).getScore());
                                //System.out.println("After: " + Search.resultSet.get(Search.resultSet.indexOf(r)).getScore());
                            }
                        }
                        else{
                            //System.out.println("After: " + Search.resultSet.get(Search.resultSet.indexOf(r)).getScore());
                            Search.resultSet.add(r);
                            //System.out.println("After: " + Search.resultSet.get(Search.resultSet.indexOf(r)).getScore());
                        }
                    }
                }
        }
    }
    
    public Word findTerm(String term){
        Word compWord = new Word(term, 0);
        for(int i = start;i <= finish; i++){
            if(Search.wordList.get(i).equals(compWord)){
                //System.out.println(Search.wordList.get(i).getWord());
                return Search.wordList.get(i);
            }
        }
        return null;
    }
}
