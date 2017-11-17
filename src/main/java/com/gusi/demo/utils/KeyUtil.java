package com.gusi.demo.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KeyUtil {
	private static final Logger logger = LoggerFactory.getLogger(KeyUtil.class);

	public static Key getKey(ServletContext context) {
		String path = (context.getRealPath("/key"));
		File file = new File(path, "key.txt");
		ObjectInputStream ois = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
				Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
				ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(file));
				oo.writeObject(key);
				oo.close();
				return key;
			}

			ois = new ObjectInputStream(new FileInputStream(file));

			Key key = (Key) ois.readObject();
			return key;
		} catch (Exception e) {
			logger.debug(e.toString());
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
