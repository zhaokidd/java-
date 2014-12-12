import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
public class Fileseacher {
	private String dir;
	private String ext;
	private File filePath;
	
	ListIterator<File> iterPdf;
	ListIterator<File> iterWord;
	
	//存储所有符合条件的文件的信息
	static ArrayList<File> recordFileOfPdf;
	static ArrayList<File> recordFileYesOfPdf;
	static ArrayList<File> recordFileOfWord;
	static ArrayList<File> recordFileYesOfWord;
	
	ArrayList<File> recordFileOfPdfNotselect;
	ArrayList<File> recordFileOfWordNotselect;
	//constructor,在构造器中完成对指定根目录的搜索
	public Fileseacher(String dir){
		this.dir=dir;
		recordFileOfPdf=new ArrayList<File>();
		recordFileOfWord=new ArrayList<File>();
		recordFileYesOfPdf= new ArrayList<File>();
		recordFileYesOfWord= new ArrayList<File>();
		
		recordFileOfPdfNotselect=new ArrayList<File>();
		recordFileOfWordNotselect=new ArrayList<File>();
		recurseFileOfPdf(new File(dir),".*\\.pdf");
		iterPdf=recordFileOfPdf.listIterator();
		
		recurseFileOfWord(new File(dir),".*\\.doc");
		iterWord=recordFileOfWord.listIterator();
	}
	
	//local返回ROOT目录下所有文件名后缀符合EXT格式的文件的集合
	public static File[]
    local(File root,final String ext){
		return root.listFiles(new FilenameFilter() {
			private Pattern pattern=Pattern.compile(ext);
			public boolean accept(File root, String name) {	
				return pattern.matcher(name).matches();
			}
		});
	}
	//local(overload),第一个参数变为ROOT目录的路径名
	public File[]
	local(String rootPath,String ext){
		return local(new File(rootPath),ext);
	}
	
	//递归访问文件夹及子文件夹直到所有文件都被遍历 ，得到所有pdf文件
	public static void recurseFileOfPdf(File path,String regex){
		for(File file:path.listFiles()){
		
		    if(file.isDirectory()){
				recurseFileOfPdf(file,regex);
			}
			else if(file.getName().matches(regex)){
				//System.out.println("Is File!!!");
				recordFileOfPdf.add(file);
				//System.out.println(file.toString());
			}
		}
	}
	//遍历得到所有word文件
	public static void recurseFileOfWord(File path,String regex){
		for(File file:path.listFiles()){
		
		    if(file.isDirectory()){
				recurseFileOfWord(file,regex);
			}
			else if(file.getName().matches(regex)){
				//System.out.println("Is File!!!");
				recordFileOfWord.add(file);
				//System.out.println(file.toString());
			}
		}
	}
	//打印存储的所有文件的信息
	public void printFile(ArrayList<File> recordFile){
		for(int i=0;i<recordFile.size();i++)
			System.out.println(recordFile.get(i).getAbsoluteFile());
	}
	
	public static void main(String[] args) {
		
	}

}
