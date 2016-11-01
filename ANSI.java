public class ANSI {

    ////////////////////////////////////////////////////////////
    // Colors

    public final static int BLACK = 0;
    public final static int RED   = 1;
    public final static int GREEN = 2;
    public final static int YELLOW = 3;
    public final static int BLUE = 4;
    public final static int MAGENTA =5;
    public final static int CYAN = 6;
    public final static int WHITE = 7;
    public final static int DEFAULT = 9;
    public final static String ESC = "\033";

    // End Colors
    ////////////////////////////////////////////////////////////

    public static void gotoXY( int x, int y ) {
	System.out.print( ESC + "[" + y + ";" + x + "H" );
    }

    public static void cls() {
	System.out.print( ESC + "[2J" );
	gotoXY( 0, 0 );
    }

    public static void foregroundColor( int COLOR ) {
        COLOR = Math.abs( COLOR ) % 10;
	if ( COLOR != 8 )
	    System.out.print( ESC + "[" + ( 30 + COLOR ) + "m" );
    }

    public static void backgroundColor( int COLOR ) {
        COLOR = Math.abs( COLOR ) % 10;
	if ( COLOR != 8 )
	    System.out.print( ESC + "[" + ( 40 + COLOR ) + "m" );
    }


} 
