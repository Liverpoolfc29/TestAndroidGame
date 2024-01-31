package com.example.myframework.Utilits;

import com.example.myframework.ObjectGameFW;

public class CollisionDetect {
    private static double object1X;
    private static double object1Y;
    private static double object2X;
    private static double object2Y;
    private static double radiusObject1;
    private static double radiusObject2;
    private static double dx;
    private static double dy;
    private static double distanceObject;

    public static boolean collisionDetect(ObjectGameFW object1, ObjectGameFW object2) {
        /*
            Получаем координаты обьектов (их центральные точки) и  потом получаем от точек радиусы и потом
            делаем проверку на столкновение с помощью растояния между обьектами, если дистанция меньше
            суммы двух радиусов значит столкновение.
         */
        object1X = object1.getHitBox().centerX();
        object1Y = object1.getHitBox().centerY();

        object2X = object2.getHitBox().centerX();
        object2Y = object2.getHitBox().centerY();

        radiusObject1 = object1.getRadius();
        radiusObject2 = object2.getRadius();

        dx = object1X - object2X;
        dy = object1Y - object2Y;

        distanceObject = Math.sqrt(dx * dx + dy * dy);
        if (distanceObject < (radiusObject1 + radiusObject2)) {
            return true;
        }
        return false;
    }

}