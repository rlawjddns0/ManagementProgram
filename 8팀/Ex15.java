/usr/bin/bash: wq: command not found
import java.util.Scanner;
import java.util.*;
public class Ex15 {

	public static void main(String[] args)  {
		for(;;) {
			Scanner scanner=new Scanner(System.in);
			System.out.print("곱하고자 하는 두 수입력>>");
		
			try {
				//Exception e = new Exception("프로그램 종료.");
				int n=scanner.nextInt();
				int m=scanner.nextInt();
				System.out.println(n+"*"+m+"="+(n*m));
				System.out.println("2017038051오지영");
				System.exit(0);
				//throw e;	 // 예외를 발생시킴
			}catch(InputMismatchException e){
				scanner.nextLine();
				System.out.println("실수는 입력하면 안됩니다");
			}
		
		}
	}

}
