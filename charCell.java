public class charCell {
    private char    character       = ' ';
    private int     foregroundColor = ANSI.DEFAULT;
    private int     backgroundColor = ANSI.DEFAULT;
    private boolean modified        = true;
    
    public charCell () {
    }

    public charCell( char c, int fg, int bg ) {
	character = c;
	fg( fg );
	bg( bg );
    }

    public char character() {
	return character;
    }

    public void character( char c ) {
	if ( character != c ) {
	    character = c;
	    modified = true;
	}
    }

    public int fg() {
	return foregroundColor;
    }

    public void fg( int color ) {
	color = Math.abs( color ) % 10;
	if ( color != 8 ) {
	    foregroundColor = color;
	    modified = true;
	}
    }

    public int bg() {
	return backgroundColor;
    }

    public void bg( int color ) {
	color = Math.abs( color ) % 10;
	if ( color != 8 ) {
	    backgroundColor = color;
	    modified = true;
	}
    }

    public boolean modified() {
	return modified;
    }

    public String toString() {
	// Set Foreground Color
	ANSI.foregroundColor( foregroundColor );
	// Set Background Color
	ANSI.backgroundColor( backgroundColor );
	// Set Modified to false
	modified = false;
	// Print the Character: this is toString...we don't print, we
	// return a String
	return character + "";
    }

}