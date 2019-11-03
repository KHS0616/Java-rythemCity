package inhatc;
import java.io.File;

public class SadLoveOfMoon extends Music{
	int[][] notelist = {{1,1,ei},{2,1,ei},{3,1,hado},{2,1,ei},{3,1,ei},{1,1,ha},{3,1,qu},{2,1,qu},{1,1,qu},
			{2,1,qu},{3,1,qu},{2,1,qu},{1,1,ei},{2,1,ei},{3,1,ei},{2,1,ei},{1,1,qu},
			{1,1,ei},{2,1,ei},{3,1,hado},{2,1,ei},{3,1,ei},{1,1,ha},{3,1,qu},{2,1,qu},{1,1,qu},
			{2,1,qu},{3,1,qu},{2,1,qu},{1,1,ei},{2,1,ei},{3,1,ei},{3,1,ei},{2,1,ha},
			{3,1,hado},{1,1,ei},{3,1,ei},{1,1,hado},{3,1,ei},{2,1,ei},{1,1,ha},
			{3,1,ha},{2,1,ha},{1,1,qu},{2,1,qu},{3,20,hado},{1,1,ei},{2,1,ei},{1,20,hado},{3,1,ei},{2,1,ei},{1,1,ha},{3,1,ha},{2,1,ha},{1,1,qu},{2,1,qu},{3,20,hado},
			{1,1,ei},{2,1,ei},{1,1,hado},{3,1,ei},{2,1,ei},{1,1,ha},{3,10,ha},{1,1,ha},{2,1,qu},{1,1,ha},{3,1,qu},{1,1,ei},{2,1,ei},{1,1,qu},{3,1,qu},{2,1,ei},{1,1,ei},
			{2,1,ei},{3,1,ei},{3,1,qu},{2,1,qu},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{2,1,qu},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,qu},
			{2,1,qu},{1,1,ei},{3,1,qu},{1,1,ha},{3,1,qu},{2,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},{3,1,qu},{2,1,qu},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{2,1,qu},
			{3,1,qu},{2,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},{3,1,ei},{1,1,hado},{1,1,ei},{2,1,ei},{3,1,hado},{1,1,ei},{2,1,ei},{3,10,ha},{1,1,qu},{2,1,qu},{3,10,ha},
			{1,1,qu},{2,1,qu},{3,1,hado},{1,1,ei},{2,1,ei},{3,1,hado},{1,1,ei},{2,1,ei},{3,10,ha},{1,1,qu},{2,1,qu},{3,10,ha},{1,1,qu},{2,1,qu},{3,120,1650},
			{1,1,ei},{3,1,ei},{1,1,ei},{1,1,ei},{3,1,ei+si},{2,1,ei+si},{1,1,ei},{2,1,ei},{3,1,ei},{3,1,ei},{3,1,ei},{1,1,ei+si},{3,1,ei+si},{2,1,ei},{1,1,ei},{3,1,ei},
			{1,1,ei},{1,1,ei},{3,1,ei+si},{3,1,ei+si},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{1,1,ei},{3,1,ei+si},{2,1,ei+si},{3,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{1,1,ei},
			{3,1,ei+si},{2,1,ei+si},{1,1,ei},{2,1,ei},{3,1,ei},{3,1,ei},{1,1,ei},{3,1,ei+si},{3,1,ei+si},{2,1,ei},{1,1,ei},{3,1,ei},{3,1,ei},{3,1,ei},{1,1,ei+si},
			{2,1,ei+si},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ha+ei},{2,1,end}};

	public SadLoveOfMoon() {	 	   
		setDelay(4);
		setBpm(150.05);
		setFulltime(85000);
		setNote(notelist);
		setFile(new File("Music\\RSW.wav"));
		super.noteChange(note);
		super.noteSetting();
	}
	@Override
	public void setDelay(int delay) {
		this.delay = delay;		
	}
	@Override
	public void setBpm(double bpm) {
		this.bpm = bpm;		
	}
	@Override
	public void setFulltime(int fulltime) {
		this.fulltime = fulltime;		
	}
	@Override
	public void setNote(int[][] note) {
		this.note = note;		
	}
	@Override
	public void setFile(File file) {
		this.file = file;
		
	}
}
