/**
 * This is part of Lec01_01: Environment Setup and Java Introduction.
 */
package lec01_01;

import java.awt.*;
import java.lang.Iterable;
import java.util.*;

/**
 * Этот контейнер можно использовать для хранения шариков.
 * Данный шар может появиться в контейнередляшариков только один раз.
 */
public class BallContainer implements Iterable<Ball> {

    // Содержимое BallContainer находится в Set-коллекции.
    private Set<Ball> contents;

    /**
     * Конструктор, который создает новый контейнер для шариков.
     */
    public BallContainer() {
        contents = new LinkedHashSet<Ball>();
    }

    /**
     * Реализует итеративный интерфейс для этого контейнера.
     * @возвращает итератор по объектам lec01_01.Ball, содержащимся
     * в этом контейнере
     */
    public Iterator<Ball> iterator() {
        // Если бы мы просто вернули итератор "содержимого", клиент
        // может вызвать метод remove() в итераторе и изменить
        // это за нашими спинами. Вместо этого мы помещаем содержимое в
        // "неизменяемый набор"; вызывая remove() на этом итераторе
        // выдает исключение. Это пример того, как избежать
        // "репрезентативная экспозиция". Вы узнаете больше об этой
        //  концепции позже в курсе.
        return Collections.unmodifiableSet(contents).iterator();
    }

    /**
     * Добавляет шарик в контейнер. Этот метод возвращает <tt>true</tt>
     * если мяч был успешно добавлен в контейнер, т.е. мяч был
     * еще не находится в контейнере. Конечно, вам разрешается ставить
     * lec01_01.Ball помещается в контейнер только один раз. Следовательно, этот метод возвращает
     * <tt>false</tt>, если шарик уже находится в контейнере.
     * @param b ball будет добавлен
     * @возвращает значение true, если мяч был успешно добавлен в контейнер,
     * т.е. мяч еще не был в контейнере. Возвращает значение false, если мяч находится
     * уже в контейнере
     */
    public boolean add(Ball b) {
        // Your code goes here.  Remove the exception after you're done.
        //Добавляем шарик
        //Пишем условие если содержимое уже содержит этот шар, то возврат false
        boolean result;

        if(!this.contents.contains(b)){
            result=true;
            this.contents.add(b);
        }
        else {result=false;}

        return result;
       // throw new RuntimeException("Method not implemented");
    }

    /**
     * Извлекает шарик из контейнера. Этот метод возвращает
     * <tt>true</tt>, если мяч был успешно извлечен из
     контейнера *, т.е. мяч действительно находился в контейнере. Вы не можете
     * извлеките шарик, если он еще не находится в контейнере, поэтому в этом
     * случай, когда метод возвращает <tt>false</tt>.
     * @param b мяч должен быть удален
     * @возвращает значение true, если мяч был успешно извлечен из контейнера,
     * т.е. мяч действительно находился в контейнере. Возвращает значение false, если мяч не
     * в контейнере
     */
    public boolean remove(Ball b) {
        // Your code goes here.  Remove the exception after you're done.

        boolean result;

        if(this.contents.contains(b)){
            result=true;
            this.contents.remove(b);
        }
        else {result=false;}

        return result;


        //throw new RuntimeException("Method not implemented");
    }

    /**
     * Каждый шар имеет свой объем. Этот метод возвращает общий объем
     * всех шариков в контейнере.
     * @возвращает объем содержимого контейнера
     */
    public double getVolume() {
        // Your code goes here.  Remove the exception after you're done.
        //Возвращает суммарный обьем всей коллекции, тоесть вся сумма!!!
        //Проходим по всей коллекции
        double result = 0;

        for(Ball b:contents){
            result+=b.getVolume();
        }
        return result;
        //throw new RuntimeException("Method not implemented");
    }

    /**
     * Возвращает количество шариков в этом контейнере.
     * @возвращает количество шариков в этом контейнере
     */
    public int size() {


        return this.contents.size();
       // throw new RuntimeException("Method not implemented");
    }

    /**
     * Возвращает количество шариков разных цветов в этом контейнере.
     * @возвращает количество шариков разных цветов в этом контейнере
     */
    public int differentColors() {
        // Your code goes here.  Remove the exception after you're done.

        Set<Color> colColor=new HashSet<>();
        for(Ball b : this.contents){
            colColor.add(b.getColor());
        }
        return colColor.size();

       // throw new RuntimeException("Method not implemented");
    }

    /**
     * Возвращает значение true, если все шары в этом контейнере имеют одинаковый цвет,
     * в противном случае возвращает значение false.
     * @возвращает значение true, если все шары в этом контейнере имеют одинаковый цвет,
     * в противном случае возвращает значение false
     */
    public boolean areSameColor() {
        // Your code goes here.  Remove the exception after you're done.
        boolean result=false;
        Set<Color> colColor=new HashSet<>();

        for(Ball b : this.contents){
            colColor.add(b.getColor());
        }

        if((colColor.size()==1) || (this.contents.size()==0)){result=true;}
        else if (colColor.size()!=1){result=false;}

        return result;


       // throw new RuntimeException("Method not implemented");
    }

    /**
     * Опорожняет контейнер, т.е. удаляет все его содержимое.
     */
    public void clear() {
        this.contents.clear();
     //   throw new RuntimeException("Method not implemented");
    }

    /**
     * Этот метод возвращает <tt>true</tt>, если этот контейнер содержит
     * указанный мяч. В противном случае он вернет <tt>false</tt>.
     * @param b шарик, который нужно проверить, находится ли он в контейнере
     * @возвращает значение true, если этот контейнер содержит указанный шар. Возвращается
     * в противном случае значение false.
     */
    public boolean contains(Ball b) {

        return this.contents.contains(b);

      //  throw new RuntimeException("Method not implemented");
    }

}