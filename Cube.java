import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cube {
	public static void main(final String[] args)
  throws IOException
  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // for later input

    String[][][] temp = new String[6][3][3];
    String[] colors = new String[] {"w","r","b","o","g","y"};
    String[][] cubemap = new String[9][15];
    String[][] cubemapPrevious = new String[9][15];
    
    //Setting all colors properly on the rubics cube
    // I = center color of rubics cube
    // J = y axis of rubics cube
    // K = x axis of rubics cube
    for(int i=0; i<6; i++) {
      for( int j=0; j<3; j++){
        for(int k=0;k<3;k++){
          temp[i][j][k] = (colors[i]);
          }
        }
      }
      
      //setting colors to proper spaces on cubemap

      //top 3 spaces of x axis
      for(int j=0;j<3;j++){
        for(int k=8;k<11;k++) {
          cubemap[j][k] = (temp[0][k-8][j]);
        }
      }

        //middle 3 space of x axis
      for(int j=3;j<6;j++){
        for(int k=0;k<3;k++){
          cubemap[j][k] = temp[1][k][j-3];
        }
        for(int k=4;k<7;k++){
          cubemap[j][k] = temp[2][k-4][j-3];
        }
        for(int k=8;k<11;k++){
          cubemap[j][k] = temp[3][k-8][j-3];
        }
       for(int k=12;k<15;k++){
          cubemap[j][k] = temp[4][k-12][j-3];
        }
      }

        //bottom 3 spaces of x axis
      for(int j=6;j<9;j++){
        for(int k=8;k<11;k++) {
          cubemap[j][k] = (temp[5][k-8][j-6]);
        }
      }
      for(int j=0; j<9; j++){
                for(int k=0; k<15; k++){
                    if(cubemap[j][k] == null)
                        System.out.printf("_");
                    else
                        System.out.printf(cubemap[j][k]);
                }
                System.out.println();
      }
      

      












            
            
            

      //THIS IS CHECKING ALL OF THE LAYER COLORS IN A MORE DIFFICULT WAY TO READ IT. Maybe useful later
      // for(int i=0; i<6; i++) {
      // for( int j=0; j<3; j++){
      //   for(int k=0;k<3;k++){
      //     System.out.printf(temp[i][j][k]);
      //     }
      //     System.out.println();
      //   }
      // }
  }
}