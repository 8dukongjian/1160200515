package p1;

import java.io.*;
import java.util.*;
public class MagicSquare 
{
     private static Scanner p;
     private static int n= 11;
    public static boolean isLegalMagicSquare(String filename)
     {
    	 try {
    		 Scanner in = new Scanner(new File(filename));
             String sc = null;
             int i=0,j=0,l,k;
             int b[][] = new int[200][200];
    	     while(in.hasNextLine())
    	     {
    		    j=0;
    		    sc = in.nextLine();
    		    OutputStream os=new FileOutputStream("temp.txt");   //temp.txt为临时文件
                PrintWriter out = new PrintWriter(os);
    		    out.write(sc);
    		    out.close();
    		    p = new Scanner(new File("temp.txt"));
    		    while(p.hasNext())
    		    {
    			   b[i][j] = p.nextInt();
    			   j++;  //j为计数元素，表示数组中实际数据的列数
    		    } 
    		     i++;    //i为计数元素，表示数组中实际数据的行数
    	     }
    	    in.close();
    	    if(i!=j)
    	    {
    		    return false;
    	    }
    	    int sum[] = new int[i+2];
            for(l=0;l<i;l++)
            {
        	   sum[l] = 0;
        	   for(k=0;k<i;k++)
        	   {
        		sum[l] = sum[l] + b[l][k];
        	   }
            }
            sum[i+1] = 0;
            sum[i] = 0;
            for(l=0,k=0;l<i&&k<i;l++,k++)
            {
        			 sum[i] = sum[i] + b[l][k];
            }
            for(l=0,k=i-1;l<i&&k>=0;l++,k--)
            {
        	 sum[i+1] = sum[i+1] + b[l][k];
            }
            for(l=0;l<i+1;l++)
            {
        	 if(sum[l]!=sum[l+1])
        		 return false;
            }
		    return true;
    	 }
    	 catch(InputMismatchException | IOException e) 
    	 {
    		 return false;
    	 } 
     }
    public static boolean generateMagicSquare(int n) {
    	try {
    	int magic[][] = new int[n][n];
    	int row = 0, col = n / 2, i, j, square = n * n;
    	for (i = 1; i <= square; i++) {
    	   magic[row][col] = i;
    	   if (i % n == 0)
    		   row++;
    	   else {
    	      if (row == 0)
    	    	  row = n - 1;
    	      else
    	    	  row--;
    	      if (col == (n - 1))
    	    	  col = 0;
    	      else
    	    	  col++;
    	        }
    	}
	    OutputStream os=new FileOutputStream("src\\p1\\txt\\6.txt");
        PrintWriter out = new PrintWriter(os);
        for (i = 0; i < n; i++) {
    	    for (j = 0; j < n; j++)
    	    	out.write(magic[i][j] + "\t");
    	    out.println();
    	}
        out.close();
    	return true;
    	}
    	catch(ArrayIndexOutOfBoundsException | NegativeArraySizeException | FileNotFoundException e)
    	{
    		return false;
    	}
    }
	 public static void main(String[] args)
     {
    		System.out.println("1.txt :"+isLegalMagicSquare("src\\p1\\txt\\1.txt"));
    		System.out.println("2.txt :"+isLegalMagicSquare("src\\p1\\txt\\2.txt"));
    		System.out.println("3.txt :"+isLegalMagicSquare("src\\p1\\txt\\3.txt"));
    		System.out.println("4.txt :"+isLegalMagicSquare("src\\p1\\txt\\4.txt"));
    		System.out.println("5.txt :"+isLegalMagicSquare("src\\p1\\txt\\5.txt"));
    		generateMagicSquare(n);
    		System.out.println("6.txt :"+isLegalMagicSquare("src\\p1\\txt\\6.txt"));

     }

}