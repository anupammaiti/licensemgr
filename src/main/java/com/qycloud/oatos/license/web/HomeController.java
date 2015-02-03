package com.qycloud.oatos.license.web;

import com.qycloud.oatos.license.domain.Lic;
import com.qycloud.oatos.license.domain.User;
import com.qycloud.oatos.license.service.LicRepository;
import com.qycloud.oatos.license.service.UserRepository;
import com.qycloud.oatos.license.utils.License;
import com.qycloud.oatos.license.utils.LicenseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

/**
 * Created by jiuyuehe on 2015/1/16.
 */

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LicRepository licenseRepository;

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld() {
        return "hello world license home";
    }
    //=============user services==============
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User user) {
        System.out.println(user.getEmail());
        User getUser = this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (getUser != null) {
            return "ok";
        }
        return "errorAccountOrPwd";
    }


    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(@PathVariable String userId) {
        System.out.println("userId : " + userId);
        User u = userRepository.findOne(Long.valueOf(userId));
        return u.getName();
    }


    //==============================license service==============

    @RequestMapping(value = "/api/lics", method = RequestMethod.POST)
    @ResponseBody
    public Lic addLicense(@RequestBody Lic lic) {

        System.out.println("POST  lic ======================" + lic);

        User u = userRepository.findOne(Long.valueOf(2));
        lic.setUser(u);

        if (lic.getLicNo() == null) {
            lic.setLicNo(LicenseUtil.get().getLicNo());
        }
        lic.setExpiry(LicenseUtil.get().getExpiry(Integer.valueOf(lic.getExpiry())));

        Lic license = licenseRepository.save(lic);
        return license;
    }

    @RequestMapping(value = "/api/lics/{licId}", method = RequestMethod.POST)
    @ResponseBody
    public Lic addLicense(@RequestBody Lic lic, @PathVariable Long licId) {

        System.out.println("POST  lic ======================" + lic);

        Lic upLic = licenseRepository.findOne(licId);

        if (lic.getExpiry() == null) {
            upLic.setExpiry(LicenseUtil.get().getExpiry(Integer.valueOf(15)));
        } else {
            //upLic.setExpiry(LicenseUtil.get().getExpiry(Integer.valueOf(lic.getExpiry())));
        }
        upLic.setLicMac(lic.getLicMac());
        upLic.setConnect(lic.getConnect());
        upLic.setLicType(lic.getLicType());
        upLic.setMaxUser(lic.getMaxUser());
        upLic.setLicTo(lic.getLicTo());

        Lic license = licenseRepository.save(upLic);
        return license;
    }

    /**
     * page 从第0页开始
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/api/lics", method = RequestMethod.GET)
    @ResponseBody
    public Page<Lic> getLicensesByPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "2") int size) {


        Page<Lic> pageLicense = licenseRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));

        return pageLicense;
    }


    @RequestMapping(value = "/api/lics/{licId}")
    @ResponseBody
    public Lic getLicenseInfo(@PathVariable String licId) {

        System.out.println("get lic=================== : " + licId);
        Lic license = licenseRepository.findOne(Long.valueOf(licId));
        return license;
    }


}
