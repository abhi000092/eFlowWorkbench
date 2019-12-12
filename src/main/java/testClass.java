import com.github.javafaker.Faker;

public class testClass {
	Faker random = new Faker();
	public static void main(String[] args) throws Exception {
		//testClass.m1(10,20);
		//testClass.m1(10, 20);
		/*monkey a = new monkey();
		System.out.println(a.x);*/
		Faker random = new Faker();
		System.out.println(random.address().city());
		
	}
	

}



