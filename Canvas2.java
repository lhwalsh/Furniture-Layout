// Needs
// - initialize
// - "plot( x,y,c,f,b)"
// - "plot( charCell C )" for robustness
// - erase the canvas
// - print the canvas

public class Canvas {

    private charCell[][] Grid;

    public Canvas( int height, int width, char c, int FG, int BG ) {
	Grid = new charCell[ height ][ width ];
	erase( c, FG, BG );
    }

    public int getHeight() {
	return Grid.length;
    }

    public int getWidth() {
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
