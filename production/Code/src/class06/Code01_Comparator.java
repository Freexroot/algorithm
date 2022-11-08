package class06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Code01_Comparator {
    public static class User {
        public User(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        public int id;
        public int age;
        public String name;
    }

    // 任何比较器：
    // compare方法里，遵循一个统一的规范：
    // 返回负数的时候，认为第一个参数应该排在前面
    // 返回正数的时候，认为第二个参数应该排在前面
    // 返回0的时候，认为无所谓谁放前面
    // id升序，如果相等，age降序
    public static class IdShengAgeJiangOrder implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
/*            if (o1.id > o2.id) {
                return -1;
            } else if (o1.id < o2.id) {
                return 1;
            } else {
                if (o1.age > o2.age) {
                    return 1;
                }else if (o1.age < o2.age){
                    return -1;
                }
                return 0;
            }*/

            return o2.id!=o1.id ? (o2.id - o1.id):(o1.age-o2.age);
        }
    }

    public static void main(String[] args) {
        //方式一
        System.out.println("方式一，用类数组比较器");
        User [] users=new User[5];
        users[0]=(new User(1, 3, "A"));
        users[1]=(new User(2, 2, "B"));
        users[2]=(new User(3, 1, "C"));
        users[3]=(new User(3, 4, "C"));
        users[4]=(new User(3, 0, "C"));

        Arrays.sort(users, new IdShengAgeJiangOrder());

        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }

        //方式二
        System.out.println("方式二，用集合比较器");
        List<User> list = new ArrayList<>();
        list.add(new User(1, 3, "A"));
        list.add(new User(2, 2, "B"));
        list.add(new User(3, 1, "C"));
        list.add(new User(3, 4, "C"));
        list.add(new User(3, 0, "C"));

        list.sort(new IdShengAgeJiangOrder());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
