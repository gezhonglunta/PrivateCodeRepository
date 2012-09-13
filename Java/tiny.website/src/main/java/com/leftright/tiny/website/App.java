package com.leftright.tiny.website;

import org.springframework.core.AliasRegistry;
import org.springframework.core.ControlFlow;

import com.leftright.tiny.website.utils.SpringContextHolder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		SpringContextHolder.getApplicationContext();
	}
}

class A implements AliasRegistry, ControlFlow {

	public void registerAlias(String name, String alias) {
		// TODO Auto-generated method stub

	}

	public void removeAlias(String alias) {
		// TODO Auto-generated method stub

	}

	public boolean isAlias(String beanName) {
		// TODO Auto-generated method stub
		return false;
	}

	public String[] getAliases(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean under(Class clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean under(Class clazz, String methodName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean underToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

}
