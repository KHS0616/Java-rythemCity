package inhatc;
import java.io.File;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Music {
	//정보 변수
	int delay; 
	double bpm; 
	int fulltime; 
	int oneX = 0, twoX = 100, threeX = 200; 
	int firstTime = 100; 
	int firstH = 0; 
	int note[][]; 
	int noteR[][]; 
	int sumTime = 0; 
	File file;
	
	//음악 재생 변수
	AudioInputStream stream;
	Clip clip;
	int temp;
	
	//박자 변수
	int end = 10000000;
	int qu = 100;
	int ei = qu/2;
	int si = ei/2;
	int ha = qu*2;
	int hado = qu*3;
	int wh = ha*2;
	
	//노트 변수
	Vector<int[]> noteOne = new Vector<int[]>();
	Vector<int[]> noteTwo = new Vector<int[]>();
	Vector<int[]> noteThree = new Vector<int[]>();
	public Music() {
	}
	
	public void noteSetting() {
		for(int i = 0 ; i < noteR.length ; i++) {
			switch(noteR[i][0]) {
			case 1: noteOne.add(noteR[i]); break;
			case 2: noteTwo.add(noteR[i]); break;
			case 3: noteThree.add(noteR[i]); break;
			default: break;
			}
		}	
		
	}
	//음악 재생
	public void playMusic() {
		 try {	            
	            stream = AudioSystem.getAudioInputStream(file);
	            clip = AudioSystem.getClip();
	            clip.open(stream);
	            clip.start();	
	            
	        } catch(Exception e) {	            
	            System.out.println("Music Error");
	        }
	}
	
	//음악 정지
	public void stopMusic() {
		 try {
			 System.out.println(clip.getFramePosition());
	           clip.stop();
	           
	        } catch(Exception e) {	            
	            System.out.println("Music Error");
	        }
	}
	
	//음악 일시정지
	public void pauseMusic() {
		 try {
			   temp = clip.getFramePosition();
	           clip.stop();	           
	        } catch(Exception e) {	            
	            System.out.println("Music Error");
	        }
	}
	
	//음악 재시작
	public void restartMusic() {
		clip.setFramePosition(temp);
		clip.start();
	}
	
	//실 노트를 게임노트로 변환
	public int[][] noteChange(int[][] note) {
		noteR = new int[note.length][4];
		for(int i = 0 ; i < note.length ; i++) {
			this.noteR[i][0] = note[i][0];
			this.noteR[i][1] = firstH;
			this.noteR[i][2] = note[i][1];
			this.noteR[i][3] = note[i][2];
			if(i==0) {
				noteR[i][3] = firstTime;
				continue;
			}
			noteR[i][3] = noteR[i-1][3] + note[i-1][2];
		}
		return noteR;
	}
	
	//추상메소드 선언
	public abstract void setDelay(int delay);
	public abstract void setBpm(double bpm);
	public abstract void setFulltime(int fulltime);
	public abstract void setNote(int[][] note);
	public abstract void setFile(File file);
	
	

}
