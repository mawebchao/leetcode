package com.test.others;

public class MySnowFlake {
    //重新模拟雪花算法
    //1.
    public static void main(String[] args) {
        IdGen_SF idGen_sf = new IdGen_SF(1, 1, 31);
        for (int i = 0; i < 100; i++) {
            System.out.println(idGen_sf.nextId());
        }
        System.out.println(Long.toBinaryString(idGen_sf.sequencemask));
    }

}


class IdGen_SF {
    private long datacenterId;
    private long workerId;
    private long sequence;
    //机器开始运行的时间戳
    private long machinestart;
    //上次的毫秒数
    private long lasttimestamp;
    //sequence遮罩
    public long sequencemask = (-1L) ^ ((-1L) << 2);

    {
        machinestart = System.currentTimeMillis();
    }

    IdGen_SF(long datacenterId, long workerId, long sequence) {
        this.datacenterId = datacenterId;
        this.workerId = workerId;
        this.sequence = sequence;
    }

    //工作机器Id的左偏移量
    private int shift_workerid = 2;
    //datacenterid的左偏移量
    private int shift_datacenterid = 7;
    //时间戳的偏移量
    private int shift_timestamp = 12;

    public long nextId() {
        //此处做判断，由于可能存在同一个毫秒内需要生成多个id的情况
        //所以每次生成了一个id后，需要记录上次的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        //如果发生了这种情况
        if (currentTimeMillis == lasttimestamp) {
            //序列号加1
            sequence=(sequence+1)&sequencemask;
            if(sequence==0){
                //等待下一秒
                currentTimeMillis=nextMil(currentTimeMillis);
            }
        }else{
            sequence=0;
        }
        lasttimestamp = currentTimeMillis;
        return ((currentTimeMillis - machinestart) << shift_timestamp) |
                (datacenterId << shift_datacenterid) | (workerId << shift_workerid) | (sequence);
    }

    private long nextMil(long currentTimeMillis) {
        long res=System.currentTimeMillis();
        while(res==currentTimeMillis){res=System.currentTimeMillis();}
        return res;
    }
}