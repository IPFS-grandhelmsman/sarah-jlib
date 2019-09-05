package com.helmsman.sarah.node;

import org.junit.Test;

import java.util.Date;

/**
 * @author yangjian
 * @since 2019-01-17 上午10:10.
 */
public class TempTest {

	@Test
	public void test() {
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println(new Long(date.getTime()/1000).intValue());
	}
}
