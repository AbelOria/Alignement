package Alignement;


import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;



public class AlignementMultipleTests extends AlignementMultiple {

	
	
	
	@Test
	public void getZTest() {
		AlignementMultiple c = new AlignementMultiple(20);
		String sequence1[] = {"a","b","-","l"};
		String sequence2[] = {"a","b","-","o"};
		String sequence3[] = {"r","b","e","r"};
		String sequence4[] = {"c","b","e","i"};
		
		LinkedList<String[]> sequencesAlignes = new LinkedList<String[]>();
		sequencesAlignes.add(sequence1);
		sequencesAlignes.add(sequence2);
		sequencesAlignes.add(sequence3);
		sequencesAlignes.add(sequence4);
		
		String z[] = {"a","b","?","?"};
		assertEquals(z, c.calculerZ(sequencesAlignes));
	}
	
	@Test
	public void pourcentageIdentite() {
		String z[] = {"a","b","-","?", "c","-"};
		
		AlignementMultiple c = new AlignementMultiple(20);
		String sequence1[] = {"a","b","-","l","c","d"};
		String sequence2[] = {"a","b","-","o","-","d"};
		String sequence3[] = {"r","b","e","r","c","c"};
		String sequence4[] = {"c","b","e","i","d","d"};
		
		LinkedList<String[]> sequencesAlignes = new LinkedList<String[]>();
		sequencesAlignes.add(sequence1);
		sequencesAlignes.add(sequence2);
		sequencesAlignes.add(sequence3);
		sequencesAlignes.add(sequence4);
		
		double[] ids = c.getPourcentageIdentite(sequencesAlignes, z);
	
		assertEquals(4/6.0, ids[0],0.01);
		assertEquals(3/6.0, ids[1], 0.01);
		assertEquals(2/6.0, ids[2],0.01);
		assertEquals(1/6.0, ids[3],0.01);
	}
	
	@Test
	public void getIntrus(){
		
		AlignementMultiple c = new AlignementMultiple(20);
		String sequence1[] = {"a","b","-","l","c","d"};
		String sequence2[] = {"a","b","-","o","-","d"};
		String sequence3[] = {"r","b","e","r","c","c"};
		String sequence4[] = {"c","b","e","i","d","d"};
		
		LinkedList<String[]> sequencesAlignes = new LinkedList<String[]>();
		sequencesAlignes.addLast(sequence1);
		sequencesAlignes.addLast(sequence2);
		sequencesAlignes.addLast(sequence3);
		sequencesAlignes.addLast(sequence4);
		
		
		int indexFamille[] = {0, 1};
		
		LinkedList<String[]> intrus = new LinkedList<String[]>();
		intrus.addLast(sequence3);
		intrus.addLast(sequence4);
		
		assertEquals(intrus,c.getIntrus(sequencesAlignes, indexFamille));
	}
	
	@Test
	public void getIndexFamilleTest(){
		AlignementMultiple c = new AlignementMultiple(5);
		
		String[] z =  		 {"a","?","?","?","?","d","a","b","b","o","-","d","a","b","-","o","-","d","a","b","-","o","-","d","a","b","-","o","-","d"};
		
		String sequence1[] = {"a","b","-","a","-","d","a","b","-","o","-","d","a","b","-","o","-","d","a","b","-","o","-","d","a","b","-","o","-","d"}; //T18, B18, M0
		String sequence2[] = {"a","c","a","a","c","d","a","b","f","o","-","d","a","b","-","o","-","d","a","b","-","o","-","d","a","b","-","o","-","d"}; //T19, B18, M1
		String sequence3[] = {"a","d","f","o","c","d","a","b","-","o","-","d","a","d","-","s","-","d","a","b","-","o","-","d","a","b","-","o","-","d"}; //T18, B16, M2
		String sequence4[] = {"a","e","g","o","-","d","v","t","f","e","t","g","e","k","l","g","y","x","p","e","t","o","a","g","w","r","s","l","g","b"}; //T19, B4, M14
		
		LinkedList<String[]> sequencesAlignes = new LinkedList<String[]>();
		sequencesAlignes.addLast(sequence1);
		sequencesAlignes.addLast(sequence2);
		sequencesAlignes.addLast(sequence3);
		sequencesAlignes.addLast(sequence4);
		
		
		int indexFamille[] = {0,1,2};
		int infa[] = c.getIndexFamille(sequencesAlignes, z);

		assertEquals(indexFamille[0],infa[0]);
		assertEquals(indexFamille[1],infa[1]);
		assertEquals(indexFamille[2],infa[2]);
//		assertEquals(indexFamille,infa);
		
	}
}
