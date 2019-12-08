package orange;
 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class dailyclosing extends JFrame implements ActionListener {
	
	int sum = 0, sum1 = 0;
	 // �� �Է�
	String []menu = {"ǰ��","�� �ǸŰ���","�� ��"};
    DefaultTableModel dtm = new DefaultTableModel(null,menu);
    
    JPanel p1 = new JPanel(new GridLayout(2,1));
    JPanel p2 = new JPanel(new GridLayout(2,1));
    JPanel p3 = new JPanel(new BorderLayout());
    JPanel p4 = new JPanel(new BorderLayout());

    // Talbe ����
    JTable jTable = new JTable(dtm);

    // ��ũ�� ��� ����
    JScrollPane pane = new JScrollPane(jTable);
    
    JButton btn = new JButton("���� ��ư");
    // �� �Ǹűݾ� �� ���� 
    JLabel total = new JLabel();
    // �� �ǸŰ��� �� ����
    JLabel total1 = new JLabel();
    
    public dailyclosing() {
    	
    	setTitle("��������");
        setBounds(700,200,500,450);
        add(p1);
        
        total1.setFont(new Font("Dialog",Font.BOLD,30));
        total.setFont(new Font("Dialog",Font.BOLD,30));
        btn.setFont(new Font("Dialog",Font.BOLD,40));
        // JFrame�� ��� �߰�
        p1.add(pane);
        p1.add(p2);
        p3.add(total1,BorderLayout.NORTH);
        p3.add(total,BorderLayout.CENTER);
        p4.add(btn);
        p2.add(p3);
        p2.add(p4);
 
        // JFrame ȭ�� ���̱�
        setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
        view();
        
        btn.addActionListener(this);
    }
 
    public static void main(String[] args) {
        dailyclosing test = new dailyclosing();
    }
	private void view() {
		
		ArrayList<orderVO> list = daybaDAO.select();
		dtm.setNumRows(0); //model rows �ʱ�ȭ ��Ű��
		if(list.size()==0) {
		}else {
			String[] rows = new String[3];
			for(orderVO vo : list) {
			rows[0] = vo.getCoffee();
			rows[1] = String.valueOf(vo.getDaliy());
			rows[2] = String.valueOf(vo.getDaliymoney()); 
			sum += vo.getDaliymoney();
			sum1 += vo.getDaliy();
			dtm.addRow(rows);
			}
		}
		total.setText("�� �Ǹ� �ݾ� : " + sum);
		total1.setText("�� �Ǹ� ���� : "+ sum1);
//		view();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		daybaDAO dao = new daybaDAO();
		int a = 0; int b = 0;
		switch(e.getActionCommand()) {
		case "���� ��ư" :
			dao.daily(a, b);
			JOptionPane.showMessageDialog(null,"���� �� �Ǹ� ���� : "+sum1+" �Ǹ� �ݾ� : " +sum+" �Դϴ�.");
			view();
			total.setText("�� �Ǹ� �ݾ� : " + 0);
			total1.setText("�� �Ǹ� ���� : "+ 0);
			break;
		}
	}

}


