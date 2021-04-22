package com.demo;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author fangli
 * @date 2017年11月27日
 */
public class TestOPMS {
	
	public static void main(String[] args) {
		String pwd = DigestUtils.md5Hex("afa") + DigestUtils.md5Hex("12345678");
		System.out.println(pwd);
	}
}
