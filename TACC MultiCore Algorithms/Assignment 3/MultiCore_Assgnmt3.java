public class MultiCore_Assgnmt3 extends Thread {
	int thread_id;
	static BSTree tree; //object of interface BSTree
	static long t1,t2;	//long variable to calculate elapsed time
	static int no_instr,key_space,dist,no_threads,lock_type;
	
	public static void main(String[] args) {
		//accept user input
		no_instr=Integer.parseInt(args[0]);
		key_space=Integer.parseInt(args[1]);
		dist=Integer.parseInt(args[2]);
		no_threads=Integer.parseInt(args[3]);
		lock_type=Integer.parseInt(args[4]);
		
		MultiCore_Assgnmt3 m[];
		//assign class reference at runtime
		if(lock_type==1)	
			tree= new CoarseGrainedSync();
		else
			tree= new FineGrainedLazySync();
		
		double avg=0.0,thr=0.0;
		int j=0;
		long tmp=0;
		
		//loop for capturing average throughput for each data point
		while(j<10){
			new MultiCore_Assgnmt3();
			
			//Initialize the tree with number of nodes equal to half the key-space
			new Instructions().init(key_space,tree,-1);
			
			//create thread objects equal to no_threads
			m=new MultiCore_Assgnmt3[no_threads];
			t1 = System.currentTimeMillis();
			for(int i=0;i<no_threads;i++){
				m[i]=new MultiCore_Assgnmt3();
				Thread t=new Thread(m[i]);
				m[i].thread_id=i;
				m[i].start();
			}
			
			//wait till all threads(except main) have completed
			while(Thread.activeCount()>1);
			//validation
			tree.traverse(-1);
			t2 = System.currentTimeMillis();
			tmp+=t2-t1;
			//System.out.println("Run "+(j+1)+" : Start Time "+t1+" End time "+t2+" Difference "+(t2-t1)+"ms");
			j++;
		}

		avg=(tmp/j);
		thr=((double)no_instr*no_threads)/avg;
		System.out.println("Throughput : "+thr);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new Instructions(no_instr).prepare(key_space,dist,tree,this.thread_id);
	}
}
