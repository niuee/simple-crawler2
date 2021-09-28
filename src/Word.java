import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable{

    private String word;
    private List<Integer> postings = new ArrayList<>();
    private int initialUrlID;
    public static final long serialVersionUID = -3696191086353573895L;

    public Word (String word, int urlID){
        this.word = word;
        this.initialUrlID = urlID;
        postings.add(0,urlID);
    }

    public void addURLID(int urlID){

        postings.add(urlID);
    }


    public boolean equals(Object obj){
        if(obj instanceof Word){
            Word word = (Word) obj;
            if(this.word.equals(word.getWord())){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public String getWord(){
        return this.word;
    }

    public List<Integer> getList(){
        return postings;
    }


//    public static void main (String [] args){
//
//        Word w = new Word("test",1);
//        Word w1 = new Word("test", 2);
//
//        System.out.println(w.equals(w1));
//    }

}
