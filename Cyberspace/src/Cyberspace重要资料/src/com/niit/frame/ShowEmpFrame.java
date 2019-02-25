package com.niit.frame;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.niit.domain.Emp;
import com.niit.factory.DaoFactory;

public class ShowEmpFrame extends JFrame{
	private List<Emp> list=null;//用来存放放至表格的数据
	private JTable jTable;//定义表格
	private DefaultTableModel model;//定义表格的模式
	private String[] titles;//表格的抬头
	private JScrollPane jScrollPane;//滚动面板
	private String[] str=new String[6]; //用来送数据到jTable行中的
	
	private JLabel nameJLabel,jobJLabel;
	private JTextField nameJTextField,jobJTextField;
	
	
	
//	表格的设置
	private void setTable(List<Emp> list){
		model=new DefaultTableModel();
		titles=new String[]{"编号","姓名","工作","日期","工资","奖金"};		
		model.setColumnIdentifiers(titles);//设置表格的抬头
		jTable.setModel(model);//让表格使用model模式		
//		表格数据居中
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.setDefaultRenderer(Object.class, r);		
//		将list数据送入表格中
		Iterator<Emp> iter=list.iterator();
		while(iter.hasNext()){
			Emp emp=iter.next(); 
			str[0]=String.valueOf(emp.getEmpno());
			str[1]=emp.getEname();
			str[2]=emp.getJob();
			str[3]=String.valueOf(emp.getHiredate());
			str[4]=String.valueOf(emp.getSal());
			str[5]=String.valueOf(emp.getComm());			
			model.addRow(str); 			
		}		
	}
	
	public void init() throws SQLException{
		setTitle("使用JTable显示数据");
		setSize(500,400);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		jTable=new JTable();
		list=new ArrayList<>();		
		list=DaoFactory.getEmpDaoInstance02().findAll();
		setTable(list);		
		jScrollPane=new JScrollPane(jTable);
		jScrollPane.setBorder(BorderFactory.createTitledBorder("显示员工数据"));		
		add(jScrollPane);
		
		nameJLabel=new JLabel("姓名",JLabel.RIGHT);
		jobJLabel=new JLabel("工作",JLabel.RIGHT);
		nameJTextField=new JTextField(10);
		jobJTextField=new JTextField(10);
		
//		nameJTextField和jobJTextField默认为表格的第一行的相关数据
		nameJTextField.setText(jTable.getValueAt(0,1).toString());
		jobJTextField.setText(jTable.getValueAt(0, 2).toString());
		
//		设置nameJTextField和jobJTextField不让修改
		nameJTextField.setEditable(false);
		jobJTextField.setEditable(false);
		
		add(nameJLabel);
		add(nameJTextField);
		add(jobJLabel);
		add(jobJTextField);
		
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=jTable.getSelectedRow();//获得鼠标所在的行号
				String name=jTable.getValueAt(row, 1).toString();
				String job=jTable.getValueAt(row, 2).toString();
				nameJTextField.setText(name);
				jobJTextField.setText(job);
				
			}
			
		});
		
		
		setVisible(true);
	}	
	public ShowEmpFrame(){
		try {
			init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ShowEmpFrame();
	}

}
