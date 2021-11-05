package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class AccidentMem {

    private final AtomicInteger integer = new AtomicInteger(0);
    private final AtomicInteger integerType = new AtomicInteger(0);
    private final AtomicInteger integerRule = new AtomicInteger(0);

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> type = new HashMap<>();

    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        init();
    }

    public void init() {

        saveType(new AccidentType(1,"Одна машина"));
        saveType(new AccidentType(2,"Машина и пешеход"));
        saveType(new AccidentType(3,"Две машины"));
        saveType(new AccidentType(4,"Более двух машин"));
        saveType(new AccidentType(5,"ДТП с пострадавшими"));
        saveType(new AccidentType(6,"Машина и животное"));

        saveRule(Rule.of(1, "Статья 12.9. Превышение установленной скорости движения"));
        saveRule(Rule.of(2, "Статья 12.11. Нарушение правил движения по автомагистрали"));
        saveRule(Rule.of(3, "Статья 12.13. Нарушение правил проезда перекрестков"));
        saveRule(Rule.of(4, "Статья 12.18. Непредоставление преимущества в движении"
                + " пешеходам или иным участникам дорожного движения"));

        save(new Accident(
                        "Превышение скорости",
                        "17.08.2021 в 12:15 Водитель автомобиля Лада Гранта г/н А 564 КВ превысил установленную сткороть",
                        "г. Казань, просект Ленина",
                findTypeById(1)
                ));
        findById(1).addRule(findRuleById(1));
        save( new Accident(
                        "Столконовение двух а/с без пострадавших ",
                        "19.08.2021 в 18:40 Автомобиль Тойота Камри г/н Х 532 УЕ не пропустил на перекресте а/м УАЗ Патриот г/н О 123 ИМ.",
                        "перекресток улиц Гагарина и Куйбышева",
                findTypeById(3)
                ));
        findById(2).addRule(findRuleById(3));
        save(new Accident(
                        "Наезд на препятствие",
                        "21.08.2021 в 21 ч 18 м автомобиль Субару Форестер г/н А 245 ВР совершил столкновение с препядствием" +
                                "(опора электропередач)",
                        "Возле дома по ул. Советов 15",
               findTypeById(5)
                ));
        findById(3).addRule(findRuleById(2));
    }

    public void save(Accident accident) {
        accident.setType(findTypeById(accident.getType().getId()));
        if (accident.getId() != 0) {
            accidents.put(accident.getId(), accident);
            return;
        }
        accident.setId(integer.incrementAndGet());
        accidents.put(integer.get(), accident);
    }

    public void saveType(AccidentType accidentType) {
        type.put(integerType.incrementAndGet(), accidentType);
    }

    public void saveRule(Rule rule) {
        rules.put(rule.getId(), rule);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public Collection<AccidentType> findAllType() {
        return type.values();
    }

    public Collection<Rule> findAllRule() {
        return rules.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public AccidentType findTypeById(int id) {
        return type.get(id);
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
