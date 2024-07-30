import java.util.Scanner;
public class StudentGradeCalculator{
    /**
     * @param args
     */
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter no. of subjects:");
        int subjects= input.nextInt();
        int sum=0;
        
        System.out.println("Enter your marks:");
        for (int i=0;i<subjects;i++){
            int marks = input.nextInt();
            sum+=marks;
        }
        float avg=(float)sum/subjects;
        System.out.println("Total marks:"+sum);
        System.out.println("Average percentage:"+avg);
        System.out.print("Your grade is:");
        if(avg>90 && avg<=100){
            System.out.println("O Grade");
        }
        else if(avg>80 && avg<=90){
            System.out.println("A+ Garde");
        }
        else if(avg>70 && avg<=80){
                System.out.println("A Grade");
        }
        else if(avg>60 && avg<=70){
            System.out.println("B+ Grade");
        }
        else if(avg>50 && avg<=60){
            System.out.println("B Grade");
        }
        else if(avg>40 && avg<=50){
            System.out.println("C Grade");
        }
        else if(avg>30 && avg<=40){
            System.out.println("D Grade");
        }
        else if(avg<=30){
            System.out.println("FAIL!!!");
        }
        else{System.out.println("Please Try Again. Thank you.");
        }
        input.close();
        
    }
}