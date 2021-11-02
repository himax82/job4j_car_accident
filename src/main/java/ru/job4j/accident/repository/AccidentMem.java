package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        init();
    }

    public void init() {
        accidents.put(1, new Accident(1,
                        "Превышение скорости",
                        "17.08.2021 в 12:15 Водитель автомобиля Лада Гранта г/н А 564 КВ превысил установленную сткороть",
                        "г. Казань, просект Ленина"
                ));
        accidents.put(2, new Accident(2,
                        "Столконовение двух а/с без пострадавших ",
                        "19.08.2021 в 18:40 Автомобиль Тойота Камри г/н Х 532 УЕ не пропустил на перекресте а/м УАЗ Патриот г/н О 123 ИМ.",
                        "перекресток улиц Гагарина и Куйбышева"
                ));
        accidents.put(3, new Accident(3,
                        "Наезд на препятствие",
                        "21.08.2021 в 21 ч 18 м автомобиль Субару Форестер г/н А 245 ВР совершил столкновение с препядствием" +
                                "(опора электропередач)",
                        "Возле дома по ул. Советов 15"
                ));
    }

    public void save(Accident accident) {
        accidents.put(accidents.size() + 1, accident);
    }


    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
