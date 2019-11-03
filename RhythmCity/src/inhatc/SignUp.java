package inhatc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SignUp extends JFrame implements ActionListener{
    
     JLabel lblSet;
     TextField txtId,txtNn,txtPwd, txtPwdCh;
    

	private JButton btnLogin;
	private JButton btnExit;
	private JButton btnSignUp;
	
	//데이터베이스 연동 관련 변수
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs;
	
     public SignUp() {
         
          super("회원가입");
          setLayout(null);
          
          getContentPane().setBackground(Color.BLACK);
          lblSet =new JLabel(new ImageIcon("Image2/signList.png"));
          lblSet.setBounds(15, 15, 104, 81);
          add(lblSet);
          
          txtId =new TextField(20);
          txtNn =new TextField(20);
          txtPwd =new TextField(20);
          txtPwd.setEchoChar('*');
          txtPwdCh =new TextField(20);
          txtPwdCh.setEchoChar('*');


     
          
          btnExit = new JButton(new ImageIcon("Image2/signCancelButton.png")); 
          btnSignUp = new JButton(new ImageIcon("Image2/signSginUpButton.png")); 
          
          
          btnExit.setBorderPainted(false);
          btnExit.setFocusPainted(false);
          btnExit.setContentAreaFilled(false);
          btnExit.setMargin(new Insets(0,0,0,0));
          
          btnSignUp.setBorderPainted(false);
          btnSignUp.setFocusPainted(false);
          btnSignUp.setContentAreaFilled(false);
          btnSignUp.setMargin(new Insets(0,0,0,0));
          
                    
          //버튼에 이벤트 리스너 등록
          btnExit.addActionListener(this);
          btnSignUp.addActionListener(this);
         
          txtId.setBounds(130, 15, 150, 19);
          txtNn.setBounds(130, 35, 150, 19);
          txtPwd.setBounds(130, 56, 150, 19);
          txtPwdCh.setBounds(130, 77, 150, 19);
        
          Panel paButton = new Panel();
       
          paButton.add(btnExit);
          paButton.add(btnSignUp);
          paButton.setBounds(0,105,300,50);
          

         
          add(txtId);
          add(txtNn);
          add(txtPwd);
          add(txtPwdCh);
          add(paButton);
         
          setSize(315,190);
          setResizable(false); // 게임 해상도 고정
          setLocationRelativeTo(null);
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          setVisible(true);
         
     }
     
     @Override
	 public void actionPerformed(ActionEvent e) {
    	 Object source = e.getSource(); //발생한 이벤트 정보 받아오기
    	 
    	 if(source == btnSignUp) { //회원가입 버튼이 눌렸을 때
    		 if(conn==null) {
    	          //데이터 베이스 연결
    	          conn = DatabaseConnect.getMyDataBase();
    		 }
    		 
    		 //SQL 회원정보 관련 	 
    		 String sql = "INSERT INTO userinfo VALUES('" + txtId.getText() + "', '" + txtPwd.getText() + "', '" + txtNn.getText() + "')";
    		 String sql2 = "SELECT * FROM userinfo WHERE userID = '" + txtId.getText() + "'";
    		 
    		 //SQL 음악 점수 관련
    		 String sql3 = "SELECT count(*) FROM musicscore";
    		 String sql4;

    		 
    		 String id = txtId.getText();
    		 String Nn = txtNn.getText();
    		 String pwd = txtPwd.getText();
    		 String pwdCh = txtPwdCh.getText();
    		//정규식 선언	
			 Pattern pId = Pattern.compile("^[a-zA-Z0-9\\S]*$"); //문자, 숫자, 공백이 아닌 글자
			 Pattern pPwd = Pattern.compile("^[\\S]*$"); // 공백이 아닌 글자만
			 Pattern pNn	= Pattern.compile("^[\\S]*$");	// 공백이 아닌 숫자만
			 
			 
			 Matcher mId = pId.matcher(id);
			 Matcher mPwd = pPwd.matcher(pwd);
			 Matcher mpwdCh = pPwd.matcher(pwdCh);
			 
			 if(id.isBlank() || pwd.isBlank() || pwdCh.isBlank()) { //모든 입력창 빈칸 체크
    			 JOptionPane.showMessageDialog(null, "빈칸을 채워주세요", "회원가입", JOptionPane.WARNING_MESSAGE);
    		 }
			 else if(!mId.find()) { //특수문자,공백문자가 있는지 체크
    			 JOptionPane.showMessageDialog(null, "영어와 숫자만 입력가능합니다", "회원가입", JOptionPane.WARNING_MESSAGE);
    		 }
			 else if(!mPwd.find() || !mpwdCh.find()) { //패스워드 공백문자 체크
    			 JOptionPane.showMessageDialog(null, "공백글자는 입력할 수 없습니다", "회원가입", JOptionPane.WARNING_MESSAGE);
    		 }
			 else if(pwd.equals(pwdCh)) { //패스워드 일치
	    		 try {
		 			//유저 개인 정보 데이터 생성
	 				pstmt = conn.prepareStatement(sql2);
	 				rs = pstmt.executeQuery(sql2);
	 				if(!rs.next()) {
	 					pstmt = conn.prepareStatement(sql);
						pstmt.executeUpdate(sql);
						
		    			 //유저 음악 점수 데이터 생성
		    			 int musicCount = 0;
		    			 pstmt = conn.prepareStatement(sql3);
			 			 rs = pstmt.executeQuery(sql3);
			 			 if(rs.next()) {
			 				musicCount = rs.getInt(1);
			 				for(int i = 0 ; i < musicCount ; i++) {
				 				 sql4 = "INSERT INTO usermusicscore VALUES('" + txtId.getText() + "'," + i + ",0)";
				 				 pstmt = conn.prepareStatement(sql4);
								 pstmt.executeUpdate(sql4);
				 			 }
			 			 }  			 
						
			 			 //가입 성공 창
		    			JOptionPane.showMessageDialog(null, "가입성공", "회원가입", JOptionPane.DEFAULT_OPTION);
		    			dispose();
		    			conn.close();
		    			
	 				}else {
	 					JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.", "회원가입", JOptionPane.DEFAULT_OPTION);
	 				}
	 			} catch (SQLException e2) {
	 				System.out.println("데이터베이스 연동 오류");
	 			} finally {
	 				
	 			}
    		 }
			 else { 					//패스워드 불일치
    			 JOptionPane.showMessageDialog(null, "패스워드가 일치하지않습니다", "회원가입", JOptionPane.WARNING_MESSAGE);
    		 }
    	}
	 	else if(source==btnExit) { //나가기 버튼을 눌렀을 떄
	 		dispose();//이 창만 닫기
	 	}
     }
    
}