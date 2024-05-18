package leetcode;

public class LargestBlackBorderedSquare {
    
    public static void main(String[] args) { 
        LargestBlackBorderedSquare test = new LargestBlackBorderedSquare();
        test.runTests();
    }
    
    private void runTests() { 
        var result = largestSquare(new char[][] {
            {'b','b','b'},
            {'b','b','w'},
            {'b','b','b'}, 
            {'w','b','b'}, 
            {'b','b','b'}});
        //var result = largestSquare(new char[][] {{'B','B','B'},{'B','w','B'},{'B','B','B'}} );
        //var result = largestSquare(new char[][] {{'b','b','w'},{'B','B','B'},{'B','B','B'},{'B','B','B'}} );        
        //var result = largestSquare(new char[][] {{'b','b'},{'w','w'}} );
        //var result = largestSquare(new char[][] {{'w','w'},{'w','b'}} );
        //var result = largestSquare(new char[][] {{'w','b'},{'b','b'}} );
        //var result = largestSquare(new char[][] {{'w','w','w','b'}} );
        /*var result = largestSquare(new char[][] {
            {'B','B','B','w'},
            {'B','B','B','b'},
            {'B','B','B','b'},
            {'b','w','b','b'}} );*/
       /* var result =  largestSquare(new char[][] {
        {'w','b','b','b'},
        {'B','B','B','B'},
        {'B','w','w','B'},
        {'B','B','B','B'},
        {'b','w','b','b'},
        {'b','b','w','b'}} );*/
        
        /*var result = largestSquare(new char[][] {
            {'w','b','b','b','b','w'},
            {'b','b','w','b','b','w'},
            {'b','b','w','b','w','b'},
            {'b','b','w','b','b','b'},
            {'b','b','w','b','b','b'},
            {'b','b','B','B','B','B'},
            {'b','w','B','B','B','B'},
            {'w','w','B','B','B','B'},
            {'b','b','B','B','B','B'}} ); */

        System.err.println(result);
        System.err.println(maxSizeSquare.row + " /" + maxSizeSquare.col);
    }
    
    //the highest square by area, and the bottom right {row,col} tuple that anchors it
    private SquareCell maxSizeSquare = new SquareCell(-1,-1, 0);
    
    public int largestSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
          return 0;
        }

        //track the rise and run for adjacent border nodes for each cell
        //in the matrix.
        //for example:
        //b b b b   ->  <1,1> <2,1> <3,1> <4,1> 
        //b c c b       <1,2>   0     0   <1,2>  
        //b c c b       <1,3>   0     0   <1,3>  
        //b b b b       <1,4> <2,1> <3,1> <4,4>
        //run  = (adjacent run val at col-1; 0 if none) + 1
        //rise = (adjacent run val at row-1; 0 if none) + 1        
        //we know this is a possible valid square when min(rise,run)>1 (cell 3,3).
        //we then evaluate the cells which "pivot" the rise/run, cells (0,3) and (3,0).
        //if(rise(3,0) >= run(3,3) and run(0,3) >= rise(3,3) we know the border is exact
        //and we've formed a square.
        //O(RxC) storage where R = number of rows and C = number of columns in matrix
        Cell[][] riseRunValues = new Cell[matrix.length][matrix[0].length];

        int rowLength = matrix.length;
        int colLength = matrix[0].length;

        // O(RxC) iteration through input matrix calculating the rise/run relative
        // to border neighbors.
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                int rise = 0, run=0;
                
                //border candidate.  
                if (matrix[row][col] == 'b' || matrix[row][col] =='B') {  //upper case for test readability                    
                    //initialize rise and run and attach adjacent node values:          
                    rise = (row>0) ? riseRunValues[row-1][col].rise + 1 : 1;                            
                    run =  (col>0) ? riseRunValues[row][col-1].run + 1 : 1;
                    
                    //a rise/run > 1 indicates this node may form the root of a square as it
                    //has connected nodes; this is represented as an "edge", 
                    //or the maximal possible width/height of the square 
                    int maxEdgeLength = Math.min(rise, run) + 1; //minimize code 
                                       
                    //check the pivot cell rise/run to see if this border is mirrored.  A pivot cell
                    //with a smaller rise/run indicates that the rise/run for this cell isn't mirrored
                    //due to a long edge which is broken in the middle.  In that case, check the
                    //rise run in pivot position -1 until we've found a satisfactory square.
                    while(
                       --maxEdgeLength > maxSizeSquare.edgeLength &&  //break if this area can't be largest.
                       maxEdgeLength > 1 &&
                       (riseRunValues[row-(maxEdgeLength-1)][col].run  < maxEdgeLength || 
                       riseRunValues[row][col-(maxEdgeLength-1)].rise < maxEdgeLength));
                        
                    //store new maximal square
                    if(maxSizeSquare.edgeLength < maxEdgeLength) {                       
                        maxSizeSquare.edgeLength = maxEdgeLength;
                        maxSizeSquare.col = col;
                        maxSizeSquare.row = row;
                    }
                }
                //track each cell rise/run for subsequent lookup
                riseRunValues[row][col] = new Cell(rise, run);                
            } // end column iteration            
        } // end row iteration

        //return total area
        return maxSizeSquare.area();
    }
    
    //represents a cell in the grid which holds the rise/run of adjacent neighbors
    class Cell {
        int rise;
        int run;
        
        public Cell(int rise, int run) {
            this.rise = rise;
            this.run = run;
        }
    }
    
    // represents the bottom right corner of a cell which has neighbors forming a square:
    // Coordinates of the square in clockwise order: 
    // {row,col}, {row, col-edgeLength}, {row-edgeLength,col+edgeLength}, {row+edgeLength,col}
    class SquareCell { 
        int row;
        int col;
        int edgeLength;        
        
        public SquareCell(int row, int col, int edgeLength) { 
            this.row = row;
            this.col  = col;
            this.edgeLength = edgeLength;
        }
        
        //the area of the maximum sized square.  May be used to break
        //the looping over a matrix grid if it's impossible to return a
        //square with greater area than this edgelength. 
        public int area() { 
            return (int)Math.round(Math.pow(edgeLength, 2));
        }
    }
}
