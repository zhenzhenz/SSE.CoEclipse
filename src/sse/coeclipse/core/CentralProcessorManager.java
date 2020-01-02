package sse.coeclipse.core;

import java.util.HashMap;
import java.util.Map;

// CentralProcessorManager 管理现存的所有CentralProcessor
// 内部维护 文件名：CentralProcessor 的map
public class CentralProcessorManager {
	public static Map<String, CentralProcessor> manager = new HashMap<String, CentralProcessor>();
	public static void addCentralProcessor(String key, CentralProcessor value) {
		CentralProcessorManager.manager.put(key, value);
	}
	public static void deleteCentralProcessor(String key) {
		CentralProcessorManager.manager.remove(key);
	}
	public static CentralProcessor findCentralProcessor(String key) {
		return CentralProcessorManager.manager.get(key);
	}
}
