
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*FileGui用于实现程序GUI界面的基本框架*/
public class FileGui extends JFrame{
	Fileseacher fs;
	readPdf pdf;
	FileRead word;
	String []keywords;
	
	
	private final int height=900;
	private final int width=700;
	String dirpath=null;
	JFileChooser jc=null;
	JTextArea ja; //用来显示筛选过后的符合条件的文件的路径名
	JScrollPane jp;//显示区域的滑动条
	JScrollPane jpkeyword;
	JTextField jf;//用来显示开始进行筛选的根目录
	JTextField jfkeyword;//用来输入关键字的文本框
	
	JButton jbshowcontains;
	JButton jbSelect; //选择按钮
	JButton jbNotselect;//筛选按钮
	JButton jbfilepathSelect;
	JButton jbofkeyword;
	public FileGui(){
		setSize(height,width);
		setLocation(300,50);
		this.setLayout(null);
		
		jf=new JTextField();
		jf.setBounds(100,10,200,20);
		jf.setEditable(false);
		
		jfkeyword=new JTextField();
		jpkeyword=new JScrollPane(jfkeyword);
		jpkeyword.setBounds(100,150, 200, 50);
		add(jpkeyword);
		
		jbofkeyword=new JButton("确定");
		jbofkeyword.setBounds(350, 150, 100, 30);
		jbofkeyword.addActionListener(new keyword());
		add(jbofkeyword);
		
		
		jbshowcontains=new JButton("显示含有关键字");
		jbshowcontains.addActionListener(new showInfo());
		jbshowcontains.setBounds(500, 250, 150, 70);
		add(jbshowcontains);
		
		jbNotselect=new JButton("显示不含关键字的简历");
		jbNotselect.setBounds(250, 250, 200, 60);
		jbNotselect.addActionListener(new showInfo());
		jbfilepathSelect=new JButton("打开文件目录");
		jbfilepathSelect.setBounds(100, 50, 200, 50);
	
		jbSelect=new JButton("开始筛选");
		jbSelect.setBounds(100, 250, 100, 60);
		jbSelect.addActionListener(new select());
		
		jbfilepathSelect.addActionListener(new choose());
		
		
		pdf=new readPdf();
		word=new FileRead();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ja=new JTextArea(20,10);
		ja.setEditable(false);
		jp=new JScrollPane(ja);
		jp.setBounds(100,350,400,250);
		jp.setVisible(true);
		//add(ja);
		add(jf);
		add(jp);
		add(jbfilepathSelect);
		add(jbSelect);
		add(jbNotselect);
		setVisible(true);
	}
	class showInfo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==jbshowcontains){
				ja.setText("");
				for(int i=0;i<fs.recordFileYesOfPdf.size();i++){
					//System.out.println(fs.recordFileYesOfPdf.get(i).getPath());
					
					ja.append(fs.recordFileYesOfPdf.get(i).getPath()+"\n");
				}
				
				for(int i=0;i<fs.recordFileYesOfWord.size();i++)
					ja.append(fs.recordFileYesOfWord.get(i).getPath()+"\n");
				}
			
			else if(e.getSource()==jbNotselect){
				ja.setText("");
				for(int i=0;i<fs.recordFileOfPdfNotselect.size();i++){
					ja.append(fs.recordFileOfPdfNotselect.get(i).getPath()+"\n");
				}
				for(int i=0;i<fs.recordFileOfWordNotselect.size();i++){
					ja.append(fs.recordFileOfWordNotselect.get(i).getPath()+"\n");
				}
			}
			}
		}
	
	//监听器 ，实现输入N个关键字，并且存入数组
	class keyword implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String text=jfkeyword.getText();
			keywords=text.split(" ");
			}
		}
	
	//筛选关键字
	class select implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(jc==null)return ;
			else{
				//筛选所有PDF文件
				if(fs.recordFileOfPdf.size()>0){
				while(fs.iterPdf.hasNext()){
					File file=fs.iterPdf.next();
					for(int i=0;i<keywords.length;i++){
						System.out.println(file.getPath());
						try {
							if(pdf.pdfword(file.getPath(), keywords[i])==false)
								fs.recordFileOfPdfNotselect.add(file);
								else
									fs.recordFileYesOfPdf.add(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				}
			
				if(fs.recordFileOfWord.size()>0){
				while(fs.iterWord.hasNext()){
					File file=fs.iterWord.next();
					System.out.println(""+file.getName());
					for(int i=0;i<keywords.length;i++){
						if(word.readFromWord(file.getPath(), keywords[i])==false){
							fs.recordFileOfWordNotselect.add(file);
							System.out.println("OK"+file.getPath());
						}
						else{
							fs.recordFileYesOfWord.add(file);
							System.out.println("OK"+file.getPath());
						}
					}
				}
				}
				
				
				
			}
		}
	}
	class choose implements ActionListener{
		public void actionPerformed(ActionEvent e){
			jc=new JFileChooser();
			jc.setCurrentDirectory(null);
			//将FC的文件选择模式设定为只选择目录
			jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int rVal=jc.showDialog(FileGui.this,"选择目录" );
			if(rVal==JFileChooser.APPROVE_OPTION){
			dirpath=jc.getSelectedFile().getPath();
			jf.setText(dirpath);
			System.out.println(dirpath);
			fs=new Fileseacher(dirpath);
			}
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new FileGui();
			}
		});
	}

}
