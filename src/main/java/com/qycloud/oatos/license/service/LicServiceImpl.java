package com.qycloud.oatos.license.service;

import com.qycloud.oatos.license.dao.LicRepository;
import com.qycloud.oatos.license.domain.Lic;
import com.qycloud.oatos.license.utils.License;
import com.qycloud.oatos.license.utils.LicenseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by jiuyuehe on 2015/2/6.
 */
@Service
public class LicServiceImpl implements LicService {

    @Autowired
    private LicRepository licRepository;


    @Override
    public File downloadLic(long licId) {
        Lic lic = licRepository.findOne(licId);
        License license = licToLicense(lic);
        File file = new File(licId+"oatos.lic");
        try {
            LicenseUtil.get().toXml(license, file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return file;
    }

    private License licToLicense(Lic lic) {
        License license = new License();
        license.setExpiry(lic.getExpiry());
        license.setKey(lic.getLicNo());
        license.setKey2(lic.getLicMac());
        license.setLicenseTo(lic.getLicTo());
        license.setLicenseType(lic.getLicType());
        license.setProductVersion("3.5");
        license.setMaxUser(lic.getMaxUser());
        return license;
    }

}
