import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
public class Fileseacher {
	private String dir;
	private String ext;
	private File filePath;
	
	ListIterator<File> iterPdf;
	ListIterator<File> iterWord;
	
	//�洢���з����������ļ�����Ϣ
	static ArrayList<File> recordFileOfPdf;
	static ArrayList<File> recordFileYesOfPdf;
	static ArrayList<File> recordFileOfWord;
	static ArrayList<File> recordFileYesOfWord;
	
	ArrayList<File> recordFileOfPdfNotselect;
	ArrayList<File> recordFileOfWordNotselect;
	//constructor,�ڹ���������ɶ�ָ����Ŀ¼������
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
	
	//local����ROOTĿ¼�������ļ�����׺����EXT��ʽ���ļ��ļ���
	public static File[]
    local(File root,final String ext){
		return root.listFiles(new FilenameFilter() {
			private Pattern pattern=Pattern.compile(ext);
			public boolean accept(File root, String name) {	
				return pattern.matcher(name).matches();
			}
		});
	}
	//local(overload),��һ��������ΪROOTĿ¼��·����
	public File[]
	local(String rootPath,String ext){
		return local(new File(rootPath),ext);
	}
	
	//�ݹ�����ļ��м����ļ���ֱ�������ļ��������� ���õ�����pdf�ļ�
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
	//�����õ�����word�ļ�
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
	//��ӡ�洢�������ļ�����Ϣ
	public void printFile(ArrayList<File> recordFile){
		for(int i=0;i<recordFile.size();i++)
			System.out.println(recordFile.get(i).getAbsoluteFile());
	}
	
	public static void main(String[] args) {
		
	}

}
