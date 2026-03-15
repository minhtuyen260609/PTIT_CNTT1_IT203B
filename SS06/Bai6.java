import java.util.*;
import java.util.concurrent.*;
import java.lang.management.*;

public class Bai6{

    static class Ticket{
        String id;
        boolean sold=false;
        Ticket(String id){this.id=id;}
    }

    static class TicketPool{
        String roomName;
        List<Ticket>tickets=new ArrayList<>();
        int price=250000;

        TicketPool(String name,int count){
            roomName=name;
            for(int i=1;i<=count;i++){
                tickets.add(new Ticket(name+"-"+String.format("%03d",i)));
            }
        }

        public synchronized Ticket sellTicket(){
            for(Ticket t:tickets){
                if(!t.sold){
                    t.sold=true;
                    return t;
                }
            }
            return null;
        }

        public synchronized void addTickets(int n){
            int start=tickets.size()+1;
            for(int i=0;i<n;i++){
                tickets.add(new Ticket(roomName+"-"+String.format("%03d",start+i)));
            }
            System.out.println("Đã thêm "+n+" vé vào phòng "+roomName);
        }

        public synchronized int soldCount(){
            int c=0;
            for(Ticket t:tickets)if(t.sold)c++;
            return c;
        }

        public synchronized int total(){
            return tickets.size();
        }

        public synchronized int remaining(){
            return total()-soldCount();
        }
    }

    static class BookingCounter implements Runnable{

        String name;
        List<TicketPool>pools;
        Random rand=new Random();
        volatile boolean running=true;
        volatile boolean paused=false;

        BookingCounter(String name,List<TicketPool>pools){
            this.name=name;
            this.pools=pools;
        }

        public void run(){

            System.out.println(name+" bắt đầu bán vé...");

            while(running){

                if(paused){
                    try{Thread.sleep(200);}catch(Exception e){}
                    continue;
                }

                TicketPool pool=pools.get(rand.nextInt(pools.size()));

                Ticket t=pool.sellTicket();

                if(t!=null){
                    System.out.println(name+" đã bán vé "+t.id);
                }

                try{Thread.sleep(300);}catch(Exception e){}

            }

        }

    }

    static class DeadlockDetector implements Runnable{

        public void run(){

            ThreadMXBean bean=ManagementFactory.getThreadMXBean();

            long[]ids=bean.findDeadlockedThreads();

            System.out.println("Đang quét deadlock...");

            if(ids==null){
                System.out.println("Không phát hiện deadlock.");
            }else{
                System.out.println("Phát hiện deadlock giữa các thread:");
                ThreadInfo[]infos=bean.getThreadInfo(ids);
                for(ThreadInfo info:infos){
                    System.out.println(info.getThreadName());
                }
            }

        }

    }

    static class CinemaManager{

        List<TicketPool>pools=new ArrayList<>();
        List<BookingCounter>counters=new ArrayList<>();
        ExecutorService executor;
        boolean started=false;

        void startSimulation(int rooms,int tickets,int countersNum){

            pools.clear();
            counters.clear();

            for(int i=0;i<rooms;i++){
                char room=(char)('A'+i);
                pools.add(new TicketPool(""+room,tickets));
            }

            executor=Executors.newFixedThreadPool(countersNum);

            for(int i=1;i<=countersNum;i++){
                BookingCounter c=new BookingCounter("Quầy "+i,pools);
                counters.add(c);
                executor.submit(c);
            }

            started=true;

            System.out.println("Đã khởi tạo hệ thống với "+rooms+" phòng, "+(rooms*tickets)+" vé, "+countersNum+" quầy");
        }

        void pause(){
            for(BookingCounter c:counters)c.paused=true;
            System.out.println("Đã tạm dừng tất cả quầy bán vé.");
        }

        void resume(){
            for(BookingCounter c:counters)c.paused=false;
            System.out.println("Đã tiếp tục hoạt động.");
        }

        void addTickets(String room,int count){
            for(TicketPool p:pools){
                if(p.roomName.equals(room)){
                    p.addTickets(count);
                    return;
                }
            }
        }

        void statistics(){

            System.out.println("=== THỐNG KÊ HIỆN TẠI ===");

            int revenue=0;

            for(TicketPool p:pools){

                int sold=p.soldCount();
                int total=p.total();

                System.out.println("Phòng "+p.roomName+": Đã bán "+sold+"/"+total+" vé");

                revenue+=sold*p.price;
            }

            System.out.println("Tổng doanh thu: "+revenue+" VNĐ");
        }

        void detectDeadlock(){
            new DeadlockDetector().run();
        }

        void shutdown(){

            for(BookingCounter c:counters)c.running=false;

            executor.shutdownNow();

            System.out.println("Đang dừng hệ thống...");
        }

    }

    public static void main(String[]args){

        Scanner sc=new Scanner(System.in);
        CinemaManager manager=new CinemaManager();

        while(true){

            System.out.println("\n===== MENU =====");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");

            int choice=sc.nextInt();

            switch(choice){

                case 1:

                    System.out.print("Số phòng: ");
                    int rooms=sc.nextInt();

                    System.out.print("Số vé/phòng: ");
                    int tickets=sc.nextInt();

                    System.out.print("Số quầy: ");
                    int counters=sc.nextInt();

                    manager.startSimulation(rooms,tickets,counters);

                    break;

                case 2:
                    manager.pause();
                    break;

                case 3:
                    manager.resume();
                    break;

                case 4:

                    System.out.print("Nhập phòng (A,B,C...): ");
                    String room=sc.next();

                    System.out.print("Số vé thêm: ");
                    int count=sc.nextInt();

                    manager.addTickets(room,count);

                    break;

                case 5:
                    manager.statistics();
                    break;

                case 6:
                    manager.detectDeadlock();
                    break;

                case 7:

                    manager.shutdown();
                    System.out.println("Kết thúc chương trình.");
                    return;

            }

        }

    }

}