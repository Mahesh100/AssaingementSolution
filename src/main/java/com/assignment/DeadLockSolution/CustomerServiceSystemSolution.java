package com.assignment.DeadLockSolution;

import com.assignment.DeadLock.Inventory;
import com.assignment.DeadLock.Order;


/* Explanation:
   To solve the dead lock we have to make sure that the bothe the threads should acquire lock in same order.
   In this solution both threads lock the resources in the same order (first Order and then Inventory).
 */

public class CustomerServiceSystemSolution {
    public static void main(String[] args) {
        final Order order = new Order("Order1");
        final Inventory inventory = new Inventory("Item1");

        Thread thread1 = new Thread(() -> {
            synchronized (order) {
                System.out.println(Thread.currentThread().getName() + " locked " + order.getId());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (inventory) {
                    System.out.println(Thread.currentThread().getName() + " locked " + inventory.getItem());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (order) {
                System.out.println(Thread.currentThread().getName() + "locked " + order.getId());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (inventory) {
                    System.out.println(Thread.currentThread().getName() + " locked " + inventory.getItem());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
