package GiovanChristoffelSihombingJBusRS;

import java.util.ArrayList;

/**
 * Write a description of class Validate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Validate
{
    public static ArrayList<Double> filter(Price[] list, int value, boolean less){
        // jika nilai
        // Boolean less bernilai true maka untuk setiap price yang kurang dari sama dengan
        // value akan di-return Arraylist yang berisi nilai nilai tersebut. Sebaliknya jika nilai
        // Boolean less bernilai false maka akan di-return Arraylist yang berisi price yang
        // nilainya lebih dari nilai value.

        ArrayList<Double> result = new ArrayList<Double>();
//        if(less == true){
//            for(int i = 0; i < list.length; i++){
//                if(list[i].price <= value){
//                    result.add(list[i].price);
//                }
//            }
//        }else{
//            for(int i = 0; i < list.length; i++){
//                if(list[i].price > value){
//                    result.add(list[i].price);
//                }
//            }
//        }

        for(Price price : list){
            if(less && price.price <= value){
                result.add(price.price);
            } else if (!less && price.price >  value){
                result.add(price.price);
            }
        }
        return result;
    }
}
