package com.qycloud.oatos.license.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jiuyuehe on 2015/1/21.
 */

@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Lic implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String licType;

    @Column(nullable = false)
    private String proVersion;

    @Column(nullable = false)
    private String licTo;

    @Column(nullable = false)
    private String licNo;

    @Column(nullable = false)
    private String licMac;

    @Column(nullable = false)
    private int maxUser;

    @Column(nullable = false)
    private String expiry;

    @Column
    private String signature;

    @Column
    private String connect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Lic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicType() {
        return licType;
    }

    public void setLicType(String licType) {
        this.licType = licType;
    }

    public String getProVersion() {
        return proVersion;
    }

    public void setProVersion(String proVersion) {
        this.proVersion = proVersion;
    }

    public String getLicTo() {
        return licTo;
    }

    public void setLicTo(String licTo) {
        this.licTo = licTo;
    }

    public String getLicNo() {
        return licNo;
    }

    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    public String getLicMac() {
        return licMac;
    }

    public void setLicMac(String licMac) {
        this.licMac = licMac;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Lic{" +
                "id=" + id +
                ", licType='" + licType + '\'' +
                ", proVersion='" + proVersion + '\'' +
                ", licTo='" + licTo + '\'' +
                ", licNo='" + licNo + '\'' +
                ", licMac='" + licMac + '\'' +
                ", maxUser=" + maxUser +
                ", expiry='" + expiry + '\'' +
                ", signature='" + signature + '\'' +
                ", connect='" + connect + '\'' +
                ", user=" + user +
                '}';
    }
}
