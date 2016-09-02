import java.util.Scanner;

public class Brackets {
	
	static int levelAt = 0;
	static int maxLevel = -1;
	int start;
	int end;
	
	public static void main(String args[]) {
		
		String input, output = "";
		char[] arr;
		Scanner in = new Scanner(System.in);
		int i;
		
		System.out.println("Please enter your phrase: ");
		input = in.nextLine();
		
		//each index is a 'level', each 'level' has a start and end point
		Brackets[] br = new Brackets[input.length()/2];
		for(i=0; i<br.length; i++) {
			br[i] = new Brackets();
		}
		
		arr = input.toCharArray();
		
		//get the bracket positions
		for(i=0; i<input.length(); i++) {
			
			//if we have an opening bracket
			if(arr[i] == '[' || arr[i] == '(' || arr[i] == '{') {
				
				//record starting position
				br[levelAt].start = i;
				levelAt++;
				maxLevel++;
			}
			
			//if we have a closing bracket
			if(arr[i] == ']' || arr[i] == ')' || arr[i] == '}') {
				
				levelAt--;
				//record ending position
				br[levelAt].end = i;
			}
		}
		
		String grabbed = "";
		
		//working from the innermost level, grab each level's strings
		for(i=maxLevel; i>=0; i--) {
			
			//lowest level
			if(i == maxLevel) {
				
				//check to make sure we didn't grab whitespace
				grabbed = input.substring(br[i].start + 1, br[i].end).trim();
				if(!grabbed.equals(""))
					output = output.concat(grabbed + " ");
			}
			
			//for all other levels, we have to grab around the next lowest level
			else {
				
				grabbed = input.substring(br[i].start + 1, br[i+1].start).trim();
				if(!grabbed.equals(""))
					output = output.concat(grabbed + " ");
				
				grabbed = input.substring(br[i+1].end + 1, br[i].end).trim();
				if(!grabbed.equals(""))
					output = output.concat(grabbed + " ");
			}
		}
		
		System.out.println(output);
	}
}
