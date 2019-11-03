package inhatc;
import java.io.File;

public class Canon extends Music {
	int[][] notelist = {{1,1,ha+ha},{3,1,ha},{2,1,ha},{1,1,ha+ha},{3,1,ha},{2,1,ha},{1,1,ha+ha},{1,1,ha},{3,1,ha},{2,1,ha+ha},{3,1,ha},{1,1,ha},{2,1,ha+ha},{3,1,ha},
			{2,1,ha},{1,1,ha},{2,1,ha},{1,1,ha},{3,1,ha},{2,1,ha},{1,1,ha},{3,1,ha},{1,1,ha},{2,1,ha},{3,1,ha},{1,1,ha},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{3,1,qu},
			{1,1,qu},{2,1,ha},{3,1,ha},{1,1,ha},{2,1,ha},{3,1,qu},{1,1,qu},{2,1,qu},{3,1,qu},{2,1,qu},{1,1,qu},{2,1,qu},{3,1,qu},{2,1,qu},{1,1,qu},{3,1,qu},{1,1,qu},
			{3,1,qu},{1,1,qu},{2,1,qu},{3,1,qu},{2,1,ha+ei},{1,1,ei},{3,1,qu},{1,1,ha},{3,1,qu},{1,1,ei},{3,1,ha},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,qu},{3,1,qu+ei},{3,1,qu+ei},
			{1,1,qu},{3,1,qu},{2,1,ei},{1,1,qu},{3,1,qu},{1,1,qu+ei},{2,1,qu+ei},{3,1,qu},{2,1,qu},{3,1,ei},{1,1,hado},{2,1,ha},{1,1,ei},{2,1,ei},{3,1,qu},{1,1,qu+ei},
			{2,1,qu+ei},{1,1,qu},{3,1,qu},{1,1,ei},{2,1,ei},{3,1,qu},{1,1,ei},{2,1,ei},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,ei},{2,1,ei},{3,1,qu},{1,1,ei},
			{2,1,ei},{3,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,qu},{3,1,ei},{2,1,ei},{1,1,qu},{2,1,ei},{3,1,ei},{1,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{3,1,ei},
			{1,1,ei},{3,1,qu},{2,1,ei},{1,1,ei},{3,10,ha},{1,10,ha},{3,1,ha},{1,1,qu},{2,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,qu},{3,1,ha},{1,1,qu},{2,1,qu},{1,1,qu},
			{3,1,qu},{2,1,qu},{1,1,qu},{3,1,ha},{2,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,qu+ei},{2,1,qu+ei},
			{3,1,qu},{2,10,ha},{1,1,qu},{2,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,qu},{3,1,ha},{1,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,qu},{3,1,ha},{2,1,qu},
			{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{3,80,4*ha}};
	
	public Canon() {
		setDelay(4);
		setBpm(159.99);
		setFulltime(90000);
		setNote(notelist);
		setFile(new File("Music\\Canon.wav"));
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
