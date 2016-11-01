// Needs
// - initialize
// - "plot( x,y,c,f,b)"
// - "plot( charCell C )" for robustness
// - erase the canvas
// - print the canvas

public class Canvas {

    private charCell[][]  Grid;

    public Canvas( int width, int height ) {
	Grid = new charCell[ width ][ height ];
	erase( '.', ANSI.GREEN, ANSI.MAGENTA );
    }

    public int getWidth() {
	return Grid.length;
    }

    public int getHeight() {
	return Grid[0].length;
    }

    public void erase( char C, int FG, int BG ) {
	for ( int i = 0; i < getWidth(); i++ )
	    for ( int j = 0 ; j < getHeight(); j++ ) 
		Grid[ i ][ j ] = new charCell( C, FG, BG );
    }
    
    public void clear( char C, int FG, int BG ) {
	erase( C, FG, BG );
    }

    public void plot( int x, int y, char C, int FG, int BG ) {
	try {
	    Grid[ x ][ y ] = new charCell( C, FG, BG );
	} catch ( ArrayIndexOutOfBoundsException e ) {
	    throw new ArrayIndexOutOfBoundsException( "." );
	}
    }
    
    public void output() {
	for ( int i = 0; i < getWidth(); i++ )
	    for ( int j = 0 ; j < getHeight(); j++ ) {
		ANSI.gotoXY( i, j );
		System.out.print( Grid[ i ][ j ] );
	    }
    }

}