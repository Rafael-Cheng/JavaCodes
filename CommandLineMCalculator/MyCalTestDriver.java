import java.util.regex.*;
/**
 *MyCalTestDriver
 *@Author Rafael Cheng
 */


public class MyCalTestDriver{
	public static void main(String[] args){
		if(args.length != 3){
			System.out.println("Wrong Length");
			return;
		}
		MyCal cal = new MyCal();
		if(isNumberic(args[0]) && isNumberic(args[2])){
			if(args[1].equals("+")){
				cal.add(Integer.parseInt(args[0]), Integer.parseInt(args[2]));
			}else if(args[1].equals("-")){
				cal.minus(Integer.parseInt(args[0]), Integer.parseInt(args[2]));
			}else if(args[1].equals("x") || args[1].equals("X)){
				cal.multiply(Integer.parseInt(args[0]), Integer.parseInt(args[2]));
			}else{
				cal.division(Integer.parseInt(args[0]), Integer.parseInt(args[2]));
			}
		}
		else{
			System.out.println("Error");
		}
	}
	public static boolean isNumberic(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}
			return true;
		}
}
