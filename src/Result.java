
import java.io.Serializable;

public class Result implements Serializable, Comparable<Result>{
    
    public static final long serialVersionUID = -938761094876384658L;
    public String url;
    public int score ;
    public int urlID;
    
    public Result(String url, int urlID){
        this.url = url;
        this.urlID = urlID;
        this.score = 1;

    }
    
    public void updateScore(int score){
        //System.out.println("Parameter: " + score);
        //System.out.println("Initial: " + this.score);
        this.score += score;
    }
    
    public void incrementScore(){
        //System.out.println(score);
        this.score++;
        //System.out.println(score);
    }
    
    public int getScore(){
        return score;
    }
    
    public String getURL(){
        return this.url;
    }
    
    public int getURLID(){
        return this.urlID;
    }
    
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Result){
            Result result = (Result) obj;
            if(result.getURLID() == this.urlID){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    
    @Override
    public int compareTo (Result Candidate){
        if(this.getScore() > Candidate.getScore()){
            return -1;
        }
        else if(this.getScore() == Candidate.getScore()){
            return 0;
        }
        else {
            return 1;
        }
    }
    
}
