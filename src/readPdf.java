import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.util.PDFTextStripper;


public class readPdf {
    public boolean pdfword(String filepath,String keyword) throws IOException{
      boolean flag=false;
   try{
  	  FileInputStream  fis=new FileInputStream(filepath); 
      //BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\1.txt"));
      PDFParser   p=   new PDFParser(fis); 
      p.parse();         
      PDFTextStripper  ts=   new   PDFTextStripper();         
      String   s=ts.getText(p.getPDDocument()); 
     //writer.write(s);
      if(check(keyword,s)==true)
    	  flag=true;
     // System.out.println(s); 
      p.getPDDocument().close();
      fis.close();  
      
    	}
    	catch(Exception e){
    		System.out.println("erro!");
    	}
    	finally{
    		
    	}
     // writer.close();
      //System.out.println(keyword+" "+flag+" "+"exist");
   return flag;
    }
    
    public boolean check(String keyword,String s){
    	Pattern pattern=Pattern.compile(keyword);
    	Matcher ma=pattern.matcher(s);
    	return ma.find();
    }
	public static void main(String[] args)throws Exception {
		 /* FileInputStream   fis   =   new   FileInputStream("F:\\1.pdf"); 
          //BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\1.txt"));
          PDFParser   p=   new PDFParser(fis); 
          p.parse();         
          PDFTextStripper  ts=   new   PDFTextStripper();         
          String   s=ts.getText(p.getPDDocument()); 
         //writer.write(s);
          System.out.println(s); 
          fis.close(); 
         // writer.close();*/
		//new readPdf().pdfword("F:\\1.pdf", "wocao");
	}
}
