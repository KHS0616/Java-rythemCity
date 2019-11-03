package inhatc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;

public class LoginWindow extends JFrame implements ActionListener {

	JLabel lblId, lblPwd;
	TextField txtId;
	TextField txtPwd;

	private JButton btnLogin;
	private JButton btnExit;
	private JButton btnSignUp;
	JPanel paButton = new JPanel();

	// 데이터베이스 연동 관련 변수
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs;
	private JButton source;

	public LoginWindow() {
		super("Czerny");
		setLayout(null);

		setSize(365, 538);
		setResizable(false); // 게임 해상도 고정
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setting();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); // 발생한 이벤트 정보 받아오기
		if (source == btnLogin) {
			// 데이터 베이스 연동 및 정보 로딩
			conn = DatabaseConnect.getMyDataBase();
			String sql = "SELECT * FROM userinfo WHERE userID = '" + txtId.getText() + "'";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery(sql);
				if (rs.next()) {
					String userID = rs.getString("userID");
					if (txtId.getText().equals(userID)) {
						if (txtPwd.getText().equals(rs.getString("password"))) {
							System.out.println("로그인 성공");
							new MusicSelect(userID);
							dispose();
							conn.close();
						} else {
							System.out.println("비밀번호가 일치하지 않습니다.");
						}
					}
				} else {
					System.out.println("등록되지 않은 ID");
				}
			} catch (SQLException e1) {
				System.out.println("데이버베이스 연동 에러");
			}
		} else if (source == btnSignUp) { // 회원가입 버튼이 눌렸을 때
			new SignUp();
		} else if (source == btnExit) { // 나가기 버튼이 눌렸을 때
			System.exit(0); // 프로그램 전체 종료
		}
	}

	class key implements KeyListener {

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				conn = DatabaseConnect.getMyDataBase();
				String sql = "SELECT * FROM userinfo WHERE userID = '" + txtId.getText() + "'";
				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery(sql);
					if (rs.next()) {
						String userID = rs.getString("userID");
						if (txtId.getText().equals(userID)) {
							if (txtPwd.getText().equals(rs.getString("password"))) {
								System.out.println("로그인 성공");
								new MusicSelect(userID);
								dispose();
								conn.close();
							} else {
								System.out.println("비밀번호가 일치하지 않습니다.");
							}
						}
					} else {
						System.out.println("등록되지 않은 ID");
					}
				} catch (SQLException e1) {
					System.out.println("데이버베이스 연동 에러");
				}
			}

		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}

	}

	public void setting() {
		lblId = new JLabel(new ImageIcon("Image2/ID.png"));
		lblPwd = new JLabel(new ImageIcon("Image2/PW.png"));

		// 아이디 비밀번호 라벨 위치
		lblId.setBounds(75, 250, 52, 20);
		lblPwd.setBounds(75, 280, 71, 20);

		add(lblId);
		add(lblPwd);

		txtId = new TextField(20);
		txtPwd = new TextField(20);
		txtPwd.setEchoChar('*');

		// 로그인 버튼, 나가기 버튼, 회원가입 버튼 이미지 추가
		btnLogin = new JButton(new ImageIcon("Image2/loginButton.png"));
		btnExit = new JButton(new ImageIcon("Image2/exitButton.png"));
		btnSignUp = new JButton(new ImageIcon("Image2/signButton.png"));

		btnExit.setBorderPainted(false);
		btnExit.setFocusPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setMargin(new Insets(0, 0, 0, 0));

		btnSignUp.setBorderPainted(false);
		btnSignUp.setFocusPainted(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setMargin(new Insets(0, 0, 0, 0));

		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setMargin(new Insets(0, 0, 0, 0));

		btnExit.addActionListener(this);
		btnLogin.addActionListener(this);
		btnSignUp.addActionListener(this);

		txtPwd.addKeyListener(new key());
		txtId.setBounds(155, 250, 120, 20);
		txtPwd.setBounds(155, 280, 120, 20);

		paButton.setOpaque(false);
		paButton.add(btnLogin);
		paButton.add(btnExit);
		paButton.add(btnSignUp);
		paButton.setBounds(50, 310, 250, 80);

		class ImagePanel extends JComponent {
			private Image image;

			public ImagePanel(Image image) {
				this.image = image;
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, this);
			}
		}

		add(txtId);
		add(txtPwd);
		add(paButton);
		BufferedImage myImage;
		try {
			myImage = ImageIO.read(new File("Image2/loginBackground.png"));
			ImagePanel Background = new ImagePanel(myImage);
			Background.setBounds(0, 0, 350, 500);
			add(Background);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}