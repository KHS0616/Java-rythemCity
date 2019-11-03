package inhatc;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class MultiGameWindow extends JFrame{
	
	public static final int sizeW = 716;//365
	public static final int sizeH = 538;//538
	CenPan cenPan ;

	public MultiGameWindow() {
		
	}
	public MultiGameWindow(String userID, int musicID, int port) {
		super("Czerny");
		setSize(sizeW, sizeH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cenPan = new CenPan(userID, musicID,port,this);
		

		add(cenPan,BorderLayout.CENTER);
		
		setVisible(true);
	}
class CenPan extends JPanel implements KeyListener, ActionListener{
	//x좌표 설정
	final int oneX = 12;
	final int twoX = 62;
	final int threeX = 112;
	final int lineY = 420;
	final int MoneX = 362;
	final int MtwoX = 412;
	final int MthreeX = 462;
	
	//노트 크기 설정
	final int barW = 50;
	final int barH = 10;

	//노트 이미지
	ImageIcon shortA = new ImageIcon("Image//ShortNoteA.png");
	ImageIcon scoreLine = new ImageIcon("Image//ScoreLine.png");
	ImageIcon longBar = new ImageIcon("Image//LongBar.png");
	ImageIcon gameBack = new ImageIcon("mImage//GameBack.png");
	ImageIcon gameTitleImage;
	
	//키패드 버튼 이미지
	ImageIcon pauseButtonImage = new ImageIcon("image/pause.png");
	ImageIcon nomalAButtonImage = new ImageIcon("image/nomalAButton.png");
	ImageIcon pushAButtonImage = new ImageIcon("image/pushAButton.png");
	ImageIcon nomalBButtonImage = new ImageIcon("image/nomalBButton.png");
	ImageIcon pushBButtonImage = new ImageIcon("image/pushBButton.png");
	ImageIcon nomalCButtonImage = new ImageIcon("image/nomalCButton.png");
	ImageIcon pushCButtonImage = new ImageIcon("image/pushCButton.png");
	
	//이펙트 이미지
	ImageIcon pushLightImage = new ImageIcon("image/pushLight.png");
	ImageIcon missImage = new ImageIcon("image/miss.png");
	ImageIcon badImage = new ImageIcon("image/bad.png"); 
	ImageIcon goodImage = new ImageIcon("image/good.png"); 
	ImageIcon greatImage = new ImageIcon("image/great.png"); 
	ImageIcon perfectImage = new ImageIcon("image/perfect.png");
	ImageIcon healthImage = new ImageIcon("image/healthBar.png");
	
	//콤보 숫자 폰트 이미지
	ImageIcon comboImage = new ImageIcon("image/combo.png");
	ImageIcon oneComboImage = new ImageIcon("image/oneCombo.png");
	ImageIcon twoComboImage = new ImageIcon("image/twoCombo.png");
	ImageIcon threeComboImage = new ImageIcon("image/threeCombo.png");
	ImageIcon fourComboImage = new ImageIcon("image/fourCombo.png");
	ImageIcon fiveComboImage = new ImageIcon("image/fiveCombo.png");
	ImageIcon sixComboImage = new ImageIcon("image/sixCombo.png");
	ImageIcon sevenComboImage = new ImageIcon("image/sevenCombo.png");
	ImageIcon eightComboImage = new ImageIcon("image/eightCombo.png");
	ImageIcon nineComboImage = new ImageIcon("image/nineCombo.png");
	ImageIcon zeroComboImage = new ImageIcon("image/zeroCombo.png");
	
	
	//스코어 숫자 폰트 이미지
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
	
	//일시정지 화면 이미지
	ImageIcon pauseWindowImage = new ImageIcon("image/pauseWindow.png");
	ImageIcon pauseResumeImage = new ImageIcon("image/pauseResume.png");
	ImageIcon pauseExitImage = new ImageIcon("image/pauseExit.png");
	
	
	//콤보 관련 변수
	JLabel comboLabel = new JLabel(comboImage);
	JLabel[] comboNumLabel = new JLabel[3];
	int combo = 0, maxCombo = 0;

	
	//본인 스코어 변수 및 멀티 스코어 변수
	JLabel[] scoreLabel = new JLabel[6];
	JLabel[] multiScoreLabel = new JLabel[6];
	
	//라인 및 체력 라벨
	JLabel line = new JLabel(scoreLine);
	JLabel[] noteOneLabel, noteTwoLabel, noteThreeLabel;
	JLabel healthLabel = new JLabel(healthImage);
	//멀티 노트 라벨
	JLabel[] multiNoteOneLabel, multiNoteTwoLabel, multiNoteThreeLabel;
	
	//유저 정보
	String userID;
	Music music = null;
	int musicID = 0;
	
	//점수 설정
	int score = 0;
	final int perfect = 32;
	final int great = 16;
	final int good = 8;
	final int bad = 0;
	int health = 390;
	final int badHealth = 10;
	final int failHealth = 20;
	
	//버튼 설정
	JButton pauseButton;
	int pauseCheck = 0; //일시정지 횟수 체크
	JLabel OneButton, TwoButton, ThreeButton;
	JLabel[] pushLight = new JLabel[3];
	JLabel scoreEffect = new JLabel();
	
	//멀티 버튼 설정
	JLabel[] multiPushLight = new JLabel[3];
	JLabel multiScoreEffect = new JLabel();

	//점수 라인 설정
	final int perfectLine = 10;
	final int greatLine = 20;
	final int goodLine = 30;
	final int badLine = 40;
	
	//스레드 설정
	DropBeat db = new DropBeat();
	
	//임시변수
	int[][] tempOneLabel, tempTwoLabel, tempThreeLabel;
	int timeCount = 0; 
	JLabel nowOneBar, nowTwoBar, nowThreeBar;
	JLabel[] tempLongBar = new JLabel[3]; 
	boolean oneMode = false, twoMode = false, threeMode = false; 
	int barOneCount = 0, barTwoCount = 0, barThreeCount = 0; 
	boolean longOneCheck = false, longTwoCheck = false, longThreeCheck = false; 
	int longOneCount = 0, longTwoCount = 0, longThreeCount = 0; 
	int addOneCount = 0, addTwoCount = 0, addThreeCount = 0;
	MultiGameWindow gw;
	
	//멀티 임시변수
	int[][] multiTempOneLabel, multiTempTwoLabel, multiTempThreeLabel;
	JLabel multiNowOneBar, multiNowTwoBar, multiNowThreeBar;
	JLabel[] multiTempLongBar = new JLabel[3]; 
	int multiBarOneCount = 0, multiBarTwoCount = 0, multiBarThreeCount = 0; 
	boolean multiLongOneCheck = false, multiLongTwoCheck = false, multiLongThreeCheck = false; 
	int multiLongOneCount = 0, multiLongTwoCount = 0, multiLongThreeCount = 0; 
	int multiAddOneCount = 0, multiAddTwoCount = 0, multiAddThreeCount = 0;
	boolean multiCheck = false;
	int enemyScore = 0;
	
	//시간 관련 변수
	double targetTime;
	double startTime;
	double currentTime;
	double tempTiem;
	double delayTime = 0.0;
	
	//데이터베이스 관련 변수
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	//일시정지 화면 관련 변수
	JPanel pausePanel = new JPanel();
	JLabel pauseWindow = new JLabel(pauseWindowImage);
	JButton pauseResume = new JButton(pauseResumeImage);
	JButton pauseExit = new JButton(pauseExitImage);
	
	//멀티모드 서버 관련 변수
	ServerSocket server;
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	int port;
	JLabel mS = new JLabel(new ImageIcon("mImage/mS.png"));
	JLabel[] mscoreLabel = new JLabel[6];
	

	public CenPan() {
		
	}
	public CenPan(String userID, int musicID, int port, MultiGameWindow gw) {
		//패널 기본 설정
		setLayout(null);
		setPreferredSize(new Dimension(MultiGameWindow.sizeW,MultiGameWindow.sizeH));
		this.musicID = musicID;
		this.userID = userID;
		this.gw = gw;
		this.port = port;
		setResizable(false);
		

		setBackground();
		setButton();
		setLight();
		setScore();
		setCombo();		

		//키 이벤트 등록
		this.addKeyListener(this);
		
		
		
		//체력 바 설정
		healthLabel.setSize(15,387);
		healthLabel.setLocation(170,100);
		add(healthLabel,2);
		//패널 활성화
		setFocusable(true);
		setVisible(true);
		//스레드 실행
		db.start();
	}
	
	//배경 설정
	private void setBackground() {
		//본인 화면
		JLabel back = new JLabel(gameBack);
		back.setLocation(0, 0);
		back.setSize(700,500);
		add(back,-1);
		
		mS.setBounds(230, 140, 244, 85);
		add(mS,0);

	}
	
	//제목 설정
	private void setTitle() {
		JLabel title = new JLabel(gameTitleImage);
		title.setLocation(169, 50);
		title.setSize(357,80);
		add(title,0);
		
	}
	
	//버튼 설정
	private void setButton() {
		
		//키 패드 버튼
		OneButton = new JLabel(nomalAButtonImage);
		OneButton.setLocation(16,475);
		OneButton.setSize(41, 22);
		add(OneButton,0);
		
		TwoButton = new JLabel(nomalBButtonImage);
		TwoButton.setLocation(66,475);
		TwoButton.setSize(41, 22);
		add(TwoButton,0);
		
		ThreeButton = new JLabel(nomalCButtonImage);
		ThreeButton.setLocation(116,475);
		ThreeButton.setSize(41, 22);
		add(ThreeButton,0);
	}
	
	//이펙트 설정
	private void setLight() {
		//본인 화면 이펙트
		pushLight[0] = new JLabel();
		pushLight[0].setLocation(12,0);
		pushLight[0].setSize(50,469);
		add(pushLight[0],1);
		
		pushLight[1] = new JLabel();
		pushLight[1].setLocation(62,0);
		pushLight[1].setSize(50,469);
		add(pushLight[1],1);
		
		pushLight[2] = new JLabel();
		pushLight[2].setLocation(112,0);
		pushLight[2].setSize(50,469);
		add(pushLight[2],1);
		
		
	}
	
	//점수 설정
	private void setScore() {
		//점수 초기화
		scoreEffect.setIcon(null);
		scoreEffect.setSize(105,39);
		scoreEffect.setLocation(35,300);
		add(scoreEffect,1);
		
		scoreLabel[0] = new JLabel(zeroScoreImage);
		scoreLabel[0].setSize(17,24);
		scoreLabel[0].setLocation(230,200);
		add(scoreLabel[0],1);
		
		scoreLabel[1] = new JLabel(zeroScoreImage);
		scoreLabel[1].setSize(17,24);
		scoreLabel[1].setLocation(250,200);
		add(scoreLabel[1],1);
		
		scoreLabel[2] = new JLabel(zeroScoreImage);
		scoreLabel[2].setSize(17,24);
		scoreLabel[2].setLocation(270,200);
		add(scoreLabel[2],1);
		
		scoreLabel[3] = new JLabel(zeroScoreImage);
		scoreLabel[3].setSize(17,24);
		scoreLabel[3].setLocation(290,200);
		add(scoreLabel[3],1);
		
		scoreLabel[4] = new JLabel(zeroScoreImage);
		scoreLabel[4].setSize(17,24);
		scoreLabel[4].setLocation(310,200);
		add(scoreLabel[4],1);
		
		scoreLabel[5] = new JLabel(zeroScoreImage);
		scoreLabel[5].setSize(17,24);
		scoreLabel[5].setLocation(330,200);
		add(scoreLabel[5],1);
		
		//멀티 점수 초기화
		multiScoreEffect.setIcon(null);
		multiScoreEffect.setSize(105,39);
		multiScoreEffect.setLocation(385,300);
		add(multiScoreEffect,1);
		
		mscoreLabel[0] = new JLabel(zeroScoreImage);
		mscoreLabel[0].setSize(17,24);
		mscoreLabel[0].setLocation(360,200);
		add(mscoreLabel[0],1);
		
		mscoreLabel[1] = new JLabel(zeroScoreImage);
		mscoreLabel[1].setSize(17,24);
		mscoreLabel[1].setLocation(380,200);
		add(mscoreLabel[1],1);
		
		mscoreLabel[2] = new JLabel(zeroScoreImage);
		mscoreLabel[2].setSize(17,24);
		mscoreLabel[2].setLocation(400,200);
		add(mscoreLabel[2],1);
		
		mscoreLabel[3] = new JLabel(zeroScoreImage);
		mscoreLabel[3].setSize(17,24);
		mscoreLabel[3].setLocation(420,200);
		add(mscoreLabel[3],1);
		
		mscoreLabel[4] = new JLabel(zeroScoreImage);
		mscoreLabel[4].setSize(17,24);
		mscoreLabel[4].setLocation(440,200);
		add(mscoreLabel[4],1);
		
		mscoreLabel[5] = new JLabel(zeroScoreImage);
		mscoreLabel[5].setSize(17,24);
		mscoreLabel[5].setLocation(460,200);
		add(mscoreLabel[5],1);

	}
	
	//콤보 설정
	private void setCombo() {
		comboLabel.setSize(90,28);
		comboLabel.setLocation(45,200);
		add(comboLabel,0);
		for(int i = 0 ; i < 3 ; i++) {
			comboNumLabel[i] = new JLabel(zeroComboImage);
			comboNumLabel[i].setSize(21,25);
		}
		
		comboNumLabel[0].setLocation(55,230);
		comboNumLabel[1].setLocation(77,230);
		comboNumLabel[2].setLocation(99,230);
		
		for(int i = 0 ; i < 3 ; i++) {
			add(comboNumLabel[i],1);
		}
	}
	
	//음악 선택
	private void selectMusic() {
		switch(musicID) {
		case 0: music = new SadLoveOfMoon(); gameTitleImage = new ImageIcon("mImage//00music.png"); setTitle(); break;
		case 1: music = new FlightOfTheBumblebee(); gameTitleImage = new ImageIcon("mImage//01music.png"); setTitle(); break;
		case 2: music = new Canon(); gameTitleImage = new ImageIcon("mImage//02music.png"); setTitle(); break;
		case 3: music = new TheNutcrackerMarch(); gameTitleImage = new ImageIcon("mImage//03music.png"); setTitle(); break;
		default: break;
		}
		targetTime = 60/music.bpm*1000;
		music.playMusic();
		startTime = System.nanoTime();
	}
	
	//노트 설정
	private void noteSetting() {
		
		noteOneLabel = new JLabel[music.noteOne.size()];
		tempOneLabel = new int[music.noteOne.size()][3];
		for(int i = 0 ; i < music.noteOne.size() ; i++) {
			noteOneLabel[i] = new JLabel(longBar);
			tempOneLabel[i] = music.noteOne.get(i);
			noteOneLabel[i].setSize(barW, barH*tempOneLabel[i][2]);
		}
		
		noteTwoLabel = new JLabel[music.noteTwo.size()];
		tempTwoLabel = new int[music.noteTwo.size()][3];
		for(int i = 0 ; i < music.noteTwo.size() ; i++) {
			noteTwoLabel[i] = new JLabel(longBar);
			tempTwoLabel[i] = music.noteTwo.get(i);
			noteTwoLabel[i].setSize(barW, barH*tempTwoLabel[i][2]);
		}		
		
		noteThreeLabel = new JLabel[music.noteThree.size()];
		tempThreeLabel = new int[music.noteThree.size()][3];
		for(int i = 0 ; i < music.noteThree.size() ; i++) {
			noteThreeLabel[i] = new JLabel(longBar);
			tempThreeLabel[i] = music.noteThree.get(i);
			noteThreeLabel[i].setSize(barW, barH*tempThreeLabel[i][2]);
		}	
		
		multiNoteOneLabel = new JLabel[music.noteOne.size()];
		multiTempOneLabel = new int[music.noteOne.size()][3];
		for(int i = 0 ; i < music.noteOne.size() ; i++) {
			multiNoteOneLabel[i] = new JLabel(longBar);
			multiTempOneLabel[i] = music.noteOne.get(i);
			multiNoteOneLabel[i].setSize(barW, barH*multiTempOneLabel[i][2]);
		}
		
		multiNoteTwoLabel = new JLabel[music.noteTwo.size()];
		multiTempTwoLabel = new int[music.noteTwo.size()][3];
		for(int i = 0 ; i < music.noteTwo.size() ; i++) {
			multiNoteTwoLabel[i] = new JLabel(longBar);
			multiTempTwoLabel[i] = music.noteTwo.get(i);
			multiNoteTwoLabel[i].setSize(barW, barH*multiTempTwoLabel[i][2]);
		}		
		
		multiNoteThreeLabel = new JLabel[music.noteThree.size()];
		multiTempThreeLabel = new int[music.noteThree.size()][3];
		for(int i = 0 ; i < music.noteThree.size() ; i++) {
			multiNoteThreeLabel[i] = new JLabel(longBar);
			multiTempThreeLabel[i] = music.noteThree.get(i);
			multiNoteThreeLabel[i].setSize(barW, barH*multiTempThreeLabel[i][2]);
		}	
	
	}
	
	//노트 추가
	private void noteAdd() {
		if(addOneCount != music.noteOne.size()) {
			if(tempOneLabel[addOneCount][3]<=(int)(currentTime/targetTime*100)) {
				if(tempOneLabel[addOneCount][2] ==1) {
					noteOneLabel[addOneCount].setLocation(oneX,tempOneLabel[addOneCount][1]);
				}else {
					noteOneLabel[addOneCount].setLocation(oneX,-(10*(tempOneLabel[addOneCount][2]-1)));
				}				
				add(noteOneLabel[addOneCount],1);
				if(addOneCount == barOneCount || addOneCount == 0) {
					nowOneBar = noteOneLabel[addOneCount];
					
				}
				addOneCount++;
			}
		}
		if(addTwoCount != music.noteTwo.size()) {
			if(tempTwoLabel[addTwoCount][3]<=(int)(currentTime/targetTime*100)) {
				if(tempTwoLabel[addTwoCount][2] ==1) {
					noteTwoLabel[addTwoCount].setLocation(twoX,tempTwoLabel[addTwoCount][1]);
				}else {
					noteTwoLabel[addTwoCount].setLocation(twoX,-(10*(tempTwoLabel[addTwoCount][2]-1)));
				}
				add(noteTwoLabel[addTwoCount],1);
				if(addTwoCount == barTwoCount || addTwoCount == 0) {
					nowTwoBar = noteTwoLabel[addTwoCount];
					
				}
				addTwoCount++;
			}
		}
		if(addThreeCount != music.noteThree.size()) {
			if(tempThreeLabel[addThreeCount][3]<=(int)(currentTime/targetTime*100)) {
				if(tempThreeLabel[addThreeCount][2] ==1) {
					noteThreeLabel[addThreeCount].setLocation(threeX,tempThreeLabel[addThreeCount][1]);
				}else {
					noteThreeLabel[addThreeCount].setLocation(threeX,-(10*(tempThreeLabel[addThreeCount][2]-1)));
				}
				add(noteThreeLabel[addThreeCount],1);
				if(addThreeCount == barThreeCount || addThreeCount == 0) {
					nowThreeBar = noteThreeLabel[addThreeCount];
				}
				addThreeCount++;
			}
		}	
		
		//멀티
		if(multiAddOneCount != music.noteOne.size()) {
			if(multiTempOneLabel[multiAddOneCount][3]<=(int)(currentTime/targetTime*100)) {
				if(multiTempOneLabel[multiAddOneCount][2] ==1) {
					multiNoteOneLabel[multiAddOneCount].setLocation(MoneX,multiTempOneLabel[multiAddOneCount][1]);
				}else {
					multiNoteOneLabel[multiAddOneCount].setLocation(MoneX,-(10*(multiTempOneLabel[multiAddOneCount][2]-1)));
				}				
				add(multiNoteOneLabel[multiAddOneCount],1);
				if(multiAddOneCount == barOneCount || multiAddOneCount == 0) {
					multiNowOneBar = multiNoteOneLabel[multiAddOneCount];
					
				}
				multiAddOneCount++;
			}
		}
		if(multiAddTwoCount != music.noteTwo.size()) {
			if(multiTempTwoLabel[multiAddTwoCount][3]<=(int)(currentTime/targetTime*100)) {
				if(multiTempTwoLabel[multiAddTwoCount][2] ==1) {
					multiNoteTwoLabel[multiAddTwoCount].setLocation(MtwoX,multiTempTwoLabel[multiAddTwoCount][1]);
				}else {
					multiNoteTwoLabel[multiAddTwoCount].setLocation(MtwoX,-(10*(multiTempTwoLabel[multiAddTwoCount][2]-1)));
				}
				add(multiNoteTwoLabel[multiAddTwoCount],1);
				if(multiAddTwoCount == barTwoCount || multiAddTwoCount == 0) {
					multiNowTwoBar = multiNoteTwoLabel[multiAddTwoCount];
					
				}
				multiAddTwoCount++;
			}
		}
		if(multiAddThreeCount != music.noteThree.size()) {
			if(multiTempThreeLabel[multiAddThreeCount][3]<=(int)(currentTime/targetTime*100)) {
				if(multiTempThreeLabel[multiAddThreeCount][2] ==1) {
					multiNoteThreeLabel[multiAddThreeCount].setLocation(MthreeX,multiTempThreeLabel[multiAddThreeCount][1]);
				}else {
					multiNoteThreeLabel[multiAddThreeCount].setLocation(MthreeX,-(10*(multiTempThreeLabel[multiAddThreeCount][2]-1)));
				}
				add(multiNoteThreeLabel[multiAddThreeCount],1);
				if(multiAddThreeCount == barThreeCount || multiAddThreeCount == 0) {
					multiNowThreeBar = multiNoteThreeLabel[multiAddThreeCount];
				}
				multiAddThreeCount++;
			}
		}

	}
	
	//노트 떨굼
	private void  noteMove() {
		
		for(int i = 0 ; i < music.noteOne.size() ; i++) {
			noteOneLabel[i].setLocation(noteOneLabel[i].getX(), noteOneLabel[i].getY()+1);
		}
		
		for(int i = 0 ; i < music.noteTwo.size() ; i++) {
			noteTwoLabel[i].setLocation(noteTwoLabel[i].getX(), noteTwoLabel[i].getY()+1);
		}
		
		for(int i = 0 ; i < music.noteThree.size() ; i++) {
			noteThreeLabel[i].setLocation(noteThreeLabel[i].getX(), noteThreeLabel[i].getY()+1);
		}
		
		for(int i = 0 ; i < music.noteOne.size() ; i++) {
			multiNoteOneLabel[i].setLocation(multiNoteOneLabel[i].getX(), multiNoteOneLabel[i].getY()+1);
		}
		
		for(int i = 0 ; i < music.noteTwo.size() ; i++) {
			multiNoteTwoLabel[i].setLocation(multiNoteTwoLabel[i].getX(), multiNoteTwoLabel[i].getY()+1);
		}
		
		for(int i = 0 ; i < music.noteThree.size() ; i++) {
			multiNoteThreeLabel[i].setLocation(multiNoteThreeLabel[i].getX(), multiNoteThreeLabel[i].getY()+1);
		}
	}
	
	//입력 모드 설정
	private void checkMode() {
		if(nowOneBar !=null) {
			if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >=lineY-badLine) {
				oneMode = true;
			}
		}
		if(nowTwoBar !=null) {
			if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >=lineY-badLine) {
				twoMode = true;
			}
		}
		if(nowThreeBar !=null) {
			if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >=lineY-badLine) {
				threeMode = true;
			}
		}
	}
	
	//터치 안한 노트 체크
	private void noTouchCheck() {
		//본인
		if(nowOneBar !=null) {
			if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY+badLine) {
				if(tempOneLabel[barOneCount][2] == 1) {
					oneMode = false;
					remove(nowOneBar);
					barOneCount++;
					nowOneBar = null;
					if(barOneCount < addOneCount) {
						nowOneBar = noteOneLabel[barOneCount];
					}			
					scoreEffect.setIcon(missImage);
					health -= failHealth;
					minusHealth(failHealth);
					checkCombo(); combo = 0; changeCombo();
				}else if(tempOneLabel[barOneCount][2] > 1) {
					oneMode = false;
					tempLongBar[0] = nowOneBar;
					barOneCount++;
					nowOneBar = null;
					if(barOneCount < addOneCount) {
						nowOneBar = noteOneLabel[barOneCount];
					}
					scoreEffect.setIcon(missImage);
					health -= failHealth;
					minusHealth(failHealth);
					checkCombo(); combo = 0; changeCombo();
				}
				
			}
		}
		if(nowTwoBar !=null) {
			if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY+badLine ) {
				if(tempTwoLabel[barTwoCount][2] == 1) {
					twoMode = false;
					remove(nowTwoBar);
					barTwoCount++;
					nowTwoBar = null;
					if(barTwoCount < addTwoCount) {
						nowTwoBar = noteTwoLabel[barTwoCount];
					}				
					scoreEffect.setIcon(missImage);
					health -= failHealth;
					minusHealth(failHealth);
					checkCombo(); combo = 0; changeCombo();
				}else if(tempTwoLabel[barTwoCount][2] > 1) {
					twoMode = false;
					tempLongBar[1] = nowTwoBar;
					barTwoCount++;
					nowTwoBar = null;
					if(barTwoCount < addTwoCount) {
						nowTwoBar = noteTwoLabel[barTwoCount];
					}				
					scoreEffect.setIcon(missImage);
					health -= failHealth;
					minusHealth(failHealth);
					checkCombo(); combo = 0; changeCombo();
				}
				
			}
		}
		if(nowThreeBar != null) {
			if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >= lineY+badLine) {
				if(tempThreeLabel[barThreeCount][2] == 1) {
					threeMode = false;
					remove(nowThreeBar);
					barThreeCount++;
					nowThreeBar = null;
					if(barThreeCount < addThreeCount) {
						nowThreeBar = noteThreeLabel[barThreeCount];
					}					
					scoreEffect.setIcon(missImage);
					health -= failHealth;
					minusHealth(failHealth);
					checkCombo(); combo = 0; changeCombo();
				}else if(tempThreeLabel[barThreeCount][2] > 1) {
					threeMode = false;
					tempLongBar[2] = nowThreeBar;
					barThreeCount++;
					nowThreeBar = null;
					if(barThreeCount < addThreeCount) {
						nowThreeBar = noteThreeLabel[barThreeCount];
					}					
					scoreEffect.setIcon(missImage);
					health -= failHealth;
					minusHealth(failHealth);
					checkCombo(); combo = 0; changeCombo();
				}

			}
		}
		
		//멀티
		if(multiNowOneBar !=null) {
			if(multiNowOneBar.getY() + (barH*multiTempOneLabel[multiBarOneCount][2]) - (barH/2)  >= lineY+badLine) {
				if(multiTempOneLabel[multiBarOneCount][2] == 1) {
					remove(multiNowOneBar);
					multiBarOneCount++;
					multiNowOneBar = null;
					if(multiBarOneCount < multiAddOneCount) {
						multiNowOneBar = multiNoteOneLabel[multiBarOneCount];
					}			
					multiScoreEffect.setIcon(missImage);
				}else if(multiTempOneLabel[multiBarOneCount][2] > 1) {
					tempLongBar[0] = multiNowOneBar;
					multiBarOneCount++;
					multiNowOneBar = null;
					if(multiBarOneCount < multiAddOneCount) {
						multiNowOneBar = multiNoteOneLabel[multiBarOneCount];
					}
					multiScoreEffect.setIcon(missImage);

				}
				
			}
		}
		if(multiNowTwoBar !=null) {
			if(multiNowTwoBar.getY() + (barH*multiTempTwoLabel[multiBarTwoCount][2]) - (barH/2)  >= lineY+badLine ) {
				if(multiTempTwoLabel[multiBarTwoCount][2] == 1) {
					remove(multiNowTwoBar);
					multiBarTwoCount++;
					multiNowTwoBar = null;
					if(multiBarTwoCount < multiAddTwoCount) {
						multiNowTwoBar = multiNoteTwoLabel[multiBarTwoCount];
					}				
					multiScoreEffect.setIcon(missImage);

				}else if(multiTempTwoLabel[multiBarTwoCount][2] > 1) {
					tempLongBar[1] = multiNowTwoBar;
					multiBarTwoCount++;
					multiNowTwoBar = null;
					if(multiBarTwoCount < multiAddTwoCount) {
						multiNowTwoBar = multiNoteTwoLabel[multiBarTwoCount];
					}				
					multiScoreEffect.setIcon(missImage);

				}
				
			}
		}
		if(multiNowThreeBar != null) {
			if(multiNowThreeBar.getY() + (barH*multiTempThreeLabel[multiBarThreeCount][2]) - (barH/2)  >= lineY+badLine) {
				if(multiTempThreeLabel[multiBarThreeCount][2] == 1) {
					remove(multiNowThreeBar);
					multiBarThreeCount++;
					multiNowThreeBar = null;
					if(multiBarThreeCount < multiAddThreeCount) {
						multiNowThreeBar = multiNoteThreeLabel[multiBarThreeCount];
					}					
					multiScoreEffect.setIcon(missImage);

				}else if(multiTempThreeLabel[multiBarThreeCount][2] > 1) {
					tempLongBar[2] = multiNowThreeBar;
					multiBarThreeCount++;
					multiNowThreeBar = null;
					if(multiBarThreeCount < multiAddThreeCount) {
						multiNowThreeBar = multiNoteThreeLabel[multiBarThreeCount];
					}					
					multiScoreEffect.setIcon(missImage);

				}

			}
		}


	}
	
	//긴 노트 체크
	private void longNoteCheck() {
		if(longOneCheck) {
			if(longOneCount >= music.delay*barH) {
				score+=(perfect*((combo/50)+1));
				scoreEffect.setIcon(perfectImage);
				combo ++; 
				changeCombo();
				longOneCount = 0;
			}
			
		}
		if(tempLongBar[0] != null && tempLongBar[0].getY() >= lineY +goodLine) {
			longOneCheck = false;
		}
		if(longTwoCheck) {
			if(longTwoCount >= music.delay*barH) {
				score+=(perfect*((combo/50)+1));
				scoreEffect.setIcon(perfectImage);
				combo ++; changeCombo();
				longTwoCount = 0;
			}
			
		}
		if(tempLongBar[1] != null && tempLongBar[1].getY() >= lineY +goodLine) {
			longTwoCheck = false;
		}
		if(longThreeCheck) {
			if(longThreeCount >= music.delay*barH) {
				score+=(perfect*((combo/50)+1));
				scoreEffect.setIcon(perfectImage);
				combo ++; 
				changeCombo();
				longThreeCount = 0;
			}
			
		}
		if(tempLongBar[2] != null && tempLongBar[2].getY() >= lineY +goodLine) {
			longThreeCheck = false;
		}
		
		//멀티
		if(multiLongOneCheck) {
			if(multiLongOneCount >= music.delay*barH) {
				multiScoreEffect.setIcon(perfectImage);
				multiLongOneCount = 0;
			}
			
		}
		if(multiTempLongBar[0] != null && multiTempLongBar[0].getY() >= lineY +goodLine) {
			multiLongOneCheck = false;
		}
		if(multiLongTwoCheck) {
			if(multiLongTwoCount >= music.delay*barH) {
				multiScoreEffect.setIcon(perfectImage);
				multiLongTwoCount = 0;
			}
			
		}
		if(multiTempLongBar[1] != null && multiTempLongBar[1].getY() >= lineY +goodLine) {
			multiLongTwoCheck = false;
		}
		if(multiLongThreeCheck) {
			if(multiLongThreeCount >= music.delay*barH) {
				multiScoreEffect.setIcon(perfectImage);
				multiLongThreeCount = 0;
			}
			
		}
		if(multiTempLongBar[2] != null && multiTempLongBar[2].getY() >= lineY +goodLine) {
			multiLongThreeCheck = false;
		}

	}
	
	//점수 최신화
	private void checkScore() {
		int temp = score;
		int temp2 = score;
		for(int i = 0 ; i < 6 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: scoreLabel[5-i].setIcon(nineScoreImage);break;
			case 8: scoreLabel[5-i].setIcon(eightScoreImage);break;
			case 7: scoreLabel[5-i].setIcon(sevenScoreImage);break;
			case 6: scoreLabel[5-i].setIcon(sixScoreImage);break;
			case 5: scoreLabel[5-i].setIcon(fiveScoreImage);break;
			case 4: scoreLabel[5-i].setIcon(fourScoreImage);break;
			case 3: scoreLabel[5-i].setIcon(threeScoreImage);break;
			case 2: scoreLabel[5-i].setIcon(twoScoreImage);break;
			case 1: scoreLabel[5-i].setIcon(oneScoreImage);break;
			case 0: scoreLabel[5-i].setIcon(zeroScoreImage);break;
			default: break;
			}
		}
	}
	
	//멀티 점수 최신화
	private void checkMScore() {
		int temp = enemyScore;
		int temp2 = enemyScore;
		for(int i = 0 ; i < 6 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: mscoreLabel[5-i].setIcon(nineScoreImage);break;
			case 8: mscoreLabel[5-i].setIcon(eightScoreImage);break;
			case 7: mscoreLabel[5-i].setIcon(sevenScoreImage);break;
			case 6: mscoreLabel[5-i].setIcon(sixScoreImage);break;
			case 5: mscoreLabel[5-i].setIcon(fiveScoreImage);break;
			case 4: mscoreLabel[5-i].setIcon(fourScoreImage);break;
			case 3: mscoreLabel[5-i].setIcon(threeScoreImage);break;
			case 2: mscoreLabel[5-i].setIcon(twoScoreImage);break;
			case 1: mscoreLabel[5-i].setIcon(oneScoreImage);break;
			case 0: mscoreLabel[5-i].setIcon(zeroScoreImage);break;
			default: break;
			}
		}
	}
	private void checkHealth() {
		if(health <= 0) {
			out.println("END");	
			music.stopMusic();
			timeCount = music.fulltime + 1;		
		}
	}
	
	private void minusHealth(int value) {
		healthLabel.setSize(healthLabel.getWidth(), healthLabel.getHeight()-value);
		healthLabel.setLocation(healthLabel.getX(), healthLabel.getY()+value);
		revalidate();
		repaint();
	}
	
	private void checkCombo() {
		if(combo >= maxCombo) {
			maxCombo = combo;
		}
	}
	
	private void changeCombo() {
		int temp = combo;
		int temp2 = combo;
		for(int i = 0 ; i < 3 ; i++) {
			temp2 = temp%10;
			temp = temp/10;
			switch(temp2) {
			case 9: comboNumLabel[2-i].setIcon(nineComboImage);break;
			case 8: comboNumLabel[2-i].setIcon(eightComboImage);break;
			case 7: comboNumLabel[2-i].setIcon(sevenComboImage);break;
			case 6: comboNumLabel[2-i].setIcon(sixComboImage);break;
			case 5: comboNumLabel[2-i].setIcon(fiveComboImage);break;
			case 4: comboNumLabel[2-i].setIcon(fourComboImage);break;
			case 3: comboNumLabel[2-i].setIcon(threeComboImage);break;
			case 2: comboNumLabel[2-i].setIcon(twoComboImage);break;
			case 1: comboNumLabel[2-i].setIcon(oneComboImage);break;
			case 0: comboNumLabel[2-i].setIcon(zeroComboImage);break;
			default: break;
			}
		}
	}
	
	//멀티 모드 화면 설정
	private void setMulti(String temp) {
		if(temp.equals("FP")) {
			multiScoreEffect.setIcon(perfectImage); 
			delMulNote(1);
		}else if(temp.equals("FG")) {
			multiScoreEffect.setIcon(greatImage); 
			delMulNote(1);
		}else if(temp.equals("FO")) {
			multiScoreEffect.setIcon(goodImage); 
			delMulNote(1);
		}
		else if(temp.equals("FB")) {
			multiScoreEffect.setIcon(badImage); 
			delMulNote(1);
		}else if(temp.equals("SP")) {
			multiScoreEffect.setIcon(perfectImage); 
			delMulNote(2);
		}
		else if(temp.equals("SG")) {
			multiScoreEffect.setIcon(greatImage); 
			delMulNote(2);
		}
		else if(temp.equals("SO")) {
			multiScoreEffect.setIcon(goodImage); 
			delMulNote(2);
		}
		else if(temp.equals("SB")) {
			multiScoreEffect.setIcon(badImage); 
			delMulNote(2);
		}
		else if(temp.equals("TP")) {
			multiScoreEffect.setIcon(perfectImage); 
			delMulNote(3);
		}
		else if(temp.equals("TG")) {
			multiScoreEffect.setIcon(greatImage); 
			delMulNote(3);
		}
		else if(temp.equals("TO")) {
			multiScoreEffect.setIcon(goodImage); 
			delMulNote(3);
		}
		else if(temp.equals("TB")) {
			multiScoreEffect.setIcon(badImage); 
			delMulNote(3);
		}
		else if(temp.equals("FLP")) {
			multiScoreEffect.setIcon(perfectImage); 
			delLongMulNote(1);
		}
		else if(temp.equals("FLG")) {
			multiScoreEffect.setIcon(greatImage); 
			delLongMulNote(1);
		}
		else if(temp.equals("FLO")) {
			multiScoreEffect.setIcon(goodImage); 
			delLongMulNote(1);
		}
		else if(temp.equals("FLB")) {
			multiScoreEffect.setIcon(badImage); 
			delLongMulNote(1);
		}
		else if(temp.equals("SLP")) {
			multiScoreEffect.setIcon(perfectImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("SLG")) {
			multiScoreEffect.setIcon(greatImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("SLO")) {
			multiScoreEffect.setIcon(goodImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("SLB")) {
			multiScoreEffect.setIcon(badImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("TLP")) {
			multiScoreEffect.setIcon(perfectImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("TLG")) {
			multiScoreEffect.setIcon(greatImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("TLO")) {
			multiScoreEffect.setIcon(goodImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("TLB")) {
			multiScoreEffect.setIcon(badImage); 
			delLongMulNote(2);
		}
		else if(temp.equals("END")) {
			music.stopMusic();
			timeCount = music.fulltime + 1;
		}else {
			enemyScore = Integer.valueOf(temp);
			System.out.println(enemyScore);
		}
		checkMScore();
	}
	
	//멀티 모드 화면 노트 제거
	private void delMulNote(int line) {
		switch(line) {
		case 1:
			if(multiTempOneLabel[multiBarOneCount][2] == 1) {
				if(multiNowOneBar!=null) {
					remove(multiNowOneBar);
				}
				
				multiBarOneCount++;
				multiNowOneBar = null;
				if(multiBarOneCount < multiAddOneCount) {
					multiNowOneBar = multiNoteOneLabel[multiBarOneCount];
				}		
			}
			else if(multiTempOneLabel[multiBarOneCount][2] > 1) {
				multiLongOneCheck = true;
				multiTempLongBar[0] = multiNowOneBar;
				multiBarOneCount++;
				multiNowOneBar = null;
				if(multiBarOneCount < multiAddOneCount) {
					multiNowOneBar = multiNoteOneLabel[multiBarOneCount];
				}
			}
			break;
		case 2: 
			if(multiTempTwoLabel[multiBarTwoCount][2] == 1) {
				if(multiNowTwoBar!=null) {
					remove(multiNowTwoBar);
				}				
				multiBarTwoCount++;
				multiNowTwoBar = null;
				if(multiBarTwoCount < multiAddTwoCount) {
					multiNowTwoBar = multiNoteTwoLabel[multiBarTwoCount];
				}		
			}
			else if(multiTempTwoLabel[multiBarTwoCount][2] > 1) {
				multiLongTwoCheck = true;
				multiTempLongBar[0] = multiNowTwoBar;
				multiBarTwoCount++;
				multiNowTwoBar = null;
				if(multiBarTwoCount < multiAddTwoCount) {
					multiNowTwoBar = multiNoteTwoLabel[multiBarTwoCount];
				}
			}
			break;
		case 3:
			if(multiTempThreeLabel[multiBarThreeCount][2] == 1) {
				if(multiNowThreeBar!=null) {
					remove(multiNowThreeBar);
				}
				
				multiBarThreeCount++;
				multiNowThreeBar = null;
				if(multiBarThreeCount < multiAddThreeCount) {
					multiNowThreeBar = multiNoteThreeLabel[multiBarThreeCount];
				}		
			}
			else if(multiTempThreeLabel[multiBarThreeCount][2] > 1) {
				multiLongThreeCheck = true;
				multiTempLongBar[0] = multiNowThreeBar;
				multiBarThreeCount++;
				multiNowThreeBar = null;
				if(multiBarThreeCount < multiAddThreeCount) {
					multiNowThreeBar = multiNoteThreeLabel[multiBarThreeCount];
				}
			}
			break;
		}
	}
	
	//멀티 화면 긴 노트 제거
	private void delLongMulNote(int line) {
		switch(line) {
		case 1: 
			multiLongOneCheck = false; 
			multiTempLongBar[0]= null;
		case 2:
			multiLongTwoCheck = false;
			multiTempLongBar[1]= null;
		case 3:
			multiLongThreeCheck = false;
			multiTempLongBar[2]= null;
		}
	}

	class DropBeat extends Thread{
		

		public void run() {
			//멀티 모드 서버 연결
			try {
				server = new ServerSocket(port);
				System.out.println("대기중");
				socket = server.accept();
				System.out.println("성공");
				
				out = new PrintWriter(socket.getOutputStream());
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//서버 연결 후 음악 재생
			selectMusic();
			noteSetting();
			String temp;
			//음악이 끝날 때 까지 실행
			while(timeCount<music.fulltime) {
				currentTime = ((System.nanoTime()-startTime)/1000000) - delayTime;
				
				out.println("game");
				out.flush();
				
				try {
					temp = in.readLine();
					if(!temp.equals("game")) {
						setMulti(temp);						
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//일시정지
				if(pauseCheck == 1) {
					synchronized (this) {
						try {
							
							tempTiem = (System.nanoTime()-startTime)/1000000;
							wait();							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				if(nowOneBar == null) {
					oneMode = false;
				}
				if(nowTwoBar == null) {
					twoMode = false;
				}
				if(nowThreeBar == null) {
					threeMode = false;
				}
				
				//실시간으로 노트 생성
				noteAdd();		
				
				//일정 시간마다 동작 수행
				if((System.nanoTime()-startTime)/1000000>=timeCount) {
					noteMove();
					timeCount+=music.delay;
					longOneCount +=music.delay;
					longTwoCount +=music.delay;
					longThreeCount +=music.delay;
					checkMode();
					noTouchCheck();			
					longNoteCheck();
					checkScore();
					checkHealth();
				}
				
				revalidate();
				repaint();
				
			}
			
			new MultiScoreWindow(userID,musicID,score,maxCombo,enemyScore);
			gw.dispose();
			System.out.println("END");
			try {
				server.close();
				socket.close();
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			this.interrupt();
			
		}
	}	

	@Override
	public void keyTyped(KeyEvent e) {
				
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:

			//버튼 이펙트 효과
			OneButton.setIcon(pushAButtonImage);
			pushLight[0].setIcon(pushLightImage);
			//입력 점수 변환
			if(oneMode == true) {
				out.println(score);
				out.flush();
				if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2) >= lineY-badLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2) < lineY-goodLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth; minusHealth(badHealth); checkCombo(); combo = 0; changeCombo();
					out.println("FB");
					out.flush();
				}else if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY-goodLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  < lineY-greatLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage); combo ++; changeCombo();
					out.println("FO");
					out.flush();
				}else if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY-greatLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  < lineY-perfectLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage);combo ++; changeCombo();
					out.println("FG");
					out.flush();
				}else if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY-perfectLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  < lineY+perfectLine) {
					score+=(perfect*((combo/50)+1));scoreEffect.setIcon(perfectImage);combo ++; changeCombo();
					out.println("FP");
					out.flush();
				}else if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY+perfectLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  < lineY+greatLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage);combo ++; changeCombo();
					out.println("FG");
					out.flush();
				}else if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY+greatLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  < lineY+goodLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage);combo ++; changeCombo();
					out.println("FO");
					out.flush();
				}else if(nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  >= lineY+goodLine && nowOneBar.getY() + (barH*tempOneLabel[barOneCount][2]) - (barH/2)  < lineY+badLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth; minusHealth(badHealth); checkCombo(); combo = 0; changeCombo();
					out.println("FB");
					out.flush();
				}
				if(tempOneLabel[barOneCount][2] == 1) {
					oneMode = false;
					remove(nowOneBar);
					barOneCount++;
					nowOneBar = null;
					if(barOneCount < addOneCount) {
						nowOneBar = noteOneLabel[barOneCount];
					}		
				}
				else if(tempOneLabel[barOneCount][2] > 1) {
					longOneCheck = true;
					oneMode = false;
					tempLongBar[0] = nowOneBar;
					barOneCount++;
					nowOneBar = null;
					if(barOneCount < addOneCount) {
						nowOneBar = noteOneLabel[barOneCount];
					}
				}	
			}break;
		case KeyEvent.VK_D:
			//버튼 이펙트
			TwoButton.setIcon(pushBButtonImage);
			pushLight[1].setIcon(pushLightImage);
			//입력 점수
			if(twoMode == true) {
				out.println(score);
				out.flush();
				if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2) >= lineY-badLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2) < lineY-goodLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth;minusHealth(badHealth); checkCombo(); combo = 0; changeCombo();
					out.println("SB");
					out.flush();
				}else if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY-goodLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  < lineY-greatLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage); combo ++; changeCombo();
					out.println("SO");
					out.flush();
				}else if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY-greatLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  < lineY-perfectLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage); combo ++; changeCombo();
					out.println("SG");
					out.flush();
				}else if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY-perfectLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  < lineY+perfectLine) {
					score+=(perfect*((combo/50)+1));scoreEffect.setIcon(perfectImage); combo ++; changeCombo();
					out.println("SP");
					out.flush();
				}else if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY+perfectLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2) - (barH/2)  < lineY+greatLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage); combo ++; changeCombo();
					out.println("SG");
					out.flush();
				}else if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY+greatLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  < lineY+goodLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage); combo ++; changeCombo();
					out.println("SO");
					out.flush();
				}else if(nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  >= lineY+goodLine && nowTwoBar.getY() + (barH*tempTwoLabel[barTwoCount][2]) - (barH/2)  < lineY+badLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -=badHealth;minusHealth(badHealth); checkCombo(); combo = 0; changeCombo();
					out.println("SB");
					out.flush();
				}
				if(tempTwoLabel[barTwoCount][2] == 1) {
					twoMode = false;
					remove(nowTwoBar);
					barTwoCount++;
					nowTwoBar = null;
					if(barTwoCount < addTwoCount) {
						nowTwoBar = noteTwoLabel[barTwoCount];
					}
				}
				else if(tempTwoLabel[barTwoCount][2] > 1) {
					longTwoCheck = true;
					twoMode = false;
					tempLongBar[1] = nowTwoBar;
					barTwoCount++;
					nowTwoBar = null;
					if(barTwoCount < addTwoCount) {
						nowTwoBar = noteTwoLabel[barTwoCount];
					}
				}		
			}break;
		case KeyEvent.VK_K:
			
			//버튼 이펙트
			ThreeButton.setIcon(pushCButtonImage);
			pushLight[2].setIcon(pushLightImage);
			//입력 점수 변환
			if(threeMode == true) {
				out.println(score);
				out.flush();
				if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2) >= lineY-badLine && nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2) < lineY-goodLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth; minusHealth(badHealth); checkCombo(); combo = 0; changeCombo();
					out.println("TB");
					out.flush();
				}else if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >= lineY-goodLine && nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  < lineY-greatLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage); combo ++; changeCombo();
					out.println("TO");
					out.flush();
				}else if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >= lineY-greatLine && nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  < lineY-perfectLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage); combo ++; changeCombo();
					out.println("TG");
					out.flush();
				}else if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >= lineY-perfectLine && nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  < lineY+perfectLine) {
					score+=(perfect*((combo/50)+1));scoreEffect.setIcon(perfectImage); combo ++; changeCombo();
					out.println("TP");
					out.flush();
				}else if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >= lineY+perfectLine && nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  < lineY+greatLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage); combo ++; changeCombo();
					out.println("TG");
					out.flush();
				}else if(nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  >= lineY+greatLine && nowThreeBar.getY() + (barH*tempThreeLabel[barThreeCount][2]) - (barH/2)  < lineY+goodLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage); combo ++; changeCombo();
					out.println("TO");
					out.flush();
				}else if(nowThreeBar.getY() + (barH*music.note[barThreeCount][2]) - (barH/2)  >= lineY+goodLine && nowThreeBar.getY() + (barH*music.note[barThreeCount][2]) - (barH/2)  < lineY+badLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -=badHealth; minusHealth(badHealth); checkCombo(); combo = 0; changeCombo();
					out.println("TB");
					out.flush();
				}
				if(tempThreeLabel[barThreeCount][2] == 1) {
					threeMode = false;
					remove(nowThreeBar);
					barThreeCount++;
					nowThreeBar = null;
					if(barThreeCount < addThreeCount) {
						nowThreeBar = noteThreeLabel[barThreeCount];
					}
				}
				else if(tempThreeLabel[barThreeCount][2] > 1) {
					longThreeCheck = true;
					threeMode = false;
					tempLongBar[2] = nowThreeBar;
					barThreeCount++;
					nowThreeBar = null;
					if(barThreeCount < addThreeCount) {
						nowThreeBar = noteThreeLabel[barThreeCount];
					}
				}					
			}break;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//키 땟을 때 설정(긴 노트)
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A: 
			//버튼 이펙트
			OneButton.setIcon(nomalAButtonImage);
			pushLight[0].setIcon(null);
		
			longOneCheck = false; 
			if(tempLongBar[0]!=null) {
				out.println(score);
				out.flush();
				if(tempLongBar[0].getY()+5 < lineY -goodLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth; minusHealth(badHealth); remove(tempLongBar[0]); checkCombo(); combo = 0; changeCombo();
					tempLongBar[0]= null;
					out.println("FLB");
					out.flush();
				}else if(tempLongBar[0].getY() >= lineY - goodLine && tempLongBar[0].getY() < lineY-greatLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage);remove(tempLongBar[0]); combo ++; changeCombo();
					tempLongBar[0]= null;
					out.println("FLO");
					out.flush();
				}else if(tempLongBar[0].getY() >= lineY - greatLine && tempLongBar[0].getY() < lineY-perfectLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage);remove(tempLongBar[0]); combo ++; changeCombo();
					tempLongBar[0]= null;
					out.println("FLG");
					out.flush();
				}else if(tempLongBar[0].getY() >= lineY - perfectLine) {
					score+=(perfect*((combo/50)+1));scoreEffect.setIcon(perfectImage);remove(tempLongBar[0]); combo ++; changeCombo();
					tempLongBar[0]= null;
					out.println("FLP");
					out.flush();
				}break;
			}
			
		case KeyEvent.VK_D: 
			//버튼 이펙트
			TwoButton.setIcon(nomalBButtonImage);
			pushLight[1].setIcon(null);
		
			longTwoCheck = false; 
			if(tempLongBar[1]!=null) {
				out.println(score);
				out.flush();
				if(tempLongBar[1].getY()+5 < lineY -goodLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth; minusHealth(badHealth); remove(tempLongBar[1]); checkCombo(); combo = 0; changeCombo();
					tempLongBar[1]= null;
					out.println("SLB");
					out.flush();
					
				}else if(tempLongBar[1].getY() >= lineY - goodLine && tempLongBar[1].getY() < lineY-greatLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage);remove(tempLongBar[1]); combo ++; changeCombo();
					tempLongBar[1]= null;
					out.println("SLO");
					out.flush();
				}else if(tempLongBar[1].getY() >= lineY - greatLine && tempLongBar[1].getY() < lineY-perfectLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage);remove(tempLongBar[1]); combo ++; changeCombo();
					tempLongBar[1]= null;
					out.println("SLG");
					out.flush();
				}else if(tempLongBar[1].getY() >= lineY - perfectLine) {
					score+=(perfect*((combo/50)+1));scoreEffect.setIcon(perfectImage);remove(tempLongBar[1]); combo ++; changeCombo();
					tempLongBar[1]= null;
					out.println("SLP");
					out.flush();
				}break;
			}
		case KeyEvent.VK_K: 
			
			ThreeButton.setIcon(nomalCButtonImage);
			pushLight[2].setIcon(null);
			
			longThreeCheck = false; 
			if(tempLongBar[2]!=null) {
				out.println(score);
				out.flush();
				if(tempLongBar[2].getY()+5 < lineY - goodLine) {
					score+=(bad*((combo/50)+1));scoreEffect.setIcon(badImage);health -= badHealth; minusHealth(badHealth); remove(tempLongBar[2]); checkCombo(); combo = 0; changeCombo();
					tempLongBar[2]= null;
					out.println("TLB");
					out.flush();
				}else if(tempLongBar[2].getY() >= lineY - goodLine && tempLongBar[2].getY() < lineY-greatLine) {
					score+=(good*((combo/50)+1));scoreEffect.setIcon(goodImage);remove(tempLongBar[2]); combo ++; changeCombo();
					tempLongBar[2]= null;
					out.println("TLO");
					out.flush();
				}else if(tempLongBar[2].getY() >= lineY - greatLine && tempLongBar[2].getY() < lineY-perfectLine) {
					score+=(great*((combo/50)+1));scoreEffect.setIcon(greatImage);remove(tempLongBar[2]); combo ++; changeCombo();
					tempLongBar[2]= null;
					out.println("TLG");
					out.flush();
				}else if(tempLongBar[2].getY() >= lineY - perfectLine) {
					score+=(perfect*((combo/50)+1));scoreEffect.setIcon(perfectImage);remove(tempLongBar[2]); combo ++; changeCombo();
					tempLongBar[2]= null;
					out.println("TLP");
					out.flush();
				}break;
			}
		default:  break;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pauseButton) {
			add(pausePanel,0);
			pauseCheck = 1;
			music.pauseMusic();
			remove(pauseButton);
			revalidate();
			repaint();
		}
		if(e.getSource() == pauseResume ) {
			remove(pausePanel);
			revalidate();
			repaint();
			synchronized (db) {
				delayTime = delayTime + ((System.nanoTime()-startTime)/1000000) - tempTiem;
				pauseCheck = 2;
				db.notify();
				this.requestFocus();
			}
			music.restartMusic();
		}
		if(e.getSource() == pauseExit) {
			new MusicSelect(userID);
			dispose();
		}
	}

		
	}
public static void main(String[] args) {
	new MultiGameWindow("test",1,9999);
}
}


