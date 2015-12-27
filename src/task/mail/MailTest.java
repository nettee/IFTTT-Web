package task.mail;

public class MailTest {
	public static void main(String[] args) {
		Mail mail=new Mail("","");
		System.out.println(mail.hasNewMessage());
		mail.sendMessage("checkyh@live.cn","Hello", "dsa");
	}
}
