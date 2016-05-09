import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

//Class for Peterson's Lock
class Lock
{
	boolean f[]=new boolean[2];
    int victim;
	public void lock(MutiCore_Assgnmt1 m,int child,int level)
	{
		//System.out.println("Thread "+m.thread_id+" enters lock function of level "+level);
		int id=child%2;   //0 or 1 as per Peterson's algorithm
		this.victim=id;	
		this.f[id]=true;
		while(this.f[1-id] && this.victim==id)
		{
			/*try {
				m.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			System.out.println("Thread "+m.thread_id+" waiting with flag value of id "+(1-id)+" as "+this.f[1-id]);
		}
		//System.out.println("Thread "+m.thread_id+" exits level "+level);
	}
	public void unlock(MutiCore_Assgnmt1 m,int child,int level)
	{
		//System.out.println("Thread "+m.thread_id+" enters unlock level "+level);
		int id=child%2;
		this.f[id]=false;
		//System.out.println("Thread "+m.thread_id+" unlocks level "+level+" with flag value of "+" id "+id);
	}
}


public class MutiCore_Assgnmt1 extends Thread{ //implements Runnable {
	MutiCore_Assgnmt1 p[];
	static Lock L[];
	int n; //no of threads;
	static int m; //no of thread runs;
	static int delay;//inter-request delay;
	static int no_level;
	static long t1,t2; //for measuring elapsed time
	static double delay_mean;
	
	static int counter; //CS variable
	
	//Each lock treats one thread as thread 0 and the other as thread 1
	//Hence we need to store id of thread (0 or 1) for each level in the tree
	//int level[]; 
	int thread_id;
	int parent, child;
	
	public void initialize()
	{
		Scanner sc=new Scanner(System.in);
		Properties prop= new Properties();
		File file= new File("resources.properties");
		InputStream f;
		try{
			f= getClass().getClassLoader().getResourceAsStream("resources.properties");
		
		prop.load(f);
		}catch(Exception e){}
			
		n=Integer.parseInt(prop.getProperty("no_threads"));
		m=Integer.parseInt(prop.getProperty("no_runs"));//sc.nextInt();
		delay_mean=Double.parseDouble(prop.getProperty("delay_mean"));
		
		double time_tmp=100; //
		double lampda=delay_mean;   //vary mean time from 0 to 100
		double lampda1=time_tmp/lampda;
		double tmp=Math.random();
		
		//https://en.wikipedia.org/wiki/Exponential_distribution#Generating_exponential_variates
		//Generating exponential variates
		double expRandom = -(Math.log(tmp)/lampda1)*time_tmp;
		
		delay=(int)expRandom;
		
		if(n==1)
			no_level=1;
		else
			no_level=(int)(Math.log(n)/Math.log(2));
		
		//Assuming number of threads are of the order 2^x e.g. 8. Parent nodes are 2^x-1 i.e. 7
		p=new MutiCore_Assgnmt1[n+1];
		
		L=new Lock[n+1]; //ignore 0th and the nth record

		for(int i=1; i<=n; i++)
		{
			L[i]=new Lock();
			p[i]=new MutiCore_Assgnmt1();
			Thread t=new Thread(p[i]);
			
			//define n-1 Lock objects (number of parents in the tree)
			if(i!=n)
				L[i]=new Lock();

			// Threads are like leaf nodes. Hence their ids will start with (n-1)+1 i.e. last parent + 1 
			p[i].thread_id=n-1+i; 
		}
		
		//start measuring time
		t1 = System.currentTimeMillis();
		
		for(int i=1; i<=n; i++)
		{
			p[i].start();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MutiCore_Assgnmt1().initialize();
		
		while(Thread.activeCount()>1); //except main
		System.out.println("Start "+t1+" End "+t2+" Difference "+(t2-t1)+"ms"+" Delay "+delay+" Counter "+counter);
		  
	}

	public static void Critical_Section() {
        counter++;
    }
	public void lock(int child, int level)
	{
		parent=child/2;
		if(parent>=1)
		{
			L[parent].lock(this,child,level);
			System.out.println("Thread "+this.thread_id+" executed lock on "+parent);
			lock(child/2,level-1);
			unlock(child,level);
			System.out.println("Thread "+this.thread_id+" executed unlock on "+parent);
		}
		else
		{
			Critical_Section();
			System.out.println("Thread "+this.thread_id+" updated COUNTER to "+counter);
		}
	}
	public void unlock(int child, int level)
	{
		parent=child/2;
		L[parent].unlock(this,child,level);
	}
	
	public void run() {
		// TODO Auto-generated method stub
	    int i=1;
	    while(i<=m)
	    {
	    	child=this.thread_id;
	    	this.lock(child,no_level);     //lock
	    	try {
				this.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	i++;
	    }
	    t2 = System.currentTimeMillis(); // last thread will update the timer.
	    //System.out.println("Start "+t1+" End "+t2+" Difference "+(t2-t1)+"ms"+" Delay "+delay+" Counter "+counter);
	}
}
