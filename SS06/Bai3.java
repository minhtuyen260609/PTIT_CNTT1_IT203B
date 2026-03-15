import java.util.*;

public class Bai3{

    static class Ticket{
        String id;
        Ticket(String id){this.id=id;}
        public String toString(){return id;}
    }

    static class TicketPool{

        String room;
        Queue<Ticket>tickets=new LinkedList<>();
        int counter=1;

        TicketPool(String room,int initial){
            this.room=room;
            addTickets(initial);
        }

        public synchronized Ticket sellTicket(){

            if(tickets.isEmpty()) return null;

            return tickets.poll();
        }

        public synchronized void addTickets(int count){

            for(int i=0;i<count;i++){
                tickets.add(new Ticket(room+"-"+String.format("%03d",counter++)));
            }

            System.out.println("Nhà cung cấp: Đã thêm "+count+" vé vào phòng "+room);
        }

        public synchronized int remainingTickets(){
            return tickets.size();
        }

    }

    static class BookingCounter implements Runnable{

        String name;
        TicketPool pool;
        int sold=0;

        BookingCounter(String name,TicketPool pool){
            this.name=name;
            this.pool=pool;
        }

        public void run(){

            while(true){

                Ticket t=pool.sellTicket();

                if(t!=null){
                    sold++;
                    System.out.println(name+" đã bán vé "+t);
                }else{
                    try{Thread.sleep(500);}catch(Exception e){}
                }

            }

        }

    }

    static class TicketSupplier implements Runnable{

        TicketPool roomA;
        TicketPool roomB;
        int supplyCount;
        int interval;
        int rounds;

        TicketSupplier(TicketPool a,TicketPool b,int supplyCount,int interval,int rounds){
            roomA=a;
            roomB=b;
            this.supplyCount=supplyCount;
            this.interval=interval;
            this.rounds=rounds;
        }

        public void run(){

            for(int i=0;i<rounds;i++){

                try{Thread.sleep(interval);}catch(Exception e){}

                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);

            }

        }

    }

    public static void main(String[]args)throws Exception{

        TicketPool roomA=new TicketPool("A",5);
        TicketPool roomB=new TicketPool("B",5);

        BookingCounter c1=new BookingCounter("Quầy 1",roomA);
        BookingCounter c2=new BookingCounter("Quầy 2",roomB);

        Thread counter1=new Thread(c1);
        Thread counter2=new Thread(c2);

        TicketSupplier supplier=new TicketSupplier(roomA,roomB,3,3000,3);
        Thread supplierThread=new Thread(supplier);

        counter1.start();
        counter2.start();
        supplierThread.start();

        Thread.sleep(12000);

        System.out.println("\n--- KẾT QUẢ ---");
        System.out.println("Quầy 1 bán được: "+c1.sold+" vé");
        System.out.println("Quầy 2 bán được: "+c2.sold+" vé");
        System.out.println("Vé còn lại phòng A: "+roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: "+roomB.remainingTickets());

        System.exit(0);

    }

}