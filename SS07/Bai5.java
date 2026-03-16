import java.util.*;

public class Bai5{

    static class Ticket{
        String ticketId;
        String roomName;
        boolean isHeld=false;
        boolean isSold=false;
        boolean isVIP=false;
        long holdExpiryTime=0;
        String heldBy=null;

        Ticket(String id,String room){
            ticketId=id;
            roomName=room;
        }

        public String toString(){
            return ticketId;
        }
    }

    static class TicketPool{

        String roomName;
        List<Ticket>tickets=new ArrayList<>();

        TicketPool(String room,int capacity){
            roomName=room;
            for(int i=1;i<=capacity;i++){
                tickets.add(new Ticket(room+"-"+String.format("%03d",i),room));
            }
        }

        public synchronized Ticket holdTicket(String counter,boolean vip){

            for(Ticket t:tickets){

                if(!t.isSold && !t.isHeld){

                    t.isHeld=true;
                    t.isVIP=vip;
                    t.heldBy=counter;
                    t.holdExpiryTime=System.currentTimeMillis()+5000;

                    System.out.println(counter+": Đã giữ vé "+t.ticketId+(vip?" (VIP)":"")+". Vui lòng thanh toán trong 5s");

                    return t;
                }

            }

            System.out.println(counter+": Không còn vé khả dụng ở phòng "+roomName);
            return null;
        }

        public synchronized void sellHeldTicket(Ticket t,String counter){

            if(t==null)return;

            if(t.isHeld && !t.isSold && t.heldBy.equals(counter)){

                t.isSold=true;
                t.isHeld=false;

                System.out.println(counter+": Thanh toán thành công vé "+t.ticketId);

            }
        }

        public synchronized void releaseExpiredTickets(){

            long now=System.currentTimeMillis();

            for(Ticket t:tickets){

                if(t.isHeld && !t.isSold && now>t.holdExpiryTime){

                    System.out.println("TimeoutManager: Vé "+t.ticketId+" hết hạn giữ, đã trả lại kho");

                    t.isHeld=false;
                    t.heldBy=null;

                }

            }

        }

    }

    static class BookingCounter implements Runnable{

        String name;
        List<TicketPool>pools;
        Random rand=new Random();

        BookingCounter(String name,List<TicketPool>pools){
            this.name=name;
            this.pools=pools;
        }

        public void run(){

            while(true){

                boolean vip=rand.nextInt(4)==0;

                TicketPool pool;

                if(vip){
                    pool=pools.get(rand.nextInt(pools.size()));
                }else{
                    pool=pools.get(rand.nextInt(pools.size()));
                }

                Ticket t=pool.holdTicket(name,vip);

                try{Thread.sleep(3000);}catch(Exception e){}

                if(t!=null){
                    pool.sellHeldTicket(t,name);
                }

                try{Thread.sleep(1000);}catch(Exception e){}

            }

        }

    }

    static class TimeoutManager implements Runnable{

        List<TicketPool>pools;

        TimeoutManager(List<TicketPool>pools){
            this.pools=pools;
        }

        public void run(){

            while(true){

                for(TicketPool p:pools){
                    p.releaseExpiredTickets();
                }

                try{Thread.sleep(1000);}catch(Exception e){}

            }

        }

    }

    public static void main(String[]args){

        TicketPool roomA=new TicketPool("A",5);
        TicketPool roomB=new TicketPool("B",6);
        TicketPool roomC=new TicketPool("C",7);

        List<TicketPool>pools=Arrays.asList(roomA,roomB,roomC);

        Thread timeout=new Thread(new TimeoutManager(pools));
        timeout.start();

        for(int i=1;i<=5;i++){
            Thread t=new Thread(new BookingCounter("Quầy "+i,pools));
            t.start();
        }

    }

}