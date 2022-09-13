import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cube {
	public static void main(final String[] args)
  throws IOException
  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String[][][] temp = new String[6][3][3];
    String[] colors = new String[] {"w","r","b","o","g","y"};
    String[][] cubemap = new String[12][9];
    
    
    
    




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
      for(int j=0;j<3;j++){
        
      }

      //THIS IS CHECKING ALL OF THE LAYER COLORS IN A MORE DIFFICULT WAY TO READ IT
      // for(int i=0; i<6; i++) {
      // for( int j=0; j<3; j++){
      //   for(int k=0;k<3;k++){
      //     System.out.printf(temp[i][j][k]);
      //     }
      //     System.out.println();
      //   }
      // }


    //displaying rubics cube map in a human readable fashion
 
    //setting the first set of underscores.
    for(int i=0; i<3; i++){
      for(int j=0; j<9; j++) {
        cubemap[i][j] = "_";
      }
    }

    //Top layer of rubics cube map
      for(int i=0; i<3; i++){
      for(int j=0; j<8; j++) {
        System.out.printf(cubemap[i][j]);
      }
      for(int k=0 ;k<3;k++) {
        System.out.printf(temp[0][i][i]);
      }
      for(int l=0;l<4;l++){
        System.out.printf("_");
      }
      System.out.println();
    }

    //Middle layer of rubics cube map
    for(int k=0;k<3;k++) {
      for(int j=0; j<3; j++) {
        System.out.printf(temp[1][j][k]);
      }
      System.out.printf("_");
      for(int j=0;j<3;j++) {
         System.out.printf(temp[2][j][k]);
      }
      System.out.printf("_");
      for(int j=0;j<3;j++) {
         System.out.printf(temp[3][j][k]);
      }
      System.out.printf("_");
      for(int j=0;j<3;j++) {
         System.out.printf(temp[4][j][k]);
      }
      System.out.println();
    }
  //Bottom layer of rubics cube map
    for(int i=0; i<3; i++){
      for(int j=0; j<8; j++) {
        System.out.printf(cubemap[i][j]);
      }
      for(int k=0 ;k<3;k++) {
        System.out.printf(temp[5][i][i]);
      }
      for(int l=0;l<4;l++){
        System.out.printf("_");
      }
      System.out.println();
    }
}
}