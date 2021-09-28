import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class Crawler {

    public MyQueue toParse;
    public static List<Page> parsed = new ArrayList<>();
    public static List<String> visited = new ArrayList<String>();
    public static List<Word> words = new ArrayList<>();
    public static Parser parser = new Parser();
    public static int currentID;
    public static int totalURLs;
    public static int limit;
    public static String domain;
    
    public Crawler(String seed, String domain, int limit){
        this.domain = domain;
        this.limit = limit;
        toParse = new MyQueue();
        this.addToQueue(seed);
        totalURLs = 0;
        //System.out.println("This is the seed: " + seed);
        currentID = 0;
    }
    
    
    public void crawl(){
        currentID = 0;
        while(!toParse.isEmpty() && currentID <= limit) {
            System.out.println((currentID + 1) + " / " + limit + ", toParse size: " + toParse.size());
            try {
                String link = (String) toParse.peek().getData();
                Document doc = Jsoup.connect(link).timeout(3000).get();
                parse(doc, currentID);
                toParse.remove();
                visited.add(link);
                Page newPage = new Page(link,currentID);
                parsed.add(newPage);
                //System.out.println("This is the " + currentID + " page.");
                //System.out.println("URL: " + link);
                currentID++;
            } catch (Exception e) {
                toParse.remove();
                //System.out.println("ID not updated!");
            }
        }
        //System.out.println("How many words: " + words.size());
        for(Word word: words){
            //System.out.println("Word: " + word.getWord());
            for(int id: word.getList()){
                //System.out.println("ID: " + id);
            }
        }
        FileUtils fus = new FileUtils();
        fus.savePageTable(parsed, "parsed.txt");
        fus.saveWordTable(words, "words.txt");
    }
    
    
    public boolean parse(Document doc, int id){
        parseLinks(doc);
        parseText(doc, id);
        return true;
    }
    
    public void parseLinks(Document doc){
        Elements parsedLinks = new Elements();
        try {
            parsedLinks = parser.getLinks(doc);
            for(Element link: parsedLinks){
                if(isInDomain(link.attr("abs:href")) && isValidURL(link.attr("abs:href"))
                        && !visited.contains(link.attr("abs:href"))){
                    addToQueue(link.attr("abs:href"));
                }
            }
            totalURLs = toParse.size();
        }catch (ParseException e){
            //System.out.println("Parse Links failed!");
        }
    }
    
    
    public void parseText(Document doc, int id){
        try {
            String context = parser.getBody(doc);
            context = context.replaceAll("[^A-Za-z]+", " ");
            String [] str = context.split("\\s+");
            for(String comparing : str){
                //System.out.println("Comparing: " + comparing);
                addWordToList(comparing, id);
            }
        }catch (ParseException e){
            //System.out.println("Parse Text went wrong!");
        }
    }
    
    
    public void addWordToList(String word, int id){
        word = word.toLowerCase();
        Word newWord = new Word(word, id);
        if(words.contains(newWord)){
            words.get(words.indexOf(newWord)).addURLID(id);
        }
        else {
            words.add(newWord);
        }
    }
    
    
    public void addToQueue(String url){
        if (toParse.size() == 0){
            toParse.add(url);
            //totalURLs ++;
            //System.out.println(url);
        }
        else if(currentID <= limit){
            toParse.add(url);
            totalURLs++;
            //System.out.println(url);
        }
    }
    
    
    public void addPageToList(Page p){
        parsed.add(p);
    }
    
    
    public boolean isInDomain(String url){
        if(url.contains(this.domain)){
            return true;
        }
        else {
            return false;
        }
    }
    
    
    public boolean isValidURL(String url){
        if(url.startsWith("http://") || url.startsWith("https://")){
            return true;
        }
        else{
            return false;
        }
    }
}
