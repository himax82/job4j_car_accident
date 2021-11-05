package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentHibernate.class.getName());

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            if (accident.getId() == 0) {
                session.save(accident);
            }
            else {
                session.update(accident);
            }
        } catch (Exception e) {
            LOG.error("Error save accident ");
        }
        return accident;
    }

    public List<Accident> findAll() {
        List<Accident> list = null;
        try {
            list = et(session -> session.createQuery("select distinct a from Accident a "
                    + "left join fetch a.rules r", Accident.class)
                    .list());
        } catch (Exception e) {
            LOG.error("Don't find All Accident");
        }
        return list;
    }

    public Collection<AccidentType> findAllTypes() {
        List<AccidentType> list = new ArrayList<>();
        try {
            list = et(session -> session.createQuery("from AccidentType", AccidentType.class).list());
        } catch (Exception e) {
            LOG.error("Don't find All Accident");
        }
        return list;
    }

    public Collection<Rule> findAllRules() {
        List<Rule> list = new ArrayList<>();
        try {
            list = et(session -> session.createQuery("from Rule", Rule.class).list());
        } catch (Exception e) {
            LOG.error("Don't find All Rules");
        }
        return list;
    }

    public Accident findAccidentById(int id) {
        Accident accident = null;
        try {
            accident = et(session -> session.createQuery("select distinct a from Accident  a "
                    + "left join fetch a.rules r where a.id = :id", Accident.class)
                    .setParameter("id", id)
                    .uniqueResult());
        } catch (Exception e) {
            LOG.error("Don't find accident");
        }
        return accident;
    }

    public Rule findRuleById(int id) {
        Rule rule = null;
        try {
            rule = et(session -> session.createQuery("select r from Rule r "
                    + "where r.id = :id", Rule.class)
                    .setParameter("id", id)
                    .uniqueResult());
        } catch (Exception e) {
            LOG.error("Don't find Rule");
        }
        return rule;
    }

    private <T> T et(Function<Session, T> f) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T result = f.apply(session);
            tx.commit();
            return result;
        } catch (final Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
