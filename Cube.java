//Authors
//Lucas Bigler
//Nick Savage

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cube {
      //Create the cubemap
      static String[][] cubemap = new String[9][12];
      //Storage for the cubemap
      static String[][] cubemapPrevious = new String[9][12];
	public static void main(final String[] args)
  throws IOException
  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // for later input
    boolean argsCheck = false;

    boolean proceed = true;
    int argsRunIndex = 0;
    if(args.length > 0){
            argsCheck = true;
        }
    //Create the color map
    String[][][] temp = new String[6][3][3];
    //Set colors to a color array
    String[] colors = new String[] {"w","r","b","o","g","y"};

    
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
        for(int k=6;k<9;k++) {
          cubemap[j][k] = (temp[0][k-6][j]);
        }
      }

        //middle 3 space of x axis
      for(int j=3;j<6;j++){
        for(int k=0;k<3;k++){
          cubemap[j][k] = temp[1][k][j-3];
        }
        for(int k=3;k<6;k++){
          cubemap[j][k] = temp[2][k-3][j-3];
        }
        for(int k=6;k<9;k++){
          cubemap[j][k] = temp[3][k-6][j-3];
        }
       for(int k=9;k<12;k++){
          cubemap[j][k] = temp[4][k-9][j-3];
        }
      }

        //bottom 3 spaces of x axis
      for(int j=6;j<9;j++){
        for(int k=6;k<9;k++) {
          cubemap[j][k] = (temp[5][k-6][j-6]);
        }
      }
      for(int j=0; j<9; j++){
                for(int k=0; k<12; k++){
                    if(cubemap[j][k] == null)
                       cubemap[j][k] = "_";
      }
      }
    for(int j=0;j<9;j++) {
      for(int k=0;k<12;k++) {
        cubemapPrevious[j][k] = cubemap[j][k];
      }
    }
    for(int j=0;j<9;j++) {
      for(int k=0;k<12;k++) {
        System.out.printf(cubemapPrevious[j][k]);
      }
      System.out.println();
    }

    //moving j=4 by 3 to the right
    //works for 
    // for (int k=0;k<12;k++) {
    //   if(k>=3) {
    //     cubemap[4][k] = cubemapPrevious[4][k-3];
    //   } else {
    //     cubemap[4][k] = cubemapPrevious[4][k+9];
    //   }
    //   System.out.printf(cubemap[4][k]);
    // }

    //moving j=4 by 3 to the left
    // Works for U and D'
    // for (int k=0;k<12;k++) {
    //   if(k>=9) {
    //     cubemap[4][k] = cubemapPrevious[4][k-9];
    //   } else {
    //     cubemap[4][k] = cubemapPrevious[4][k+3];
    //   }
    //   System.out.printf(cubemap[4][k]);
    // }




      












            
            
            

      //stolen
        while(proceed){   
          String input;

             if(!argsCheck){
                input = reader.readLine();
            }else{
                if(argsRunIndex == args.length){
                    argsCheck=false;
                    input = "e";
                }else{
                    input = args[argsRunIndex];
                    argsRunIndex++;
                }
            }
            
            switch(input){
              //Top of cube left
                case "U":
                    left(3);
                    displayCubeMap();
                    break;
              //Top Bottom of Cube Right
                case "D":
                    right(5);
                    displayCubeMap();
                    break;
              //Right side of cube up
                case "R":
                    break;
              //Left side of cube down
                case "L":
                    break;
              //Front face clockwise
                case "F":
                    break;
              //Back face counterclockwise
                case "B":
                    break;
              //Top of cube right
                case "U'":
                    right(3);
                    displayCubeMap();
                    break;
              //Bottom of cube left
                case "D'":
                    left(5);
                    displayCubeMap();
                    break;
              //Right side of cube down
                case "R'":
                    break;
              //Left side of cube up
                case "L'":
                    break;
              //Front face counterclockwise
                case "F'":
                    break;
              //Back face of cube clockwise
                case "B'":
                    break;
              //Stop solving / give up
                case "q":
                    proceed = false;
                    System.out.println("QUITTER!");
                    break;
                default:
                    System.out.println("You must enter one of the commands!");
            }
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
  private static void displayCubeMap() {
    for(int j=0;j<9;j++) {
      for(int k=0;k<12;k++){
        System.out.printf(cubemap[j][k]);
        cubemapPrevious[j][k] = cubemap[j][k];
      }
      System.out.println();
    }
  }
  private static void left(int l){
    for (int k=0;k<12;k++) {
      if(k>=9) {
        cubemap[l][k] = cubemapPrevious[l][k-9];
      } else {
        cubemap[l][k] = cubemapPrevious[l][k+3];
      }
    }
  }
  private static void right(int l){
    for (int k=0;k<12;k++) {
      if(k>=3) {
        cubemap[l][k] = cubemapPrevious[l][k-3];
      } else {
        cubemap[l][k] = cubemapPrevious[l][k+9];
      }

    }
  }
}