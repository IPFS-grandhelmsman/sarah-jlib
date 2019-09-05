package com.helmsman.sarah.node;

import com.ppblock.common.utils.StringUtil;
import com.helmsman.sarah.node.model.NodeEntry;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试 HashMap 的并发读写功能
 * @author yangjian
 * @since 2019-01-16 下午2:25.
 */
public class HashMapTest {

	// Here if replace with HashMap will throw ConcurrentModificationException
	static volatile Map<Long, String> map = new ConcurrentHashMap<>();

	/**
	 * concurrent test
	 * @throws InterruptedException
	 */
	@Test
	public void concurrentTest() throws InterruptedException {

		Thread thread = new Thread(() -> {
			for (long i = 0; i < 10; i++) {
				map.put(i, i + "");
				System.out.println("ADD:" + i);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			while (true) {
				for (Iterator<Map.Entry<Long, String>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
					Map.Entry<Long, String> entry = iterator.next();
					System.out.println(entry.getKey() + " - " + entry.getValue());
				}

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread2.start();

		Thread.sleep(2000);
	}

	/**
	 * conflict test
	 */
	@Test
	public void conflictTest() {
		NodeEntry node1 = new NodeEntry(1L, 1);
		NodeEntry node2 = new NodeEntry(2L, 2);

		HashMap<NodeEntry, NodeEntry> map = new HashMap<>();
		map.put(node1, node1);
		map.put(node2, node2);

		NodeEntry nodeEntry = map.get(node1);
		NodeEntry nodeEntry2 = map.get(node2);
		System.out.println(StringUtil.jsonEncode(nodeEntry));
		System.out.println(nodeEntry2.getUserId());

	}
}
