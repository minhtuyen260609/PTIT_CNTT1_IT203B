import java.util.*;

public class Bai4{

    static class Ticket{
        String ticketId;
        String roomName;
        boolean isSold;

        Ticket(String ticketId,String roomName){
            this.ticketId=ticketId;
            this.roomName=roomName;
            this.isSold=false;
        }

        public String toString(){
            return ticketId;
        }
    }

    static class TicketPool{

        String roomName;
        List<Ticket>tickets=new ArrayList<>();

        TicketPool(String roomName,int count){
            this.roomName=roomName;
            for(int i=1;i<=count;i++){
                tickets.add(new Ticket(roomName+"-"+String.format("%03d",i),roomName));
            }
        }

        public synchronized Ticket sellTicket(){

            for(Ticket t:tickets){
                if(!t.isSold){
                    t.isSold=true;
                    return t;
                }
            }

            return null;
        }

        public synchronized int remainingTickets(){

            int c=0;
            for(Ticket t:tickets){
                if(!t.isSold)c++;
            }
            return c;
        }

    }

    static class BookingCounter implements Runnable{

        String counterName;
        TicketPool roomA;
        TicketPool roomB;
        int soldCount=0;
        Random rand=new Random();

        BookingCounter(String name,TicketPool a,TicketPool b){
            counterName=name;
            roomA=a;
            roomB=b;
        }

        public void run(){

            while(true){

                if(roomA.remainingTickets()==0 && roomB.remainingTickets()==0)break;

                Ticket ticket;

                if(rand.nextBoolean()){
                    ticket=roomA.sellTicket();
                }else{
                    ticket=roomB.sellTicket();
                }

                if(ticket!=null){
                    soldCount++;
                    System.out.println(counterName+" đã bán vé "+ticket.ticketId);
                }

                try{Thread.sleep(100);}catch(Exception e){}

            }

        }

    }

    public static void main(String[]args)throws Exception{

        TicketPool roomA=new TicketPool("A",10);
        TicketPool roomB=new TicketPool("B",10);

        BookingCounter c1=new BookingCounter("Quầy 1",roomA,roomB);
        BookingCounter c2=new BookingCounter("Quầy 2",roomA,roomB);

        Thread t1=new Thread(c1);
        Thread t2=new Thread(c2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\n--- KẾT QUẢ ---");

        System.out.println("Quầy 1 bán được: "+c1.soldCount+" vé");
        System.out.println("Quầy 2 bán được: "+c2.soldCount+" vé");

        System.out.println("Vé còn lại phòng A: "+roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: "+roomB.remainingTickets());

    }

}