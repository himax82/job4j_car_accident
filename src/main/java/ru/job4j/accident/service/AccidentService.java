package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    private final AccidentTypeRepository accidentTypeRepository;

    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public void save(Accident accident, String[] ids) {
        for (String s : ids) {
            accident.addRule(ruleRepository.findById(Integer.parseInt(s)).get());
        }
        accidentRepository.save(accident);
    }

    public Collection<Accident> findAll() {
        List<Accident> list = new ArrayList<>();
        accidentRepository.findAll().forEach(list::add);
        return list;
    }

    public Collection<AccidentType> findAllType() {
        List<AccidentType> list = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(list::add);
        return list;
    }

    public Collection<Rule> findAllRule() {
        List<Rule> list = new ArrayList<>();
        ruleRepository.findAll().forEach(list::add);
        return list;
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id).get();
    }

}