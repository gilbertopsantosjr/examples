/**
 * 
 */
package org.mail.box.useful;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author Santos, Giblerto
 *
 */
public class StatefulBean implements Scope {

	private Map<String, Object> objectMap = Collections.synchronizedMap(new HashMap<String, Object>());

	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (!objectMap.containsKey(name)) {
			objectMap.put(name, objectFactory.getObject());
		}
		return objectMap.get(name);

	}

	public Object remove(String name) {
		return objectMap.remove(name);
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		// do nothing
	}

	public Object resolveContextualObject(String key) {
		return null;
	}

	public String getConversationId() {
		return "StatefulBean";
	}

	public void clearBean() {
		objectMap.clear();
	}
}
