package com.nbprod.eaviculture.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.nbprod.eaviculture.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.nbprod.eaviculture.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.nbprod.eaviculture.domain.User.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Authority.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.User.class.getName() + ".authorities");
            createCache(cm, com.nbprod.eaviculture.domain.Employe.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Fonction.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Fournisseur.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Produit.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Stock.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Emplacement.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Facture.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Facture.class.getName() + ".depenses");
            createCache(cm, com.nbprod.eaviculture.domain.PhaseProduction.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.PhaseProduction.class.getName() + ".logParametreEnvironements");
            createCache(cm, com.nbprod.eaviculture.domain.Depense.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.TypeProduit.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.LigneEclairage.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Batiment.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Batiment.class.getName() + ".ligneEclairages");
            createCache(cm, com.nbprod.eaviculture.domain.Vente.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.Client.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.LogParametreEnvironement.class.getName());
            createCache(cm, com.nbprod.eaviculture.domain.PhaseProduction.class.getName() + ".listeDepenses");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
