package com.qycloud.oatos.license.utils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("license")
public class License {

	@XStreamAlias("licenseType")
	private String licenseType;

	@XStreamAlias("licenseVersion")
	private String licenseVersion = "2.0";

	@XStreamAlias("productVersion")
	private String productVersion;

	@XStreamAlias("licenseTo")
	private String licenseTo;

	@XStreamAlias("key1")
	private String key;

	@XStreamAlias("key2")
	private String key2;

	@XStreamAlias("maxUser")
	private int maxUser;

	@XStreamAlias("expiry")
	private String expiry;

	@XStreamAlias("licenseBy")
	private String licenseBy = "深圳企业云科技有限公司";

	@XStreamAlias("email")
	private String email = "cs@oatos.com";

	@XStreamAlias("tel")
	private String tel = "400-030-1108";

	@XStreamAlias("signature")
	private String signature;

	public License() {
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseVersion() {
		return licenseVersion;
	}

	public void setLicenseVersion(String licenseVersion) {
		this.licenseVersion = licenseVersion;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getLicenseTo() {
		return licenseTo;
	}

	public void setLicenseTo(String licenseTo) {
		this.licenseTo = licenseTo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public int getMaxUser() {
		return maxUser;
	}

	public void setMaxUser(int maxUser) {
		this.maxUser = maxUser;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getLicenseBy() {
		return licenseBy;
	}

	public void setLicenseBy(String licenseBy) {
		this.licenseBy = licenseBy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
