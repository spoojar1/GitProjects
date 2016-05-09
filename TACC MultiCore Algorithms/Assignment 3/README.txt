1.Copy all the .java and .sh files provided in the zip file to your home directory on the Tacc machine or whereever you are going to run the program from.
2.Make sure all .java files and .sh file are in the same folder.
3. Make sure the compile.sh file has execute permissions. If not, please do chmod 777 compile.sh
4. The program accepts 5 parameters.
	#instructions (constant at 1 million as suggested by professor but you can change this)
	key space size
	distribution type (1 for read-dominated, 2 for mixed and 3 for write-dominated)
	#threads
	implementation type (1 for coarse grained and 2 for fine grained)
  To run the program for 1 data point for fine grained approach, you need to run the compile.sh bash file as shown below
eg: compile.sh 1000000 10000 2 16 2
5. The program will output the throughput after averaging through 10 runs.
Sample output:

c557-504.stampede(9)$ compile.sh 1000000 10000 2 16 2
Throughput : 3279.360524697684
c557-504.stampede(10)$
