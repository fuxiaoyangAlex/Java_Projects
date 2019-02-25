package com.niit.frame;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class JTableDemo extends JFrame{
	private JTable jTable; //表格
	private String[] titles; //表标题
	private JScrollPane jsp;//滚动条
	
	public JTableDemo(){		
		init();	
		setTitle("表格JTable的使用");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	
		setVisible(true);
		
	}
	
	public void init(){
		titles=new String[]{"姓名","年龄","性别"};
		Object[][] info={
				{"张三",20,"男"},{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},{"张三",20,"男"},
				{"李四",30,"男"},
				{"李丽",21,"女"}
				};
		jTable=new JTable(info,titles); //将数据和标题送到表格中
		
//		居中设置
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.setDefaultRenderer(Object.class, r);		
		
		jsp=new JScrollPane(jTable); //添加滚动条
		add(jsp);   //界面上只需要添加滚动条
	}	

	public static void main(String[] args) {
		new JTableDemo();
		
	}

}
