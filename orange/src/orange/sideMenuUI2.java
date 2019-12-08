package orange;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class sideMenuUI2 extends JFrame implements ActionListener,Runnable{
	JPanel panel = new JPanel();
	
	JComboBox<String> comboBox1;
	JComboBox<String> comboBox2;
	JComboBox<String> comboBox3;
	JComboBox<String> comboBox4;
	JComboBox<String> comboBox5;
	
	JLabel mainLabel;
	JLabel hotLabel;
	JLabel shotLabel;
	JLabel sizeLabel;
	JLabel takeLabel;
	JLabel iceLabel;
	
	JButton confirmButton;
	JButton cancelButton;
	
	String comboBoxStr;
	
	public sideMenuUI2() {
		setTitle("���� �ɼ� ����");
		setBounds(400, 200, 600, 550);
		setLayout(null);
			
		mainLabel = new JLabel("<<<���ἱ��>>>");
		mainLabel.setFont(new Font("D2coding",Font.BOLD,40));
		mainLabel.setAlignmentX(CENTER_ALIGNMENT);
		mainLabel.setAlignmentY(CENTER_ALIGNMENT);
		mainLabel.setBounds(160, 20, 800, 50);
		
		add(mainLabel);
		String[] hotice = {"HOT","ICE"};
		String[] ICE = {"���� ����", "���� ����", "���� �⺻","���� ����"};
		String[] SHOT = {"SHOT �⺻" ,"SHOT �߰�(+500)"};
		String[] SIZE = {"SHORT(�⺻)", "TALL(+500)" , "GRANDE(+1000)"}; 
		String[] takeOut = {"����ũ�ƿ�","����(�ӱ���)","����+����"};
		
		comboBox1 = new JComboBox<String>(hotice);
		comboBox2 = new JComboBox<String>(ICE);
		comboBox3 = new JComboBox<String>(SHOT);
		comboBox4 = new JComboBox<String>(SIZE);
		comboBox5 = new JComboBox<String>(takeOut);
		
		comboBox1.setBounds(330, 100, 150, 30);
		comboBox2.setBounds(330, 150, 150, 30);
		comboBox3.setBounds(330, 200, 150, 30);
		comboBox4.setBounds(330, 250, 150, 30);
		comboBox5.setBounds(330, 300, 150, 30);
		
		add(comboBox1);
		add(comboBox2);
		add(comboBox3);
		add(comboBox4);
		add(comboBox5);
		
		hotLabel = new JLabel("HOT / ICE >>>");
		hotLabel.setFont(new Font("D2coding",Font.BOLD,20));
		hotLabel.setBounds(110, 97, 150, 30);
		add(hotLabel);
		JLabel iceLabel = new JLabel("�������� >>>");
		iceLabel.setFont(new Font("D2coding",Font.BOLD,20));
		iceLabel.setBounds(120, 147, 150, 30);
		add(iceLabel);
		shotLabel = new JLabel("�� ���� >>>");
		shotLabel.setFont(new Font("D2coding",Font.BOLD,20));
		shotLabel.setBounds(130, 197, 150, 30);
		add(shotLabel);
		sizeLabel = new JLabel("������ ���� >>>");
		sizeLabel.setFont(new Font("D2coding",Font.BOLD,20));
		sizeLabel.setBounds(90, 251, 150, 30);
		add(sizeLabel);
		takeLabel = new JLabel("���� ���� >>>");
		takeLabel.setFont(new Font("D2coding",Font.BOLD,20));
		takeLabel.setBounds(110, 301, 150, 30);
		add(takeLabel);
		
		confirmButton = new JButton("Ȯ��");
		confirmButton.setBounds(0, 390, 290, 120);
		add(confirmButton);
		cancelButton = new JButton("���");
		cancelButton.setBounds(293, 390, 290, 120);
		add(cancelButton);
		
		confirmButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		sideMenuUI2 window = new sideMenuUI2();
		Thread ad = new Thread(window);
		ad.start();
	}

	public void actionPerformed(ActionEvent e) {
		String item = "";
		orderVO vo = new orderVO();
		if(e.getActionCommand().equals("Ȯ��")) {
			
			vo.hotice = (String) comboBox1.getSelectedItem();
			vo.ICE = (String) comboBox2.getSelectedItem();
			vo.SHOT = (String) comboBox3.getSelectedItem();
			vo.SIZE = (String) comboBox4.getSelectedItem();
			vo.takeOut = (String) comboBox5.getSelectedItem();
			item = comboBox1.getSelectedItem() +" / "+ comboBox2.getSelectedItem()+" / "+ comboBox3.getSelectedItem()+ " / "+ comboBox4.getSelectedItem()+
					" / "+ comboBox5.getSelectedItem() + "\n" +"               �ֹ��Ͻðڽ��ϱ�?";
			int result = JOptionPane.showConfirmDialog(comboBox4, item, "���� �ɼ� Ȯ��", JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE);	
			
			
			if(result ==0) {
				if(comboBox3.getSelectedIndex()==1) {
					vo.setWon(vo.getWon()+500);
					if(comboBox4.getSelectedIndex() ==1) {
						vo.setWon(vo.getWon()+500);
					}else if(comboBox4.getSelectedIndex() ==2) {
						vo.setWon(vo.getWon()+1000);
					}
				}else {
					if(comboBox4.getSelectedIndex() ==1) {
						vo.setWon(vo.getWon()+500);
					}else if(comboBox4.getSelectedIndex() ==2) {
						vo.setWon(vo.getWon()+1000);
					}
				}
				vo.setCount(vo.getCount()+1);
				setVisible(false);
				
			}
			daybaDAO dayba =  new daybaDAO();
			String c = vo.getMenu();
			daybaDAO.choice(c);
			 int a = vo.getEa2()+1;
			 int b = vo.getWon()+vo.getSum();
			dayba.update(a, b, c);
		}
		
		else if(e.getActionCommand().equals("���")) {
			
			setVisible(false);
		}
		
			
		
	}

	public void run() {
		Thread ad = new Thread();
		Boolean falg = true;
		while(true) {
			try {ad.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		if(comboBox1.getSelectedIndex()==0) {
			comboBox2.setSelectedIndex(0);
			comboBox2.setEnabled(false);
			falg = true;
		}
		else {
			comboBox2.setEnabled(true);
			
		}
		if(comboBox1.getSelectedIndex()==1) {
			if(falg) {
				comboBox2.setSelectedIndex(2);
				falg = false;
			}
		}
		}
	
		
	}

}
