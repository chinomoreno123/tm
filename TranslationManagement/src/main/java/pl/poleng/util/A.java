package pl.poleng.util;



public class A {
	
	public A(){
		
		Dog dog = new Dog();
		test2(dog);
		System.out.println(dog.getName());
	}
	
	public void test (String str) {
		str = "NEW TEXT";
	}
	
	
	public void test2(Dog dog) {
		dog.setName("AZOR");
	}
	
	public static void main(String[] args) {
		//A a = new A();
		int x = 1;
		int y = 07;
		
		
		
		System.out.println(x);
		/*String str = new String("ALA MA KOTA");
		a.test(str);	
		System.out.println(str);*/

	}
	
	public class Dog {
		private String name;
		
		public Dog() {
			name = "DOG";
		}
		
		public void setName(String newName) {
			this.name = newName;
		}
		
		public String getName() {
			return this.name;
		}
	}
}