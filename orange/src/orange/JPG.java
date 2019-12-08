package orange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class JPG extends JFrame implements ActionListener, Runnable{
	String[] tab = {"�ֹ���ȣ","�ֹ�����","�ݾ�"};  // �ֹ� ���̺� 
	String mennu[] = {"�� ��", "�ݾ�"};	 // �޴� ���̺�			
	Image[] images = new Image[10]; 	// �̹��� �迭
	String coffee[]= {"�Ƹ޸�ī��","ī���","���⽺����","��������","������","������","������ġ��","�׸�Ƽ","����Ƽ","��Ƽ"}; // Ŀ���̸�
	JPanel tpa[] = new JPanel[10];	// 10������ �޴� ��ư,���� �ø� �г�
	JButton btn[] = new JButton[10]; // 10������ �޴� ��ư 
	JLabel menu[] = new JLabel[10]; // 10������ �޴��� ��
	
	DefaultTableModel model = new DefaultTableModel(null,tab) {
		public boolean isCellEditable(int i, int c) {return false;}};
	DefaultTableModel menumodel = new DefaultTableModel(null,mennu) {
		public boolean isCellEditable(int i, int c) {return false;}};
	
	JPanel toppanel = new JPanel(new GridLayout(1,5));
	
	JPanel botpa1 = new JPanel();
	JPanel botpa2 = new JPanel();
	JPanel botpa3 = new JPanel();
	JPanel botpa4 = new JPanel();
	JPanel botpa5 = new JPanel();
	JPanel botpa6 = new JPanel();
	JPanel botpa7 = new JPanel();
	JPanel botpanel1 = new JPanel(new GridLayout(1,2));
	
	JTable table = new JTable(model);
	JTable menutable = new JTable(menumodel);
	
	JScrollPane sp = new JScrollPane(table);
	JScrollPane menupane = new JScrollPane(menutable);
	
	JCheckBox check[] = new JCheckBox[10];
	
	JLabel money = new JLabel("�ݾ� :");
	JLabel count = new JLabel("");
	
	JButton order = new JButton("�ֹ�");
	JButton cancel = new JButton("���");
	JButton review = new JButton("����");
	JButton manager = new JButton("������");
	int sum =0; //���� ����
	int i=0;// Ŀ�� ��������?
	String namel = "";
	String menulist = "";
	String menurow[] = new String[2];
	boolean sell = true;

	public JPG() {
		setTitle("�����ڼ�");
		setBounds(700,50,920,900);
		setLayout(new GridLayout(2,1));
		toppanel.setLayout(new GridLayout(2,5));
		
		for(int i=0;i<10;i++) {
			//�г� ������ ���ÿ� ��ư�� ���� �����ϰ� ��ư�� �̹����� �ִ´�
			tpa[i] = new JPanel();
			String filename = "./src/image/"+(i+1)+".jpg";
			images[i] = Toolkit.getDefaultToolkit().getImage(filename);
			tpa[i].add(btn[i] = new JButton(new ImageIcon(images[i])));
			tpa[i].add(menu[i]= new JLabel(""+coffee[i]));
			btn[i].setPreferredSize(new Dimension(180,180));
			toppanel.add(tpa[i]);
			btn[i].setName(coffee[i]);
			btn[i].addActionListener(this);
		}
		
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		menutable.getColumnModel().getColumn(0).setPreferredWidth(350);
		
		botpa1.add(sp);
		
		botpa2.setLayout(new GridLayout(2,1));
		
		botpa4.setLayout(new BorderLayout());
		
		order.setPreferredSize(new Dimension(130,70));
		cancel.setPreferredSize(new Dimension(130,70));
		
		botpa6.add(money);
		botpa6.add(count);
		
		botpa5.add(order);
		botpa5.add(cancel);
		botpa5.add(review);
		botpa5.add(manager);
		
		botpa3.add(menupane);
		botpa2.add(botpa3,new GridLayout(1,2));
		botpa2.add(botpa4);
		
		botpa4.add(botpa6,BorderLayout.NORTH);
		botpa4.add(botpa7, BorderLayout.CENTER);
		botpa4.add(botpa5,BorderLayout.SOUTH);
		
		botpanel1.add(botpa1);
		botpanel1.add(botpa2);
		
		order.addActionListener(this);
		cancel.addActionListener(this);
		review.addActionListener(this);
		manager.addActionListener(this);
		
		add(toppanel);
		add(botpanel1);
		
//		view();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		JPG jj = new JPG();
		Thread th = new Thread(jj);
		th.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		JButton btn = (JButton)ob;
		
		String input[] = new String[3];
		String menurow[] = new String[2];
		if(btn.getName()!=null) {
			
			orderVO vo = new orderVO();
			OrderDAO.coffee(btn.getName());
			if(vo.getEa() >= 0 ) {
				menurow[0] = vo.getMenu();
				menurow[1] = String.valueOf(vo.getWon());
				menumodel.addRow(menurow);
				sideMenuUI2 side = new sideMenuUI2();
				side.mainLabel.setText(btn.getName());
				Runnable side1 = side;
				Thread side2 = new Thread(side1);
				side2.start();
				
				switch(btn.getName()) {
				case "�Ƹ޸�ī��":
					break;
				case "ī���":
					break;
				case "���⽺����":
					side.comboBox1.setEnabled(false);
					side.comboBox3.setEnabled(false);
					side.comboBox1.setSelectedIndex(1);
					break;
				case "��������":
					side.comboBox1.setEnabled(false);
					side.comboBox3.setEnabled(false);
					side.comboBox1.setSelectedIndex(1);
					break;
				case "������":
					side.comboBox3.setEnabled(false);
					break;
				case "������":
					side.comboBox3.setEnabled(false);
					break;
				case "������ġ��":
					break;
				case "�׸�Ƽ":
					side.comboBox3.setEnabled(false);
					break;
				case "����Ƽ":
					side.comboBox1.setEnabled(false);
					side.comboBox1.setSelectedIndex(1);
					side.comboBox3.setEnabled(false);
					break;
				case "��Ƽ":
					side.comboBox3.setEnabled(false);
					break;
				}
				
			}else {
				JOptionPane.showMessageDialog(null,btn.getName() + "ǰ���Ǿ����ϴ�.");
			}
		}else {
			switch (e.getActionCommand()) {
			
			
			case "�ֹ�":
				orderVO ordervo = new orderVO();
				if(sum != 0) {
					
					ordervo.setMenulist(menulist);
					ordervo.setMoney(sum);
					
					input[0] = String.valueOf(ordervo.idx);
					input[1] = menulist;
					input[2] = String.valueOf(sum);
					
					OrderDAO.insert(ordervo);
					
					
					model.addRow(input);
					
					view();
					clear();
					break;
				} else {
					JOptionPane.showMessageDialog(null, "�ֹ��� �޴��� �����ϴ�.");
				}
			case "���":
				clear();
				break;
			case "����":
				HugiProjectWIN2 hugi = new HugiProjectWIN2();
				break;
			case "������":
				String pass = JOptionPane.showInputDialog("��й�ȣ�� �Է��ϼ���");
				if(pass.equals("0000") ) {
					manager manager = new manager();
					Runnable re = manager;
					Thread as = new Thread(re);
					as.start();
					
				}else {
					JOptionPane.showMessageDialog(null,"��й�ȣ�� �ùٸ��� �ʽ��ϴ�.!!!!");
				}
			}
		}
	}

	public void run() {
		see();
	}

	private void see() {
		Thread th = new Thread();
		orderVO vo = new orderVO();
		while(true) {
			try {th.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			view();
			menurow[0] = vo.getMenu()+"("+vo.getHotice()+", "+vo.getICE()+", "+vo.getSHOT()+", "+vo.getSIZE()+", "+vo.getTakeOut();
			menurow[1] = String.valueOf(vo.getWon());
			menumodel.addRow(menurow);
			menumodel.setNumRows(vo.count);
			if(i==vo.count) {
				if(i!=0) {
					sum += vo.getWon();
					if(i>1) {
						menulist+=", ";
					}
					menulist += vo.getMenu();
				}
				i++;
				count.setText(String.valueOf(sum));
			}
		}
	}
	private void view() {
		ArrayList<orderVO> list = OrderDAO.select();
		model.setNumRows(0); //model rows �ʱ�ȭ ��Ű��
		if(list.size()==0) {
		}else {
			for(orderVO vo1 : list) {
				String[] rows = new String[3];
				rows[0] = String.valueOf(vo1.getIdx());
				rows[1] = vo1.getMenulist();
				rows[2] = String.valueOf(vo1.money);
				model.addRow(rows);
			}
		}
	}
	
	private void clear() {
		orderVO vo = new orderVO();
		vo.setCount(0);
		menumodel.setNumRows(vo.count);
		menurow[0] = "";
		menurow[1] = "";
		menulist = "";
		sum = 0;
		i = 0;
	}
}