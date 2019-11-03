package inhatc;
import java.io.File;

public class TheNutcrackerMarch extends Music{
	int[][] notelist = {{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ha},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},
			{3,1,qu},{1,1,qu},{2,1,ha},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{1,1,si},{2,1,ei+si},{3,1,si},{2,1,ei+si},
			{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{2,1,si},{1,1,ei+si},{2,1,si},{3,1,ei+si},
			{2,1,si},{1,1,ei+si},{1,1,si},{3,1,ei+si},{1,1,si/2},{2,1,si/2},{3,1,qu},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ha},
			{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ha},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},
			{1,1,ei+si},{1,1,si},{2,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},
			{1,1,ei+si},{2,1,si},{1,1,ei+si},{2,1,si},{3,1,ei+si},{2,1,si},{1,1,ei+si},{1,1,si},{3,1,ei+si},{1,1,si/2},{2,1,si/2},{3,1,qu},
			{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,qu},{2,1,si},{3,1,ei+si},{1,1,si},{2,1,ei+si},{1,1,si},{3,1,ei+si},{2,1,si},
			{3,1,ei+si},{1,1,si},{2,1,ei+si},{1,1,si},{3,1,ei+si},{1,1,si},{2,1,ei+si},{1,1,si},{3,1,ei+si},{2,1,si},{3,1,qu},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},
			{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},
			{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{2,1,ei},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},
			{1,1,qu},{2,1,ha},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ha},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},
			{1,1,ei+si},{3,1,si},{1,1,ei+si},{1,1,si},{2,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},
			{1,1,ei+si},{3,1,si},{1,1,ei+si},{2,1,si},{1,1,ei+si},{2,1,si},{3,1,ei+si},{2,1,si},{1,1,ei+si},{1,1,si},{3,1,ei+si},{1,1,si/2},{2,1,si/2},{3,1,qu},
			{1,1,si},{3,1,si},{1,1,si},{3,1,si},{2,1,si},{3,1,si},{2,1,si},{1,1,si},{2,1,si},{3,1,si},{2,1,si},{3,1,si},{1,1,si},{3,1,si},{1,1,si},{3,1,si},
			{2,1,si},{2,1,si},{3,1,si},{3,1,si},{1,1,si},{3,1,si},{2,1,si},{3,1,si},{1,1,si},{3,1,si},{2,1,si},{3,1,si},{1,1,si},{3,1,si},{2,1,si},{3,1,si},
			{1,1,si},{3,1,si},{1,1,si},{3,1,si},{2,1,si},{3,1,si},{2,1,si},{1,1,si},{2,1,si},{3,1,si},{2,1,si},{3,1,si},{1,1,si},{3,1,si},{1,1,si},{3,1,si},
			{2,1,si},{2,1,si},{3,1,si},{3,1,si},{1,1,si},{3,1,si},{2,1,si},{3,1,si},{1,1,si},{2,1,si},{3,1,si},{2,1,si},{3,1,si},{1,1,si},{3,1,si},{1,1,si},{2,1,si},
			{3,1,qu+ei},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ha},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},
			{3,1,qu},{1,1,qu},{2,1,ha},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{1,1,si},{2,1,ei+si},{3,1,si},{2,1,ei+si},
			{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{2,1,si},{1,1,ei+si},{2,1,si},{3,1,ei+si},
			{2,1,si},{1,1,ei+si},{1,1,si},{3,1,ei+si},{1,1,si/2},{2,1,si/2},{3,1,qu},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},
			{3,1,qu},{1,1,qu},{3,1,qu},{2,1,qu},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},
			{3,1,ei},{2,1,ei},{1,1,ei},{3,1,ei},{1,1,ei},{3,1,ei},{2,1,ei},{2,1,ei},
			{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},{3,1,qu},{1,1,qu},{2,1,ha},{3,1,qu+si},{1,1,si},{1,1,si},{1,1,si},{2,1,qu},{2,1,qu},
			{3,1,qu},{1,1,qu},{2,1,ha},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{1,1,si},{2,1,ei+si},{3,1,si},{2,1,ei+si},
			{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{3,1,si},{2,1,ei+si},{2,1,si},{1,1,ei+si},{3,1,si},{1,1,ei+si},{2,1,si},{1,1,ei+si},{2,1,si},{3,1,ei+si},
			{2,1,si},{1,1,ei+si},{1,1,si},{3,1,ei+si},{1,1,ei},{2,1,end}};

	public TheNutcrackerMarch() {	 	   
		setDelay(4);
		setBpm(141.01);
		setFulltime(100000);
		setNote(notelist);
		setFile(new File("Music\\TheNutcrackerMarch.wav"));
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
