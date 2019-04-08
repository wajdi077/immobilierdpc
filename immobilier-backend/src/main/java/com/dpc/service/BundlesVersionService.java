/**
 *
 */
package com.dpc.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.BundlesVersion;
import com.dpc.repository.BundlesVersionRepository;

/**
 * Created by LMA
 *
 * 6 avr. 2017
 */
@Service
public class BundlesVersionService {

    /*
     * (non-Javadoc)
     * @see com.oav.pegase.service.BundlesVersionService#getAllBundlesVersion()
     */

    @Autowired
    BundlesVersionRepository bvRepo;

    public Collection<BundlesVersion> getAllBundlesVersion(Long env) {
        List<BundlesVersion> list = bvRepo.findByEnv(env);
        Collections.sort(list);
        return list;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.oav.pegase.service.BundlesVersionService#getBundlesVersionById(java.
     * util.UUID)
     */

    public Long getBundlesVersionById(Long id) {
        return bvRepo.findOne(id).getVersion();
    }

    public String getBundlesVersionFileNameById(Long id) {
        return bvRepo.findOne(id).getFile();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.oav.pegase.service.BundlesVersionService#createNewBundlesVersion(java
     * .lang.String, java.lang.String)
     */

    public void createNewBundlesVersion(String file, String date, Long env) {
        BundlesVersion bv = new BundlesVersion();
        bv.setEnv(env);
        bv.setFile(file);
        bv.setActionDate(date);
        bv.setUsername("test");
        BundlesVersion bvExist = bvRepo.findTopByEnvOrderByVersionDesc(env);
        Long version = bvExist == null ? Long.valueOf(0) : bvExist.getVersion() + 1;
        bv.setVersion(version);
        bvRepo.save(bv);
    }

}
