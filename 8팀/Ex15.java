/usr/bin/bash: wq: command not found
import java.util.Scanner;
import java.util.*;
public class Ex15 {

	public static void main(String[] args)  {
		for(;;) {
			Scanner scanner=new Scanner(System.in);
			System.out.print("���ϰ��� �ϴ� �� ���Է�>>");
		
			try {
				//Exception e = new Exception("���α׷� ����.");
				int n=scanner.nextInt();
				int m=scanner.nextInt();
				System.out.println(n+"*"+m+"="+(n*m));
				System.out.println("2017038051������");
				System.exit(0);
				//throw e;	 // ���ܸ� �߻���Ŵ
			}catch(InputMismatchException e){
				scanner.nextLine();
				System.out.println("�Ǽ��� �Է��ϸ� �ȵ˴ϴ�");
			}
		
		}
	}

}
