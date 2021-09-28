import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser {


    public Parser(){}

    public String getBody(Document doc) throws ParseException{
        try {
            Element body = doc.body();
            String content = body.text();
            return content;
        }catch (Exception e){
            throw new ParseException("getBody() failed. Document parameter is null.");
        }
        
    }

    public Document getDocument(String url) throws ParseException{
        if(url == null){
            throw new ParseException("getDocument() failed. String url is null.");
        }
        else if(url.equals("")){
            throw new ParseException("getDocument() failed. String url is empty.");
        }
        try{
            Document doc = Jsoup.connect(url).timeout(3000).get();
            return doc;
        }catch (IOException | IllegalArgumentException e){
            throw new ParseException("getDocument() failed. Connection failed.");
        }

    }

    public Elements getLinks(Document doc) throws ParseException{
        if(doc == null){
            throw new ParseException("getLinks() failed. Document parameter is null.");
        }

        Elements links = doc.select("a[href]");

        return links;
    }
}
