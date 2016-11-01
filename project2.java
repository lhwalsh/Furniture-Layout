// Walsh, Luke {lhwalsh}
// CS 141 01
// Project #2 : Furniture Layout
  
/*	Takes a command file' file name as an argument. This command file 
	includes commands to draw furniture pieces on a grid. The result of the 
	program is to print the resulting grid (with colors) to reveal a possible 
	layout. 
*/

import java.util.Scanner;
import java.io.*;

public class project2 {
	public static void main( String args[] ) {
		try {
			File	test   = new File(args[0]);
			Scanner inFile = new Scanner(test);
			doWork( inFile );
			inFile.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	public static void doWork( Scanner inFile ) {
		Canvas myCanvas = null;
		char ESC = (char)27;
		String currentPosition = inFile.next();
		while (inFile.hasNextLine()) {
			int height = 0;
			int width = 0;
			currentPosition = currentPosition.toLowerCase();
			if (currentPosition.charAt(0) == '/') {
				inFile.nextLine();
			} else { 
				if (currentPosition.charAt(0) == 'd') {
					width = inFile.nextInt();
					height = inFile.nextInt();
					myCanvas = new Canvas(width, height);
				} else {
					 if (currentPosition.charAt(0) == 'b') {
						char c = inFile.next().charAt(0);
						myCanvas.erase(c, color2Int(inFile.next()), color2Int(inFile.next()));
					} else {
						int objectXPosition = inFile.nextInt();
						int objectYPosition = inFile.nextInt();
						String commands     = inFile.next();
						try {
							File shape 	    = new File(currentPosition + ".cmd");
							Scanner sc 	    = new Scanner(shape);
							furniture temp      = new furniture(objectXPosition, objectYPosition, commands, sc);
							temp.executeCommands();
							char[][][] transfer = temp.positionArray();
							System.out.println(transfer[0][0][0]);
							arrayToCanvas(transfer, myCanvas, objectXPosition, objectYPosition);
						} catch (FileNotFoundException e) {
						}
					}
				}
			}	
			currentPosition = inFile.next();
		}
		System.out.println(ESC + "[2J");
		myCanvas.output();
	}

	public static void arrayToCanvas(char[][][] transfer, Canvas myCanvas, int x, int y) {
		for (int i = x; i < transfer.length; i++) {
			for (int q = y; q < transfer[0].length; q++) {
				myCanvas.plot(i, q, transfer[i][q][0], (int)transfer[i][q][1], (int)transfer[i][q][2]);
			}
		}
	}


	public static int color2Int( String S ) {
        	if ( S.toLowerCase().equals( "black" ) )
        	    return ANSI.BLACK;
        	if ( S.toLowerCase().equals( "red" ) )
        	    return ANSI.RED;
        	if ( S.toLowerCase().equals( "green" ) )
        	    return ANSI.GREEN;
        	if ( S.toLowerCase().equals( "yellow" ) )
        	    return ANSI.YELLOW;
        	if ( S.toLowerCase().equals( "blue" ) )
        	    return ANSI.BLUE;
        	if ( S.toLowerCase().equals( "magenta" ) )
        	    return ANSI.MAGENTA;
        	if ( S.toLowerCase().equals( "cyan" ) )
        	    return ANSI.CYAN;
        	if ( S.toLowerCase().equals( "white" ) )
        	    return ANSI.WHITE;
        	return ANSI.DEFAULT;
	}


}
