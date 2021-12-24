package LYAMBDA.ПереписьНаселения;

import java.util.*;
import java.util.stream.Collectors;

import static LYAMBDA.ПереписьНаселения.Education.HIGHER;
import static LYAMBDA.ПереписьНаселения.Sex.MAN;
import static LYAMBDA.ПереписьНаселения.Sex.WOMAN;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //1. Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        //2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> recruits = persons.stream()
                .filter(x -> x.getSex().equals(MAN))
                .filter(x -> x.getAge() > 18 & x.getAge() < 27)
                .map(value -> value.getFamily())    //нужны только фамилии
                .collect(Collectors.toList());
//        System.out.println("Список фамилий призывников:\n" + recruits);

        //3. Список работоспособных людей
        List<Person> listOfWorkablePeople = persons.stream()
                .filter(x -> x.getEducation().equals(HIGHER))
                .filter(x -> x.getAge() > 18 & x.getAge() < 65)
                .filter(obj -> obj.getSex().equals(WOMAN) & obj.getAge() <= 60 ||
                        obj.getSex().equals(MAN) & obj.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
//        System.out.println("Список работоспособных людей:\n" + listOfWorkablePeople);

    }
}
