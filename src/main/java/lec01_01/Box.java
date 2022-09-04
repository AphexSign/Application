/**
 * This is part of Lec01_01: Environment Setup and Java Introduction.
 */
package lec01_01;

import java.lang.Iterable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Это контейнер, который можно использовать для хранения шариков. Ключевая
 * разница между BallContainer и Box заключается в том, что Box имеет
 * конечный объем. Как только коробка заполняется, клиент не может положить больше мячей.
 */
public class Box implements Iterable<Ball> {

    /**
     * ballContainer используется для внутреннего хранения шариков для этой коробки
     */
    private BallContainer ballContainer;
    private double maxVolume;

    /**
     * Конструктор, который создает новое поле.
     * @param maxVolume Общий объем шариков, который может содержать это поле.
     */
    public Box(double maxVolume) {
        // Your code goes here.  Remove the exception after you're done.
        ballContainer=new BallContainer();
        this.maxVolume=maxVolume;
        //throw new RuntimeException("Method not implemented");
    }

    /**
     * Реализует итеративный интерфейс для этого поля.
     * @возвращает итератор по объектам lec01_01.Ball, содержащимся
     * в этом поле.
     */
    public Iterator<Ball> iterator() {
        return ballContainer.iterator();
    }


    /**
     * Этот метод используется для добавления объектов lec01_01.Ball в это поле
     * конечного объема. Метод возвращает значение true, если мяч находится
     * успешно добавлен в поле, т.е. мяч еще не находится в поле
     * и если поле еще не заполнено; и возвращает значение false,
     * если мяч уже находится в поле или если поле слишком заполнено
     * чтобы подогнать новый мяч.
     * @param - Мяч должен быть добавлен.
     * @возвращает значение true, если мяч был успешно добавлен в поле,
     * т.е. мяч еще не находится в поле, и если поле не
     * уже заполнен. Возвращает значение false, если мяч уже находится в штрафной или
     * если коробка слишком полна, чтобы вместить новый мяч.
     */
    public boolean add(Ball b) {
        // Your code goes here.  Remove the exception after you're done.

        if((getVolume()+b.getVolume()>maxVolume) || contains(b)){
            return false;
        } else {return ballContainer.add(b);}


        //throw new RuntimeException("Method not implemented");
    }

    /**
     * Этот метод возвращает итератор, который перебирает все шары в
     * этом поле в порядке возрастания их размера, т.е. сначала возвращает
     * наименьший lec01_01.Ball, за которым следуют шары увеличивающегося размера.
     * @return итератор, который возвращает все шары в этом поле в
     * порядок возрастания по lec01_01.Размер шара.
     */
    public Iterator<Ball> getBallsFromSmallest() {
        // Your code goes here.  Remove the exception after you're done.
        //Создаем дерево в котором будет хранится наше множество, в порядке возрастания
        //Напишем компаратор для правильной работы

        TreeSet<Ball> treeBall=new TreeSet<>(new Comparator<Ball>() {
            @Override
            public int compare(Ball o1, Ball o2) {
                return Double.compare(o1.getVolume(),o2.getVolume());
            }
        });
        //Компаратор для перебора нашей коллекции
        Iterator<Ball> itBall=iterator();
        //Передача в TreeSet - нашей случайно коллекции
        while (itBall.hasNext()){
            treeBall.add(itBall.next());
        }
        return treeBall.iterator();

        //throw new RuntimeException("Method not implemented");
    }

    /**
     * Вынимает мяч из коробки. Этот метод возвращает
     * <tt>true</tt>, если мяч был успешно извлечен из
     контейнера *, т.е. мяч действительно находился в коробке. Вы не можете
     * удалите lec01_01.Ball, если его еще нет в коробке, поэтому в этом
     * случай, когда метод возвращает <tt>false</tt>.
     * @param -b_01_01.Мяч должен быть удален.
     * @возвращает значение true, если мяч был успешно удален из коробки,
     * т.е. мяч действительно находился в коробке. Возвращает значение false, если мяч не
     * в коробке.
     */
    public boolean remove(Ball b) {
        return ballContainer.remove(b);
    }

    /**
     * Каждый lec01_01.Ball имеет объем. Этот метод возвращает общий объем
     * всех шаров в коробке.
     * @возвращает объем содержимого коробки.
     */
    public double getVolume() {
        return ballContainer.getVolume();
    }

    /**
     * Возвращает количество шаров в этом поле.
     * @возвращает количество шаров в этом поле.
     */
    public int size() {
        return ballContainer.size();
    }

    /**
     * Опустошает коробку, т.е. удаляет все ее содержимое.
     */
    public void clear() {
        ballContainer.clear();
    }

    /**
     * Этот метод возвращает <tt>true</tt>, если это поле содержит
     * указанный lec01_01.Ball. В противном случае он возвращает <tt>false</tt>.
     * @param - b_01_01.Мяч должен быть проверен, если он находится в поле
     * @возвращает значение true, если это поле содержит указанный lec01_01.Ball. Возвращается
     * в противном случае значение false.
     */
    public boolean contains(Ball b) {
        return ballContainer.contains(b);
    }

}
