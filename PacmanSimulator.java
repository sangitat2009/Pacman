import java.util.Arrays;


/* The application is a simulation of Pacman moving on in a grid, of dimensions 5 units x 5 units. */

public class PacmanSimulator {

public static final int SIZE = 5;
private int row, col;
private String direction; 
enum operations {PLACE,MOVE, LEFT ,RIGHT ,REPORT };

public PacmanSimulator() {

}

public static void main(String[] args) {
	//variable to store the PLACE position
	int validPlacePos = 0;
	boolean validPlace = false;
	//regular expression for PLACE command
	String placePattern = "\\d\\,\\d\\,(SOUTH|NORTH|EAST|WEST)";
	String[] input;
	
	
    PacmanSimulator pacman = new PacmanSimulator();
  
   
    //For each String in the input string array search for a valid PLACE command.	    
    for(int i=0 ; i< args.length ; i++)
    {        
        if(args[i].equals("PLACE"))
        {
        	validPlacePos = i;
        	if (((args[validPlacePos+1]).trim()).matches(placePattern))
            	validPlace= true;
        	break;
        }
    }
    
    //If valid PLACE is found process the commands for the pacman movement 
    if (validPlace)
    {
    	input = Arrays.copyOfRange(args,validPlacePos,args.length);
    	//process commands
    	pacman.process(input);
    }
}
//process commands PLACE, MOVE, LEFT, RIGHT, REPORT
private void process(String[] input)
{
	
	String[] placeInput;
	for(int i=0 ;i < input.length; i++)	
	{	
		switch (input[i].toUpperCase())
		{
			case "PLACE" :
				placeInput = input[i+1].split(",");			// split arguments for place command
				int rownum = Integer.parseInt(placeInput[0]);
				int colnum = Integer.parseInt(placeInput[1]);
				String face = placeInput[2];
				if (row > PacmanSimulator.SIZE || col > PacmanSimulator.SIZE || face == null )
					break;
				else
					place(rownum, colnum, face);		
				break;
			case "MOVE" :
				move();			
				break;
			case "LEFT" :
				left();			
				break;
			case "RIGHT" :
				right();		
				break;
			case "REPORT" :
				if(this.direction != null)
					System.out.println("OUTPUT : "+ this.row + "," + this.col + "," + this.direction); 
				break;
			default:
				break;
		}
	}
}
//PLACE will put the Pacman on the grid in positon X,Y and facing NORTH,SOUTH, EAST or WEST.
private void place(int row, int col, String direction)
{
	if (row >= PacmanSimulator.SIZE || col >= PacmanSimulator.SIZE )
		return;
	this.row = row;
	this.col = col;
	this.direction = direction;
	
}
//move Pacman one unit forward in the direction it is currently facing.
private void move()
{	
	if (this.direction != null)
	{
		switch (this.direction.toUpperCase())
		{
			case "EAST" :
				if (row++ < PacmanSimulator.SIZE)
					row = row++;			
				break;
			case "WEST" :
				if (row++ < PacmanSimulator.SIZE)
					row = row++;			
				break;
			case "NORTH" :
				if (col++ < PacmanSimulator.SIZE)
					col = col++;			
				break;
			case "SOUTH" :
				if (row++ < PacmanSimulator.SIZE)
					col = col++;			
				break;
			default:
				break;
		}
	}
}
//LEFT will rotate Pacman 90 degrees in the specified direction without changing the position of Pacman.
private void left()
{
	if (this.direction != null)
	{
		switch (this.direction.toUpperCase())
		{
			case "EAST" :
				direction = "NORTH"	;		
				break;
			case "WEST" :
				direction = "SOUTH"	;
				break;
			case "NORTH" :
				direction = "WEST"	;
				break;
			case "SOUTH" :
				direction = "EAST"	;
				break;
			default:
				break;
		}
	}
}

//RIGHT will rotate Pacman 90 degrees in the specified direction without changing the position of Pacman.
private void right()
{
	if (this.direction != null)
	{
		switch (this.direction.toUpperCase())
		{
			case "EAST" :
				direction = "SOUTH"	;		
				break;
			case "WEST" :
				direction = "NORTH"	;
				break;
			case "NORTH" :
				direction = "EAST"	;
				break;
			case "SOUTH" :
				direction = "NORTH"	;
				break;
			default:
				break;
		}
	}
}

}






