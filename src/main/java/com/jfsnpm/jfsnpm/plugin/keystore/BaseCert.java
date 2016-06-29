package com.jfsnpm.jfsnpm.plugin.keystore;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

@SuppressWarnings("all")
public class BaseCert {
	String CA_ROOT_ISSUER="C=CN,ST=JiangSu,L=JiangSu,O=JFSNPM,OU=JFSNPM,CN=CA";   
    String CA_DEFAULT_SUBJECT="C=CN,ST=JiangSu,L=JiangSu,O=JFSNPM,OU=JFSNPM,CN=";    
    String CA_SHA="SHA256WithRSAEncryption";  
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	protected static KeyPairGenerator kpg = null;
	protected static KeyPair keyPair = null;

	public BaseCert() {
		try {
            // 采用 RSA 非对称算法加密
			kpg = KeyPairGenerator.getInstance("RSA");
            // 初始化为 1024 位
			kpg.initialize(1024);
			keyPair = kpg.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	public KeyPair getKeyPair(){
		return keyPair;
	}

	/**
	 * 生成 X509 证书
	 * @param user
	 * @return
	 */
	public X509Certificate generateCert(String user) {
		return generateCert(user, this.keyPair.getPrivate());
	}
	public X509Certificate generateCert(String user,PrivateKey priKeya) {
		X509Certificate cert = null;
		try {
			KeyPair keyPair = this.keyPair;
            // 公钥
			PublicKey pubKey = keyPair.getPublic();
            // 私钥
			PrivateKey priKey = priKeya;
			X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
            // 设置序列号
			certGen.setSerialNumber(BigInteger.valueOf(1));
			//certGen.setSerialNumber(CertUtil.getNextSerialNumber());
            // 设置颁发者
			certGen.setIssuerDN(new X500Principal(CA_ROOT_ISSUER));
            // 设置有效期
			certGen.setNotBefore(new Date());
			GregorianCalendar gc=new GregorianCalendar(); 
			gc.setTime(new Date()); 
			gc.add(1,10); 
			certGen.setNotAfter(gc.getTime());
            // 设置使用者
			certGen.setSubjectDN(new X500Principal(CA_DEFAULT_SUBJECT + user));
			// 公钥
			certGen.setPublicKey(pubKey);
            // 签名算法
			certGen.setSignatureAlgorithm(CA_SHA);
			cert = certGen.generateX509Certificate(priKey, "BC");
		} catch (Exception e) {
			System.out.println(e.getClass() + e.getMessage());
		}
		return cert;
	}
}
