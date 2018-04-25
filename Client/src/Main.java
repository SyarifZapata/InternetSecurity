import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

class Main {
    public static void main(String argv[]) throws Exception {

        Socket socket = new Socket("127.0.0.1", 3008);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println("GET /InterViewReligion.mp4 HTTP/1.1");
        pw.println("Host: localhost");
        pw.println("");
        pw.flush();

//        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String t;
//        while((t = br.readLine()) != null) System.out.println(t);
//        br.close();

        InputStream input = socket.getInputStream();


        byte[] bytes = new byte[102400]; // 100K

        boolean cond = true;
        long total = 0;
        long start = System.currentTimeMillis();

        while (cond) {
            int read = input.read(bytes);
            if (read > 0) {
                total += read;
            }
            long cost = System.currentTimeMillis() - start;
            if (read>0 && cost > 0 && System.currentTimeMillis() % 10 == 0) {
                System.out.println("Read " + total + " bytes, speed: " + total / cost + "KB/s");
            }else if(read == -1){
                cond = false;
                System.out.println(cost/1000.00);
                input.close();
                socket.close();
            }
        }
    }
}