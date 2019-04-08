CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- table bundle
CREATE TABLE IF NOT EXISTS bundle  
(
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  key text NOT NULL,
  value text,
  comment text,
  env bigint NOT NULL DEFAULT 1,
  CONSTRAINT bundle_pkey PRIMARY KEY (id),
  CONSTRAINT bundle_ukey UNIQUE (key,env)
);

--table valueHisto
CREATE TABLE IF NOT EXISTS value_histo
(
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  value text,
  comment text,
  version bigint NOT NULL,
  bundle_id uuid,
  CONSTRAINT valueHisto_pkey PRIMARY KEY (id),
  CONSTRAINT valueHisto_fkey FOREIGN KEY (bundle_id)
      REFERENCES bundle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- table bundlesVersion
CREATE TABLE IF NOT EXISTS bundles_version
(
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  file text NOT NULL,
  username text NOT NULL,
  version bigint NOT NULL,
  action_date text NOT NULL,
  env bigint NOT NULL DEFAULT 1,
  CONSTRAINT bundlesVersion_pkey PRIMARY KEY (id),
  CONSTRAINT bundlesVersion_ukey1 UNIQUE (file),
  CONSTRAINT bundlesVersion_ukey UNIQUE (version,env)
);




-- Table: region

CREATE TABLE IF NOT EXISTS region
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  CONSTRAINT region_pkey PRIMARY KEY (id)
);

-- Table: evenement

CREATE TABLE IF NOT EXISTS evenement
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  CONSTRAINT evenement_pkey PRIMARY KEY (id)
);
-- Table: champ

CREATE TABLE IF NOT EXISTS champ
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  CONSTRAINT champ_pkey PRIMARY KEY (id)
);

-- Table: activite

CREATE TABLE IF NOT EXISTS activite
(
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  CONSTRAINT activite_pkey PRIMARY KEY (id)
);

-- Table: ville

CREATE TABLE IF NOT EXISTS ville
(
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  region_id uuid,
  CONSTRAINT ville_pkey PRIMARY KEY (id),
  CONSTRAINT ville_fkey FOREIGN KEY (region_id)
      REFERENCES region (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: role

CREATE TABLE IF NOT EXISTS role
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id)
);
-- Table: codepostale

CREATE TABLE IF NOT EXISTS codepostale
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL, 
  code bigint NOT NULL,
  ville_id uuid,
  CONSTRAINT codepostale_pkey PRIMARY KEY (id),
  CONSTRAINT codepostale_fkey FOREIGN KEY (ville_id)
      REFERENCES ville (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: employer

CREATE TABLE IF NOT EXISTS employer
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  email character varying(255) NOT NULL,
  nom character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  prenom character varying(255) NOT NULL,
  CONSTRAINT employer_pkey PRIMARY KEY (id)
);

-- Table: client

CREATE TABLE IF NOT EXISTS client
(
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  prenom character varying(255) NOT NULL,
  civilite character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  telephone bigint NOT NULL,
  employer_id uuid,
  CONSTRAINT client_pkey PRIMARY KEY (id),
  CONSTRAINT client_fkey FOREIGN KEY (employer_id)
      REFERENCES employer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: artisant


CREATE TABLE IF NOT EXISTS artisant
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  prenom character varying(255) NOT NULL,
  adresse character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  raisonsocial character varying(255) NOT NULL,
  codepostale_id uuid,
  employer_id uuid,
  CONSTRAINT artisant_pkey PRIMARY KEY (id),
  CONSTRAINT artisant_fkey FOREIGN KEY (codepostale_id)
      REFERENCES codepostale (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT artisant_fkey1 FOREIGN KEY (employer_id)
      REFERENCES employer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: categorie

CREATE TABLE IF NOT EXISTS categorie
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  nom character varying(255) NOT NULL,
  activite_id uuid,
  CONSTRAINT categorie_pkey PRIMARY KEY (id),
  CONSTRAINT categorie_fkey FOREIGN KEY (activite_id)
      REFERENCES activite (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



-- Table: chantier

CREATE TABLE IF NOT EXISTS chantier
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  rdv character varying(255) NOT NULL,
  adresse character varying(255) NOT NULL,
  discription character varying(255) NOT NULL,
  etat character varying(255) NOT NULL,
  client_id uuid,
  codepostale_id uuid,
  employer_id uuid,
  CONSTRAINT chantier_pkey PRIMARY KEY (id),
  CONSTRAINT chantier_fkey FOREIGN KEY (employer_id)
      REFERENCES employer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT chantier_fkey1 FOREIGN KEY (client_id)
      REFERENCES client (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT chantier_fkey2 FOREIGN KEY (codepostale_id)
      REFERENCES codepostale (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: role_employe

CREATE TABLE IF NOT EXISTS role_employer
(
  role_id uuid NOT NULL,
  employer_id uuid NOT NULL,
  CONSTRAINT role_fkey FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT employer_fkey FOREIGN KEY (employer_id)
      REFERENCES employer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: historique

CREATE TABLE historique
(
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
  date character varying(255) NOT NULL,
  discriptif character varying(255) NOT NULL,
  artisant_id uuid,
  chantier_id uuid,
  employee_id uuid,
  evenement_id uuid,
  CONSTRAINT historique_pkey PRIMARY KEY (id),
  CONSTRAINT historique_fkey FOREIGN KEY (evenement_id)
      REFERENCES evenement (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT historique_fkey1 FOREIGN KEY (artisant_id)
      REFERENCES artisant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT historique_fkey2 FOREIGN KEY (chantier_id)
      REFERENCES chantier (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT historique_fkey3 FOREIGN KEY (employee_id)
      REFERENCES employer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: historique_employer

CREATE TABLE historique_employer
(
  historique_id uuid NOT NULL,
  employer_id uuid NOT NULL,
  CONSTRAINT employer_fkey FOREIGN KEY (employer_id)
      REFERENCES employer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT historique_fkey FOREIGN KEY (historique_id)
      REFERENCES historique (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: historique_artisant

CREATE TABLE historique_artisant
(
  historique_id uuid NOT NULL,
  artisant_id uuid NOT NULL,
  CONSTRAINT historique_fkey FOREIGN KEY (historique_id)
      REFERENCES historique (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT artisant_fkey FOREIGN KEY (artisant_id)
      REFERENCES artisant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: chantier_historique

CREATE TABLE chantier_historique
(
  chantier_id uuid NOT NULL,
  historique_id uuid NOT NULL,
  CONSTRAINT chantier_fkey FOREIGN KEY (chantier_id)
      REFERENCES chantier (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT historique_fkey FOREIGN KEY (historique_id)
      REFERENCES historique (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: chantier_artisant

CREATE TABLE chantier_artisant
(
  chantier_id uuid NOT NULL,
  artisant_id uuid NOT NULL,
  CONSTRAINT artisant_fkey FOREIGN KEY (artisant_id)
      REFERENCES artisant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT chantier_fkey FOREIGN KEY (chantier_id)
      REFERENCES chantier (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: champ_chantier

CREATE TABLE champ_chantier
(
  champ_id uuid NOT NULL,
  chantier_id uuid NOT NULL,
  CONSTRAINT chantier_fkey FOREIGN KEY (chantier_id)
      REFERENCES chantier (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT champ_fkey FOREIGN KEY (champ_id)
      REFERENCES champ (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: champ_categorie

CREATE TABLE champ_categorie
(
  champ_id uuid NOT NULL,
  categorie_id uuid NOT NULL,
  CONSTRAINT categorie_fkey FOREIGN KEY (categorie_id)
      REFERENCES categorie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT champ_fkey FOREIGN KEY (champ_id)
      REFERENCES champ (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: categorie_artisant

CREATE TABLE categorie_artisant
(
  categorie_id uuid NOT NULL,
  artisant_id uuid NOT NULL,
  CONSTRAINT categorie_fkey FOREIGN KEY (categorie_id)
      REFERENCES categorie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT artisant_fkey FOREIGN KEY (artisant_id)
      REFERENCES artisant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);