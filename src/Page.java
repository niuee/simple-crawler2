import java.io.Serializable;
import java.util.Objects;

public class Page implements Serializable, Comparable<Page> {


    private String url;
    private int urlID;
    public static final long serialVersionUID = -1827677255104766839L;

    public Page (String url, int urlID){
        this.url = url;
        this.urlID = urlID;
    }

    public int getURLID(){
        return this.urlID;
    }


    public String getURL(){
        return this.url;
    }


    public boolean equals(Object obj){
        if(obj instanceof Page){
            Page page = (Page) obj;
            if(this.url.equals(page.getURL()) || this.urlID == page.getURLID()){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }

    public int compareTo(Page Candidate){
        if(this.urlID == Candidate.getURLID()){
            return 0;
        }
        else if (this.urlID > Candidate.getURLID()){
            return 1;
        }
        else {
            return -1;
        }
    }


}


