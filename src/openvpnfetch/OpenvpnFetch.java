/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openvpnfetch;

import java.io.File;

/**
 *
 * @author sadpanda
 */
 public  class OpenvpnFetch {
     static String  folder_tar="~/VPN/";
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File tempfile;
         AppspotSocket appsock;
        if(args != null&&args.length==1)
        {
           folder_tar=args[0];   
        }

        tempfile=new File(folder_tar);
           if(!tempfile.isDirectory())
           {
               System.out.println("output folder "+folder_tar+" is not a valid folder!");
               System.exit(1);
           }
           
           
                                   try {
                            //Sleep for at least one second to simulate "startup".
                           // try {
                           //     Thread.sleep(1000 + random.nextInt(2000));
                           // } catch (InterruptedException ignore) {}
                            appsock= new AppspotSocket("vpngatefetch");
                             Thread tempthd=new Thread(new AppspotsockThread(appsock));
                              tempthd.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.exit(3);
                        }

        
    }
    
}


class AppspotsockThread implements Runnable
{
AppspotSocket appsock;
    public AppspotsockThread(AppspotSocket appsock){this.appsock=appsock;}
    @Override
    public void run() {  
                try {
            String restr=null;           
            restr=appsock.URLConmunicate("urlfopenvpn?qtype=http://www.vpngate.net/api/iphone/");           
            int delaynum=120;
            int speednum=2500000;
            String targetoutputfolder=OpenvpnFetch.folder_tar;
            appsock.resultAnalyst(restr,delaynum,speednum,targetoutputfolder);
            appsock.closeappsocket();
                    } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println( "please re-try ,Error:"+ex.toString());
            //System.exit(2); 
                                            }
    }
}