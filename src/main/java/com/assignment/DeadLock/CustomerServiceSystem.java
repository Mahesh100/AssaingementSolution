package com.assignment.DeadLock;

/*
 - Here we have two threads, one thread tries to update an order and then adjust the inventory,
    while another thread tries to adjust the inventory first and then update the order.
   This can lead to a deadlock situation.

 */

public class CustomerServiceSystem
{
    public static void main( String[] args ) {

            final Order order = new Order("Order1");
            final Inventory inventory = new Inventory("Item1");

            // Here Thread 1 locks Order then tries to lock Inventory
            Thread thread1 = new Thread(() -> {
                synchronized (order) {
                    System.out.println(Thread.currentThread().getName() + "locked " + order.getId());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (inventory) {
                        System.out.println(Thread.currentThread().getName() + " locked" + inventory.getItem());
                    }
                }
            });

            // now here thread 2 locks Inventory then tries to lock Order
            Thread thread2 = new Thread(() -> {
                synchronized (inventory) {
                    System.out.println(Thread.currentThread().getName() + "locked " + inventory.getItem());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (order) {
                        System.out.println(Thread.currentThread().getName() + "locked" + order.getId());
                    }
                }
            });

            thread1.start();
            thread2.start();
    }
}
