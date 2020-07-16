/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author jaiconakpil
 */
import java.util.*;
import java.io.*;
public class Project {

    /**
     * @param args the command line arguments
     */


    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Scanner keyboard = new Scanner(System.in);
        String fileInput;
        String timeComplexity1;
        String timeComplexity2;
        String[] operands = {"+", "-", "*", "/"};
        List runTimes = new ArrayList();

        // Prompting User for file name
        // System.out.println("Enter File Name");
        // String filename = keyboard.nextLine();

        BufferedReader reader;
            System.out.print("Enter file path");
            fileInput = keyboard.nextLine();
            System.out.println(fileInput);
        File dir = new File(fileInput);
        ///Users/jaiconakpil/Documents/COSC 336/COSCPROJECT
  File[] directoryListing = dir.listFiles();
  if (directoryListing != null) {
    for(int n=1;n<directoryListing.length;n++){
        if((directoryListing[n].isFile())&&(!directoryListing[n].getName().contains("BigO_"))&&(directoryListing[n].getAbsolutePath().contains(".txt"))){
        try {
            ///Users/jaiconakpil/Documents/COSC 336/COSCPROJECT
            File file = new File(directoryListing[n].getPath());
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String firstAnswer=null;
            String secondAnswer=null;
            int counter=1;
                    
            while (line != null) {
                //System.out.println(line);
                if ((line.contains("for(")||(line.contains("for (")))) {
                    line = line.replaceAll("\\s+", "");// Removes Spaces
                    if ((line.substring(0, 3).equals("for"))) {
                        String s = line;
                        
                        // Counting the for loops
                        if(counter==1){
                          firstAnswer= findAnswer(line); 
                          System.out.println("Final answer " + firstAnswer);
                        }
                        else if(counter==2){
                          secondAnswer= findAnswer(line);
                          System.out.println("Final answer " + secondAnswer);
                        }
                        
                        counter++;
                    }
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
            String finalAnswer=Combine(firstAnswer,secondAnswer);
            System.out.println("FINAL " + finalAnswer);
            System.out.println(fileInput+"/BigO_"+directoryListing[n].getName());
            PrintWriter(finalAnswer,fileInput+"/BigO_"+directoryListing[n].getName());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    }    
  }
    

    }// End of the main method

    public static String Operators(String third, String middle)
    {
        if (third.contains(("*=")))
        {
            third = "*=";
        }
        else if (third.contains("+="))
        {
            third = "+=";
        }
        else if (third.contains("-="))
        {
            third = "-=";
        }
        else if (third.contains("/="))
        {
            third = "/=";
        }
        else if (third.contains("++"))
        {
            third = "++";
        }
        else if (third.contains("--"))
        {
            third = "--";
        }
        switch (third)
        {
            case "++":
                return middle;
            case "--":
                return middle;
            case "*=":
                return "log (" + middle + ")";
            case "+=":
                return middle;
            case "-=":
                return middle;
            case "/=":
                return "log (" + middle + ")";
        }
        return null;
    }
    public static String Middle (String middle, String second)
    {
        System.out.println("MIDDLEE:  "+ middle);
        System.out.println("SECONDD:  "+ second);
        
        if (second.contains("*"))
        {
            return "Sqrt(" + middle + ")";
        }
        else if (second.contains("/"))
        {
            return middle + "^2";
        }
        return middle;
    }
    // Combines both for loops into O(n)
    public static String Combine (String firstCalc, String secondCalc)
    {
        if (secondCalc != null)
        {
            return "O (" + firstCalc + " * " + secondCalc + ")";
        }
        else
            return "O (" + firstCalc +  ")";
    }
    
    public static String findAnswer (String line)
    {
        String s = line;
                        s = s.substring(s.indexOf("(") + 1);
                        s = s.substring(0, s.indexOf(")"));


                        String first = s.substring(0,1);
                        System.out.println("First: " + first);
                        //String iEquals = first.substring(first.indexOf("=") + 1);
                        //System.out.println("I = " +iEquals);


                        String second = s.substring(s.indexOf(";")+1, s.lastIndexOf(";"));
                        
// Finds i*i
//                        if (middle.contains("=")){
//                            middle = second.substring(second.indexOf("<")+2);
//                        }
                        String middle="";
                        second = second.replaceAll("\\s+", ""); // Removes spaces from string
                        if(second.contains("=")){
                        middle = second.substring(second.indexOf("=") + 1 );
                        second = second.substring(0, second.lastIndexOf("=")-1);
                        } 
                        else if(second.contains("<"))
                        {
                        middle = second.substring(second.indexOf("<") + 1 );
                        second = second.substring(0, second.lastIndexOf("<"));
                        }
                        else if(second.contains(">"))
                        {
                        middle = second.substring(second.indexOf(">") + 1 );
                        second = second.substring(0, second.lastIndexOf(">"));                        
                        }
                        System.out.println("Second: " + second);
                        
                        
                        
                        
                        System.out.println("Middle: " +middle);


                        String third = s.substring(s.lastIndexOf(";")+1);
                        third = third.replaceAll("\\s+", ""); // Removes spaces from string
                        System.out.println("Third: " + third);
                        middle= Middle(middle,second);
                        middle=FormattingMiddle(middle);
                        middle= Operators (third,middle);
                        //second = Operators (third,middle);
                        System.out.println("Test: " + middle);
                        System.out.println("Operators: "+middle);
//                        if (middle.contains("^"))
//                        {
//                           middle= middle.substring(middle.indexOf("^")-1);
//                        }


                        return middle;
                        //second = Middle (middle,second);
                        //runTimes.add(Middle(middle, second));

////                        // Time Complexity Analysis
//                        if (third.contains("++")||third.contains("--"))
//                        {
//                           timeComplexity1  = "n";
//                        }
//                        // breaking down logarithmic values
//                        if (third.contains("*="))
//                        {
//                           timeComplexity1 = "logn";
//                       }
                       
                    } // First for loop
    
    public static void PrintWriter(String Answer,String newPath) throws IOException
    {
        try {
            FileWriter fw = new FileWriter(newPath);
            fw.write(Answer);
            fw.close();
        } catch (FileNotFoundException e)
        {
            // FIle not found
            e.printStackTrace();
            
        
        } catch (IOException e)
        {
            // Error when writing to the file
            e.printStackTrace();
        }
     
  } 
    
    
    public static String FormattingMiddle(String middle)
    {
     char B[] = middle.toCharArray();
      for (int i=0; i<B.length ; i++)
      {
      if (Character.isDigit(B[i]))
      {
      if((i-1>0)&&((B[i-1]=='*')||(B[i-1]=='+')||(B[i-1]=='/')||(B[i-1]=='-')))
      {
      //delete the int
      //delete the minus 1
      B[i]='$';
      B[i-1]='$';
      
      }
      if((i+1<B.length)&&(((B[i+1]=='*')||(B[i+1]=='+')||(B[i+1]=='/')||(B[i+1]=='-'))))
      {
      //delete the int
      //delete the plus 1
      B[i]='$';
      B[i+1]='$';
      }
      if((i-1>0)&&(B[i-1]!='^'))
      {
      //delete the int
      //delete the plus 1
      B[i]='$';
      }
      if((i-1<0))
      {
      //delete the int
      //delete the plus 1
          int x=0;
          while(Character.isDigit(B[i+x])){
                    B[i+x]='$';
                    x++;
          }

      }
      
      
      }
      String mid="";
  for(int j=0; j<B.length; j++){
      if(B[j]!='$'){
          mid=mid+B[j];
      }    
  }

  
  middle=mid;
      }
      return middle;
    }
}// End of the whole project