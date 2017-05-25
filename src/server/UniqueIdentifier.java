package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueIdentifier {
	private static List<Integer> ids=new ArrayList<Integer>();
	private static final int RANGE=10000;
	
	private static int index=0;
	static{ //this is static class bc it will run without a method,bc we do not want to call it just want to be exist
		for(int i=0;i<RANGE;i++){
			ids.add(i);
		}
		Collections.shuffle(ids); //
	}
	private UniqueIdentifier(){
		
	}
	public static int getIdentifier(){
		if(index>ids.size()-1) index=0;
		return ids.get(index++);
		
	}
	

}
