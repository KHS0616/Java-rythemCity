package inhatc;
import java.io.File;


public class FlightOfTheBumblebee extends Music{
	int[][] notelist = { {3,1,hado+ei},{2,1,si},{3,1,hado+ei},{1,1,si},{2,1,ha},{3,1,ha},{2,1,qu},{2,1,qu},{1,1,qu},{3,1,qu},{1,1,ei},{2,1,ei},{3,25,hado+ei},
			{1,1,qu},{1,1,qu},{3,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,ei},{1,1,ei},{3,1,ei},
			{1,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,qu},{3,1,qu},{1,1,qu},{3,1,qu},{3,1,qu},
			{2,1,qu},{1,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,qu},{2,1,qu},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},
			{1,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ha},{1,1,ei},{2,1,ei},
			{3,1,ei},{1,1,ei},{3,1,ha+si},{2,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},
			{1,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ha},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,wh},{1,1,qu+ei},{3,1,ei},{1,1,qu},{3,1,qu},
			{1,1,qu},{3,1,qu},{2,1,qu},{1,1,si},{2,1,si},{3,1,si},{1,1,qu},{1,1,qu},{3,1,qu},{3,1,qu},{1,1,qu},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},
			{1,1,qu},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{2,1,ei},{3,1,qu},{1,1,qu},{3,1,qu},{3,1,qu},{2,1,qu},{2,1,qu},{3,1,qu},{3,1,qu},{1,1,qu},{3,1,ei},
			{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,qu},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,qu},{1,1,qu},{3,1,qu},{3,1,qu},{1,1,ei},
			{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,qu},{1,1,qu},{3,1,qu},{1,1,qu},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{3,1,ei},{1,1,ei},
			{3,1,ei},{2,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,qu},{1,1,qu},{3,1,qu},
			{1,1,qu},{2,1,qu},{1,1,qu},{2,1,qu},{1,1,qu},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},
			{1,1,ei},{2,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{2,1,ei},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ei},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,qu},{3,1,qu},{2,1,ei},{3,1,ei},{1,1,ei},
			{2,1,ei},{1,1,ei},{3,1,qu},{1,1,qu},{3,1,ha},{2,1,qu},{3,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{1,1,ei},{3,1,qu},{1,1,qu},{3,1,ha},{3,1,ei},{1,1,ei},
			{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,qu},{1,1,ei},{3,1,ei},
			{2,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{3,1,qu},{1,1,ei},{3,1,ei},{2,1,ei},{3,1,ei},{1,1,ei},{2,1,ei},{3,1,ha},{1,1,qu},{3,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},
			{3,1,qu},{2,1,ha},{3,1,ha},{1,20,end}};

	public FlightOfTheBumblebee() {	 	   
		setDelay(4);
		setBpm(175.01);
		setFulltime(78000);
		setNote(notelist);
		setFile(new File("Music\\CZ0k1.wav"));
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
