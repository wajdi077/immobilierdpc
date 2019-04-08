package com.dpc.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LMA
 *
 * 06 April 2017
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BundlesVersion implements Comparable<BundlesVersion> {

	
	@Id
   // @Type( type = "pg-uuid")
   // @GenericGenerator( name = "bundlesVersionId", strategy = "uuid2")
    @GeneratedValue
    UUID id;

   
    @Column(
            unique = false, nullable = false)
    private Long   env;

    
    @Column(
            unique = true, nullable = false)
    private String file;

    @Column(
            unique = false, nullable = false)
    private Long   version;

    
    @Column(
            unique = false, nullable = false)
    private String username;

    
    @Column(
            unique = false, nullable = false)
    private String actionDate;

    @Override
    public int compareTo(BundlesVersion p) {
        return this.version.compareTo(p.version);
    }
}
