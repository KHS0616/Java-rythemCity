package inhatc;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoWindow extends JFrame {
	//로고 이미지 및 라벨
	ImageIcon[] logoImage = new ImageIcon[11];
	JLabel logoLabel = new JLabel();
	JPanel paLogo = new JPanel();
	
	public LogoWindow() {
		//기본 설정
		super("Czerny");
		setSize(365, 538);
 		setResizable(false);
 		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 //로고 이미지 추가
        for(int i = 0 ; i <= 10 ; i++) {
      	  logoImage[i] = new ImageIcon("ImageLogo/" + i + ".jpg");
        }
        logoLabel.setIcon(logoImage[0]); 
        
        add(logoLabel);
        new runLogo().start();
        setVisible(true);
	}
    
	//로고 표시
    class runLogo extends Thread{
   	 @Override
   	public void run() {

   		 for(int i = 0 ; i <= 10 ; i++) {
   			 try {
   				logoLabel.setIcon(logoImage[i]);
					sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		 }
   		 for(int i = 10 ; i >= 0 ; i--) {
   			 try {
   				logoLabel.setIcon(logoImage[i]);
					sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		 }
   		remove(paLogo);
   		new LoginWindow();
   		close();
   	    interrupt();
   	    }
    }
    
    public void close() {
    	this.dispose();
    }

}
