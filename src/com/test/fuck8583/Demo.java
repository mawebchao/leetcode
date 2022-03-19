package com.test.fuck8583;

import static java.lang.System.arraycopy;

public class Demo {
    public static void main(String[] args) {
        My8583Ans myans = new My8583Ans();
        //签到组包
        myans.frame8583QD(myans.fieldsSend, myans.pack);
        //打印出待发送的报文
        byte[] send = new byte[myans.pack.txLen];
        arraycopy(myans.pack.txBuffer, 0, send, 0, myans.pack.txLen);
        System.out.println("->send:");
        System.out.println(My8583Ans.bytesToHexString(send));
        System.out.println(myans.pack.toString());
        System.out.println(myans.getFields(myans.fieldsSend));
        //接收解析,假设收到的报文在recv中
        String recvstr = "007960000001386131003111080810003800010AC0001450021122130107200800085500323231333031343931333239303039393939393930363030313433303137303131393939390011000005190030004046F161A743497B32EAC760DF5EA57DF5900ECCE3977731A7EA402DDF0000000000000000CFF1592A";
        System.out.println("->recv:" + recvstr);
        byte[] recv = My8583Ans.hexStringToBytes(recvstr);
        // mypack.ans8583Fields(bt,bt.length,mypack.fieldsRecv);
        //解析
        System.out.println("开始解析...");
        int ret = myans.ans8583QD(recv, recv.length);
        if (ret == 0) {
            //打印出解析成功的各个域
            System.out.println("签到成功!");
            System.out.println(myans.getFields(myans.fieldsRecv));

        }
    }
}