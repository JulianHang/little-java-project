package tech.zlia.interest.reflection;

import java.lang.reflect.*;

/**
 * 查看类对象的内部结构
 * <p>通过反射的手段
 * @version 2019-1-24
 * @author zlia
 */
public class ReflectionClass {

    /**
     * 接收完全限定名
     * @param name 类的全路径
     */
    public static void  fullNameForClass(String name)throws ClassNotFoundException{
        if(name == null || "".equals(name)){
            System.out.println("不好意思，你的参数有问题！");
        }
        Class clazz = Class.forName(name);
        viewClassStructor(clazz);
    }

    /**
     * 接收对象
     * @param object 对象
     */
    public static void typeForClass(Object object){
        Class clazz = object.getClass();
        viewClassStructor(clazz);
    }

    private static void viewClassStructor(Class clazz){
        viewClass(clazz);
        System.out.print("{\n");
        viewClassFields(clazz);
        System.out.println();
        viewClassConstructors(clazz);
        System.out.println();
        viewClassMethods(clazz);
        System.out.print("\n}");
    }

    /**
     * 查看类的结构
     * @param clazz 类对象
     */
    private static void viewClass(Class clazz){
        Class superClazz = clazz.getSuperclass();
        String modifier = Modifier.toString(clazz.getModifiers()); //访问修饰符
        String className = clazz.getName();

        if(modifier.length() > 0){
            System.out.print(modifier + " ");
        }

        System.out.print("class " + className);

        if(superClazz != null && superClazz != Object.class){
            System.out.print(" extends " + superClazz.getName());
        }
    }

    /**
     * 查看类的成员属性
     * @param clazz 对象
     */
    private static void viewClassFields(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            String modifier = Modifier.toString(field.getModifiers());

            System.out.print("  ");

            if(modifier.length() > 0){
                System.out.print(modifier + " ");
            }

            Class cls = field.getType();
            String name = field.getName();

            System.out.println(cls.getName() + " " + name + ";");
        }
    }

    /**
     * 查看类的构造
     * @param clazz 对象
     */
    private static void viewClassConstructors(Class clazz){
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            String modifier = Modifier.toString(constructor.getModifiers());
            String name = constructor.getName();

            System.out.print("  ");

            if(modifier.length() > 0){
                System.out.print(modifier + " ");
            }

            System.out.print(name + "(");

            Class[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0 ; i < parameterTypes.length ; i ++){
                if(i > 0 ){
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }

            System.out.println(");");
        }

    }

    /**
     * 查看类的方法
     * @param clazz 对象
     */
    private static void viewClassMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            String modifier = Modifier.toString(method.getModifiers());

            System.out.print("  ");

            if(modifier.length() > 0){
                System.out.print(modifier + " ");
            }

            Class returnCls = method.getReturnType();
            String name = method.getName();

            System.out.print(returnCls.getName() + " " + name + "(");

            Class[] parameterTypes = method.getParameterTypes();
            for (int i = 0 ; i < parameterTypes.length ; i ++){
                if(i > 0 ){
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

}
