package SuperKartWebServices.SuperKartWebServices;

import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.InetSocketAddress;

public class MemCached {

	//   private static final Logger logger = LogManager.getLogger(MemCacheUtil.class);
	private static MemcachedClient client = null;

	static {

		try {
			client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean putInCache(String key, String jobListVO) {
		System.out.println("Setting up cache for "+key);
		return client.set(key, 900,jobListVO) != null;
	}

	public static String getFromCache(String key) {
		//    return null;

		String value = (String) client.get(key);
		if (value == null) {
			System.out.println("Cache miss for "+key);
			return null;
		} else {
			System.out.println("Cache hit for key "+key);
			return value;
		}
	}
	public static boolean removeFromCache(String key) {
		//    return null;
		System.out.println("Clearing cache for "+key);
		boolean value = client.delete(key) != null;
		return value;
	}

}
