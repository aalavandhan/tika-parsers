import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.UidParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class Runner {
	
	public static void main(String [] args){
		Parser parser = new UidParser();
		ContentHandler handler = new BodyContentHandler(new ToXMLContentHandler());
	    Metadata metadata = new Metadata();
	    ParseContext context = new ParseContext(); 
	    
		String fileName = "/users/nithinkrishna/desktop/hello";
		
		try(InputStream stream = new FileInputStream(fileName)){
			metadata.set("source-file-path", fileName);
			
			parser.parse(stream, handler, metadata, context);
			
			System.out.println(handler.toString());
			
			System.out.println(metadata.toString());
			
			stream.close();
		}

		catch(Exception ex) {
            System.out.println(ex.getMessage());                
        }
	}

}
