/*
Authors
Lucas Bigler @Dragonruler6008
Nick Savage @thesavagen
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cube {
  // Create the cubemap
  static String[][] cubemap = new String[9][12];
  // Storage for the cubemap
  static String[][] cubemapPrevious = new String[9][12];
  // Make this global because I can.
  static String[][][] temp = new String[6][3][3];
  static String oppcheck;

  public static void main(final String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // for later input
    boolean argsCheck = false;

    boolean proceed = true;
    int argsRunIndex = 0;
    if (args.length > 0) {
      argsCheck = true;
    }

    // Create the color map

    // Set colors to a color array
    // MAYBE CHANGE TO CHAR ARRAY LATER
    String[] colors = new String[] { "w", "r", "b", "o", "g", "y" };

    // Setting all colors properly on the rubics cube
    // I = center color of rubics cube
    // J = y axis of rubics cube
    // K = x axis of rubics cube
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          temp[i][j][k] = (colors[i]);
        }
      }
    }

    // setting colors to proper spaces on cubemap

    // Top 3 spaces of x axis
    for (int j = 0; j < 3; j++) {
      for (int k = 6; k < 9; k++) {
        cubemap[j][k] = (temp[0][k - 6][j]);
      }
    }

    // Middle 3 space of x axis
    for (int j = 3; j < 6; j++) {
      for (int k = 0; k < 3; k++) {
        cubemap[j][k] = temp[1][k][j - 3];
      }
      for (int k = 3; k < 6; k++) {
        cubemap[j][k] = temp[2][k - 3][j - 3];
      }
      for (int k = 6; k < 9; k++) {
        cubemap[j][k] = temp[3][k - 6][j - 3];
      }
      for (int k = 9; k < 12; k++) {
        cubemap[j][k] = temp[4][k - 9][j - 3];
      }
    }

    // Bottom 3 spaces of x axis
    for (int j = 6; j < 9; j++) {
      for (int k = 6; k < 9; k++) {
        cubemap[j][k] = (temp[5][k - 6][j - 6]);
      }
    }
    for (int j = 0; j < 9; j++) {
      for (int k = 0; k < 12; k++) {
        if (cubemap[j][k] == null)
          cubemap[j][k] = "_";
      }
    }
    for (int j = 0; j < 9; j++) {
      for (int k = 0; k < 12; k++) {
        cubemapPrevious[j][k] = cubemap[j][k];
      }
    }
    // for (int j = 0; j < 9; j++) {
    // for (int k = 0; k < 12; k++) {
    // System.out.printf(cubemapPrevious[j][k]);
    // }
    // System.out.println();
    // }

    // moving j=4 by 3 to the right
    // works for
    // for (int k=0;k<12;k++) {
    // if(k>=3) {
    // cubemap[4][k] = cubemapPrevious[4][k-3];
    // } else {
    // cubemap[4][k] = cubemapPrevious[4][k+9];
    // }
    // System.out.printf(cubemap[4][k]);
    // }

    // moving j=4 by 3 to the left
    // Works for U and D'
    // for (int k=0;k<12;k++) {
    // if(k>=9) {
    // cubemap[4][k] = cubemapPrevious[4][k-9];
    // } else {
    // cubemap[4][k] = cubemapPrevious[4][k+3];
    // }
    // System.out.printf(cubemap[4][k]);
    // }

    // moving K=6 by 3 to the top
    // for(int j=0; j<6;j++) {
    // cubemap[l][j] = cubemapPrevious[l][j+3];

    // }
    // for(int j=6; j<9;j++) {
    // cubemap[l-5][j-3] = cubemapPrevious[l][J];
    // }
    // for(int j=9; j<12;j++; {
    // cubemap[l][j] = cubemapPrevious[l-5][j-6];
    // }

    // Stolen
    while (proceed) {
      String input;
      if (!argsCheck) {
        // Instructs the user to input a command to move the cube
        System.out.println("Enter a command to move the cube!");
        displayCubeMap();
        input = reader.readLine();
      } else {
        if (argsRunIndex == args.length) {
          System.out.println("Reads bottom to top for solving\n");
          The_Way_The_Teacher_Wants_It_Displayed_Unoptimial();
          argsCheck = false;
          input = "q";
        } else {
          oppcheck = args[argsRunIndex];
          Opposite();
          input = args[argsRunIndex];
          argsRunIndex++;
        }
      }

      switch (input) {
        // Top of cube left
        case "U":
          left(3);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Bottom of Cube Right
        case "D":
          right(5);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Right side of cube up
        case "R":
          up(8);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Left side of cube down
        case "L":
          down(6);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Front face clockwise
        case "F":
          clockwise(2); // is Nick's 3
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Back face counterclockwise
        case "B":
          counterclockwise(3);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Top of cube right
        case "U'":
          right(3);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Bottom of cube left
        case "D'":
          left(5);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Right side of cube down
        case "R'":
          down(8);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Left side of cube up
        case "L'":
          up(6);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Front face counterclockwise
        case "F'":
          counterclockwise(2);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Back face of cube clockwise
        case "B'":
          clockwise(3);
          if (!argsCheck) {
            displayCubeMap();
          }
          break;
        // Stop solving / give up
        case "q":
          proceed = false;
          break;
        case "display":
          The_Way_The_Teacher_Wants_It_Displayed_Unoptimial();
          break;
        case "L+Ratio":
          System.out.println("Ratioed");
        case "Randomize":
          RandomizingCubeTest();
          displayCubeMap();
          break;
        default:
          System.out.println("You must enter one of the cube movement commands!");
      }
    }
  }

  // THIS IS CHECKING ALL OF THE LAYER COLORS IN A MORE DIFFICULT WAY TO READ IT.
  // Maybe useful later

  // Shows the cubemap to the user via cmd line.
  private static void displayCubeMap() {
    for (int j = 0; j < 9; j++) {
      for (int k = 0; k < 12; k++) {
        System.out.printf(cubemap[j][k]);
        cubemapPrevious[j][k] = cubemap[j][k];
      }
      System.out.println();
    }
  }

  // Does code for moving cube parts left
  // REDOING CODE AS A PROOF OF CONCEPT
  private static void left(int l) {
    // Top of Cube Left
    if (l == 3) {
      int k = 8;
      int f = 2;
      int g = 0;
      int u = 0;
      int r = 0;
      for (int j = 9; j < 12; j++) {
        cubemap[3][j - 3] = cubemapPrevious[3][j];
        cubemap[2][j - 3] = cubemapPrevious[f][8];
        f--;
      }

      for (int j = 6; j < 9; j++) {
        cubemap[3][j - 3] = cubemapPrevious[3][j];
        cubemap[u][6] = cubemapPrevious[2][j];
        u++;
      }

      for (int j = 3; j < 6; j++) {
        cubemap[3][j - 3] = cubemapPrevious[3][j];
        cubemap[0][k] = cubemapPrevious[r][6];
        r++;
        k--;
      }

      for (int j = 0; j < 3; j++) {
        cubemap[3][j + 9] = cubemapPrevious[3][j];
        cubemap[g][8] = cubemapPrevious[0][j + 6];
        g++;
      }
    }
    // Bottom of Cube Left
    else {
      int k = 8;
      int f = 8;
      int g = 8;
      for (int j = 9; j < 12; j++) {
        cubemap[5][j - 3] = cubemapPrevious[5][j];
        cubemap[6][j - 3] = cubemapPrevious[j - 3][8];
      }

      for (int j = 6; j < 9; j++) {
        cubemap[5][j - 3] = cubemapPrevious[5][j];
        cubemap[k][6] = cubemapPrevious[6][j];
        k--;
      }

      for (int j = 3; j < 6; j++) {
        cubemap[5][j - 3] = cubemapPrevious[5][j];
        cubemap[8][f] = cubemapPrevious[f][6];
        f--;
      }

      for (int j = 0; j < 3; j++) {
        cubemap[5][j + 9] = cubemapPrevious[5][j];
        cubemap[g][8] = cubemapPrevious[8][j + 6];
        g--;
      }
    }

    // for (int k = 0; k < 12; k++) {
    // if (k >= 9) {
    // cubemap[l][k] = cubemapPrevious[l][k - 9];
    // } else {
    // cubemap[l][k] = cubemapPrevious[l][k + 3];
    // }
    // }
  }

  // Does code for moving cube parts right //MAY NEED REDOING
  private static void right(int l) {
    // Bottom of Cube Right
    if (l == 5) {
      int k = 8;
      int f = 8;
      int g = 8;
      for (int j = 9; j < 12; j++) {
        cubemap[5][j] = cubemapPrevious[5][j - 3];
        cubemap[k][8] = cubemapPrevious[6][j - 3];
        k--;
      }

      for (int j = 6; j < 9; j++) {
        cubemap[5][j] = cubemapPrevious[5][j - 3];
        cubemap[8][j] = cubemapPrevious[f][8];
        f--;

      }

      for (int j = 3; j < 6; j++) {
        cubemap[5][j] = cubemapPrevious[5][j - 3];
        cubemap[j + 3][6] = cubemapPrevious[8][j + 3];
      }

      for (int j = 0; j < 3; j++) {
        cubemap[5][j] = cubemapPrevious[5][+9];
        cubemap[6][g] = cubemapPrevious[j + 6][6];
        g--;
      }
    }
    // Top of Cube Right and top face rotate with it.
    else {
      int f = 2;
      int g = 2;
      int u = 0;
      int r = 0;
      for (int j = 9; j < 12; j++) {
        cubemap[3][j] = cubemapPrevious[3][j - 3];
        cubemap[r][8] = cubemapPrevious[0][j - 3];
        r++;
      }

      for (int j = 6; j < 9; j++) {
        cubemap[3][j] = cubemapPrevious[3][j - 3];
        cubemap[2][j] = cubemapPrevious[f][8];
        f--;
      }

      for (int j = 3; j < 6; j++) {
        cubemap[3][j] = cubemapPrevious[3][j - 3];
        cubemap[u][6] = cubemapPrevious[2][j + 3];
        u++;
      }

      for (int j = 0; j < 3; j++) {
        cubemap[3][j] = cubemapPrevious[3][+9];
        cubemap[0][j + 6] = cubemapPrevious[g][6];
        g--;
      }
    }

  }

  // {
  // cubemap[l][k] = cubemapPrevious[l][k - 3];
  // } else {
  // cubemap[l][k] = cubemapPrevious[l][k + 9];
  // }

  // Does Code for moving cube up
  private static void up(int l) {
    if (l == 8) {
      // Right side of cube up and rotates green face with it.
      int k = 5;
      int h = 5;
      int m = 5;
      int y = 2;
      for (int j = 0; j < 3; j++) {
        cubemap[j][8] = cubemapPrevious[j + 3][8];
        cubemap[j + 3][11] = cubemapPrevious[3][j + 9];
      }
      for (int j = 3; j < 6; j++) {
        cubemap[j][0] = cubemapPrevious[y][8];
        cubemap[5][j + 6] = cubemapPrevious[h][11];
        y--;
        h--;
      }
      for (int j = 6; j < 9; j++) {
        cubemap[j][8] = cubemapPrevious[m][0];
        cubemap[j - 3][9] = cubemapPrevious[5][j + 3];
        m--;
      }
      for (int j = 9; j < 12; j++) {
        cubemap[j - 6][8] = cubemapPrevious[j - 3][8];
        cubemap[3][j] = cubemapPrevious[k][9];
        k--;
      }
    } else {
      // Left side of cube up and rotates blue face with it
      int k = 5;
      int h = 5;
      int m = 5;
      int y = 5;
      int u = 5;
      int fthis = 5;
      for (int j = 0; j < 3; j++) {
        cubemap[j][6] = cubemapPrevious[j + 3][6];
        cubemap[3][j + 3] = cubemapPrevious[j + 3][5];
      }
      for (int j = 3; j < 6; j++) {
        cubemap[fthis][2] = cubemapPrevious[j - 3][6];
        cubemap[h][3] = cubemapPrevious[3][j];
        fthis--;
        h--;
      }
      for (int j = 6; j < 9; j++) {
        cubemap[j][6] = cubemapPrevious[u][2];
        cubemap[5][y] = cubemapPrevious[k][3];
        u--;
        y--;
        k--;
      }
      for (int j = 9; j < 12; j++) {
        cubemap[j - 6][6] = cubemapPrevious[j - 3][6];
        cubemap[j - 6][5] = cubemapPrevious[5][m];
        m--;
      }
    }
  }

  // Does code for moving cube parts down
  private static void down(int l) {
    if (l == 6) {
      // Moves left side of front down
      int k = 8;
      int h = 5;
      for (int j = 0; j < 3; j++) {
        cubemap[j + 3][6] = cubemapPrevious[j][6];
      }
      for (int j = 3; j < 6; j++) {
        cubemap[j + 3][6] = cubemapPrevious[j][6];
      }
      for (int j = 6; j < 9; j++) {
        cubemap[j - 3][2] = cubemapPrevious[k][6];
        k--;
      }
      for (int j = 9; j < 12; j++) {
        cubemap[j - 9][6] = cubemapPrevious[h][2];
        h--;
      }

      // rotating blue
      for (int j = 5; j > 2; j--) {
        cubemap[j][5] = cubemapPrevious[3][j];
      }
      for (int j = 5; j > 2; j--) {
        cubemap[j][4] = cubemapPrevious[4][j];
      }
      for (int j = 5; j > 2; j--) {
        cubemap[j][3] = cubemapPrevious[5][j];
      }
    } else {
      // Moves Right side of cube down
      int k = 8;
      int h = 5;
      for (int j = 0; j < 3; j++) {
        cubemap[j + 3][8] = cubemapPrevious[j][8];
      }
      for (int j = 3; j < 6; j++) {
        cubemap[j + 3][8] = cubemapPrevious[j][8];
      }
      for (int j = 6; j < 9; j++) {
        cubemap[j - 3][0] = cubemapPrevious[k][8];
        k--;
      }
      for (int j = 9; j < 12; j++) {
        cubemap[j - 9][8] = cubemapPrevious[h][0];
        h--;
      }

      // Rotating Green face
      n = 3;
      for (int j = 11; j > 8; j--) {
        cubemap[3][j] = cubemapPrevious[n][9];
        n++;
      }
      n = 3;
      for (int j = 11; j > 8; j--) {
        cubemap[4][j] = cubemapPrevious[n][10];
        n++;
      }
      n = 3;
      for (int j = 11; j > 8; j--) {
        cubemap[5][j] = cubemapPrevious[n][11];
        n++;
      }

    }
  }

  // Does clockwise Function for the cube (front/back)
  private static void clockwise(int l) {

    if (l == 2) {
      int k = 5;
      int h = 5;
      // Front of cube clockwise
      for (int i = 9; i < 12; i++) {
        cubemap[i - 6][9] = cubemapPrevious[2][i - 3];
        cubemap[i - 6][8] = cubemapPrevious[3][i - 3];
      }
      for (int j = 6; j < 9; j++) {
        cubemap[6][j] = cubemapPrevious[h][9];
        cubemap[5][j] = cubemapPrevious[h][8];
        h -= 1;
      }
      for (int j = 3; j < 6; j++) {
        cubemap[j][5] = cubemapPrevious[6][j + 3];
        cubemap[j][6] = cubemapPrevious[5][j + 3];
      }
      for (int j = 0; j < 3; j++) {
        cubemap[2][j + 6] = cubemapPrevious[k][5];
        cubemap[3][j + 6] = cubemapPrevious[k][6];
        k -= 1;
      }

      // cubemap[l][j+6] = cubemapPrevious[j+3][5];
    }
    // Back face of cube clockwise
    else {
      int k = 5;
      int h = 5;
      int f = 2;
      int g = 2;
      int u = 0;
      int r = 0;
      for (int j = 9; j < 12; j++) {
        cubemap[j - 6][11] = cubemapPrevious[0][j - 3];
        cubemap[j - 6][0] = cubemapPrevious[3][f];
        f -= 1;
      }
      for (int j = 6; j < 9; j++) {
        cubemap[8][j] = cubemapPrevious[k][11];
        cubemap[5][u] = cubemapPrevious[j - 3][0];
        u += 1;
        k -= 1;
      }
      for (int j = 3; j < 6; j++) {
        cubemap[j][3] = cubemapPrevious[8][j + 3];
        cubemap[j][2] = cubemapPrevious[5][g];
        g--;
      }
      for (int j = 0; j < 3; j++) {
        cubemap[0][j + 6] = cubemapPrevious[h][3];
        cubemap[3][r] = cubemapPrevious[j + 3][2];
        r += 1;
        h -= 1;
      }
    }
  }

  static int n = 0;
  static int s = 0;

  // Does counterclockwise functions for the cube (front/back)
  private static void counterclockwise(int l) {
    if (l == 2) {

      // Front of cube counterclockwise
      n = 8;
      for (int i = 9; i < 12; i++) {

        cubemap[2][i - 3] = cubemapPrevious[i - 6][9];
        cubemap[i - 6][6] = cubemapPrevious[3][n];
        n--;
      }
      n = 8;
      s = 5;
      for (int j = 6; j < 9; j++) {
        cubemap[s][5] = cubemapPrevious[l][j];
        cubemap[j - 3][7] = cubemapPrevious[4][n];
        n--;
        s -= 1;
      }
      n = 8;
      for (int j = 3; j < 6; j++) {
        cubemap[6][j + 3] = cubemapPrevious[j][5];
        cubemap[5][j + 3] = cubemapPrevious[j][6];
        cubemap[j][8] = cubemapPrevious[5][n];
        n--;
      }
      s = 5;
      for (int j = 0; j < 3; j++) {
        cubemap[s][9] = cubemapPrevious[6][j + 6];
        cubemap[s][8] = cubemapPrevious[5][j + 6];
        s -= 1;
      }
    }
    // Back face of cube counterclockwise
    else {
      int k = 5;
      int h = 5;
      int f = 2;
      int g = 2;
      int u = 0;
      int r = 0;
      for (int j = 9; j < 12; j++) {
        cubemap[k][3] = cubemapPrevious[0][j - 3];
        cubemap[k][0] = cubemapPrevious[3][f];
        f -= 1;
        k -= 1;
      }
      for (int j = 6; j < 9; j++) {
        cubemap[8][j] = cubemapPrevious[j - 3][3];
        cubemap[j - 3][2] = cubemapPrevious[3][u];
        u += 1;
      }
      for (int j = 3; j < 6; j++) {
        cubemap[h][11] = cubemapPrevious[8][j + 3];
        cubemap[5][g] = cubemapPrevious[j][2];
        g -= 1;
        h -= 1;
      }
      for (int j = 0; j < 3; j++) {
        cubemap[0][j + 6] = cubemapPrevious[j + 3][11];
        cubemap[j + 3][0] = cubemapPrevious[5][r];
        r += 1;
      }
    }
  }

  private static void RandomizingCube() {
    int[][] ScrambleCube = new int[30][12];
    String[][] saveMoves = new String[26][12];
    int min = 0;
    int max = 11;

    for (int r = 0; r < 26; r++) {
      int a = (int) (Math.random() * (max - min + 1) + min);
      ScrambleCube[r][a] = 1;
      if (ScrambleCube[r][0] == 1) {
        left(3);
        saveMoves[r][0] = "U";
      }
      if (ScrambleCube[r][1] == 1) {
        right(5);
        saveMoves[r][0] = "D";
      }
      if (ScrambleCube[r][2] == 1) {
        up(8);
        saveMoves[r][0] = "R";
      }
      if (ScrambleCube[r][3] == 1) {
        down(6);
        saveMoves[r][0] = "L";
      }
      if (ScrambleCube[r][4] == 1) {
        clockwise(2);
        saveMoves[r][0] = "F";
      }
      if (ScrambleCube[r][5] == 1) {
        counterclockwise(3);
        saveMoves[r][0] = "B";
      }
      if (ScrambleCube[r][6] == 1) {
        right(3);
        saveMoves[r][0] = "U'";
      }
      if (ScrambleCube[r][7] == 1) {
        left(5);
        saveMoves[r][0] = "D'";
      }
      if (ScrambleCube[r][8] == 1) {
        down(8);
        saveMoves[r][0] = "R'";
      }
      if (ScrambleCube[r][9] == 1) {
        up(6);
        saveMoves[r][0] = "L'";
      }
      if (ScrambleCube[r][10] == 1) {
        counterclockwise(2);
        saveMoves[r][0] = "F'";
      }
      if (ScrambleCube[r][11] == 1) {
        clockwise(3);
        saveMoves[r][0] = "B'";

      }
      ScrambleCube[r][a] = 0;
    }
    for (int r = 0; r < 26; r++) {
      System.out.println(saveMoves[r][0]);
    }
  }

  private static void RandomizingCubeTest() {
    int[][] ScrambleCube = new int[30][12];
    String[][] saveMoves = new String[26][12];
    int min = 0;
    int max = 11;

    for (int r = 0; r < 5; r++) {
      int a = (int) (Math.random() * (max - min + 1) + min);
      ScrambleCube[r][a] = 1;
      if (ScrambleCube[r][0] == 1) {
        left(3);
        saveMoves[r][0] = "U";
      }
      if (ScrambleCube[r][1] == 1) {
        right(5);
        saveMoves[r][0] = "D";
      }
      if (ScrambleCube[r][2] == 1) {
        up(8);
        saveMoves[r][0] = "R";
      }
      if (ScrambleCube[r][3] == 1) {
        down(6);
        saveMoves[r][0] = "L";
      }
      if (ScrambleCube[r][4] == 1) {
        clockwise(2);
        saveMoves[r][0] = "F";
      }
      if (ScrambleCube[r][5] == 1) {
        counterclockwise(3);
        saveMoves[r][0] = "B";
      }
      if (ScrambleCube[r][6] == 1) {
        right(3);
        saveMoves[r][0] = "U'";
      }
      if (ScrambleCube[r][7] == 1) {
        left(5);
        saveMoves[r][0] = "D'";
      }
      if (ScrambleCube[r][8] == 1) {
        down(8);
        saveMoves[r][0] = "R'";
      }
      if (ScrambleCube[r][9] == 1) {
        up(6);
        saveMoves[r][0] = "L'";
      }
      if (ScrambleCube[r][10] == 1) {
        counterclockwise(2);
        saveMoves[r][0] = "F'";
      }
      if (ScrambleCube[r][11] == 1) {
        clockwise(3);
        saveMoves[r][0] = "B'";

      }
      displayCubeMap();
      System.out.println();
      ScrambleCube[r][a] = 0;
    }
    for (int r = 0; r < 5; r++) {
      System.out.println(saveMoves[r][0]);
    }

  }

  private static void The_Way_The_Teacher_Wants_It_Displayed_Unoptimial() {
    for (int j = 3; j < 6; j++) {
      for (int k = 0; k < 3; k++) {
        System.out.printf(cubemap[j][k]);
      }
      System.out.println();

    }
    for (int j = 3; j < 6; j++) {
      for (int k = 3; k < 6; k++) {
        System.out.printf(cubemap[j][k]);
      }
      System.out.println();

    }
    for (int j = 3; j < 6; j++) {
      for (int k = 6; k < 9; k++) {
        System.out.printf(cubemap[j][k]);
      }
      System.out.println();

    }
    for (int j = 3; j < 6; j++) {
      for (int k = 9; k < 12; k++) {
        System.out.printf(cubemap[j][k]);
      }
      System.out.println();

    }
    for (int j = 6; j < 9; j++) {
      for (int k = 6; k < 9; k++) {
        System.out.printf(cubemap[j][k]);
      }
      System.out.println();

    }
    for (int j = 0; j < 3; j++) {
      for (int k = 6; k < 9; k++) {
        System.out.printf(cubemap[j][k]);
      }
      System.out.println();

    }
  }

  // Displays top as last and bottom is first
  private static void Opposite() {
    if (oppcheck.equals("U")) {
      System.out.println("U'");
    } else if (oppcheck.equals("D")) {
      System.out.println("D'");
    } else if (oppcheck.equals("R")) {
      System.out.println("R'");
    } else if (oppcheck.equals("L")) {
      System.out.println("L'");
    } else if (oppcheck.equals("F")) {
      System.out.println("F'");
    } else if (oppcheck.equals("B")) {
      System.out.println("B'");
    } else if (oppcheck.equals("U'")) {
      System.out.println("U");
    } else if (oppcheck.equals("D'")) {
      System.out.println("D");
    } else if (oppcheck.equals("R'")) {
      System.out.println("R");
    } else if (oppcheck.equals("L'")) {
      System.out.println("L");
    } else if (oppcheck.equals("F'")) {
      System.out.println("F");
    } else if (oppcheck.equals("B'")) {
      System.out.println("B");
    }
  }
}
