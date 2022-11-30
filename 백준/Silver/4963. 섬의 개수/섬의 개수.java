
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main{
    public static int w,h; // w는 x, h는 y
    public static int[][] map;
    public static int cnt=0;
    public static int[] moveX = new int[]{0,0,1,-1,1,1,-1,-1};
    public static int[] moveY = new int[]{1,-1,0,0,-1,1,1,-1};

    public static void Setting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            //System.out.println();
            //System.out.println("w: " +w+" h: "+h);
            if(w==0 && h==0) break;
            cnt=0;
            map = new int[h][w];
            for (int y = 0; y < h; y++) {
                st= new StringTokenizer(br.readLine());
                for (int x = 0; x < w; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            solve();
            System.out.println(cnt);
        }
    }
    public static void solve(){
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]==1){
                    //System.out.println("(y,x) : ("+y+","+x+")");
                    cnt++;
                    dfs(y,x);
                }
            }
        }

    }
    public static void dfs(int y,int x){
        //System.out.println("DFS  (y,x) : ("+y+","+x+")");

        //방문--> 2로 변경
        map[y][x]=2;
        for(int dir=0;dir<8;dir++){
            int tempx= x+moveX[dir];
            int tempy = y + moveY[dir];

            if(tempx<0 || tempx>=w||tempy<0||tempy>=h)continue;
            //System.out.println("DFS____not success (y,x) : ("+tempy+","+tempx+") : "+map[tempy][tempx]);


            if(map[tempy][tempx]!=1)continue;
            //System.out.println("DFS____(y,x) : ("+tempy+","+tempx+")");
            dfs(tempy,tempx);
        }
    }
    public static void main(String[] args) throws IOException {
        Setting();

    }

}