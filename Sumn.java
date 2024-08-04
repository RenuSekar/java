import java.util.Scanner;

public class Sumn{
public static void main(String[] args)
{
Scanner scanner = new Scanner(System.in);

System.out.println("enter the value of n:");
int n = scanner.nextInt();

int sum = 0;

for(int i=0;i<=n;i++)
{
sum+=i;
}

System.out.println("Sum of the "+ n + " numbers is:" + sum);
scanner.close();
}
}