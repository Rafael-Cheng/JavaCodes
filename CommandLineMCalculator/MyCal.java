/**
 *This is a  Calculator class
 *@Author Rafael Cheng
 */

public class MyCal{
	public void add(int a, int b){
		System.out.println(a + b);		
 	}
		public void minus(int a, int b){
			System.out.println(a - b);
		}
		public void multiply(int a, int b){
			System.out.println(a * b);
		}
		public void division(int a, int b){
			if(b == 0){
				System.out.println("Error");
			}
			else{ 
				System.out.println(a / b);
			}
		}

}
