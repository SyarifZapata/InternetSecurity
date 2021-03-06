import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Main {
    public static void main(String argv[]) throws Exception {

        URL url = new URL("http://172.16.1.2:3008/hallo.img");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
//        con.setRequestProperty("Range","bytes=0-100");
        InputStream input = con.getInputStream();
        double size = con.getContentLength()/1.00;
        System.out.println(size);


        byte[] bytes = new byte[1024];

        boolean cond = true;
        long total = 0;
        long start = System.currentTimeMillis();

        while (cond) {
            int read = input.read(bytes);
            if (read > 0) {
                total += read;
            }
            long cost = System.currentTimeMillis() - start;
            if (read>0 && cost > 0) {
                System.out.println("Read " + total + " bytes, speed: " + total / cost + "KB/s "+ "finished "+
                        (int)((total/size)*100) + " %");
            }else if(read == -1){
                cond = false;
                System.out.println("time elapsed " + cost/1000.00 + " seconds");
                input.close();
            }
        }
    }
}