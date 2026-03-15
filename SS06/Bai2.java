import java.util.*;

public class Bai2{

    static class Ticket{
        String id;
        Ticket(String id){this.id=id;}
        public String toString(){return id;}
    }

    static class TicketPool{

        String room;
        Queue<Ticket>tickets=new LinkedList<>();
        int counter=1;

        TicketPool(String room,int amount){
            this.room=room;
            addTickets(amount);
        }

        public synchronized Ticket sellTicket(){

            while(tickets.isEmpty()){
                try{
                    System.out.println(Thread.currentThread().getName()+": Hết vé phòng "+room+", đang chờ...");
                    wait();
                }catch(Exception e){}
            }

            Ticket t=tickets.poll();

            System.out.println(Thread.currentThread().getName()+" bán vé "+t);

            return t;
        }

        public synchronized void addTickets(int amount){

            for(int i=0;i<amount;i++){
                tickets.add(new Ticket(room+"-"+String.format("%03d",counter++)));
            }

            System.out.println("Nhà cung cấp: Đã thêm "+amount+" vé vào phòng "+room);

            notifyAll();
        }

    }

    static class BookingCounter implements Runnable{

        TicketPool pool;

        BookingCounter(TicketPool pool){
            this.pool=pool;
        }

        public void run(){

            while(true){

                pool.sellTicket();

                try{
                    Thread.sleep(500);
                }catch(Exception e){}

            }

        }

    }

    static class Supplier implements Runnable{

        TicketPool pool;

        Supplier(TicketPool pool){
            this.pool=pool;
        }

        public void run(){

            try{

                Thread.sleep(5000);

                pool.addTickets(3);

            }catch(Exception e){}

        }

    }

    public static void main(String[]args){

        TicketPool roomA=new TicketPool("A",3);
        TicketPool roomB=new TicketPool("B",5);

        Thread counter1=new Thread(new BookingCounter(roomA),"Quầy 1");
        Thread counter2=new Thread(new BookingCounter(roomB),"Quầy 2");

        Thread supplier=new Thread(new Supplier(roomA));

        counter1.start();
        counter2.start();
        supplier.start();

    }

}