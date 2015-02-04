package com.qycloud.oatos.license.dao;

import com.qycloud.oatos.license.domain.Lic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiuyuehe on 2015/1/21.
 */
public interface LicRepository extends JpaRepository<Lic,Long> {

   public  Page<Lic> findByLicToContaining(String licTo,Pageable pageable);

}
