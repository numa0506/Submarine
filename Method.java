public class Method{
    public void M11(double[][] attackMap, int x, int y){
        for(int i=-1;i<2;i++){  //11
            for(int j=-1;j<2;j++){
                try {
                    if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                        attackMap[y+i][x+j]++;
                    }    
                } catch (Exception e) {
                    continue;
                }
            }
        }
        attackMap[y][x]=0; //11
    }
    public void M12(double[][] attackMap, int x,int y){
        M11(attackMap, x, y);
    }
    public void M13(double[][]attackMap,int x,int y){
        for(int i=-1;i<2;i++){  
            for(int j=-1;j<2;j++){
                try {
                    if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                        attackMap[y+i][x+j]=0;
                    }    
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }
    public void M14(double[][]attackMap,int x,int y){
        attackMap[y][x]=attackMap[y][x]+5;
    }
    public void M15(double[][]attackMap){
        for(int x=1;x<=5;x++){
            for(int y=1;y<=5;y++){
                attackMap[y][x]=attackMap[y][x]+0.5;
            }
        }
    }
    public void M21(double[][] moveMap, int x,int y, String result){
        if(result.equals("波高し")||result.equals("はずれ")){ //21
            for(int i=-2;i<3;i++){  
                for(int j=-2;j<3;j++){
                    try {
                        if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                            moveMap[y+i][x+j]=moveMap[y+i][x+j]+0.5;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    
                }
            }
            for(int i=-1;i<2;i++){  
                for(int j=-1;j<2;j++){
                    try {
                        if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                            moveMap[y+i][x+j]=moveMap[y+i][x+j]+0.5;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    
                }
            }
        }                      //21

    }
    public void M22(double[][] moveMap, int x, int y, String result){
        if(result.equals("命中")||result.equals("命中、撃沈")){//22
            moveMap[y][x] =moveMap[y][x]+5;
        }//22
    }
    public void M23(){

    }
    public int[] M31(int[][] myPlace,double[][]attackMap){
        int[] aMax = new int[2];
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                if(myPlace[j][i]>=1){
                    for(int k=-1;k<2;k++){  
                        for(int l=-1;l<2;l++){
                            try {
                                if(j+k>=1&&j+k<=5&&i+l>=1&&i+l<=5){
                                    if(myPlace[j+k][i+l]==0){
                                        if(attackMap[j+k][i+l]>attackMap[aMax[0]][aMax[1]]){
                                            aMax[0]=j+k;
                                            aMax[1]=i+l;
                                        }
                                    }
                                }    
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("attack"+ aMax[0]+ " "+ aMax[1]);
        return aMax;
    }
    public int[] M32(int[][] myPlace, double[][] moveMap){
        int[] mMax = new int[2];
        for(int x=1;x<=5;x++){
            for(int y=1;y<=5;y++){
                if(myPlace[y][x] >=1){
                    if(moveMap[y][x]>moveMap[mMax[0]][mMax[1]]){
                        mMax[0] = y;
                        mMax[1] = x;
                    }
                }
            }
        }
        System.out.println("move" + mMax[0] +"," + mMax[1]);
        return mMax;
    }
    public void M33(int[][] myPlace,double[][] attackMap,double[][] moveMap,int[] aMax,int[]mMax){
        if(attackMap[aMax[0]][aMax[1]]>=moveMap[mMax[0]][mMax[1]]){
            System.out.println(aMax[0]+ " "+aMax[1] + "に攻撃！");
            M34(aMax);
        }else{
            M35(myPlace,moveMap,mMax);
        }
    }
    public void M34(int[] aMax){
        System.out.println(aMax[0] + "," + aMax[1] + "マスに攻撃");
    }
    public void M35(int[][] myPlace,double[][] moveMap,int[] mMax){//作り直し！
        //どうすれば、周囲のマスの探索と向きを同時に保存できるか
        int[] min = {mMax[0]-1,mMax[1]-1};
        
        for(int i=-1;i<2;i++){  
            for(int j=-1;j<2;j++){
                try {
                    if(mMax[0]+i>=1&&mMax[0]+i<=5&&mMax[1]+j>=1&&mMax[1]+j<=5){
                        if(moveMap[mMax[0]+i][mMax[1]+j]<moveMap[min[0]][min[1]]){
                            min[0]=mMax[0]+i;
                            min[1]=mMax[1]+j;
                        }
                    }    
                } catch (Exception e) {
                    continue;
                }
            }
        }
        for(int a=0;a<=1;a++){
            if(min[a]<=0){
                min[a]=1;
            }
        }
        System.out.println(min[0]+" "+min[1]);
        myPlace[min[0]][min[1]]=myPlace[mMax[0]][mMax[1]];
        myPlace[mMax[0]][mMax[1]]=0;
    }
}