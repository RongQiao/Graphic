package Basic;

public class BasicUtil {

	public static boolean isOdd(int num) {
		boolean ret = false;
		
		int m = num % 2;
		ret = (m == 0 ? false : true);
		
		return ret;
	}

}
