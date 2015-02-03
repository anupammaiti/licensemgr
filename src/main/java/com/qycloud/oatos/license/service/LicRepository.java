package com.qycloud.oatos.license.service;

import com.qycloud.oatos.license.domain.Lic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiuyuehe on 2015/1/21.
 */
public interface LicRepository extends JpaRepository<Lic,Long> {

}
