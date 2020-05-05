package com.qi.datastructure.annotation;


import com.qi.datastructure.proxy.ITest;
import com.qi.datastructure.proxy.TestImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

/**
 * Created by feng on 2020/4/8.
 */
@AnnotationStudy(value = "AnnotationTest")
public class AnnotationTest<T> implements InnerInterFace {
    AnnotationTest<String>[] genericArray;
    List<? extends AnnotationTest> wildcardType;
    Map<? extends AnnotationTest, ? super AnnotationTest> wildcardType2;
    Inner<AnnotationTest, T> inner;

    @Override
    public void innerInterFace() {

    }

    static class Inner<T extends AnnotationTest & InnerInterFace, V> {
        T get(T t, AnnotationTest annotationTest) {
            return t;
        }
    }


    @AnnotationStudy(value = "annotationTestMethod")
    public Map.Entry<String, Inner> annotationTestMethod(@AnnotationStudy(value = "AnnotationTestParameter") List<String> array, int index) {
        return null;
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        annotationTestClass.getGenericInterfaces();
        annotationTestClass.getDeclaredFields();
        try {
            Method annotationTestMethod = annotationTestClass.getMethod("annotationTestMethod", List.class, int.class);
            AnnotationStudy annotation = annotationTestMethod.getAnnotation(AnnotationStudy.class);
            System.out.println(annotation.value() + "," + annotation.annotationType());

            /***
             * method
             * 方法里的参数类型
             * getGenericParameterTypes()
             * getParameterTypes()
             */
            Type[] genericParameterTypes = annotationTestMethod.getGenericParameterTypes();
            for (Type type : genericParameterTypes) {
                System.out.println("method---getGenericParameterTypes:" + type);
            }
            System.out.println();

            Class<?>[] parameterTypes = annotationTestMethod.getParameterTypes();
            for (Type type : parameterTypes) {
                System.out.println("method---getParameterTypes:" + type);
            }
            System.out.println();
            /**
             * 方法里的参数注解
             * 因为每个字段可能有多个注解，所以返回二维数组
             */
            Annotation[][] parameterAnnotations = annotationTestMethod.getParameterAnnotations();
            for (Annotation[] annotations : parameterAnnotations) {
                for (Annotation annotation1 : annotations) {
                    System.out.println("Method---getParameterAnnotations:" + annotation1);
                }
            }
            System.out.println();
            /**
             * method
             * getGenericReturnType():返回方法的返回值类型(如果返回值类型是参数化类型则是这样的O<T> -->O<T>,否则和getReturnType()的结果是一样的)
             * getReturnType():返回方法的返回值类型(是Type的子类Class，不带泛型属性) O<T> -->O</>
             *
             * Field(同上)
             * getGenericType():
             * getType():
             *
             */
            Type genericReturnType = annotationTestMethod.getGenericReturnType();
            System.out.println("Method---genericReturnType=" + genericReturnType);
            Class<?> returnType = annotationTestMethod.getReturnType();
            System.out.println("Method---returnType=" + returnType);
            System.out.println();
/***
 *
 */
            TypeVariable<Method>[] typeParameters = annotationTestMethod.getTypeParameters();
            for (TypeVariable type : typeParameters) {
                System.out.println("method---getTypeParameters:" + type);
            }
            System.out.println();

            /**
             * 参数化类型 ParameterizedType
             * getActualTypeArguments():返回参数化类型变量的参数的类型数组
             * getRawType():返回参数化类型变量的类型
             * getOwnerType():返回参数化类型中的外部类的类型
             */
            //带有泛型的参数Object<T,M>,具有<>符号的变量是参数化类型
            if (genericReturnType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
                //参数化类型变量的类型：Object<M> ->Object
                Type rawType = parameterizedType.getRawType();
                System.out.println("parameterizedType---getRawType=" + rawType);
                //参数化类型中的外部类的类型
                Type ownerType = parameterizedType.getOwnerType();
                System.out.println("parameterizedTyp---getOwnerType=" + ownerType);
                //参数化类型Object<T,M>里面的的泛型类型的数组[T,M], 返回了一个Type数组,数组里是参数化类型的参数
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type type : actualTypeArguments) {
                    System.out.println("parameterizedTyp---actualTypeArguments=" + type);
                }
                System.out.println();

                /**
                 *参数化数组类型 genericArray
                 * O<T>[]
                 * getGenericComponentType():返回的是参数化类型
                 */
                Field genericArray = annotationTestClass.getDeclaredField("genericArray");
                Type genericType = genericArray.getGenericType();
                System.out.println("GenericArrayType---genericType is ParameterizedType :" + (genericType instanceof ParameterizedType) + ",genericType is GenericArrayType: " + (genericType instanceof GenericArrayType));//FALSE,true
                Type genericComponentType = ((GenericArrayType) genericType).getGenericComponentType();
                System.out.println("GenericArrayType---genericComponentType is ParameterizedType " + (genericComponentType instanceof ParameterizedType));//FALSE,true
                System.out.println();

                /**
                 *   List<? extends AnnotationTest> wildcardType;
                 * 参数化界限类型 WildcardType
                 */

                Field wildcardType = annotationTestClass.getDeclaredField("wildcardType");
                Type genericType1 = wildcardType.getGenericType();
                Type[] actualTypeArguments1 = ((ParameterizedType) genericType1).getActualTypeArguments();
                Type type = actualTypeArguments1[0];
                System.out.println("wildcardType---type is WildcardType :" + (type instanceof WildcardType));//true
                Type[] lowerBounds = ((WildcardType) type).getLowerBounds();
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                System.out.println("wildcardType---lowerBounds.length=" + lowerBounds.length + ",upperBounds.length=" + upperBounds.length);
                Type upperBound = upperBounds[0];
                System.out.println("wildcardType---upperBound=" + upperBound);
                //wildcardType---upperBound=class com.qi.datastructure.annotation.AnnotationTest 根据打印的结果可知能得到上限的具体的类型
                /**
                 * 参数化界限类型 WildcardType
                 *  Map<? extends AnnotationTest, ? super AnnotationTest> wildcardType2;
                 */

                Field wildcardType2 = annotationTestClass.getDeclaredField("wildcardType2");
                Type genericType2 = wildcardType2.getGenericType();
                Type[] actualTypeArguments2 = ((ParameterizedType) genericType2).getActualTypeArguments();
                WildcardType wildcardType1 = (WildcardType) actualTypeArguments2[1];
                Type[] lowerBounds1 = wildcardType1.getLowerBounds();
                Type[] upperBounds1 = wildcardType1.getUpperBounds();
                System.out.println("wildcardType---lowerBounds1=" + lowerBounds1[0]);
                System.out.println();
                /**
                 * TypeVariable
                 *
                 * Type的最后一种实现形式是TypeVariable接口，这种实现形式是在泛型类中使用的。
                 * 比如我们定义一个泛型类TestReflect<T>，并在类中定义方法oneMethod(T para)，
                 * 那么当调用method.getGenericParameterTypes()方法得到的Type数组，
                 * 数组的元素就是由TypeVariable接口实现的。
                 * 原文链接：https://blog.csdn.net/lkforce/java/article/details/82466893
                 *
                 */
                TypeVariable<Class<Inner>>[] typeParameters1 = Inner.class.getTypeParameters();
                for (TypeVariable t1 : typeParameters1) {
                    //获得该类型变量的上限（上边界）(可能会有多个,第一个后面一定是接口类型)
                    Type[] bounds = t1.getBounds();
                    for (Type b : bounds) {
                        System.out.println("bounds.length=" + bounds.length + ",bound=" + b);
                    }
                    //获得声明这个类型变量的类型(Class，Method ，Constructor)
                    GenericDeclaration genericDeclaration = t1.getGenericDeclaration();
                    System.out.println("genericDeclaration=" + genericDeclaration.toString());

                    //类型变量的名称
                    String name = t1.getName();
                    System.out.println("TypeVariable---name=" + name);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
