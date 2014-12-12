/*FileRead主要用于实现对PDF WORD文件的读取操作*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class FileRead {
	/*public boolean readTable(String filePath,String compileName,String keyword){
		boolean flag=false;
		try {
			FileInputStream fis=new FileInputStream(filePath);
			POIFSFileSystem pfs=new POIFSFileSystem(fis);
			HWPFDocument hwpf=new HWPFDocument(pfs);
			String str=null;
			Pattern pattern=Pattern.compile(compileName);
			
			//读取文档范围,生成迭代器
			Range range=hwpf.getRange();
			TableIterator table=new TableIterator(range);
			//迭代文档中的表格
			while(table.hasNext()){
				Table tableAll=(Table)table.next();
				//迭代行
				for(int i=0;i<tableAll.numRows();i++){
					TableRow tr=tableAll.getRow(i);
				//迭代列
					for(int j=0;j<tr.numCells();j++){
						TableCell tc=tr.getCell(j);
						
						//取得单元格内的内容
						for(int k=0;k<tc.numParagraphs();k++){
							//读取单元格中的内容
							Matcher ma=pattern.matcher(keyword);
							if(ma.find()==true){
								flag=true;
								break;
							}
						}}
				}	
			}	
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return flag==true;
	}
	*/
	public boolean readFromWord(String filepath,String keyword){
		boolean flag=false;
		Pattern pattern=Pattern.compile(keyword);
	
		try {
			InputStream is= new FileInputStream(filepath);
			WordExtractor ex=new WordExtractor(is);
			String text=ex.getText();	
			Matcher ma=pattern.matcher(text);
			System.out.println(text+(flag=ma.find()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	public static void main(String[] args) {
		String filepath="F:\\1.doc";
		String keyword="";
		new FileRead().readFromWord(filepath, keyword);
	}

}
