package task.mail;

public class IFTTT_Mail {
		static String address="the_arctic_ifttt@163.com";
		static String password="dpufxikrveagbqjp";
		public static Mail getPublicMail(){
			Mail publicMail=new Mail(address,password);
			return publicMail;
		}
}
