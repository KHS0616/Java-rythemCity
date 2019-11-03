package inhatc;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiScoreWindow extends JFrame implements ActionListener {
	private String userID = "";
	private int musicID = 0;
	private int userHighScore = 0;
	private int musicHighScore = 0;
	private int score = 0;
	private int maxCombo = 0;
	private int enemyScore = 0;
	
	public static final int sizeW = 365;
	public static final int sizeH = 538;
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	JLabel ScoreBackLabel = new JLabel(new ImageIcon("image/ScoreBack.png"));
	
	JPanel northPan = new JPanel();
	JPanel centerPan = new JPanel();
	JPanel southPan = new JPanel();
	
	//숫자 라벨 
	JLabel gradeLabel = new JLabel();
	JLabel[] myScoreLabel = new JLabel[6];
	JLabel[] myHighScoreLabel = new JLabel[6];
	JLabel[] musicHighScoreLabel = new JLabel[6];
	JLabel[] maxComboLabel = new JLabel[3];
	
	//숫자 폰트
	ImageIcon oneScoreImage = new ImageIcon("image/oneScore.png");
	ImageIcon twoScoreImage = new ImageIcon("image/twoScore.png");
	ImageIcon threeScoreImage = new ImageIcon("image/threeScore.png");
	ImageIcon fourScoreImage = new ImageIcon("image/fourScore.png");
	ImageIcon fiveScoreImage = new ImageIcon("image/fiveScore.png");
	ImageIcon sixScoreImage = new ImageIcon("image/sixScore.png");
	ImageIcon sevenScoreImage = new ImageIcon("image/sevenScore.png");
	ImageIcon eightScoreImage = new ImageIcon("image/eightScore.png");
	ImageIcon nineScoreImage = new ImageIcon("image/nineScore.png");
	ImageIcon zeroScoreImage = new ImageIcon("image/zeroScore.png");
	
	//등급 폰트
	ImageIcon gradeSImage = new ImageIcon("image/gradeS.png");
	ImageIcon gradeAImage = new ImageIcon("image/gradeA.png");
	ImageIcon gradeBImage = new ImageIcon("image/gradeB.png");
	ImageIcon gradeCImage = new ImageIcon("image/gradeC.png");
	ImageIcon gradeFImage = new ImageIcon("image/gradeF.png");	
	
	//박스 이미지
	ImageIcon maxComboBoxImage = new ImageIcon("image/maxComboBox.png");
	ImageIcon myScoreBoxImage = new ImageIcon("image/myScoreBox.png");
	ImageIcon myHighScoreBoxImage = new ImageIcon("image/enemyScoreBox.png");
	ImageIcon musicHighScoreBoxImage = new ImageIcon("image/musicHighScoreBox.png");
	
	//버튼 이미지
	ImageIcon retryButtonImage = new ImageIcon("image/scoreRetryButton.png");
	ImageIcon homeButtonImage = new ImageIcon("image/scoreHomeButton.png");
	
	//박스 라벨
	JLabel maxComboBoxLabel = new JLabel();
	JLabel myScoreBoxLabel = new JLabel();
	JLabel myHighScoreBoxLabel = new JLabel();
	JLabel musicHighScoreBoxLabel = new JLabel();
	
	//바튼 변수
	JButton retryButton = new JButton(retryButtonImage);
	JButton exitButton = new JButton(homeButtonImage);
	
	
	public MultiScoreWindow() {
	}

	public MultiScoreWindow(String userID, int musicID, int score, int maxCombo, int enemyScore) throws HeadlessException {
		//프레임 기본 설정
		super("Czerny");
		setSize(sizeW, sizeH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		this.userID = userID;
		this.musicID = musicID;
		this.score = score;
		this.maxCombo = maxCombo;
		this.enemyScore = enemyScore;
		setResizable(false);
		
		//배경 설정
		ScoreBackLabel.setSize(350,500);
		ScoreBackLabel.setLocation(0,0);
		add(ScoreBackLabel,-1);

		loadingData();
		setGrade();
		setMaxCombo();
		setMyScore();
		setMyHighScore();
		setMusicHighScore();
		setButton();
		saveData();
		
		setVisible(true);
	}

	//버튼 설정
	private void setButton() {
		retryButton.setSize(72,81);
		retryButton.setLocation(40,400);
		retryButton.setBorderPainted(false);
		retryButton.setFocusPainted(false);
		retryButton.setContentAreaFilled(false);
		retryButton.addActionListener(this);
		add(retryButton,0);
		
		exitButton.setSize(72,81);
		exitButton.setLocation(240,400);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(this);
		add(exitButton,0);
		
		
	}
	
	//스코어 표시
	private void setMyScore() {
		myScoreBoxLabel.setIcon(myScoreBoxImage);
		myScoreBoxLabel.setSize(136,44);
		myScoreBoxLabel.setLocation(180,230);
		add(myScoreBoxLabel,0);		
		
		myScoreLabel[0] = new JLabel(zeroScoreImage);
		myScoreLabel[0].setSize(17,24);
		myScoreLabel[0].setLocation(190,245);
		add(myScoreLabel[0],0);
		
		myScoreLabel[1] = new JLabel(zeroScoreImage);
		myScoreLabel[1].setSize(17,24);
		myScoreLabel[1].setLocation(210,245);
		add(myScoreLabel[1],0);
		
		myScoreLabel[2] = new JLabel(zeroScoreImage);
		myScoreLabel[2].setSize(17,24);
		myScoreLabel[2].setLocation(230,245);
		add(myScoreLabel[2],0);
		
		myScoreLabel[3] = new JLabel(zeroScoreImage);
		myScoreLabel[3].setSize(17,24);
		myScoreLabel[3].setLocation(250,245);
		add(myScoreLabel[3],0);
		
		myScoreLabel[4] = new JLabel(zeroScoreImage);
		myScoreLabel[4].setSize(17,24);
		myScoreLabel[4].setLocation(270,245);
		add(myScoreLabel[4],0);
		
		myScoreLabel[5] = new JLabel(zeroScoreImage);
		myScoreLabel[5].setSize(17,24);
		myScoreLabel[5].setLocation(290,245);
		add(myScoreLabel[5],0);
		
		checkMyScore();
	}
	
	//콤보표시
	private void setMaxCombo() {
		maxComboBoxLabel.setIcon(maxComboBoxImage);
		maxComboBoxLabel.setSize(136,44);
		maxComboBoxLabel.setLocation(180,150);
		add(maxComboBoxLabel,0);	
		
		maxComboLabel[0] = new JLabel(zeroScoreImage);
		maxComboLabel[0].setSize(17,24);
		maxComboLabel[0].setLocation(250,165);
		add(maxComboLabel[0],0);
		
		maxComboLabel[1] = new JLabel(zeroScoreImage);
		maxComboLabel[1].setSize(17,24);
		maxComboLabel[1].setLocation(270,165);
		add(maxComboLabel[1],0);
		
		maxComboLabel[2] = new JLabel(zeroScoreImage);
		maxComboLabel[2].setSize(17,24);
		maxComboLabel[2].setLocation(290,165);
		add(maxComboLabel[2],0);
		
		int temp = maxCombo;
		int temp2 = maxCombo;
		for(int i = 0 ; i < 3 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: maxComboLabel[2-i].setIcon(nineScoreImage);break;
			case 8: maxComboLabel[2-i].setIcon(eightScoreImage);break;
			case 7: maxComboLabel[2-i].setIcon(sevenScoreImage);break;
			case 6: maxComboLabel[2-i].setIcon(sixScoreImage);break;
			case 5: maxComboLabel[2-i].setIcon(fiveScoreImage);break;
			case 4: maxComboLabel[2-i].setIcon(fourScoreImage);break;
			case 3: maxComboLabel[2-i].setIcon(threeScoreImage);break;
			case 2: maxComboLabel[2-i].setIcon(twoScoreImage);break;
			case 1: maxComboLabel[2-i].setIcon(oneScoreImage);break;
			case 0: maxComboLabel[2-i].setIcon(zeroScoreImage);break;
			default: break;
			}
		}
	}
	
	//등급 표시
	private void setGrade() {
		gradeLabel.setSize(118,130);
		gradeLabel.setLocation(40,60);
		switch(score/10000) {
		case 7: case 6: case 5: case 4: gradeLabel.setIcon(gradeSImage); break;
		case 3: gradeLabel.setIcon(gradeAImage); break;
		case 2: gradeLabel.setIcon(gradeBImage); break;
		case 1: gradeLabel.setIcon(gradeCImage); break;
		default : gradeLabel.setIcon(gradeFImage); break;
		}
		add(gradeLabel,0);
		
	}
	
	//음악 최고 점수 설정 및 표시
	private void setMusicHighScore() {
		if(score >= musicHighScore) {
			musicHighScore = score;
		}
		
		musicHighScoreBoxLabel.setIcon(musicHighScoreBoxImage);
		musicHighScoreBoxLabel.setSize(131,49);
		musicHighScoreBoxLabel.setLocation(180,300);
		add(musicHighScoreBoxLabel,0);
		
		musicHighScoreLabel[0] = new JLabel(zeroScoreImage);
		musicHighScoreLabel[0].setSize(17,24);
		musicHighScoreLabel[0].setLocation(190,315);
		add(musicHighScoreLabel[0],0);
		
		musicHighScoreLabel[1] = new JLabel(zeroScoreImage);
		musicHighScoreLabel[1].setSize(17,24);
		musicHighScoreLabel[1].setLocation(210,315);
		add(musicHighScoreLabel[1],0);
		
		musicHighScoreLabel[2] = new JLabel(zeroScoreImage);
		musicHighScoreLabel[2].setSize(17,24);
		musicHighScoreLabel[2].setLocation(230,315);
		add(musicHighScoreLabel[2],0);
		
		musicHighScoreLabel[3] = new JLabel(zeroScoreImage);
		musicHighScoreLabel[3].setSize(17,24);
		musicHighScoreLabel[3].setLocation(250,315);
		add(musicHighScoreLabel[3],0);
		
		musicHighScoreLabel[4] = new JLabel(zeroScoreImage);
		musicHighScoreLabel[4].setSize(17,24);
		musicHighScoreLabel[4].setLocation(270,315);
		add(musicHighScoreLabel[4],0);
		
		musicHighScoreLabel[5] = new JLabel(zeroScoreImage);
		musicHighScoreLabel[5].setSize(17,24);
		musicHighScoreLabel[5].setLocation(290,315);
		add(musicHighScoreLabel[5],0);
		
		checkMusicHighScore();
	}
	
	//개인 최고 점수 설정 및 표시
	private void setMyHighScore() {
		if(score >= userHighScore) {
			userHighScore = score;
		}
		
		myHighScoreBoxLabel.setIcon(myHighScoreBoxImage);
		myHighScoreBoxLabel.setSize(131,49);
		myHighScoreBoxLabel.setLocation(35,300);
		add(myHighScoreBoxLabel,0);	
		
		
		myHighScoreLabel[0] = new JLabel(zeroScoreImage);
		myHighScoreLabel[0].setSize(17,24);
		myHighScoreLabel[0].setLocation(45,315);
		add(myHighScoreLabel[0],0);
		
		myHighScoreLabel[1] = new JLabel(zeroScoreImage);
		myHighScoreLabel[1].setSize(17,24);
		myHighScoreLabel[1].setLocation(65,315);
		add(myHighScoreLabel[1],0);
		
		myHighScoreLabel[2] = new JLabel(zeroScoreImage);
		myHighScoreLabel[2].setSize(17,24);
		myHighScoreLabel[2].setLocation(85,315);
		add(myHighScoreLabel[2],0);
		
		myHighScoreLabel[3] = new JLabel(zeroScoreImage);
		myHighScoreLabel[3].setSize(17,24);
		myHighScoreLabel[3].setLocation(105,315);
		add(myHighScoreLabel[3],0);
		
		myHighScoreLabel[4] = new JLabel(zeroScoreImage);
		myHighScoreLabel[4].setSize(17,24);
		myHighScoreLabel[4].setLocation(125,315);
		add(myHighScoreLabel[4],0);
		
		myHighScoreLabel[5] = new JLabel(zeroScoreImage);
		myHighScoreLabel[5].setSize(17,24);
		myHighScoreLabel[5].setLocation(145,315);
		add(myHighScoreLabel[5],0);
		
		checkMyHighScore();
	}
	
	//데이터불러오기
	private void loadingData() {
		conn = DatabaseConnect.getMyDataBase();
		String musicSql = "SELECT * FROM musicscore where musicID = '" + musicID + "'";
		try {
			
			pstmt = conn.prepareStatement(musicSql);
			ResultSet mrs = pstmt.executeQuery(musicSql);
			
			if(mrs.next()) {
				musicHighScore = mrs.getInt(3);
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB에러");
		}
	}
	
	//데이터 저장
	private void saveData() {
		String userSql = "UPDATE usermusicscore SET userhighscore = '" + userHighScore + "' where userID = '" + userID + "' and musicID = '" + musicID + "'";
		String musicSql = "UPDATE musicscore SET musichighscore = '" + musicHighScore + "' where musicID = '" + musicID + "'";
		try {
			pstmt = conn.prepareStatement(userSql);
			pstmt.executeUpdate(userSql);
			pstmt = conn.prepareStatement(musicSql);
			pstmt.executeUpdate(musicSql);
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//점수 체크 후 변경
	private void checkMyScore() {
		int temp = score;
		int temp2 = score;
		for(int i = 0 ; i < 6 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: myScoreLabel[5-i].setIcon(nineScoreImage);break;
			case 8: myScoreLabel[5-i].setIcon(eightScoreImage);break;
			case 7: myScoreLabel[5-i].setIcon(sevenScoreImage);break;
			case 6: myScoreLabel[5-i].setIcon(sixScoreImage);break;
			case 5: myScoreLabel[5-i].setIcon(fiveScoreImage);break;
			case 4: myScoreLabel[5-i].setIcon(fourScoreImage);break;
			case 3: myScoreLabel[5-i].setIcon(threeScoreImage);break;
			case 2: myScoreLabel[5-i].setIcon(twoScoreImage);break;
			case 1: myScoreLabel[5-i].setIcon(oneScoreImage);break;
			case 0: myScoreLabel[5-i].setIcon(zeroScoreImage);break;
			default: break;
			}
		}
	}
	
	//개인 최고 기록 변경
	private void checkMyHighScore() {
		int temp = enemyScore;
		int temp2 = enemyScore;
		for(int i = 0 ; i < 6 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: myHighScoreLabel[5-i].setIcon(nineScoreImage);break;
			case 8: myHighScoreLabel[5-i].setIcon(eightScoreImage);break;
			case 7: myHighScoreLabel[5-i].setIcon(sevenScoreImage);break;
			case 6: myHighScoreLabel[5-i].setIcon(sixScoreImage);break;
			case 5: myHighScoreLabel[5-i].setIcon(fiveScoreImage);break;
			case 4: myHighScoreLabel[5-i].setIcon(fourScoreImage);break;
			case 3: myHighScoreLabel[5-i].setIcon(threeScoreImage);break;
			case 2: myHighScoreLabel[5-i].setIcon(twoScoreImage);break;
			case 1: myHighScoreLabel[5-i].setIcon(oneScoreImage);break;
			case 0: myHighScoreLabel[5-i].setIcon(zeroScoreImage);break;
			default: break;
			}
		}
	}
	
	//음악 최고 기록 변경
	private void checkMusicHighScore() {
		int temp = musicHighScore;
		int temp2 = musicHighScore;
		for(int i = 0 ; i < 6 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: musicHighScoreLabel[5-i].setIcon(nineScoreImage);break;
			case 8: musicHighScoreLabel[5-i].setIcon(eightScoreImage);break;
			case 7: musicHighScoreLabel[5-i].setIcon(sevenScoreImage);break;
			case 6: musicHighScoreLabel[5-i].setIcon(sixScoreImage);break;
			case 5: musicHighScoreLabel[5-i].setIcon(fiveScoreImage);break;
			case 4: musicHighScoreLabel[5-i].setIcon(fourScoreImage);break;
			case 3: musicHighScoreLabel[5-i].setIcon(threeScoreImage);break;
			case 2: musicHighScoreLabel[5-i].setIcon(twoScoreImage);break;
			case 1: musicHighScoreLabel[5-i].setIcon(oneScoreImage);break;
			case 0: musicHighScoreLabel[5-i].setIcon(zeroScoreImage);break;
			default: break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retryButton) {
			new GameWindow(userID,musicID);
			dispose();
		}
		else if(e.getSource() == exitButton) {
			new MusicSelect(userID);
			dispose();
		}
		
	}

}
