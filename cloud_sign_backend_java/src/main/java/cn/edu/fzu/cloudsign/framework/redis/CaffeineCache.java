package cn.edu.fzu.cloudsign.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;

/**
 * spring caffeine 工具类
 * 
 * @author fanxu
 *
 */
@Component
public class CaffeineCache {

	@Autowired
	private CacheManager cacheManager;

	/**
	 * 获得缓存的基本对象
	 * 
	 * @param <T>
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getCacheObject(String key) {
		Cache cache = cacheManager.getCache("cloudsign");
		return StringUtils.isNotNull(cache.get(key)) ? (T) cache.get(key).get() : null;

	}

	/**
	 * 删除单个对象
	 * 
	 * @param userKey
	 */
	public void deleteObject(String userKey) {
		Cache cache = cacheManager.getCache("cloudsign");
		cache.evict(userKey);
	}

	public void setCacheObject(String userKey, LoginUser loginUser) {
		Cache cache = cacheManager.getCache("cloudsign");
		cache.putIfAbsent(userKey, loginUser);
	}

}
