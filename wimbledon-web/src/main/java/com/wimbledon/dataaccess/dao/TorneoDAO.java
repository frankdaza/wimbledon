package com.wimbledon.dataaccess.dao;

import com.wimbledon.dataaccess.api.HibernateDaoImpl;

import com.wimbledon.modelo.Torneo;
import com.wimbledon.modelo.dto.TorneoDTO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * Torneo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Torneo
 */
@Scope("singleton")
@Repository("TorneoDAO")
public class TorneoDAO extends HibernateDaoImpl<Torneo, Integer>
    implements ITorneoDAO {
    private static final Logger log = LoggerFactory.getLogger(TorneoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ITorneoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ITorneoDAO) ctx.getBean("TorneoDAO");
    }
    
    @Override
    public List<TorneoDTO> consultarResultadosTorneos(Integer tornId) throws Exception {
    	try {
			Query query = getSession().getNamedQuery("consultar_resultados_torneos");
			query.setParameter("pTornId", tornId);
			query.setResultTransformer(Transformers.aliasToBean(TorneoDTO.class));			
			return query.list();
		} catch (Exception e) {
			throw e;
		}
    }
    
}
