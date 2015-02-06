package com.qycloud.oatos.license.web;

import com.qycloud.oatos.license.domain.Lic;
import com.qycloud.oatos.license.domain.User;
import com.qycloud.oatos.license.dao.LicRepository;
import com.qycloud.oatos.license.dao.UserRepository;
import com.qycloud.oatos.license.service.LicService;
import com.qycloud.oatos.license.service.LicServiceImpl;
import com.qycloud.oatos.license.service.UserService;
import com.qycloud.oatos.license.utils.LicenseUtil;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * Created by jiuyuehe on 2015/1/16.
 */

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LicRepository licenseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LicService licService;

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld() {
        return "hello world license home";
    }

    //=============user services==============
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User user) {
        System.out.println(user);
        try {
            String re = userService.login(user.getEmail().trim(), user.getPassword().trim());
            if (re == null) {
                return "errorAccountOrPwd";
            } else {
                return re.trim();
            }
        } catch (Exception e) {
            return "error500";
        }
    }

//    @RequestMapping(value = "/api/sc/user/{userId}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getUserInfo(@PathVariable String userId) {
//        System.out.println("userId : " + userId);
//        User u = userRepository.findOne(Long.valueOf(userId));
//        return u.getName();
//    }

    //==============================license service==============

    @RequestMapping(value = "/api/sc/lic/search/", method = RequestMethod.GET)
    @ResponseBody
    public Page<Lic> searchLic(@RequestParam(value = "keyWord", required = true) String keyWord,
                               @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "2") int size
    ) {
        System.out.println("search  lic ======================" + keyWord);
        Page<Lic> license = licenseRepository.findByLicToContaining(keyWord, new PageRequest(page, size));
        return license;
    }

    @RequestMapping(value = "/api/lic/download/{licId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadLic(@PathVariable long licId,
                                              @RequestParam(value = "ut", required = true)String ut) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "oatos.lic");
            return new ResponseEntity<byte[]>(FileUtil.readAsByteArray(this.licService.downloadLic(licId)),
                    headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/api/sc/lics", method = RequestMethod.POST)
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

    @RequestMapping(value = "/api/sc/lics/{licId}", method = RequestMethod.POST)
    @ResponseBody
    public Lic updateLicense(@RequestBody Lic lic, @PathVariable Long licId) {

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
    @RequestMapping(value = "/api/sc/lics", method = RequestMethod.GET)
    @ResponseBody
    public Page<Lic> getLicensesByPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        Page<Lic> pageLicense = licenseRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        return pageLicense;
    }


    @RequestMapping(value = "/api/sc/lics/{licId}")
    @ResponseBody
    public Lic getLicenseInfo(@PathVariable String licId) {

        System.out.println("get lic=================== : " + licId);
        Lic license = licenseRepository.findOne(Long.valueOf(licId));
        return license;
    }


}
