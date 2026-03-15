import java.util.*;

public class Bai1{

    static class Ticket{
        String id;
        Ticket(String id){this.id=id;}
        public String toString(){return id;}
    }

    static class TicketPool{
        String room;
        Queue<Ticket>tickets=new LinkedList<>();

        TicketPool(String room,int amount){
            this.room=room;
            for(int i=1;i<=amount;i++){
                tickets.add(new Ticket(room+"-"+String.format("%03d",i)));
            }
        }

        Ticket getTicket(){
            return tickets.poll();
        }

        void returnTicket(Ticket t){
            if(t!=null)tickets.add(t);
        }
    }

    static class BookingCounter implements Runnable{

        String name;
        TicketPool roomA;
        TicketPool roomB;
        boolean reverse;

        BookingCounter(String name,TicketPool a,TicketPool b,boolean reverse){
            this.name=name;
            roomA=a;
            roomB=b;
            this.reverse=reverse;
        }

        public void run(){
            while(true){
                sellCombo();
                try{Thread.sleep(500);}catch(Exception e){}
            }
        }

        void sellCombo(){

            TicketPool first=reverse?roomB:roomA;
            TicketPool second=reverse?roomA:roomB;

            synchronized(first){

                Ticket t1=first.getTicket();

                if(t1==null){
                    System.out.println(name+": hết vé "+first.room);
                    return;
                }

                System.out.println(name+": đã lấy "+t1);

                try{Thread.sleep(200);}catch(Exception e){}

                synchronized(second){

                    Ticket t2=second.getTicket();

                    if(t2==null){
                        first.returnTicket(t1);
                        System.out.println(name+": hết vé "+second.room+" -> trả lại "+t1);
                        return;
                    }

                    System.out.println(name+" bán combo thành công: "+t1+" & "+t2);

                }

            }

        }

    }

    public static void main(String[]args){

        TicketPool roomA=new TicketPool("A",2);
        TicketPool roomB=new TicketPool("B",2);

        Thread counter1=new Thread(new BookingCounter("Quầy 1",roomA,roomB,false));
        Thread counter2=new Thread(new BookingCounter("Quầy 2",roomA,roomB,true));

        counter1.start();
        counter2.start();

    }

}