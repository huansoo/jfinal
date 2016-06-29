package com.jfsnpm.jfsnpm.plugin.keystore;

import java.io.File;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class keyStoreManage {
	private static String keyFile = AppHelper.getEnv(AppConfig.properties.getProperty("keyfile", "/jfsnpmFileSystem/"))+"jfsnpm.keystore";
	private static String keyType = "JKS";
	private static String CAPassword = "jfsnpm";
	private static String CAName = "CA";
	/**
	 * 断言存在
	 * @param alias
	 * @param password
	 * @return
	 * @throws Throwable
	 */
	public static void assertExist(String alias,String password) throws Throwable{
		AppHelper.assertNotEmpty(alias);
		AppHelper.assertNotEmpty(password);
		KeyStore ks = getStore();
		if(ks.isKeyEntry(alias)){
			PrivateKeyEntry userCert = (PrivateKeyEntry) ks.getEntry(alias,  
	                new PasswordProtection(password.toCharArray()));
			X509Certificate cert = (X509Certificate) userCert.getCertificate();
			if(new Date().after(cert.getNotAfter())){
				//用户证书过期
				createCert(alias, password);
				//return true;
			}else{
				//return true;
			}
		}else{
			//不存在用户证书
			createCert(alias, password);
			//return true;
		}
	}
	/**
	 * 用CA下发证书
	 * @param alias 别名
	 * @param password 密码
	 * @throws Throwable
	 */
	private static void createCert(String alias,String password) throws Throwable{
		KeyStore ks = getStore();
		//获取CA
		PrivateKeyEntry ca = (PrivateKeyEntry) ks.getEntry(CAName,  
                new PasswordProtection(CAPassword.toCharArray()));
		X509Certificate caCert = (X509Certificate) ca.getCertificate();
		if(new Date().after(caCert.getNotAfter())){
			//CA过期，重新生成CA
			createCA();
			//重新生成CA后重新获取
			ks = getStore();
			ca = (PrivateKeyEntry) ks.getEntry(CAName,  
	                new PasswordProtection(CAPassword.toCharArray()));
		}
		BaseCert baseCert = new BaseCert();
		Certificate cert = baseCert.generateCert(alias,ca.getPrivateKey());
		ks.setKeyEntry(alias, baseCert.getKeyPair().getPrivate(),  
				password.toCharArray(), new Certificate[] { cert,ca.getCertificate() });
        java.io.FileOutputStream fos = null;
	    try {
	    	fos = new java.io.FileOutputStream(keyFile);
	        ks.store(fos, CAPassword.toCharArray());
	    } finally {
	        if (fos != null) {
	            fos.close();
	        }
	    }
	}
	/**
	 * 生成CA证书
	 * @throws Throwable
	 */
	private static void createCA() throws Throwable{
		KeyStore ks = getStore();
		BaseCert baseCert = new BaseCert();
		Certificate certCA = baseCert.generateCert(CAName);
		ks.setKeyEntry(CAName, baseCert.getKeyPair().getPrivate(),  
				CAPassword.toCharArray(), new Certificate[] { certCA });  
		certCA.verify(baseCert.getKeyPair().getPublic());
        java.io.FileOutputStream fos = null;
	    try {
	    	fos = new java.io.FileOutputStream(keyFile);
	        ks.store(fos, CAPassword.toCharArray());
	    } finally {
	        if (fos != null) {
	            fos.close();
	        }
	    }
	}
	/**
	 * 获取KeyStore文件
	 * @return
	 * @throws Throwable
	 */
	public static KeyStore getStore() throws Throwable{
		if(!new File(keyFile).exists()){
			createStore();
		}
		KeyStore ks = KeyStore.getInstance(keyType);
	    java.io.FileInputStream fis = null;
	    try {
	        fis = new java.io.FileInputStream(keyFile);
	        ks.load(fis, CAPassword.toCharArray());
		} finally {
	        if (fis != null) {
				fis.close();
	        }
	    }
	    return ks;
	}
	/**
	 * 建立KeyStore文件和CA证书
	 * @throws Throwable
	 */
	private static void createStore() throws Throwable{
		KeyStore ks = KeyStore.getInstance(keyType);
		File kf = new File(keyFile).getParentFile();
		if(!kf.exists()){
			kf.mkdirs();
		}
		java.io.FileOutputStream fos = null;
		ks.load(null, CAPassword.toCharArray());
	    try {
	        fos = new java.io.FileOutputStream(keyFile);
	        ks.store(fos, CAPassword.toCharArray());
	    } finally {
	        if (fos != null) {
	            fos.close();
	        }
	    }
	    createCA();
	}
	public static String getKeyFile() {
		return keyFile;
	}
	public static void setKeyFile(String keyFile) {
		keyStoreManage.keyFile = keyFile;
	}
	public static String getKeyType() {
		return keyType;
	}
	public static void setKeyType(String keyType) {
		keyStoreManage.keyType = keyType;
	}
	public static String getCAPassword() {
		return CAPassword;
	}
	public static void setCAPassword(String cAPassword) {
		CAPassword = cAPassword;
	}
	public static String getCAName() {
		return CAName;
	}
	public static void setCAName(String cAName) {
		CAName = cAName;
	}
}
