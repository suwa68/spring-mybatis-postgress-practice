package alpha.gewei_alp_week1_spring.excercise3.utils;

import java.util.ArrayList;
import java.util.List;

//用來切割 List
public class ListUtils<T> {

    public static <T> List<List<T>>
    splitListBycapacity(List<T> source, int capacity) {

        List<List<T>> result = new ArrayList<List<T>>();

        if (source != null) {
            int size = source.size();
            if (size > 0) {
                for (int i = 0; i < size; ) {

                    List<T> value = null;
                    int end = i + capacity;
                    //最後 1 round
                    if (end > size) {
                        end = size;
                    }
                    value = source.subList(i, end);
                    i = size;
                    result.add(value);
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return result;
    }

}
