import java.io.*;
class tp
{
	public static boolean deleteDir(File dir)
	{
    	if (dir.isDirectory())
    	{
        	String[] children = dir.list();
        	for (int i=0; i<children.length; i++)
        	{
            	boolean success = deleteDir(new File(dir, children[i]));
            	if (!success)
            	{
                	return false;
            	}
        	}
        	return true;
    	}

    	// The directory is now empty so delete it
    	return dir.delete();
	}
	public static void main(String str[])
	{
		File file = new File("tp.java");
 		String tp = file.getAbsolutePath();
 		File file1 = new File(tp);
 		tp=file1.getParent();
 		System.out.println("	"+tp);
		if(deleteDir(new File(tp)))
			System.out.println("true");
		else
			System.out.println("false");
	}
}
