package com.pango.maven.helloworld

public class HelloWorldTest{
	@Test
	public void testSayHello(){
		HelloWorld helloworld = new HelloWorld();
		String result = helloworld.sayHello();
		AssertEquals("hellworld!",result);
	}
}