package com.dpc.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by MKJS
 *
 * 20 juillet 2017
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Champ {

	
	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "champId", strategy = "uuid2")
	@GeneratedValue
	private Long id;

	
	@Column(unique = false, nullable = false)
	private String nom;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "champ_chantier", joinColumns = @JoinColumn(
                    name = "champ_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "chantier_id", referencedColumnName = "id"))
    private List<Chantier> chantiers;
	
	@ManyToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "champ_categorie", joinColumns = @JoinColumn(
                    name = "champ_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "categorie_id", referencedColumnName = "id"))
    private List<Categorie> categories;
}
