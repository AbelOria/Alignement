package Alignement;

public class Sequence {
	
	private String name;
	private String[] sequence;
	
	public Sequence(String name, String sequence){
		this.sequence = getSequence(sequence);
	}
	
	private String[] getSequence(String sequence2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName(){
		return name;
	};
	
	public String[] getSequence(){
		// TODO Auto-generated method stub
		return sequence;
	}
	
	public int size(){
		return sequence.length;
	}

}
