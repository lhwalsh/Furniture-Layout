//	class that creates an object of furniture and orients and positions the piece of furniture

import java.util.Scanner;
import java.io.*;

public class furniture {

	private int X;
	private int Y;
	private char C;
	private int FG;
	private int BG;
	private String commands;
	private char[][][] furnitureArray;

	public furniture(int tempX, int tempY, String tempCommands, Scanner sc) {
		X 	 = tempX;
		Y 	 = tempY;
		commands = tempCommands;
		furnitureArray = constructFurnitureArray(sc);
		fillArray(sc);
	}

	public char[][][] constructFurnitureArray(Scanner sc) {
		char[][][] answer;
		int height = 0;
		int width  = 0;
		if (sc.next().charAt(0) == 'd') {
			height = sc.nextInt();
			width  = sc.nextInt();
		}
		answer = new char[height][width][3];
		if (sc.next().charAt(0) == 'b') {
			C  = sc.next().charAt(0);
			FG = color2Int(sc.next());
			BG = color2Int(sc.next());
		}
		fillGrid(answer, C, FG, BG);
		return answer;
	}

	public void fillGrid(char[][][] answer, char C, int FG, int BG) {
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[i].length; j++) {
				answer[i][j][0] = C;
				answer[i][j][1] = (char)FG;
				answer[i][j][2] = (char)BG;
			}
		}
	}

	public void executeCommands() {
		commands = commands.toLowerCase();
		for (int i = 0; i < commands.length(); i++) {
                        if (commands.charAt(i) == 118) {
                                flipVertically();
                        } else if (commands.charAt(i) == 104) {
                                flipHorizontally();
                        } else if (commands.charAt(i) == 114) {
                                rotate();
                        } else if (commands.charAt(i) == 'n') {
				
			}
                }

	}
	
	public void flipVertically() {
                char tempY;
                int endY = (furnitureArray[0].length - 1);
               	for (int a = 0; a < furnitureArray.length/2; a++) {
               	        for (int y = 0; y < furnitureArray[a].length; y++) {
				for (int i = 0; i < 3; i++) {
               	                	tempY           	   = furnitureArray[y][a][i];
                       	        	furnitureArray[y][a][i]    = furnitureArray[y][endY][i];
                       	        	furnitureArray[y][endY][i] = tempY;
				}
                       	}
                       	endY -= 1;
                }	

	}
	
	public void flipHorizontally() {
                int endX = furnitureArray.length - 1;
                char tempX;
                for (int x = 0; x < furnitureArray.length/2; x++) {
                        for (int y = 0; y < furnitureArray[x].length; y++) {
				for (int i = 0; i < 3; i++) {
                                	tempX                  	   = furnitureArray[x][y][i];
                                	furnitureArray[x][y][i]    = furnitureArray[endX][y][i];
                                	furnitureArray[endX][y][i] = tempX;
				}
                        }
                        endX -= 1;
                }
	}

	public void rotate() {
                int endY     = furnitureArray[0].length - 1;
                int endY1    = furnitureArray[0].length - 2;
                int endX     = furnitureArray.length - 1;
                int endX1    = furnitureArray.length - 2;
                int x        = 0;
                int y        = 0;
                int x1       = 1;
                int y1       = 1;
                int counter  = 0;
                int counter2 = 0;
                char temp;
		for (int q = 0; q < 3; q++) {
                	for (int i = 0; i < furnitureArray.length/2; i++) {
                	        for (int w = 0; w < ((furnitureArray[0].length-1) - counter2); w++) {
                	                temp          		       = furnitureArray[x][y1][q];
                	                furnitureArray[x][y1][q]       = furnitureArray[x1][endY][q];
                	                furnitureArray[x1][endY][q]    = furnitureArray[endX][endY1][q];
                	                furnitureArray[endX][endY1][q] = furnitureArray[endX1][y][q];
                	                furnitureArray[endX1][y][q]    = temp;
	
	                                endX1 -= 1;
	                                endY1 -= 1;
	                                x1    += 1;
	                                y1    += 1;
	                        }
	                        counter2 += 2;
	                        counter  += 1;
        	                endY     -= 1;
	                        endX     -= 1;
	                        y        += 1;
	                        x        += 1;
	                        endX1     = (furnitureArray.length - 2) - counter;
	                        endY1     = (furnitureArray[i].length - 2) - counter;
	                        x1        = 1 + counter;
	                        y1        = 1 + counter;
	                }
		}

	}

	public char[][][] positionArray() {
		char[][][] answer = new char[furnitureArray.length + X][furnitureArray[0].length + Y][3];
		for (int i = 0; i < furnitureArray.length; i++) {
			for (int q = 0; q < furnitureArray[0].length; q++) {
				for (int w = 0; w < 3; w++) {
					answer[i + X][q + Y][w] = furnitureArray[i][q][w];
				}
			}
		}
		return answer;
	}

	public void fillArray(Scanner sc) {
                if (sc.next() == "p") {
                        plotPoint(sc.nextInt(), sc.nextInt(), sc.next().charAt(0), color2Int(sc.next()), color2Int(sc.next()));
                }
                if (sc.next() == "l") {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			char c = sc.next().charAt(0);
			int foregroundColor = color2Int(sc.next());
			int backgroundColor = color2Int(sc.next());
                        plotLine(x1, y1, x2, y2, c, foregroundColor, backgroundColor);
                }
		if (sc.next() == "c") {
			plotCircle( sc.nextInt(), sc.nextInt(), sc.nextFloat(), sc.nextFloat(), sc. nextFloat(), sc.next().charAt(0), 
					color2Int(sc.next()), color2Int(sc.next()));
		}

	}

	public void plotLine( int x1, int y1, int x2, int y2, char c, int FG, int BG) {
       		double m;
        	double b;
        	int temp;
        	int x, y;
	
	        if ( x1 == x2 ) { // Vertical line
	        	if ( y1 > y2 ) {
	                	temp = y1;
	                	y1 = y2;
	                	y2 = temp;
	            	}
	            	for ( int i = y1; i <= y2; i++ ) {
				plotPoint(x1, i, c, FG, BG);
			}
	        } else if ( y1 == y2 ) { // Horizontal line
	        	if ( x1 > x2 ) {
	                	temp = x1;
	                	x1 = x2;
	                	x2 = temp;
	            	}
	            	for ( int i = x1; i <= x2; i++ ) {
				plotPoint(i, y1, c, FG, BG);
			}
	        } else {             // Diagonal Line
        		m = ( y2 - y1 ) * 1.0 / ( x2 - x1 ); // * 1.0 to force
        	                                             // floating point
        	                                             // math
        	    // y = mx + b, so
        	    // b = y - mx  ....either point will solve for b
        	    b = y1 - m * x1;  // m is double, so all the math will now
        	                      // be floating point math...want that
        	                      // precision!
	
	        	if ( Math.abs( m ) <= 1.0 ) { // "flatter" line, loop on
	                                         // X's
	                	if ( x2 < x1 ) { // swap points to start on left
	                		temp = x1;
		        	        x1 = x2;
       	        			x2 = temp;
       	        			temp = y1;
       	        			y1 = y2;
                			y2 = temp;
                		}
                		for ( x = x1; x <= x2; x++ ) {
                			y = (int)( m * x + b );
					plotPoint(x, y, c, FG, BG);
                		}
          		} else { // "Steeper" line, loop on Y's
                	 // y = mx + b, so
                	 // x = ( y - b ) / m
                		if ( y2 < y1 ) { // swap points to start on at lower Y
                			temp = x1;   // value
                			x1 = x2;
                    			x2 = temp;
                    			temp = y1;
                    			y1 = y2;
                    			y2 = temp;
                		}
                		for ( y = y1; y <= y2; y++ ) {
                    			x = (int)( ( y - b ) / m );
					plotPoint(x, y, c, FG, BG);
                		}
			}
            	}
        }

	public void plotPoint(int a, int b, char c, int FG, int BG) {
		furnitureArray[a][b][0] = c;
		furnitureArray[a][b][1] = (char)FG;
		furnitureArray[a][b][2] = (char)BG;
	}

	public  void plotCircle( int Cx, int Cy, float radius, float theta1, float theta2, char c, int FG, int BG ) {
        	float start = theta1;
        	float stop  = theta2;
        	float step  = ( stop - start ) / 40;
        	float angle = start;
        	int    x     = 0;
        	int    y     = 0;

        	while ( angle < stop ) {
            		x = (int)( radius * Math.cos( angle ) );
            		y = (int)( radius * Math.sin( angle ) );
            		plotPoint( x + Cx, y + Cy, c, FG, BG );
            		plotPoint( y + Cx, x + Cy, c, FG, BG );
            		plotPoint( -x + Cx, y + Cy, c, FG, BG );
            		plotPoint( -y + Cx, x + Cy, c, FG, BG );
            		plotPoint( -x + Cx, -y + Cy, c, FG, BG );
            		plotPoint( -y + Cx, -x + Cy, c, FG, BG );
            		plotPoint( x + Cx, -y + Cy, c, FG, BG );
            		plotPoint( y + Cx, -x + Cy, c, FG, BG );
            		angle += step;
        	}
	}

        public int color2Int( String S ) {
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
