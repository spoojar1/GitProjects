import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//Classes for CLH lock
class QNode{
	volatile boolean lock=false; 
}
class CLHLock implements Lock{
	volatile static AtomicReference<QNode> tail=new AtomicReference<QNode>(new QNode());
	ThreadLocal<QNode> myPred;
	ThreadLocal<QNode> myNode;
		
	public CLHLock()
	{
		myNode=new ThreadLocal<QNode>(){
		protected QNode initialValue(){
			return new QNode();
		}
		};
		
		myPred=new ThreadLocal<QNode>(){
			protected QNode initialValue(){
			return null;
			}
		};
	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		QNode qnode=myNode.get();
		qnode.lock=true;
		QNode pred=tail.getAndSet(qnode);
		myPred.set(pred);
		while(pred.lock){}		
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		QNode qnode=myNode.get();
		qnode.lock=false;
		myNode.set(myPred.get());
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub	
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}
}


//Class for Peterson's Lock
class PLock
{
	boolean f[]=new boolean[2];
    volatile int victim;
	public void lock(MultiCore_Assgnmt2 m,int child)
	{
		int id=child%2;   //0 or 1 as per Peterson's algorithm
		this.f[id]=true;
		this.victim=id;	
		
		while(this.f[1-id] && this.victim==id){}
	}
	public void unlock(MultiCore_Assgnmt2 m,int child)
	{
		int id=child%2;
		this.f[id]=false;
	}
}

//Class for TTAS Backoff
class Backoff {
	final int minDelay, maxDelay;
	int limit;
	final Random random;
	public Backoff(int min, int max) {
		minDelay = min;
		maxDelay = max;
		limit = minDelay;
		random = new Random();
	}
	public void backoff() throws InterruptedException {
		int delay = random.nextInt(limit);
		limit = Math.min(maxDelay, 2 * limit);
		Thread.sleep(0,delay);
	}
 }

public class MultiCore_Assgnmt2 extends Thread{ 

	//Common
	MultiCore_Assgnmt2 p[];
	volatile static int counter; //CS variable
	static String algo;
	int n; //no of threads;
	static int m; //no of thread runs;
	static int delay;//inter-request delay;
	static double delay_mean;
	static long t1,t2; //for measuring elapsed time
	volatile static AtomicBoolean state = new AtomicBoolean(false);
	
	//Peterson
	static PLock L[];
	int thread_id;
	int parent, child;

	//CLH
	CLHLock lock;
	static int max;
	public void initialize(String a,int b,int c)
	{
		//max=d;
		algo=a;
		m=1000;		//no of CS entries per thread
		n=b;
		delay_mean=c;
		double time_tmp=1000; // 1000 nanoseconds is 100 time units
		double lampda=delay_mean;   //vary mean time from 10 to 1000 (1 to 100 time units)
		double lampda1=time_tmp/lampda;
		double tmp=Math.random();
		
		//https://en.wikipedia.org/wiki/Exponential_distribution#Generating_exponential_variates
		//Generating exponential variates
		double expRandom = -(Math.log(tmp)/lampda1)*time_tmp;
		
		delay=(int)expRandom;
		//delay=500;
		
		//Assuming number of threads are of the order 2^x e.g. 8. Parent nodes are 2^x-1 i.e. 7
		p=new MultiCore_Assgnmt2[n+1];
		
		L=new PLock[n+1]; //ignore 0th and the nth record

		for(int i=1; i<=n; i++)
		{
			p[i]=new MultiCore_Assgnmt2();
			Thread t=new Thread(p[i]);
			
			//define n-1 Lock objects (number of parents in the tree)
			if(i!=n)
				L[i]=new PLock();

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

	public void Critical_Section() {
        counter++;
        //System.out.println("Thread "+thread_id+" updated COUNTER to "+counter);
    }
	public void lock(int child)
	{
		parent=child/2;
		if(parent>=1)
		{
			L[parent].lock(this,child);
			lock(child/2);
			unlock(child);
		}
		else
		{
			Critical_Section();
		}
	}
	public void unlock(int child)
	{
		parent=child/2;
		L[parent].unlock(this,child);
	}
	
	public void Peterson()
	{
		int i=1;
	    while(i<=m)
	    {
	    	child=this.thread_id;
	    	this.lock(child);     //lock
	    	try {
				this.sleep(0,delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	i++;
	    }
	    t2 = System.currentTimeMillis(); // last thread will update the timer.
	}
	
	public void TAS_lock(){
		while (state.getAndSet(true)) {}
	}
	
	public void TAS_unlock() {
		state.set(false);
	}
	
	public void TAS()
	{
		int i=1;
	    while(i<=m)
	    {
			TAS_lock();
			Critical_Section();
			TAS_unlock();
			try {
				Thread.sleep(0,delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++i;
	    }
	    t2 = System.currentTimeMillis(); // last thread will update the timer.
	}
	
	public void TTAS_lock(){
		while (true) {
			while (state.get()) {};
			if (!state.getAndSet(true))
				return;
		}
	}	
	
	public void TTAS_unlock() {
		state.set(false);
	}
	
	public void TTAS()
	{
		int i=1;
	    while(i<=m)
	    {
			TTAS_lock();
			Critical_Section();
			TTAS_unlock();
			try {
				Thread.sleep(0,delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++i;
	    }
	    t2 = System.currentTimeMillis(); // last thread will update the timer.
	}
	
	public void TTAS_Backoff(int MIN_DELAY, int MAX_DELAY)
	{
		int i=1;
	    while(i<=m)
	    {
			TTAS_Backoff_lock(MIN_DELAY,MAX_DELAY);
			Critical_Section();
			TTAS_Backoff_unlock();
			try {
				Thread.sleep(0,delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++i;
	    }
	    t2 = System.currentTimeMillis(); // last thread will update the timer.
	}
	
	public void TTAS_Backoff_lock(int MIN_DELAY,int MAX_DELAY)
	{
	    Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
		while (true) {
			while (state.get()) {};
			if (!state.getAndSet(true))
			 return;
			else {
				try {
					backoff.backoff();
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
	}
	
	public void TTAS_Backoff_unlock() {
		state.set(false);
	}
	
	public void CLH() 
	{
		// TODO to-generated method stub
		int i=1;
		while(i<=m)
		{
			lock=new CLHLock();
			lock.lock();
			Critical_Section();
			lock.unlock();
			try {
				Thread.sleep(0,delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++i;
		}
		t2 = System.currentTimeMillis(); // last thread will update the timer.
	}
	public void run() {
		// TODO Auto-generated method stub
		switch(algo)
		{
			case "peterson": 	Peterson();
								break;
			case "tas"	   : 	TAS();
								break;
			case "ttas"	   : 	TTAS();
								break;
			case "ttas-eb" : 	TTAS_Backoff(1,4);
								break;
			case "clh" 	   : 	CLH();
								break;
			default		   : 	break;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i=1;
		long t=0;
		int d=0;
		double avg=0.0,thr=0.0;
		
		while(i<=15)
		{
			counter=0;
			new MultiCore_Assgnmt2().initialize(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]));
			//new MultiCore_Assgnmt2().initialize(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
			while(Thread.activeCount()>1); //except main
			t+=t2-t1;
			d+=delay;
			//System.out.println("Start "+t1+" End "+t2+" Difference "+(t2-t1)+"ms"+" Delay "+delay+" Counter "+counter);
			++i;	
		}
		avg=(t/15);
		thr=((double)counter)/avg;
		//System.out.println("Average Delay : "+(d/15)+"	Throughput : "+thr+" Counter : "+counter);	
		System.out.println("Shared Counter Value: "+counter+" Throughput : "+thr);	
	}
}
