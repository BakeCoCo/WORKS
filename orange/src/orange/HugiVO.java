package orange;

import java.text.SimpleDateFormat;
import java.util.Date;

//	�޸� 1���� ����ϴ� Ŭ����
public class HugiVO {

	private int idx;			// �۹�ȣ, �ڵ�����
	private String name;		// �г���
	private String password;	// ���
	private String han;			// �� ����
	private String review;		// ����
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String gethan() {
		return han;
	}
	public void sethan(String han) {
		this.han = han;
	}
	public String getreview() {
		return review;
	}
	public void setreview(String review) {
		this.review = review;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return idx + ". " + name + "(" + han + ")���� " +  "�� �����\n";
	}
	
}











